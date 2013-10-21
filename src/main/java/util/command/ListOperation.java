package util.command;

import org.apache.commons.lang3.StringUtils;

public class ListOperation extends AbstractOperation {
    @Override
    public void run() {
        final String[] ids = this.dao.list();
        System.out.println(StringUtils.join(ids, "\n"));
    }

    @Override
    public boolean equals(final Object obj) {
        return obj instanceof ListOperation;
    }

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public String toString() {
        return "ListOperation{}";
    }
}
