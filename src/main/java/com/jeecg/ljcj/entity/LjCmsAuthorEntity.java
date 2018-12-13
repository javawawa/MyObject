package com.jeecg.ljcj.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.web.system.pojo.base.TSDepart;

import javax.persistence.*;
import java.util.Date;

/**   
 * @Title: Entity
 * @Description: 作者管理
 * @author zhangdaihao
 * @date 2018-11-12 16:55:02
 * @version V1.0   
 *
 */
@Entity
@Table(name = "lj_cms_author", schema = "")

@SuppressWarnings("serial")
public class LjCmsAuthorEntity implements java.io.Serializable {
	/**id*/
	private Integer id;
	/**name*/
//	private String name;
//	/**avatar*/
//	private String avatar;
	/**type*/
	private String type;
	/**label*/
	private String label;
	/**文章数，默认0*/
	private Integer worksSum;
	/**阅读数，默认0*/
	private Integer clicksSum;
	/**status*/
	private String status;

	private Integer userId;

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

	/* 用户昵称 关联用户表*/
    private String nickName;
	private String trueName;
	private String isVerify;
	private String loginMobile;

	@Transient
	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	@Transient
	public String getIsVerify() {
		return isVerify;
	}

	public void setIsVerify(String isVerify) {
		this.isVerify = isVerify;
	}

	@Transient
	public String getLoginMobile() {
		return loginMobile;
	}

	public void setLoginMobile(String loginMobile) {
		this.loginMobile = loginMobile;
	}

	@Transient
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

//	@OneToOne(targetEntity=LjBaseUserEntity.class)
//	@JoinColumn(name="id")
//	private LjBaseUserEntity baseUser;
//
//	public LjBaseUserEntity getBaseUser() {
//		return this.baseUser;
//	}
//
//	public void setBaseUser(LjBaseUserEntity baseUser) {
//		this.baseUser = baseUser;
//	}

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

	@Column(name ="USER_ID",nullable=false,length=11)
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

//	/**
//	 *方法: 取得java.lang.String
//	 *@return: java.lang.String  name
//	 */
//	@Column(name ="NAME",nullable=true,length=128)
//	public String getName(){
//		return this.name;
//	}
//
//	/**
//	 *方法: 设置java.lang.String
//	 *@param: java.lang.String  name
//	 */
//	public void setName(String name){
//		this.name = name;
//	}
//	/**
//	 *方法: 取得java.lang.String
//	 *@return: java.lang.String  avatar
//	 */
//	@Column(name ="AVATAR",nullable=true,length=255)
//	public String getAvatar(){
//		return this.avatar;
//	}
//
//	/**
//	 *方法: 设置java.lang.String
//	 *@param: java.lang.String  avatar
//	 */
//	public void setAvatar(String avatar){
//		this.avatar = avatar;
//	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  type
	 */
	@Column(name ="TYPE",nullable=true,length=1)
	public String getType(){
		return this.type;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  type
	 */
	public void setType(String type){
		this.type = type;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  label
	 */
	@Column(name ="LABEL",nullable=true,length=128)
	public String getLabel(){
		return this.label;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  label
	 */
	public void setLabel(String label){
		this.label = label;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  文章数，默认0
	 */
	@Column(name ="WORKS_SUM",nullable=false,precision=10,scale=0)
	public Integer getWorksSum(){
		return this.worksSum;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  文章数，默认0
	 */
	public void setWorksSum(Integer worksSum){
		this.worksSum = worksSum;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  阅读数，默认0
	 */
	@Column(name ="CLICKS_SUM",nullable=false,precision=10,scale=0)
	public Integer getClicksSum(){
		return this.clicksSum;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  阅读数，默认0
	 */
	public void setClicksSum(Integer clicksSum){
		this.clicksSum = clicksSum;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  status
	 */
	@Column(name ="STATUS",nullable=true,length=2)
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
	@Column(name ="DELETED",nullable=false,precision=3,scale=0)
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
