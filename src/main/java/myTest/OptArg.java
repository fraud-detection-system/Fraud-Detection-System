package myTest;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonValue;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.annotation.Generated;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "valueDataType",
        "value"
})
public class OptArg implements Serializable {

    /**
     *
     * (Required)
     *
     */
    @JsonProperty("valueDataType")
    private OptArg.ValueDataType valueDataType;
    /**
     *
     * (Required)
     *
     */
    @JsonProperty("value")
    private String value;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * (Required)
     *
     * @return
     *     The valueDataType
     */
    @JsonProperty("valueDataType")
    public OptArg.ValueDataType getValueDataType() {
        return valueDataType;
    }

    /**
     *
     * (Required)
     *
     * @param valueDataType
     *     The valueDataType
     */
    @JsonProperty("valueDataType")
    public void setValueDataType(OptArg.ValueDataType valueDataType) {
        this.valueDataType = valueDataType;
    }

    /**
     *
     * (Required)
     *
     * @return
     *     The value
     */
    @JsonProperty("value")
    public String getValue() {
        return value;
    }

    /**
     *
     * (Required)
     *
     * @param value
     *     The value
     */
    @JsonProperty("value")
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object other) {
        return EqualsBuilder.reflectionEquals(this, other);
    }

    @Generated("org.jsonschema2pojo")
    public static enum ValueDataType {

        BOOLEAN("boolean"),
        STRING("string"),
        INTEGER("integer"),
        DATE_MACRO("date_macro"),
        DATE_MACRO_OPTIONS("date_macro_options"),
        DATE("date");
        private final String value;
        private static Map<String, OptArg.ValueDataType> constants = new HashMap<String, OptArg.ValueDataType>();

        static {
            for (OptArg.ValueDataType c: values()) {
                constants.put(c.value, c);
            }
        }

        private ValueDataType(String value) {
            this.value = value;
        }

        @JsonValue
        @Override
        public String toString() {
            return this.value;
        }

        @JsonCreator
        public static OptArg.ValueDataType fromValue(String value) {
            OptArg.ValueDataType constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}