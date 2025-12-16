package model.repository;

import model.WritingMode;
import model.domain.WritingSession;
import org.junit.jupiter.api.*;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonSessionRepositoryTest {

    private static final String TEST_FILE = "sessions.json";

    @BeforeEach
    void cleanFile() {
        File f = new File(TEST_FILE);
        if (f.exists()) f.delete();
    }

    @Test
    void saveAndLoadSingleSession() {
        SessionRepository repo = new JsonSessionRepository();

        WritingSession session = new WritingSession(
                "input text",
                "output text",
                WritingMode.CREATIVE,
                LocalDateTime.now()
        );

        repo.saveSession(session);

        List<WritingSession> loaded = repo.loadAllSessions();

        assertEquals(1, loaded.size());
        assertEquals("input text", loaded.get(0).getUserInput());
        assertEquals("output text", loaded.get(0).getAiOutput());
        assertEquals(WritingMode.CREATIVE, loaded.get(0).getMode());
    }


    @Test
    void loadingEmptyFileReturnsEmptyList() {
        SessionRepository repo = new JsonSessionRepository();
        List<WritingSession> loaded = repo.loadAllSessions();

        assertTrue(loaded.isEmpty());
    }


    @Test
    void multipleSessionsAreSavedAndLoadedCorrectly() {
        SessionRepository repo = new JsonSessionRepository();

        repo.saveSession(new WritingSession("A", "B", WritingMode.ACADEMIC, LocalDateTime.now()));
        repo.saveSession(new WritingSession("C", "D", WritingMode.PROFESSIONAL, LocalDateTime.now()));

        List<WritingSession> loaded = repo.loadAllSessions();

        assertEquals(2, loaded.size());
    }
}

