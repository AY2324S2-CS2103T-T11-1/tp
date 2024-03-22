package seedu.address.logic;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;

class ArgumentMultimapTest {
    @Test
    public void verifyNoDuplicatePrefixesFor_duplicate_exception() {
        ArgumentMultimap argMultimap = new ArgumentMultimap();
        Prefix prefix = new Prefix("p/");
        try {
            argMultimap.verifyNoDuplicatePrefixesFor(new Prefix[] {prefix, prefix});
        } catch (ParseException e) {
            assertTrue(e instanceof ParseException);
        }
    }
    @Test
    public void verifyNoDuplicatePrefixesFor_noDuplicate_noException() {
        ArgumentMultimap argMultimap = new ArgumentMultimap();
        Prefix prefix = new Prefix("p/");
        try {
            argMultimap.verifyNoDuplicatePrefixesFor(new Prefix[] {prefix});
        } catch (ParseException e) {
            assertTrue(false);
        }
    }

}

