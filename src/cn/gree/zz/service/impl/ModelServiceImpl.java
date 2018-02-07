package cn.gree.zz.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.gree.zz.base.DaoSupportImpl;
import cn.gree.zz.domain.Model;
import cn.gree.zz.service.ModelService;
@Service
@Transactional
public class ModelServiceImpl extends DaoSupportImpl<Model> implements ModelService {

}
