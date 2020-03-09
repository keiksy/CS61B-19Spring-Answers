import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    @Test
    public void testOffBy1() {
        CharacterComparator offByOne = new OffByN(1);
        assertTrue(offByOne.equalChars('a', 'b'));
        assertFalse(offByOne.equalChars('a', 'a'));
        assertTrue(offByOne.equalChars('b', 'a'));
        assertFalse(offByOne.equalChars('r', 'b'));
    }

    @Test
    public void testOffBy5() {
        CharacterComparator offByFive = new OffByN(5);
        assertTrue(offByFive.equalChars('a', 'f'));
        assertTrue(offByFive.equalChars('f', 'a'));
        assertFalse(offByFive.equalChars('f', 'h'));
    }
}
