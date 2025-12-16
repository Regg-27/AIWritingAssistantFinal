package controller;

import model.WritingMode;
import model.observer.StatusObserver;
import model.strategy.AcademicStrategy;
import model.strategy.CreativeStrategy;
import model.strategy.ProfessionalStrategy;
import model.strategy.WritingStrategy;
import service.APIService;
import service.RequestFactory;
import view.MainFrame;
import javax.swing.*;
import model.domain.WritingSession;
import model.repository.JsonSessionRepository;
import model.repository.SessionRepository;
import java.time.LocalDateTime;

public class MainController {

    private final MainFrame view;
    private final StatusObserver statusObserver;
    private final APIService apiService;
    private final SessionRepository sessionRepo = new JsonSessionRepository();


    private WritingStrategy strategy;

    public MainController(MainFrame view) {
        this.view = view;
        this.statusObserver = view;
        this.apiService = new APIService();

        view.onGenerate(this::handleGenerate);
    }


    private void setMode(WritingMode mode) {
        switch (mode) {
            case PROFESSIONAL -> strategy = new ProfessionalStrategy();
            case ACADEMIC -> strategy = new AcademicStrategy();
            case CREATIVE -> strategy = new CreativeStrategy();
        }
    }


    private void handleGenerate() {
        String input = view.getInputText().trim();
        if (input.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter some text.");
            return;
        }

        WritingMode selectedMode = view.getSelectedMode();
        setMode(selectedMode);

        statusObserver.onStatusChanged("Contacting OpenAI...");
        view.setOutputText("");

        var params = RequestFactory.buildChatRequest(strategy, input);

        SwingWorker<String, Void> worker = new SwingWorker<>() {
            @Override
            protected String doInBackground() {
                return apiService.generateText(String.valueOf(params));
            }

            @Override
            protected void done() {
                try {
                    String result = get();
                    view.setOutputText(result);
                    statusObserver.onStatusChanged("Done");

                    WritingSession session = new WritingSession(
                            input,
                            result,
                            selectedMode,
                            LocalDateTime.now()
                    );

                    sessionRepo.saveSession(session);

                } catch (Exception e) {
                    view.setOutputText("Error: " + e.getMessage());
                    statusObserver.onStatusChanged("Error");
                }
            }
        };
        worker.execute();
    }
}

