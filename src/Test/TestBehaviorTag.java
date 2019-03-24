package Test;

import model.client.BehaviorTag;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestBehaviorTag {
    private BehaviorTag behaviorTag;

    @Before
    public void runBefore() {
        behaviorTag = new BehaviorTag("testName");
    }

    @Test
    public void testConstructor() {
        assertEquals("testName", behaviorTag.getBehaviorName());
    }

}

