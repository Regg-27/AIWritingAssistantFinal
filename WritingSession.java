package model.domain;

import model.WritingMode;

import java.time.LocalDateTime;

public final class WritingSession {

    private final String userInput;
    private final String aiOutput;
    private final WritingMode mode;
    private final LocalDateTime timestamp;

    public WritingSession(String userInput, String aiOutput, WritingMode mode, LocalDateTime timestamp) {
        this.userInput = userInput;
        this.aiOutput = aiOutput;
        this.mode = mode;
        this.timestamp = timestamp;
    }

    public String getUserInput() {
        return userInput;
    }

    public String getAiOutput() {
        return aiOutput;
    }

    public WritingMode getMode() {
        return mode;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Session[" + timestamp + "] " + mode + "\nInput: " + userInput + "\nOutput: " + aiOutput;
    }
}
