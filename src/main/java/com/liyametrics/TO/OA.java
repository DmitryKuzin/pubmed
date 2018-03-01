package com.liyametrics.TO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "actionResultType", propOrder = {
        "responseDate",
        "request",
        "records"
})
public class OA {

    @XmlElement(required = true)
    protected String responseDate;

    @XmlElement(required = true)
    protected RequestType request;

    @XmlElement(required = true)
    protected RecordsType records;


    public String getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(String responseDate) {
        this.responseDate = responseDate;
    }

    public RequestType getRequest() {
        return request;
    }

    public void setRequest(RequestType request) {
        this.request = request;
    }

    public RecordsType getRecords() {
        return records;
    }

    public void setRecords(RecordsType records) {
        this.records = records;
    }
}
