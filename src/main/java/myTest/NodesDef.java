package myTest;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.annotation.Generated;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;


/**
 * Schema which defines the metadata needed for Nodes generation
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "dataSource",
        "qzBaseURLOverride",
        "autoNodeDef",
        "entityListNodeDef",
        "nodes"
})
public class NodesDef implements Serializable {

    /**
     * Defines the metadata needed to decide which data to be used for node creation
     *
     */
    @JsonProperty("dataSource")
    private DataSource dataSource;
    /**
     * To override the Base Quick Zoom URL
     *
     */
    @JsonProperty("qzBaseURLOverride")
    private String qzBaseURLOverride;
    /**
     * Schema which defines metadata which is needed for automatic generation of nodes
     *
     */
    @JsonProperty("autoNodeDef")
    private AutoNodeDef autoNodeDef;
    /**
     * Collection of nodes to be part of this axis. Typically axes are composed of nodes. Nodes can be of Total or Detail Type. These are specified nodes.
     *
     */
    @JsonProperty("nodes")
    @JsonDeserialize(as = java.util.LinkedHashSet.class)
    private Set<Node> nodes = new LinkedHashSet<Node>();

    /**
     * Defines the metadata needed to decide which data to be used for node creation
     *
     * @return
     *     The dataSource
     */
    @JsonProperty("dataSource")
    public DataSource getDataSource() {
        return dataSource;
    }

    /**
     * Defines the metadata needed to decide which data to be used for node creation
     *
     * @param dataSource
     *     The dataSource
     */
    @JsonProperty("dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * To override the Base Quick Zoom URL
     *
     * @return
     *     The qzBaseURLOverride
     */
    @JsonProperty("qzBaseURLOverride")
    public String getQzBaseURLOverride() {
        return qzBaseURLOverride;
    }

    /**
     * To override the Base Quick Zoom URL
     *
     * @param qzBaseURLOverride
     *     The qzBaseURLOverride
     */
    @JsonProperty("qzBaseURLOverride")
    public void setQzBaseURLOverride(String qzBaseURLOverride) {
        this.qzBaseURLOverride = qzBaseURLOverride;
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

    /**
     * Collection of nodes to be part of this axis. Typically axes are composed of nodes. Nodes can be of Total or Detail Type. These are specified nodes.
     *
     * @return
     *     The nodes
     */
    @JsonProperty("nodes")
    public Set<Node> getNodes() {
        return nodes;
    }

    /**
     * Collection of nodes to be part of this axis. Typically axes are composed of nodes. Nodes can be of Total or Detail Type. These are specified nodes.
     *
     * @param nodes
     *     The nodes
     */
    @JsonProperty("nodes")
    public void setNodes(Set<Node> nodes) {
        this.nodes = nodes;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }


    @JsonProperty("entityListNodeDef")
    EntityListNodeDef entityListNodeDef;

    public EntityListNodeDef getEntityListNodeDef() {
        return entityListNodeDef;
    }

    public void setEntityListNodeDef(EntityListNodeDef entityListNodeDef) {
        this.entityListNodeDef = entityListNodeDef;
    }


}