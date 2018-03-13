package com.liyametrics.TO.fetch;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "OA")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {
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
