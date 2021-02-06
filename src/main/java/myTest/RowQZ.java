package myTest;

import java.io.Serializable;
import java.util.HashMap;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "fieldIdentifier",
        "fieldType",
        "rowQZMap"
})

/**
 *
 * @author ndevadas
 *
 * QBO-199292: Row specific QZ for RCL Detail Reports
 *
 * Example Catalog SubToken: GST_GSTR1_ZERO_TAX_TRIAL
 * https://console-qa.mcs.intuit.com/type/text/metadata-customreport/67002c83-e78a-47a8-b3a8-c662cafbb630?navigate=project
 *
 */
public class RowQZ implements Serializable {

    @JsonProperty("fieldIdentifier")
    private String fieldIdentifier;

    @JsonProperty("fieldType")
    private String fieldType;

    @JsonProperty("rowQZMap")
    private HashMap<Object, String> rowQZMap;



    /**
     * Field based on which unique QZUrl is identified
     *
     * @return
     *     The fieldIdentifier
     */
    @JsonProperty("fieldIdentifier")
    public String getFieldIdentifier() {
        return fieldIdentifier;
    }


    /**
     * Field based on which unique QZUrl is identified
     *
     * @param fieldIdentifier
     *     The fieldIdentifier
     */
    @JsonProperty("fieldIdentifier")
    public void setFieldIdentifier(String fieldIdentifier) {
        this.fieldIdentifier = fieldIdentifier;
    }


    /**
     * The type of FieldIdentifier (LEGACY OR CATALOGDEFINED)
     *
     * @return
     * 		The fieldType
     */
    public String getFieldType() {
        return fieldType;
    }

    /**
     * The type of FieldIdentifier (LEGACY OR CATALOGDEFINED)
     *
     * @param fieldType
     * 		The fieldType
     */
    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }


    /**
     * Mapping between field values and QZUrls
     * @return
     * 		The rowQZMap
     */
    public HashMap<Object, String> getRowQZMap() {
        return rowQZMap;
    }


    /**
     *  Mapping between field values and QZUrls
     *
     * @param rowQZMap
     */
    public void setRowQZMap(HashMap<Object, String> rowQZMap) {
        this.rowQZMap = rowQZMap;
    }


}