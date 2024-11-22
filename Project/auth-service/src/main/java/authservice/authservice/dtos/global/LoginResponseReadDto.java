package authservice.authservice.dtos.global;

public class LoginResponseReadDto {


    private String accessToken;
    private String refreshToken;

    private String feature;

    private String userAccountId;

    public LoginResponseReadDto(String accessToken, String refreshToken, String feature, String userAccountId) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.feature= feature;
        this.userAccountId=userAccountId;
    }

    /*
     * Getters and Setters
     */

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public String getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(String userAccountId) {
        this.userAccountId = userAccountId;
    }
}
