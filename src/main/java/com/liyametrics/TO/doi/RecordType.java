package com.liyametrics.TO.doi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "record")
@XmlAccessorType(XmlAccessType.FIELD)
public class RecordType {

    @XmlAttribute(name = "requested-id")
    protected String requestedId;

    @XmlAttribute(name = "pmcid")
    protected String pmcid;

    @XmlAttribute(name = "pmid")
    protected String pmid;

    @XmlAttribute(name = "doi")
    protected String doi;

    public String getRequestedId() {
        return requestedId;
    }

    public String getPmcid() {
        return pmcid;
    }

    public String getPmid() {
        return pmid;
    }

    public String getDoi() {
        return doi;
    }
}
