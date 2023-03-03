package org.example.controller;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
public class GptController {

    @GetMapping("/gpt/ask")
    @ResponseBody
    public String ask(@RequestParam("question") String question) {
        log.info("question: {}", question);
        String result = HttpUtil.get("https://gpt.baixing.com?p=" + question + "&k=EGW5HMV5");
        log.info("result: {}", result);
        JSONObject jsonObject = JSON.parseObject(result);
        if (jsonObject.getIntValue("code") == 0) {
            return jsonObject.getString("data");
        } else {
            return jsonObject.getString("message");
        }
    }

    @RequestMapping("/")
    public ModelAndView index() {
        return new ModelAndView("index.html");
    }

}
