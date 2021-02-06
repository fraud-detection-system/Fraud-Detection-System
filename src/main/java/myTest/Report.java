package myTest;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.annotation.Generated;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "isIncrementalDefinition",
        "cashBasisTable",
        "dataAcquisition",
        "customMessages",
        "complianceMessages",
        "defaultDateMacro",
        "dependencies",
        "extends",
        "isCumulativeReport",
        "name",
        "reportOptions",
        "title",
        "propogateTitleToQZ",
        "type"
})
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({ @Type(value = SummaryReport.class, name = "SUMMARY"),
        @Type(value = DetailReport.class, name = "DETAIL") })
public class Report implements Serializable {

    /**
     * To specify whether the report is enhanced or made from the scratch. If report is enhanced for some particular region, this field will be true.
     * (Required)
     *
     */
    @JsonProperty("isIncrementalDefinition")
    private Boolean isIncrementalDefinition;
    /**
     * the Fact table to be used for the current report, should be one of the DataDict tables
     *
     */
    @JsonProperty("cashBasisTable")
    private String cashBasisTable;
    /**
     * Metadata used for generating the query and get the data from database.
     *
     */
    @JsonProperty("dataAcquisition")
    private DataAcquisition dataAcquisition;
    /**
     * Any custom messages we want to show - disclaimer, notes etc
     *
     */
    @JsonProperty("customMessages")
    private List<CustomMessage> customMessages = new ArrayList<CustomMessage>();
    @JsonProperty("complianceMessages")
    private List<String> complianceMessages = new ArrayList<String>();
    /**
     * Deafult time period to be used for creating the report.
     *
     */
    @JsonProperty("defaultDateMacro")
    private Report.DefaultDateMacro defaultDateMacro;
    /**
     * If an entity has references to other entities, prerequisite would be to list down all the referenced entities in this attribute.( It is not supported yet. It is for future scope)
     *
     */
    @JsonProperty("dependencies")
    private List<String> dependencies = new ArrayList<String>();
    @JsonProperty("extends")
    private String _extends;
    /**
     * Specify whether cumulative report i.e. Balance Sheet is a cumulative report, But Profit And Loss is not.
     *
     */
    @JsonProperty("isCumulativeReport")
    private Boolean isCumulativeReport;
    /**
     * Fully Qualified name of the Report
     * (Required)
     *
     */
    @JsonProperty("name")
    private String name;
    /**
     * Report Options initialization
     *
     */
    @JsonProperty("reportOptions")
    @JsonDeserialize(as = java.util.LinkedHashSet.class)
    private Set<ReportOption> reportOptions = new LinkedHashSet<ReportOption>();
    /**
     * The Report Title
     *
     */
    @JsonProperty("title")
    private String title;
    /**
     * propogate the current title of the report in QZ URL of detailed node appended by Node Name. Used in case of intermediate Report to have the context of parent Report
     *
     */
    @JsonProperty("propogateTitleToQZ")
    private Boolean propogateTitleToQZ;
    /**
     * Specify the type of Reports, i.e. Summary or Detail Report
     * (Required)
     *
     */
    @JsonProperty("type")
    private Report.Type type;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();



    /**
     * To specify whether the report is enhanced or made from the scratch. If report is enhanced for some particular region, this field will be true.
     * (Required)
     *
     * @return
     *     The isIncrementalDefinition
     */
    @JsonProperty("isIncrementalDefinition")
    public Boolean getIsIncrementalDefinition() {
        return isIncrementalDefinition;
    }

    /**
     * To specify whether the report is enhanced or made from the scratch. If report is enhanced for some particular region, this field will be true.
     * (Required)
     *
     * @param isIncrementalDefinition
     *     The isIncrementalDefinition
     */
    @JsonProperty("isIncrementalDefinition")
    public void setIsIncrementalDefinition(Boolean isIncrementalDefinition) {
        this.isIncrementalDefinition = isIncrementalDefinition;
    }

    /**
     * the Fact table to be used for the current report, should be one of the DataDict tables
     *
     * @return
     *     The cashBasisTable
     */
    @JsonProperty("cashBasisTable")
    public String getCashBasisTable() {
        return cashBasisTable;
    }

    /**
     * the Fact table to be used for the current report, should be one of the DataDict tables
     *
     * @param cashBasisTable
     *     The cashBasisTable
     */
    @JsonProperty("cashBasisTable")
    public void setCashBasisTable(String cashBasisTable) {
        this.cashBasisTable = cashBasisTable;
    }

    /**
     * Metadata used for generating the query and get the data from database.
     *
     * @return
     *     The dataAcquisition
     */
    @JsonProperty("dataAcquisition")
    public DataAcquisition getDataAcquisition() {
        return dataAcquisition;
    }

    /**
     * Metadata used for generating the query and get the data from database.
     *
     * @param dataAcquisition
     *     The dataAcquisition
     */
    @JsonProperty("dataAcquisition")
    public void setDataAcquisition(DataAcquisition dataAcquisition) {
        this.dataAcquisition = dataAcquisition;
    }

