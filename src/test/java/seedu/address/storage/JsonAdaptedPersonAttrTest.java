package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Person;

public class JsonAdaptedPersonAttrTest {

    private static final String VALID_ATTRIBUTE_NAME = "Age";
    private static final String VALID_ATTRIBUTE_VALUE = "30";
    private static final JsonAdaptedAttribute VALID_ATTRIBUTE =
            new JsonAdaptedAttribute(VALID_ATTRIBUTE_NAME, VALID_ATTRIBUTE_VALUE);
    private static final List<JsonAdaptedAttribute> VALID_ATTRIBUTES = new ArrayList<>();
    private static final String VALID_TAG_NAME = "friend";
    private static final JsonAdaptedTag VALID_TAG = new JsonAdaptedTag(VALID_TAG_NAME);
    private static final List<JsonAdaptedTag> VALID_TAGS = new ArrayList<>();

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        // Create a list of valid attributes
        VALID_ATTRIBUTES.add(VALID_ATTRIBUTE);

        // Create a list of valid tags
        VALID_TAGS.add(VALID_TAG);

        // Create JsonAdaptedPersonAttr with valid attributes and tags
        JsonAdaptedPersonAttr jsonAdaptedPersonAttr = new JsonAdaptedPersonAttr(VALID_ATTRIBUTES, VALID_TAGS);

        // Convert to model type and check if conversion was successful
        Person person = jsonAdaptedPersonAttr.toModelType();
        assertEquals(1, person.getAttributes().size());
    }

    @Test
    public void toModelType_nullAttributes_throwsIllegalValueException() {
        // Create JsonAdaptedPersonAttr with null attributes
        JsonAdaptedPersonAttr jsonAdaptedPersonAttr = new JsonAdaptedPersonAttr(null, VALID_TAGS);

        // Verify that IllegalValueException is thrown when converting to model type
        assertThrows(IllegalValueException.class, jsonAdaptedPersonAttr::toModelType);
    }

    @Test
    public void toModelType_emptyAttributes_throwsIllegalValueException() {
        // Create JsonAdaptedPersonAttr with empty attributes list
        JsonAdaptedPersonAttr jsonAdaptedPersonAttr = new JsonAdaptedPersonAttr(new ArrayList<>(), VALID_TAGS);

        // Verify that IllegalValueException is thrown when converting to model type
        assertThrows(IllegalValueException.class, jsonAdaptedPersonAttr::toModelType);
    }

}
