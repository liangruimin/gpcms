package cn.gree.zz.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.gree.zz.base.DaoSupportImpl;
import cn.gree.zz.bean.Plan;
import cn.gree.zz.service.PlanService;

@Service
@Transactional
public class PlanServiceImpl extends DaoSupportImpl<Plan> implements PlanService {

}
