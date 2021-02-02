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
 * Schema for Summary Reports
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "axesDef",
        "preCollapseFixUp",
        "processSummaryEntries",
        "postProcessEvaluator",
        "summarize"
})
public class SummaryReport
        extends Report implements Serializable
{

    public SummaryReport() {
        this.setType(Type.SUMMARY);
    }
    /**
     * Defines the Axes for the Report i.e. Row, Column, Sub Axis
     *
     */
    @JsonProperty("axesDef")
    private AxesDef axesDef;
    @JsonProperty("preCollapseFixUp")
    private String preCollapseFixUp;
    @JsonProperty("summarize")
    private Boolean summarize;

    public String getPreCollapseFixUp() {
        return preCollapseFixUp;
    }

    public void setPreCollapseFixUp(String preCollapseFixUp) {
        this.preCollapseFixUp = preCollapseFixUp;
    }

    @JsonProperty("processSummaryEntries")
    private String processSummaryEntries;

    public String getProcessSummaryEntries() {
        return processSummaryEntries;
    }

    public void setProcessSummaryEntries(String processSummaryEntries) {
        this.processSummaryEntries = processSummaryEntries;
    }
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();


    /**
     * Defines the Axes for the Report i.e. Row, Column, Sub Axis
     *
     * @return
     *     The axesDef
     */
    @JsonProperty("axesDef")
    public AxesDef getAxesDef() {
        return axesDef;
    }

    public Boolean getSummarize() {
        if (summarize == null) {
            return true;
        }
        return summarize;
    }

    public void setSummarize(Boolean summarize) {
        this.summarize = summarize;
    }

    /**
     * Defines the Axes for the Report i.e. Row, Column, Sub Axis
     *
     * @param axesDef
     *     The axesDef
     */
    @JsonProperty("axesDef")
    public void setAxesDef(AxesDef axesDef) {
        this.axesDef = axesDef;
    }

    /*
     * Defines the report postProcessEvaluator
     */
    @JsonProperty("postProcessEvaluator")
    private String postProcessEvaluator;

    public String getPostProcessEvaluator() {
        return postProcessEvaluator;
    }

    public void setPostProcessEvaluator(String postProcessEvaluator) {
        this.postProcessEvaluator = postProcessEvaluator;
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