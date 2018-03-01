package com.liyametrics.TO;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "recordType", propOrder = {
        "link"
})
public class RecordType {

    @XmlAttribute(name = "id")
    protected String id;

    @XmlAttribute(name = "citation")
    protected String citation;

    @XmlElement(name = "link", required = false)
    protected LinkType link;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCitation() {
        return citation;
    }

    public void setCitation(String citation) {
        this.citation = citation;
    }

    public LinkType getLink() {
        return link;
    }

    public void setLink(LinkType link) {
        this.link = link;
    }
}
