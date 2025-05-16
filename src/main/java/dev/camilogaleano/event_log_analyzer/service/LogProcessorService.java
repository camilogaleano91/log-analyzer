package dev.camilogaleano.event_log_analyzer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.camilogaleano.event_log_analyzer.entity.ApplicationServerLog;
import dev.camilogaleano.event_log_analyzer.entity.LogEventEntity;
import dev.camilogaleano.event_log_analyzer.entity.ServerLog;
import dev.camilogaleano.event_log_analyzer.entity.State;
import dev.camilogaleano.event_log_analyzer.repository.LogEventRepository;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

@Service
public class LogProcessorService {

    private final LogEventRepository repository;
    private final ObjectMapper mapper = new ObjectMapper();

    public LogProcessorService(LogEventRepository repository) {
        this.repository = repository;
    }

    public void processLogFile(String path) {
        Map<String, ServerLog> tempStorage = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Map<String, Object> json = mapper.readValue(line, Map.class);
                String id = (String)json.get("id");
                State state = State.valueOf((String) json.get("state"));
                long timestamp = ((Long) json.get("timestamp"));

                ServerLog log;
                if (json.containsKey("type") && json.containsKey("host")) {
                    log = new ApplicationServerLog(
                            id, state, timestamp,
                            (String) json.get("type"),
                            (String) json.get("host")
                    );
                } else {
                    log = new ServerLog(id, state, timestamp);
                }

                if (tempStorage.containsKey(id)) {
                    ServerLog existing = tempStorage.get(id);
                    long duration = Math.abs(existing.getTimestamp() - log.getTimestamp());
                    boolean alert = duration > 4;

                    String type = log instanceof ApplicationServerLog ? ((ApplicationServerLog) log).getType() : null;
                    String host = log instanceof ApplicationServerLog ? ((ApplicationServerLog) log).getHost() : null;

                    LogEventEntity entity = new LogEventEntity(id, duration, type, host, alert);
                    repository.save(entity);
                    System.out.println("Saved event with id: " + id);
                    tempStorage.remove(id);
                } else {
                    tempStorage.put(id, log);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
