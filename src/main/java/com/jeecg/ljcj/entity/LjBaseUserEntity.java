package com.jeecg.ljcj.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * 用户管理表
 */
@Entity
@Table(name = "lj_base_user", schema = "")
@SuppressWarnings("serial")
public class LjBaseUserEntity implements java.io.Serializable {
	/**主键*/
	private Integer id;

	private String loginMobile;

	private String loginEmail;

	private String type;
	/**作者id*/
	private Integer authorId;
	/**status*/
	private String status;

	private String nickName;

	private String headPic;

	private Integer isVerify;

	private String sign;

	private String registFrom;
	private String trueName;
	private String idCode;
	private Integer grade;
	private Integer score;
	private Integer favoriteSum;
	private String wechatId;
	private String qqId;
	private String blogId;
	private String loginPassword;
	/**createBy*/
	private String createBy;
	/**updateBy*/
	private String updateBy;
	/**createAt*/
	private Date createAt;
	/**updateAt*/
	private Date updateAt;
	/**activityTime*/
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

//	@OneToOne(targetEntity=LjCmsAuthorEntity.class,mappedBy="baseUser")
//	@JoinColumn(name="id")
//	private LjCmsAuthorEntity cmsauthor;
//
//	public LjCmsAuthorEntity getCmsauthor() {
//		return cmsauthor;
//	}
//
//	public void setCmsauthor(LjCmsAuthorEntity cmsauthor) {
//		this.cmsauthor = cmsauthor;
//	}

	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  主键
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name ="ID",nullable=false,length=11)
	public Integer getId(){
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
	public Integer getDeleted(){
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
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  作者id
	 */
	@Column(name ="AUTHOR_ID",nullable=true,precision=10,scale=0)
	public Integer getAuthorId(){
		return this.authorId;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  作者id
	 */
	public void setAuthorId(Integer authorId){
		this.authorId = authorId;
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

	@Column(name ="LOGIN_MOBILE",nullable=false)
	public String getLoginMobile() {
		return loginMobile;
	}

	public void setLoginMobile(String loginMobile) {
		this.loginMobile = loginMobile;
	}

	@Column(name ="LOGIN_EMAIL",nullable=false)
	public String getLoginEmail() {
		return loginEmail;
	}

	public void setLoginEmail(String loginEmail) {
		this.loginEmail = loginEmail;
	}

	@Column(name ="TYPE",nullable=false)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name ="NICK_NAME",nullable=false)
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@Column(name ="HEAD_PIC",nullable=false)
	public String getHeadPic() {
		return headPic;
	}

	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}

	@Column(name ="IS_VERIFY",nullable=false)
	public Integer getIsVerify() {
		return isVerify;
	}

	public void setIsVerify(Integer isVerify) {
		this.isVerify = isVerify;
	}

	@Column(name ="SIGN",nullable=false)
	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	@Column(name ="REGIST_FROM",nullable=false)
	public String getRegistFrom() {
		return registFrom;
	}

	public void setRegistFrom(String registFrom) {
		this.registFrom = registFrom;
	}

	@Column(name ="TRUE_NAME",nullable=false)
	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	@Column(name ="ID_CODE",nullable=false)
	public String getIdCode() {
		return idCode;
	}

	public void setIdCode(String idCode) {
		this.idCode = idCode;
	}

	@Column(name ="GRADE",nullable=false)
	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	@Column(name ="SCORE",nullable=false)
	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	@Column(name ="FAVORITE_SUM",nullable=false)
	public Integer getFavoriteSum() {
		return favoriteSum;
	}

	public void setFavoriteSum(Integer favoriteSum) {
		this.favoriteSum = favoriteSum;
	}

	@Column(name ="WECHAT_ID",nullable=false)
	public String getWechatId() {
		return wechatId;
	}

	public void setWechatId(String wechatId) {
		this.wechatId = wechatId;
	}

	@Column(name ="QQ_ID",nullable=false)
	public String getQqId() {
		return qqId;
	}

	public void setQqId(String qqId) {
		this.qqId = qqId;
	}

	@Column(name ="BLOG_ID",nullable=false)
	public String getBlogId() {
		return blogId;
	}

	public void setBlogId(String blogId) {
		this.blogId = blogId;
	}

	@Column(name ="LOGIN_PASSWORD",nullable=false)
	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}
}
