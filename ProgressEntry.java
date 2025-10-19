import java.time.LocalDate;

public final class ProgressEntry {
    private final LocalDate date;
    private final int score;
    private final String journalEntry;
    
    public ProgressEntry(LocalDate date, int score, String journalEntry) {
        this.date = date;
        this.score = score;
        this.journalEntry = journalEntry;
    }
    
    public LocalDate getDate() {
        return date;
    }
    
    public int getScore() {
        return score;
    }
    
    public String getJournalEntry() {
        return journalEntry;
    }
}