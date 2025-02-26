package com.example.cloudgateway;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "Actor Not Found")
public class UnauthorizeResponse extends Exception {
    // ...
}