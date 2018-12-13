package com.jeecg.ljcj.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**   
 * @Title: Entity
 * @Description: 广告配置
 * @author zhangdaihao
 * @date 2018-11-12 16:54:24
 * @version V1.0   
 *
 */
@Entity
@Table(name = "lj_cms_advert", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class LjCmsAdvertEntity implements java.io.Serializable {
	/**id*/
	private Integer id;
	/**position*/
	private String position;
	/**targetType*/
	private String targetType;
	/**target*/
	private String target;
	/**title*/
	private String title;
	/**content*/
	private String content;
	/**pic*/
	private String pic;

	private String picMobile;
	/**点击量*/
	private Integer clickQantity;
	/**0:草稿,1:待审核,2:审核通过,3:审核不通过*/
	private Integer verifyStatus;
	/**排序*/
	private Integer sort;
	/**status*/
	private String status;
	/**createBy*/
	private String createBy;
	/**updateBy*/
	private String updateBy;
	/**是否删除（1删除/0可用）*/
	private Integer deleted;
	/**createAt*/
	private Date createAt;
	/**updateAt*/
	private Date updateAt;
	private String auditMessage;
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  auditMessage
	 */
	@Column(name ="AUDIT_MESSAGE",nullable=true,length=255)
	public String getAuditMessage(){
		return this.auditMessage;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  auditMessage
	 */
	public void setAuditMessage(String auditMessage){
		this.auditMessage = auditMessage;
	}
	
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  id
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name ="ID",nullable=false,length=11)
	public java.lang.Integer getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  id
	 */
	public void setId(Integer id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  position
	 */
	@Column(name ="POSITION",nullable=true,length=2)
	public String getPosition(){
		return this.position;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  position
	 */
	public void setPosition(String position){
		this.position = position;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  targetType
	 */
	@Column(name ="TARGET_TYPE",nullable=true,length=2)
	public String getTargetType(){
		return this.targetType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  targetType
	 */
	public void setTargetType(String targetType){
		this.targetType = targetType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  target
	 */
	@Column(name ="TARGET",nullable=true,length=1000)
	public String getTarget(){
		return this.target;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  target
	 */
	public void setTarget(String target){
		this.target = target;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  title
	 */
	@Column(name ="TITLE",nullable=true,length=255)
	public String getTitle(){
		return this.title;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  title
	 */
	public void setTitle(String title){
		this.title = title;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  content
	 */
	@Column(name ="CONTENT",nullable=true)
	public String getContent(){
		return this.content;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  content
	 */
	public void setContent(String content){
		this.content = content;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  pic
	 */
	@Column(name ="PIC",nullable=true,length=255)
	public String getPic(){
		return this.pic;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  pic
	 */
	public void setPic(String pic){
		this.pic = pic;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  pic
	 */
	@Column(name ="PIC_MOBILE",nullable=true,length=255)
	public String getPicMobile(){
		return this.picMobile;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  pic
	 */
	public void setPicMobile(String picMobile){
		this.picMobile = picMobile;
	}

	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  点击量
	 */
	@Column(name ="CLICK_QANTITY",nullable=true,precision=10,scale=0)
	public Integer getClickQantity(){
		return this.clickQantity;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  点击量
	 */
	public void setClickQantity(Integer clickQantity){
		this.clickQantity = clickQantity;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  0:草稿,1:待审核,2:审核通过,3:审核不通过
	 */
	@Column(name ="VERIFY_STATUS",nullable=true,precision=3,scale=0)
	public Integer getVerifyStatus(){
		return this.verifyStatus;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  0:草稿,1:待审核,2:审核通过,3:审核不通过
	 */
	public void setVerifyStatus(Integer verifyStatus){
		this.verifyStatus = verifyStatus;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  排序
	 */
	@Column(name ="SORT",nullable=true,precision=10,scale=0)
	public Integer getSort(){
		return this.sort;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  排序
	 */
	public void setSort(Integer sort){
		this.sort = sort;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  status
	 */
	@Column(name ="STATUS",nullable=true,length=1)
	public String getStatus(){
		return this.status;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  status
	 */
	public void setStatus(String status){
		this.status = status;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  createBy
	 */
	@Column(name ="CREATE_BY",nullable=true,length=32)
	public String getCreateBy(){
		return this.createBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  createBy
	 */
	public void setCreateBy(String createBy){
		this.createBy = createBy;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  updateBy
	 */
	@Column(name ="UPDATE_BY",nullable=true,length=32)
	public String getUpdateBy(){
		return this.updateBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  updateBy
	 */
	public void setUpdateBy(String updateBy){
		this.updateBy = updateBy;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  是否删除（1删除/0可用）
	 */
	@Column(name ="DELETED",nullable=true,precision=3,scale=0)
	public Integer getDeleted(){
		return this.deleted;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  是否删除（1删除/0可用）
	 */
	public void setDeleted(Integer deleted){
		this.deleted = deleted;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  createAt
	 */
	@Column(name ="CREATE_AT",nullable=false)
	public Date getCreateAt(){
		return this.createAt;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  createAt
	 */
	public void setCreateAt(Date createAt){
		this.createAt = createAt;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  updateAt
	 */
	@Column(name ="UPDATE_AT",nullable=false)
	public Date getUpdateAt(){
		return this.updateAt;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  updateAt
	 */
	public void setUpdateAt(Date updateAt){
		this.updateAt = updateAt;
	}
}
