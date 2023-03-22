package models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor(staticName = "of")
public class Route {
    String location;
    String description;
    boolean disabled;
    Map<String, String> parameters;

    public Route(String location, String description) {
        this.location = location;
        this.description = description;
        this.disabled = false;
    }

    public String toUrl() {
        StringBuilder url = new StringBuilder(location);
        if (parameters != null && !parameters.isEmpty()) {
            url.append("?");
            for (Map.Entry<String, String> entry : parameters.entrySet()) {
                url.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
            url.deleteCharAt(url.length() - 1);
        }
        return url.toString();
    }
}
