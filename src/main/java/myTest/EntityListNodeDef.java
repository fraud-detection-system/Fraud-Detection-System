package myTest;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sjoshi10 on 4/4/17.
 */
public class EntityListNodeDef implements Serializable {


    @JsonProperty("entity")
    private String entity;

    @JsonProperty("label")
    private String label;

    @JsonProperty("parenEntityRelatedBy")
    private String parenEntityRelatedBy;

    @JsonProperty("aggregatorKey")
    private String aggregatorKey;


    @JsonProperty("entityListNodeDef")
    public EntityListNodeDef entityListNodeDef;




    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getParenEntityRelatedBy() {
        return parenEntityRelatedBy;
    }

    public void setParenEntityRelatedBy(String parenEntityRelatedBy) {
        this.parenEntityRelatedBy = parenEntityRelatedBy;
    }

    public String getAggregatorKey() {
        return aggregatorKey;
    }

    public void setAggregatorKey(String aggregatorKey) {
        this.aggregatorKey = aggregatorKey;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public EntityListNodeDef getEntityListNodeDef() {
        return entityListNodeDef;
    }

    public void setEntityListNodeDef(EntityListNodeDef entityListNodeDef) {
        this.entityListNodeDef = entityListNodeDef;
    }



    /* legacy properties from AutoNodeDef */

    /**
     * expression to be used to find the value in cached list and used for node label. Used if sourceType is cachedList
     *
     */
    @JsonProperty("labelExpression")
    private String labelExpression;
    /**
     * Referenece of the DB field to be used to generate the label for the nodes. Used if sourceType is resultSet
     *
     */
    @JsonProperty("labelDbFieldRef")
    private String labelDbFieldRef;
    /**
     * References of the DB field to be used to decide which db fields will be shown on the node
     *
     */
    @JsonProperty("dbFieldsRef")
    private List<String> dbFieldsRef = new ArrayList<String>();
    /**
     * Show fall back Nodes in the report. Default is true
     *
     */
    @JsonProperty("showFallbackNode")
    private Boolean showFallbackNode;
    /**
     * Title of the fall back node
     *
     */
    @JsonProperty("fallBackNodeTitle")
    private String fallBackNodeTitle;
    /**
     * Flip for amount sign
     *
     */
    @JsonProperty("amountSignFlip")
    private Boolean amountSignFlip;
    /**
     * Schema for Total Node
     *
     */
    @JsonProperty("totalNode")
    private TotalNode totalNode;
    /**
     * Default is false. This should be set to true when rowAttributes need grouping of multiple entities as part of same node. e.g. customer sales by address may include more then one customer in one address node
     *
     */
    @JsonProperty("sameLabelToBeGrouped")
    private Boolean sameLabelToBeGrouped;
    /**
     * Decides whether to show grand total node or not.
     *
     */
    @JsonProperty("showTotalNode")
    private Boolean showTotalNode;
    @JsonProperty("qzInfo")
    private QzInfo qzInfo;
    /**
     * Collection of pairs of criteria and qzInfo. QZ Info for auto generated nodes will be overriden based on criterias
     *
     */
    @JsonProperty("qzInfoCriterias")
    private List<QzInfoCriteria> qzInfoCriterias = new ArrayList<QzInfoCriteria>();
    /**
     * Collection of criterias to be satisfied
     *
     */
    @JsonProperty("criteria")
    private List<Criteria> criteria = new ArrayList<Criteria>();


    /**
     * expression to be used to find the value in cached list and used for node label. Used if sourceType is cachedList
     *
     * @return
     *     The labelExpression
     */
    @JsonProperty("labelExpression")
    public String getLabelExpression() {
        return labelExpression;
    }

    /**
     * expression to be used to find the value in cached list and used for node label. Used if sourceType is cachedList
     *
     * @param labelExpression
     *     The labelExpression
     */
    @JsonProperty("labelExpression")
    public void setLabelExpression(String labelExpression) {
        this.labelExpression = labelExpression;
    }

    /**
     * Referenece of the DB field to be used to generate the label for the nodes. Used if sourceType is resultSet
     *
     * @return
     *     The labelDbFieldRef
     */
    @JsonProperty("labelDbFieldRef")
    public String getLabelDbFieldRef() {
        return labelDbFieldRef;
    }

    /**
     * Referenece of the DB field to be used to generate the label for the nodes. Used if sourceType is resultSet
     *
     * @param labelDbFieldRef
     *     The labelDbFieldRef
     */
    @JsonProperty("labelDbFieldRef")
    public void setLabelDbFieldRef(String labelDbFieldRef) {
        this.labelDbFieldRef = labelDbFieldRef;
    }

    /**
     * References of the DB field to be used to decide which db fields will be shown on the node
     *
     * @return
     *     The dbFieldsRef
     */
    @JsonProperty("dbFieldsRef")
    public List<String> getDbFieldsRef() {
        return dbFieldsRef;
    }

    /**
     * References of the DB field to be used to decide which db fields will be shown on the node
     *
     * @param dbFieldsRef
     *     The dbFieldsRef
     */
    @JsonProperty("dbFieldsRef")
    public void setDbFieldsRef(List<String> dbFieldsRef) {
        this.dbFieldsRef = dbFieldsRef;
    }

    /**
     * Show fall back Nodes in the report. Default is true
     *
     * @return
     *     The showFallbackNode
     */
    @JsonProperty("showFallbackNode")
    public Boolean getShowFallbackNode() {
        return showFallbackNode;
    }

    /**
     * Show fall back Nodes in the report. Default is true
     *
     * @param showFallbackNode
     *     The showFallbackNode
     */
    @JsonProperty("showFallbackNode")
    public void setShowFallbackNode(Boolean showFallbackNode) {
        this.showFallbackNode = showFallbackNode;
    }

    /**
     * Title of the fall back node
     *
     * @return
     *     The fallBackNodeTitle
     */
    @JsonProperty("fallBackNodeTitle")
    public String getFallBackNodeTitle() {
        return fallBackNodeTitle;
    }

    /**
     * Title of the fall back node
     *
     * @param fallBackNodeTitle
     *     The fallBackNodeTitle
     */
    @JsonProperty("fallBackNodeTitle")
    public void setFallBackNodeTitle(String fallBackNodeTitle) {
        this.fallBackNodeTitle = fallBackNodeTitle;
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
     * Schema for Total Node
     *
     * @return
     *     The totalNode
     */
    @JsonProperty("totalNode")
    public TotalNode getTotalNode() {
        return totalNode;
    }

    /**
     * Schema for Total Node
     *
     * @param totalNode
     *     The totalNode
     */
    @JsonProperty("totalNode")
    public void setTotalNode(TotalNode totalNode) {
        this.totalNode = totalNode;
    }

    /**
     * Default is false. This should be set to true when rowAttributes need grouping of multiple entities as part of same node. e.g. customer sales by address may include more then one customer in one address node
     *
     * @return
     *     The sameLabelToBeGrouped
     */
    @JsonProperty("sameLabelToBeGrouped")
    public Boolean getSameLabelToBeGrouped() {
        return sameLabelToBeGrouped;
    }

    /**
     * Default is false. This should be set to true when rowAttributes need grouping of multiple entities as part of same node. e.g. customer sales by address may include more then one customer in one address node
     *
     * @param sameLabelToBeGrouped
     *     The sameLabelToBeGrouped
     */
    @JsonProperty("sameLabelToBeGrouped")
    public void setSameLabelToBeGrouped(Boolean sameLabelToBeGrouped) {
        this.sameLabelToBeGrouped = sameLabelToBeGrouped;
    }

    /**
     * Decides whether to show grand total node or not.
     *
     * @return
     *     The showTotalNode
     */
    @JsonProperty("showTotalNode")
    public Boolean getShowTotalNode() {
        return showTotalNode;
    }

    /**
     * Decides whether to show grand total node or not.
     *
     * @param showTotalNode
     *     The showTotalNode
     */
    @JsonProperty("showTotalNode")
    public void setShowTotalNode(Boolean showTotalNode) {
        this.showTotalNode = showTotalNode;
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

    /**
     * Collection of pairs of criteria and qzInfo. QZ Info for auto generated nodes will be overriden based on criterias
     *
     * @return
     *     The qzInfoCriterias
     */
    @JsonProperty("qzInfoCriterias")
    public List<QzInfoCriteria> getQzInfoCriterias() {
        return qzInfoCriterias;
    }

    /**
     * Collection of pairs of criteria and qzInfo. QZ Info for auto generated nodes will be overriden based on criterias
     *
     * @param qzInfoCriterias
     *     The qzInfoCriterias
     */
    @JsonProperty("qzInfoCriterias")
    public void setQzInfoCriterias(List<QzInfoCriteria> qzInfoCriterias) {
        this.qzInfoCriterias = qzInfoCriterias;
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