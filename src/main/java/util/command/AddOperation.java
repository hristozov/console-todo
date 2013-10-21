package util.command;

public class AddOperation extends AbstractOperation {
    private static final int DEFAULT_PRIORITY = 0;
    private final String content;
    private final int priority;

    public AddOperation(final String content) {
        this(content, AddOperation.DEFAULT_PRIORITY);
    }

    public AddOperation(final String content, final int priority) {
        this.content = content;
        this.priority = priority;
    }

    @Override
    public void run() {
        final String id = this.dao.add(this.content, this.priority);
        System.out.println("Added as " + id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AddOperation that = (AddOperation) o;

        if (priority != that.priority) return false;
        if (!content.equals(that.content)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = content.hashCode();
        result = 31 * result + priority;
        return result;
    }

    @Override
    public String toString() {
        return "AddOperation{" +
                "content='" + content + '\'' +
                ", priority=" + priority +
                '}';
    }
}
