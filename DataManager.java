// DataManager.java
import java.io.*;
import java.time.LocalDate;
import java.util.LinkedList;

public class DataManager {
    private static final String DATA_FILE = "user_profile.txt";
    
    public static UserProfile loadProfile() {
        File file = new File(DATA_FILE);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String name = reader.readLine();
                if (name == null) return null;
                
                int dailyStreak = Integer.parseInt(reader.readLine());
                String lastCheckInStr = reader.readLine();
                LocalDate lastCheckIn = lastCheckInStr.equals("null") ? null : LocalDate.parse(lastCheckInStr);
                
                // Read achievements
                int achievementCount = Integer.parseInt(reader.readLine());
                LinkedList<String> achievements = new LinkedList<>();
                for (int i = 0; i < achievementCount; i++) {
                    achievements.add(reader.readLine());
                }
                
                // Read progress history
                int historyCount = Integer.parseInt(reader.readLine());
                LinkedList<ProgressEntry> progressHistory = new LinkedList<>();
                for (int i = 0; i < historyCount; i++) {
                    LocalDate date = LocalDate.parse(reader.readLine());
                    int score = Integer.parseInt(reader.readLine());
                    String journalEntry = reader.readLine();
                    if (journalEntry.equals("###EMPTY###")) {
                        journalEntry = "";
                    }
                    progressHistory.add(new ProgressEntry(date, score, journalEntry));
                }
                
                // Create and populate UserProfile
                UserProfile profile = new UserProfile(name);
                profile.setDailyStreak(dailyStreak);
                profile.setLastCheckIn(lastCheckIn);
                profile.setAchievements(achievements);
                profile.setProgressHistory(progressHistory);
                
                return profile;
            } catch (Exception e) {
                System.out.println("Error loading profile. Starting fresh.");
            }
        }
        return null;
    }
    
    public static void saveProfile(UserProfile profile) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(DATA_FILE))) {
            writer.println(profile.getName());
            writer.println(profile.getDailyStreak());
            writer.println(profile.getLastCheckIn() == null ? "null" : profile.getLastCheckIn().toString());
            
            // Write achievements
            writer.println(profile.getAchievements().size());
            for (String achievement : profile.getAchievements()) {
                writer.println(achievement);
            }
            
            // Write progress history
            writer.println(profile.getProgressHistory().size());
            for (ProgressEntry entry : profile.getProgressHistory()) {
                writer.println(entry.getDate());
                writer.println(entry.getScore());
                writer.println(entry.getJournalEntry().isEmpty() ? "###EMPTY###" : entry.getJournalEntry());
            }
        } catch (IOException e) {
            System.out.println("Error saving profile: " + e.getMessage());
        }
    }
    
    public static void exportToCSV(UserProfile profile) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("progress_export.csv"))) {
            writer.println("Date,Score,JournalEntry");
            
            for (ProgressEntry entry : profile.getProgressHistory()) {
                String journal = entry.getJournalEntry();
                if (journal.contains(",") || journal.contains("\"") || journal.contains("\n")) {
                    journal = "\"" + journal.replace("\"", "\"\"") + "\"";
                }
                writer.println(entry.getDate() + "," + entry.getScore() + "," + journal);
            }
            
            System.out.println("\n✓ Progress exported to progress_export.csv");
        } catch (IOException e) {
            System.out.println("Error exporting data: " + e.getMessage());
        }
    }
}