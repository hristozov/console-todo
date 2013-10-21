package util.command;

import org.jmock.Expectations;
import org.junit.Test;

public class UpdatePriorityOperationTest extends BaseOperationTest {
    @Test
    public void testUpdatePriorityOperation() {
        final Operation updatePriorityOperation = new UpdatePriorityOperation("foo", 1337);
        updatePriorityOperation.setDao(this.mockDao);
        this.context.checking(new Expectations() {{
            oneOf(mockDao).updatePriority("foo", 1337);
        }});
        updatePriorityOperation.run();
    }
}
