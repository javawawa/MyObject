package com.jeecg.ljcj.service;

import com.jeecg.ljcj.entity.LjCmsLiveEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface LjCmsLiveServiceI extends CommonService{
	
 	public void delete(LjCmsLiveEntity entity) throws Exception;
 	
 	public Serializable save(LjCmsLiveEntity entity) throws Exception;
 	
 	public void saveOrUpdate(LjCmsLiveEntity entity) throws Exception;
 	
}
