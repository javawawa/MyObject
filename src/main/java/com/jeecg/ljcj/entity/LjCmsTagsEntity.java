package com.jeecg.ljcj.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.lang.String;
import java.lang.Double;
import java.lang.Integer;
import java.math.BigDecimal;
import javax.xml.soap.Text;
import java.sql.Blob;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: lj_cms_tags
 * @author onlineGenerator
 * @date 2018-11-08 17:58:06
 * @version V1.0   
 *
 */
@Entity
@Table(name = "lj_cms_tags", schema = "")
@SuppressWarnings("serial")
public class LjCmsTagsEntity implements java.io.Serializable {
	/**id*/
	private java.lang.Integer id;
	/**标签名*/
	@Excel(name="标签名",width=15)
	private java.lang.String name;
	/**标签描述*/
	@Excel(name="标签描述",width=15)
	private java.lang.String description;
	/**标签类型*/
	@Excel(name="标签类型",width=15)
	private java.lang.String type;
	/**使用次数*/
	@Excel(name="使用次数",width=15)
	private java.lang.Integer stat;
	/**控制状态*/
	@Excel(name="控制状态",width=15)
	private java.lang.String status;
	/**createAt*/
	@Excel(name="createAt",width=15,format = "yyyy-MM-dd")
	private java.util.Date createAt;
	/**createBy*/
	@Excel(name="createBy",width=15)
	private java.lang.String createBy;
	/**updateAt*/
	@Excel(name="updateAt",width=15,format = "yyyy-MM-dd")
	private java.util.Date updateAt;
	/**updateBy*/
	@Excel(name="updateBy",width=15)
	private java.lang.String updateBy;
	/**deleted*/
	@Excel(name="deleted",width=15)
	private java.lang.Integer deleted;
	
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name ="ID",nullable=false,length=10)
	public java.lang.Integer getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  id
	 */
	public void setId(java.lang.Integer id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  标签名
	 */

	@Column(name ="NAME",nullable=false,length=20)
	public java.lang.String getName(){
		return this.name;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  标签名
	 */
	public void setName(java.lang.String name){
		this.name = name;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  标签描述
	 */

	@Column(name ="DESCRIPTION",nullable=true,length=255)
	public java.lang.String getDescription(){
		return this.description;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  标签描述
	 */
	public void setDescription(java.lang.String description){
		this.description = description;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  标签类型
	 */

	@Column(name ="TYPE",nullable=false,length=2)
	public java.lang.String getType(){
		return this.type;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  标签类型
	 */
	public void setType(java.lang.String type){
		this.type = type;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  使用次数
	 */

	@Column(name ="STAT",nullable=false,length=10)
	public java.lang.Integer getStat(){
		return this.stat;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  使用次数
	 */
	public void setStat(java.lang.Integer stat){
		this.stat = stat;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  控制状态
	 */

	@Column(name ="STATUS",nullable=true,length=1)
	public java.lang.String getStatus(){
		return this.status;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  控制状态
	 */
	public void setStatus(java.lang.String status){
		this.status = status;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  createAt
	 */

	@Column(name ="CREATE_AT",nullable=false)
	public java.util.Date getCreateAt(){
		return this.createAt;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  createAt
	 */
	public void setCreateAt(java.util.Date createAt){
		this.createAt = createAt;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  createBy
	 */

	@Column(name ="CREATE_BY",nullable=true,length=32)
	public java.lang.String getCreateBy(){
		return this.createBy;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  createBy
	 */
	public void setCreateBy(java.lang.String createBy){
		this.createBy = createBy;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  updateAt
	 */

	@Column(name ="UPDATE_AT",nullable=false)
	public java.util.Date getUpdateAt(){
		return this.updateAt;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  updateAt
	 */
	public void setUpdateAt(java.util.Date updateAt){
		this.updateAt = updateAt;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  updateBy
	 */

	@Column(name ="UPDATE_BY",nullable=true,length=32)
	public java.lang.String getUpdateBy(){
		return this.updateBy;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  updateBy
	 */
	public void setUpdateBy(java.lang.String updateBy){
		this.updateBy = updateBy;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  deleted
	 */

	@Column(name ="DELETED",nullable=true,length=3)
	public java.lang.Integer getDeleted(){
		return this.deleted;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  deleted
	 */
	public void setDeleted(java.lang.Integer deleted){
		this.deleted = deleted;
	}
}
