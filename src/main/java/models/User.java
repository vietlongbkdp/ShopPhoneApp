package models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    private int id;
    private String userName;
    private String password;
    private String fullName;
    private String email;
    private String address;
    private String phone;
    private EGender gender;
    private Date dob;
    private Role role;
}
