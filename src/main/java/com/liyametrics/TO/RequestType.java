package com.liyametrics.TO;

import javax.xml.bind.annotation.*;

@XmlRootElement(name="request")
@XmlAccessorType(XmlAccessType.FIELD)
public class RequestType {

    @XmlAttribute(name = "from")
    protected String fromDate;

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }
}
