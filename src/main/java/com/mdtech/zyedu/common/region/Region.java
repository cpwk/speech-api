package com.mdtech.zyedu.common.region;

import javax.persistence.Convert;
import java.util.List;

public class Region {

    private String value;
    private String label;

    @Convert(converter = RegionArrayConverter.class)
    private List<Region> children;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<Region> getChildren() {
        return children;
    }

    public void setChildren(List<Region> children) {
        this.children = children;
    }
}
