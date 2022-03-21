package org.pic.service.impl;

import org.pic.db.entity.PixivPicture;
import org.pic.db.mapper.PictureMapper;
import org.pic.param.SearchPictureParam;
import org.pic.service.PixivService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PixivServiceImpl implements PixivService {

    @Autowired
    private PictureMapper pictureMapper;


    @Override
    public List<PixivPicture> getPicture(SearchPictureParam searchPictureParam) {
        return null;
    }
}
