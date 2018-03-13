package com.liyametrics.TO.doi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "request")
@XmlAccessorType(XmlAccessType.FIELD)
public class RequestType {

    @XmlAttribute(name = "idtype")
    protected String idtype;

    @XmlAttribute(name = "pmcids")
    protected String pmcids;

    @XmlAttribute(name = "versions")
    protected String versions;

    @XmlAttribute(name = "showaiid")
    protected String showaiid;

    public String getIdtype() {
        return idtype;
    }

    public String getPmcids() {
        return pmcids;
    }

    public String getVersions() {
        return versions;
    }

    public String getShowaiid() {
        return showaiid;
    }
}
