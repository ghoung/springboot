package com.ks.sharding.pojo;


import com.baomidou.mybatisplus.annotations.TableName;
import org.iemans.backend.pojo.table.BaseTable;

@TableName(value = "test")
public class TestTable extends BaseTable {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TestTable{" +
                "name='" + name + '\'' +
                '}';
    }
}
