package com.xiamo.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * The type Wx user.
 */
@Data
@TableName("wx_user")
public class WxUser {
    /**
     * 用户open_id
     */
    @TableId("open_id")
    private String openId;

    /**
     * 昵称
     */
    @TableField("nickname")
    private String nickname;

    /**
     * 头像
     */
    @TableField("avatar")
    private String avatar;

    /**
     * The Is ban.
     */
    @TableField("is_ban")
    private String is_ban;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("update_time")
    private Date updateTime;

    /* ===================================== */

    /**
     * The Token.
     */
    @TableField(exist = false)
    private String token;

    /**
     * The Session key.
     */
    @TableField(exist = false)
    private String sessionKey;
}

