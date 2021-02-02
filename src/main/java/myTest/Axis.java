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
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "expenseKey",
        "extends",
        "incomeKey",
        "totalKey",
        "isTAxis",
        "name",
        "axisType",
        "nodesDef"
})
public class Axis implements Serializable {

    /**
     * The Expense Key for P&L kind of report
     *
     */
    @JsonProperty("expenseKey")
    private String expenseKey;
    @JsonProperty("extends")
    private String _extends;
    /**
     * The Income Key for P&L kind of report
     *
     */
    @JsonProperty("incomeKey")
    private String incomeKey;
    /**
     * The Total Key for P&L kind of report
     *
     */
    @JsonProperty("totalKey")
    private String totalKey;
    /**
     * Set whether T-Axis - for generating T-structured report from backend
     *
     */
    @JsonProperty("isTAxis")
    private Boolean isTAxis;
    /**
     * Name of the axis
     * (Required)
     *
     */
    @JsonProperty("name")
    private String name;
    /**
     * Describes the type of the axis i.e. Row, Column, Sub axis etc.
     * (Required)
     *
     */
    @JsonProperty("axisType")
    private Axis.AxisType axisType;
    /**
     * Schema which defines the metadata needed for Nodes generation
     *
     */
    @JsonProperty("nodesDef")
    private NodesDef nodesDef;

    /**
     * The Expense Key for P&L kind of report
     *
     * @return
     *     The expenseKey
     */
    @JsonProperty("expenseKey")
    public String getExpenseKey() {
        return expenseKey;
    }

    /**
     * The Expense Key for P&L kind of report
     *
     * @param expenseKey
     *     The expenseKey
     */
    @JsonProperty("expenseKey")
    public void setExpenseKey(String expenseKey) {
        this.expenseKey = expenseKey;
    }

    /**
     *
     * @return
     *     The _extends
     */
    @JsonProperty("extends")
    public String getExtends() {
        return _extends;
    }

    /**
     *
     * @param _extends
     *     The extends
     */
    @JsonProperty("extends")
    public void setExtends(String _extends) {
        this._extends = _extends;
    }

    /**
     * The Income Key for P&L kind of report
     *
     * @return
     *     The incomeKey
     */
    @JsonProperty("incomeKey")
    public String getIncomeKey() {
        return incomeKey;
    }

    /**
     * The Income Key for P&L kind of report
     *
     * @param incomeKey
     *     The incomeKey
     */
    @JsonProperty("incomeKey")
    public void setIncomeKey(String incomeKey) {
        this.incomeKey = incomeKey;
    }

    /**
     * The Total Key for P&L kind of report
     *
     * @return
     *     The totalKey
     */
    @JsonProperty("totalKey")
    public String getTotalKey() {
        return totalKey;
    }

    /**
     * The Total Key for P&L kind of report
     *
     * @param totalKey
     *     The totalKey
     */
    @JsonProperty("totalKey")
    public void setTotalKey(String totalKey) {
        this.totalKey = totalKey;
    }

    /**
     * Set whether T-Axis - for generating T-structured report from backend
     *
     * @return
     *     The isTAxis
     */
    @JsonProperty("isTAxis")
    public Boolean getIsTAxis() {
        return isTAxis;
    }

    /**
     * Set whether T-Axis - for generating T-structured report from backend
     *
     * @param isTAxis
     *     The isTAxis
     */
    @JsonProperty("isTAxis")
    public void setIsTAxis(Boolean isTAxis) {
        this.isTAxis = isTAxis;
    }

    /**
     * Name of the axis
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
     * Name of the axis
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
     * Describes the type of the axis i.e. Row, Column, Sub axis etc.
     * (Required)
     *
     * @return
     *     The axisType
     */
    @JsonProperty("axisType")
    public Axis.AxisType getAxisType() {
        return axisType;
    }

    /**
     * Describes the type of the axis i.e. Row, Column, Sub axis etc.
     * (Required)
     *
     * @param axisType
     *     The axisType
     */
    @JsonProperty("axisType")
    public void setAxisType(Axis.AxisType axisType) {
        this.axisType = axisType;
    }

    /**
     * Schema which defines the metadata needed for Nodes generation
     *
     * @return
     *     The nodesDef
     */
    @JsonProperty("nodesDef")
    public NodesDef getNodesDef() {
        return nodesDef;
    }

    /**
     * Schema which defines the metadata needed for Nodes generation
     *
     * @param nodesDef
     *     The nodesDef
     */
    @JsonProperty("nodesDef")
    public void setNodesDef(NodesDef nodesDef) {
        this.nodesDef = nodesDef;
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


    @Generated("org.jsonschema2pojo")
    public static enum AxisType {

        ROW_AXIS("ROW_AXIS"),
        COL_AXIS("COL_AXIS"),
        SUB_AXIS("SUB_AXIS");
        private final String value;
        private static Map<String, Axis.AxisType> constants = new HashMap<String, Axis.AxisType>();

        static {
            for (Axis.AxisType c: values()) {
                constants.put(c.value, c);
            }
        }

        private AxisType(String value) {
            this.value = value;
        }

        @JsonValue
        @Override
        public String toString() {
            return this.value;
        }

        @JsonCreator
        public static Axis.AxisType fromValue(String value) {
            Axis.AxisType constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}