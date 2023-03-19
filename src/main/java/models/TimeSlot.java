package models;

import lombok.Builder;
import lombok.Data;

import java.sql.Time;

@Data
@Builder
public class TimeSlot {
    int id;
    Time start_time;
    Time end_time;
}
