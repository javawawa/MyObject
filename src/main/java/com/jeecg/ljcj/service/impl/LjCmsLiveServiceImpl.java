package com.jeecg.ljcj.service.impl;

import com.jeecg.ljcj.entity.LjCmsLiveEntity;
import com.jeecg.ljcj.service.LjCmsLiveServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Service("ljCmsLiveService")
@Transactional
public class LjCmsLiveServiceImpl extends CommonServiceImpl implements LjCmsLiveServiceI {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
 	public void delete(LjCmsLiveEntity entity) throws Exception{
 		super.delete(entity);
 	}
 	
 	public Serializable save(LjCmsLiveEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(LjCmsLiveEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 	}
 	
}