package model.repository;

import model.WritingMode;
import model.domain.WritingSession;
import java.io.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.*;

public class JsonSessionRepository implements SessionRepository {

    private static final String FILE_NAME = "sessions.json";

    @Override
    public void saveSession(WritingSession session) {
        try (FileWriter fw = new FileWriter(FILE_NAME, true)) {

            String json = String.format(
                    "{\"mode\":\"%s\",\"input\":\"%s\",\"output\":\"%s\",\"timestamp\":\"%s\"}\n",
                    session.getMode(),
                    escape(session.getUserInput()),
                    escape(session.getAiOutput()),
                    session.getTimestamp()
            );

            fw.write(json);

        } catch (IOException e) {
            throw new RuntimeException("Failed to save session: " + e.getMessage(), e);
        }
    }

    @Override
    public List<WritingSession> loadAllSessions() {
        List<WritingSession> sessions = new ArrayList<>();

        Path path = Path.of(FILE_NAME);
        if (!Files.exists(path)) return sessions;

        try {
            List<String> lines = Files.readAllLines(path);

            for (String line : lines) {
                if (line.isBlank()) continue;

                Map<String, String> parsed = parseLine(line);

                WritingMode mode = WritingMode.valueOf(parsed.get("mode"));
                String input = unescape(parsed.get("input"));
                String output = unescape(parsed.get("output"));
                LocalDateTime timestamp = LocalDateTime.parse(parsed.get("timestamp"));

                sessions.add(new WritingSession(input, output, mode, timestamp));
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load sessions: " + e.getMessage(), e);
        }

        return sessions;
    }

    private String escape(String s) {
        return s.replace("\"", "\\\"");
    }

    private String unescape(String s) {
        return s.replace("\\\"", "\"");
    }

    private Map<String, String> parseLine(String jsonLine) {
        Map<String, String> map = new HashMap<>();

        String trimmed = jsonLine.trim();
        trimmed = trimmed.substring(1, trimmed.length() - 1); // remove { }

        for (String part : trimmed.split("\",\"")) {
            String[] kv = part.replace("\"", "").split(":", 2);
            map.put(kv[0], kv[1]);
        }
        return map;
    }
}

