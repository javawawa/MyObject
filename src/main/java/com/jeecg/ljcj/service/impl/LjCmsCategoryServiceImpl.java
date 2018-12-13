package com.jeecg.ljcj.service.impl;

import com.jeecg.ljcj.service.LjCmsCategoryServiceI;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("ljCmsCategoryService")
@Transactional
public class LjCmsCategoryServiceImpl extends CommonServiceImpl implements LjCmsCategoryServiceI {

}