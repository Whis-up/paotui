package com.xiaozhao.paotui.intf.dto;

import com.xiaozhao.paotui.intf.entity.DemandDetail;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
@Setter
@Getter
@ToString
public class DemandListShowDO {
    private List<DemandDetail> demandDetailsList;


}
