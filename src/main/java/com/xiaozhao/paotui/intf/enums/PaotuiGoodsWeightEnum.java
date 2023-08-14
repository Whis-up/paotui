package com.xiaozhao.paotui.intf.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum PaotuiGoodsWeightEnum {
    All(0,"不限", new BigDecimal(0)),
    Light(1,"3斤以下", new BigDecimal(3)),
    Medium(2,"3-10斤", new BigDecimal(5)),
    Heavy(3,"10斤以上", new BigDecimal(10));

    private int id;
    private String name;
    private BigDecimal amount;

    PaotuiGoodsWeightEnum(int id, String name, BigDecimal amount){
        this.id = id;
        this.name = name;
        this.amount = amount;

    }

    public static PaotuiGoodsWeightEnum getById(int id){
        for (PaotuiGoodsWeightEnum weightEnum : PaotuiGoodsWeightEnum.values()
             ) {
            if (weightEnum.id == id){
                return weightEnum;
            }
        }
        return null;
    }
    public static List<PaotuiGoodsWeightEnum> getAllTye(){
        List<PaotuiGoodsWeightEnum> weightEnums = new ArrayList<>();
        for (PaotuiGoodsWeightEnum weightEnum : PaotuiGoodsWeightEnum.values()
        ) {
            weightEnums.add(weightEnum);
        }
        return weightEnums;

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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
