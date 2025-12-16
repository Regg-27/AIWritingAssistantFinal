package model.strategy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WritingStrategyTest {
    

    @Test
    void academicStrategyBuildsAcademicPrompt() {
        WritingStrategy strategy = new AcademicStrategy();
        String input = "This is a test.";

        String result = strategy.buildPrompt(input);

        assertNotNull(result);
        assertTrue(result.contains(input));
        assertTrue(result.toLowerCase().contains("academic"));
    }

    @Test
    void creativeStrategyBuildsCreativePrompt() {
        WritingStrategy strategy = new CreativeStrategy();
        String input = "This is a test.";

        String result = strategy.buildPrompt(input);

        assertNotNull(result);
        assertTrue(result.contains(input));
        assertTrue(result.toLowerCase().contains("creative"));
    }

    @Test
    void professionalStrategyBuildsProfessionalPrompt() {
        WritingStrategy strategy = new ProfessionalStrategy();
        String input = "This is a test.";

        String result = strategy.buildPrompt(input);

        assertNotNull(result);
        assertTrue(result.contains(input));
        assertTrue(result.toLowerCase().contains("professional"));
    }


    @Test
    void differentStrategiesProduceDifferentPrompts() {
        WritingStrategy academic = new AcademicStrategy();
        WritingStrategy creative = new CreativeStrategy();

        String input = "Same input text";

        String academicPrompt = academic.buildPrompt(input);
        String creativePrompt = creative.buildPrompt(input);

        assertNotEquals(academicPrompt, creativePrompt);
    }

    @Test
    void strategiesCanBeUsedPolymorphically() {
        WritingStrategy strategy = new AcademicStrategy();

        String result = strategy.buildPrompt("Test");

        assertTrue(result.contains("Test"));
    }


    @Test
    void emptyInputDoesNotCauseCrash() {
        WritingStrategy strategy = new ProfessionalStrategy();

        String result = strategy.buildPrompt("");

        assertNotNull(result);
        assertTrue(result.contains(""));
    }


    @Test
    void sameInputProducesSameOutputAcrossCalls() {
        WritingStrategy strategy = new AcademicStrategy();
        String input = "Consistency test";

        String first = strategy.buildPrompt(input);
        String second = strategy.buildPrompt(input);

        assertEquals(first, second);
    }

    @Test
    void strategiesDoNotShareState() {
        WritingStrategy s1 = new AcademicStrategy();
        WritingStrategy s2 = new AcademicStrategy();

        String input = "Independent instances";

        assertEquals(
                s1.buildPrompt(input),
                s2.buildPrompt(input)
        );
    }
}
