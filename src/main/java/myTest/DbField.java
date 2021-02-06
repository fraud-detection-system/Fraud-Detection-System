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

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "field",
        "fieldMacro",
        "id",
        "isSummary",
        "toBeDisplayed",
        "evaluator"
})
public class DbField implements Serializable {

    /**
     * Specify the DB Field
     *
     */
    @JsonProperty("field")
    private String field;
    /**
     * Macro that defines the strategy which will be used for adding DB Fields in data generation. e.g. scheduleSixTxDate
     *
     */
    @JsonProperty("fieldMacro")
    private String fieldMacro;
    /**
     * Associate an ID with the field for reference
     * (Required)
     *
     */
    @JsonProperty("id")
    private String id;
    /**
     * Specify whether the field is summary field or non-summary field. Summary fields are used as group by for aggregate functions being applied to the SQL being dynamically generated
     *
     */
    @JsonProperty("isSummary")
    private Boolean isSummary;
    /**
     * Specify whether the field is to be shown on column axis. All summary fields will be shown on column axis irrespective of the value of this field
     *
     */
    @JsonProperty("toBeDisplayed")
    private Boolean toBeDisplayed;

    /**
     * Specifies if any processing is required on row fetched from db
     *
     */
    @JsonProperty("evaluator")
    private String evaluator;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * Specify the DB Field
     *
     * @return
     *     The field
     */
    @JsonProperty("field")
    public String getField() {
        return field;
    }

    /**
     * Specify the DB Field
     *
     * @param field
     *     The field
     */
    @JsonProperty("field")
    public void setField(String field) {
        this.field = field;
    }

    /**
     * Macro that defines the strategy which will be used for adding DB Fields in data generation. e.g. scheduleSixTxDate
     *
     * @return
     *     The fieldMacro
     */
    @JsonProperty("fieldMacro")
    public String getFieldMacro() {
        return fieldMacro;
    }

    /**
     * Macro that defines the strategy which will be used for adding DB Fields in data generation. e.g. scheduleSixTxDate
     *
     * @param fieldMacro
     *     The fieldMacro
     */
    @JsonProperty("fieldMacro")
    public void setFieldMacro(String fieldMacro) {
        this.fieldMacro = fieldMacro;
    }

    /**
     * Associate an ID with the field for reference
     * (Required)
     *
     * @return
     *     The id
     */
    @JsonProperty("id")
    public String getId() {
        return id;
    }

    /**
     * Associate an ID with the field for reference
     * (Required)
     *
     * @param id
     *     The id
     */
    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Specify whether the field is summary field or non-summary field. Summary fields are used as group by for aggregate functions being applied to the SQL being dynamically generated
     *
     * @return
     *     The isSummary
     */
    @JsonProperty("isSummary")
    public Boolean getIsSummary() {
        return isSummary;
    }

    /**
     * Specify whether the field is summary field or non-summary field. Summary fields are used as group by for aggregate functions being applied to the SQL being dynamically generated
     *
     * @param isSummary
     *     The isSummary
     */
    @JsonProperty("isSummary")
    public void setIsSummary(Boolean isSummary) {
        this.isSummary = isSummary;
    }

    /**
     * Specify whether the field is to be shown on column axis. All summary fields will be shown on column axis irrespective of the value of this field
     *
     * @return
     *     The toBeDisplayed
     */
    @JsonProperty("toBeDisplayed")
    public Boolean getToBeDisplayed() {
        return toBeDisplayed;
    }

    /**
     * Specify whether the field is to be shown on column axis. All summary fields will be shown on column axis irrespective of the value of this field
     *
     * @param toBeDisplayed
     *     The toBeDisplayed
     */
    @JsonProperty("toBeDisplayed")
    public void setToBeDisplayed(Boolean toBeDisplayed) {
        this.toBeDisplayed = toBeDisplayed;
    }

    public String getEvaluator() {
        return evaluator;
    }

    public void setEvaluator(String evaluator) {
        this.evaluator = evaluator;
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

}