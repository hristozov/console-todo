import com.mongodb.*;
import dao.MongoTodoItemDao;
import dao.TodoItemDao;
import model.TodoItem;
import org.mongodb.morphia.Morphia;
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
        final ServerAddress address = new ServerAddress(
                "localhost", 27017);
        final MongoClient client = new MongoClient(address);
        final Morphia morphia = new Morphia();
        morphia.map(TodoItem.class);
        return new MongoTodoItemDao(morphia.createDatastore(client, "sample-todo"));
    }
}
