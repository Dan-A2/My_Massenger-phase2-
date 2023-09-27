package org.openjfx.listeners.entrance;

import org.openjfx.controller.registrationController.RegisterController;
import org.openjfx.event.RegistrationEvent;

public class RegistrationListenerImplementation implements RegistrationListener{

    private RegisterController registerController = new RegisterController();

    @Override
    public void listen(RegistrationEvent event) {
        registerController.register(event.getFirstname(), event.getLastname(), event.getUsername(), event.getPassword(), event.getEmail(), event.getBio(), event.getPhoneNumber(), event.getBirthday());
    }

}