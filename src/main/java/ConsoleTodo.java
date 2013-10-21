import com.mongodb.*;
import dao.MongoTodoItemDao;
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
        ServerAddress address = new ServerAddress(
                "localhost", 27017);
        MongoClient client = new MongoClient(address);
        DB database = client.getDB("sample-todo");
        DBCollection todoCollection =
                database.getCollection("todo");
        return new MongoTodoItemDao(todoCollection);
    }
}
