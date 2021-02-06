package myTest;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonValue;

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
 * Schema for a Detail Node
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "id",
        "criteria",
        "multipleNodes",
        "hiddenWhen",
        "isFallbackNode",
        "dbFieldRef",
        "isIllustrationNode",
        "doesNotBelongToAnyAccount"
})
public class DetailNode
        extends Node implements Serializable
{
    public DetailNode() {
        this.setNodeType(NodeType.DETAIL_NODE);
    }
    /**
     * The id of the detail node
     * (Required)
     *
     */
    @JsonProperty("id")
    private String id;
    /**
     * Collection of criterias to be satisfied
     *
     */
    @JsonProperty("criteria")
    private List<Criteria> criteria = new ArrayList<Criteria>();
    /**
     * Whether the to show leaf level accounts
     *
     */
    @JsonProperty("multipleNodes")
    private Boolean multipleNodes;
    /**
     * Node visibility based on certain condition
     *
     */
    @JsonProperty("hiddenWhen")
    private DetailNode.HiddenWhen hiddenWhen;
    /**
     * Whether this one is a fallback kind of node
     *
     */
    @JsonProperty("isFallbackNode")
    private Boolean isFallbackNode;
    /**
     * Referenec id of the dbField which is to be shown on column axis in this node.
     *
     */
    @JsonProperty("dbFieldRef")
    private String dbFieldRef;
    /**
     * Whether the node is being used only for illustration purpose.
     *
     */
    @JsonProperty("isIllustrationNode")
    private Boolean isIllustrationNode;

    @JsonProperty("doesNotBelongToAnyAccount")
    private Boolean doesNotBelongToAnyAccount;
    /**
     * The id of the detail node
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
     * The id of the detail node
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
     * Whether the to show leaf level accounts
     *
     * @return
     *     The multipleNodes
     */
    @JsonProperty("multipleNodes")
    public Boolean getMultipleNodes() {
        return multipleNodes;
    }

    /**
     * Whether the to show leaf level accounts
     *
     * @param multipleNodes
     *     The multipleNodes
     */
    @JsonProperty("multipleNodes")
    public void setMultipleNodes(Boolean multipleNodes) {
        this.multipleNodes = multipleNodes;
    }

    /**
     * Node visibility based on certain condition
     *
     * @return
     *     The hiddenWhen
     */
    @JsonProperty("hiddenWhen")
    public DetailNode.HiddenWhen getHiddenWhen() {
        return hiddenWhen;
    }

    /**
     * Node visibility based on certain condition
     *
     * @param hiddenWhen
     *     The hiddenWhen
     */
    @JsonProperty("hiddenWhen")
    public void setHiddenWhen(DetailNode.HiddenWhen hiddenWhen) {
        this.hiddenWhen = hiddenWhen;
    }

    /**
     * Whether this one is a fallback kind of node
     *
     * @return
     *     The isFallbackNode
     */
    @JsonProperty("isFallbackNode")
    public Boolean getIsFallbackNode() {
        return isFallbackNode;
    }

    /**
     * Whether this one is a fallback kind of node
     *
     * @param isFallbackNode
     *     The isFallbackNode
     */
    @JsonProperty("isFallbackNode")
    public void setIsFallbackNode(Boolean isFallbackNode) {
        this.isFallbackNode = isFallbackNode;
    }

    /**
     * Referenec id of the dbField which is to be shown on column axis in this node.
     *
     * @return
     *     The dbFieldRef
     */
    @JsonProperty("dbFieldRef")
    public String getDbFieldRef() {
        return dbFieldRef;
    }

    /**
     * Referenec id of the dbField which is to be shown on column axis in this node.
     *
     * @param dbFieldRef
     *     The dbFieldRef
     */
    @JsonProperty("dbFieldRef")
    public void setDbFieldRef(String dbFieldRef) {
        this.dbFieldRef = dbFieldRef;
    }

    /**
     * Whether the node is being used only for illustration purpose.
     *
     * @return
     *     The isIllustrationNode
     */
    @JsonProperty("isIllustrationNode")
    public Boolean getIsIllustrationNode() {
        return isIllustrationNode;
    }

    /**
     * Whether the node is being used only for illustration purpose.
     *
     * @param isIllustrationNode
     *     The isIllustrationNode
     */
    @JsonProperty("isIllustrationNode")
    public void setIsIllustrationNode(Boolean isIllustrationNode) {
        this.isIllustrationNode = isIllustrationNode;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @JsonProperty("doesNotBelongToAnyAccount")
    public Boolean getDoesNotBelongToAnyAccount() {
        return doesNotBelongToAnyAccount;
    }

    @JsonProperty("doesNotBelongToAnyAccount")
    public void setDoesNotBelongToAnyAccount(Boolean doesNotBelongToAnyAccount) {
        this.doesNotBelongToAnyAccount = doesNotBelongToAnyAccount;
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
    public static enum HiddenWhen {

        BALANCE_IS_NEGATIVE("BALANCE_IS_NEGATIVE"),
        BALANCE_IS_ZERO("BALANCE_IS_ZERO"),
        BALANCE_IS_NEGATIVE_OR_ZERO("BALANCE_IS_NEGATIVE_OR_ZERO");
        private final String value;
        private static Map<String, DetailNode.HiddenWhen> constants = new HashMap<String, DetailNode.HiddenWhen>();

        static {
            for (DetailNode.HiddenWhen c: values()) {
                constants.put(c.value, c);
            }
        }

        private HiddenWhen(String value) {
            this.value = value;
        }

        @JsonValue
        @Override
        public String toString() {
            return this.value;
        }

        @JsonCreator
        public static DetailNode.HiddenWhen fromValue(String value) {
            DetailNode.HiddenWhen constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}