package com.sixtyrobbers.GSQ.fourm.service.forumService.forumServiceImpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sixtyrobbers.GSQ.fourm.common.util.CheckObj;
import com.sixtyrobbers.GSQ.fourm.dao.entity.fourm.dbdo.MenuDO;
import com.sixtyrobbers.GSQ.fourm.dao.entity.fourm.dbdo.RoleMenuDO;
import com.sixtyrobbers.GSQ.fourm.dao.entity.fourm.dbdo.UserJurisdictionDO;
import com.sixtyrobbers.GSQ.fourm.dao.entity.fourm.param.ForeignJurisdictionParam;
import com.sixtyrobbers.GSQ.fourm.dao.entity.fourm.param.JurisdictionParam;
import com.sixtyrobbers.GSQ.fourm.dao.forum.ForeignJurisdictionDAO;
import com.sixtyrobbers.GSQ.fourm.dao.forum.JurisdictionDAO;
import com.sixtyrobbers.GSQ.fourm.service.entity.ServiceResult;
import com.sixtyrobbers.GSQ.fourm.service.entity.forum.request.ForeignJurisdictionReq;
import com.sixtyrobbers.GSQ.fourm.service.entity.forum.request.JurisdictionReq;
import com.sixtyrobbers.GSQ.fourm.service.entity.forum.response.JurisdictionRes;
import com.sixtyrobbers.GSQ.fourm.service.forumService.ForeignJurisdictionService;
import com.sixtyrobbers.GSQ.fourm.service.forumService.JurisdictionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author:         luoheng
 * @CreateDate:     2019/5/15 21:34
 * @Version:        1.0
 */
@Service
public class ForeignJurisdictionServiceImpl implements ForeignJurisdictionService {
    private static final Logger logger = LoggerFactory.getLogger(ForeignJurisdictionServiceImpl.class);

    @Autowired
    private ForeignJurisdictionDAO foreignJurisdictionDAO;
    /**
     * @Description:    更新用户对外权限
     * @Author:         luoheng
     * @CreateDate:     2019/5/15 21:36
     * @Version:        1.0
     */

    @Override
    public String updateForeignJurisdictionUser(ForeignJurisdictionReq foreignJurisdictionReq) {
        String flag = null;
        try {
            String param[] = {"userID"};
            flag = CheckObj.checkObjIsNull(foreignJurisdictionReq,param);
        } catch (IllegalAccessException e) {
            logger.error("修改用户对外权限--判断对象为空异常，param:{},error:{}", JSONObject.toJSONString(foreignJurisdictionReq), e.getMessage());
        }
        if (flag != null){
            return "success";
        }
        ForeignJurisdictionParam foreignJurisdictionParam = JSON.parseObject(JSON.toJSONString(foreignJurisdictionReq),ForeignJurisdictionParam.class);
        UserJurisdictionDO userJurisdictionDO  = foreignJurisdictionDAO.getUserId(foreignJurisdictionParam);
        if (userJurisdictionDO == null){
            return "请确定用户ID是否存在!";
        }
        int result = foreignJurisdictionDAO.updateForeignJurisdictionUser(foreignJurisdictionParam);

        return String.valueOf(result);
    }

}
