package seedu.address.model.person.relationship;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.testutil.TypicalPersonsUuid.getTypicalAddressBook;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.AddRelationshipCommandParser;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class RelationshipTest {
    private static final UUID PERSON_1_UUID = UUID.fromString("00000000-0000-0000-0000-000000000001");
    private static final UUID PERSON_2_UUID = UUID.fromString("00000000-0000-0000-0000-000000000002");
    private static final String SIBLINGS_RELATIONSHIP_DESCRIPTOR = "siblings";
    private static final String FRIEND_RELATIONSHIP_DESCRIPTOR = "friend";
    private static final String INVALID_RELATIONSHIP_DESCRIPTOR = "123";

    private Model model;
    private AddRelationshipCommandParser parser = new AddRelationshipCommandParser();
    @BeforeEach
    void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }
    @Test
    public void testConstructorWithValidSiblingsDescriptorDoesNotThrowIllegalArgumentException() {
        assertDoesNotThrow(() -> new Relationship(PERSON_1_UUID, PERSON_2_UUID, SIBLINGS_RELATIONSHIP_DESCRIPTOR));
    }
    @Test
    public void testConstructorWithInvalidSiblingsDescriptorDoesThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () ->
                new Relationship(PERSON_1_UUID, PERSON_2_UUID, INVALID_RELATIONSHIP_DESCRIPTOR));
    }
    @Test
    public void getPerson1_returnsCorrectUuid() {
        Relationship test = new Relationship(PERSON_1_UUID, PERSON_2_UUID, SIBLINGS_RELATIONSHIP_DESCRIPTOR);
        assertEquals(PERSON_1_UUID, test.getPerson1());
    }
    @Test
    public void getPerson2_returnsCorrectUuid() {
        Relationship test = new Relationship(PERSON_1_UUID, PERSON_2_UUID, SIBLINGS_RELATIONSHIP_DESCRIPTOR);
        assertEquals(PERSON_2_UUID, test.getPerson2());
    }
    @Test
    public void testEqualsMethod() {
        Relationship test1 = new Relationship(PERSON_1_UUID, PERSON_2_UUID, SIBLINGS_RELATIONSHIP_DESCRIPTOR);
        Relationship test2 = new Relationship(PERSON_1_UUID, PERSON_2_UUID, SIBLINGS_RELATIONSHIP_DESCRIPTOR);
        Relationship test3 = new Relationship(PERSON_2_UUID, PERSON_1_UUID, SIBLINGS_RELATIONSHIP_DESCRIPTOR);
        Relationship test4 = new Relationship(PERSON_2_UUID, PERSON_1_UUID, FRIEND_RELATIONSHIP_DESCRIPTOR);
        assertEquals(test1.equals(test2), true);
        assertEquals(test1.equals(test3), true);
        assertEquals(test3.equals(test4), false);
        assertEquals(test1.equals(test4), false);
    }
    @Test
    public void testStringMethod() {
        Relationship test1 = new Relationship(PERSON_1_UUID, PERSON_2_UUID, SIBLINGS_RELATIONSHIP_DESCRIPTOR);
        assertEquals(
                String.format("%s and %s are %s",
                        PERSON_1_UUID.toString(),
                        PERSON_2_UUID.toString(),
                        SIBLINGS_RELATIONSHIP_DESCRIPTOR), test1.toString());
    }

    @Test
    public void testEqualsMethod_objectNotInstanceOfRelationship_returnsFalse() {
        // Setup
        Relationship relationship = new Relationship(PERSON_1_UUID, PERSON_2_UUID, SIBLINGS_RELATIONSHIP_DESCRIPTOR);
        Object notARelationship = new Object(); // Creating a non-Relationship object

        // Verify
        assertFalse(relationship.equals(notARelationship));
    }
    @Test
    void testGetRelativeRelationshipDescriptorWithoutUuid() {
        UUID person1Uuid = UUID.fromString("00000000-0000-0000-0000-000000000001");
        UUID person2Uuid = UUID.fromString("00000000-0000-0000-0000-000000000002");
        // Assuming Relationship is an abstract class and MyRelationship is a concrete subclass
        Relationship relationship = new Relationship(person1Uuid, person2Uuid, "parent");
        String expected = "parent of";
        assertEquals(expected, relationship.getRelativeRelationshipDescriptorWithoutUuid(person1Uuid));
    }

    @Test
    void testGetRelativeRelationshipDescriptor() {
        UUID person1Uuid = UUID.fromString("00000000-0000-0000-0000-000000000001");
        UUID person2Uuid = UUID.fromString("00000000-0000-0000-0000-000000000002");
        // Assuming Relationship is an abstract class and MyRelationship is a concrete subclass
        Relationship relationship = new SiblingRelationship(person1Uuid, person2Uuid,
                 "brother", "sister");
        String expected = " (siblings) brother of ";
        assertEquals(expected, relationship.getRelativeRelationshipDescriptorWithoutUuid(person1Uuid));
    }
}
