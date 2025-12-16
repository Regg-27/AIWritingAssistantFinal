package model.repository;

import model.domain.WritingSession;

import java.util.List;

public interface SessionRepository {

    void saveSession(WritingSession session);

    List<WritingSession> loadAllSessions();
}

