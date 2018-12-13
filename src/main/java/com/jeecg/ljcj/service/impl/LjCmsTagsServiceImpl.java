package com.jeecg.ljcj.service.impl;

import com.jeecg.ljcj.entity.LjCmsTagsEntity;
import com.jeecg.ljcj.service.LjCmsTagsServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Service("ljCmsTagsService")
@Transactional
public class LjCmsTagsServiceImpl extends CommonServiceImpl implements LjCmsTagsServiceI {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
 	public void delete(LjCmsTagsEntity entity) throws Exception{
 		super.delete(entity);
 	}
 	
 	public Serializable save(LjCmsTagsEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(LjCmsTagsEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 	}
 	
}