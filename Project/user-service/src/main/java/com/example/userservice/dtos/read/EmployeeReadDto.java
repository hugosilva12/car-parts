package com.example.userservice.dtos.read;

import com.example.userservice.dtos.global.BasicDto;
import com.example.userservice.global.TypeEmployee;
import com.example.userservice.global.UserState;

public class EmployeeReadDto extends BasicDto {

    private String username;

    private String firstName;

    private String lastName;

    private String email;


    private UserState userState;


    private TypeEmployee typeEmployee;

    /*
     * Getters and Setters
     */

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserState getUserState() {
        return userState;
    }

    public void setUserState(UserState userState) {
        this.userState = userState;
    }

    public TypeEmployee getTypeEmployee() {
        return typeEmployee;
    }

    public void setTypeEmployee(TypeEmployee typeEmployee) {
        this.typeEmployee = typeEmployee;
    }
}
