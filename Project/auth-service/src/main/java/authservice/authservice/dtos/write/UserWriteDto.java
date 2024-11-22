package authservice.authservice.dtos.write;

import authservice.authservice.dtos.global.BasicDto;

public class UserWriteDto extends BasicDto {


    private String username;

    private String password;

    private String confirmPassword;

    /*
     * Getters and Setters y
     */

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
