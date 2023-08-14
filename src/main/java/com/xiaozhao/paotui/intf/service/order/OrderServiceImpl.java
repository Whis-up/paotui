package com.xiaozhao.paotui.intf.service.order;

import com.xiaozhao.paotui.intf.demand.DemandDetailService;
import com.xiaozhao.paotui.intf.dto.DemandListShowDO;
import com.xiaozhao.paotui.intf.entity.DemandDetail;
import com.xiaozhao.paotui.intf.entity.Order;
import com.xiaozhao.paotui.intf.enums.PaotuiGoodsNumEnum;
import com.xiaozhao.paotui.intf.enums.PaotuiGoodsTypeEnum;
import com.xiaozhao.paotui.intf.enums.PaotuiGoodsWeightEnum;
import com.xiaozhao.paotui.intf.enums.PaotuiTimeTypeEnum;
import com.xiaozhao.paotui.intf.mapper.OrderMapper;
import com.xiaozhao.paotui.intf.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private DemandDetailService demandDetailService;



    @Override
    public int loadById(int id) {
        return orderMapper.loadById(id);
    }

    @Override
    public int grabOrder(Order order) {
        //获取当前登陆用户id
        int currentUserId = 123;
        order.setUserId(currentUserId);
        //根据当前的需求id查询需求详情
        DemandDetail demandDetail = demandDetailService.getDemandDetailById(order.getDemandId());
        //设置状态为2 已接单
        demandDetail.setStatus(2);
        //利用乐观锁思想，在需求详情表里添加version字段，在查询出来的demandDetail中的version值跟更新数据库语句中条件一致时，需求未被抢单，
        // 更新状态status成功
        demandDetailService.updateDemandStatus(demandDetail);
        return orderMapper.insert(order);
    }

    @Override
    public int update(Order order) {
        return orderMapper.update(order);
    }


}
