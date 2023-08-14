package com.xiaozhao.paotui.intf.mapper;


import com.xiaozhao.paotui.intf.entity.Order;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;



@Mapper
public interface OrderMapper {

    @Select("select * from paotui_order where id = #{id}")
    int loadById(int id);

    @Insert("insert into paotui_order (demand_id, user_id, amount, status, add_time, update_time) VALUES (#{demandId}," +
            "#{userId}, #{amount}, #{status}, now(), now())")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    int insert(Order order);
    int update(Order order);



}
