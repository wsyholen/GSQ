package com.sixtyrobbers.GSQ.fourm.controller.forumController;

import com.sixtyrobbers.GSQ.fourm.service.entity.BaseResult;
import com.sixtyrobbers.GSQ.fourm.service.entity.ResponseCodeEnum;
import com.sixtyrobbers.GSQ.fourm.service.entity.forum.request.ForeignJurisdictionReq;
import com.sixtyrobbers.GSQ.fourm.service.forumService.ForeignJurisdictionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @Description: 更新用户对外权限
 * @Author: luoheng
 * @CreateDate: 2019/5/15 21:49
 * @Version: 1.0
 */
@Controller
@RequestMapping("/jurisdiction")
public class ForeignJurisdictionController {

    private static final Logger logger = LoggerFactory.getLogger(ForeignJurisdictionController.class);

    @Autowired
    private ForeignJurisdictionService foreignJurisdictionService;

    /**
     * @Description: 用户对外权限修改
     * @Author: luoheng
     * @CreateDate: 2019/5/15 20:43
     * @Version: 1.0
     */
    @RequestMapping(value = "/V1.0/updateForeignJurisdictionUser", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult login(@RequestBody ForeignJurisdictionReq foreignJurisdictionReq) throws Exception {
        BaseResult result = foreignJurisdictionService.updateForeignJurisdictionUser(foreignJurisdictionReq);
        return result;
    }


}
