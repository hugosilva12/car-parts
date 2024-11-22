package authservice.authservice.services;

import authservice.authservice.domain.Feature;
import authservice.authservice.domain.User;
import authservice.authservice.exceptions.BaseException;
import authservice.authservice.global.Messages;
import authservice.authservice.repository.FeatureRepository;
import authservice.authservice.repository.UserRepository;
import authservice.authservice.templates.MessageToSend;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class UserService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final FeatureRepository featureRepository;

    private  ObjectMapper objectMapper = new ObjectMapper();


    private ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    public UserService(UserRepository userRepository, FeatureRepository featureRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.featureRepository= featureRepository;
    }

    public User save(User entityToInsert) throws BaseException {
        Optional<User> user = getUserByUsername(entityToInsert.getUsername());
        if (user.isPresent())
            throw new BaseException(Messages.USER_EXISTS);

        entityToInsert.setEnabled(true);
        entityToInsert.setPassword(passwordEncoder.encode(entityToInsert.getPassword()));
        return userRepository.save(entityToInsert);
    }

    /**
     * This method is only used to change the password
     */
    public User updateUser(UUID uuid, User userToUpdate) throws BaseException {
        Optional<User> user = getById(uuid);
        if (!user.isPresent()) {
            throw new BaseException(Messages.USER_NOT_FOUND);
        }
        if (!passwordEncoder.matches(user.get().getPassword(), userToUpdate.getPassword())) {
            user.get().setPassword(passwordEncoder.encode(userToUpdate.getPassword()));
        }

        return userRepository.save(user.get());
    }

    public void addFeatureToUser(UUID uuid, String featureName) throws  BaseException {
        Optional<User> user = userRepository.findById(uuid);
        Optional<Feature> role = featureRepository.findByName(featureName);
        if(role.isPresent() && user.isPresent()){
            user.get().addFeature(role.get());
        }else{
            throw new BaseException("Feature or user invalid");
        }
        System.out.println("Feature added!");
    }

    public void enableUser(UUID uuid) throws BaseException {
        Optional<User> user = userRepository.findById(uuid);
        if (!user.isPresent()) {
            throw new BaseException(Messages.USER_NOT_FOUND);
        }
        user.get().setEnabled(true);
        userRepository.save(user.get());
    }

    public void disableUser(UUID uuid) throws BaseException {
        Optional<User> user = userRepository.findById(uuid);
        if (!user.isPresent()) {
            throw new BaseException(Messages.USER_NOT_FOUND);
        }
        user.get().setEnabled(false);
        userRepository.save(user.get());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).get();
        Collection<SimpleGrantedAuthority> authorities = user.getFeatures()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> getById(UUID uuid) {
        return userRepository.findById(uuid);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }


    @RabbitListener(queues = "auth_service_queue")
    public void listenerMessagesOfQueue(String message)  {
        System.out.println("Message of User Service... ");
        Boolean isValid = false;
        JsonNode responseUpdate = null;
        try {
            responseUpdate = objectMapper.readTree(message);
            isValid = true;
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
        if(isValid){
            operationQueue(responseUpdate);
        }
    }

    public void operationQueue(JsonNode responseUpdate){
        if(responseUpdate.get("typeOperation").textValue().equals("SAVE")){
            User user = new User();
            user.setId(UUID.fromString(responseUpdate.get("userId").textValue()));
            user.setUsername(responseUpdate.get("username").textValue());
            user.setPassword(responseUpdate.get("password").textValue());
            user.setUserAccountId(UUID.fromString(responseUpdate.get("userId").textValue()));
            System.out.println("Type user " + user.getUserAccountId() );
            try {
               User userCreated = save(user);
               addFeatureToUser(userCreated.getId(),responseUpdate.get("typeUser").textValue());
               sendMessageToVerifyIdUserExists("CREATED",responseUpdate.get("userId").textValue(),responseUpdate.get("typeUser").textValue() );
            } catch (BaseException e) {
                sendMessageToVerifyIdUserExists("FAIL",responseUpdate.get("userId").textValue(),responseUpdate.get("typeUser").textValue());
            }
        }else if (responseUpdate.get("typeOperation").textValue().equals("DELETE")){
            User user = getUserByUsername(responseUpdate.get("username").textValue()).get();
            user.setEnabled(false);
            userRepository.save(user);
        }
    }
    public void sendMessageToVerifyIdUserExists(String messageToSend,String userid, String userType) {
        MessageToSend message = new MessageToSend();
        message.setUserId(userid);
        message.setTypeUser(userType);
        message.setTypeResponse(messageToSend);
        try {
            System.out.println("Sending message to User service...");
            this.rabbitTemplate.convertAndSend("user_service_queue_exchange", "user_service_queue_routingKey", ow.writeValueAsString(message));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}