package dao;


import com.mongodb.*;
import org.bson.types.ObjectId;

import java.util.*;

public class MongoTodoItemDao implements TodoItemDao {
    private static final String ID_FIELD = "_id";
    private static final String CONTENT_FIELD = "content";
    private static final String PRIORITY_FIELD = "priority";
    private static final String MODIFICATION_DATES = "modificationDates";

    private DBCollection todoCollection;

    public MongoTodoItemDao(final DBCollection todoCollection) {
        this.todoCollection = todoCollection;
    }

    @Override
    public String add(final String content, final int priority) {
        final ObjectId id = new ObjectId();
        final DBObject newItem =
                new BasicDBObject();
        newItem.put(MongoTodoItemDao.ID_FIELD, id);
        newItem.put(MongoTodoItemDao.CONTENT_FIELD, content);
        newItem.put(MongoTodoItemDao.PRIORITY_FIELD, Integer.valueOf(priority));
        newItem.put(MongoTodoItemDao.MODIFICATION_DATES, new BasicDBList());
        this.todoCollection.insert(newItem);
        return id.toString();
    }

    @Override
    public String readOverview(final String id) {
        final ObjectId objectId = new ObjectId(id);
        final DBObject query =
                new BasicDBObject(MongoTodoItemDao.ID_FIELD, objectId);
        final DBObject targetItem = this.todoCollection.findOne(query);

        if (targetItem == null) {
            return "No such todo item :(";
        }

        final String content = (String) targetItem.get(
                MongoTodoItemDao.CONTENT_FIELD);
        Number priority = (Number) targetItem.get(
                MongoTodoItemDao.PRIORITY_FIELD);
        if (priority == null) {
            priority = Integer.valueOf(0);
        }

        BasicDBList dates = (BasicDBList) targetItem.get(MODIFICATION_DATES);
        if (dates != null) {
            String datesJoined = "";
            for (Object date : dates) {
                datesJoined += ((Date) date).toString();
            }
            return String.format(
                    "id: %s; content: %s; priority: %d; modified: %s",
                    id, content, priority, datesJoined);
        } else {
            return String.format(
                    "id: %s; content: %s; priority: %d",
                    id, content, priority);
        }
    }

    @Override
    public String[] list() {
        final DBCursor items = this.todoCollection.find();
        final Collection<String> result = new ArrayList<>();
        for (final DBObject item : items) {
            final String id = item.get(MongoTodoItemDao.ID_FIELD).toString();
            result.add(id);
        }
        return Arrays.copyOf(result.toArray(), result.size(), String[].class);
    }

    @Override
    public void remove(final String id) {
        final DBObject query = new BasicDBObject(
                MongoTodoItemDao.ID_FIELD, new ObjectId(id));
        this.todoCollection.remove(query);
    }

    @Override
    public void updateContent(final String id, final String newContent) {
        final DBObject query = new BasicDBObject(
                MongoTodoItemDao.ID_FIELD, new ObjectId(id));
        final DBObject update = new BasicDBObject();
        update.put("$set",
                new BasicDBObject(MongoTodoItemDao.CONTENT_FIELD, newContent));
        update.put("$push",
                new BasicDBObject(MongoTodoItemDao.MODIFICATION_DATES,
                        new Date()));
        this.todoCollection.update(query, update);
    }

    @Override
    public void updatePriority(final String id, final int newPriority) {
        final DBObject query = new BasicDBObject(
                MongoTodoItemDao.ID_FIELD, new ObjectId(id));
        final DBObject update = new BasicDBObject();
        update.put("$set",
                new BasicDBObject(MongoTodoItemDao.PRIORITY_FIELD,
                        Integer.valueOf(newPriority)));
        update.put("$push",
                new BasicDBObject(MongoTodoItemDao.MODIFICATION_DATES,
                        new Date()));
        this.todoCollection.update(query, update);
    }
}
