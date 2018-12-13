package com.jeecg.ljcj.service;

import com.jeecg.ljcj.entity.LjCmsTagsEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface LjCmsTagsServiceI extends CommonService{
	
 	public void delete(LjCmsTagsEntity entity) throws Exception;
 	
 	public Serializable save(LjCmsTagsEntity entity) throws Exception;
 	
 	public void saveOrUpdate(LjCmsTagsEntity entity) throws Exception;
 	
}
