
package com.cy.customwidget.multilevel;

public class OptionBean {

    private String id;
    private java.util.List<OptionBean> list;
    private String name;
    private String remark;
    private Long status;
    private OptionBean mParent;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public java.util.List<OptionBean> getList() {
        return list;
    }

    public void setList(java.util.List<OptionBean> list) {
        this.list = list;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OptionBean{" +
                "id='" + id + '\'' +
                ", list=" + list +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                ", status=" + status +
                '}';
    }

    public OptionBean getParent() {
        return mParent;
    }

    public void setParent(final OptionBean parent) {
        mParent = parent;
    }
}
