package org.pic.service;

import org.pic.db.entity.PixivPicture;
import org.pic.param.SearchPictureParam;

import java.util.List;

public interface PixivService {

    public List<PixivPicture> getPicture(SearchPictureParam searchPictureParam);
}
