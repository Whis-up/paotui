package com.xiaozhao.paotui.controller.detail;


import com.xiaozhao.paotui.controller.BaseController;
import com.xiaozhao.paotui.intf.constant.PageSizeConstant;
import com.xiaozhao.paotui.intf.demand.DO.ResponseDO;
import com.xiaozhao.paotui.intf.demand.DemandDetailService;
import com.xiaozhao.paotui.intf.dto.DemandListShowDO;
import com.xiaozhao.paotui.intf.entity.DemandDetail;
import com.xiaozhao.paotui.intf.enums.PaotuiGoodsNumEnum;
import com.xiaozhao.paotui.intf.enums.PaotuiGoodsTypeEnum;
import com.xiaozhao.paotui.intf.enums.PaotuiGoodsWeightEnum;
import com.xiaozhao.paotui.intf.enums.PaotuiTimeTypeEnum;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;

@RestController
@Slf4j
@RequestMapping("/demand")
public class DemandDetailController extends BaseController {


    @Autowired
    private DemandDetailService demandDetailService;

    /**
     * 发布需求
     * @param demandDetail 需求信息
     * @return
     */
    @RequestMapping
    public ResponseDO insert(@RequestBody DemandDetail demandDetail){
        //log.info("需求信息 {}",demandDetail);
        PaotuiGoodsNumEnum goodsNumEnum = PaotuiGoodsNumEnum.getById(demandDetail.getGoodsNum());
        PaotuiGoodsWeightEnum goodsWeightEnum = PaotuiGoodsWeightEnum.getById(demandDetail.getGoodsWeight());
        PaotuiTimeTypeEnum timeTypeEnum = PaotuiTimeTypeEnum.getById(demandDetail.getTimeType());
        //计算跑腿费
        BigDecimal amount = demandDetailService.getAmount(goodsNumEnum,goodsWeightEnum,timeTypeEnum);
        BigDecimal totalAmount = amount.add(demandDetail.getFee());
        demandDetail.setAmount(amount);
        demandDetail.setTotalAmount(totalAmount);
        demandDetailService.insert(demandDetail);
        return new ResponseDO(true,SUCCESS,null);
//        int i = demandDetailService.insert(demandDetail);
//        if (i == 1){
//            return new ResponseDO(true,"success",null);
//        }
//        else return new ResponseDO(false,"fail",null);
    }

    /**
     *根据不同条件筛选能够下单的需求信息
     * @param goodsNum 物品数量
     * @param goodsType 物品种类
     * @param goodsWeight 物品重量
     * @param timeType 时间类型
     * @param pageId 页码
     * @return
     */
    @GetMapping("/demandList")
    public ResponseDO loadDemandList(int goodsNum, int goodsType, int goodsWeight, int timeType, int pageId){
        //log.info("条件信息 {}",goodsNum);
        if (pageId < 0){
            return new ResponseDO(false,FAIL,"参数错误");
        }
        //判断相关参数是否合法,第二期每个枚举类型新增All(0,"不限")
        String result = verifyParams(goodsNum,goodsType,goodsWeight,timeType);
        if (!StringUtils.equals(result,SUCCESS)){
            return new ResponseDO(false,result,null);
        }
        //获取对应枚举值
        PaotuiGoodsNumEnum goodsNumEnum = PaotuiGoodsNumEnum.getById(goodsNum);
        PaotuiGoodsTypeEnum goodsTypeEnum = PaotuiGoodsTypeEnum.getById(goodsType);
        PaotuiGoodsWeightEnum goodsWeightEnum = PaotuiGoodsWeightEnum.getById(goodsWeight);
        PaotuiTimeTypeEnum timeTypeEnum = PaotuiTimeTypeEnum.getById(timeType);

        DemandListShowDO demandListShowDO = demandDetailService.loadDemandListByPage(goodsNumEnum,goodsTypeEnum,
                goodsWeightEnum,timeTypeEnum, PageSizeConstant.PAGE_SIZE,pageId);

        HashMap<Object, Object> map = new HashMap<>();
        map.put("demandListDO", demandListShowDO);
        return new ResponseDO(true,SUCCESS,map);
    }

    /**
     * 参数验证
     * @param goodsNum
     * @param goodsType
     * @param goodsWeight
     * @param timeType
     * @return
     */
    private String verifyParams(int goodsNum, int goodsType, int goodsWeight, int timeType){
        if (goodsNum != 0 && goodsNum != 1 && goodsNum != 2 && goodsNum != 3 ){
            return "错误的物品数量";
        }
        if (goodsType != 0 &&goodsType != 1 && goodsType != 2 && goodsType != 3 && goodsType != 4 && goodsType != 5){
            return "错误的物品类型";
        }
        if (goodsWeight != 0 &&goodsWeight != 1 && goodsWeight != 2 && goodsWeight != 3){
            return "错误的物品重量";
        }
        if (timeType != 0 && timeType != 1 && timeType != 2 && timeType != 3 && timeType != 4){
            return "错误的时间类型";
        }
        return SUCCESS;
    }
}
