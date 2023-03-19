package models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Room {
    int id;
    String name;
}
