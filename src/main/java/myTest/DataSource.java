package myTest;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
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


/**
 * Defines the metadata needed to decide which data to be used for node creation
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "sourceType",
        "cachedListType"
})
public class DataSource implements Serializable {

    /**
     * Decides whether to use Cached list (cachedList)  or database result (resultSet) generated after query
     *
     */
    @JsonProperty("sourceType")
    private DataSource.SourceType sourceType;
    /**
     * Defines the type of the cached list to be used(entity type)
     *
     */
    @JsonProperty("cachedListType")
    private DataSource.CachedListType cachedListType;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * Decides whether to use Cached list (cachedList)  or database result (resultSet) generated after query
     *
     * @return
     *     The sourceType
     */
    @JsonProperty("sourceType")
    public DataSource.SourceType getSourceType() {
        return sourceType;
    }

    /**
     * Decides whether to use Cached list (cachedList)  or database result (resultSet) generated after query
     *
     * @param sourceType
     *     The sourceType
     */
    @JsonProperty("sourceType")
    public void setSourceType(DataSource.SourceType sourceType) {
        this.sourceType = sourceType;
    }

    /**
     * Defines the type of the cached list to be used(entity type)
     *
     * @return
     *     The cachedListType
     */
    @JsonProperty("cachedListType")
    public DataSource.CachedListType getCachedListType() {
        return cachedListType;
    }

    /**
     * Defines the type of the cached list to be used(entity type)
     *
     * @param cachedListType
     *     The cachedListType
     */
    @JsonProperty("cachedListType")
    public void setCachedListType(DataSource.CachedListType cachedListType) {
        this.cachedListType = cachedListType;
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
    public static enum CachedListType {

        ACCOUNT("Account"),
        CUSTOMER("Customer"),
        EMPLOYEE("Employee"),
        VENDOR("Vendor"),
        DEPARTMENT("Department"),
        EMPLOYEE_DEPARTMENT("EmployeeDepartment"),
        KLASS("Klass"),
        ITEM("Item"),
        TAX_CODE_RATE("TaxCodeRate"),
        TAX_CODE_RATE_AST("TaxCodeRateAST"),
        TAX_CODE_RATE_AST_BY_ID("TaxCodeRateASTById");

        private final String value;
        private static Map<String, DataSource.CachedListType> constants = new HashMap<String, DataSource.CachedListType>();

        static {
            for (DataSource.CachedListType c: values()) {
                constants.put(c.value, c);
            }
        }

        private CachedListType(String value) {
            this.value = value;
        }

        @JsonValue
        @Override
        public String toString() {
            return this.value;
        }

        @JsonCreator
        public static DataSource.CachedListType fromValue(String value) {
            DataSource.CachedListType constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

    @Generated("org.jsonschema2pojo")
    public static enum SourceType {

        CACHED_LIST("cachedList"),
        RESULT_SET("resultSet");
        private final String value;
        private static Map<String, DataSource.SourceType> constants = new HashMap<String, DataSource.SourceType>();

        static {
            for (DataSource.SourceType c: values()) {
                constants.put(c.value, c);
            }
        }

        private SourceType(String value) {
            this.value = value;
        }

        @JsonValue
        @Override
        public String toString() {
            return this.value;
        }

        @JsonCreator
        public static DataSource.SourceType fromValue(String value) {
            DataSource.SourceType constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}