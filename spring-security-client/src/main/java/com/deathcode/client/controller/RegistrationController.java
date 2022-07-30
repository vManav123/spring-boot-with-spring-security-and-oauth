package com.deathcode.client.controller;

import com.deathcode.client.data.entity.User;
import com.deathcode.client.data.rest.UserModel;
import com.deathcode.client.events.RegistrationCompleteEvent;
import com.deathcode.client.helper.AppUtility;
import com.deathcode.client.service.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

import static com.deathcode.client.helper.AppUtility.parseUserEntity;
import static com.deathcode.client.helper.AppUtility.parseUserModel;

@RestController
@RequestMapping(path = "/user-security/v1/api/user")
public class RegistrationController {

    @Autowired
    private UserService userService;
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @PostMapping(path = "/register")
    @SneakyThrows
    public ResponseEntity<UserModel> insertUser(@RequestBody UserModel userModel, HttpServletRequest request)
    {

        User user = parseUserEntity(userModel);
        user = Optional
                .ofNullable(userService.insert(user))
                .orElseThrow(Exception::new);

        String applicationUrl = "http://"+request.getServerName()+":"+request.getServerPort()+request.getRequestURI();
        eventPublisher.publishEvent(new RegistrationCompleteEvent(user,applicationUrl));
        return ResponseEntity.ok(parseUserModel(user));
    }

    @GetMapping(path = "/getAll")
    public ResponseEntity<List<UserModel>> getAllUserModel()
    {
        return ResponseEntity.ok(
                userService
                        .getAll()
                        .stream()
                        .map(AppUtility::parseUserModel)
                        .toList()
        );
    }
}
