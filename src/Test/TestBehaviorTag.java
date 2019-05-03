package Test;

import model.client.BehaviorTag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TestBehaviorTag {
    private BehaviorTag behaviorTag;

    @BeforeEach
    public void runBefore() {
        behaviorTag = new BehaviorTag("testName");
    }

    @Test
    public void testConstructor() {
        assertEquals("testName", behaviorTag.getBehaviorName());
    }

}

