import java.time.LocalDate;
import java.util.Random;
import java.util.Scanner;

public class MindWell {
    private static final String[] QUOTES = {
        "\"The only way out is through.\" - Robert Frost",
        "\"You are not your thoughts; you are the observer of your thoughts.\"",
        "\"Progress, not perfection.\"",
        "\"Every day may not be good, but there's something good in every day.\"",
        "\"Mental health is not a destination, but a process.\"",
        "\"Your mental health is a priority, not a luxury.\"",
        "\"It's okay to not be okay, as long as you are not giving up.\"",
        "\"Healing takes time, and asking for help is a courageous step.\"",
        "\"Self-care is how you take your power back.\"",
        "\"You don't have to be positive all the time. It's perfectly okay to feel sad, angry, annoyed, frustrated, scared, or anxious.\""
    };
    
    private UserProfile profile;
    private Scanner scanner;
    private int sessionAssessments = 0;
    private int sessionAchievements = 0;
    
    public static void main(String[] args) {
        new MindWell().run();
    }
    
    public void run() {
        scanner = new Scanner(System.in);
        displayRandomQuote();
        Logger.logActivity("Application started");
        
        // Load or create profile
        profile = DataManager.loadProfile();
        if (profile == null) {
            System.out.print("\nWelcome to MindWell! Please enter your name: ");
            String name = scanner.nextLine();
            profile = new UserProfile(name);
            Logger.logActivity("New user profile created: " + name);
        } else {
            System.out.println("\nWelcome back, " + profile.getName() + "!");
            Logger.logActivity("User profile loaded: " + profile.getName());
        }
        
        mainMenu();
    }
    
    private void displayRandomQuote() {
        Random random = new Random();
        System.out.println("\n" + QUOTES[random.nextInt(QUOTES.length)]);
    }
    
    private void mainMenu() {
        boolean running = true;
        
        while (running) {
            long startTime = System.nanoTime();
            
            System.out.println("\n=== MindWell Main Menu ===");
            System.out.println("1. Take Daily Assessment");
            System.out.println("2. View Profile & Progress");
            System.out.println("3. Export Progress to CSV");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            
            int choice = getValidChoice();
            Logger.logActivity("Menu option selected: " + choice);
            
            switch (choice) {
                case 1:
                    takeDailyAssessment();
                    break;
                case 2:
                    viewProfileAndProgress();
                    break;
                case 3:
                    DataManager.exportToCSV(profile);
                    Logger.logActivity("Progress exported to CSV");
                    break;
                case 4:
                    running = false;
                    break;
            }
            
            long endTime = System.nanoTime();
            long duration = endTime - startTime;
            Logger.logPerformance(duration);
        }
        
        exitApplication();
    }
    
    private int getValidChoice() {
        while (true) {
            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                if (choice >= 1 && choice <= 4) {
                    return choice;
                }
            } else {
                scanner.nextLine();
            }
            System.out.print("Invalid choice. Please enter 1-4: ");
        }
    }
    
    private void takeDailyAssessment() {
        LocalDate today = LocalDate.now();
        
        // Check if already completed today
        if (profile.getLastCheckIn() != null && profile.getLastCheckIn().equals(today)) {
            System.out.println("\nYou've already completed your assessment today!");
            System.out.print("Would you like to do another assessment anyway? (y/n): ");
            String response = scanner.nextLine().trim().toLowerCase();
            if (!response.equals("y")) {
                return;
            }
        }
        
        int initialAchievements = profile.getAchievements().size();
        
        int score = AssessmentModule.conductAssessment(scanner);
        FeedbackLevel level = FeedbackLevel.fromScore(score);
        
        System.out.println("\n=== Your Results ===");
        System.out.println("Total Score: " + score);
        System.out.println("Feedback Level: " + level.name());
        System.out.println("\n" + level.getMessage());
        
        Logger.logActivity("Assessment completed - Score: " + score + ", Level: " + level.name());
        
        // Journal entry
        System.out.print("\nWould you like to add a journal entry? (y/n): ");
        String response = scanner.nextLine().trim().toLowerCase();
        String journalEntry = "";
        
        if (response.equals("y")) {
            System.out.println("Enter your journal entry (press Enter when done):");
            journalEntry = scanner.nextLine();
            Logger.logActivity("Journal entry added");
        }
        
        ProgressEntry entry = new ProgressEntry(today, score, journalEntry);
        profile.addProgressEntry(entry);
        
        AchievementChecker.checkAndUnlockAchievements(profile);
        
        int newAchievements = profile.getAchievements().size() - initialAchievements;
        sessionAssessments++;
        sessionAchievements += newAchievements;
        
        DataManager.saveProfile(profile);
    }
    
    private void viewProfileAndProgress() {
        System.out.println("\n=== User Profile ===");
        System.out.println("Name: " + profile.getName());
        System.out.println("Current Streak: " + profile.getDailyStreak() + " days");
        
        System.out.println("\n=== Achievements ===");
        if (profile.getAchievements().isEmpty()) {
            System.out.println("No achievements unlocked yet. Keep going!");
        } else {
            for (String achievement : profile.getAchievements()) {
                System.out.println("🏆 " + achievement);
            }
        }
        
        System.out.println("\n=== Score History ===");
        if (profile.getProgressHistory().isEmpty()) {
            System.out.println("No assessments recorded yet.");
        } else {
            for (ProgressEntry entry : profile.getProgressHistory()) {
                int barLength = entry.getScore();
                System.out.print(entry.getDate() + " [" + entry.getScore() + "] ");
                for (int i = 0; i < barLength; i++) {
                    System.out.print("█");
                }
                System.out.println();
            }
        }
        
        System.out.println("\n=== Progress Entries (Most Recent First) ===");
        if (profile.getProgressHistory().isEmpty()) {
            System.out.println("No entries yet.");
        } else {
            for (int i = profile.getProgressHistory().size() - 1; i >= 0; i--) {
                ProgressEntry entry = profile.getProgressHistory().get(i);
                System.out.println("\n--- " + entry.getDate() + " ---");
                System.out.println("Score: " + entry.getScore());
                System.out.println("Journal: " + (entry.getJournalEntry().isEmpty() ? "(No entry)" : entry.getJournalEntry()));
            }
        }
        
        Logger.logActivity("Profile and progress viewed");
    }
    
    private void exitApplication() {
        System.out.println("\nGenerating session analytics...");
        Logger.logAnalytics(sessionAssessments, sessionAchievements);
        Logger.logActivity("Application closed");
        
        DataManager.saveProfile(profile);
        PerformanceAnalyzer.displayStatistics();
        
        System.out.println("\nThank you for using MindWell. Take care!");
        scanner.close();
    }
}