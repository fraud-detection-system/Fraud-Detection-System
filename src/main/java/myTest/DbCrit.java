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
        "key",
        "valueDataType",
        "value"
})
public class DbCrit implements Serializable {

    /**
     * Specify the key(field) on which value will be checked. Use miscsql for general check.
     * (Required)
     *
     */
    @JsonProperty("key")
    private String key;
    /**
     * Data type of the value.
     * (Required)
     *
     */
    @JsonProperty("valueDataType")
    private DbCrit.ValueDataType valueDataType;
    /**
     * Value for the key to be checked. i.e. false for nopost or (SalesDetailType is null) for miscsql.
     * (Required)
     *
     */
    @JsonProperty("value")
    private String value;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();


    @JsonProperty("customCritModifier")
    private String customCritModifier;

    @JsonProperty("customCritModifier")
    public String getCustomCritModifier() {
        return customCritModifier;
    }

    @JsonProperty("customCritModifier")
    @JsonIgnore
    public void setCustomCritModifier(String customCritModifier) {
        this.customCritModifier = customCritModifier;
    }

    /**
     * Specify the key(field) on which value will be checked. Use miscsql for general check.
     * (Required)
     *
     * @return
     *     The key
     */
    @JsonProperty("key")
    public String getKey() {
        return key;
    }

    /**
     * Specify the key(field) on which value will be checked. Use miscsql for general check.
     * (Required)
     *
     * @param key
     *     The key
     */
    @JsonProperty("key")
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Data type of the value.
     * (Required)
     *
     * @return
     *     The valueDataType
     */
    @JsonProperty("valueDataType")
    public DbCrit.ValueDataType getValueDataType() {
        return valueDataType;
    }

    /**
     * Data type of the value.
     * (Required)
     *
     * @param valueDataType
     *     The valueDataType
     */
    @JsonProperty("valueDataType")
    public void setValueDataType(DbCrit.ValueDataType valueDataType) {
        this.valueDataType = valueDataType;
    }

    /**
     * Value for the key to be checked. i.e. false for nopost or (SalesDetailType is null) for miscsql.
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
     * Value for the key to be checked. i.e. false for nopost or (SalesDetailType is null) for miscsql.
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
        TRISTATE("tristate");
        private final String value;
        private static Map<String, DbCrit.ValueDataType> constants = new HashMap<String, DbCrit.ValueDataType>();

        static {
            for (DbCrit.ValueDataType c: values()) {
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
        public static DbCrit.ValueDataType fromValue(String value) {
            DbCrit.ValueDataType constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}