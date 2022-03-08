package org.pic.db.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("pixivPicture")

public class PixivPicture implements Serializable {
    private String userId;
    private String nickName;
    private int kind;
    private String pictureName;
    private String pictureId;
    private String pictureUrl;
    private int r18;
    private int likeNum;
    private int storeNum;
    private List tags;
    private LocalDateTime sumbitTime;
    private String exception;

}
