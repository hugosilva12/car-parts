package authservice.authservice.dtos.global;

public class FailLogin {

    private String message;

    public FailLogin(String message) {
        this.message = message;
    }
    /*
     * Getters and Setters
     */

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
