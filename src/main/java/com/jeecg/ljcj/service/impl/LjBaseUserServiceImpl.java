package com.jeecg.ljcj.service.impl;

import com.jeecg.ljcj.service.LjBaseUserServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("ljBaseUserService")
@Transactional
public class LjBaseUserServiceImpl extends CommonServiceImpl implements LjBaseUserServiceI {
}
