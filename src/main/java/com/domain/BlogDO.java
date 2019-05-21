package com.domain;

import java.util.Date;

/**
 * CREATE TABLE `Blog` (
 *   `id` bigint(20) NOT NULL AUTO_INCREMENT,
 *   `name` varchar(12) DEFAULT NULL,
 *   `remark` varchar(24) DEFAULT NULL,
 *   `createtime` datetime DEFAULT NULL,
 *   `updatetime` datetime DEFAULT NULL,
 *   PRIMARY KEY (`id`)
 * ) ENGINE=MyISAM AUTO_INCREMENT=100001 DEFAULT CHARSET=utf8 ;
 *
 */
public class BlogDO {

    private Long id;

    private String name;

    private String remark;

    private Date createtime;

    private Date updateTime;

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
