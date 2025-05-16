package dev.camilogaleano.event_log_analyzer.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationServerLog extends ServerLog {

    private String type;
    private String host;

    public ApplicationServerLog(String id, State state, long timestamp, String type, String host) {
        super(id, state, timestamp);
        this.type = type;
        this.host = host;
    }


}
