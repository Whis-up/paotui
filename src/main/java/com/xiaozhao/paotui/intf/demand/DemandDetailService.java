package com.xiaozhao.paotui.intf.demand;


import com.xiaozhao.paotui.intf.dto.DemandListShowDO;
import com.xiaozhao.paotui.intf.entity.DemandDetail;
import com.xiaozhao.paotui.intf.enums.PaotuiGoodsNumEnum;
import com.xiaozhao.paotui.intf.enums.PaotuiGoodsTypeEnum;
import com.xiaozhao.paotui.intf.enums.PaotuiGoodsWeightEnum;
import com.xiaozhao.paotui.intf.enums.PaotuiTimeTypeEnum;

import java.math.BigDecimal;

public interface DemandDetailService {


    DemandDetail getDemandDetailById(int id);

    int insert(DemandDetail demandDetail);

    /**
     * 根据相关需求计算跑腿费用amount
     * @param num 物品数量
     * @param weight 物品重量
     * @param time 预计送达时间范围
     * @return
     */
    BigDecimal getAmount(PaotuiGoodsNumEnum num, PaotuiGoodsWeightEnum weight, PaotuiTimeTypeEnum time);

    /**
     * 根据物品数量，物品类型，物品重量，送达时间等不同排序条件进行动态查询
     * @param goodsNum
     * @param goodsType
     * @param goodsWeight
     * @param timeType
     * @param size
     * @param pageId
     * @return
     */
    DemandListShowDO loadDemandListByPage(PaotuiGoodsNumEnum goodsNum,
                      PaotuiGoodsTypeEnum goodsType, PaotuiGoodsWeightEnum goodsWeight,
                      PaotuiTimeTypeEnum timeType, int size, int pageId);

    /**
     * 根据需求详情表的version字段判断需求是否已经被人抢单，version数据不相等代表抢单失败
     * @param demandDetail
     * @return
     */
    int updateDemandStatus(DemandDetail demandDetail);




}
