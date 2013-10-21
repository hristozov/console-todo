package util.command;

import dao.TodoItemDao;

public interface Operation extends Runnable {
    void setDao(TodoItemDao dao);
}
