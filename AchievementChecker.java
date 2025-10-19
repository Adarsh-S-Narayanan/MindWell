import java.util.List;

public class AchievementChecker {
    
    public static void checkAndUnlockAchievements(UserProfile profile) {
        List<ProgressEntry> history = profile.getProgressHistory();
        int historySize = history.size();
        
        // First Step
        if (historySize == 1 && !profile.getAchievements().contains("First Step")) {
            profile.addAchievement("First Step");
            System.out.println("\n🏆 Achievement Unlocked: First Step!");
        }
        
        // 10 Assessments Completed
        if (historySize == 10 && !profile.getAchievements().contains("10 Assessments Completed")) {
            profile.addAchievement("10 Assessments Completed");
            System.out.println("\n🏆 Achievement Unlocked: 10 Assessments Completed!");
        }
        
        // 7-Day Streak
        if (profile.getDailyStreak() == 7 && !profile.getAchievements().contains("7-Day Streak")) {
            profile.addAchievement("7-Day Streak");
            System.out.println("\n🏆 Achievement Unlocked: 7-Day Streak!");
        }
        
        // Mindful Month
        if (profile.getDailyStreak() == 30 && !profile.getAchievements().contains("Mindful Month")) {
            profile.addAchievement("Mindful Month");
            System.out.println("\n🏆 Achievement Unlocked: Mindful Month!");
        }
        
        // Positive Trend
        if (historySize >= 6 && !profile.getAchievements().contains("Positive Trend")) {
            double recentAvg = (history.get(historySize - 1).getScore() +
                              history.get(historySize - 2).getScore() +
                              history.get(historySize - 3).getScore()) / 3.0;
            
            double previousAvg = (history.get(historySize - 4).getScore() +
                                history.get(historySize - 5).getScore() +
                                history.get(historySize - 6).getScore()) / 3.0;
            
            if (recentAvg < previousAvg) {
                profile.addAchievement("Positive Trend");
                System.out.println("\n🏆 Achievement Unlocked: Positive Trend!");
            }
        }
    }
}