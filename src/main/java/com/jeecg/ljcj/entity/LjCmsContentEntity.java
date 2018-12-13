package com.jeecg.ljcj.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**   
 * @Title: Entity
 * @Description: 内容管理
 * @author zhangdaihao
 * @date 2018-11-12 16:55:39
 * @version V1.0   
 *
 */
@Entity
@Table(name = "lj_cms_content", schema = "")
@SuppressWarnings("serial")
public class LjCmsContentEntity implements java.io.Serializable {
	/**id*/
	private Integer id;
	/**所属频道*/
	private Integer catId;
	/**contentTitle*/
	private String contentTitle;
	/**listTile*/
	private String listTile;
	/**picLink*/
	private String picLink;
	/**listStyle*/
	private String listStyle;
	/**contentAbstract*/
	private String contentAbstract;
	/**contentLabel*/
	private String contentLabel;
	/**内容作者*/
	private Integer author;
	/**contentSource*/
	private String contentSource;
	/**contentSourceUrl*/
	private String contentSourceUrl;
	/**contentType*/
	private String contentType;
	/**contentHtml*/
	private String contentHtml;
	/**contentTemplate*/
	private String contentTemplate;
	/**status*/
	private String status;
	/**发布渠道 1全部（默认），2web/H5，3app*/
	private Integer releaseChannel;
	/**releaseDate*/
	private Date releaseDate;
	/**频道推荐*/
	private Integer sort;
	/**全部推荐*/
	private Integer pushTop;
	/**点击数*/
	private Integer stat;
	/**点赞数*/
	private Integer thumbup;
	/**createBy*/
	private String createBy;
	/**updateBy*/
	private String updateBy;
	/**deleted*/
	private Integer deleted;
	/**createAt*/
	private Date createAt;
	/**updateAt*/
	private Date updateAt;
	private Date publishTime;
	private String auditMessage;
	private String authorName;

