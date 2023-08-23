package com.example.dslist.events;

import com.example.dslist.dto.RegisterUserDTO;
import com.example.dslist.dto.UserDTO;
import com.example.dslist.entities.User;
import org.springframework.context.ApplicationEvent;

public class RegistrationCompleteEvent extends ApplicationEvent {
    private User user;
    private String confirmationURL;

    public RegistrationCompleteEvent(User user, String confirmationUrl) {
        super(user);
        this.user = user ;
        this.confirmationURL = confirmationUrl;
    }

    public User getUser() {
        return user;
    }

    public void setRegisterUserDTO(User user) {
        this.user = user;
    }

    public String getConfirmationURL() {
        return confirmationURL;
    }

    public void setConfirmationURL(String confirmationURL) {
        this.confirmationURL = confirmationURL;
    }
}
