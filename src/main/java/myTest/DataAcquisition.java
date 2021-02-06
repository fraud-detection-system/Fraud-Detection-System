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
 * Metadata used for generating the query and get the data from database.
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "dbFields",
        "dbCrits"
})
public class DataAcquisition implements Serializable {

    /**
     * Specifying DB Fields to be used in data acquisition and generate the query.
     *
     */
    @JsonProperty("dbFields")
    private List<DbField> dbFields = new ArrayList<DbField>();
    /**
     * Specifying additional DB criteria i.e. NO_POST might be required to be set to false for Profit And Loss report.
     *
     */
    @JsonProperty("dbCrits")
    private List<DbCrit> dbCrits = new ArrayList<DbCrit>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * Specifying DB Fields to be used in data acquisition and generate the query.
     *
     * @return
     *     The dbFields
     */
    @JsonProperty("dbFields")
    public List<DbField> getDbFields() {
        return dbFields;
    }

    /**
     * Specifying DB Fields to be used in data acquisition and generate the query.
     *
     * @param dbFields
     *     The dbFields
     */
    @JsonProperty("dbFields")
    public void setDbFields(List<DbField> dbFields) {
        this.dbFields = dbFields;

        //Let's initialize the DB field id to DB field map
        for(DbField dbField : dbFields) {
            dbFieldMap.put(dbField.getId(), dbField);
        }

    }

    /**
     * Specifying additional DB criteria i.e. NO_POST might be required to be set to false for Profit And Loss report.
     *
     * @return
     *     The dbCrits
     */
    @JsonProperty("dbCrits")
    public List<DbCrit> getDbCrits() {
        return dbCrits;
    }

    /**
     * Specifying additional DB criteria i.e. NO_POST might be required to be set to false for Profit And Loss report.
     *
     * @param dbCrits
     *     The dbCrits
     */
    @JsonProperty("dbCrits")
    public void setDbCrits(List<DbCrit> dbCrits) {
        this.dbCrits = dbCrits;
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

    private Map<String, DbField> dbFieldMap = new HashMap<>();

    public DbField findDBFieldById(String dbFieldId) {
        return dbFieldMap.get(dbFieldId);
    }

    public boolean containsDBFieldId(String dbFieldId) {
        return dbFieldMap.containsKey(dbFieldId);
    }

}