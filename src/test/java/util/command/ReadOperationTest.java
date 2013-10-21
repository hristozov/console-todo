package util.command;

import org.jmock.Expectations;
import org.junit.Assert;
import org.junit.Test;

public class ReadOperationTest extends BaseOperationTest {
    @Test
    public void testReadOperation() {
        Operation readOperation = new ReadOperation("foo");
        readOperation.setDao(mockDao);
        this.context.checking(new Expectations() {{
            oneOf(ReadOperationTest.this.mockDao).readOverview("foo");
            will(returnValue("bar"));
        }});
        readOperation.run();
    }

    @Test
    public void testEqualsAndHashCode() {
        final Operation op1 = new ReadOperation("boo");
        final Operation op2 = new ReadOperation("boo");
        Assert.assertEquals(op1, op2);
        Assert.assertEquals((long) op1.hashCode(), (long) op2.hashCode());
        Assert.assertEquals(op1.toString(), op2.toString());
    }
}
