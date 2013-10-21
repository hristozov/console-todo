package util.command;

import org.jmock.Expectations;
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
}
