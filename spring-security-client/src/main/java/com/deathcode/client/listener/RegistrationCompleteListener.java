package com.deathcode.client.listener;

import com.deathcode.client.data.entity.User;
import com.deathcode.client.events.RegistrationCompleteEvent;
import com.deathcode.client.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class RegistrationCompleteListener implements ApplicationListener<RegistrationCompleteEvent> {
    @Autowired
    private UserService userService;

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.saveToken(user,token);

        String url = event.getApplicationUrl()+"verifyRegistration?token="+token;
        log.info("{ VerificationUrl : {} }",url);
    }
}
