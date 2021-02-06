package myTest;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "legacyColName",
        "availability",
        "colName",
        "colHeader",
        "dbFieldRef",
        "isPrefControlled",
        "isSortable",
        "isRunningTotal",
        "subTotalType",
        "colIndex",
        "evaluator",
        "prefEvaluator",
        "colType",
        "colRef"
})
public class CatalogColumn implements Serializable {

    @JsonProperty("legacyColName")
    private CatalogColumn.LegacyColName legacyColName;
    @JsonProperty("availability")
    private CatalogColumn.Availability availability;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    @JsonProperty("colType")
    private CatalogColumn.ColType colType;
    @JsonProperty("colRef")
    private String colRef;

    public CatalogColumn.ColType getColType() {
        return colType;
    }

    public void setColType(CatalogColumn.ColType colType) {
        this.colType = colType;
    }

    /**
     *
     * @return
     *     The legacyColName
     */
    @JsonProperty("legacyColName")
    public CatalogColumn.LegacyColName getLegacyColName() {
        return legacyColName;
    }

    /**
     *
     * @param legacyColName
     *     The legacyColName
     */
    @JsonProperty("legacyColName")
    public void setLegacyColName(CatalogColumn.LegacyColName legacyColName) {
        this.legacyColName = legacyColName;
    }

    /**
     *
     * @return
     *     The availability
     */
    @JsonProperty("availability")
    public CatalogColumn.Availability getAvailability() {
        return availability;
    }

    /**
     *
     * @param availability
     *     The availability
     */
    @JsonProperty("availability")
    public void setAvailability(CatalogColumn.Availability availability) {
        this.availability = availability;
    }

    public String getColRef() {
        return colRef;
    }

