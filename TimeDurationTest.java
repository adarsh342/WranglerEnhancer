import io.cdap.wrangler.api.parser.TimeDuration;
import io.cdap.wrangler.api.parser.SyntaxError;
import io.cdap.wrangler.api.parser.TokenType;

/**
 * Simple test program for TimeDuration
 */
public class TimeDurationTest {
    public static void main(String[] args) {
        try {
            System.out.println("Testing TimeDuration class...");
            
            // Test basic units
            TimeDuration nanos = new TimeDuration("1000ns");
            System.out.println("1000ns in nanoseconds: " + nanos.value() + " ns");
            System.out.println("Token type: " + nanos.type());
            System.out.println("Unit: " + nanos.getUnit());
            
            TimeDuration millis = new TimeDuration("1ms");
            System.out.println("1ms in nanoseconds: " + millis.value() + " ns");
            
            TimeDuration seconds = new TimeDuration("1s");
            System.out.println("1s in nanoseconds: " + seconds.value() + " ns");
            
            TimeDuration minutes = new TimeDuration("1m");
            System.out.println("1m in nanoseconds: " + minutes.value() + " ns");
            
            TimeDuration hours = new TimeDuration("1h");
            System.out.println("1h in nanoseconds: " + hours.value() + " ns");
            
            TimeDuration days = new TimeDuration("1d");
            System.out.println("1d in nanoseconds: " + days.value() + " ns");
            
            // Test fractional values
            TimeDuration halfSecond = new TimeDuration("0.5s");
            System.out.println("0.5s in nanoseconds: " + halfSecond.value() + " ns");
            
            // Test unit conversion
            TimeDuration twoSeconds = new TimeDuration("2s");
            System.out.println("2s in ms: " + twoSeconds.convertTo("ms") + " ms");
            System.out.println("2s in s: " + twoSeconds.convertTo("s") + " s");
            System.out.println("2s in m: " + twoSeconds.convertTo("m") + " m");
            
            // Test whitespace handling
            TimeDuration noSpace = new TimeDuration("10ms");
            TimeDuration withSpace = new TimeDuration("10 ms");
            System.out.println("10ms equals 10 ms: " + (noSpace.value() == withSpace.value()));
            
            // Test error cases
            try {
                new TimeDuration("ms10");
                System.out.println("Failed: Should have rejected ms10");
            } catch (SyntaxError e) {
                System.out.println("Correctly rejected ms10");
            }
            
            try {
                new TimeDuration("10ys");
                System.out.println("Failed: Should have rejected 10ys");
            } catch (SyntaxError e) {
                System.out.println("Correctly rejected 10ys");
            }
            
            try {
                new TimeDuration("1000");
                System.out.println("Failed: Should have rejected 1000");
            } catch (SyntaxError e) {
                System.out.println("Correctly rejected 1000");
            }
            
            System.out.println("All TimeDuration tests passed successfully!");
        } catch (Exception e) {
            System.err.println("Test failed with exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
