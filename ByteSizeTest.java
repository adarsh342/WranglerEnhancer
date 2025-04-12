import io.cdap.wrangler.api.parser.ByteSize;
import io.cdap.wrangler.api.parser.SyntaxError;
import io.cdap.wrangler.api.parser.TokenType;

/**
 * Simple test program for ByteSize
 */
public class ByteSizeTest {
    public static void main(String[] args) {
        try {
            System.out.println("Testing ByteSize class...");
            
            // Test basic units
            ByteSize bytes = new ByteSize("1024B");
            System.out.println("1024B in bytes: " + bytes.value() + " bytes");
            System.out.println("Token type: " + bytes.type());
            System.out.println("Unit: " + bytes.getUnit());
            
            ByteSize kilobytes = new ByteSize("1KB");
            System.out.println("1KB in bytes: " + kilobytes.value() + " bytes");
            
            ByteSize megabytes = new ByteSize("1MB");
            System.out.println("1MB in bytes: " + megabytes.value() + " bytes");
            
            ByteSize gigabytes = new ByteSize("1GB");
            System.out.println("1GB in bytes: " + gigabytes.value() + " bytes");
            
            // Test fractional values
            ByteSize halfMegabyte = new ByteSize("0.5MB");
            System.out.println("0.5MB in bytes: " + halfMegabyte.value() + " bytes");
            
            // Test unit conversion
            ByteSize twoMegabytes = new ByteSize("2MB");
            System.out.println("2MB in KB: " + twoMegabytes.convertTo("KB") + " KB");
            System.out.println("2MB in MB: " + twoMegabytes.convertTo("MB") + " MB");
            System.out.println("2MB in GB: " + twoMegabytes.convertTo("GB") + " GB");
            
            // Test case insensitivity
            ByteSize upperCase = new ByteSize("5MB");
            ByteSize lowerCase = new ByteSize("5mb");
            System.out.println("5MB equals 5mb: " + (upperCase.value() == lowerCase.value()));
            
            // Test whitespace handling
            ByteSize noSpace = new ByteSize("10MB");
            ByteSize withSpace = new ByteSize("10 MB");
            System.out.println("10MB equals 10 MB: " + (noSpace.value() == withSpace.value()));
            
            // Test error cases
            try {
                new ByteSize("MB10");
                System.out.println("Failed: Should have rejected MB10");
            } catch (SyntaxError e) {
                System.out.println("Correctly rejected MB10");
            }
            
            try {
                new ByteSize("10ZB");
                System.out.println("Failed: Should have rejected 10ZB");
            } catch (SyntaxError e) {
                System.out.println("Correctly rejected 10ZB");
            }
            
            try {
                new ByteSize("1024");
                System.out.println("Failed: Should have rejected 1024");
            } catch (SyntaxError e) {
                System.out.println("Correctly rejected 1024");
            }
            
            System.out.println("All ByteSize tests passed successfully!");
        } catch (Exception e) {
            System.err.println("Test failed with exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