    /**
     * Any custom messages we want to show - disclaimer, notes etc
     *
     * @return
     *     The customMessages
     */
    @JsonProperty("customMessages")
    public List<CustomMessage> getCustomMessages() {
        return customMessages;
    }

    /**
     * Any custom messages we want to show - disclaimer, notes etc
     *
     * @param customMessages
     *     The customMessages
     */
    @JsonProperty("customMessages")
    public void setCustomMessages(List<CustomMessage> customMessages) {
        this.customMessages = customMessages;
    }

    /**
     *
     * @return
     *     The complianceMessages
     */
    @JsonProperty("complianceMessages")
    public List<String> getComplianceMessages() {
        return complianceMessages;
    }

    /**
     *
     * @param complianceMessages
     *     The complianceMessages
     */
    @JsonProperty("complianceMessages")
    public void setComplianceMessages(List<String> complianceMessages) {
        this.complianceMessages = complianceMessages;
    }

    /**
     * Deafult time period to be used for creating the report.
     *
     * @return
     *     The defaultDateMacro
     */
    @JsonProperty("defaultDateMacro")
    public Report.DefaultDateMacro getDefaultDateMacro() {
        return defaultDateMacro;
    }

    /**
     * Deafult time period to be used for creating the report.
     *
     * @param defaultDateMacro
     *     The defaultDateMacro
     */
    @JsonProperty("defaultDateMacro")
    public void setDefaultDateMacro(Report.DefaultDateMacro defaultDateMacro) {
        this.defaultDateMacro = defaultDateMacro;
    }

    /**
     * If an entity has references to other entities, prerequisite would be to list down all the referenced entities in this attribute.( It is not supported yet. It is for future scope)
     *
     * @return
     *     The dependencies
     */
    @JsonProperty("dependencies")
    public List<String> getDependencies() {
        return dependencies;
    }

