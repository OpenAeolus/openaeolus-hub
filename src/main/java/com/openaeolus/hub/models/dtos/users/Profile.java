package com.openaeolus.hub.models.dtos.users;

import com.openaeolus.hub.models.User;
import lombok.Data;

@Data
public class Profile {

    private String username;
    private String email;
    private String firstname;
    private String lastname;

    public Profile(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.firstname = user.getFirstname();
        this.lastname = user.getLastname();
    }
}
