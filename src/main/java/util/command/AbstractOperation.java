package util.command;

import dao.TodoItemDao;

public abstract class AbstractOperation implements Operation {
    protected TodoItemDao dao = null;

    @Override
    public void setDao(final TodoItemDao dao) {
        this.dao = dao;
    }
}
