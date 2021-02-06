package myTest;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonValue;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.annotation.Generated;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


/**
 * Nodes in a report, typically axes composed of nodes. Nodes can be of Total or Detail Type
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "name",
        "nodeType",
        "amountSignFlip",
        "qzURLOverride",
        "qzURLOverrideCriteria",
        "qzDisabled",
        "tBreak",
        "donotTotal",
        "visibilityMacro"
})
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "nodeType")
@JsonSubTypes({ @Type(value = TotalNode.class, name = "TotalNode"),
        @Type(value = DetailNode.class, name = "DetailNode") })
public class Node implements Serializable {

    /**
     * Name of the node.
     * (Required)
     *
     */
    @JsonProperty("name")
    private String name;
    /**
     * Type of the node i.e. total node, detail node
     * (Required)
     *
     */
    @JsonProperty("nodeType")
    private Node.NodeType nodeType;
    /**
     * Flip for amount sign
     *
     */
    @JsonProperty("amountSignFlip")
    private Boolean amountSignFlip;
    /**
     * To override the Quick Zoom URL
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
     */
    @JsonProperty("qzDisabled")
    private Boolean qzDisabled;
    /**
     * This specifies where to introduce the T-Break for T-Structured reports
     *
     */
    @JsonProperty("tBreak")
    private Boolean tBreak;

    /**
     * This macro controls whether or not the node should be added to the axis
     */
    @JsonProperty("tBreak")
    private String visibilityMacro;


    public Boolean getDonotTotal() {
        if(donotTotal==null){
            return false;
        }
        return donotTotal;
    }

    public void setDonotTotal(Boolean donotTotal) {
        this.donotTotal = donotTotal;
    }

    @JsonProperty("donotTotal")
    private Boolean donotTotal;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * Name of the node.
     * (Required)
     *
     * @return
     *     The name
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     * Name of the node.
     * (Required)
     *
     * @param name
     *     The name
     */
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Type of the node i.e. total node, detail node
     * (Required)
     *
     * @return
     *     The nodeType
     */
    @JsonProperty("nodeType")
    public Node.NodeType getNodeType() {
        return nodeType;
    }

    /**
     * Type of the node i.e. total node, detail node
     * (Required)
     *
     * @param nodeType
     *     The nodeType
     */
    @JsonProperty("nodeType")
    public void setNodeType(Node.NodeType nodeType) {
        this.nodeType = nodeType;
    }

    /**
     * Flip for amount sign
     *
     * @return
     *     The amountSignFlip
     */
    @JsonProperty("amountSignFlip")
    public Boolean getAmountSignFlip() {
        return amountSignFlip;
    }

    /**
     * Flip for amount sign
     *
     * @param amountSignFlip
     *     The amountSignFlip
     */
    @JsonProperty("amountSignFlip")
    public void setAmountSignFlip(Boolean amountSignFlip) {
        this.amountSignFlip = amountSignFlip;
    }

    /**
     * To override the Quick Zoom URL
     *
     * @return
     *     The qzURLOverride
     */
    @JsonProperty("qzURLOverride")
    public String getQzURLOverride() {
        return qzURLOverride;
    }

    /**
     * To override the Quick Zoom URL
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
     * This specifies where to introduce the T-Break for T-Structured reports
     *
     * @return
     *     The tBreak
     */
    @JsonProperty("tBreak")
    public Boolean getTBreak() {
        return tBreak;
    }

    /**
     * This specifies where to introduce the T-Break for T-Structured reports
     *
     * @param tBreak
     *     The tBreak
     */
    @JsonProperty("tBreak")
    public void setTBreak(Boolean tBreak) {
        this.tBreak = tBreak;
    }

    @JsonProperty("visibilityMacro")
    public String getVisibilityMacro() {
        return visibilityMacro;
    }

    @JsonProperty("visibilityMacro")
    public void setVisibilityMacro(String visibilityMacro) {
        this.visibilityMacro = visibilityMacro;
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
    public static enum NodeType {

        TOTAL_NODE("TotalNode"),
        DETAIL_NODE("DetailNode");
        private final String value;
        private static Map<String, Node.NodeType> constants = new HashMap<String, Node.NodeType>();

        static {
            for (Node.NodeType c: values()) {
                constants.put(c.value, c);
            }
        }

        private NodeType(String value) {
            this.value = value;
        }

        @JsonValue
        @Override
        public String toString() {
            return this.value;
        }

        @JsonCreator
        public static Node.NodeType fromValue(String value) {
            Node.NodeType constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}