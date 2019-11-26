package com.mdtech.speecher.common.region;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.List;

@Converter(autoApply = true)
public class RegionArrayConverter implements AttributeConverter<List<Region>, String> {

    @Override
    public String convertToDatabaseColumn(List<Region> list) {
        return JSON.toJSONString(list);
    }

    @Override
    public List<Region> convertToEntityAttribute(String data) {
        try {
            return JSONArray.parseArray(data, Region.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
