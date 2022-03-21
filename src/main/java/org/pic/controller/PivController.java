package org.pic.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.pic.annotation.Log;
import org.pic.param.SearchPictureParam;
import org.pic.service.impl.PixivServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@Api(tags = "图片")
@RestController
@RequestMapping(value = "/picture", produces = MediaType.APPLICATION_JSON_VALUE)
public class PivController {

    @Autowired
    private PixivServiceImpl pixivService;

    @ResponseBody
    @PostMapping("/test")
    public String test() {
        return "hello";
    }

    @Log
    @ApiOperation(value = "图片数据查询")
    @ApiImplicitParams({
//            @ApiImplicitParam(name = Constants.USER_ID, value = Constants.USER_ID, dataTypeClass = Long.class,
//                    required = true, paramType = Constants.PARAM_TYPE_HEADER)
    })
    @GetMapping(value = "/find")
    @ResponseBody
    public void getPicture(SearchPictureParam searchPictureParam) {
        pixivService.getPicture(searchPictureParam);
    }
}
