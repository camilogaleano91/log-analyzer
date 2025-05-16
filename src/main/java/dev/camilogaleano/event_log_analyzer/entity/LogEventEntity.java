package dev.camilogaleano.event_log_analyzer.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class LogEventEntity {

    @Id
    private String id;
    private long duration;
    private String type;
    private String host;
    private boolean alert;

    public LogEventEntity() {}

    public LogEventEntity(String id, long duration, String type, String host, boolean alert) {
        this.id = id;
        this.duration = duration;
        this.type = type;
        this.host = host;
        this.alert = alert;
    }
}
