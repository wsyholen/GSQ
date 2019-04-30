package com.sixtyrobbers.GSQ.fourm.controller.forumController;

import com.sixtyrobbers.GSQ.fourm.controller.entity.BaseResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

    /**
     * <pre>
     * Explain:
     * Author: holennnnnn_
     * Create_Time: 2019/4/30 14:41
     * Version: V1.0
     * </pre>
     */
    @RequestMapping(value = "/V1.0/getJurisdictionByRoleId", method = RequestMethod.GET)
    @ResponseBody
    public BaseResult getJurisdictionByRoleId(){
        return null;
    }

}
