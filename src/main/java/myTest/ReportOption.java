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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "optArgs",
        "optName",
        "include",
        "visible",
        "visibilityEvaluator",
        "criteria"
})
public class ReportOption implements Serializable {

    @JsonProperty("optArgs")
    private List<OptArg> optArgs = new ArrayList<OptArg>();
    /**
     *
     * (Required)
     *
     */
    @JsonProperty("optName")
    private ReportOption.OptName optName;
    /**
     * true means add the report option to the report. false means removal of report option from the report. Default is true
     *
     */
    @JsonProperty("include")
    private Boolean include;

    /**
     * true means add the report option to the report. false means hide report option from the report. Default is true
     * If hidden, option may be used for processing of other options or criteria
     *
     */
    @JsonProperty("visible")
    private Boolean visible;

    /**
     *
     * Add criteria to define the specific option
     *
     */
    @JsonProperty("visibilityEvaluator")
    private String visibilityEvaluator;

    /**
     *
     * Add criteria to define the specific option
     *
     */
    @JsonProperty("criteria")
    private String criteria;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     *     The optArgs
     */
    @JsonProperty("optArgs")
    public List<OptArg> getOptArgs() {
        return optArgs;
    }

    /**
     *
     * @param optArgs
     *     The optArgs
     */
    @JsonProperty("optArgs")
    public void setOptArgs(List<OptArg> optArgs) {
        this.optArgs = optArgs;
    }

    /**
     *
     * (Required)
     *
     * @return
     *     The optName
     */
    @JsonProperty("optName")
    public ReportOption.OptName getOptName() {
        return optName;
    }

    /**
     *
     * (Required)
     *
     * @param optName
     *     The optName
     */
    @JsonProperty("optName")
    public void setOptName(ReportOption.OptName optName) {
        this.optName = optName;
    }

    /**
     * true means add the report option to the report. false means removal of report option from the report. Default is true
     *
     * @return
     *     The include
     */
    @JsonProperty("include")
    public Boolean getInclude() {
        return include;
    }

    /**
     * true means add the report option to the report. false means removal of report option from the report. Default is true
     *
     * @param include
     *     The include
     */
    @JsonProperty("include")
    public void setInclude(Boolean include) {
        this.include = include;
    }

    /**
     * true means add the report option to the report. false means hide report option from the report. Default is true
     * If hidden, option may be used for processing of other options or criteria
     *
     * @return
     *     The visible
     */
    public Boolean getVisible() {
        return visible;
    }

    /**
     * true means add the report option to the report. false means hide report option from the report. Default is true
     * If hidden, option may be used for processing of other options or criteria
     *
     * * @param visible
     *     The visible
     */
    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    /**
     * Add criteria to define the specific option
     *
     * @return
     *     The criteria
     */
    public String getCriteria() {
        return criteria;
    }

