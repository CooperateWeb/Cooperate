package com.cooperate.fly.bo;

public class DataValue {
    private Integer id;

    private Integer versionId;

    private Integer dataInfoId;

    private String value;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVersionId() {
        return versionId;
    }

    public void setVersionId(Integer versionId) {
        this.versionId = versionId;
    }

    public Integer getDataInfoId() {
        return dataInfoId;
    }

    public void setDataInfoId(Integer dataInfoId) {
        this.dataInfoId = dataInfoId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }
}