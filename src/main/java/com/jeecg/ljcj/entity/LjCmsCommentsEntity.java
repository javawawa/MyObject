package com.jeecg.ljcj.entity;

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
@Table(name = "lj_cms_comments", schema = "")
@SuppressWarnings("serial")
public class LjCmsCommentsEntity implements java.io.Serializable {
	/**id*/
	private Integer id;

	/**user_nickname*/
	private String userNickname;

	/**评论的用户头像*/
	private String userHeadPic;

	/** 评论的用户id */
	private Integer userId;

	/**posts_id  回复的新闻id */
	private Integer postsId;

	private String postsTittle;
	/** 内容  */
	private String content;

	/**reply_id  回复的回复id */
	private Integer replyId;

	/** lou_id 楼层id */
	private Integer louId;

	/** reply_user_id 楼层id */
	private Integer replyUserId;

	/**reply_user_head_pic*/
//	private String replyUserHeadPic;

	/**deleted*/
	private Integer deleted;
	/**create_time*/
	private Date createTime;


	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  id
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name ="ID",nullable=false,length=11)
	public Integer getId(){
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
	 *@return: java.lang.Integer
	 */
	@Column(name ="USER_ID",nullable=true,precision=11,scale=0)
	public Integer getUserId(){
		return this.userId;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer
	 */
	public void setUserId(Integer userId){
		this.userId = userId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  contentTitle
	 */
	@Column(name ="USER_HEAD_PIC",nullable=true,length=200)
	public String getUserHeadPic(){
		return this.userHeadPic;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  contentTitle
	 */
	public void setUserHeadPic(String userHeadPic){
		this.userHeadPic = userHeadPic;
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
	 *@return: java.util.Date
	 */
	@Column(name ="CREATE_TIME",nullable=false)
	public Date getCreateTime(){
		return this.createTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date
	 */
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}

	@Column(name ="USER_NICKNAME",nullable=false)
	public String getUserNickname() {
		return userNickname;
	}

	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}

	@Column(name ="POSTS_ID",nullable=false)
	public Integer getPostsId() {
		return postsId;
	}

	public void setPostsId(Integer postsId) {
		this.postsId = postsId;
	}

	@Column(name ="CONTENT",nullable=false)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name ="REPLY_ID",nullable=false)
	public Integer getReplyId() {
		return replyId;
	}

	public void setReplyId(Integer replyId) {
		this.replyId = replyId;
	}

	@Column(name ="LOU_ID",nullable=false)
	public Integer getLouId() {
		return louId;
	}

	public void setLouId(Integer louId) {
		this.louId = louId;
	}

	@Column(name ="REPLY_USER_ID",nullable=false)
	public Integer getReplyUserId() {
		return replyUserId;
	}

	public void setReplyUserId(Integer replyUserId) {
		this.replyUserId = replyUserId;
	}

	@Column(name ="POSTS_TITLE",nullable=false)
	public String getPostsTittle() {
		return postsTittle;
	}

	public void setPostsTittle(String postsTittle) {
		this.postsTittle = postsTittle;
	}

//	@Column(name ="REPLY_USER_HEAD_PIC",nullable=false)
//	public String getReplyUserHeadPic() {
//		return replyUserHeadPic;
//	}
//
//	public void setReplyUserHeadPic(String replyUserHeadPic) {
//		this.replyUserHeadPic = replyUserHeadPic;
//	}
}
