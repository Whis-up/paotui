package com.xiaozhao.paotui.intf.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.ArrayList;
import java.util.List;
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum PaotuiTimeTypeEnum {
    All(0, "全部", 0),
    In30Min(1, "半小时之内", 30),
    In2Hours(2, "2小时之内", 120),
    In5Hours(3, "5小时之内", 300),
    InADay(4, "一天之内", 1440);



    private int id;
    private String name;
    private int minute;

    PaotuiTimeTypeEnum(int id, String name, int minute){
        this.id = id;
        this.name = name;
        this.minute = minute;
    }
    public static PaotuiTimeTypeEnum getById(int id){
        for (PaotuiTimeTypeEnum timeTypeEnum : PaotuiTimeTypeEnum.values()
        ) {
            if (timeTypeEnum.id == id){
                return timeTypeEnum;
            }
        }
        return null;
    }
    public static List<PaotuiTimeTypeEnum> getAllTye(){
        List<PaotuiTimeTypeEnum> timeTypeEnums = new ArrayList<>();
        for (PaotuiTimeTypeEnum timeTypeEnum : PaotuiTimeTypeEnum.values()
        ) {
            timeTypeEnums.add(timeTypeEnum);
        }
        return timeTypeEnums;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

}
