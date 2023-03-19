package models;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Student {
    int id;
    User user;
    String dept_code;
    List<Group> groups;
    List<Status> statuses;
}
