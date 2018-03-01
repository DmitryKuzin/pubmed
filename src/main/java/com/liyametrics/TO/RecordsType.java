package com.liyametrics.TO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "recordsType", propOrder = {
        "record"
})
public class RecordsType {

    protected List<RecordType> record;

    public List<RecordType> getRecord() {
        return record;
    }

    public void setRecord(List<RecordType> record) {
        this.record = record;
    }
}
