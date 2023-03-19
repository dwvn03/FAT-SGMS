package models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Status {
    Student student;
    Session session;
    boolean attended;
}
