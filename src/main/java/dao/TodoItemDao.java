package dao;

public interface TodoItemDao {
    String add(String content, int priority);
    String readOverview(String id);
    String[] list();
    void remove(String id);
    void updateContent(String id, String newContent);
    void updatePriority(String id, int newPriority);
}
