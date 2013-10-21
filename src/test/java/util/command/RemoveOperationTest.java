package util.command;

import org.jmock.Expectations;
import org.junit.Assert;
import org.junit.Test;

public class RemoveOperationTest extends BaseOperationTest {
    @Test
    public void testRemoveOperation() {
        final Operation removeOperation = new RemoveOperation("boo");
        removeOperation.setDao(this.mockDao);
        this.context.checking(new Expectations() {{
            oneOf(mockDao).remove("boo");
        }});
        removeOperation.run();
    }

    @Test
    public void testEqualsAndHashCode() {
        final Operation op1 = new RemoveOperation("boo");
        final Operation op2 = new RemoveOperation("boo");
        Assert.assertEquals(op1, op2);
        Assert.assertEquals(op1.hashCode(), op2.hashCode());
        Assert.assertEquals(op1.toString(), op2.toString());
    }
}
