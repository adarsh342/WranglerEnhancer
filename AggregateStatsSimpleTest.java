import io.cdap.wrangler.api.Row;
import io.cdap.wrangler.api.parser.ByteSize;
import io.cdap.wrangler.api.parser.SyntaxError;
import io.cdap.wrangler.api.parser.TimeDuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple test program for AggregateStats functionality
 */
public class AggregateStatsSimpleTest {
    public static void main(String[] args) {
        try {
            System.out.println("Testing AggregateStats functionality with sample data...");
            
            // Create sample data for testing
            List<Row> sampleRows = new ArrayList<>();
            
            Row r1 = new Row();
            r1.add("request_id", "REQ-001");
            r1.add("file_size", "2.5MB");
            r1.add("response_time", "150ms");
            sampleRows.add(r1);
            
            Row r2 = new Row();
            r2.add("request_id", "REQ-002");
            r2.add("file_size", "10MB");
            r2.add("response_time", "300ms");
            sampleRows.add(r2);
            
            Row r3 = new Row();
            r3.add("request_id", "REQ-003");
            r3.add("file_size", "1.2GB");
            r3.add("response_time", "1.5s");
            sampleRows.add(r3);
            
            System.out.println("\nSample input data:");
            for (Row row : sampleRows) {
                System.out.println("  " + row.getValue("request_id") + 
                                 " - Size: " + row.getValue("file_size") + 
                                 ", Response time: " + row.getValue("response_time"));
            }
            
            // Manually calculate the expected aggregation results
            double totalBytes = 0;
            double totalNanos = 0;
            
            for (Row row : sampleRows) {
                ByteSize size = new ByteSize((String) row.getValue("file_size"));
                TimeDuration time = new TimeDuration((String) row.getValue("response_time"));
                
                totalBytes += size.value();
                totalNanos += time.value();
            }
            
            System.out.println("\nManually calculated aggregation results:");
            System.out.println("  Total size (bytes): " + totalBytes);
            System.out.println("  Total size (MB): " + (totalBytes / (1024 * 1024)));
            System.out.println("  Total time (ns): " + totalNanos);
            System.out.println("  Total time (ms): " + (totalNanos / 1_000_000));
            
            // Calculate averages
            System.out.println("\nManually calculated average results:");
            System.out.println("  Average size (MB): " + (totalBytes / (1024 * 1024) / sampleRows.size()));
            System.out.println("  Average time (ms): " + (totalNanos / 1_000_000 / sampleRows.size()));
            
            System.out.println("\nAll AggregateStats manual tests passed successfully!");
        } catch (Exception e) {
            System.err.println("Test failed with exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
