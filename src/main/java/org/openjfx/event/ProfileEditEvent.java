package org.openjfx.event;

import org.openjfx.models.User;

import java.time.LocalDate;

public class ProfileEditEvent {

    private User user;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String email;
    private String bio;
    private String phoneNumber;
    private LocalDate birthday;

    public ProfileEditEvent(User user, String firstname, String lastname, String username, String password, String email, String bio, String phoneNumber, LocalDate birthday) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.bio = bio;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
        this.user = user;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getBio() {
        return bio;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public User getUser() {
        return user;
    }
}