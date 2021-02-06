
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
        "expression",
        "dbFieldRef",
        "value",
        "valueType",
        "operation",
        "evaluationPhase",
        "expressionEvalutorRef"
})
public class Criteria implements Serializable {

    /**
     * expression to be used to find the value in cached list for filtering.Used if sourceType is cachedList
     *
     */
    @JsonProperty("expression")
    private String expression;
    /**
     * Reference of the DB field to be used to find the value in database result generated after query and used for filtering.Used in case of resultSet driven dataSource.
     *
     */
    @JsonProperty("dbFieldRef")
    private String dbFieldRef;
    /**
     * comma separated value
     * (Required)
     *
     */
    @JsonProperty("value")
    private String value;
    /**
     * this will describe the data type of values
     *
     */
    @JsonProperty("valueType")
    private Criteria.ValueType valueType;
    /**
     * Specifies which entities to be included and excluded.
     *
     */
    @JsonProperty("operation")
    private Criteria.Operation operation;
    /**
     * defines whether the evaluation is done at static time or delayed till runtime. When not specified static is default
     *
     */
    @JsonProperty("evaluationPhase")
    private Criteria.EvaluationPhase evaluationPhase;

    /**
     * expression to be used to find the value in cached list for filtering.Used if sourceType is cachedList
     *
     * @return
     *     The expression
     */
    @JsonProperty("expression")
    public String getExpression() {
        return expression;
    }

    /**
     * expression to be used to find the value in cached list for filtering.Used if sourceType is cachedList
     *
     * @param expression
     *     The expression
     */
    @JsonProperty("expression")
    public void setExpression(String expression) {
        this.expression = expression;
    }

    /**
     * Reference of the DB field to be used to find the value in database result generated after query and used for filtering.Used in case of resultSet driven dataSource.
     *
     * @return
     *     The dbFieldRef
     */
    @JsonProperty("dbFieldRef")
    public String getDbFieldRef() {
        return dbFieldRef;
    }

    /**
     * Reference of the DB field to be used to find the value in database result generated after query and used for filtering.Used in case of resultSet driven dataSource.
     *
     * @param dbFieldRef
     *     The dbFieldRef
     */
    @JsonProperty("dbFieldRef")
    public void setDbFieldRef(String dbFieldRef) {
        this.dbFieldRef = dbFieldRef;
    }

    /**
     * comma separated value
     * (Required)
     *
     * @return
     *     The value
     */
    @JsonProperty("value")
    public String getValue() {
        return value;
    }

    /**
     * comma separated value
     * (Required)
     *
     * @param value
     *     The value
     */
    @JsonProperty("value")
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * this will describe the data type of values
     *
     * @return
     *     The valueType
     */
    @JsonProperty("valueType")
    public Criteria.ValueType getValueType() {
        return valueType;
    }

    /**
     * this will describe the data type of values
     *
     * @param valueType
     *     The valueType
     */
    @JsonProperty("valueType")
    public void setValueType(Criteria.ValueType valueType) {
        this.valueType = valueType;
    }

    /**
     * Specifies which entities to be included and excluded.
     *
     * @return
     *     The operation
     */
    @JsonProperty("operation")
    public Criteria.Operation getOperation() {
        return operation;
    }

    /**
     * Specifies which entities to be included and excluded.
     *
     * @param operation
     *     The operation
     */
    @JsonProperty("operation")
    public void setOperation(Criteria.Operation operation) {
        this.operation = operation;
    }

    /**
     * defines whether the evaluation is done at static time or delayed till runtime. When not specified static is default
     *
     * @return
     *     The evaluationPhase
     */
    @JsonProperty("evaluationPhase")
    public Criteria.EvaluationPhase getEvaluationPhase() {
        return evaluationPhase;
    }

    /**
     * defines whether the evaluation is done at static time or delayed till runtime. When not specified static is default
     *
     * @param evaluationPhase
     *     The evaluationPhase
     */
    @JsonProperty("evaluationPhase")
    public void setEvaluationPhase(Criteria.EvaluationPhase evaluationPhase) {
        this.evaluationPhase = evaluationPhase;
    }

    @JsonProperty("expressionEvalutorRef")
    private String expressionEvalutorRef;

    @JsonProperty("expressionEvalutorRef")
    public String getExpressionEvalutorRef() {
        return expressionEvalutorRef;
    }

    @JsonProperty("expressionEvalutorRef")
    public void setExpressionEvalutorRef(String expressionEvalutorRef) {
        this.expressionEvalutorRef = expressionEvalutorRef;
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
    public static enum EvaluationPhase {

        STATIC("static"),
        RUNTIME("runtime");
        private final String value;
        private static Map<String, Criteria.EvaluationPhase> constants = new HashMap<String, Criteria.EvaluationPhase>();

        static {
            for (Criteria.EvaluationPhase c: values()) {
                constants.put(c.value, c);
            }
        }

        private EvaluationPhase(String value) {
            this.value = value;
        }

        @JsonValue
        @Override
        public String toString() {
            return this.value;
        }

        @JsonCreator
        public static Criteria.EvaluationPhase fromValue(String value) {
            Criteria.EvaluationPhase constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

    @Generated("org.jsonschema2pojo")
    public static enum Operation {

        INCLUSION_SET("INCLUSION_SET"),
        EXCLUSION_SET("EXCLUSION_SET");
        private final String value;
        private static Map<String, Criteria.Operation> constants = new HashMap<String, Criteria.Operation>();

        static {
            for (Criteria.Operation c: values()) {
                constants.put(c.value, c);
            }
        }

        private Operation(String value) {
            this.value = value;
        }

        @JsonValue
        @Override
        public String toString() {
            return this.value;
        }

        @JsonCreator
        public static Criteria.Operation fromValue(String value) {
            Criteria.Operation constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

    @Generated("org.jsonschema2pojo")
    public static enum ValueType {

        STRING("String"),
        NUMBER("Number"),
        ALPHANUMERIC("alphanumeric"),
        DATE("date");
        private final String value;
        private static Map<String, Criteria.ValueType> constants = new HashMap<String, Criteria.ValueType>();

        static {
            for (Criteria.ValueType c: values()) {
                constants.put(c.value, c);
            }
        }

        private ValueType(String value) {
            this.value = value;
        }

        @JsonValue
        @Override
        public String toString() {
            return this.value;
        }

        @JsonCreator
        public static Criteria.ValueType fromValue(String value) {
            Criteria.ValueType constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}