package util.command;

import org.jmock.Expectations;
import org.junit.Assert;
import org.junit.Test;

public class UpdateContentOperationTest extends BaseOperationTest {
    @Test
    public void testUpdateContentOperation() {
        final Operation updateContentOperation =
                new UpdateContentOperation("boo", "bar");
        updateContentOperation.setDao(this.mockDao);
        this.context.checking(new Expectations() {{
            oneOf(mockDao).updateContent("boo", "bar");
        }});
        updateContentOperation.run();
    }

    @Test
    public void testEqualsAndHashCode() {
        final Operation op1 = new UpdateContentOperation("boo", "bar");
        final Operation op2 = new UpdateContentOperation("boo", "bar");
        Assert.assertEquals(op1, op2);
        Assert.assertEquals((long) op1.hashCode(), (long) op2.hashCode());
        Assert.assertEquals(op1.toString(), op2.toString());
    }
}
