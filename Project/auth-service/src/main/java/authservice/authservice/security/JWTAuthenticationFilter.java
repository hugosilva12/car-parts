package authservice.authservice.security;

import authservice.authservice.dtos.global.FailLogin;
import authservice.authservice.dtos.global.LoginResponseReadDto;
import authservice.authservice.global.Messages;
import authservice.authservice.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    private ObjectMapper mapper=new ObjectMapper();

    private final UserService userService;
    private final TokenService tokenService;

    public JWTAuthenticationFilter(UserService userService,TokenService tokenService , AuthenticationManager authenticationManager){
        this.tokenService = tokenService;
        this.userService=userService;
        this.authenticationManager= authenticationManager;
    }

    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)  {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Optional<authservice.authservice.domain.User> user = userService.getUserByUsername(username);

        /** Not found user **/
        if (!user.isPresent()) {
            response.setContentType(APPLICATION_JSON_VALUE);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            FailLogin failLogin = new FailLogin(Messages.INVALID_USER);
            new ObjectMapper().writeValue(response.getOutputStream(), failLogin);
        }
        /** Valid user **/
        if (user.get().isEnabled()) {
            System.out.println("User is attempting to login with username "+ username + "  and password " + password);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
            return authenticationManager.authenticate(authenticationToken);
        }

        /** Removed User**/
        response.setContentType(APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        FailLogin failLogin = new FailLogin("Dear " + username + "," + Messages.REMOVED_USER);
        new ObjectMapper().writeValue(response.getOutputStream(), failLogin);
        return null;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        Object principal = authResult.getPrincipal();
        if (!(principal instanceof User)) {
            String invalidCredentialsMessage = "Invalid Credentials";
            throw new BadCredentialsException(invalidCredentialsMessage);

        }

        User user = (User) authResult.getPrincipal();
        Optional<authservice.authservice.domain.User> userAccount = userService.getUserByUsername(user.getUsername());

        List<String> features = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        String accessToken = tokenService.createAccessToken(user.getUsername(), request.getRequestURL().toString(), features);
        LoginResponseReadDto tokens = new LoginResponseReadDto(accessToken, null, features.get(0), userAccount.get().getUserAccountId() != null ? userAccount.get().getUserAccountId().toString() : "null");
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), tokens);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
       super.unsuccessfulAuthentication(request, response, failed);
    }


}