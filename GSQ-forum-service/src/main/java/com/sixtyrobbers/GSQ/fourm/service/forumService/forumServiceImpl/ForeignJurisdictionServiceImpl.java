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
import com.sixtyrobbers.GSQ.fourm.service.entity.BaseResult;
import com.sixtyrobbers.GSQ.fourm.service.entity.ResponseCodeEnum;
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
 * @Author: luoheng
 * @CreateDate: 2019/5/15 21:34
 * @Version: 1.0
 */
@Service
public class ForeignJurisdictionServiceImpl implements ForeignJurisdictionService {
    private static final Logger logger = LoggerFactory.getLogger(ForeignJurisdictionServiceImpl.class);

    @Autowired
    private ForeignJurisdictionDAO foreignJurisdictionDAO;

    /**
     * @Description: 更新用户对外权限
     * @Author: luoheng
     * @CreateDate: 2019/5/15 21:36
     * @Version: 1.0
     */

    @Override
    public BaseResult updateForeignJurisdictionUser(ForeignJurisdictionReq foreignJurisdictionReq) {
        String checkResult = null;
        try {
            String param[] = {"userID"};
            checkResult = CheckObj.checkObjIsNull(foreignJurisdictionReq, param);
        } catch (IllegalAccessException e) {
            logger.error("修改用户对外权限--判断对象为空异常，param:{},error:{}", JSONObject.toJSONString(foreignJurisdictionReq), e.getMessage());
        }
        if (checkResult != null) {
            return new BaseResult(false, ResponseCodeEnum.ERROR_CODE_LACK_PARAM.getCode(), ResponseCodeEnum.ERROR_CODE_LACK_PARAM.getValue(), checkResult);
        }
        ForeignJurisdictionParam foreignJurisdictionParam = JSON.parseObject(JSON.toJSONString(foreignJurisdictionReq), ForeignJurisdictionParam.class);
        UserJurisdictionDO userJurisdictionDO = foreignJurisdictionDAO.getUserId(foreignJurisdictionParam);
        if (userJurisdictionDO == null) {
            return new BaseResult(false, ResponseCodeEnum.ERROR_CODE_USERID_ERROR.getCode(), ResponseCodeEnum.ERROR_CODE_USERID_ERROR.getValue(), "请确定用户ID是否存在!");
        }
        foreignJurisdictionDAO.updateForeignJurisdictionUser(foreignJurisdictionParam);

        return new BaseResult(true, ResponseCodeEnum.ERROR_CODE_FOREIGNJURISDICTION_SUCCESS.getCode(), ResponseCodeEnum.ERROR_CODE_MODIFY_SUCCESS.getValue(), "更新用户对外权限成功！");


    }

}
