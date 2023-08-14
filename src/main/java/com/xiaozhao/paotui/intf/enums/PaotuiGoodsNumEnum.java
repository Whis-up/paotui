package com.xiaozhao.paotui.intf.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.ArrayList;
import java.util.List;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum PaotuiGoodsNumEnum {

    All(0,"不限"),
    Num1(1,"2个"),
    Num2(2,"2个"),
    Num3more(3,"3个及3个以上");


    private int id;
    private String name;


    PaotuiGoodsNumEnum(int id, String name){
        this.id = id;
        this.name = name;
    }

    public static PaotuiGoodsNumEnum getById(int id){
        for (PaotuiGoodsNumEnum numEnum: PaotuiGoodsNumEnum.values()){
            if (numEnum.id == id){
                return numEnum;
            }
        }
        return null;
    }

    public static List<PaotuiGoodsNumEnum> getAllTye(){
    List<PaotuiGoodsNumEnum> numEnums = new ArrayList<>();
        for (PaotuiGoodsNumEnum numEnum : PaotuiGoodsNumEnum.values()
             ) {
            numEnums.add(numEnum);
        }
        return numEnums;

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
