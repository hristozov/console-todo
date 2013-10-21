package util.command;

import org.jmock.Expectations;
import org.junit.Assert;
import org.junit.Test;

public class ListOperationTest extends BaseOperationTest {
    @Test
    public void testReadOperation() {
        Operation listOperation = new ListOperation();
        listOperation.setDao(mockDao);
        this.context.checking(new Expectations() {{
            oneOf(mockDao).list();
            will(returnValue(new String[]{"bar"}));
        }});
        listOperation.run();
    }

    @Test
    public void testEqualsAndHashCode() {
        final Operation op1 = new ListOperation();
        final Operation op2 = new ListOperation();
        Assert.assertEquals(op1, op2);
        Assert.assertEquals((long) op1.hashCode(), (long) op2.hashCode());
        Assert.assertEquals(op1.toString(), op2.toString());
    }
}
