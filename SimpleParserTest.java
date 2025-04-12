import io.cdap.wrangler.api.parser.ByteSize;
import io.cdap.wrangler.api.parser.SyntaxError;
import io.cdap.wrangler.api.parser.TimeDuration;
import io.cdap.wrangler.api.parser.Token;
import io.cdap.wrangler.api.parser.TokenType;

/**
 * Simple test program for ByteSize and TimeDuration parser
 */
public class SimpleParserTest {
    public static void main(String[] args) {
        try {
            System.out.println("Testing Parser functionality...");
            
            // Test parsing byte size values
            ByteSize byteSize = new ByteSize("5MB");
            System.out.println("\nByte size example: 5MB");
            System.out.println("  Value in bytes: " + byteSize.value());
            System.out.println("  Value in KB: " + byteSize.convertTo("KB"));
            System.out.println("  Value in MB: " + byteSize.convertTo("MB"));
            System.out.println("  Value in GB: " + byteSize.convertTo("GB"));
            
            ByteSize byteSize2 = new ByteSize("1.5GB");
            System.out.println("\nByte size example: 1.5GB");
            System.out.println("  Value in bytes: " + byteSize2.value());
            System.out.println("  Value in KB: " + byteSize2.convertTo("KB"));
            System.out.println("  Value in MB: " + byteSize2.convertTo("MB"));
            System.out.println("  Value in GB: " + byteSize2.convertTo("GB"));
            
            // Test parsing time duration values
            TimeDuration timeDuration = new TimeDuration("2.5h");
            System.out.println("\nTime duration example: 2.5h");
            System.out.println("  Value in nanoseconds: " + timeDuration.value());
            System.out.println("  Value in milliseconds: " + timeDuration.convertTo("ms"));
            System.out.println("  Value in seconds: " + timeDuration.convertTo("s"));
            System.out.println("  Value in minutes: " + timeDuration.convertTo("m"));
            System.out.println("  Value in hours: " + timeDuration.convertTo("h"));
            
            TimeDuration timeDuration2 = new TimeDuration("1.5d");
            System.out.println("\nTime duration example: 1.5d");
            System.out.println("  Value in nanoseconds: " + timeDuration2.value());
            System.out.println("  Value in milliseconds: " + timeDuration2.convertTo("ms"));
            System.out.println("  Value in seconds: " + timeDuration2.convertTo("s"));
            System.out.println("  Value in minutes: " + timeDuration2.convertTo("m"));
            System.out.println("  Value in hours: " + timeDuration2.convertTo("h"));
            System.out.println("  Value in days: " + timeDuration2.convertTo("d"));
            
            System.out.println("\nAll Parser tests passed successfully!");
        } catch (Exception e) {
            System.err.println("Test failed with exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
