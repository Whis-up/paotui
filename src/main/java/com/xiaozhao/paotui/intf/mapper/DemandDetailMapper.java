package com.xiaozhao.paotui.intf.mapper;


import com.xiaozhao.paotui.intf.entity.DemandDetail;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DemandDetailMapper{

    /**
     * 根据id查询详情
     * @param id
     * @return
     */
    @Select("select * from paotui_info_detail where id = #{id}")
    DemandDetail getDetailById(int id);
    @Insert("insert into paotui_info_detail (user_id, pickup_address_id, delivery_address_id, pickup_address, delivery_address," +
            " college_location_id, status, goods_type, goods_weight, goods_num, goods_info, time_type, expect_time, amount, fee, total_amount,add_time, " +
            "update_time, user_ip) VALUES (#{userId}, #{pickupAddressId}, #{deliveryAddressId}, #{pickupAddress}, #{deliveryAddress}," +
            "#{collegeLocationId}, #{status}, #{goodsType}, #{goodsWeight}, #{goodsNum}, #{goodsInfo}, #{timeType}, now(), #{amount}, #{fee},#{totalAmount}, now()," +
            " now(), #{userIp})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    int insert(DemandDetail demandDetail);

    /**
     * 根据不同条件展示已经发布的需求
     * @param goodsNum
     * @param goodsType
     * @param goodsWeight
     * @param timeType
     * @param size
     * @param pageId
     * @return
     */
    List<DemandDetail> loadDemandListByPage(int goodsNum,int goodsType, int goodsWeight,int timeType, int size, int pageId);

    /**
     *利用乐观锁思想，在需求详情表里添加version字段，在查询出来的demandDetail中的version值跟语句中条件一致时，需求未被抢单，此时抢单成功
     *
     * @param demandDetail
     * @return
     */
    @Update("update paotui_info_detail set status = #{status}, update_time = now(), version = version + 1 where id = #{id} " +
            "and version = #{version}")
    public int updateStatus(DemandDetail demandDetail);




}