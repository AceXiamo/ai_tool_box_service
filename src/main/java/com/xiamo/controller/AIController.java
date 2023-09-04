package com.xiamo.controller;

import cn.hutool.core.lang.Assert;
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiamo.common.AjaxResult;
import com.xiamo.entity.UserHistory;
import com.xiamo.security.SecurityService;
import com.xiamo.service.IRequestHistoryService;
import com.xiamo.service.IUserHistoryService;
import com.xiamo.service.IWxUserService;
import com.xiamo.utils.BanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * The type Ai controller.
 *
 * @Author: AceXiamo
 * @ClassName: AIController
 * @Date: 2023 /3/12 13:40
 */
@RestController
@RequestMapping("ai")
public class AIController  {

    /**
     * The constant API.
     */
    private static final String API = "https://api.openai.com/v1/chat/completions";

    /**
     * The Key.
     */
    @Value("${openai.key}")
    private String key;

    /**
     * The Open.
     */
    @Value("${openai.open}")
    private Boolean open;

    /**
     * The User service.
     */
    @Autowired
    private IWxUserService userService;

    /**
     * The User history service.
     */
    @Autowired
    private IUserHistoryService userHistoryService;

    /**
     * The Request history service.
     */
    @Autowired
    private IRequestHistoryService requestHistoryService;

    /**
     * Send ajax result.
     * <br/>
     * {
     * messageId: 1,
     * body: {
     * "model": "gpt-3.5-turbo",
     * "messages": [{"role":"system","content":""},{"role":"user","content":""},{"role":"assistant","content":""}]
     * }
     * }
     * <p>
     * role:
     * system - 系统预设
     * user - 用户
     * assistant - ai
     *
     * @param jsonObject the json object
     * @return the ajax result
     */
    @PostMapping("send")
    public AjaxResult send(@RequestBody JSONObject jsonObject) {
        BanUtil.isBan();
        Assert.isTrue(open, "维护中～");

        Integer messageId = jsonObject.getInteger("messageId");
        JSONObject jsonBody = jsonObject.getJSONObject("body");
        JSONArray messages = jsonBody.getJSONArray("messages");

        String body = HttpRequest.post(API)
                .header("Authorization", "Bearer " + key)
                .body(jsonBody.toJSONString(), "application/json")
                .execute()
                .body();
        JSONObject json = JSONObject.parseObject(body);
        JSONObject resMsg = json.getJSONArray("choices").getJSONObject(0).getJSONObject("message");

        messages.add(resMsg);
        if (messageId != null) {
            userHistoryService.updateHis(messageId, messages.toJSONString());
        } else {
            UserHistory userHistory = new UserHistory();
            userHistory.setOpenId(SecurityService.getWxUser().getOpenId());
            userHistory.setMessageContent(messages.toJSONString());
            userHistory.setType(jsonObject.getString("type"));
            userHistoryService.save(userHistory);
            messageId = userHistory.getId();
        }
        requestHistoryService.saveReqHis(SecurityService.getWxUser().getOpenId(), messageId, jsonObject.toJSONString(), body);

        JSONObject res = new JSONObject();
        res.put("messageId", messageId);
        res.put("body", resMsg);
        return AjaxResult.success(res);
    }

    /**
     * History ajax result.
     *
     * @param type     the type
     * @param pageNum  the page num
     * @param pageSize the page size
     * @return the ajax result
     */
    @PostMapping("history")
    public AjaxResult history(@RequestParam(name = "type") String type,
                              @RequestParam(name = "pageNum") Integer pageNum,
                              @RequestParam(name = "pageSize") Integer pageSize) {
        IPage<UserHistory> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<UserHistory> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserHistory::getOpenId, SecurityService.getWxUser().getOpenId());
        wrapper.eq(UserHistory::getType, type);
        wrapper.orderByDesc(UserHistory::getCreateTime);
        page = userHistoryService.page(page, wrapper);
        return AjaxResult.success(page);
    }

}
