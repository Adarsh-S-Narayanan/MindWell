import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    public static void logActivity(String message) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("log.txt", true))) {
            String timestamp = LocalDateTime.now().format(formatter);
            writer.println("[" + timestamp + "] " + message);
        } catch (IOException e) {
            System.err.println("Error writing to log: " + e.getMessage());
        }
    }
    
    public static void logPerformance(long nanoseconds) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("cpu_time.txt", true))) {
            writer.println(nanoseconds);
        } catch (IOException e) {
            System.err.println("Error writing to performance log: " + e.getMessage());
        }
    }
    
    public static void logAnalytics(int totalAssessments, int newAchievements) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("analytics.txt"))) {
            writer.println("=== Session Analytics Report ===");
            writer.println("Generated: " + LocalDateTime.now().format(formatter));
            writer.println("Total Assessments This Session: " + totalAssessments);
            writer.println("New Achievements Unlocked: " + newAchievements);
        } catch (IOException e) {
            System.err.println("Error writing analytics: " + e.getMessage());
        }
    }
}

