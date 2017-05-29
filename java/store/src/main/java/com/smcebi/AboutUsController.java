package com.smcebi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 29.05.2017 15:59
 */
@Controller
@RequestMapping("/about-us")
public class AboutUsController {

    @RequestMapping("/sitemap")
    @ResponseBody
    public ResponseEntity sitemap() {
        return new ResponseEntity(HttpStatus.I_AM_A_TEAPOT);
    }
}