    public void setColRef(String colRef) {
        this.colRef = colRef;
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
    public static enum Availability {

        AVAILABLE("AVAILABLE"),
        SELECTED("SELECTED");
        private final String value;
        private static Map<String, CatalogColumn.Availability> constants = new HashMap<String, CatalogColumn.Availability>();

        static {
            for (CatalogColumn.Availability c: values()) {
                constants.put(c.value, c);
            }
        }

        private Availability(String value) {
            this.value = value;
        }

        @JsonValue
        @Override
        public String toString() {
            return this.value;
        }

        @JsonCreator
        public static CatalogColumn.Availability fromValue(String value) {
            CatalogColumn.Availability constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }
    @Generated("org.jsonschema2pojo")
    public static enum ColType {
        LEGACY("LEGACY"),
        CATALOG_DEFINED_SIMPLE("CATALOG_DEFINED_SIMPLE"),
        CATALOG_DEFINED_COMPUTED("CATALOG_DEFINED_COMPUTED"),
        CATENATED("CATENATED");
        private final String value;
        private static Map<String, CatalogColumn.ColType> constants = new HashMap<String, CatalogColumn.ColType>();

        static {
            for (CatalogColumn.ColType c: values()) {
                constants.put(c.value, c);
            }
        }

        private ColType(String value) {
            this.value = value;
        }

        @JsonValue
        @Override
        public String toString() {
            return this.value;
        }

        @JsonCreator
        public static CatalogColumn.ColType fromValue(String value) {
            CatalogColumn.ColType constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

    @Generated("org.jsonschema2pojo")
    public static enum LegacyColName {

        ACCT_ACCT_NUM("ACCT_ACCT_NUM"),
        ACCT_DESCRIPTION("ACCT_DESCRIPTION"),
        ACCT_FORM_1099_BOX("ACCT_FORM1099_BOX"),
        ACCT_FULL_NAME("ACCT_FULL_NAME"),
        ACCT_ORDER_NAME("ACCT_ORDER_NAME"),
        ACCT_ID("ACCT_ID"),
        ACCT_INT_NAT_TOTAL_BAL("ACCT_INT_NAT_TOTAL_BAL"),
        ACCT_NUM("ACCT_NUM"),
        ACCT_TYPE_ID("ACCT_TYPE_ID"),
        AMOUNT("AMOUNT"),
        AMOUNT_HOME("AMOUNT_HOME"),
        AMOUNT_FOREIGN("AMOUNT_FOREIGN"),
        BILLABLE_STATE("BILLABLE_STATE"),
        BILLING_EMAIL("BILLING_EMAIL"),
        BILLING_FAX("BILLING_FAX"),
        COMPANY_NAME("COMPANY_NAME"),
        CONTACT_BILLING_ADDRESS_1("CONTACT_BILLING_ADDRESS_1"),
        CONTACT_BILLING_ADDRESS_2("CONTACT_BILLING_ADDRESS_2"),
        CONTACT_FULL_NAME_1("CONTACT_FULL_NAME_1"),
        CONTACT_FULL_NAME_2("CONTACT_FULL_NAME_2"),
        CONTACT_PHONE_NUMS_1("CONTACT_PHONE_NUMS_1"),
        CONTACT_PHONE_NUMS_1_A("CONTACT_PHONE_NUMS_1A"),
        CONTACT_PHONE_NUMS_2("CONTACT_PHONE_NUMS_2"),
        CONTACT_PHONE_NUMS_2_A("CONTACT_PHONE_NUMS_2A"),
        CONTACT_PHONE_NUMS_3("CONTACT_PHONE_NUMS_3"),
        CONTACT_PHONE_NUMS_3_A("CONTACT_PHONE_NUMS_3A"),
        CONTACT_PRIMARY_ADDRESS_1("CONTACT_PRIMARY_ADDRESS_1"),
        CONTACT_PRIMARY_EMAIL("CONTACT_PRIMARY_EMAIL"),
        CONTACT_PRIMARY_TEL_1("CONTACT_PRIMARY_TEL1"),
        CONTACT_PRIMARY_TEL_2("CONTACT_PRIMARY_TEL2"),
        CONTACT_SHIPPING_ADDRESS("CONTACT_SHIPPING_ADDRESS"),
        CREATED("CREATED"),
        CREATED_BY("CREATED_BY"),
        CREDIT("CREDIT"),
        CREDIT_HOME("CREDIT_HOME"),
        CREDIT_FOREIGN("CREDIT_FOREIGN"),
        CREDIT_CARD_EXPIRE("CREDIT_CARD_EXPIRE"),
        CREDIT_CARD_NUM("CREDIT_CARD_NUM"),
        CUST_FULL_NAME("CUST_FULL_NAME"),
        CUST_ORDER_NAME("CUST_ORDER_NAME"),
        CUST_ID("CUST_ID"),
        CUST_MESSAGE("CUST_MESSAGE"),
        DEBIT("DEBIT"),
        DEBIT_HOME("DEBIT_HOME"),
        DEBIT_FOREIGN("DEBIT_FOREIGN"),
        DELIVERY_ADDR("DELIVERY_ADDR"),
        DELIVERY_TYPE_ID("DELIVERY_TYPE_ID"),
        DEPT_FULL_NAME("DEPT_FULL_NAME"),
        DEPT_ORDER_NAME("DEPT_ORDER_NAME"),
        DEPT_ID("DEPT_ID"),
        DETAIL_ACCT_TYPE("DETAIL_ACCT_TYPE"),
        DOCNUM("DOCNUM"),
        DUE_DATE("DUE_DATE"),
        DURATION("DURATION"),
        EMP_ORDER_NAME("EMP_ORDER_NAME"),
        EMP_ID("EMP_ID"),
        EXTRA_DOCNUM("EXTRA_DOCNUM"),
        HIRED_DATE("HIRED_DATE"),
        INVOICE_DATE("INVOICE_DATE"),
        IS_ADJUSTING("IS_ADJUSTING"),
        IS_AP_PAID("IS_AP_PAID"),
        IS_AR_PAID("IS_AR_PAID"),
        IS_BILLABLE("IS_BILLABLE"),
        IS_CLEARED("IS_CLEARED"),
        OLB_STATUS("OLB_STATUS"),
        IS_NO_POST("IS_NO_POST"),
        ITEM_FULL_NAME("ITEM_FULL_NAME"),
        ITEM_ORDER_NAME("ITEM_ORDER_NAME"),
        ITEM_ID("ITEM_ID"),
        ITEM_SALES_ACCT_FULL_NAME("ITEM_SALES_ACCT_FULL_NAME"),
        ITEM_TYPE_ID("ITEM_TYPE_ID"),
        KLASS_ORDER_NAME("KLASS_ORDER_NAME"),
        KLASS_ID("KLASS_ID"),
        LAST_MOD_BY("LAST_MOD_BY"),
        LAST_MOD_DATE("LAST_MOD_DATE"),
        LAST_PRINT_1099("LAST_PRINT_1099"),
        MEMO("MEMO"),
        NAME_ORDER_NAME("NAME_ORDER_NAME"),
        NAME_ID("NAME_ID"),
        NAT_AMOUNT("NAT_AMOUNT"),
        NAT_AMOUNT_HOME("NAT_AMOUNT_HOME"),
        NAT_AMOUNT_FOREIGN("NAT_AMOUNT_FOREIGN"),
        NAT_OPEN_BALANCE("NAT_OPEN_BALANCE"),
        NAT_OPEN_BALANCE_HOME("NAT_OPEN_BALANCE_HOME"),
        NAT_OPEN_BALANCE_FOREIGN("NAT_OPEN_BALANCE_FOREIGN"),
        NEG_AMOUNT("NEG_AMOUNT"),
        NEG_AMOUNT_HOME("NEG_AMOUNT_HOME"),
        NEG_AMOUNT_FOREIGN("NEG_AMOUNT_FOREIGN"),
        NEG_AT_QTY("NEG_AT_QTY"),
        NEG_OPEN_BALANCE("NEG_OPEN_BALANCE"),
        NEG_OPEN_BALANCE_HOME("NEG_OPEN_BALANCE_HOME"),
        NEG_OPEN_BALANCE_FOREIGN("NEG_OPEN_BALANCE_FOREIGN"),
        NEG_QTY("NEG_QTY"),
        OPEN_BALANCE("OPEN_BALANCE"),
        OPEN_BALANCE_HOME("OPEN_BALANCE_HOME"),
        OPEN_BALANCE_FOREIGN("OPEN_BALANCE_FOREIGN"),
        PAST_DUE("PAST_DUE"),
        PAYMENTMETHOD_ORDER_NAME("PAYMENTMETHOD_ORDER_NAME"),
        PAYMENTMETHOD_ID("PAYMENTMETHOD_ID"),
        PRINTED("PRINTED"),
        QTY("QTY"),
        RATE("RATE"),
        RBAL_AMOUNT("RBAL_AMOUNT"),
        RBAL_AMOUNT_HOME("RBAL_AMOUNT_HOME"),
        RBAL_NAT_AMOUNT("RBAL_NAT_AMOUNT"),
        RBAL_NAT_AMOUNT_HOME("RBAL_NAT_AMOUNT_HOME"),
        RBAL_NEG_AMOUNT("RBAL_NEG_AMOUNT"),
        RBAL_NEG_AMOUNT_HOME("RBAL_NEG_AMOUNT_HOME"),
        RBAL_NEG_OPEN_BALANCE("RBAL_NEG_OPEN_BALANCE"),
        RBAL_NEG_OPEN_BALANCE_HOME("RBAL_NEG_OPEN_BALANCE_HOME"),
        RBAL_OPEN_BALANCE("RBAL_OPEN_BALANCE"),
        RBAL_OPEN_BALANCE_HOME("RBAL_OPEN_BALANCE_HOME"),
        RBAL_TIME_AMOUNT("RBAL_TIME_AMOUNT"),
        RECUR_END_DATE("RECUR_END_DATE"),
        RECUR_EXPIRED("RECUR_EXPIRED"),
        RECUR_LAST_ENTERED_DATE("RECUR_LAST_ENTERED_DATE"),
        RECUR_MEMORIZE_TYPE("RECUR_MEMORIZE_TYPE"),
        RECUR_NAME("RECUR_NAME"),
        RECUR_NEXT_DATE("RECUR_NEXT_DATE"),
        RECUR_NUM_ENTERED("RECUR_NUM_ENTERED"),
        REIMB_ACCT_ORDER_NAME("REIMB_ACCT_ORDER_NAME"),
        REIMB_ACCT_ID("REIMB_ACCT_ID"),
        RELEASED_DATE("RELEASED_DATE"),
        RESALE_NUM("RESALE_NUM"),
        SALES_ACCT_ORDER_NAME("SALES_ACCT_ORDER_NAME"),
        SALES_ACCT_ID("SALES_ACCT_ID"),
        PURCHASE_ACCT_ORDER_NAME("PURCHASE_ACCT_ORDER_NAME"),
        PURCHASE_ACCT_ID("PURCHASE_ACCT_ID"),
        QOH("QOH"),
        SALES_CUST_1("SALES_CUST1"),
        SALES_CUST_2("SALES_CUST2"),
        SALES_CUST_3("SALES_CUST3"),
        PURCHASE_CUST_1("PURCHASE_CUST1"),
        PURCHASE_CUST_2("PURCHASE_CUST2"),
        PURCHASE_CUST_3("PURCHASE_CUST3"),
        SALES_DESCRIPTION("SALES_DESCRIPTION"),
        SALES_PRICE("SALES_PRICE"),
        PURCHASE_DESCRIPTION("PURCHASE_DESCRIPTION"),
        PURCHASE_PRICE("PURCHASE_PRICE"),
        AVERAGE_COST("AVERAGE_COST"),
        ASSET_VALUE("ASSET_VALUE"),
        HOME_ASSET_VALUE("HOME_ASSET_VALUE"),
        QUANTITY_ON_HAND("QUANTITY_ON_HAND"),
        SALES_PRINTED("SALES_PRINTED"),
        SALES_SENT("SALES_SENT"),
        SERVICE_DATE("SERVICE_DATE"),
        SHIP_DATE("SHIP_DATE"),
        SHIP_VIA("SHIP_VIA"),
        FIFO_COST("FIFO_COST"),
        SPLIT_ACCT_FULL_NAME("SPLIT_ACCT_FULL_NAME"),
        SPLIT_ACCT_ORDER_NAME("SPLIT_ACCT_ORDER_NAME"),
        SPLIT_ACCT_ID("SPLIT_ACCT_ID"),
        STMT_DATE("STMT_DATE"),
        SUBT_AMOUNT("SUBT_AMOUNT"),
        SUBT_AMOUNT_HOME("SUBT_AMOUNT_HOME"),
        SUBT_DURATION("SUBT_DURATION"),
        SUBT_NAT_AMOUNT("SUBT_NAT_AMOUNT"),
        SUBT_NAT_AMOUNT_HOME("SUBT_NAT_AMOUNT_HOME"),
        SUBT_NEG_AMOUNT("SUBT_NEG_AMOUNT"),
        SUBT_NEG_AMOUNT_HOME("SUBT_NEG_AMOUNT_HOME"),
        SUBT_NEG_OPEN_BALANCE("SUBT_NEG_OPEN_BALANCE"),
        SUBT_NEG_OPEN_BALANCE_HOME("SUBT_NEG_OPEN_BALANCE_HOME"),
        SUBT_OPEN_BALANCE("SUBT_OPEN_BALANCE"),
        SUBT_OPEN_BALANCE_HOME("SUBT_OPEN_BALANCE_HOME"),
        SUBT_TIME_AMOUNT("SUBT_TIME_AMOUNT"),
        TAXABLE_TYPE("TAXABLE_TYPE"),
        TAX_ID("TAX_ID"),
        QTY_AT("QTY_AT"),
        TERM_DAY_OF_MONTH("TERM_DAY_OF_MONTH"),
        TERM_DISC_DAYS("TERM_DISC_DAYS"),
        TERM_DISC_DAY_OF_MONTH("TERM_DISC_DAY_OF_MONTH"),
        TERM_DISC_RATE("TERM_DISC_RATE"),
        TERM_ORDER_NAME("TERM_ORDER_NAME"),
        TERM_ID("TERM_ID"),
        TERM_MIN_DAYS("TERM_MIN_DAYS"),
        TERM_NET_DUE_DAYS("TERM_NET_DUE_DAYS"),
        TERM_TYPE("TERM_TYPE"),
        TIME_ACTIVITY_DATE("TIME_ACTIVITY_DATE"),
        TIME_ACT_TAXABLE_TYPE("TIME_ACT_TAXABLE_TYPE"),
        TIME_BILLING_RATE("TIME_BILLING_RATE"),
        TIME_EMP_FULL_NAME("TIME_EMP_FULL_NAME"),
        TIME_EMP_NAME("TIME_EMP_NAME"),
        TIME_INVOICE_DATE("TIME_INVOICE_DATE"),
        TIME_IS_NO_POST("TIME_IS_NO_POST"),
        TIME_ITEM_ORDER_NAME("TIME_ITEM_ORDER_NAME"),
        TIME_ITEM_ID("TIME_ITEM_ID"),
        TIME_NAME_ORDER_NAME("TIME_NAME_ORDER_NAME"),
        TIME_NAME_ID("TIME_NAME_ID"),
        TIME_RATE("TIME_RATE"),
        TIME_TAXABLE_TYPE("TIME_TAXABLE_TYPE"),
        TIME_START_TIME("TIME_START_TIME"),
        TIME_END_TIME("TIME_END_TIME"),
        TIME_BREAK_DURATION("TIME_BREAK_DURATION"),
        TRACKING_NUM("TRACKING_NUM"),
        TRACK_1099("TRACK_1099"),
        TXDET_CUST_ORDER_NAME("TXDET_CUST_ORDER_NAME"),
        TXDET_CUST_ID("TXDET_CUST_ID"),
        TXDET_IS_CLEARED("TXDET_IS_CLEARED"),
        TXDET_OLB_STATUS("TXDET_OLB_STATUS"),
        TXDET_OTHER_ACCT_ORDER_NAME("TXDET_OTHER_ACCT_ORDER_NAME"),
        TXDET_OTHER_ACCT_ID("TXDET_OTHER_ACCT_ID"),
        TXDET_VEND_ORODER_NAME("TXDET_VEND_ORODER_NAME"),
        TXDET_VEND_ID("TXDET_VEND_ID"),
        TXHDR_ACCT_ACCT_NUM("TXHDR_ACCT_ACCT_NUM"),
        TXHDR_ACCT_ORDER_NAME("TXHDR_ACCT_ORDER_NAME"),
        TXHDR_CREATED("TXHDR_CREATED"),
        TXHDR_CREATED_BY("TXHDR_CREATED_BY"),
        TXHDR_CUST_MESSAGE("TXHDR_CUST_MESSAGE"),
        TXHDR_DELIV_ADDRESS("TXHDR_DELIV_ADDRESS"),
        TXHDR_DELIV_TYPE("TXHDR_DELIV_TYPE"),
        TXHDR_DEPT_ORDER_NAME("TXHDR_DEPT_ORDER_NAME"),
        TXHDR_DOCNUM("TXHDR_DOCNUM"),
        TXHDR_EXTRA_DOCNUM("TXHDR_EXTRA_DOCNUM"),
        TXHDR_IS_AP_PAID("TXHDR_IS_AP_PAID"),
        TXHDR_IS_AR_PAID("TXHDR_IS_AR_PAID"),
        TXHDR_IS_NO_POST("TXHDR_IS_NO_POST"),
        TXHDR_IS_ADJUSTING("TXHDR_IS_ADJUSTING"),
        TXHDR_LAST_MOD_BY("TXHDR_LAST_MOD_BY"),
        TXHDR_LAST_MOD_DATE("TXHDR_LAST_MOD_DATE"),
        TXHDR_MEMO("TXHDR_MEMO"),
        TXHDR_MERGED_DOCNUM("TXHDR_MERGED_DOCNUM"),
        TXHDR_NAME_FULL_NAME("TXHDR_NAME_FULL_NAME"),
        TXHDR_NAME_ORDER_NAME("TXHDR_NAME_ORDER_NAME"),
        TXHDR_NAME_ID("TXHDR_NAME_ID"),
        TXHDR_PAYMENTMETHOD_ORDER_NAME("TXHDR_PAYMENTMETHOD_ORDER_NAME"),
        TXHDR_PRINTED("TXHDR_PRINTED"),
        TXHDR_SALES_CUST_1("TXHDR_SALES_CUST1"),
        TXHDR_SALES_CUST_2("TXHDR_SALES_CUST2"),
        TXHDR_SALES_CUST_3("TXHDR_SALES_CUST3"),
        TXHDR_PURCHASE_CUST_1("TXHDR_PURCHASE_CUST1"),
        TXHDR_PURCHASE_CUST_2("TXHDR_PURCHASE_CUST2"),
        TXHDR_PURCHASE_CUST_3("TXHDR_PURCHASE_CUST3"),
        TXHDR_SALES_PRINTED("TXHDR_SALES_PRINTED"),
        TXHDR_SALES_SENT("TXHDR_SALES_SENT"),
        TXHDR_SHIP_DATE("TXHDR_SHIP_DATE"),
        TXHDR_SHIP_VIA("TXHDR_SHIP_VIA"),
        TXHDR_TERM_ORDER_NAME("TXHDR_TERM_ORDER_NAME"),
        TXHDR_TERM_ID("TXHDR_TERM_ID"),
        TXHDR_TRACKING_NUM("TXHDR_TRACKING_NUM"),
        TXHDR_TYPE_LONG_NAME("TXHDR_TYPE_LONG_NAME"),
        TXHDR_TYPE_ID("TXHDR_TYPE_ID"),
        TXHDR_SUPPLIER_NAME_ID("TXHDR_SUPPLIER_NAME_ID"),
        TX_DATE("TX_DATE"),
        TX_TYPE_LONG_NAME("TX_TYPE_LONG_NAME"),
        TX_TYPE_ID("TX_TYPE_ID"),
        IS_MAS_TXN("IS_MAS_TXN"),
        MAS_STATE("MAS_STATE"),
        NOTE_TEXT("NOTE_TEXT"),
        CUST_COMPANY_NAME("CUST_COMPANY_NAME"),
        CUST_CONTACT_PRIMARY_TEL_1("CUST_CONTACT_PRIMARY_TEL1"),
        CUST_CONTACT_PHONE_NUMS_3("CUST_CONTACT_PHONE_NUMS_3"),
        CUST_BILLING_EMAIL("CUST_BILLING_EMAIL"),
        CUST_CONTACT_FULL_NAME_1("CUST_CONTACT_FULL_NAME_1"),
        TXN_SHIPPING_ADDRESS("TXN_SHIPPING_ADDRESS"),
        DET_TXN_SHIPPING_ADDRESS("DET_TXN_SHIPPING_ADDRESS"),
        TXN_BILLING_ADDRESS("TXN_BILLING_ADDRESS"),
        DET_TXN_BILLING_ADDRESS("DET_TXN_BILLING_ADDRESS"),
        CUST_BILLING_FAX("CUST_BILLING_FAX"),
        CUST_WEBSITE("CUST_WEBSITE"),
        VEND_ACCT_NUM("VEND_ACCT_NUM"),
        VEND_FULL_NAME("VEND_FULL_NAME"),
        VEND_ORDER_NAME("VEND_ORDER_NAME"),
        VEND_ID("VEND_ID"),
        VEND_COMPANY_NAME("VEND_COMPANY_NAME"),
        VEND_CONTACT_PRIMARY_TEL_1("VEND_CONTACT_PRIMARY_TEL1"),
        VEND_CONTACT_PHONE_NUMS_1("VEND_CONTACT_PHONE_NUMS_1"),
        VEND_CONTACT_PRIMARY_EMAIL("VEND_CONTACT_PRIMARY_EMAIL"),
        VEND_CONTACT_FULL_NAME_1("VEND_CONTACT_FULL_NAME_1"),
        VEND_CONTACT_BILLING_ADDRESS_2("VEND_CONTACT_BILLING_ADDRESS_2"),
        VEND_WEBSITE("VEND_WEBSITE"),
        EXPIRATION_DATE("EXPIRATION_DATE"),
        ESTIMATE_STATUS("ESTIMATE_STATUS"),
        PO_STATUS("PO_STATUS"),
        ACCEPTED_DATE("ACCEPTED_DATE"),
        ACCEPTED_BY("ACCEPTED_BY"),
        INVOICE_NUM("INVOICE_NUM"),
        PAYROLL_ITEM_ORDER_NAME("PAYROLL_ITEM_ORDER_NAME"),
        PAYROLL_ITEM_ID("PAYROLL_ITEM_ID"),
        HOURS("HOURS"),
        WAGE_BASE("WAGE_BASE"),
        INCOME_SUBJECT_TO_TAX("INCOME_SUBJECT_TO_TAX"),
        SS_NUM("SS_NUM"),
        BIRTH_DATE("BIRTH_DATE"),
        GENDER("GENDER"),
        EMPLOYEE_ID_STRING("EMPLOYEE_ID_STRING"),
        IS_DIY("IS_DIY"),
        PAYGROUP("PAYGROUP"),
        PAYROLL_DEPT("PAYROLL_DEPT"),
        PAYROLL_KLASS("PAYROLL_KLASS"),
        WORK_LOCATION("WORK_LOCATION"),
        TIME_PITEM("TIME_PITEM"),
        WITHHOLDING_LOCATION("WITHHOLDING_LOCATION"),
        EARNING_ORDER_NAME("EARNING_ORDER_NAME"),
        EARNING_ID("EARNING_ID"),
        DETAIL_TYPE("DETAIL_TYPE"),
        BASED_ON_QTY("BASED_ON_QTY"),
        EXPENSE_ACCT_FULL_NAME("EXPENSE_ACCT_FULL_NAME"),
        TAX_ORDER_NAME("TAX_ORDER_NAME"),
        TAX_ITEM_ID("TAX_ITEM_ID"),
        AGENCY_ORDER_NAME("AGENCY_ORDER_NAME"),
        AGENCY_ID("AGENCY_ID"),
        TAX_ID_NUM("TAX_ID_NUM"),
        TAX_RATE("TAX_RATE"),
        ACCRUAL_LIMIT("ACCRUAL_LIMIT"),
        LIABILITY_ACCT_FULL_NAME("LIABILITY_ACCT_FULL_NAME"),
        OPD_ORDER_NAME("OPD_ORDER_NAME"),
        OPD_ITEM_ID("OPD_ITEM_ID"),
        OPD_VEND_ACCT_NUM("OPD_VEND_ACCT_NUM"),
        EMP_PITEM_EMPLOYEE_NAME("EMP_PITEM_EMPLOYEE_NAME"),
        EMP_PITEM_PMT_DEDUCT_NAME("EMP_PITEM_PMT_DEDUCT_NAME"),
        EMP_PITEM_TAX_NAME("EMP_PITEM_TAX_NAME"),
        EMP_PITEM_RATE_AMOUNT("EMP_PITEM_RATE_AMOUNT"),
        EMP_PITEM_TAX_RATE("EMP_PITEM_TAX_RATE"),
        EMP_PITEM_ANNUAL_LIMIT("EMP_PITEM_ANNUAL_LIMIT"),
        EMP_PITEM_FILING_STATUS("EMP_PITEM_FILING_STATUS"),
        EMP_PITEM_ALLOWANCES("EMP_PITEM_ALLOWANCES"),
        EMP_PITEM_EXTRA_WITHHOLDING("EMP_PITEM_EXTRA_WITHHOLDING"),
        EMP_PITEM_WAGE_LIMIT("EMP_PITEM_WAGE_LIMIT"),
        EMP_PITEM_EXEMPTIONS("EMP_PITEM_EXEMPTIONS"),
        EMP_PITEM_EXEMPTION_AMOUNT("EMP_PITEM_EXEMPTION_AMOUNT"),
        EMP_PITEM_SPECIAL_EXEMPTION("EMP_PITEM_SPECIAL_EXEMPTION"),
        EMP_PITEM_DEDUCTIONS("EMP_PITEM_DEDUCTIONS"),
        EMP_PITEM_DEPENDENTS("EMP_PITEM_DEPENDENTS"),
        EMP_PITEM_SPECIAL_RATE("EMP_PITEM_SPECIAL_RATE"),
        EMP_PITEM_MD_SUBJECT_TO("EMP_PITEM_MD_SUBJECT_TO"),
        PAYROLL_VENDOR_ORDER_NAME("PAYROLL_VENDOR_ORDER_NAME"),
        PAYROLL_VENDOR_ID("PAYROLL_VENDOR_ID"),
        PITEM_DEFAULT_RATE("PITEM_DEFAULT_RATE"),
        PITEM_STATUS("PITEM_STATUS"),
        PITEM_DEFAULT_LIMIT("PITEM_DEFAULT_LIMIT"),
        PITEM_NET_EFFECT("PITEM_NET_EFFECT"),
        NET_PAY("NET_PAY"),
        PAYCHECK_PRINTED("PAYCHECK_PRINTED"),
        PAYSTUB_PRINTED("PAYSTUB_PRINTED"),
        PAY_PERIOD_BEGIN_DATE("PAY_PERIOD_BEGIN_DATE"),
        PAY_PERIOD_END_DATE("PAY_PERIOD_END_DATE"),
        TXHDR_PAY_PERIOD_BEGIN_DATE("TXHDR_PAY_PERIOD_BEGIN_DATE"),
        TXHDR_PAY_PERIOD_END_DATE("TXHDR_PAY_PERIOD_END_DATE"),
        EMP_NAME_ORDER_NAME("EMP_NAME_ORDER_NAME"),
        EMP_NAME_ID("EMP_NAME_ID"),
        EMP_SSN("EMP_SSN"),
        ACCRUAL_DATE("ACCRUAL_DATE"),
        REASON("REASON"),
        TIMEOFF_TYPE("TIMEOFF_TYPE"),
        TIMEOFF_TXN_TYPE("TIMEOFF_TXN_TYPE"),
        SUBT_HOURS("SUBT_HOURS"),
        RBAL_HOURS("RBAL_HOURS"),
        EMP_PAYROLL_DEPT_ORDER_NAME("EMP_PAYROLL_DEPT_ORDER_NAME"),
        EMP_PAYROLL_DEPT_ID("EMP_PAYROLL_DEPT_ID"),
        CONTACT_VENDOR_STREET("CONTACT_VENDOR_STREET"),
        CONTACT_VENDOR_CITY("CONTACT_VENDOR_CITY"),
        CONTACT_VENDOR_STATE("CONTACT_VENDOR_STATE"),
        CONTACT_VENDOR_POSTALCODE("CONTACT_VENDOR_POSTALCODE"),
        CONTACT_VENDOR_COUNTRY("CONTACT_VENDOR_COUNTRY"),
        CONTACT_BILLING_STREET("CONTACT_BILLING_STREET"),
        CONTACT_BILLING_CITY("CONTACT_BILLING_CITY"),
        CONTACT_BILLING_STATE("CONTACT_BILLING_STATE"),
        CONTACT_BILLING_POSTALCODE("CONTACT_BILLING_POSTALCODE"),
        CONTACT_BILLING_COUNTRY("CONTACT_BILLING_COUNTRY"),
        CONTACT_SHIPPING_STREET("CONTACT_SHIPPING_STREET"),
        CONTACT_SHIPPING_CITY("CONTACT_SHIPPING_CITY"),
        CONTACT_SHIPPING_STATE("CONTACT_SHIPPING_STATE"),
        CONTACT_SHIPPING_POSTALCODE("CONTACT_SHIPPING_POSTALCODE"),
        CONTACT_SHIPPING_COUNTRY("CONTACT_SHIPPING_COUNTRY"),
        CONTACT_EMPLOYEE_STREET("CONTACT_EMPLOYEE_STREET"),
        CONTACT_EMPLOYEE_CITY("CONTACT_EMPLOYEE_CITY"),
        CONTACT_EMPLOYEE_STATE("CONTACT_EMPLOYEE_STATE"),
        CONTACT_EMPLOYEE_POSTALCODE("CONTACT_EMPLOYEE_POSTALCODE"),
        CONTACT_EMPLOYEE_COUNTRY("CONTACT_EMPLOYEE_COUNTRY"),
        TX_LIABILITY_DATE("TX_LIABILITY_DATE"),
        PAYGROUP_NAME("PAYGROUP_NAME"),
        PAYGROUP_PAY_INTERVAL("PAYGROUP_PAY_INTERVAL"),
        PAYGROUP_LAST_CHECK_DATE("PAYGROUP_LAST_CHECK_DATE"),
        PAYGROUP_CHECK_DATE_INTERVAL("PAYGROUP_CHECK_DATE_INTERVAL"),
        OTHER_KLASS_ORDER_NAME("OTHER_KLASS_ORDER_NAME"),
        OTHER_KLASS_ID("OTHER_KLASS_ID"),
        LIABILITY_PERIOD_BEGIN_DATE("LIABILITY_PERIOD_BEGIN_DATE"),
        LIABILITY_PERIOD_END_DATE("LIABILITY_PERIOD_END_DATE"),
        LIABILITY_PERIOD_BEGIN_DATE_D("LIABILITY_PERIOD_BEGIN_DATE_D"),
        LIABILITY_PERIOD_END_DATE_D("LIABILITY_PERIOD_END_DATE_D"),
        PAYMENT_STATUS("PAYMENT_STATUS"),
        REFERENCE_NUM("REFERENCE_NUM"),
        PAYMENT_PERIOD("PAYMENT_PERIOD"),
        PAYMENT_NAME("PAYMENT_NAME"),
        PAYMENT_METHOD("PAYMENT_METHOD"),
        PAYMENT_VEND_ID("PAYMENT_VEND_ID"),
        TAXRATECODEID("TAXRATECODEID"),
        TAXCODE_NAME("TAXCODE_NAME"),
        TAXRATEID("TAXRATEID"),
        TAXRETURNLINENUMBER("TAXRETURNLINENUMBER"),
        TAX_RATE_DB_ID("TAX_RATE_DB_ID"),
        SALE_TAXCODE_NAME("SALE_TAXCODE_NAME"),
        PURCHASE_TAXCODE_NAME("PURCHASE_TAXCODE_NAME"),
        TAXCODE_ID("TAXCODE_ID"),
        NETAMOUNT("NETAMOUNT"),
        NETAMOUNT_HOME("NETAMOUNT_HOME"),
        NETAMOUNT_FOREIGN("NETAMOUNT_FOREIGN"),
        SUBT_NETAMOUNT("SUBT_NETAMOUNT"),
        SUBT_NETAMOUNT_HOME("SUBT_NETAMOUNT_HOME"),
        NATURAL_NETAMOUNT("NATURAL_NETAMOUNT"),
        NATURAL_NETAMOUNT_HOME("NATURAL_NETAMOUNT_HOME"),
        NATURAL_NETAMOUNT_FOREIGN("NATURAL_NETAMOUNT_FOREIGN"),
        SUBT_NATURAL_NETAMOUNT("SUBT_NATURAL_NETAMOUNT"),
        SUBT_NATURAL_NETAMOUNT_HOME("SUBT_NATURAL_NETAMOUNT_HOME"),
        NEG_NETAMOUNT("NEG_NETAMOUNT"),
        NEG_NETAMOUNT_HOME("NEG_NETAMOUNT_HOME"),
        NEG_NETAMOUNT_FOREIGN("NEG_NETAMOUNT_FOREIGN"),
        SUBT_NEG_NETAMOUNT("SUBT_NEG_NETAMOUNT"),
        SUBT_NEG_NETAMOUNT_HOME("SUBT_NEG_NETAMOUNT_HOME"),
        GLOBALTAX_NETAMOUNT("GLOBALTAX_NETAMOUNT"),
        GLOBALTAX_NETAMOUNT_HOME("GLOBALTAX_NETAMOUNT_HOME"),
        GLOBALTAX_NETAMOUNT_FOREIGN("GLOBALTAX_NETAMOUNT_FOREIGN"),
        TAXAMOUNT("TAXAMOUNT"),
        TAXAMOUNT_HOME("TAXAMOUNT_HOME"),
        TAXAMOUNT_FOREIGN("TAXAMOUNT_FOREIGN"),
        SUBT_TAXAMOUNT("SUBT_TAXAMOUNT"),
        SUBT_TAXAMOUNT_HOME("SUBT_TAXAMOUNT_HOME"),
        NEG_TAXAMOUNT("NEG_TAXAMOUNT"),
        NEG_TAXAMOUNT_HOME("NEG_TAXAMOUNT_HOME"),
        NEG_TAXAMOUNT_FOREIGN("NEG_TAXAMOUNT_FOREIGN"),
        SUBT_NEG_TAXAMOUNT("SUBT_NEG_TAXAMOUNT"),
        SUBT_NEG_TAXAMOUNT_HOME("SUBT_NEG_TAXAMOUNT_HOME"),
        NATURAL_TAXAMOUNT("NATURAL_TAXAMOUNT"),
        NATURAL_TAXAMOUNT_HOME("NATURAL_TAXAMOUNT_HOME"),
        NATURAL_TAXAMOUNT_FOREIGN("NATURAL_TAXAMOUNT_FOREIGN"),
        SUBT_NATURAL_TAXAMOUNT("SUBT_NATURAL_TAXAMOUNT"),
        SUBT_NATURAL_TAXAMOUNT_HOME("SUBT_NATURAL_TAXAMOUNT_HOME"),
        GLOBALTAX_AMOUNT("GLOBALTAX_AMOUNT"),
        GLOBALTAX_AMOUNT_HOME("GLOBALTAX_AMOUNT_HOME"),
        GLOBALTAX_AMOUNT_FOREIGN("GLOBALTAX_AMOUNT_FOREIGN"),
        RBAL_GLOBALTAX_AMOUNT("RBAL_GLOBALTAX_AMOUNT"),
        RBAL_GLOBALTAX_AMOUNT_HOME("RBAL_GLOBALTAX_AMOUNT_HOME"),
        LINE_NUM("LINE_NUM"),
        CONTACT_CUSTOMER_COUNTRY("CONTACT_CUSTOMER_COUNTRY"),
        TXN_ID("TXN_ID"),
        ASSIGNED_ACCT_NUM("ASSIGNED_ACCT_NUM"),
        VEND_UEN("VEND_UEN"),
        PERMIT_NUM("PERMIT_NUM"),
        CUST_UEN("CUST_UEN"),
        TXN_TYPE("TXN_TYPE"),
        EXCHANGE_RATE("EXCHANGE_RATE"),
        CURRENCY_TYPE("CURRENCY_TYPE"),
        REALIZED_GAINLOSS_AMT("REALIZED_GAINLOSS_AMT"),
        RBAL_REALIZED_GAINLOSS_AMT("RBAL_REALIZED_GAINLOSS_AMT"),
        ACCT_CURRENCY_TYPE("ACCT_CURRENCY_TYPE"),
        ACCT_FOREIGN_BAL("ACCT_FOREIGN_BAL"),
        USER_EXCHANGE_RATE("USER_EXCHANGE_RATE"),
        ACCT_ADJ_BAL("ACCT_ADJ_BAL"),
        ACCT_HOME_BAL("ACCT_HOME_BAL"),
        GAIN_LOSS_AMT("GAIN_LOSS_AMT"),
        GTM_EXCEPTION_TXN_NUM("GTM_EXCEPTION_TXN_NUM"),
        GTM_EXCEPTION_TXN_TYPE("GTM_EXCEPTION_TXN_TYPE"),
        GTM_EXCEPTION_NAME("GTM_EXCEPTION_NAME"),
        GTM_EXCEPTION_TXN_ORG_DATE("GTM_EXCEPTION_TXN_ORG_DATE"),
        GTM_EXCEPTION_FILED_AMOUNT("GTM_EXCEPTION_FILED_AMOUNT"),
        GTM_EXCEPTION_FILED_AMOUNT_HOME("GTM_EXCEPTION_FILED_AMOUNT_HOME"),
        GTM_EXCEPTION_TXN_DATE("GTM_EXCEPTION_TXN_DATE"),
        GTM_EXCEPTION_CASHBASIS_TXN_DATE("GTM_EXCEPTION_CASHBASIS_TXN_DATE"),
        GTM_EXCEPTION_CASHBASIS_CURRENT_AMOUNT("GTM_EXCEPTION_CASHBASIS_CURRENT_AMOUNT"),
        GTM_EXCEPTION_TXDETAILS_CURRENT_AMOUNT("GTM_EXCEPTION_TXDETAILS_CURRENT_AMOUNT"),
        GTM_EXCEPTION_DIFF_AMOUNT("GTM_EXCEPTION_DIFF_AMOUNT"),
        GTM_EXCEPTION_FILED_AMOUNT_FOREIGN("GTM_EXCEPTION_FILED_AMOUNT_FOREIGN"),
        GTM_EXCEPTION_CASHBASIS_CURRENT_AMOUNT_FOREIGN("GTM_EXCEPTION_CASHBASIS_CURRENT_AMOUNT_FOREIGN"),
        GTM_EXCEPTION_TXDETAILS_CURRENT_AMOUNT_FOREIGN("GTM_EXCEPTION_TXDETAILS_CURRENT_AMOUNT_FOREIGN"),
        GTM_EXCEPTION_EXCHANGE_RATE("GTM_EXCEPTION_EXCHANGE_RATE"),
        GTM_EXCEPTION_CURRENCY_TYPE("GTM_EXCEPTION_CURRENCY_TYPE"),
        TXHDR_BILLNUM("TXHDR_BILLNUM"),
        BILL_AMOUNT("BILL_AMOUNT"),
        SUPPLIER_PAN("SUPPLIER_PAN"),
        TDS_SECTION("TDS_SECTION"),
        TDS_AMOUNT("TDS_AMOUNT"),
        TXHDR_BILLNUM_NEWARAP("TXHDR_BILLNUM_NEWARAP"),
        BILL_AMOUNT_NEWARAP("BILL_AMOUNT_NEWARAP"),
        TXHDR_BILLNUM_MIGR("TXHDR_BILLNUM_MIGR"),
        BILL_AMOUNT_MIGR("BILL_AMOUNT_MIGR"),
        TDS_ENTITY("TDS_ENTITY"),
        AVAILABLE_INPUT_CREDIT("AVAILABLE_INPUT_CREDIT"),
        TOTALTAX_AMOUNT("TOTALTAX_AMOUNT"),
        TAXABLE_AMOUNT("TAXABLE_AMOUNT"),
        TAX_UTILIZATION_BILL_ID("TAX_UTILIZATION_BILL_ID"),
        TOTAL_PURCHASE_AMOUNT("TOTAL_PURCHASE_AMOUNT"),
        SUBT_NAT_TAXAMOUNT_AMT("SUBT_NAT_TAXAMOUNT_AMT"),
        CONTACT_LAST_NAME("CONTACT_LAST_NAME"),
        CONTACT_FIRST_NAME("CONTACT_FIRST_NAME"),
        SUPPLIER_COMPANY_NAME("SUPPLIER_COMPANY_NAME"),
        SUPPLIER_FULL_NAME("SUPPLIER_FULL_NAME"),
        ABN_NUM("ABN_NUM"),
        TOTAL_AMOUNT("TOTAL_AMOUNT"),
        TOTAL_AMOUNT_HOME("TOTAL_AMOUNT_HOME"),
        TOTAL_GST("TOTAL_GST"),
        TOTAL_GST_HOME("TOTAL_GST_HOME"),
        NET_AMOUNT_WITH_GST("NET_AMOUNT_WITH_GST"),
        SEQUENCE("SEQUENCE"),
        ABATEMENT_RATE("ABATEMENT_RATE"),
        ABATEMENT_AMOUNT("ABATEMENT_AMOUNT"),
        ABATEMENT_TAX_AMOUNT("ABATEMENT_TAX_AMOUNT"),
        ABATEMENT_NET_AMOUNT("ABATEMENT_NET_AMOUNT"),
        ABATEMENT_NET_AMOUNT_HOME("ABATEMENT_NET_AMOUNT_HOME"),
        SERVICE_TYPE("SERVICE_TYPE"),
        REVERSE_CHARGE_TOTAL_AMOUNT("REVERSE_CHARGE_TOTAL_AMOUNT"),
        SERVICE_TYPE_ID("SERVICE_TYPE_ID"),
        REVERSE_CHARGE_TAXABLE_AMOUNT("REVERSE_CHARGE_TAXABLE_AMOUNT"),
        REVERSE_CHARGE_TOTAL_TAX("REVERSE_CHARGE_TOTAL_TAX"),
        REVERSE_CHARGE_TOTAL_AMOUNT_HOME("REVERSE_CHARGE_TOTAL_AMOUNT_HOME"),
        REVERSE_CHARGE_TAXABLE_AMOUNT_HOME("REVERSE_CHARGE_TAXABLE_AMOUNT_HOME"),
        REVERSE_CHARGE_TOTAL_TAX_HOME("REVERSE_CHARGE_TOTAL_TAX_HOME"),
        COUNTRY_CODE("COUNTRY_CODE"),
        CUSTOMER_VAT_NUM("CUSTOMER_VAT_NUM"),
        TOTAL_SALES_AMOUNT("TOTAL_SALES_AMOUNT"),
        TOTAL_SALES_HOME_AMOUNT("TOTAL_SALES_HOME_AMOUNT"),
        TAX_RATE_CODE("TAX_RATE_CODE"),
        SUBT_NAT_AMOUNT_NT("SUBT_NAT_AMOUNT_NT"),
        ASSET_VALUE_NT("ASSET_VALUE_NT"),
        RBAL_NAT_AMOUNT_NT("RBAL_NAT_AMOUNT_NT"),
        RBAL_NEG_AMOUNT_NT("RBAL_NEG_AMOUNT_NT"),
        RATE_INVENTORY("RATE_INVENTORY"),
        NAT_AMOUNT_NT("NAT_AMOUNT_NT"),
        DEBIT_NT("DEBIT_NT"),
        CREDIT_NT("CREDIT_NT"),
        JOURNAL_CODE_NAME("JOURNAL_CODE_NAME"),
        TXN_SUB_TYPE("TXN_SUB_TYPE"),
        UEN_NUMBER("UEN_NUMBER"),
        CUSTOMER_CST_NUM("CUSTOMER_CST_NUM"),
        CST_FORM_NUM("CST_FORM_NUM"),
        CST_FORM_TYPE("CST_FORM_TYPE"),
        TXDET_ABN_NUM("TXDET_ABN_NUM"),
        VEND_TIN_NUM("VEND_TIN_NUM"),
        GROSS_TOTAL("GROSS_TOTAL"),
        CUSTOMER_TIN("CUSTOMER_TIN"),
        CONTACT_CUSTOMER_STATE("CONTACT_CUSTOMER_STATE"),
        RBAL_NAT_AMOUNT_HOME_NT("RBAL_NAT_AMOUNT_HOME_NT"),
        SUBT_NAT_AMOUNT_HOME_NT("SUBT_NAT_AMOUNT_HOME_NT"),
        ITEM_SKU("ITEM_SKU"),
        ITEM_ITEM_SKU("ITEM_ITEM_SKU"),
        NOPOST_RECEIVED_QTY("NOPOST_RECEIVED_QTY"),
        NOPOST_REMAINING_QTY("NOPOST_REMAINING_QTY"),
        NOPOST_LINE_TOTAL_AMT("NOPOST_LINE_TOTAL_AMT"),
        NOPOST_RECEIVED_AMT("NOPOST_RECEIVED_AMT"),
        NOPOST_LINE_OPEN_BALANCE("NOPOST_LINE_OPEN_BALANCE"),
        PRODUCT_ID("PRODUCT_ID"),
        REORDER_POINT("REORDER_POINT"),
        PHYSICAL_INV_COUNT("PHYSICAL_INV_COUNT");

        private final String value;
        private static Map<String, CatalogColumn.LegacyColName> constants = new HashMap<String, CatalogColumn.LegacyColName>();

        static {
            for (CatalogColumn.LegacyColName c: values()) {
                constants.put(c.value, c);
            }
        }

        private LegacyColName(String value) {
            this.value = value;
        }

        @JsonValue
        @Override
        public String toString() {
            return this.value;
        }

        @JsonCreator
        public static CatalogColumn.LegacyColName fromValue(String value) {
            CatalogColumn.LegacyColName constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }
    @JsonProperty("evaluator")
    private Evaluator evaluator;

    @JsonProperty("colName")
    private String colName;
    @JsonProperty("colHeader")
    private String colHeader;

    /**
     * Reference id of the dbField to the data acquisition section for catalog defined column
     *
     */
    @JsonProperty("dbFieldRef")
    private String dbFieldRef;

    @JsonProperty("prefEvaluator")
    private String prefEvaluator;

    public String getPrefEvaluator() {
        return prefEvaluator;
    }

    public void setPrefEvaluator(String prefEvaluator) {
        this.prefEvaluator = prefEvaluator;
    }

    /**
     * Specify whether this column is pref controlled
     *
     */
    @JsonProperty("isPrefControlled")
    private Boolean isPrefControlled = new Boolean(false);
    @JsonProperty("isSortable")
    private Boolean isSortable  = new Boolean(false);;
    @JsonProperty("isRunningTotal")
    private Boolean isRunningTotal  = new Boolean(false);;
    @JsonProperty("subTotalType")
    private CatalogColumn.SubTotalType subTotalType;
    /**
     * Column index, -1 being column not selected
     *
     */
    @JsonProperty("colIndex")
    private Double colIndex;

    /**
     *
     * @return
     *     The colName
     */
    @JsonProperty("colName")
    public String getColName() {
        return colName;
    }

    /**
     *
     * @param colName
     *     The colName
     */
    @JsonProperty("colName")
    public void setColName(String colName) {
        this.colName = colName;
    }

    /**
     *
     * @return
     *     The colHeader
     */
    @JsonProperty("colHeader")
    public String getColHeader() {
        return colHeader;
    }

    /**
     *
     * @param colHeader
     *     The colHeader
     */
    @JsonProperty("colHeader")
    public void setColHeader(String colHeader) {
        this.colHeader = colHeader;
    }

    /**
     * Reference id of the dbField to the data acquisition section for catalog defined column
     *
     * @return
     *     The dbFieldRef
     */
    @JsonProperty("dbFieldRef")
    public String getDbFieldRef() {
        return dbFieldRef;
    }

    /**
     * Reference id of the dbField to the data acquisition section for catalog defined column
     *
     * @param dbFieldRef
     *     The dbFieldRef
     */
    @JsonProperty("dbFieldRef")
    public void setDbFieldRef(String dbFieldRef) {
        this.dbFieldRef = dbFieldRef;
    }

    /**
     * Specify whether this column is pref controlled
     *
     * @return
     *     The isPrefControlled
     */
    @JsonProperty("isPrefControlled")
    public Boolean getIsPrefControlled() {
        return isPrefControlled;
    }

    /**
     * Specify whether this column is pref controlled
     *
     * @param isPrefControlled
     *     The isPrefControlled
     */
    @JsonProperty("isPrefControlled")
    public void setIsPrefControlled(Boolean isPrefControlled) {
        this.isPrefControlled = isPrefControlled;
    }

    /**
     *
     * @return
     *     The isSortable
     */
    @JsonProperty("isSortable")
    public Boolean getIsSortable() {
        return isSortable;
    }

    /**
     *
     * @param isSortable
     *     The isSortable
     */
    @JsonProperty("isSortable")
    public void setIsSortable(Boolean isSortable) {
        this.isSortable = isSortable;
    }

    /**
     *
     * @return
     *     The isRunningTotal
     */
    @JsonProperty("isRunningTotal")
    public Boolean getIsRunningTotal() {
        return isRunningTotal;
    }

    /**
     *
     * @param isRunningTotal
     *     The isRunningTotal
     */
    @JsonProperty("isRunningTotal")
    public void setIsRunningTotal(Boolean isRunningTotal) {
        this.isRunningTotal = isRunningTotal;
    }

    /**
     *
     * @return
     *     The subTotalType
     */
    @JsonProperty("subTotalType")
    public CatalogColumn.SubTotalType getSubTotalType() {
        return subTotalType;
    }

    /**
     *
     * @param subTotalType
     *     The subTotalType
     */
    @JsonProperty("subTotalType")
    public void setSubTotalType(CatalogColumn.SubTotalType subTotalType) {
        this.subTotalType = subTotalType;
    }

    /**
     * Column index, -1 being column not selected
     *
     * @return
     *     The colIndex
     */
    @JsonProperty("colIndex")
    public Double getColIndex() {
        return colIndex;
    }

    /**
     * Column index, -1 being column not selected
     *
     * @param colIndex
     *     The colIndex
     */
    @JsonProperty("colIndex")
    public void setColIndex(Double colIndex) {
        this.colIndex = colIndex;
    }

    @JsonProperty("evaluator")
    public Evaluator getEvaluator() {
        return evaluator;
    }

    @JsonProperty("evaluator")
    public void setEvaluator(Evaluator evaluator) {
        this.evaluator = evaluator;
    }

    @Generated("org.jsonschema2pojo")
    public static enum SubTotalType {

        SUBTOTALTYPE_MONEY("SUBTOTALTYPE_MONEY"),
        SUBTOTALTYPE_QUANTITY("SUBTOTALTYPE_QUANTITY"),
        SUBTOTALTYPE_DURATION("SUBTOTALTYPE_DURATION");
        private final String value;
        private static Map<String, CatalogColumn.SubTotalType> constants = new HashMap<String, CatalogColumn.SubTotalType>();

        static {
            for (CatalogColumn.SubTotalType c: values()) {
                constants.put(c.value, c);
            }
        }

        private SubTotalType(String value) {
            this.value = value;
        }

        @JsonValue
        @Override
        public String toString() {
            return this.value;
        }

        @JsonCreator
        public static CatalogColumn.SubTotalType fromValue(String value) {
            CatalogColumn.SubTotalType constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }



}