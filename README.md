# Final Project Report – AI Writing Assistant
Reginald Meeks  
CS 3560  
Cal Poly Pomona

## Overview
The AI Writing Assistant is a Java Swing desktop application built using the MVC architecture and multiple object-oriented design patterns. The program provides Creative, Professional, and Academic writing modes and integrates directly with the OpenAI API for text transformation. All network calls run asynchronously using SwingWorker to keep the UI responsive. The application also supports file persistence, allowing users to save and reload writing sessions.

This final version expands the original Assignment 3 system into a more robust, pattern-driven, testable, and production-style application.


## Features

### Writing Modes (Strategy Pattern)
Writing behavior is implemented using the Strategy Pattern.  
Each writing mode encapsulates its own transformation logic:

- `CreativeStrategy`
- `ProfessionalStrategy`
- `AcademicStrategy`

The controller selects the strategy dynamically at runtime.


### OpenAI API Integration
- Uses the official **openai-java SDK v4.9.0**
- Communicates with the GPT-4.1-Mini model
- Requests are constructed through a **Factory Pattern** (`RequestFactory`)
- API calls are executed asynchronously to avoid UI blocking
- Error handling covers:
    - Invalid API keys
    - Network failures
    - Rate limits (HTTP 429)
    - Unexpected exceptions


### Swing User Interface
The UI includes:

- Input text area
- Mode selection dropdown
- Output display panel
- Status label (updates during API calls)
- Generate button

The layout is organized for readability and horizontal text flow to mimic real writing.


### File Persistence
Although optional in the final build, the project supports:

- Saving input + selected mode to a text file
- Reloading saved content back into the UI

This demonstrates basic serialization and a clear separation of I/O responsibilities.


### MVC Architecture
The project is divided into three main components:

- `model/`  
  Writing strategies, domain objects, and enums.

- `view/`  
  Swing UI (MainFrame) with no business logic.

- `controller/`  
  Manages events, strategy selection, async API calls, and view updates.

MVC ensures modularity, testability, and maintainability.


## Design Patterns Used

### 1. Strategy Pattern
Implements runtime-switchable writing behavior.

### 2. Factory Pattern
`RequestFactory` creates OpenAI request objects in a centralized, reusable way.

### 3. Singleton (API Client Behavior)
OpenAI client is created once and reused across the system, avoiding unnecessary network overhead.

### 4. Observer Pattern
Swing’s event listeners act as observers:
- The controller reacts to button clicks
- The dropdown notifies the controller of mode changes
- `SwingWorker` notifies the UI when tasks complete

### 5. MVC (Architectural Pattern)
Ensures separation of concerns and clearer organization.


## Requirements
- Java 17+
- Maven
- OpenAI API Key
- Reliable network connection


## Setup Instructions

### 1. Verify Project Structure
Assignment3/  
│ pom.xml  
│ config.properties (not committed)  
└── src/  
  └── main/java/...


### 2. Add Your API Key
Create `config.properties` OR set an environment variable:

OPENAI_API_KEY=your_actual_api_key_here



Add `config.properties` to `.gitignore` to protect your credentials.


### 3. Build the Project
mvn clean install


### 4. Run the Application
In IntelliJ:
Right-click Main.java → Run 'Main'

arduino
Copy code

Or via command line:
mvn exec:java -Dexec.mainClass="Main"



## How to Use

Enter text in the input field.

Select a writing mode:

Creative

Professional

Academic

Click "Generate".

The rewritten AI output appears in the output panel.

Can also Save or load previous writing sessions.


## Example Prompts

**Creative:**  
A traveler discovers a floating island drifting above the ocean.

**Professional:**  
Explain the importance of user experience design in enterprise software products.

**Academic:**  
Discuss the influence of machine learning techniques on modern data analytics frameworks.


## Common Errors and Solutions

### OpenAI Error 429 — Quota Exceeded
You exceeded your current quota.

This is not a program bug. The API key has insufficient credits.


### config.properties Not Found
Ensure IntelliJ’s working directory is set to:
$PROJECT_DIR$


### Missing API Key
If no key is found, the output panel displays:
Error: Missing or invalid API key.


## Lessons Learned
- Navigating rapid SDK changes required refactoring and deeper study of generated classes.
- Separating UI, controller logic, and model behavior greatly simplified debugging.
- Asynchronous APIs are essential for GUI applications.
- Strategy and Factory patterns make the system more modular and extensible.
- External services (OpenAI) require careful error handling and secure credential management.


## Time Spent
Approx. **30–35 hours**, which included:
- Rebuilding after OneDrive issues
- Debugging SDK version mismatches
- Implementing design patterns
- Designing and refining the UI
- Writing strategies and tests
- Documentation and polishing  

## Link
