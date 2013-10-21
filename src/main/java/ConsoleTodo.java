import dao.TodoItemDao;
import util.ArgumentsParser;
import util.command.Operation;

import java.net.UnknownHostException;

public class ConsoleTodo {
    public static void main(final String[] args) throws UnknownHostException {
        final TodoItemDao dao = ConsoleTodo.getDao();
        final Operation operation = ArgumentsParser.parse(args);
        operation.setDao(dao);
        operation.run();
    }

    private static TodoItemDao getDao() throws UnknownHostException {
        return null;
    }
}
