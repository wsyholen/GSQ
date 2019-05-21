package com.sixtyrobbers.GSQ.fourm.controller.forumController;

import com.alibaba.fastjson.JSONObject;
import com.sixtyrobbers.GSQ.fourm.controller.entity.BaseResult;
import com.sixtyrobbers.GSQ.fourm.controller.entity.ResponseCodeEnum;
import com.sixtyrobbers.GSQ.fourm.service.entity.forum.request.ForeignJurisdictionReq;
import com.sixtyrobbers.GSQ.fourm.service.entity.forum.request.JurisdictionReq;
import com.sixtyrobbers.GSQ.fourm.service.entity.forum.response.JurisdictionRes;
import com.sixtyrobbers.GSQ.fourm.service.forumService.ForeignJurisdictionService;
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
 * @Description:    更新用户对外权限
 * @Author:         luoheng
 * @CreateDate:     2019/5/15 21:49
 * @Version:        1.0
 */
@Controller
@RequestMapping("/jurisdiction")
public class ForeignJurisdictionController {

    private static final Logger logger = LoggerFactory.getLogger(ForeignJurisdictionController.class);

    @Autowired
    private ForeignJurisdictionService foreignJurisdictionService;
    /**
     * @Description:    用户对外权限修改
     * @Author:         luoheng
     * @CreateDate:     2019/5/15 20:43
     * @Version:        1.0
     */
    @RequestMapping(value = "/V1.0/updateForeignJurisdictionUser", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult login(@RequestBody ForeignJurisdictionReq foreignJurisdictionReq)throws Exception{
        String result = null;
           result = foreignJurisdictionService.updateForeignJurisdictionUser(foreignJurisdictionReq);
        if (result.equals("1")){
            return new BaseResult(true, ResponseCodeEnum.ERROR_CODE_FOREIGNJURISDICTION_SUCCESS.getCode(), ResponseCodeEnum.ERROR_CODE_MODIFY_SUCCESS.getValue(), "更新用户对外权限成功！");
        }else if (result.equals("0")){
            return new BaseResult(false, ResponseCodeEnum.ERROR_CODE_FOREIGNJURISDICTION_ERROR.getCode(), ResponseCodeEnum.ERROR_CODE_MODIFY_ERRORS.getValue(), "更新用户对外权限失败！");
        }else if (result.equals("请确定用户ID是否存在!")){
            return new BaseResult(false, ResponseCodeEnum.ERROR_CODE_USERID_ERROR.getCode(), ResponseCodeEnum.ERROR_CODE_PHONE_PASSWORD_ERROR.getValue(), result);
        }
        return null;
    }


}
