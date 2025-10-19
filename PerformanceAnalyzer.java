import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PerformanceAnalyzer {
    
    public static void displayStatistics() {
        List<Long> times = loadPerformanceTimes();
        
        if (times.isEmpty()) {
            System.out.println("\nNo performance data to analyze.");
            return;
        }
        
        double mean = calculateMean(times);
        double median = calculateMedian(times);
        double variance = calculateVariance(times, mean);
        double stdDev = Math.sqrt(variance);
        
        System.out.println("\n=== Performance Statistics ===");
        System.out.printf("Mean: %.2f ns\n", mean);
        System.out.printf("Median: %.2f ns\n", median);
        System.out.printf("Variance: %.2f\n", variance);
        System.out.printf("Standard Deviation: %.2f ns\n", stdDev);
    }
    
    private static List<Long> loadPerformanceTimes() {
        List<Long> times = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("cpu_time.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    times.add(Long.parseLong(line.trim()));
                } catch (NumberFormatException e) {
                    // Skip invalid lines
                }
            }
        } catch (IOException e) {
            // File doesn't exist or can't be read
        }
        return times;
    }
    
    private static double calculateMean(List<Long> values) {
        long sum = 0;
        for (long val : values) {
            sum += val;
        }
        return (double) sum / values.size();
    }
    
    private static double calculateMedian(List<Long> values) {
        List<Long> sorted = new ArrayList<>(values);
        Collections.sort(sorted);
        int size = sorted.size();
        
        if (size % 2 == 0) {
            return (sorted.get(size / 2 - 1) + sorted.get(size / 2)) / 2.0;
        } else {
            return sorted.get(size / 2);
        }
    }
    
    private static double calculateVariance(List<Long> values, double mean) {
        double sumSquaredDiff = 0;
        for (long val : values) {
            double diff = val - mean;
            sumSquaredDiff += diff * diff;
        }
        return sumSquaredDiff / values.size();
    }
}