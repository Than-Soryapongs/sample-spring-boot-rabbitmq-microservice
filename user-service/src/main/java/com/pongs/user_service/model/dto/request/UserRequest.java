package com.pongs.user_service.model.dto.request;

import jakarta.validation.constraints.NotBlank;

public class UserRequest {

    @NotBlank(message = "Username cannot be blank")
    String username;

    @NotBlank(message = "First name cannot be blank")
    String firstName;

    @NotBlank(message = "Last name cannot be blank")
    String lastName;

    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}

    public String getFirstName() {return firstName;}
    public void setFirstName(String firstName) {this.firstName = firstName;}

    public String getLastName() {return lastName;}
    public void setLastName(String lastName) {this.lastName = lastName;}
}
