package myTest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.annotation.Generated;
import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "qzDisabled",
        "qzURLOverride",
        "qzURLOverrideCriteria"
})
public class QzInfo implements Serializable {

    /**
     * Sets Quick Zoom info i.e. QuickZoom enabled or not
     *
     */
    @JsonProperty("qzDisabled")
    private Boolean qzDisabled;
    /**
     * Override the Quick Zoom URL for all the auto generated node.
     *
     */
    @JsonProperty("qzURLOverride")
    private String qzURLOverride;

    /**
     * To enable conditional override of the Quick Zoom URL
     *
     */
    @JsonProperty("qzURLOverrideCriteria")
    private String qzURLOverrideCriteria;

    /**
     * Sets Quick Zoom info i.e. QuickZoom enabled or not
     *
     * @return
     *     The qzDisabled
     */
    @JsonProperty("qzDisabled")
    public Boolean getQzDisabled() {
        return qzDisabled;
    }

    /**
     * Sets Quick Zoom info i.e. QuickZoom enabled or not
     *
     * @param qzDisabled
     *     The qzDisabled
     */
    @JsonProperty("qzDisabled")
    public void setQzDisabled(Boolean qzDisabled) {
        this.qzDisabled = qzDisabled;
    }

    /**
     * Override the Quick Zoom URL for all the auto generated node.
     *
     * @return
     *     The qzURLOverride
     */
    @JsonProperty("qzURLOverride")
    public String getQzURLOverride() {
        return qzURLOverride;
    }

    /**
     * Override the Quick Zoom URL for all the auto generated node.
     *
     * @param qzURLOverride
     *     The qzURLOverride
     */
    @JsonProperty("qzURLOverride")
    public void setQzURLOverride(String qzURLOverride) {
        this.qzURLOverride = qzURLOverride;
    }

    public String getQzURLOverrideCriteria() {
        return qzURLOverrideCriteria;
    }

    public void setQzURLOverrideCriteria(String qzURLOverrideCriteria) {
        this.qzURLOverrideCriteria = qzURLOverrideCriteria;
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

}