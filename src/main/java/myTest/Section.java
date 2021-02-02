
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

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "title",
        "sectionType",
        "groupKey",
        "fullNameKey",
        "totalColumnKey",
        "evaluator",
        "orderBy",
        "quickZoomInfo",
        "qzDisabled",
        "rowQZDisabled",
        "rowQZInfo",
        "adapter",
        "colRefKey"
})
public class Section implements Serializable {

    /**
     * Title for the section
     *
     */
    @JsonProperty("title")
    private String title;
    /**
     * Type of section
     *
     */
    @JsonProperty("sectionType")
    private Section.SectionType sectionType;
    /**
     * Applicable for Group Section, based on which key grouping should happen, could be multiple keys comma separated
     *
     */
    @JsonProperty("groupKey")
    private String groupKey;
    /**
     * Full name key for Group Section
     *
     */
    @JsonProperty("fullNameKey")
    private String fullNameKey;
    /**
     * Column key to be used as total
     *
     */
    @JsonProperty("totalColumnKey")
    private String totalColumnKey;
    /**
     * key that defines row order in the section, could be multiple keys comma separated. For descending order should be suffixed by ' desc' (whitespace+desc)
     *
     */
    @JsonProperty("orderBy")
    private String orderBy;
    /**
     * Quick Zoom info for detail section
     *
     */
    @JsonProperty("quickZoomInfo")
    private String quickZoomInfo;


    /**
     * Making addition of quick zoom info optional
     *
     */
    @JsonProperty("qzDisabled")
    private Boolean qzDisabled;

    @JsonProperty("rowQZDisabled")
    private Boolean rowQZDisabled;

    @JsonProperty("rowQZInfo")
    private RowQZ rowQZInfo;

    @JsonProperty("evaluator")
    private Evaluator evaluator;

    @JsonProperty("colRefKey")
    private String colRefKey;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * Title for the section
     *
     * @return
     *     The title
     */
    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    /**
     * Title for the section
     *
     * @param title
     *     The title
     */
    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Type of section
     *
     * @return
     *     The sectionType
     */
    @JsonProperty("sectionType")
    public Section.SectionType getSectionType() {
        return sectionType;
    }

    /**
     * Type of section
     *
     * @param sectionType
     *     The sectionType
     */
    @JsonProperty("sectionType")
    public void setSectionType(Section.SectionType sectionType) {
        this.sectionType = sectionType;
    }

    /**
     * Applicable for Group Section, based on which key grouping should happen, could be multiple keys comma separated
     *
     * @return
     *     The groupKey
     */
    @JsonProperty("groupKey")
    public String getGroupKey() {
        return groupKey;
    }

    /**
     * Applicable for Group Section, based on which key grouping should happen, could be multiple keys comma separated
     *
     * @param groupKey
     *     The groupKey
     */
    @JsonProperty("groupKey")
    public void setGroupKey(String groupKey) {
        this.groupKey = groupKey;
    }

    /**
     * Full name key for Group Section
     *
     * @return
     *     The fullNameKey
     */
    @JsonProperty("fullNameKey")
    public String getFullNameKey() {
        return fullNameKey;
    }

    /**
     * Full name key for Group Section
     *
     * @param fullNameKey
     *     The fullNameKey
     */
    @JsonProperty("fullNameKey")
    public void setFullNameKey(String fullNameKey) {
        this.fullNameKey = fullNameKey;
    }

    /**
     * Column key to be used as total
     *
     * @return
     *     The totalColumnKey
     */
    @JsonProperty("totalColumnKey")
    public String getTotalColumnKey() {
        return totalColumnKey;
    }

    /**
     * Column key to be used as total
     *
     * @param totalColumnKey
     *     The totalColumnKey
     */
    @JsonProperty("totalColumnKey")
    public void setTotalColumnKey(String totalColumnKey) {
        this.totalColumnKey = totalColumnKey;
    }

    /**
     * key that defines row order in the section, could be multiple keys comma separated. For descending order should be suffixed by ' desc' (whitespace+desc)
     *
     * @return
     *     The orderBy
     */
    @JsonProperty("orderBy")
    public String getOrderBy() {
        return orderBy;
    }

    /**
     * key that defines row order in the section, could be multiple keys comma separated. For descending order should be suffixed by ' desc' (whitespace+desc)
     *
     * @param orderBy
     *     The orderBy
     */
    @JsonProperty("orderBy")
    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
    /**
     * Adapter for the section
     *
     */
    @JsonProperty("adapter")
    private String adapter;

    public String getAdapter() {
        return adapter;
    }

    public void setAdapter(String adapter) {
        this.adapter = adapter;
    }

    /**
     * Quick Zoom info for detail section
     *
     * @return
     *     The quickZoomInfo
     */
    @JsonProperty("quickZoomInfo")
    public String getQuickZoomInfo() {
        return quickZoomInfo;
    }

    /**
     * Quick Zoom info for detail section
     *
     * @param quickZoomInfo
     *     The quickZoomInfo
     */
    @JsonProperty("quickZoomInfo")
    public void setQuickZoomInfo(String quickZoomInfo) {
        this.quickZoomInfo = quickZoomInfo;
    }

    /**
     * Making addition of quick zoom info optional
     *
     * @param qzDisabled
     *     The qzDisabled
     */
    public Boolean getQzDisabled() {
        return qzDisabled;
    }

    public void setQzDisabled(Boolean qzDisabled) {
        this.qzDisabled = qzDisabled;
    }

    /**
     * Checks enabling of customRowQZs
     *
     * @return rowQZDisabled
     */
    @JsonProperty("rowQZDisabled")
    public Boolean getRowQZDisabled() {
        return rowQZDisabled;
    }

    /**
     * Checks enabling of customRowQZs
     *
     * @param rowQZDisabled
     */
    @JsonProperty("rowQZDisabled")
    public void setRowQZDisabled(Boolean rowQZDisabled) {
        this.rowQZDisabled = rowQZDisabled;
    }

    /**
     * Custom Row QuickZoom store
     *
     * @return rowQZInfo
     */
    @JsonProperty("rowQZInfo")
    public RowQZ getRowQZInfo() {
        return rowQZInfo;
    }

    /**
     * Custom Row QuickZoom store
     *
     * @param rowQZInfo
     */
    @JsonProperty("rowQZInfo")
    public void setRowQZInfo(RowQZ rowQZInfo) {
        this.rowQZInfo = rowQZInfo;
    }

    @JsonProperty("evaluator")
    public Evaluator getEvaluator() {
        return evaluator;
    }

    @JsonProperty("evaluator")
    public void setEvaluator(Evaluator evaluator) {
        this.evaluator = evaluator;
    }

    public String getColRefKey() {
        return colRefKey;
    }

    public void setColRefKey(String colRefKey) {
        this.colRefKey = colRefKey;
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

    @Generated("org.jsonschema2pojo")
    public static enum SectionType {

        DETAIL("DETAIL"),
        TOTAL("TOTAL"),
        GROUP("GROUP"),
        TX("TX");
        private final String value;
        private static Map<String, Section.SectionType> constants = new HashMap<String, Section.SectionType>();

        static {
            for (Section.SectionType c: values()) {
                constants.put(c.value, c);
            }
        }

        private SectionType(String value) {
            this.value = value;
        }

        @JsonValue
        @Override
        public String toString() {
            return this.value;
        }

        @JsonCreator
        public static Section.SectionType fromValue(String value) {
            Section.SectionType constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}