package org.pic.db.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.pic.db.entity.PixivPicture;

@Mapper
public interface PictureMapper extends BaseMapper<PixivPicture> {


}
