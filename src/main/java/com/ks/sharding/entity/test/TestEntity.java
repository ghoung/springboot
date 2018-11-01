package com.ks.sharding.entity.test;

import org.iemans.backend.entity.base.BaseEntity;
import org.iemans.backend.util.Constant;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TestEntity extends BaseEntity {
    @NotNull(message = "名字不能为空")
    @Size(min = 1,max = 50 ,message = Constant.PARAMETER_ERROR)
    private  String name ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
