
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
 * Generated Auto nodes satisfying this criteria will have corresponding qz Info set
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "criteria",
        "qzInfo"
})
public class QzInfoCriteria implements Serializable {

    /**
     * Collection of criterias to be satisfied
     *
     */
    @JsonProperty("criteria")
    private List<Criteria> criteria = new ArrayList<Criteria>();
    @JsonProperty("qzInfo")
    private QzInfo qzInfo;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * Collection of criterias to be satisfied
     *
     * @return
     *     The criteria
     */
    @JsonProperty("criteria")
    public List<Criteria> getCriteria() {
        return criteria;
    }

    /**
     * Collection of criterias to be satisfied
     *
     * @param criteria
     *     The criteria
     */
    @JsonProperty("criteria")
    public void setCriteria(List<Criteria> criteria) {
        this.criteria = criteria;
    }

    /**
     *
     * @return
     *     The qzInfo
     */
    @JsonProperty("qzInfo")
    public QzInfo getQzInfo() {
        return qzInfo;
    }

    /**
     *
     * @param qzInfo
     *     The qzInfo
     */
    @JsonProperty("qzInfo")
    public void setQzInfo(QzInfo qzInfo) {
        this.qzInfo = qzInfo;
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