	@Transient
	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
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
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  所属频道
	 */
	@Column(name ="CAT_ID",nullable=true,precision=10,scale=0)
	public Integer getCatId(){
		return this.catId;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  所属频道
	 */
	public void setCatId(Integer catId){
		this.catId = catId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  contentTitle
	 */
	@Column(name ="CONTENT_TITLE",nullable=true,length=100)
	public String getContentTitle(){
		return this.contentTitle;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  contentTitle
	 */
	public void setContentTitle(String contentTitle){
		this.contentTitle = contentTitle;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  listTile
	 */
	@Column(name ="LIST_TILE",nullable=true,length=255)
	public String getListTile(){
		return this.listTile;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  listTile
	 */
	public void setListTile(String listTile){
		this.listTile = listTile;
	}
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
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  picLink
	 */
	@Column(name ="PIC_LINK",nullable=true,length=255)
	public String getPicLink(){
		return this.picLink;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  picLink
	 */
	public void setPicLink(String picLink){
		this.picLink = picLink;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  listStyle
	 */
	@Column(name ="LIST_STYLE",nullable=true,length=1)
	public String getListStyle(){
		return this.listStyle;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  listStyle
	 */
	public void setListStyle(String listStyle){
		this.listStyle = listStyle;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  contentAbstract
	 */
	@Column(name ="CONTENT_ABSTRACT",nullable=true,length=500)
	public String getContentAbstract(){
		return this.contentAbstract;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  contentAbstract
	 */
	public void setContentAbstract(String contentAbstract){
		this.contentAbstract = contentAbstract;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  contentLabel
	 */
	@Column(name ="CONTENT_LABEL",nullable=true,length=128)
	public String getContentLabel(){
		return this.contentLabel;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  contentLabel
	 */
	public void setContentLabel(String contentLabel){
		this.contentLabel = contentLabel;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  内容作者
	 */
	@Column(name ="AUTHOR",nullable=false,precision=10,scale=0)
	public Integer getAuthor(){
		return this.author;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  内容作者
	 */
	public void setAuthor(Integer author){
		this.author = author;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  contentSource
	 */
	@Column(name ="CONTENT_SOURCE",nullable=true,length=255)
	public String getContentSource(){
		return this.contentSource;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  contentSource
	 */
	public void setContentSource(String contentSource){
		this.contentSource = contentSource;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  contentSourceUrl
	 */
	@Column(name ="CONTENT_SOURCE_URL",nullable=true,length=255)
	public String getContentSourceUrl(){
		return this.contentSourceUrl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  contentSourceUrl
	 */
	public void setContentSourceUrl(String contentSourceUrl){
		this.contentSourceUrl = contentSourceUrl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  contentType
	 */
	@Column(name ="CONTENT_TYPE",nullable=true,length=1)
	public String getContentType(){
		return this.contentType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  contentType
	 */
	public void setContentType(String contentType){
		this.contentType = contentType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  contentHtml
	 */
	@Column(name ="CONTENT_HTML",nullable=true)
	public String getContentHtml(){
		return this.contentHtml;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  contentHtml
	 */
	public void setContentHtml(String contentHtml){
		this.contentHtml = contentHtml;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  contentTemplate
	 */
	@Column(name ="CONTENT_TEMPLATE",nullable=true,length=1)
	public String getContentTemplate(){
		return this.contentTemplate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  contentTemplate
	 */
	public void setContentTemplate(String contentTemplate){
		this.contentTemplate = contentTemplate;
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
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  发布渠道 1全部（默认），2web/H5，3app
	 */
	@Column(name ="RELEASE_CHANNEL",nullable=false,precision=10,scale=0)
	public Integer getReleaseChannel(){
		return this.releaseChannel;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  发布渠道 1全部（默认），2web/H5，3app
	 */
	public void setReleaseChannel(Integer releaseChannel){
		this.releaseChannel = releaseChannel;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  releaseDate
	 */
	@Column(name ="RELEASE_DATE",nullable=true)
	public Date getReleaseDate(){
		return this.releaseDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  releaseDate
	 */
	public void setReleaseDate(Date releaseDate){
		this.releaseDate = releaseDate;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  频道推荐
	 */
	@Column(name ="SORT",nullable=false,precision=10,scale=0)
	public Integer getSort(){
		return this.sort;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  频道推荐
	 */
	public void setSort(Integer sort){
		this.sort = sort;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  全部推荐
	 */
	@Column(name ="PUSH_TOP",nullable=false,precision=10,scale=0)
	public Integer getPushTop(){
		return this.pushTop;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  全部推荐
	 */
	public void setPushTop(Integer pushTop){
		this.pushTop = pushTop;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  点击数
	 */
	@Column(name ="STAT",nullable=true,precision=10,scale=0)
	public Integer getStat(){
		return this.stat;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  点击数
	 */
	public void setStat(Integer stat){
		this.stat = stat;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  点赞数
	 */
	@Column(name ="THUMBUP",nullable=true,precision=10,scale=0)
	public Integer getThumbup(){
		return this.thumbup;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  点赞数
	 */
	public void setThumbup(Integer thumbup){
		this.thumbup = thumbup;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  createBy
	 */
	@Column(name ="CREATE_BY",nullable=true,precision=10,scale=0)
	public String getCreateBy(){
		return this.createBy;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  createBy
	 */
	public void setCreateBy(String createBy){
		this.createBy = createBy;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  updateBy
	 */
	@Column(name ="UPDATE_BY",nullable=true,precision=10,scale=0)
	public String getUpdateBy(){
		return this.updateBy;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  updateBy
	 */
	public void setUpdateBy(String updateBy){
		this.updateBy = updateBy;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  deleted
	 */
	@Column(name ="DELETED",nullable=false,precision=3,scale=0)
	public Integer getDeleted(){
		return this.deleted;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  deleted
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
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  publishTime
	 */
	@Column(name ="PUBLISH_TIME",nullable=false)
	public Date getPublishTime(){
		return this.publishTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  publishTimes
	 */
	public void setPublishTime(Date publishTime){
		this.publishTime = publishTime;
	}
}
