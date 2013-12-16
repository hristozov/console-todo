package dao;


import model.TodoItem;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;

import java.util.*;

public class MongoTodoItemDao implements TodoItemDao {
    private Datastore datastore;

    public MongoTodoItemDao(final Datastore datastore) {
        this.datastore = datastore;
    }

    @Override
    public String add(final String content, final int priority) {
        final TodoItem todoItem = new TodoItem();
        todoItem.content = content;
        todoItem.priority = Integer.valueOf(priority);
        this.datastore.save(todoItem);
        return todoItem.id.toString();
    }

    @Override
    public String readOverview(final String id) {
        final TodoItem targetItem = this.datastore.get(TodoItem.class, new ObjectId(id));

        if (targetItem == null) {
            return "No such todo item :(";
        }

        final List<Date> dates = targetItem.modificationDates;
        if (dates != null) {
            String datesJoined = "";
            for (final Object date : dates) {
                datesJoined += ((Date) date).toString();
            }
            return String.format(
                    "id: %s; content: %s; priority: %d; modified: %s",
                    id, targetItem.content, targetItem.priority, datesJoined);
        } else {
            return String.format(
                    "id: %s; content: %s; priority: %d",
                    id, targetItem.content, targetItem.priority);
        }
    }

    @Override
    public String[] list() {
        final List<TodoItem> items = this.datastore.find(TodoItem.class).asList();
        final Collection<String> result = new ArrayList<>(items.size());
        for (final TodoItem item : items) {
            result.add(item.id.toString());
        }
        return Arrays.copyOf(result.toArray(), result.size(), String[].class);
    }

    @Override
    public void remove(final String id) {
        this.datastore.delete(TodoItem.class, id);
    }

    @Override
    public void updateContent(final String id, final String newContent) {
        final TodoItem targetItem = this.datastore.get(TodoItem.class, new ObjectId(id));
        if (targetItem == null) {
            return;
        }
        targetItem.content = newContent;
        targetItem.modificationDates.add(new Date());
        this.datastore.save(targetItem);
    }

    @Override
    public void updatePriority(final String id, final int newPriority) {
        final TodoItem targetItem = this.datastore.get(TodoItem.class, new ObjectId(id));
        if (targetItem == null) {
            return;
        }
        targetItem.priority = Integer.valueOf(newPriority);
        targetItem.modificationDates.add(new Date());
        this.datastore.save(targetItem);
    }
}
