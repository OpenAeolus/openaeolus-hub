package com.openaeolus.hub.models.dtos.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreationDTO {

    private String username;
    private String password;
    private String email;
    private String firstname;
    private String lastname;

}
