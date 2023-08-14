package com.xiaozhao.paotui.intf.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.ArrayList;
import java.util.List;
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum PaotuiGoodsTypeEnum {
    All(0,"不限"),
    Yifu(1,"衣服"),
    Shipin(2,"食品"),
    Shuji(3,"书籍"),
    Shenghuoyongpin(4,"生活用品"),
    Qita(5,"其他");

    private int id;
    private String name;

    PaotuiGoodsTypeEnum(int id, String name){
        this.id = id;
        this.name = name;
    }

    public static PaotuiGoodsTypeEnum getById(int id){
        for (PaotuiGoodsTypeEnum typeEnum : PaotuiGoodsTypeEnum.values()
             ) {
            if (typeEnum.id == id){
                return typeEnum;
            }
        }
        return null;
    }
    public static List<PaotuiGoodsTypeEnum> getAllTye(){
        List<PaotuiGoodsTypeEnum> typeEnums = new ArrayList<>();
        for (PaotuiGoodsTypeEnum typeEnum : PaotuiGoodsTypeEnum.values()
        ) {
            typeEnums.add(typeEnum);
        }
        return typeEnums;

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
}
