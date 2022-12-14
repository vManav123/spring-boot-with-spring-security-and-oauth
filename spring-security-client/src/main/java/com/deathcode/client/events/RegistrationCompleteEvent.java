package com.deathcode.client.events;

import com.deathcode.client.data.entity.User;
import lombok.Builder;
import lombok.Data;
import org.springframework.context.ApplicationEvent;

@Builder
@Data
public class RegistrationCompleteEvent extends ApplicationEvent {
    private User user;
    private String applicationUrl;

    public RegistrationCompleteEvent(User user , String applicationUrl) {
        super(user);
        this.user=user;
        this.applicationUrl=applicationUrl;
    }
}
