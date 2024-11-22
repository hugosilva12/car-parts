package authservice.authservice.security;


import authservice.authservice.domain.Feature;
import authservice.authservice.domain.Path;
import authservice.authservice.domain.User;
import authservice.authservice.dtos.global.LoginResponseReadDto;
import authservice.authservice.dtos.read.UserWithFeaturesReadDto;
import authservice.authservice.exceptions.BaseException;
import authservice.authservice.exceptions.TokenInvalidException;
import authservice.authservice.global.Messages;
import authservice.authservice.services.PathService;
import authservice.authservice.services.UserService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;
import java.util.stream.Collectors;


@Service
@Slf4j
public class TokenService {

    private final PathService pathService;

    private final UserService userService;

    private final Algorithm algorithm = Algorithm.HMAC256("S3cR$t");

    private final  long oneDay = System.currentTimeMillis() + (24 * 60 * 60 * 1000);

    public TokenService(PathService pathService, UserService userService){
        this.userService= userService;
        this.pathService= pathService;
    }
    public String createAccessToken(String username, String issuer, List<String> features) {

        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(oneDay))
                .withIssuer(issuer)
                .withClaim("features", features)
                .sign(algorithm);
    }

    public String createRefreshToken(String username, String issuer) {
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(oneDay))
                .withIssuer(issuer)
                .sign(algorithm);
    }

    public UserWithFeaturesReadDto validateToken(String token, String method, String path) throws JWTVerificationException, BaseException {
        boolean valid = false;
        Path pathObj = null;

        /**Path invalid **/
        System.out.println("Method: " + method);
        System.out.println("Path: " + path);
        pathObj = pathService.getPath(method, path);


        Algorithm algorithm = Algorithm.HMAC256("S3cR$t");
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        List<String> tokenFeatures = decodedJWT.getClaim("features").asList(String.class);
        for (String feature : tokenFeatures) {
            if (pathObj.containsFeature(feature)) {
                valid = true;
                break;
            }
        }
        /**Token invalid **/
        if (!valid) {
            throw new TokenInvalidException();
        }

        String username = decodedJWT.getSubject();
        Optional<User> user = userService.getUserByUsername(username);

        /**User invalid **/
        if(!user.isPresent()){
            throw new BaseException(Messages.USER_NOT_FOUND);
        }

        UserWithFeaturesReadDto userWithFeaturesReadDtoToReturn = new UserWithFeaturesReadDto(user.get().getUsername(), user.get().isEnabled(), user.get().getFeatures());
        userWithFeaturesReadDtoToReturn.setId(user.get().getId());
        return userWithFeaturesReadDtoToReturn;
    }

    public LoginResponseReadDto refreshToken(String authorizationHeader, String url) throws  RuntimeException {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Refresh token is missing");
        }

        String refreshToken = authorizationHeader.substring("Bearer ".length());

        Algorithm algorithm = Algorithm.HMAC256("S3cR$t");
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(refreshToken);
        String username = decodedJWT.getSubject();
        User user = userService.getUserByUsername(username).get();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        String accessToken = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(oneDay))
                .withIssuer(url)
                .withClaim("features", user.getFeatures().stream().map(Feature::getName).collect(Collectors.toList()))
                .sign(algorithm);
        return new LoginResponseReadDto(accessToken, refreshToken, null, null);
    }

}
