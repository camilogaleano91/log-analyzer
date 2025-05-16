package dev.camilogaleano.event_log_analyzer.repository;

import dev.camilogaleano.event_log_analyzer.entity.LogEventEntity;
import org.springframework.data.repository.CrudRepository;

public interface LogEventRepository extends CrudRepository<LogEventEntity, String> {

}
