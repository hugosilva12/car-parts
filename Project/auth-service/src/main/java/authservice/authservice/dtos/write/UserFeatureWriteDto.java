package authservice.authservice.dtos.write;

public class UserFeatureWriteDto {

    public String userId;

    public String name;

    public String username;

    /*
     * Getters and Setters y
     */

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
