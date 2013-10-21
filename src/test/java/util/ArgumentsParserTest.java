package util;

import org.junit.Assert;
import org.junit.Test;
import util.command.*;

@SuppressWarnings({"InstanceMethodNamingConvention", "DesignForExtension", "HardCodedStringLiteral",
        "DuplicateStringLiteralInspection"})
public class ArgumentsParserTest {
    private static Operation parse(final String... arguments) {
        return ArgumentsParser.parse(arguments);
    }

    @Test
    public void testAddOperationCreation() {
        Assert.assertEquals(new AddOperation("content"), ArgumentsParserTest.parse("add", "content"));
    }

    @Test
    public void testAddOperationCreationWithExplicitPriorities() {
        Assert.assertEquals(new AddOperation("content", 1337),
                ArgumentsParserTest.parse("add", "content", "1337"));
        Assert.assertEquals(new AddOperation("content", 0), ArgumentsParserTest.parse("add", "content", "0"));
        Assert.assertEquals(new AddOperation("content", -5),
                ArgumentsParserTest.parse("add", "content", "-5"));
    }

    @Test(expected = ParserException.class)
    public void testAddOperationCreationWithNoContentGiven() {
        ArgumentsParserTest.parse("add");
    }

    @Test(expected = ParserException.class)
    public void testAddOperationCreationWithTooManyArguments() {
        ArgumentsParserTest.parse("add", "bla", "5", "foo");
    }

    @Test
    public void testListOperationCreation() {
        Assert.assertEquals(new ListOperation(), ArgumentsParserTest.parse("list"));
    }

    @Test(expected = ParserException.class)
    public void testListOperationCreationWithTooManyArguments() {
        ArgumentsParserTest.parse("list", "foo");
    }

    @Test
    public void testRemoveOperationCreation() {
        Assert.assertEquals(new RemoveOperation("abcd"), ArgumentsParserTest.parse("remove", "abcd"));
    }

    @Test(expected = ParserException.class)
    public void testRemoveOperationCreationWithNoArguments() {
        ArgumentsParserTest.parse("remove");
    }

    @Test(expected = ParserException.class)
    public void testRemoveOperationCreationWithTooManyArguments() {
        ArgumentsParserTest.parse("remove", "foo", "bar");
    }

    @Test
    public void testUpdateContentOperationCreation() {
        Assert.assertEquals(new UpdateContentOperation("abab", "foo"),
                ArgumentsParserTest.parse("update-content", "abab", "foo"));
    }

    @Test(expected = ParserException.class)
    public void testUpdateContentOperationCreationWithNoArguments() {
        ArgumentsParserTest.parse("update-content");
    }

    @Test(expected = ParserException.class)
    public void testUpdateContentOperationCreationWithTooManyArguments() {
        ArgumentsParserTest.parse("update-content", "aaa", "bbb", "ccc");
    }

    @Test
    public void testUpdatePriorityOperationCreation() {
        Assert.assertEquals(new UpdatePriorityOperation("baz", 16),
                ArgumentsParserTest.parse("update-priority", "baz", "16"));
    }

    @Test(expected = ParserException.class)
    public void testUpdatePriorityOperationCreationWithNoArguments() {
        ArgumentsParserTest.parse("update-priority");
    }

    @Test(expected = ParserException.class)
    public void testUpdatePriorityOperationCreationWithTooManyArguments() {
        ArgumentsParserTest.parse("update-priority", "aaa", "bbb", "ccc");
    }

    @Test
    public void testReadOperationCreation() {
        Assert.assertEquals(new ReadOperation("baba"), ArgumentsParserTest.parse("read", "baba"));
    }

    @Test(expected = ParserException.class)
    public void testReadOperationCreationWithNoArguments() {
        ArgumentsParserTest.parse("read");
    }

    @Test(expected = ParserException.class)
    public void testReadOperationCreationWithTooManyArguments() {
        ArgumentsParserTest.parse("read", "foo", "bar");
    }

    @Test(expected = ParserException.class)
    public void testWithInvalidOperation() {
        ArgumentsParserTest.parse("bleh");
    }

    @Test(expected = ParserException.class)
    public void testWithInvalidOperationAndArguments() {
        ArgumentsParserTest.parse("bleh", "foo", "bar");
    }

    @Test(expected = ParserException.class)
    public void testWithNoArguments() {
        ArgumentsParserTest.parse();
    }
}
