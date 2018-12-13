package com.jeecg.ljcj.service.impl;

import com.jeecg.ljcj.service.LjCmsContentServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("ljCmsContentService")
@Transactional
public class LjCmsContentServiceImpl extends CommonServiceImpl implements LjCmsContentServiceI {
	
}