package models;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class Route {
    String location;
    String description;
    boolean disabled;

    public Route(String location, String description) {
        this.location = location;
        this.description = description;
        this.disabled = false;
    }
}
