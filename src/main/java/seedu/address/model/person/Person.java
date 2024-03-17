package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.attribute.Attribute;
import seedu.address.model.person.attribute.NameAttribute;
import seedu.address.model.person.attribute.StringAttribute;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {
    private final UUID uuid;
    // Data fields
    private final Set<Tag> tags = new HashSet<>();
    private final HashMap<String, Attribute> attributes = new HashMap<>();

    /**
     * THIS CONSTRUCTOR WILL BE DEPRECATED.
     * Constructs a person with a random UUID.
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, tags);
        this.uuid = UUID.randomUUID();
        this.attributes.put("Name", new NameAttribute("Name", name.toString()));
        this.attributes.put("Phone", new NameAttribute("Phone", phone.toString()));
        this.attributes.put("Email", new StringAttribute("Email", email.toString()));
        this.attributes.put("Address", new StringAttribute("Address", address.toString()));

        this.tags.addAll(tags); // Earmarked for deprecation - to be superseded by relations
    }

    /**
     * Constructs a person with a random UUID and a list of attributes.
     * There are no compulsory fields, attribute list can be null.
     *
     * @params attributes A list of attributes to be added to the person.
     *      If the list is null, the person will have no attributes.
     *      If there are multiple attributes of the same type, the last one will be used.
     * @return A person with the given attributes.
     */
    public Person(Attribute[] attributes) {
        this.uuid = UUID.randomUUID();
        for (Attribute attribute : attributes) {
            this.attributes.put(attribute.getName(), attribute);
        }
    }

    public Name getName() { //Earmarked for deprecation - superseded by getAttribute - name should be optional
        if (!attributes.containsKey("Name")) {
            return new Name("<no name>");
        }
        return new Name(attributes.get("Name").getValueAsString());
    }

    public Phone getPhone() { //Earmarked for deprecation - superseded by getAttribute - phone should be optional
        if (!attributes.containsKey("Phone")) {
            return new Phone("<no phone>");
        }
        return new Phone(attributes.get("Phone").getValueAsString());
    }

    public Email getEmail() { //Earmarked for deprecation - superseded by getAttribute - email should be optional
        if (!attributes.containsKey("Email")) {
            return new Email("<no email>");
        }
        return new Email(attributes.get("Email").getValueAsString());
    }

    public Address getAddress() { //Earmarked for deprecation - superseded by getAttribute - address should be optional
        if (!attributes.containsKey("Address")) {
            return new Address("<no address>");
        }
        return new Address(attributes.get("Address").getValueAsString());
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() { //Earmarked for deprecation - to be superseded by relations
        if (tags.isEmpty()) {
            return Collections.unmodifiableSet(new HashSet<>());
        }
        return Collections.unmodifiableSet(tags);
    }
    /**
     * Returns the uuid of the person.
     *
     * @return The uuid of the person.
     */
    public UUID getUuid() {
        return uuid;
    }
    /**
     * Returns the uuid of the person as a string.
     *
     * @return The uuid of the person as a string.
     */
    public String getUuidString() {
        return uuid.toString();
    }
    /**
     * Returns whether the person has an attribute with the given type.
     *
     * @param attributeName The type of the attribute to check for.
     * @return True if the person has an attribute with the given type.
     */
    public boolean hasAttribute(String attributeName) {
        return attributes.containsKey(attributeName);
    }
    /**
     * Returns an attribute of the person.
     *
     * @param attributeName The type of the attribute to get.
     * @return The attribute with the given type.
     */
    public Attribute getAttribute(String attributeName) {
        assertValidAttributeName(attributeName);
        assertAttributeExistsInPerson(attributeName, attributes);
        return attributes.get(attributeName);
    }
    /**
     * Edits/adds an attribute of/to the person.
     *
     * @param attribute The attribute to edit or add.
     */
    public void updateAttribute(Attribute attribute) {
        assertValidAttribute(attribute);
        attributes.put(attribute.getName(), attribute);
    }

    /**
     * Deletes an attribute from the person.
     *
     * @param attributeType The type of the attribute to delete.
     */
    public void deleteAttribute(String attributeType) {
        if (!attributes.containsKey(attributeType)) {
            throw new IllegalArgumentException("Attribute with name " + attributeType + " does not exist");
        }
        attributes.remove(attributeType);
    }

    private static void assertValidAttribute(Attribute attribute) {
        if (attribute == null) {
            throw new IllegalArgumentException("Attribute cannot be null.");
        }
        assertValidAttributeName(attribute.getName());
    }

    private static void assertValidAttributeName(String attributeType) {
        if (attributeType == "") {
            throw new IllegalArgumentException("Attribute name cannot be empty.");
        }
    }

    private static void assertAttributeExistsInPerson(String attributeType, Map<String, Attribute> attributes) {
        if (!attributes.containsKey(attributeType)) {
            throw new IllegalArgumentException("Attribute with name " + attributeType + " does not exist");
        }
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson
                .attributes.get("Name").getValueAsString().equals(
                        attributes.get("Name").getValueAsString());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Person)) {
            return false;
        }
        Person otherPerson = (Person) other;
        // compare all attributes
        for (Map.Entry<String, Attribute> entry : attributes.entrySet()) {
            if (!otherPerson.hasAttribute(entry.getKey())) {
                return false;
            }
            if (!entry.getValue().getValueAsString().equals(
                    otherPerson.getAttribute(entry.getKey()).getValueAsString())) {
                return false;
            }
        }
        if (!tags.equals(otherPerson.tags)) { // Earmarked for deprecation - to be superseded by relations
            return false;
        }
        return true;
    }
    /**
     * Returns true if the UUID of the person is the same as the UUID of the other object.
     *
     * @param other The object to compare with.
     * @return True if the UUID of the person is the same as the UUID of the other object.
     */
    public boolean equalsUuid(Object other) {
        if (!(other instanceof Person)) {
            return false;
        }

        if (other == this) {
            return true;
        }

        Person otherPerson = (Person) other;
        return uuid.equals(otherPerson.uuid);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(
                attributes,
                uuid);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("uuid", uuid)
                .add("name", getName().toString())
                .add("phone", getPhone().toString())
                .add("email", getEmail().toString())
                .add("address", getAddress().toString())
                .add("tags", getTags()) // Earmarked for deprecation - to be superseded by relations
                .toString();
    }
    /**
     * Returns a string representation of all the attributes of the person.
     * The attributes are sorted by length of the string representation.
     *
     * @return A string representation of all the attributes of the person.
     */
    public String allAttributesAsString() {
        StringBuilder sb = new StringBuilder();
        if (attributes.isEmpty()) {
            return "No details available";
        }
        List<Map.Entry<String, Attribute>> sortedAttributes = new ArrayList<>(attributes.entrySet());
        sortedAttributes.sort(
                Comparator.comparingInt(
                        entry -> (entry.getKey() + ": " + entry.getValue().getValueAsString()).length()));

        for (Map.Entry<String, Attribute> entry : sortedAttributes) {
            sb.append(entry.getKey()).append(": ").append(entry.getValue().getValueAsString()).append("\n");
        }
        return sb.toString().trim();
    }

    public Set<Attribute> getAttributes() {
        return new HashSet<>(attributes.values());
    }
}
