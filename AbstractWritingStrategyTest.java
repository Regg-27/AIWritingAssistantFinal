package model.strategy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AbstractWritingStrategyTest {

    @Test
    void creativeStrategyProducesDistinctPrompt() {
        WritingStrategy s = new CreativeStrategy();
        String result = s.buildPrompt("hello world");

        assertTrue(result.toLowerCase().contains("creative"));
        assertTrue(result.contains("hello world"));
    }


    @Test
    void professionalStrategyProducesDistinctPrompt() {
        WritingStrategy s = new ProfessionalStrategy();
        String result = s.buildPrompt("hello world");

        assertTrue(result.toLowerCase().contains("professional"));
        assertTrue(result.contains("hello world"));
    }


    @Test
    void academicStrategyProducesDistinctPrompt() {
        WritingStrategy s = new AcademicStrategy();
        String result = s.buildPrompt("hello world");

        assertTrue(result.toLowerCase().contains("academic"));
        assertTrue(result.contains("hello world"));
    }


    @Test
    void sanitizeRemovesLeadingTrailingWhitespace() {
        WritingStrategy s = new CreativeStrategy();
        String result = s.buildPrompt("   test   ");

        assertTrue(result.contains("test"));
        assertFalse(result.contains("   test   "));
    }


    @Test
    void polymorphismWorksCorrectly() {
        WritingStrategy strategy = new AcademicStrategy();
        String result = strategy.buildPrompt("Test Input");

        assertNotNull(result);
        assertTrue(result.contains("Test Input"));
    }
}

