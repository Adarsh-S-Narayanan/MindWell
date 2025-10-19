import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class UserProfile {
    private String name;
    private int dailyStreak;
    private LocalDate lastCheckIn;
    private LinkedList<String> achievements;
    private LinkedList<ProgressEntry> progressHistory;
    
    public UserProfile(String name) {
        this.name = name;
        this.dailyStreak = 0;
        this.lastCheckIn = null;
        this.achievements = new LinkedList<>();
        this.progressHistory = new LinkedList<>();
    }
    
    public String getName() {
        return name;
    }
    
    public int getDailyStreak() {
        return dailyStreak;
    }
    
    public LocalDate getLastCheckIn() {
        return lastCheckIn;
    }
    
    public LinkedList<String> getAchievements() {
        return achievements;
    }
    
    public LinkedList<ProgressEntry> getProgressHistory() {
        return progressHistory;
    }
    
    public void setDailyStreak(int streak) {
        this.dailyStreak = streak;
    }
    
    public void setLastCheckIn(LocalDate date) {
        this.lastCheckIn = date;
    }
    
    public void setAchievements(LinkedList<String> achievements) {
        this.achievements = achievements;
    }
    
    public void setProgressHistory(LinkedList<ProgressEntry> history) {
        this.progressHistory = history;
    }
    
    public void addProgressEntry(ProgressEntry entry) {
        progressHistory.add(entry);
        updateStreak(entry.getDate());
    }
    
    public void addAchievement(String achievement) {
        if (!achievements.contains(achievement)) {
            achievements.add(achievement);
        }
    }
    
    private void updateStreak(LocalDate currentDate) {
        if (lastCheckIn == null) {
            dailyStreak = 1;
        } else if (lastCheckIn.plusDays(1).equals(currentDate)) {
            dailyStreak++;
        } else if (!lastCheckIn.equals(currentDate)) {
            dailyStreak = 1;
        }
        lastCheckIn = currentDate;
    }
}