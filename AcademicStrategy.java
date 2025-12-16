package model.strategy;

public class AcademicStrategy extends AbstractWritingStrategy {

    @Override
    protected String generatePrompt(String cleanedInput) {
        return "You are an academic writer. Rewrite the text with scholarly tone and structure:\n\n"
                + cleanedInput;
    }
}


