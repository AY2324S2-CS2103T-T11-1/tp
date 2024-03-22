package seedu.address.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.person.Person;
import seedu.address.model.person.attribute.Attribute;

class MessagesTest {
    @Test
    public void getErrorMessageForDuplicatePrefixes() {
        assertEquals(Messages.MESSAGE_DUPLICATE_FIELDS + "name",
                Messages.getErrorMessageForDuplicatePrefixes(new Prefix("name")));
    }

    @Test
    public void format() {
        Person person = new Person(new Attribute[0]);
        assertEquals("Details:\n"
                + "No details available", Messages.format(person));
    }

}

