package authservice.authservice.templates;

import lombok.*;

@Data
public class MessageToSend {


    private String typeResponse;


    private String userId;

    private String typeUser;

    /*
     * Getters and Setters y
     */

    public String getTypeResponse() {
        return typeResponse;
    }

    public void setTypeResponse(String typeResponse) {
        this.typeResponse = typeResponse;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTypeUser() {
        return typeUser;
    }

    public void setTypeUser(String typeUser) {
        this.typeUser = typeUser;
    }
}
