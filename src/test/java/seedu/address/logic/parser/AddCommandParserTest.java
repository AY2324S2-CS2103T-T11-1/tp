package seedu.address.logic.parser;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test void parse_emptyArgs_success() {
        HashMap<String, String> expectedAttributeMap = new HashMap<>();
        assertParseSuccess(parser, "add", new AddCommand(expectedAttributeMap));
    }
    @Test
    public void parse_singleAttribute_success() {
        HashMap<String, String> expectedAttributeMap = new HashMap<>();
        expectedAttributeMap.put("Name", VALID_NAME_BOB);

        // whitespace only preamble
        assertParseSuccess(parser, "add /Name " + VALID_NAME_BOB, new AddCommand(expectedAttributeMap));
    }

    @Test
    public void parse_multipleAttributes_success() {
        HashMap<String, String> expectedAttributeMap = new HashMap<>();
        expectedAttributeMap.put("Name", VALID_NAME_BOB);
        expectedAttributeMap.put("Phone", VALID_PHONE_BOB);
        expectedAttributeMap.put("Email", VALID_EMAIL_BOB);
        expectedAttributeMap.put("Address", VALID_ADDRESS_BOB);
        expectedAttributeMap.put("Tag", VALID_TAG_FRIEND);

        assertParseSuccess(parser, "add /Name " + VALID_NAME_BOB + " /Phone " + VALID_PHONE_BOB + " /Email "
                + VALID_EMAIL_BOB + " /Address " + VALID_ADDRESS_BOB + " /Tag "
                + VALID_TAG_FRIEND, new AddCommand(expectedAttributeMap));
    }

    @Test
    public void parse_incompleteAttribute_parseException() {
        String command = "add /Name";
        try {
            parser.parse(command);
        } catch (ParseException e) {
            assert e.getMessage().equals("Invalid command format! \n" + AddCommand.MESSAGE_USAGE);
        }
    }
}
