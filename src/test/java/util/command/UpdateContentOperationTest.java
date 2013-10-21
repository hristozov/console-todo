package util.command;

import org.jmock.Expectations;
import org.junit.Test;

public class UpdateContentOperationTest extends BaseOperationTest {
    @Test
    public void testUpdateContentOperation() {
        final Operation updateContentOperation = new UpdateContentOperation("boo", "bar");
        updateContentOperation.setDao(this.mockDao);
        this.context.checking(new Expectations() {{
            oneOf(mockDao).updateContent("boo", "bar");
        }});
        updateContentOperation.run();
    }
}
