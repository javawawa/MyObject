package com.jeecg.ljcj.entity;

import org.jeecgframework.poi.excel.annotation.Excel;

import javax.persistence.*;

/**   
 * @Title: Entity
 * @Description: lj_cms_live
 * @author onlineGenerator
 * @date 2018-11-09 11:57:05
 * @version V1.0   
 *
 */
@Entity
@Table(name = "lj_cms_live", schema = "")
@SuppressWarnings("serial")
public class LjCmsLiveEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.Integer id;
	/**源数据id*/
	@Excel(name="源数据id",width=15)
	private java.lang.Integer sourceId;
	/**label*/
	@Excel(name="label",width=15)
	private java.lang.String label;
	/**title*/
	@Excel(name="title",width=15)
	private java.lang.String title;
	/**content*/
	@Excel(name="content",width=15)
	private java.lang.String content;
	/**source*/
	@Excel(name="source",width=15)
	private java.lang.String source;
	/**contentHtml*/
	@Excel(name="contentHtml",width=15)
	private java.lang.String contentHtml;
	/**isImportant*/
	@Excel(name="isImportant",width=15)
	private java.lang.String isImportant;
	/**isComment*/
	@Excel(name="isComment",width=15)
	private java.lang.String isComment;
	/**commentContent*/
	@Excel(name="commentContent",width=15)
	private java.lang.String commentContent;
	/**status*/
	@Excel(name="status",width=15)
	private java.lang.String status;
	/**点击数*/
	@Excel(name="点击数",width=15)
	private java.lang.Integer stat;
	/**liveTime*/
	@Excel(name="liveTime",width=15,format = "yyyy-MM-dd")
	private java.util.Date liveTime;
	/**创建人id*/
	@Excel(name="创建人id",width=32)
	private java.lang.String createBy;
	/**更新人id*/
	@Excel(name="更新人id",width=32)
	private java.lang.String updateBy;
	/**是否删除*/
	@Excel(name="是否删除",width=15)
	private java.lang.Integer deleted;
	/**createAt*/
	@Excel(name="createAt",width=15,format = "yyyy-MM-dd")
	private java.util.Date createAt;
	/**updateAt*/
	@Excel(name="updateAt",width=15,format = "yyyy-MM-dd")
	private java.util.Date updateAt;

	private java.lang.String shareLink;

	@Column(name ="SHARE_LINK",nullable=true,length=255)
	public java.lang.String getShareLink(){
		return this.shareLink;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  label
	 */
	public void setShareLink(java.lang.String shareLink){
		this.shareLink = shareLink;
	}

	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	@Column(name ="ID",nullable=false,length=10)
	public java.lang.Integer getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  主键
	 */
	public void setId(java.lang.Integer id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  源数据id
	 */

	@Column(name ="SOURCE_ID",nullable=true,length=10)
	public java.lang.Integer getSourceId(){
		return this.sourceId;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  源数据id
	 */
	public void setSourceId(java.lang.Integer sourceId){
		this.sourceId = sourceId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  label
	 */

	@Column(name ="LABEL",nullable=true,length=128)
	public java.lang.String getLabel(){
		return this.label;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  label
	 */
	public void setLabel(java.lang.String label){
		this.label = label;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  title
	 */

	@Column(name ="TITLE",nullable=true,length=200)
	public java.lang.String getTitle(){
		return this.title;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  title
	 */
	public void setTitle(java.lang.String title){
		this.title = title;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  content
	 */

	@Column(name ="CONTENT",nullable=true,length=2000)
	public java.lang.String getContent(){
		return this.content;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  content
	 */
	public void setContent(java.lang.String content){
		this.content = content;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  source
	 */

	@Column(name ="SOURCE",nullable=true,length=1)
	public java.lang.String getSource(){
		return this.source;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  source
	 */
	public void setSource(java.lang.String source){
		this.source = source;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  contentHtml
	 */

	@Column(name ="CONTENT_HTML",nullable=true)
	public java.lang.String getContentHtml(){
		return this.contentHtml;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  contentHtml
	 */
	public void setContentHtml(java.lang.String contentHtml){
		this.contentHtml = contentHtml;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  isImportant
	 */

	@Column(name ="IS_IMPORTANT",nullable=true,length=1)
	public java.lang.String getIsImportant(){
		return this.isImportant;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  isImportant
	 */
	public void setIsImportant(java.lang.String isImportant){
		this.isImportant = isImportant;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  isComment
	 */

	@Column(name ="IS_COMMENT",nullable=true,length=1)
	public java.lang.String getIsComment(){
		return this.isComment;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  isComment
	 */
	public void setIsComment(java.lang.String isComment){
		this.isComment = isComment;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  commentContent
	 */

	@Column(name ="COMMENT_CONTENT",nullable=true,length=2000)
	public java.lang.String getCommentContent(){
		return this.commentContent;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  commentContent
	 */
	public void setCommentContent(java.lang.String commentContent){
		this.commentContent = commentContent;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  status
	 */

	@Column(name ="STATUS",nullable=true,length=1)
	public java.lang.String getStatus(){
		return this.status;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  status
	 */
	public void setStatus(java.lang.String status){
		this.status = status;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  点击数
	 */

	@Column(name ="STAT",nullable=true,length=11)
	public java.lang.Integer getStat(){
		return this.stat;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  点击数
	 */
	public void setStat(java.lang.Integer stat){
		this.stat = stat;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  liveTime
	 */

	@Column(name ="LIVE_TIME",nullable=true)
	public java.util.Date getLiveTime(){
		return this.liveTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  liveTime
	 */
	public void setLiveTime(java.util.Date liveTime){
		this.liveTime = liveTime;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  创建人id
	 */

	@Column(name ="CREATE_BY",nullable=true,length=32)
	public java.lang.String getCreateBy(){
		return this.createBy;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  创建人id
	 */
	public void setCreateBy(java.lang.String createBy){
		this.createBy = createBy;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  更新人id
	 */

	@Column(name ="UPDATE_BY",nullable=true,length=132)
	public java.lang.String getUpdateBy(){
		return this.updateBy;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  更新人id
	 */
	public void setUpdateBy(java.lang.String updateBy){
		this.updateBy = updateBy==null?"0":updateBy;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  是否删除
	 */

	@Column(name ="DELETED",nullable=false,length=1)
	public java.lang.Integer getDeleted(){
		return this.deleted;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  是否删除
	 */
	public void setDeleted(java.lang.Integer deleted){
		this.deleted = deleted;
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
}
