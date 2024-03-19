package seedu.address.logic.attribute;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.attributes.AddAttributeCommand;

public class AddAttributeCommandTest {

    @Test
    public void execute_null() {
        AddAttributeCommand addAttributeCommand =
                new AddAttributeCommand(ALICE.getUuidString(), "Name", "Alice");
        assertThrows(NullPointerException.class, () -> addAttributeCommand.execute(null));
    }

    @Test
    public void execute_pass() {
        ALICE.deleteAttribute("Name");
        ALICE.setAttribute("Name", "Alice Pauline");
        assertNotNull(ALICE.getAttribute("Name"));
    }

    @Test
    public void execute_fail() {
        AddAttributeCommand addAttributeCommand =
                new AddAttributeCommand(null, "Name", "Alice");
        assertThrows(NullPointerException.class, () -> addAttributeCommand.execute(null));
    }

    @Test
    public void execute_fail2() {
        AddAttributeCommand addAttributeCommand =
                new AddAttributeCommand(ALICE.getUuidString(), null, "Alice");
        assertThrows(NullPointerException.class, () -> addAttributeCommand.execute(null));
    }
}