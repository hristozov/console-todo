package util.command;

import org.jmock.Expectations;
import org.junit.Test;

public class ListOperationTest extends BaseOperationTest {
    @Test
    public void testReadOperation() {
        Operation listOperation = new ListOperation();
        listOperation.setDao(mockDao);
        this.context.checking(new Expectations() {{
            oneOf(mockDao).list();
            will(returnValue(new String[] {"bar"}));
        }});
        listOperation.run();
    }
}
