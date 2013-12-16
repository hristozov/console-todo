package model;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity("todo")
public class TodoItem {
    @Id
    public ObjectId id;
    public String content = "";
    public List<Date> modificationDates = new ArrayList<>();
    public Integer priority = 0;
}
