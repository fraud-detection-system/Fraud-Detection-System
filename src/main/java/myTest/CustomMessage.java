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
        "messageText",
        "position"
})
public class CustomMessage implements Serializable {

    /**
     * Message to be shown
     * (Required)
     *
     */
    @JsonProperty("messageText")
    private String messageText;
    /**
     * Position of the message
     *
     */
    @JsonProperty("position")
    private CustomMessage.Position position;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public CustomMessage() {
    }

    /**
     * Message to be shown
     * (Required)
     *
     * @return
     *     The messageText
     */
    @JsonProperty("messageText")
    public String getMessageText() {
        return messageText;
    }

    /**
     * Message to be shown
     * (Required)
     *
     * @param messageText
     *     The messageText
     */
    @JsonProperty("messageText")
    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    /**
     * Position of the message
     *
     * @return
     *     The position
     */
    @JsonProperty("position")
    public CustomMessage.Position getPosition() {
        return position;
    }

    /**
     * Position of the message
     *
     * @param position
     *     The position
     */
    @JsonProperty("position")
    public void setPosition(CustomMessage.Position position) {
        this.position = position;
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
    public static enum Position {

        HEADER("HEADER"),
        FOOTER("FOOTER");
        private final String value;
        private static Map<String, CustomMessage.Position> constants = new HashMap<String, CustomMessage.Position>();

        static {
            for (CustomMessage.Position c: values()) {
                constants.put(c.value, c);
            }
        }

        private Position(String value) {
            this.value = value;
        }

        @JsonValue
        @Override
        public String toString() {
            return this.value;
        }

        @JsonCreator
        public static CustomMessage.Position fromValue(String value) {
            CustomMessage.Position constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}