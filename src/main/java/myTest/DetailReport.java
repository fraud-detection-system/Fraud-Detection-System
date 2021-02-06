
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Schema for Detail Reports
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "sections",
        "columns",
        "dbRowProcessor",
        "dbRowKey",
        "context"
})
public class DetailReport
        extends Report implements Serializable
{

    public DetailReport(){
        this.setType(Type.DETAIL);
    }

    /**
     * Report Sections for Detail Reports, i.e. Detail Section, Group Section, Total Section etc.
     *
     */
    @JsonProperty("sections")
    private List<Section> sections = new ArrayList<Section>();
    /**
     * Report columns for Detail Reports
     *
     */
    @JsonProperty("columns")
    private List<CatalogColumn> columns = new ArrayList<CatalogColumn>();

    @JsonProperty("dbRowProcessor")
    private String dbRowProcessor;

    @JsonProperty("dbRowKey")
    private String dbRowKey;

    public List<CatalogColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<CatalogColumn> columns) {
        this.columns = columns;
    }

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * Report Sections for Detail Reports, i.e. Detail Section, Group Section, Total Section etc.
     *
     * @return
     *     The sections
     */
    @JsonProperty("sections")
    public List<Section> getSections() {
        return sections;
    }

    /**
     * Report Sections for Detail Reports, i.e. Detail Section, Group Section, Total Section etc.
     *
     * @param sections
     *     The sections
     */
    @JsonProperty("sections")
    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    public String getDbRowProcessor() {
        return dbRowProcessor;
    }

    public void setDbRowProcessor(String dbRowProcessor) {
        this.dbRowProcessor = dbRowProcessor;
    }

    public String getDbRowKey() {
        return dbRowKey;
    }

    public void setDbRowKey(String dbRowKey) {
        this.dbRowKey = dbRowKey;
    }

    @JsonProperty("context")
    private Map<String, Evaluator> context;

    public Map<String, Evaluator> getContext() {
        return context;
    }

    public void setContext(Map<String, Evaluator> context) {
        this.context = context;
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