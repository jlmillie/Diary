package com.on.diary.execldata;

/**
 * Created by Administrator on 2018/3/24.
 * execl表格数据
 */

public class ExeclBean {
    private int id;
    /**
     * 协会职务
     */
    private String duty;
    /**
     * 负责人
     */
    private String name;
    /**
     * 公司名称
     */
    private String commpany;
    /**
     * 联系电话
     */
    private String number;


    public ExeclBean() {
    }

    public ExeclBean(int id, String duty, String name, String commpany, String number) {
        super();
        this.id = id;
        this.duty = duty;
        this.name = name;
        this.commpany = commpany;
        this.number = number;
    }

    public ExeclBean(String duty, String name, String commpany, String number) {
        super();
        this.duty = duty;
        this.name = name;
        this.commpany = commpany;
        this.number = number;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCommpany() {
        return commpany;
    }

    public void setCommpany(String commpany) {
        this.commpany = commpany;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "ExeclBean{" +
                "id=" + id +
                ", duty='" + duty + '\'' +
                ", name='" + name + '\'' +
                ", commpany='" + commpany + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
