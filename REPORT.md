## Overview
This project is a full-featured **AI Writing Assistant** built in Java using Swing for the GUI, the OpenAI Java SDK for real AI text generation, and a clean **MVC architecture**. It applies multiple design patterns (Strategy, Factory, Singleton, Observer) and includes asynchronous execution to prevent UI freezing.

Users type text, select a writing mode, and receive rewritten AI-generated output in real time. This final project builds on the earlier assignment but expands the architecture, patterns, testing, documentation, and professional software engineering practices.


## Features
### Core Functionality
- Rewrite text using:
    - **Creative Mode**
    - **Professional Mode**
    - **Academic Mode**
- Responses generated through the **OpenAI GPT-4.1 Mini** model.
- Smooth, responsive GUI using Swing.
- Asynchronous API calls via `SwingWorker`.
- Error handling for:
    - Missing API key
    - Rate limits
    - Network failures
    - Unexpected API errors

### Advanced Engineering Features
- **MVC architecture** across model, view, controller.
- **Strategy Pattern** for switching writing behaviors.
- **Factory Pattern** (`RequestFactory`) to build OpenAI request objects.
- **Singleton-style API client** (OpenAI client created once and reused).
- **Observer Pattern** via event-driven UI components.
- **JUnit tests** for Strategy and Factory behavior.


## Project Structure

src/
├── model/
│ ├── WritingMode.java
│ └── strategy/
│ ├── WritingStrategy.java
│ ├── CreativeStrategy.java
│ ├── ProfessionalStrategy.java
│ └── AcademicStrategy.java
├── service/
│ ├── APIService.java
│ └── RequestFactory.java
├── controller/
│ └── MainController.java
├── view/
│ └── MainFrame.java
└── Main.java

test/
├── model/strategy/StrategyTests.java
└── service/RequestFactoryTest.java

## Installation & Setup
1. Install Java 17+
java -version

2. Install Maven
mvn -version

3. Set OpenAI API Key (required)
   macOS / Linux:
   export OPENAI_API_KEY="your_key_here"
   Windows PowerShell:
   setx OPENAI_API_KEY "your_key_here"

4. Make sure IntelliJ is restarted after setting the environment variable.

## Running the Application
Compile
mvn clean install

Run the program
mvn exec:java -Dexec.mainClass="Main"

Or in IntelliJ:
Right-click Main.java → Run 'Main'

## Running Tests
mvn test

JUnit tests include:

- Strategy behavior
- Polymorphism
- RequestFactory parameter building
- Edge case handling

## Usage Instructions
1. Launch the desktop application.
2. Type text into the Input field.
3. Choose a writing mode (Creative, Professional, Academic).
4. Click "Generate".
5. The AI-rewritten text appears in the Output panel.
6. Status label shows API progress ("Contacting OpenAI…", "Done", etc.)
7. Any API or network errors appear in the Output panel.

## Dependencies
- OpenAI Java SDK 4.9.0
- Java 17+
- Maven 3+
- Swing (Java Standard Library)
- JUnit 5.10