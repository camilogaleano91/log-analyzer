package dev.camilogaleano.event_log_analyzer.runner;

import dev.camilogaleano.event_log_analyzer.service.LogProcessorService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class LogFileRunner implements CommandLineRunner {

    private final LogProcessorService processor;

    public LogFileRunner(LogProcessorService processor) {
        this.processor = processor;
    }

    @Override
    public void run(String... args) {
        if (args.length < 1) {
            System.out.println("Please provide path to logfile.txt");
            return;
        }

        processor.processLogFile(args[0]);
    }
}
