package org.pic.db.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("pixivPicture")

public class PixivPicture implements Serializable {

    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户ID")
    @TableField("USER_ID")
    private Long userId;

    @ApiModelProperty(value = "用户昵称")
    @TableField("NICKNAME")
    private String nickName;

    @ApiModelProperty(value = "用户昵称")
    @TableField("DESCRIPTION")
    private String description;

    @ApiModelProperty(value = "图片名称")
    @TableField("PICTURE_NAME")
    private String pictureName;

    @ApiModelProperty(value = "图片Id")
    @TableField("PICTURE_ID")
    private long pictureId;

    @ApiModelProperty(value = "图片URL")
    @TableField("PICTURE_URL")
    private String pictureUrl;

    @ApiModelProperty(value = "是否是R18 1-是，0-否")
    @TableField("R_18")
    private int r18;

    @ApiModelProperty(value = "图片喜欢数")
    @TableField("LIKE_NUM")
    private Long likeNum;

    @ApiModelProperty(value = "图片收藏数")
    @TableField("STORE_NUM")
    private Long storeNum;

    @ApiModelProperty(value = "图片tag")
    @TableField("TAG")
    private String tag;

    @ApiModelProperty(value = "图片提交时间")
    @TableField("SUBMIT_TIME")
    private LocalDateTime sumbitTime;

    @ApiModelProperty(value = "异常")
    @TableField("EXCEPTION")
    private String exception;

}
