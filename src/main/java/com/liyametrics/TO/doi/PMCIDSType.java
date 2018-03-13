package com.liyametrics.TO.doi;


import javax.xml.bind.annotation.*;

@XmlRootElement(name = "pmcids")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {
        "request",
        "record"
})
public class PMCIDSType {

    @XmlAttribute(name = "status")
    protected String status;

    @XmlElement(required = true)
    protected RequestType request;

    @XmlElement(required = true)
    protected RecordType record;

    public String getStatus() {
        return status;
    }

    public RequestType getRequest() {
        return request;
    }

    public RecordType getRecord() {
        return record;
    }
}
