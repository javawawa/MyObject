package com.jeecg.ljcj.service.impl;

import com.jeecg.ljcj.service.LjCmsSensitiveServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("ljCmsSensitiveService")
@Transactional
public class LjCmsSensitiveServiceImpl extends CommonServiceImpl implements LjCmsSensitiveServiceI {
}
