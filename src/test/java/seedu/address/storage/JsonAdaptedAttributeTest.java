package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.attribute.Attribute;

public class JsonAdaptedAttributeTest {

    private static final String VALID_TYPE = "name";
    private static final String VALID_VALUE = "John Doe";

    @Test
    public void toModelType_validAttribute_returnsAttribute() throws IllegalValueException {
        // Create JsonAdaptedAttribute with valid type and value
        JsonAdaptedAttribute jsonAdaptedAttribute = new JsonAdaptedAttribute(VALID_TYPE, VALID_VALUE);

        // Convert to model type and check if conversion was successful
        Attribute attribute = jsonAdaptedAttribute.toModelType();
        assertEquals(VALID_TYPE, attribute.getName());
        assertEquals(VALID_VALUE, attribute.getValueAsString());
    }

    @Test
    public void toModelType_nullType_throwsIllegalValueException() {
        // Create JsonAdaptedAttribute with null type
        JsonAdaptedAttribute jsonAdaptedAttribute = new JsonAdaptedAttribute(null, VALID_VALUE);

        // Verify that IllegalValueException is thrown when converting to model type
        assertThrows(IllegalValueException.class, jsonAdaptedAttribute::toModelType);
    }

    @Test
    public void toModelType_nullValue_throwsIllegalValueException() {
        // Create JsonAdaptedAttribute with null value
        JsonAdaptedAttribute jsonAdaptedAttribute = new JsonAdaptedAttribute(VALID_TYPE, null);

        // Verify that IllegalValueException is thrown when converting to model type
        assertThrows(IllegalValueException.class, jsonAdaptedAttribute::toModelType);
    }

}
