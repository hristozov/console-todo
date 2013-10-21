package util.command;

import org.jmock.Expectations;
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
}
