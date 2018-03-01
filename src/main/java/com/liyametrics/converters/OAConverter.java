package com.liyametrics.converters;

import com.liyametrics.TO.OA;
import com.liyametrics.TO.RecordType;
import com.liyametrics.domain.Record;

import java.util.ArrayList;
import java.util.List;

public class OAConverter {

    public static List<Record> convertToDO(OA response){

        List<RecordType> record = response.getRecords().getRecord();

        List<Record> result = new ArrayList<>();

        record.forEach(r-> {
            Record rec = new Record(r.getId(),r.getLink().getHref(),"",r.getCitation() );

            result.add(rec);
        });

        return result;
    }
}
