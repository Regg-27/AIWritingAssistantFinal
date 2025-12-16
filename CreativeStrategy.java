package model.strategy;

public class CreativeStrategy extends AbstractWritingStrategy {

    @Override
    protected String generatePrompt(String cleanedInput) {
        return "You are a highly creative writer. Rewrite the following text in a vivid, imaginative way:\n\n"
                + cleanedInput;
    }
}


