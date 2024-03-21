package seedu.address.model.person.relationship;

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
     * Retrieves a string representation of an existing relationship in the tracker.
     * @param toGet The relationship to retrieve.
     * @return A string representation of the specified relationship if it exists.
     * @throws IndexOutOfBoundsException if the relationship does not exist in the tracker.
     */
    public String getExistingRelationship(Relationship toGet) {
        int index = relationshipsTracker.indexOf(toGet);
        if (index == -1) {
            throw new IndexOutOfBoundsException("Relationship does not exist.");
        }
        return relationshipsTracker.get(index).toString();
    }

    /**
     * Retrieves the list of relationships in the tracker.
     * @return The list of relationships in the tracker.
     */
    public ArrayList<Relationship> getRelationshipsTracker() {
        return relationshipsTracker;
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
}
