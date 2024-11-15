package com.escass.testmemo.controllers;

import com.escass.testmemo.entities.MemoEntity;
import com.escass.testmemo.results.memo.WriteResult;
import com.escass.testmemo.services.MemoService;
import com.escass.testmemo.vos.MemoVo;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/memo")
@RequiredArgsConstructor
public class MemoController {
    private final MemoService memoService;

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView getIndex(@RequestParam(value = "index", required = false, defaultValue = "1") int index) {
        ModelAndView modelAndView = new ModelAndView();
        Pair<MemoEntity[], MemoVo> memos = this.memoService.getMemosByIndex(index);
        modelAndView.addObject("memos", memos.getLeft());
        modelAndView.addObject("pages", memos.getRight());
        modelAndView.setViewName("memo/index");
        return modelAndView;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String postIndex(MemoEntity memoEntity) {
        JSONObject response = new JSONObject();
        WriteResult writeResult = this.memoService.write(memoEntity);
        return response.toString();
    }
}
