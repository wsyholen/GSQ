package com.sixtyrobbers.GSQ.fourm.controller.forumController;

import com.alibaba.fastjson.JSONObject;
import com.sixtyrobbers.GSQ.fourm.controller.entity.BaseResult;
import com.sixtyrobbers.GSQ.fourm.controller.entity.ResponseCodeEnum;
import com.sixtyrobbers.GSQ.fourm.service.entity.PageInfo;
import com.sixtyrobbers.GSQ.fourm.service.entity.forum.request.TestReq;
import com.sixtyrobbers.GSQ.fourm.service.entity.forum.response.TestRes;
import com.sixtyrobbers.GSQ.fourm.service.forumService.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * <pre>
 * Explain: 测试controller
 * Author: holennnnnn_
 * Create_Time: 2019/3/26 13:42
 * Version: V1.0
 * </pre>
 */
@Controller
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private TestService testService;

    @Value("${test.properties}")
    private String testProperties;


    /**
     * <pre>
     * Explain: 分页测试
     * Author: holennnnnn_
     * Create_Time: 2019/3/26 13:43
     * Version: V1.0
     * </pre>
     */
    @RequestMapping(value = "/testPage")
    @ResponseBody
    public BaseResult testForPage(TestReq testRequest, HttpServletRequest request) {
        String sessionid = request.getSession().getId();
        PageInfo<TestRes> result = testService.testForPage(testRequest);
        return new BaseResult(true, ResponseCodeEnum.ERROR_CODE_SUCCESS.getCode(), ResponseCodeEnum.ERROR_CODE_SUCCESS.getValue(), result);
    }

    /**
     * <pre>
     * Explain: 查询测试
     * Author: holennnnnn_
     * Create_Time: 2019/3/26 13:43
     * Version: V1.0
     * </pre>
     */
    @RequestMapping(value = "/test")
    @ResponseBody
    public BaseResult test(TestReq testRequest) {
        logger.info("properties value controller：" + testProperties);
        logger.info("process request start----------开始处理请求-----------");
        logger.info("param----" + JSONObject.toJSONString(testRequest));
        TestRes result = testService.test(testRequest);
        return new BaseResult(true, ResponseCodeEnum.ERROR_CODE_SUCCESS.getCode(), ResponseCodeEnum.ERROR_CODE_SUCCESS.getValue(), result);
    }



}
