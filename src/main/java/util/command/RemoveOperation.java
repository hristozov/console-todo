package util.command;

public class RemoveOperation extends AbstractOperation {
    private final String id;

    public RemoveOperation(final String id) {
        this.id = id;
    }

    @Override
    public void run() {
        this.dao.remove(this.id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RemoveOperation that = (RemoveOperation) o;

        if (!id.equals(that.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "RemoveOperation{" +
                "id='" + id + '\'' +
                '}';
    }
}
