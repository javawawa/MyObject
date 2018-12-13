package com.jeecg.ljcj.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**   
 * @Title: Entity
 * @Description: 频道管理
 * @author zhangdaihao
 * @date 2018-11-09 14:53:26
 * @version V1.0   
 *
 */
@Entity
@Table(name = "lj_cms_category", schema = "")
@SuppressWarnings("serial")
public class LjCmsCategoryEntity implements java.io.Serializable {
	/**主键自增*/
	private java.lang.Integer id;
	/**catName*/
	private java.lang.String catName;
	/**catCode*/
	private java.lang.String catCode;
	/**catTitle*/
	private java.lang.String catTitle;
	/**catDesc*/
	private java.lang.String catDesc;
	/**catPic*/
	private java.lang.String catPic;
	/**父频道ID*/
	private java.lang.Integer parentId;
	/**status*/
	private java.lang.String status;
	/**releaseChannel*/
	private java.lang.String releaseChannel;
	/**path*/
	private java.lang.String path;
	/**排序(数字大在前)*/
	private java.lang.Integer sort;
	/**depth*/
	private java.lang.String depth;
	/**级别*/
	private java.lang.Integer level;
	/**是否叶子结点*/
	private java.lang.Integer isLeaf;
	/**创建者id*/
	private java.lang.String createBy;
	/**修改者id*/
	private java.lang.String updateBy;
	/**是否已被删除*/
	private java.lang.Integer deleted;
	/**createAt*/
	private java.util.Date createAt;
	/**updateAt*/
	private java.util.Date updateAt;
	
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  主键自增
	 */
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name ="ID",nullable=false,length=10)
	public java.lang.Integer getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  主键自增
	 */
	public void setId(java.lang.Integer id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  catName
	 */
	@Column(name ="CAT_NAME",nullable=true,length=20)
	public java.lang.String getCatName(){
		return this.catName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  catName
	 */
	public void setCatName(java.lang.String catName){
		this.catName = catName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  catCode
	 */
	@Column(name ="CAT_CODE",nullable=true,length=10)
	public java.lang.String getCatCode(){
		return this.catCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  catCode
	 */
	public void setCatCode(java.lang.String catCode){
		this.catCode = catCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  catTitle
	 */
	@Column(name ="CAT_TITLE",nullable=true,length=100)
	public java.lang.String getCatTitle(){
		return this.catTitle;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  catTitle
	 */
	public void setCatTitle(java.lang.String catTitle){
		this.catTitle = catTitle;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  catDesc
	 */
	@Column(name ="CAT_DESC",nullable=true,length=255)
	public java.lang.String getCatDesc(){
		return this.catDesc;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  catDesc
	 */
	public void setCatDesc(java.lang.String catDesc){
		this.catDesc = catDesc;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  catPic
	 */
	@Column(name ="CAT_PIC",nullable=true,length=255)
	public java.lang.String getCatPic(){
		return this.catPic;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  catPic
	 */
	public void setCatPic(java.lang.String catPic){
		this.catPic = catPic;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  父频道ID
	 */
	@Column(name ="PARENT_ID",nullable=true,precision=10,scale=0)
	public java.lang.Integer getParentId(){
		return this.parentId;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  父频道ID
	 */
	public void setParentId(java.lang.Integer parentId){
		this.parentId = parentId;
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
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  releaseChannel
	 */
	@Column(name ="RELEASE_CHANNEL",nullable=true,length=1)
	public java.lang.String getReleaseChannel(){
		return this.releaseChannel;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  releaseChannel
	 */
	public void setReleaseChannel(java.lang.String releaseChannel){
		this.releaseChannel = releaseChannel;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  path
	 */
	@Column(name ="PATH",nullable=true,length=45)
	public java.lang.String getPath(){
		return this.path;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  path
	 */
	public void setPath(java.lang.String path){
		this.path = path;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  排序(数字大在前)
	 */
	@Column(name ="SORT",nullable=true,precision=10,scale=0)
	public java.lang.Integer getSort(){
		return this.sort;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  排序(数字大在前)
	 */
	public void setSort(java.lang.Integer sort){
		this.sort = sort;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  depth
	 */
	@Column(name ="DEPTH",nullable=true,length=255)
	public java.lang.String getDepth(){
		return this.depth;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  depth
	 */
	public void setDepth(java.lang.String depth){
		this.depth = depth;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  级别
	 */
	@Column(name ="LEVEL",nullable=true,precision=10,scale=0)
	public java.lang.Integer getLevel(){
		return this.level;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  级别
	 */
	public void setLevel(java.lang.Integer level){
		this.level = level;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  是否叶子结点
	 */
	@Column(name ="IS_LEAF",nullable=true,precision=3,scale=0)
	public java.lang.Integer getIsLeaf(){
		return this.isLeaf;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  是否叶子结点
	 */
	public void setIsLeaf(java.lang.Integer isLeaf){
		this.isLeaf = isLeaf;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  创建者id
	 */
	@Column(name ="CREATE_BY",nullable=true,precision=32,scale=0)
	public java.lang.String getCreateBy(){
		return this.createBy;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  创建者id
	 */
	public void setCreateBy(java.lang.String createBy){
		this.createBy = createBy;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  修改者id
	 */
	@Column(name ="UPDATE_BY",nullable=true,precision=32,scale=0)
	public java.lang.String getUpdateBy(){
		return this.updateBy;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  修改者id
	 */
	public void setUpdateBy(java.lang.String updateBy){
		this.updateBy = updateBy;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  是否已被删除
	 */
	@Column(name ="DELETED",nullable=true,precision=3,scale=0)
	public java.lang.Integer getDeleted(){
		return this.deleted;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  是否已被删除
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
	@Column(name ="UPDATE_AT",nullable=true)
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
