package authservice.authservice.exceptions;

import authservice.authservice.dtos.global.ErrorResponseDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class UnauthorisedException extends RuntimeException {
    private ErrorResponseDto errorResponseDto;

    public UnauthorisedException(String message, String developerMessage) {
        super(message);
        errorResponseDto = new ErrorResponseDto();
        errorResponseDto.setDeveloperMsg(developerMessage);
        errorResponseDto.setErrorMsg(message);
        errorResponseDto.setStatus(HttpStatus.UNAUTHORIZED);
    }
}