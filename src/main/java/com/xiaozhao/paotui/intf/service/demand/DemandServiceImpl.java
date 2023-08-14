package com.xiaozhao.paotui.intf.service.demand;


import com.xiaozhao.paotui.intf.demand.DemandDetailService;
import com.xiaozhao.paotui.intf.dto.DemandListShowDO;
import com.xiaozhao.paotui.intf.entity.DemandDetail;
import com.xiaozhao.paotui.intf.enums.PaotuiGoodsNumEnum;
import com.xiaozhao.paotui.intf.enums.PaotuiGoodsTypeEnum;
import com.xiaozhao.paotui.intf.enums.PaotuiGoodsWeightEnum;
import com.xiaozhao.paotui.intf.enums.PaotuiTimeTypeEnum;
import com.xiaozhao.paotui.intf.mapper.DemandDetailMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

@Service
public class DemandServiceImpl implements DemandDetailService {
    @Resource
    private DemandDetailMapper demandDetailMapper;

    @Override
    public DemandDetail getDemandDetailById(int id) {
        return demandDetailMapper.getDetailById(id);
    }

    /**
     * 发布需求
     * @param demandDetail
     * @return
     */
    @Override
    public int insert(DemandDetail demandDetail) {
        return demandDetailMapper.insert(demandDetail);


    }

    /**
     * 根据枚举信息计算跑腿费用（不包含小费）
     * @param num 物品数量
     * @param weight 物品重量
     * @param time 预计送达时间范围
     * @return
     */
    @Override
    public BigDecimal getAmount(PaotuiGoodsNumEnum num, PaotuiGoodsWeightEnum weight, PaotuiTimeTypeEnum time) {
        BigDecimal amount;
        int goodsNum = num.getId();
        //判断物品重量
        if (weight.toString().equals("Light")) {
            amount = BigDecimal.valueOf(goodsNum* 3L);
        }
        else if (weight.toString().equals("Medium")){
            amount = BigDecimal.valueOf(goodsNum* 5L);
        }
        else {
            amount = BigDecimal.valueOf(goodsNum* 10L);
        }
        //判断时间区间,期望送达时间半小时内加2元，两小时内加1元，其余情况不计费
        if (StringUtils.equals("In30Min", String.valueOf(time))){
            amount = amount.add(BigDecimal.valueOf(2));
        } else if (StringUtils.equals("In2Hours", String.valueOf(time))) {
            amount = amount.add(BigDecimal.valueOf(1));
        }
         return amount;
        //amount = time.toString().equals("In30Min") ? amount = amount.add(BigDecimal.valueOf(2)) : time.toString().equals("In2Hours") ? amount = amount.add(BigDecimal.valueOf(1)) : amount;

    }

    /**
     * 根据不同条件筛选能够下单的需求信息
     * @param goodsNum
     * @param goodsType
     * @param goodsWeight
     * @param timeType
     * @param size
     * @param pageId
     * @return
     */
    @Override
    public DemandListShowDO loadDemandListByPage(PaotuiGoodsNumEnum goodsNum, PaotuiGoodsTypeEnum goodsType,
                                                 PaotuiGoodsWeightEnum goodsWeight, PaotuiTimeTypeEnum timeType,
                                                 int size, int pageId) {
        List<DemandDetail> demandDetailList =  demandDetailMapper.loadDemandListByPage(goodsNum.getId(),
                goodsType.getId(),goodsWeight.getId(),timeType.getId(),size,pageId);

        DemandListShowDO demandListShowDO = new DemandListShowDO();
        demandListShowDO.setDemandDetailsList(demandDetailList);

        return demandListShowDO;
    }

    /**
     * 抢单
     * @param demandDetail
     * @return
     */
    @Override
    public int updateDemandStatus(DemandDetail demandDetail) {
        return demandDetailMapper.updateStatus(demandDetail);
    }
}
