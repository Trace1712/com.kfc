package org.pic.control;

import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Validated
@Api(tags = "图片")
@RestController
@RequestMapping(value = "/picture", produces = MediaType.APPLICATION_JSON_VALUE)
public class PivController {

    @ResponseBody
    @PostMapping("/test")
    public String test() {
        return "hello";
    }
}
