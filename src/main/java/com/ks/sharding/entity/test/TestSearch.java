package com.ks.sharding.entity.test;

import org.iemans.backend.entity.base.BaseSearchPageEntity;

import java.util.Map;

public class TestSearch extends BaseSearchPageEntity {
    private String name;

    private Map<String,String>sorts;

    public Map<String, String> getSorts() {
        return sorts;
    }

    public void setSorts(Map<String, String> sorts) {
        this.sorts = sorts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
