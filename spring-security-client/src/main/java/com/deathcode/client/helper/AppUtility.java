package com.deathcode.client.helper;

import com.deathcode.client.data.entity.User;
import com.deathcode.client.data.rest.UserModel;
import lombok.SneakyThrows;

public class AppUtility {
    public static UserModel parseUserModel(final User user)
    {
        return UserModel
                .builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .password(user.getPassword())
                .emailAddress(user.getEmailAddress())
                .build();
    }

    @SneakyThrows
    public static User parseUserEntity(final UserModel user)
    {
        if(!user.getPassword().equals(user.getConfirmPassword()))
            throw new Exception("User password is not matching");

        String role = "USER";
        if(user.getLastName().toLowerCase().contains("admin"))
            role="ADMIN";
        return User
                .builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .password(user.getPassword())
                .role(role)
                .emailAddress(user.getEmailAddress())
                .active(true)
                .build();
    }

}
