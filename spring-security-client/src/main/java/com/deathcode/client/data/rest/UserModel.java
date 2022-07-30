package com.deathcode.client.data.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserModel {
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String password;
    private String confirmPassword;
}
