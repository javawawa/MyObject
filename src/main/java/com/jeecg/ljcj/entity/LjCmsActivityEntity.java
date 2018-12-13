package com.jeecg.ljcj.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;

/**   
 * @Title: Entity
 * @Description: 活动管理表
 * @author zhangdaihao
 * @date 2018-11-12 16:53:50
 * @version V1.0   
 *
 */
@Entity
@Table(name = "lj_cms_activity", schema = "")
@SuppressWarnings("serial")
public class LjCmsActivityEntity implements java.io.Serializable {
	/**主键*/
	private Integer id;
	/**activityTitle*/
	private String activityTitle;
	/**activityPic*/
	private String activityPic;
	/**organizer*/
	private String organizer;
	/**activityType*/
	private String activityType;
	/**activityAddress*/
	private String activityAddress;
	/**activityHtml*/
	private String activityHtml;
	/**价格*/
	private Integer price;
	/**作者id*/
//	private Integer authorId;
	/**status*/
	private String status;
	/**数字大在前，默认0，不显示*/
	private Integer sort;
	/**默认0，*/
	private Integer stat;
	/**createBy*/
	private String createBy;
	/**updateBy*/
	private String updateBy;
	/**createAt*/
	private Date createAt;
	/**updateAt*/
	private Date updateAt;
	/**activityTime*/
	private Date activityTime;
	private Integer deleted;
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
	 *@return: java.lang.Integer  主键
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name ="ID",nullable=false,length=11)
	public java.lang.Integer getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  主键
	 */
	public void setId(Integer id){
		this.id = id;
	}

	@Column(name ="DELETED",nullable=false,length=1)
	public java.lang.Integer getDeleted(){
		return this.deleted;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  主键
	 */
	public void setDeleted(Integer deleted){
		this.deleted = deleted;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  activityTitle
	 */
	@Column(name ="ACTIVITY_TITLE",nullable=true,length=100)
	public String getActivityTitle(){
		return this.activityTitle;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  activityTitle
	 */
	public void setActivityTitle(String activityTitle){
		this.activityTitle = activityTitle;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  activityPic
	 */
	@Column(name ="ACTIVITY_PIC",nullable=true,length=255)
	public String getActivityPic(){
		return this.activityPic;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  activityPic
	 */
	public void setActivityPic(String activityPic){
		this.activityPic = activityPic;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  organizer
	 */
	@Column(name ="ORGANIZER",nullable=true,length=128)
	public String getOrganizer(){
		return this.organizer;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  organizer
	 */
	public void setOrganizer(String organizer){
		this.organizer = organizer;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  activityType
	 */
	@Column(name ="ACTIVITY_TYPE",nullable=true,length=1)
	public String getActivityType(){
		return this.activityType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  activityType
	 */
	public void setActivityType(String activityType){
		this.activityType = activityType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  activityAddress
	 */
	@Column(name ="ACTIVITY_ADDRESS",nullable=true,length=128)
	public String getActivityAddress(){
		return this.activityAddress;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  activityAddress
	 */
	public void setActivityAddress(String activityAddress){
		this.activityAddress = activityAddress;
	}
	/**
	 *方法: 取得java.lang.Object
	 *@return: java.lang.Object  activityHtml
	 */
	@Column(name ="ACTIVITY_HTML",nullable=true)
	public String getActivityHtml(){
		return this.activityHtml;
	}

	/**
	 *方法: 设置java.lang.Object
	 *@param: java.lang.Object  activityHtml
	 */
	public void setActivityHtml(String activityHtml){
		this.activityHtml = activityHtml;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  价格
	 */
	@Column(name ="PRICE",nullable=true,precision=10,scale=0)
	public Integer getPrice(){
		return this.price;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  价格
	 */
	public void setPrice(Integer price){
		this.price = price;
	}
//	/**
//	 *方法: 取得java.lang.Integer
//	 *@return: java.lang.Integer  作者id
//	 */
//	@Column(name ="AUTHOR_ID",nullable=true,precision=10,scale=0)
//	public Integer getAuthorId(){
//		return this.authorId;
//	}
//
//	/**
//	 *方法: 设置java.lang.Integer
//	 *@param: java.lang.Integer  作者id
//	 */
//	public void setAuthorId(Integer authorId){
//		this.authorId = authorId;
//	}
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
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  数字大在前，默认0，不显示
	 */
	@Column(name ="SORT",nullable=false,precision=10,scale=0)
	public Integer getSort(){
		return this.sort;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  数字大在前，默认0，不显示
	 */
	public void setSort(Integer sort){
		this.sort = sort;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  默认0，
	 */
	@Column(name ="STAT",nullable=false,precision=10,scale=0)
	public Integer getStat(){
		return this.stat;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  默认0，
	 */
	public void setStat(Integer stat){
		this.stat = stat;
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
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  activityTime
	 */
	@Column(name ="ACTIVITY_TIME",nullable=false)
	public Date getActivityTime(){
		return this.activityTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  activityTime
	 */
	public void setActivityTime(Date activityTime){
		this.activityTime = activityTime;
	}
}
