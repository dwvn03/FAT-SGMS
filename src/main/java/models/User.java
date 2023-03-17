package models;

import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Data
@Builder
public class User {
    int id;
    String name;
    String email;
    String avatar;
    boolean gender;
    Date dob;
    String role;
}
