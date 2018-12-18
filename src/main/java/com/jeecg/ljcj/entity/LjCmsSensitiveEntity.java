package com.jeecg.ljcj.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;


@Entity
@Table(name = "lj_cms_sensitive", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
public class LjCmsSensitiveEntity implements java.io.Serializable {
    /**id*/
    private Integer id;
    /**敏感词名称*/
    private String sensitiveName;
    /**是否删除（1删除/0可用）*/
    private Integer deleted;


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

    @Column(name ="SENSITIVE_NAME",nullable=true)
    public String getSensitiveName() {
        return sensitiveName;
    }

    public void setSensitiveName(String sensitiveName) {
        this.sensitiveName = sensitiveName;
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

}
