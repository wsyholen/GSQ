package com.sixtyrobbers.GSQ.fourm.controller.forumController;

import com.alibaba.fastjson.JSONObject;
import com.sixtyrobbers.GSQ.fourm.service.entity.BaseResult;
import com.sixtyrobbers.GSQ.fourm.service.entity.ResponseCodeEnum;
import com.sixtyrobbers.GSQ.fourm.service.entity.forum.request.JurisdictionReq;
import com.sixtyrobbers.GSQ.fourm.service.entity.forum.response.JurisdictionRes;
import com.sixtyrobbers.GSQ.fourm.service.forumService.JurisdictionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * Explain: 权限controller
 * Author: holennnnnn_
 * Create_Time: 2019/4/30 13:58
 * Version: V1.0
 * </pre>
 */
@Controller
@RequestMapping("/jurisdiction")
public class JurisdictionController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private JurisdictionService jurisdictionService;

    /**
     * <pre>
     * Explain: 权限查询
     * Author: holennnnnn_
     * Create_Time: 2019/4/30 14:41
     * Version: V1.0
     * </pre>
     */
    @RequestMapping(value = "/V1.0/getJurisdictionByRoleId", method = RequestMethod.GET)
    @ResponseBody
    public BaseResult getJurisdictionByRoleId(JurisdictionReq JurisdictionReq) throws Exception {
        BaseResult result = jurisdictionService.getJurisdictionByRoleId(JurisdictionReq);
        return result;
    }

    /**
     * <pre>
     * Explain: 编辑权限
     * Author: holennnnnn_
     * Create_Time: 2019/5/7 15:20
     * Version: V1.0
     * </pre>
     */
    @RequestMapping(value = "/V1.0/updateJurisdictionByRoleId", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult updateJurisdictionByRoleId(@RequestBody JurisdictionReq JurisdictionReq) throws Exception {
        BaseResult result = jurisdictionService.updateJurisdictionByRoleId(JurisdictionReq);
        return result;
    }

}
