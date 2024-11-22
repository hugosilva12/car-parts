package authservice.authservice.dtos.read;

import authservice.authservice.dtos.global.BasicDto;

public class UserReadDto extends BasicDto {

    private String username;

    private boolean isEnabled;


    /*
     * Getters and Setters
     */

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }
}
