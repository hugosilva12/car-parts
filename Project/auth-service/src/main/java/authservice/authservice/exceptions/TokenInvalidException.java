package authservice.authservice.exceptions;

public class TokenInvalidException extends BaseException {

    public TokenInvalidException() {
        super("Token is invalid");
    }
}
