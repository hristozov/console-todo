package util;

import util.command.*;

import java.util.Arrays;

public class ArgumentsParser {
    public static Operation parse(final String[] args) {
        if (args.length == 0) {
            ArgumentsParser.error("No arguments given.");
        }
        final String operation = args[0];
        String[] operationArguments = new String[0];
        if (args.length > 1) {
            operationArguments = Arrays.copyOfRange(args, 1, args.length);
        }
        switch (operation) {
            case "list":
                return ArgumentsParser.getListOperation(operationArguments);
            case "add":
                return ArgumentsParser.getAdditionOperation(operationArguments);
            case "remove":
                return ArgumentsParser.getRemoveOperation(operationArguments);
            case "update-content":
                return ArgumentsParser.getUpdateContentOperation(operationArguments);
            case "update-priority":
                return ArgumentsParser.getUpdatePriorityOperation(operationArguments);
            case "read":
                return ArgumentsParser.getReadOperation(operationArguments);
            default:
                ArgumentsParser.error("Invalid arguments provided.");
        }
        return ArgumentsParser.getNoop();
    }

    private static Operation getListOperation(final String[] args) {
        if (args.length > 0) {
            ArgumentsParser.error("Invalid number of arguments for the add operation.");
        }
        return new ListOperation();
    }

    private static Operation getAdditionOperation(final String[] args) {
        if (args.length == 1) {
            return new AddOperation(args[0]);
        } else if (args.length == 2) {
            final int priority = Integer.parseInt(args[1]);
            return new AddOperation(args[0], priority);
        } else {
            ArgumentsParser.error("Invalid number of arguments for the add operation.");
            return ArgumentsParser.getNoop();
        }
    }

    private static Operation getRemoveOperation(final String[] args) {
        if (args.length != 1) {
            ArgumentsParser.error("Invalid number of arguments for the remove operation.");
        }
        return new RemoveOperation(args[0]);
    }

    private static Operation getUpdateContentOperation(final String[] args) {
        if (args.length != 2) {
            ArgumentsParser.error("Invalid number of arguments for the update-content operation.");
        }

        return new UpdateContentOperation(args[0], args[1]);
    }

    private static Operation getUpdatePriorityOperation(final String[] args) {
        if (args.length != 2) {
            ArgumentsParser.error("Invalid number of arguments for the update-priority operation.");
        }
        final int priority = Integer.parseInt(args[1]);
        return new UpdatePriorityOperation(args[0], priority);
    }

    private static Operation getReadOperation(final String[] args) {
        if (args.length != 1) {
            ArgumentsParser.error("Invalid number of arguments for the read operation.");
        }
        return new ReadOperation(args[0]);
    }

    private static Operation getNoop() {
        return new Noop();
    }

    private static void error(final String message) {
        throw new ParserException(message);
    }
}

class Noop extends AbstractOperation {
    @Override
    public void run() {
        System.out.println("<aborted>");
    }
}
