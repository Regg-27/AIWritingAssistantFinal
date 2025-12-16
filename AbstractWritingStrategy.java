package model.strategy;
public abstract class AbstractWritingStrategy implements WritingStrategy {

    @Override
    public final String buildPrompt(String userInput) {
        String cleaned = sanitize(userInput);

        return generatePrompt(cleaned);
    }


    protected abstract String generatePrompt(String cleanedInput);

    protected String sanitize(String input) {
        if (input == null) return "";
        return input.strip();
    }
}

