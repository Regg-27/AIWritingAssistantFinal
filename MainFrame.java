package view;

import model.WritingMode;
import model.observer.StatusObserver;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame implements StatusObserver {

    private final JTextArea inputArea;
    private final JTextArea outputArea;
    private final JComboBox<WritingMode> modeSelector;
    private final JButton generateButton;
    private final JLabel statusLabel;

    public MainFrame() {
        super("AI Writing Assistant");

        inputArea = new JTextArea(6, 50);
        outputArea = new JTextArea(10, 50);
        outputArea.setEditable(false);
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);
        outputArea.setFont(new Font("Serif", Font.PLAIN, 14));
        outputArea.setMargin(new Insets(10, 10, 10, 10));


        modeSelector = new JComboBox<>(WritingMode.values());
        generateButton = new JButton("Generate");
        statusLabel = new JLabel("Ready");

        JPanel top = new JPanel(new BorderLayout());
        top.add(new JLabel("Input:"), BorderLayout.NORTH);
        top.add(new JScrollPane(inputArea), BorderLayout.CENTER);

        JPanel middle = new JPanel(new FlowLayout(FlowLayout.LEFT));
        middle.add(new JLabel("Mode:"));
        middle.add(modeSelector);
        middle.add(generateButton);

        JPanel bottom = new JPanel(new BorderLayout());
        bottom.add(new JLabel("Output:"), BorderLayout.NORTH);
        bottom.add(new JScrollPane(outputArea), BorderLayout.CENTER);
        bottom.add(statusLabel, BorderLayout.SOUTH);

        setLayout(new BorderLayout());
        add(top, BorderLayout.NORTH);
        add(middle, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public String getInputText() {
        return inputArea.getText();
    }

    public void setOutputText(String text) {
        outputArea.setText(text);
    }

    public WritingMode getSelectedMode() {
        return (WritingMode) modeSelector.getSelectedItem();
    }

    public void setStatus(String text) {
        statusLabel.setText(text);
    }

    public void onGenerate(Runnable action) {
        generateButton.addActionListener(e -> action.run());
    }

    @Override
    public void onStatusChanged(String newStatus) {
        statusLabel.setText(newStatus);
    }

}
