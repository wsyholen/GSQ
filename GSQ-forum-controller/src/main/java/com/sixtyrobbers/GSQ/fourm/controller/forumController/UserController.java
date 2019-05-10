package com.sixtyrobbers.GSQ.fourm.controller.forumController;

import com.alibaba.fastjson.JSONObject;
import com.sixtyrobbers.GSQ.fourm.controller.entity.BaseResult;
import com.sixtyrobbers.GSQ.fourm.controller.entity.ResponseCodeEnum;
import com.sixtyrobbers.GSQ.fourm.service.entity.ServiceResult;
import com.sixtyrobbers.GSQ.fourm.service.entity.forum.request.UserReq;
import com.sixtyrobbers.GSQ.fourm.service.forumService.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * <pre>
 * Explain:
 * Author: holennnnnn_
 * Create_Time: 2019/5/9 13:43
 * Version: V1.0
 * </pre>
 */
@RequestMapping("/user")
@Controller
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     * <pre>
     * Explain: 更新用户背景图
     * Author: holennnnnn_
     * Create_Time: 2019/5/9 13:52
     * Version: V1.0
     * </pre>
     */
    @RequestMapping(value = "/V1.0/updateBackGround", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult updateBackGround(@RequestParam(value = "background") CommonsMultipartFile[] background, UserReq userReq) throws Exception{
        ServiceResult result = null;
        result = userService.updateBackGround(background, userReq);
        if (result.getSuccess() == true){
            return new BaseResult(true, ResponseCodeEnum.ERROR_CODE_SUCCESS.getCode(), ResponseCodeEnum.ERROR_CODE_SUCCESS.getValue(), result);
        }else {
            return new BaseResult(false, ResponseCodeEnum.ERROR_CODE_PARAM.getCode(), ResponseCodeEnum.ERROR_CODE_PARAM.getValue(), result);
        }
    }

}
