package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.attribute.Attribute;
import seedu.address.model.person.attribute.NameAttribute;
import seedu.address.model.person.attribute.StringAttribute;

/**
 * Jackson-friendly version of {@link Attribute}.
 */
class JsonAdaptedAttribute {

    private final String type;
    private final String value;

    /**
     * Constructs a {@code JsonAdaptedAttribute} with the given attribute details.
     */
    @JsonCreator
    public JsonAdaptedAttribute(@JsonProperty("type") String type, @JsonProperty("value") String value) {
        this.type = type;
        this.value = value;
    }

    /**
     * Converts a given {@code Attribute} into this class for Jackson use.
     */
    public JsonAdaptedAttribute(Attribute source) {
        this.type = source.getName();
        this.value = source.getValueAsString();
    }

    /**
     * Converts this Jackson-friendly adapted attribute object into the model's {@code Attribute} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted attribute.
     */
    public Attribute toModelType() throws IllegalValueException {
        if (type == null || value == null || type.isEmpty() || value.isEmpty()) {
            throw new IllegalValueException("Invalid attribute type or value in JSON.");
        }
        if (type.equals("Name") || type.equals("name")) {
            return new NameAttribute("name", value);
        } else {
            return new StringAttribute(type, value);
        }
    }
}
