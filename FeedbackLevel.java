public enum FeedbackLevel {
    MINIMAL(10, 14, "Your results suggest minimal signs of distress. Keep up your healthy habits!"),
    MILD(15, 19, "You may be experiencing mild distress. Consider activities like mindfulness, exercise, or talking to a friend."),
    MODERATE(20, 29, "Your score indicates moderate distress. It's a good time to focus on self-care and stress-management techniques."),
    MODERATELY_SEVERE(30, 39, "You seem to be facing significant challenges. It is highly recommended you speak with a trusted friend, family member, or mental health professional."),
    SEVERE(40, 50, "Your results indicate severe distress. Please prioritize your mental health and seek support from a professional immediately.");
    
    private final int minScore;
    private final int maxScore;
    private final String message;
    
    FeedbackLevel(int minScore, int maxScore, String message) {
        this.minScore = minScore;
        this.maxScore = maxScore;
        this.message = message;
    }
    
    public String getMessage() {
        return message;
    }
    
    public static FeedbackLevel fromScore(int score) {
        for (FeedbackLevel level : values()) {
            if (score >= level.minScore && score <= level.maxScore) {
                return level;
            }
        }
        return SEVERE;
    }
}