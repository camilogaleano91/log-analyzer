# Event Log Analyzer

A Spring Boot command-line application that processes server log files, calculates event durations, and flags long-running events. Results are stored in an embedded HSQLDB database.

---

## Features

- Parses `STARTED` and `FINISHED` log pairs
- Calculates duration per event
- Flags events with duration > 4ms
- Stores results in HSQLDB

---

## Requirements

- Java 11
- Maven 3.6+
- Git

---

### 1. Clone the project

git clone https://github.com/camilogaleano91/log-analyzer.git
cd log-analyzer

---
### 2. Build the project

mvn clean install

---

### 3. Run the application

java -jar target/event-log-analyzer-0.0.1-SNAPSHOT.jar logfile.txt

---

### Disclaimer

During the initial setup, I encountered some issues related to Java 11 compatibility and Spring Boot dependency configuration. Resolving those took more time than expected, which limited the time available to implement full unit test coverage.

### Next steps

- Add comprehensive unit tests for LogProcessorService and other components
- Improve error handling for malformed log entries
- Add integration tests
