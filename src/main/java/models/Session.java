package models;

import lombok.Builder;
import lombok.Data;

import java.math.BigInteger;
import java.sql.Date;
import java.util.List;

@Data
@Builder
public class Session {
    private BigInteger id;
    private TimeSlot timeSlot;
    private User instructor;
    private Room room;
    private Group group;
    private Date date;
    private List<Status> status;
    private boolean yetToStart;
}

