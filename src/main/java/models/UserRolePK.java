package models;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserRolePK implements Serializable {
    private User user;
    private Role role;
}
