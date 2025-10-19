import java.util.Scanner;

public class AssessmentModule {
    private static final String[] QUESTIONS = {
        "1. How would you rate your overall mood today?",
        "2. How well did you sleep last night?",
        "3. How anxious or worried have you felt?",
        "4. How much energy have you had?",
        "5. How satisfied are you with your social interactions?",
        "6. How well have you been able to concentrate?",
        "7. How physically active have you been?",
        "8. How satisfied are you with your self-care routines?",
        "9. How overwhelming have your responsibilities felt?",
        "10. How hopeful do you feel about the future?"
    };
    
    public static int conductAssessment(Scanner scanner) {
        System.out.println("\n=== Daily Wellness Assessment ===");
        System.out.println("Please rate each question from 1 (best) to 5 (worst)\n");
        
        int totalScore = 0;
        
        for (String question : QUESTIONS) {
            System.out.println(question);
            int score = getValidScore(scanner);
            totalScore += score;
        }
        
        return totalScore;
    }
    
    private static int getValidScore(Scanner scanner) {
        while (true) {
            System.out.print("Your answer (1-5): ");
            if (scanner.hasNextInt()) {
                int score = scanner.nextInt();
                scanner.nextLine(); // Consume the newline after the number
                if (score >= 1 && score <= 5) {
                    return score;
                }
            } else {
                scanner.nextLine(); // Changed from next() to nextLine()
            }
            System.out.println("Invalid input. Please enter a number between 1 and 5.");
        }
    }
}