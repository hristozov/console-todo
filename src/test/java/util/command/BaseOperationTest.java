package util.command;

import dao.TodoItemDao;
import org.jmock.Mockery;
import org.junit.After;
import org.junit.Before;

public abstract class BaseOperationTest {
    protected Mockery context = null;
    protected TodoItemDao mockDao = null;

    @Before
    public void setUp() {
        this.context = new Mockery();
        this.mockDao = this.context.mock(TodoItemDao.class);
    }

    @After
    public void tearDown() {
        this.context.assertIsSatisfied();
    }
}
