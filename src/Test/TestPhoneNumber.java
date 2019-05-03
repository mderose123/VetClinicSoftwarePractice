package Test;

import model.client.PhoneNumber;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestPhoneNumber {

    private PhoneNumber pn;

    @Before
    public void runBefore() {
        pn = new PhoneNumber(604, 353, 7014);
    }

    @Test
    public void testConstructor() {
        assertEquals(pn.getCountryCode(), 1);
        assertEquals(pn.getAreaCode(), 604);
        assertEquals(pn.getFirstNum(), 353);
        assertEquals(pn.getLastNum(), 7014);
    }

    @Test
    public void changeCountryCode() {
        assertEquals(pn.getCountryCode(), 1);
        pn.setCountryCode(62);
        assertEquals(pn.getCountryCode(), 62);
    }

    @Test
    public void changeAreaCode() {
        assertEquals(pn.getAreaCode(), 604);
        pn.setAreaCode(403);
        assertEquals(pn.getAreaCode(), 403);
    }

    @Test
    public void changeFirstNum() {
        assertEquals(pn.getFirstNum(), 353);
        pn.setFirstNum(416);
        assertEquals(pn.getFirstNum(), 416);
    }

    @Test
    public void changeLastNum() {
        assertEquals(pn.getLastNum(), 7014);
        pn.setLastNum(8876);
        assertEquals(pn.getLastNum(), 8876);
    }
}
