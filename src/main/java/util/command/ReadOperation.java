package util.command;

public class ReadOperation extends AbstractOperation {
    private final String id;

    public ReadOperation(final String id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println(String.format("Overview for %s: %s\n", this.id,
                this.dao.readOverview(this.id)));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReadOperation that = (ReadOperation) o;

        if (!id.equals(that.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "ReadOperation{" +
                "id='" + id + '\'' +
                '}';
    }
}
