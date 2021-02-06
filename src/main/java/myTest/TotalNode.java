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
 * Schema for Total Node
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "alwaysCollapse",
        "collapsible",
        "children",
        "header",
        "footer",
        "formula",
        "isTotalNode",
        "isGrandTotal",
        "autoNodeDef"
})
public class TotalNode
        extends Node implements Serializable
{
    public TotalNode() {
        this.setNodeType(NodeType.TOTAL_NODE);
    }
    /**
     * Whether to collapse the node always
     *
     */
    @JsonProperty("alwaysCollapse")
    private Boolean alwaysCollapse;
    /**
     * Whether to node is collapsible or not
     *
     */
    @JsonProperty("collapsible")
    private Boolean collapsible;
    /**
     * Collection of nodes inside a node
     *
     */
    @JsonProperty("children")
    private List<Node> children = new ArrayList<Node>();
    /**
     * Header text for the node
     *
     */
    @JsonProperty("header")
    private String header;
    /**
     * Footer text for the node
     *
     */
    @JsonProperty("footer")
    private String footer;
    /**
     * formula to calculate the amount for the node
     *
     */
    @JsonProperty("formula")
    private String formula;
    /**
     * specifies whether it's total node.
     *
     */
    @JsonProperty("isTotalNode")
    private Boolean isTotalNode;
    /**
     * Indicates whether the node is a grand total.
     *
     */
    @JsonProperty("isGrandTotal")
    private Boolean isGrandTotal;
    /**
     * Schema which defines metadata which is needed for automatic generation of nodes
     *
     */
    @JsonProperty("autoNodeDef")
    private AutoNodeDef autoNodeDef;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * Whether to collapse the node always
     *
     * @return
     *     The alwaysCollapse
     */
    @JsonProperty("alwaysCollapse")
    public Boolean getAlwaysCollapse() {
        return alwaysCollapse;
    }

    /**
     * Whether to collapse the node always
     *
     * @param alwaysCollapse
     *     The alwaysCollapse
     */
    @JsonProperty("alwaysCollapse")
    public void setAlwaysCollapse(Boolean alwaysCollapse) {
        this.alwaysCollapse = alwaysCollapse;
    }

    /**
     * Whether to node is collapsible or not
     *
     * @return
     *     The collapsible
     */
    @JsonProperty("collapsible")
    public Boolean getCollapsible() {
        return collapsible;
    }

    /**
     * Whether to node is collapsible or not
     *
     * @param collapsible
     *     The collapsible
     */
    @JsonProperty("collapsible")
    public void setCollapsible(Boolean collapsible) {
        this.collapsible = collapsible;
    }

    /**
     * Collection of nodes inside a node
     *
     * @return
     *     The children
     */
    @JsonProperty("children")
    public List<Node> getChildren() {
        return children;
    }

    /**
     * Collection of nodes inside a node
     *
     * @param children
     *     The children
     */
    @JsonProperty("children")
    public void setChildren(List<Node> children) {
        this.children = children;
    }

    /**
     * Header text for the node
     *
     * @return
     *     The header
     */
    @JsonProperty("header")
    public String getHeader() {
        return header;
    }

    /**
     * Header text for the node
     *
     * @param header
     *     The header
     */
    @JsonProperty("header")
    public void setHeader(String header) {
        this.header = header;
    }

    /**
     * Footer text for the node
     *
     * @return
     *     The footer
     */
    @JsonProperty("footer")
    public String getFooter() {
        return footer;
    }

    /**
     * Footer text for the node
     *
     * @param footer
     *     The footer
     */
    @JsonProperty("footer")
    public void setFooter(String footer) {
        this.footer = footer;
    }

    /**
     * formula to calculate the amount for the node
     *
     * @return
     *     The formula
     */
    @JsonProperty("formula")
    public String getFormula() {
        return formula;
    }

    /**
     * formula to calculate the amount for the node
     *
     * @param formula
     *     The formula
     */
    @JsonProperty("formula")
    public void setFormula(String formula) {
        this.formula = formula;
    }

    /**
     * specifies whether it's total node.
     *
     * @return
     *     The isTotalNode
     */
    @JsonProperty("isTotalNode")
    public Boolean getIsTotalNode() {
        return isTotalNode;
    }

    /**
     * specifies whether it's total node.
     *
     * @param isTotalNode
     *     The isTotalNode
     */
    @JsonProperty("isTotalNode")
    public void setIsTotalNode(Boolean isTotalNode) {
        this.isTotalNode = isTotalNode;
    }

    /**
     * Indicates whether the node is a grand total.
     *
     * @return
     *     The isGrandTotal
     */
    @JsonProperty("isGrandTotal")
    public Boolean getIsGrandTotal() {
        return isGrandTotal;
    }

    /**
     * Indicates whether the node is a grand total.
     *
     * @param isGrandTotal
     *     The isGrandTotal
     */
    @JsonProperty("isGrandTotal")
    public void setIsGrandTotal(Boolean isGrandTotal) {
        this.isGrandTotal = isGrandTotal;
    }

    /**
     * Schema which defines metadata which is needed for automatic generation of nodes
     *
     * @return
     *     The autoNodeDef
     */
    @JsonProperty("autoNodeDef")
    public AutoNodeDef getAutoNodeDef() {
        return autoNodeDef;
    }

    /**
     * Schema which defines metadata which is needed for automatic generation of nodes
     *
     * @param autoNodeDef
     *     The autoNodeDef
     */
    @JsonProperty("autoNodeDef")
    public void setAutoNodeDef(AutoNodeDef autoNodeDef) {
        this.autoNodeDef = autoNodeDef;
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