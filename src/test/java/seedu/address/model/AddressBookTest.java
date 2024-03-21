package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.CAT;
import static seedu.address.testutil.TypicalPersonsUuid.ALICE;
import static seedu.address.testutil.TypicalPersonsUuid.HOON;
import static seedu.address.testutil.TypicalPersonsUuid.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.relationship.Relationship;
import seedu.address.testutil.PersonBuilder;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        addressBook.addPerson(ALICE);
        assertTrue(addressBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(addressBook.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getPersonList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = AddressBook.class.getCanonicalName() + "{persons=" + addressBook.getPersonList() + ","
                + " relationships=" + "}";
        assertEquals(expected, addressBook.toString());
    }

    @Test
    void deleteAttribute_existingAttribute_removesAttribute() {
        assertTrue(CAT.hasAttribute("Name"), "Attribute should exist before deletion.");

        CAT.deleteAttribute("Name");

        assertFalse(CAT.hasAttribute("Name"), "Attribute should not exist after deletion.");

        CAT.setAttribute("Name", "Cat");
    }

    @Test
    void getPersonByUuid_existingUuid_returnsPerson() {
        Person result = HOON;
        String uuidString = result.getUuid().toString();

        assertNotNull(result, "Should return a person for existing UUID.");
        assertEquals(uuidString, result.getUuid().toString(), "Returned person should have the same UUID.");
    }

    @Test
    void hasAttribute_existingAttribute_returnsTrue() {
        boolean result = HOON.hasAttribute("Name");

        assertTrue(result, "Should return true for an existing attribute.");
    }

    @Test
    void hasAttribute_nonExistentAttribute_returnsFalse() {
        boolean result = HOON.hasAttribute("Pet");

        assertFalse(result, "Should return false for a non-existent attribute.");
    }

    @Test
    void deleteAttribute_test() {
        addressBook.addPerson(HOON);
        addressBook.deleteAttribute(HOON.getUuidString(), "Name");
        assertFalse(HOON.hasAttribute("Name"));
        HOON.setAttribute("Name", "Hoon Meier");
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();
        private final List<Relationship> relationships = Collections.emptyList();

        AddressBookStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }

        @Override
        public List<Relationship> getRelationshipList() {
            return relationships;
        }
    }

}
