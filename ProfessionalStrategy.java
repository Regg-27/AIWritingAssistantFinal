package model.strategy;

public class ProfessionalStrategy extends AbstractWritingStrategy {

    @Override
    protected String generatePrompt(String cleanedInput) {
        return "You are a professional, formal writer. Rewrite the text clearly and concisely:\n\n"
                + cleanedInput;
    }
}