    /**
     * If an entity has references to other entities, prerequisite would be to list down all the referenced entities in this attribute.( It is not supported yet. It is for future scope)
     *
     * @param dependencies
     *     The dependencies
     */
    @JsonProperty("dependencies")
    public void setDependencies(List<String> dependencies) {
        this.dependencies = dependencies;
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
     * Specify whether cumulative report i.e. Balance Sheet is a cumulative report, But Profit And Loss is not.
     *
     * @return
     *     The isCumulativeReport
     */
    @JsonProperty("isCumulativeReport")
    public Boolean getIsCumulativeReport() {
        return isCumulativeReport;
    }

    /**
     * Specify whether cumulative report i.e. Balance Sheet is a cumulative report, But Profit And Loss is not.
     *
     * @param isCumulativeReport
     *     The isCumulativeReport
     */
    @JsonProperty("isCumulativeReport")
    public void setIsCumulativeReport(Boolean isCumulativeReport) {
        this.isCumulativeReport = isCumulativeReport;
    }

    /**
     * Fully Qualified name of the Report
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
     * Fully Qualified name of the Report
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
     * Report Options initialization
     *
     * @return
     *     The reportOptions
     */
    @JsonProperty("reportOptions")
    public Set<ReportOption> getReportOptions() {
        return reportOptions;
    }

    /**
     * Report Options initialization
     *
     * @param reportOptions
     *     The reportOptions
     */
    @JsonProperty("reportOptions")
    public void setReportOptions(Set<ReportOption> reportOptions) {
        this.reportOptions = reportOptions;
    }

    /**
     * The Report Title
     *
     * @return
     *     The title
     */
    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    /**
     * The Report Title
     *
     * @param title
     *     The title
     */
    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * propogate the current title of the report in QZ URL of detailed node appended by Node Name. Used in case of intermediate Report to have the context of parent Report
     *
     * @return
     *     The propogateTitleToQZ
     */
    @JsonProperty("propogateTitleToQZ")
    public Boolean getPropogateTitleToQZ() {
        return propogateTitleToQZ;
    }

    /**
     * propogate the current title of the report in QZ URL of detailed node appended by Node Name. Used in case of intermediate Report to have the context of parent Report
     *
     * @param propogateTitleToQZ
     *     The propogateTitleToQZ
     */
    @JsonProperty("propogateTitleToQZ")
    public void setPropogateTitleToQZ(Boolean propogateTitleToQZ) {
        this.propogateTitleToQZ = propogateTitleToQZ;
    }

    /**
     * Specify the type of Reports, i.e. Summary or Detail Report
     * (Required)
     *
     * @return
     *     The type
     */
    @JsonProperty("type")
    public Report.Type getType() {
        return type;
    }

    /**
     * Specify the type of Reports, i.e. Summary or Detail Report
     * (Required)
     *
     * @param type
     *     The type
     */
    @JsonProperty("type")
    public void setType(Report.Type type) {
        this.type = type;
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
    public static enum DefaultDateMacro {

        MACRO_ALL("MACRO_ALL"),
        MACRO_CUSTOM("MACRO_CUSTOM"),
        MACRO_TODAY("MACRO_TODAY"),
        MACRO_THISWEEK("MACRO_THISWEEK"),
        MACRO_THISWEEKTODATE("MACRO_THISWEEKTODATE"),
        MACRO_THISMONTH("MACRO_THISMONTH"),
        MACRO_THISMONTHTODATE("MACRO_THISMONTHTODATE"),
        MACRO_THISQUARTER("MACRO_THISQUARTER"),
        MACRO_THISQUARTERTODATE("MACRO_THISQUARTERTODATE"),
        MACRO_THISFISCALQUARTER("MACRO_THISFISCALQUARTER"),
        MACRO_THISFISCALQUARTERTODATE("MACRO_THISFISCALQUARTERTODATE"),
        MACRO_THISYEAR("MACRO_THISYEAR"),
        MACRO_THISYEARTODATE("MACRO_THISYEARTODATE"),
        MACRO_THISYEARTOLASTMONTH("MACRO_THISYEARTOLASTMONTH"),
        MACRO_THISFISCALYEAR("MACRO_THISFISCALYEAR"),
        MACRO_THISFISCALYEARTODATE("MACRO_THISFISCALYEARTODATE"),
        MACRO_THISFISCALYEARTOLASTMONTH("MACRO_THISFISCALYEARTOLASTMONTH"),
        MACRO_YESTERDAY("MACRO_YESTERDAY"),
        MACRO_RECENT("MACRO_RECENT"),
        MACRO_LASTWEEK("MACRO_LASTWEEK"),
        MACRO_LASTWEEKTODATE("MACRO_LASTWEEKTODATE"),
        MACRO_LASTMONTH("MACRO_LASTMONTH"),
        MACRO_LASTMONTHTODATE("MACRO_LASTMONTHTODATE"),
        MACRO_LASTQUARTER("MACRO_LASTQUARTER"),
        MACRO_LASTQUARTERTODATE("MACRO_LASTQUARTERTODATE"),
        MACRO_LASTFISCALQUARTER("MACRO_LASTFISCALQUARTER"),
        MACRO_LASTFISCALQUARTERTODATE("MACRO_LASTFISCALQUARTERTODATE"),
        MACRO_LASTYEAR("MACRO_LASTYEAR"),
        MACRO_LASTYEARTODATE("MACRO_LASTYEARTODATE"),
        MACRO_LASTFISCALYEAR("MACRO_LASTFISCALYEAR"),
        MACRO_LASTFISCALYEARTODATE("MACRO_LASTFISCALYEARTODATE"),
        MACRO_NEXTWEEK("MACRO_NEXTWEEK"),
        MACRO_NEXTFOURWEEKS("MACRO_NEXTFOURWEEKS"),
        MACRO_NEXTMONTH("MACRO_NEXTMONTH"),
        MACRO_NEXTQUARTER("MACRO_NEXTQUARTER"),
        MACRO_NEXTFISCALQUARTER("MACRO_NEXTFISCALQUARTER"),
        MACRO_NEXTYEAR("MACRO_NEXTYEAR"),
        MACRO_NEXTFISCALYEAR("MACRO_NEXTFISCALYEAR"),
        MACRO_SINCE_30_DAYSAGO("MACRO_SINCE30DAYSAGO"),
        MACRO_SINCE_60_DAYSAGO("MACRO_SINCE60DAYSAGO"),
        MACRO_SINCE_90_DAYSAGO("MACRO_SINCE90DAYSAGO"),
        MACRO_SINCE_365_DAYSAGO("MACRO_SINCE365DAYSAGO"),
        MACRO_LASTPAYCHECKDATE("MACRO_LASTPAYCHECKDATE"),
        MACRO_LAST_3_MONTHS("MACRO_LAST3MONTHS");
        private final String value;
        private static Map<String, Report.DefaultDateMacro> constants = new HashMap<String, Report.DefaultDateMacro>();

        static {
            for (Report.DefaultDateMacro c: values()) {
                constants.put(c.value, c);
            }
        }

        private DefaultDateMacro(String value) {
            this.value = value;
        }

        @JsonValue
        @Override
        public String toString() {
            return this.value;
        }

        @JsonCreator
        public static Report.DefaultDateMacro fromValue(String value) {
            Report.DefaultDateMacro constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

    @Generated("org.jsonschema2pojo")
    public static enum Type {

        SUMMARY("SUMMARY"),
        DETAIL("DETAIL");
        private final String value;
        private static Map<String, Report.Type> constants = new HashMap<String, Report.Type>();

        static {
            for (Report.Type c: values()) {
                constants.put(c.value, c);
            }
        }

        private Type(String value) {
            this.value = value;
        }

        @JsonValue
        @Override
        public String toString() {
            return this.value;
        }

        @JsonCreator
        public static Report.Type fromValue(String value) {
            Report.Type constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}