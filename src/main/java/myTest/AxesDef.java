
package myTest;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.annotation.Generated;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


/**
 * Defines the Axes for the Report i.e. Row, Column, Sub Axis
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "rowAxis",
        "colAxis",
        "subAxis"
})
public class AxesDef implements Serializable {

    /**
     *
     */
    @JsonProperty("rowAxis")
    private Axis rowAxis;
    /**
     *
     */
    @JsonProperty("colAxis")
    private Axis colAxis;
    /**
     *
     */
    @JsonProperty("subAxis")
    private Axis subAxis;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     *     The rowAxis
     */
    @JsonProperty("rowAxis")
    public Axis getRowAxis() {
        return rowAxis;
    }

    /**
     *
     * @param rowAxis
     *     The rowAxis
     */
    @JsonProperty("rowAxis")
    public void setRowAxis(Axis rowAxis) {
        this.rowAxis = rowAxis;
    }

    /**
     *
     * @return
     *     The colAxis
     */
    @JsonProperty("colAxis")
    public Axis getColAxis() {
        return colAxis;
    }

    /**
     *
     * @param colAxis
     *     The colAxis
     */
    @JsonProperty("colAxis")
    public void setColAxis(Axis colAxis) {
        this.colAxis = colAxis;
    }

    /**
     *
     * @return
     *     The subAxis
     */
    @JsonProperty("subAxis")
    public Axis getSubAxis() {
        return subAxis;
    }

    /**
     *
     * @param subAxis
     *     The subAxis
     */
    @JsonProperty("subAxis")
    public void setSubAxis(Axis subAxis) {
        this.subAxis = subAxis;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object other) {
        return EqualsBuilder.reflectionEquals(this, other);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}