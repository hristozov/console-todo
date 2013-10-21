package util.command;

import org.jmock.Expectations;
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
}
