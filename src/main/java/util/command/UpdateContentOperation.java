package util.command;

public class UpdateContentOperation extends AbstractOperation {
    private final String id;
    private final String newContent;

    public UpdateContentOperation(final String id, final String newContent) {
        this.id = id;
        this.newContent = newContent;
    }

    @Override
    public void run() {
        this.dao.updateContent(this.id, this.newContent);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UpdateContentOperation that = (UpdateContentOperation) o;

        if (!id.equals(that.id)) return false;
        if (!newContent.equals(that.newContent)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + newContent.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "UpdateContentOperation{" +
                "id='" + id + '\'' +
                ", newContent='" + newContent + '\'' +
                '}';
    }
}
