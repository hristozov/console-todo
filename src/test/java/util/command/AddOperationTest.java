package util.command;

import org.jmock.Expectations;
import org.junit.Assert;
import org.junit.Test;

public class AddOperationTest extends BaseOperationTest {
    @Test
    public void testAddOperationWithDefaultPriority() {
        final Operation addOperation = new AddOperation("boo");
        addOperation.setDao(this.mockDao);
        this.context.checking(new Expectations() {{
            oneOf(AddOperationTest.this.mockDao).add("boo", 0);
            will(returnValue("id1"));
        }});
        addOperation.run();
    }

    @Test
    public void testAddOperationWithExplicitPriority() {
        final Operation addOperation = new AddOperation("bar", 31337);
        addOperation.setDao(this.mockDao);
        this.context.checking(new Expectations() {{
            oneOf(AddOperationTest.this.mockDao).add("bar", 31337);
            will(returnValue("id2"));
        }});
        addOperation.run();
    }

    @Test
    public void testEqualsAndHashCode() {
        final Operation op1 = new AddOperation("aaa", 1337);
        final Operation op2 = new AddOperation("aaa", 1337);
        Assert.assertEquals(op1, op2);
        Assert.assertEquals((long) op1.hashCode(), (long) op2.hashCode());
        Assert.assertEquals(op1.toString(), op2.toString());
    }
}
