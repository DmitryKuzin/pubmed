package com.liyametrics.TO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "requestType")
public class RequestType {

    @XmlAttribute(name = "from")
    protected String fromDate;

    public String getFromDate() {
        return fromDate;
    }
}
