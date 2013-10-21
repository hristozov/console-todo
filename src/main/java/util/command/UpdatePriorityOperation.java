package util.command;

public class UpdatePriorityOperation extends AbstractOperation {
    private final String id;
    private final int newPriority;

    public UpdatePriorityOperation(final String id, final int newPriority) {
        this.id = id;
        this.newPriority = newPriority;
    }

    @Override
    public void run() {
        this.dao.updatePriority(this.id, this.newPriority);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UpdatePriorityOperation that = (UpdatePriorityOperation) o;

        if (newPriority != that.newPriority) return false;
        if (!id.equals(that.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + newPriority;
        return result;
    }

    @Override
    public String toString() {
        return "UpdatePriorityOperation{" +
                "id='" + id + '\'' +
                ", newPriority=" + newPriority +
                '}';
    }
}
