package models;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Group {
    int id;
    String name;
    Course course;
    List<Student> students;
}
