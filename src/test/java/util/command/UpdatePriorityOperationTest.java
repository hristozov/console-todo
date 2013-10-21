package util.command;

import org.jmock.Expectations;
import org.junit.Assert;
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

    @Test
    public void testEqualsAndHashCode() {
        final Operation op1 = new UpdatePriorityOperation("boo", 1337);
        final Operation op2 = new UpdatePriorityOperation("boo", 1337);
        Assert.assertEquals(op1, op2);
        Assert.assertEquals(op1.hashCode(), op2.hashCode());
        Assert.assertEquals(op1.toString(), op2.toString());
    }
}
