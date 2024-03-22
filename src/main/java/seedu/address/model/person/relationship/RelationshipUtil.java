package seedu.address.model.person.relationship;

import seedu.address.model.person.Person;

import java.util.ArrayList;

/**
 * Represents a utility class for managing the relationships associated with a person.
 * Allows for adding, deleting, and checking for existing relationships.
 */
public class RelationshipUtil {
    private ArrayList<Relationship> relationshipsTracker = new ArrayList<>();

    /**
     * Adds a new relationship to the tracker.
     * @param toAdd The relationship to be added.
     */
    public void addRelationship(Relationship toAdd) {
        relationshipsTracker.add(toAdd);
    }

    /**
     * Deletes a specific relationship from the tracker.
     * @param toDelete The relationship to be deleted.
     */
    public void deleteRelationship(Relationship toDelete) {
        relationshipsTracker.remove(toDelete);
    }

    /**
     * Checks if a specific relationship exists in the tracker.
     * @param toFind The relationship to find.
     * @return true if the relationship exists, false otherwise.
     */
    public boolean hasRelationship(Relationship toFind) {
        return !relationshipsTracker.isEmpty() && relationshipsTracker.contains(toFind);
    }

    /**
     * Checks if a specific relationship exists in the tracker.
     * @param toFind The relationship to find.
     * @return true if the relationship exists, false otherwise.
     */
    public boolean hasRelationshipWithDescriptor(Relationship toFind) {
        for (Relationship relationship : relationshipsTracker) {
            if ((relationship.getPerson1().equals(toFind.getPerson1()) && relationship.getPerson2()
                    .equals(toFind.getPerson2())
                    || (relationship.getPerson1().equals(toFind.getPerson2()) && relationship.getPerson2()
                            .equals(toFind.getPerson1()))) && relationship.getRoleDescriptor(
                            toFind.getPerson1()).equals(toFind.getRoleDescriptor(toFind.getPerson2()))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retrieves a string representation of an existing relationship in the tracker.
     * @param toGet The relationship to retrieve.
     * @return A string representation of the specified relationship if it exists.
     * @throws IndexOutOfBoundsException if the relationship does not exist in the tracker.
     */
    public String getExistingRelationship(Relationship toGet) {
        for (Relationship relationship : relationshipsTracker) {
            if (relationship.equals(toGet)) {
                return relationship.toString();
            }
        }
        throw new IllegalArgumentException("Relationship does not exist.");
    }

    /**
     * Retrieves the list of relationships in the tracker.
     * @return The list of relationships in the tracker.
     */
    public ArrayList<Relationship> getRelationshipsTracker() {
        return relationshipsTracker;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RelationshipUtil)) {
            return false;
        }
        RelationshipUtil other = (RelationshipUtil) o;
        return relationshipsTracker.equals(other.relationshipsTracker);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Relationship relationship : relationshipsTracker) {
            sb.append(relationship.toString());
            sb.append("\n");
        }
        return sb.toString();
    }

    public String getImmediateRelationshipsAsString(Person person) {
        StringBuilder sb = new StringBuilder();
        for (Relationship relationship : relationshipsTracker) {
            if (relationship.getPerson1().equals(person) || relationship.getPerson2().equals(person)) {
                // roleName of personName
                sb.append(relationship.getRoleDescriptor(person.getUuid()));
                sb.append(" of ");
                sb.append(relationship.getPerson1().equals(person) ? relationship.getPerson2()
                        : relationship.getPerson1());
            }
        }
        return sb.toString();
    }
}
