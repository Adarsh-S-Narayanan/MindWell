<h1>MindWell: A Command-Line Wellness Tracker</h1>

This project is a console-based Java application designed to help users monitor their mental well-being through daily assessments and journaling. It serves as a comprehensive demonstration of core software engineering principles, including Data Structures, Object-Oriented Programming, and system performance analysis.

<h3>📌 Core Concepts Demonstrated</h3>

Data Structures: LinkedList for efficient, ordered data storage and retrieval.

Object-Oriented Programming: High cohesion and low coupling through Encapsulation, Exception Handling, and the Single Responsibility Principle.

File I/O: Persistent user data storage (user_profile.txt) and CSV export functionality.

Probability & Statistics: Performance analysis of task execution times using descriptive statistics.

<h2>🚀 Features</h2>

Persistent User Profiles: User data is automatically saved and loaded across sessions.

Guided Daily Assessments: A 10-question wellness quiz with dynamic, score-based feedback.

Chronological Progress History: Review past scores and journal entries in a clean, readable format.

Gamification Engine: Daily streaks and unlockable achievements encourage consistent user engagement.

Data Export: Export a complete history of progress to a progress_export.csv file for analysis in other tools.

Performance Monitoring: Automated logging of task completion times for statistical review.

<h3>🛠 Technologies</h3>

Java

<h2>⚙️ How to Run</h2>

Ensure you have a Java Development Kit (JDK) installed.

Clone the repository or place all .java files in the same directory.

Compile the source code:

javac *.java


Run the application:

java MindWell


The application will create user_profile.txt, log.txt, and other data files in the same directory.

<h2>📁 Project Structure</h2>

/mindwell-wellness-tracker
│
├── MindWell.java           # Main application driver class
├── UserProfile.java        # Data model for user state
├── ProgressEntry.java      # Data model for a single daily entry
├── DataManager.java        # Handles all file I/O (save, load, export)
├── AssessmentModule.java   # Manages the assessment logic and questions
├── AchievementChecker.java # Implements gamification logic
├── FeedbackLevel.java      # Enum for score-based feedback levels
├── Logger.java             # Handles activity and performance logging
├── PerformanceAnalyzer.java# Performs statistical calculations on performance data
└── README.md


<h2>✒️ Author</h2>

Adarsh S Narayanan - [https://www.linkedin.com/in/adarsh-s-narayanan-965949294/]
