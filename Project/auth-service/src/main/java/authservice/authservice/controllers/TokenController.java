package authservice.authservice.controllers;

import authservice.authservice.dtos.global.LoginResponseReadDto;
import authservice.authservice.dtos.read.UserWithFeaturesReadDto;
import authservice.authservice.exceptions.BaseException;
import authservice.authservice.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static authservice.authservice.global.ApiPaths.TokenPath.PATH;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;


@RestController
@RequestMapping(value = PATH)
public class TokenController {

    private final TokenService tokenService;


    @Autowired
    public TokenController(TokenService tokenService ) {
        this.tokenService = tokenService;
    }


    @GetMapping("/refresh")
    public ResponseEntity<LoginResponseReadDto> refreshToken(HttpServletRequest request)  {
        return ResponseEntity.ok().body(tokenService.refreshToken(request.getHeader(AUTHORIZATION), request.getRequestURL().toString()));
    }

    @PostMapping("/validateToken")
    public ResponseEntity<UserWithFeaturesReadDto> validateToken(@RequestParam String token,
                                                                 @RequestParam String method,
                                                                 @RequestParam String path , HttpServletRequest httpServletRequest) throws BaseException {
            return ResponseEntity.ok().body(tokenService.validateToken(token, method, path));
        }


}
