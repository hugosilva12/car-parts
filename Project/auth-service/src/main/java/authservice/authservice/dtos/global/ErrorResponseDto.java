package authservice.authservice.dtos.global;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;


@RequiredArgsConstructor
public class ErrorResponseDto {

    private HttpStatus status;
    private String errorMsg;
    private String developerMsg;

    /*
     * Getters and Setters
     */

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getDeveloperMsg() {
        return developerMsg;
    }

    public void setDeveloperMsg(String developerMsg) {
        this.developerMsg = developerMsg;
    }
}
