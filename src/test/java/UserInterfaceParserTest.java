import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserInterfaceParserTest {

    @Test
    void nullArgs_treatedAsNoArgs_listMode() {
        DetermineUsage req = UserInterfaceParser.parse(null);
        assertEquals(DetermineUsage.Mode.LIST, req.getMode());
        assertNull(req.getFileNumber());
        assertNull(req.getKeyOverride());
    }

    @Test
    void noArgs_listMode() {
        DetermineUsage req = UserInterfaceParser.parse(new String[]{});
        assertEquals(DetermineUsage.Mode.LIST, req.getMode());
    }

    @Test
    void oneArg_twoDigits_showMode_defaultKey() {
        DetermineUsage req = UserInterfaceParser.parse(new String[]{"01"});
        assertEquals(DetermineUsage.Mode.SHOW, req.getMode());
        assertEquals("01", req.getFileNumber());
        assertNull(req.getKeyOverride());
    }

    @Test
    void twoArgs_showMode_withKey() {
        DetermineUsage req = UserInterfaceParser.parse(new String[]{"01", "ciphers/key.txt"});
        assertEquals(DetermineUsage.Mode.SHOW, req.getMode());
        assertEquals("01", req.getFileNumber());
        assertEquals("ciphers/key.txt", req.getKeyOverride());
    }

    @Test
    void invalidFileNumber_throws() {
        assertThrows(IllegalArgumentException.class, () -> UserInterfaceParser.parse(new String[]{"1"}));
        assertThrows(IllegalArgumentException.class, () -> UserInterfaceParser.parse(new String[]{"abc"}));
        assertThrows(IllegalArgumentException.class, () -> UserInterfaceParser.parse(new String[]{"0a"}));
    }

    @Test
    void blankKey_throws() {
        assertThrows(IllegalArgumentException.class, () -> UserInterfaceParser.parse(new String[]{"01", ""}));
        assertThrows(IllegalArgumentException.class, () -> UserInterfaceParser.parse(new String[]{"01", "   "}));
    }

    @Test
    void tooManyArgs_throws() {
        assertThrows(IllegalArgumentException.class, () -> UserInterfaceParser.parse(new String[]{"01", "k", "x"}));
    }
}