    /**
     * Add criteria to define the specific option
     *
     * * @param criteria
     *     The criteria
     */
    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }

    public String getVisibilityEvaluator() {
        return visibilityEvaluator;
    }

    public void setVisibilityEvaluator(String visibilityEvaluator) {
        this.visibilityEvaluator = visibilityEvaluator;
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
    public static enum OptName {

        TXNDATE_MACRO("TXNDATE_MACRO"),
        HIGH_TXNDATE("HIGH_TXNDATE"),
        LOW_TXNDATE("LOW_TXNDATE"),
        REPORT_TXNDATE("REPORT_TXNDATE"),
        CREATEDATE_MACRO("CREATEDATE_MACRO"),
        HIGH_CREATEDATE("HIGH_CREATEDATE"),
        LOW_CREATEDATE("LOW_CREATEDATE"),
        REPORT_CREATEDATE("REPORT_CREATEDATE"),
        MODDATE_MACRO("MODDATE_MACRO"),
        HIGH_MODDATE("HIGH_MODDATE"),
        LOW_MODDATE("LOW_MODDATE"),
        REPORT_MODDATE("REPORT_MODDATE"),
        DUEDATE_MACRO("DUEDATE_MACRO"),
        HIGH_DUEDATE("HIGH_DUEDATE"),
        LOW_DUEDATE("LOW_DUEDATE"),
        REPORT_DUEDATE("REPORT_DUEDATE"),
        STMTDATE_MACRO("STMTDATE_MACRO"),
        HIGH_STMTDATE("HIGH_STMTDATE"),
        LOW_STMTDATE("LOW_STMTDATE"),
        REPORT_STMTDATE("REPORT_STMTDATE"),
        ACTDATE_MACRO("ACTDATE_MACRO"),
        HIGH_ACTDATE("HIGH_ACTDATE"),
        LOW_ACTDATE("LOW_ACTDATE"),
        REPORT_ACTDATE("REPORT_ACTDATE"),
        SVCDATE_MACRO("SVCDATE_MACRO"),
        HIGH_SVCDATE("HIGH_SVCDATE"),
        LOW_SVCDATE("LOW_SVCDATE"),
        REPORT_SVCDATE("REPORT_SVCDATE"),
        EXPIREDATE_MACRO("EXPIREDATE_MACRO"),
        HIGH_EXPIREDATE("HIGH_EXPIREDATE"),
        LOW_EXPIREDATE("LOW_EXPIREDATE"),
        REPORT_EXPIREDATE("REPORT_EXPIREDATE"),
        HIREDDATE_MACRO("HIREDDATE_MACRO"),
        REPORT_HIREDDATE("REPORT_HIREDDATE"),
        HIGH_HIREDDATE("HIGH_HIREDDATE"),
        LOW_HIREDDATE("LOW_HIREDDATE"),
        RELEASEDDATE_MACRO("RELEASEDDATE_MACRO"),
        HIGH_RELEASEDDATE("HIGH_RELEASEDDATE"),
        LOW_RELEASEDDATE("LOW_RELEASEDDATE"),
        REPORT_RELEASEDDATE("REPORT_RELEASEDDATE"),
        TIMEOFFACCRUALDATE_MACRO("TIMEOFFACCRUALDATE_MACRO"),
        HIGH_TIMEOFFACCRUALDATE("HIGH_TIMEOFFACCRUALDATE"),
        LOW_TIMEOFFACCRUALDATE("LOW_TIMEOFFACCRUALDATE"),
        REPORT_TIMEOFFACCRUALDATE("REPORT_TIMEOFFACCRUALDATE"),
        LIABILITYDATE_MACRO("LIABILITYDATE_MACRO"),
        HIGH_LIABILITYDATE("HIGH_LIABILITYDATE"),
        LOW_LIABILITYDATE("LOW_LIABILITYDATE"),
        OFXDATE_MACRO("OFXDATE_MACRO"),
        HIGH_OFXDATE("HIGH_OFXDATE"),
        LOW_OFXDATE("LOW_OFXDATE"),
        REPORT_LIABILITYDATE("REPORT_LIABILITYDATE"),
        ACCOUNT("ACCOUNT"),
        ACCOUNT_TYPE("ACCOUNT_TYPE"),
        DETAIL_ACCOUNT_TYPE("DETAIL_ACCOUNT_TYPE"),
        ACCOUNT_INCL_SUBS("ACCOUNT_INCL_SUBS"),
        SOURCE_ACCOUNT("SOURCE_ACCOUNT"),
        SOURCE_ACCOUNT_TYPE("SOURCE_ACCOUNT_TYPE"),
        SOURCE_ACCOUNT_INCL_SUBS("SOURCE_ACCOUNT_INCL_SUBS"),
        F_1099_ACCOUNT("F1099_ACCOUNT"),
        CUSTOMER("CUSTOMER"),
        CUSTOMER_INCL_SUBS("CUSTOMER_INCL_SUBS"),
        CUST_ROOT_PARENT_ID("CUST_ROOT_PARENT_ID"),
        VENDOR("VENDOR"),
        VENDOR_INCL_SUBS("VENDOR_INCL_SUBS"),
        DEPARTMENT("DEPARTMENT"),
        DEPARTMENT_INCL_SUBS("DEPARTMENT_INCL_SUBS"),
        SOURCE_DEPARTMENT("SOURCE_DEPARTMENT"),
        SOURCE_DEPARTMENT_INCL_SUBS("SOURCE_DEPARTMENT_INCL_SUBS"),
        KLASS("KLASS"),
        KLASS_INCL_SUBS("KLASS_INCL_SUBS"),
        EMPLOYEE("EMPLOYEE"),
        ITEM("ITEM"),
        ITEM_INCL_SUBS("ITEM_INCL_SUBS"),
        NAME("NAME"),
        NAME_INCL_SUBS("NAME_INCL_SUBS"),
        TIME_USER("TIME_USER"),
        TIME_USER_INCL_SUBS("TIME_USER_INCL_SUBS"),
        PAYMENTMETHOD("PAYMENTMETHOD"),
        BUDGET("BUDGET"),
        TERM("TERM"),
        PAYROLL_DEPARTMENT("PAYROLL_DEPARTMENT"),
        PAYROLL_DEPARTMENT_INCL_SUBS("PAYROLL_DEPARTMENT_INCL_SUBS"),
        PAYROLL_KLASS("PAYROLL_KLASS"),
        PAYROLL_KLASS_INCL_SUBS("PAYROLL_KLASS_INCL_SUBS"),
        PAYGROUP("PAYGROUP"),
        PAYROLL_ITEM("PAYROLL_ITEM"),
        PAYROLL_ITEM_INCL_SUBS("PAYROLL_ITEM_INCL_SUBS"),
        PAYGROUP_INCL_SUBS("PAYGROUP_INCL_SUBS"),
        EARNING_ITEM("EARNING_ITEM"),
        EARNING_ITEM_INCL_SUBS("EARNING_ITEM_INCL_SUBS"),
        TAX_ITEM("TAX_ITEM"),
        TAX_ITEM_INCL_SUBS("TAX_ITEM_INCL_SUBS"),
        OPD_ITEM("OPD_ITEM"),
        OPD_ITEM_INCL_SUBS("OPD_ITEM_INCL_SUBS"),
        EMP_PAYROLL_DEPARTMENT("EMP_PAYROLL_DEPARTMENT"),
        EMP_PAYROLL_DEPARTMENT_INCL_SUBS("EMP_PAYROLL_DEPARTMENT_INCL_SUBS"),
        STATE_TAX_ITEM("STATE_TAX_ITEM"),
        STATE_TAX_ITEM_INCL_SUBS("STATE_TAX_ITEM_INCL_SUBS"),
        FEDERAL_TAX_ITEM("FEDERAL_TAX_ITEM"),
        ADD_TARGET("ADD_TARGET"),
        AGING_PERIOD("AGING_PERIOD"),
        AMOUNT("AMOUNT"),
        AP_PAID("AP_PAID"),
        AR_PAID("AR_PAID"),
        BOTH_AMOUNT("BOTH_AMOUNT"),
        BOX_1099("BOX_1099"),
        CASH_BASIS("CASH_BASIS"),
        CLEARED("CLEARED"),
        COLUMNS("COLUMNS"),
        CREATEDBY_ID("CREATEDBY_ID"),
        CRIT("CRIT"),
        CUMULATIVE("CUMULATIVE"),
        CUSTOMIZED("CUSTOMIZED"),
        DELETED("DELETED"),
        DET_TAXABLE("DET_TAXABLE"),
        DOCNUM("DOCNUM"),
        FULL_COLUMNS("FULL_COLUMNS"),
        GROUP_BY("GROUP_BY"),
        HDR_TAXABLE("HDR_TAXABLE"),
        HIDE_GROUP_BY("HIDE_GROUP_BY"),
        IS_BILLABLE("IS_BILLABLE"),
        IS_CUST_PAYMENT("IS_CUST_PAYMENT"),
        IS_INVOICED("IS_INVOICED"),
        IS_SALE("IS_SALE"),
        IS_STATEMENTED("IS_STATEMENTED"),
        IS_TIME_INVOICED("IS_TIME_INVOICED"),
        IS_TIME_STATEMENTED("IS_TIME_STATEMENTED"),
        LIMIT("LIMIT"),
        LIST("LIST"),
        MEMO("MEMO"),
        MISC_SQL("MISC_SQL"),
        MULTSELECT_QF("MULTSELECT_QF"),
        NO_POST("NO_POST"),
        NUM_PERIODS("NUM_PERIODS"),
        ORDER_BY("ORDER_BY"),
        PARENT_TOKEN("PARENT_TOKEN"),
        PAST_DUE("PAST_DUE"),
        PRINTED("PRINTED"),
        SALES_CUSTOM_1("SALES_CUSTOM1"),
        SALES_CUSTOM_2("SALES_CUSTOM2"),
        SALES_CUSTOM_3("SALES_CUSTOM3"),
        SALES_PRINTED("SALES_PRINTED"),
        SALES_SENT("SALES_SENT"),
        SALES_TYPE("SALES_TYPE"),
        SELECTED_COLUMNS("SELECTED_COLUMNS"),
        SHIP_VIA("SHIP_VIA"),
        SOURCE_AMOUNT("SOURCE_AMOUNT"),
        SUMMARY_COLUMNS("SUMMARY_COLUMNS"),
        TIME_MEMO("TIME_MEMO"),
        TITLE("TITLE"),
        TOKEN("TOKEN"),
        TOTAL("TOTAL"),
        TXN_ID("TXN_ID"),
        TXN_TYPE("TXN_TYPE"),
        TX_LINE("TX_LINE"),
        ROW_AXIS("ROW_AXIS"),
        MEM_NAME("MEM_NAME"),
        MEM_TYPE("MEM_TYPE"),
        ENDDATE_MACRO("ENDDATE_MACRO"),
        HIGH_ENDDATE("HIGH_ENDDATE"),
        LOW_ENDDATE("LOW_ENDDATE"),
        REPORT_ENDDATE("REPORT_ENDDATE"),
        AXIS_SET("AXIS_SET"),
        VISIBLE_SOURCE_AMOUNT("VISIBLE_SOURCE_AMOUNT"),
        AGING_AS_OF("AGING_AS_OF"),
        SHOW_ROWS("SHOW_ROWS"),
        SHOW_COLS("SHOW_COLS"),
        NEGATIVE_NUMS("NEGATIVE_NUMS"),
        NEGATIVE_RED("NEGATIVE_RED"),
        DIVIDE_BY_1000("DIVIDE_BY_1000"),
        EXCEPT_ZEROS("EXCEPT_ZEROS"),
        HIDE_CENTS("HIDE_CENTS"),
        BUDGET_AMOUNT_OVER("BUDGET_AMOUNT_OVER"),
        BUDGET_AMOUNT_REMAINING("BUDGET_AMOUNT_REMAINING"),
        BUDGET_PERCENT("BUDGET_PERCENT"),
        BUDGET_PERCENT_REMAINING("BUDGET_PERCENT_REMAINING"),
        SUBCOL_PREV_PERIOD("SUBCOL_PREV_PERIOD"),
        SUBCOL_PREV_PERIOD_CHANGE("SUBCOL_PREV_PERIOD_CHANGE"),
        SUBCOL_PREV_PERIOD_PCT_CHANGE("SUBCOL_PREV_PERIOD_PCT_CHANGE"),
        SUBCOL_PREV_YEAR("SUBCOL_PREV_YEAR"),
        SUBCOL_PREV_YEAR_CHANGE("SUBCOL_PREV_YEAR_CHANGE"),
        SUBCOL_PREV_YEAR_PCT_CHANGE("SUBCOL_PREV_YEAR_PCT_CHANGE"),
        SUBCOL_YTD("SUBCOL_YTD"),
        SUBCOL_PCT_YTD("SUBCOL_PCT_YTD"),
        SUBCOL_PCT_ROW("SUBCOL_PCT_ROW"),
        SUBCOL_PCT_COL("SUBCOL_PCT_COL"),
        SUBCOL_PCT_INC("SUBCOL_PCT_INC"),
        SUBCOL_PCT_EXP("SUBCOL_PCT_EXP"),
        ESTIMATE_STATUS("ESTIMATE_STATUS"),
        NEGATIVE_HOURS("NEGATIVE_HOURS"),
        NEGATIVE_HOURS_RED("NEGATIVE_HOURS_RED"),
        EXCEPT_ZEROHOURS("EXCEPT_ZEROHOURS"),
        HIDE_SECONDS("HIDE_SECONDS"),
        EMPLOYEE_DELETED("EMPLOYEE_DELETED"),
        PAYEE("PAYEE"),
        PAYROLL_ITEM_DETAIL_TYPE("PAYROLL_ITEM_DETAIL_TYPE"),
        PAYCHECK_PRINTED("PAYCHECK_PRINTED"),
        PAYSTUB_PRINTED("PAYSTUB_PRINTED"),
        HIDE_SSN("HIDE_SSN"),
        RECUR_TYPE("RECUR_TYPE"),
        COMPANY_NAME("COMPANY_NAME"),
        SHOW_HEADER_COMPANY("SHOW_HEADER_COMPANY"),
        SHOW_HEADER_TITLE("SHOW_HEADER_TITLE"),
        SHOW_HEADER_RANGE("SHOW_HEADER_RANGE"),
        SHOW_FOOTER_DATE("SHOW_FOOTER_DATE"),
        SHOW_FOOTER_TIME("SHOW_FOOTER_TIME"),
        SHOW_FOOTER_BASIS("SHOW_FOOTER_BASIS"),
        HEADER_ALIGNMENT("HEADER_ALIGNMENT"),
        FOOTER_ALIGNMENT("FOOTER_ALIGNMENT"),
        BUDGETED_ACCOUNTS_ONLY("BUDGETED_ACCOUNTS_ONLY"),
        WRAP("WRAP"),
        COLLAPSE_SUBS("COLLAPSE_SUBS"),
        SORT_BY("SORT_BY"),
        SORT_ORDER("SORT_ORDER"),
        SORT_MODE("SORT_MODE"),
        COLUMN_WIDTH("COLUMN_WIDTH"),
        PURCHASE_CUSTOM_1("PURCHASE_CUSTOM1"),
        PURCHASE_CUSTOM_2("PURCHASE_CUSTOM2"),
        PURCHASE_CUSTOM_3("PURCHASE_CUSTOM3"),
        PO_STATUS("PO_STATUS"),
        PURCHASE_TYPE("PURCHASE_TYPE"),
        TAX_AGENCY("TAX_AGENCY"),
        EXCHANGE_RATES("EXCHANGE_RATES"),
        FRS_TAX_BASIS("FRS_TAX_BASIS"),
        ADJUSTED_GAIN_LOSS("ADJUSTED_GAIN_LOSS"),
        VENDOR_1099("VENDOR_1099"),
        PAYMENTS("PAYMENTS"),
        BOX_ACCOUNTS("BOX_ACCOUNTS"),
        REPORT_EXPORT_GC_JSON("REPORT_EXPORT_GC_JSON"),
        HIDE_VOIDED_TRANSACTION("HIDE_VOIDED_TRANSACTION"),
        REPORT_EXPORT_FORMATS("REPORT_EXPORT_FORMATS"),
        ROW_OFFSET("ROW_OFFSET"),
        ROW_LIMIT("ROW_LIMIT"),
        BOX_THRESHOLD("BOX_THRESHOLD"),
        PROJECT_STATUS("PROJECT_STATUS");
        private final String value;
        private static Map<String, ReportOption.OptName> constants = new HashMap<String, ReportOption.OptName>();

        static {
            for (ReportOption.OptName c: values()) {
                constants.put(c.value, c);
            }
        }

        private OptName(String value) {
            this.value = value;
        }

        @JsonValue
        @Override
        public String toString() {
            return this.value;
        }

        @JsonCreator
        public static ReportOption.OptName fromValue(String value) {
            ReportOption.OptName constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}