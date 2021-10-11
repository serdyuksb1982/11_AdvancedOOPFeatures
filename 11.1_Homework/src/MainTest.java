import junit.framework.TestCase;
import org.junit.Test;

import java.text.ParseException;

public class MainTest extends TestCase {
    public MainTest() {
        super( String.valueOf ( Main.class ) );
    }
    @Test
    public void testStart() throws ParseException {
        String exp = "Дмитрий Кочергин - 140000 - 31.01.2017";
        String result = Main.start ();
        assertEquals ( exp,result );
    }
}