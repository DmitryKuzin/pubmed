package com.liyametrics.TO;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name="records")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"record"})
public class RecordsType {

    @XmlElement(name = "record")
    protected List<RecordType> record;

    public List<RecordType> getRecord() {

        if(record == null) {
            record = new ArrayList<RecordType>();
        }
        return this.record;
    }

    public void setRecord(List<RecordType> record) {
        this.record = record;
    }
}
