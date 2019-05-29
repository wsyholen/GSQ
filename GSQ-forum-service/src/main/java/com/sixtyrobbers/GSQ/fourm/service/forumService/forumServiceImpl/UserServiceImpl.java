package com.sixtyrobbers.GSQ.fourm.service.forumService.forumServiceImpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sixtyrobbers.GSQ.fourm.common.util.CheckObj;
import com.sixtyrobbers.GSQ.fourm.common.util.StringUtil;
import com.sixtyrobbers.GSQ.fourm.common.util.UploadFileUtil;
import com.sixtyrobbers.GSQ.fourm.common.util.oid.OIDGennerator;
import com.sixtyrobbers.GSQ.fourm.dao.entity.fourm.dbdo.UserDO;
import com.sixtyrobbers.GSQ.fourm.dao.entity.fourm.dbdo.UserPictureDO;
import com.sixtyrobbers.GSQ.fourm.dao.entity.fourm.param.ModifyPasswordParam;
import com.sixtyrobbers.GSQ.fourm.dao.entity.fourm.param.RegisterParam;
import com.sixtyrobbers.GSQ.fourm.dao.entity.fourm.param.UserParam;
import com.sixtyrobbers.GSQ.fourm.dao.entity.fourm.param.UserPictureParam;
import com.sixtyrobbers.GSQ.fourm.dao.forum.UserDAO;
import com.sixtyrobbers.GSQ.fourm.dao.forum.UserPictureDAO;
import com.sixtyrobbers.GSQ.fourm.service.entity.BaseResult;
import com.sixtyrobbers.GSQ.fourm.service.entity.ResponseCodeEnum;
import com.sixtyrobbers.GSQ.fourm.service.entity.forum.constant.RedisConstant;
import com.sixtyrobbers.GSQ.fourm.service.entity.forum.request.ModifyPasswordReq;
import com.sixtyrobbers.GSQ.fourm.service.entity.forum.request.RegisterReq;
import com.sixtyrobbers.GSQ.fourm.service.entity.forum.request.UserReq;
import com.sixtyrobbers.GSQ.fourm.service.forumService.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * <pre>
 * Explain:
 * Author: holennnnnn_
 * Create_Time: 2019/4/26 16:18
 * Version: V1.0
 * </pre>
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserPictureDAO userPictureDAO;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Value("${test.user.background.online.path}")
    private String onlinePath;

    @Value("${test.user.background.path}")
    private String userBackPath;

    /**
     * <pre>
     * Explain: 添加用户
     * Author: holennnnnn_
     * Create_Time: 2019/4/26 16:18
     * Version: V1.0
     * </pre>
     */
    @Override
    public BaseResult addUser(RegisterReq registerReq) {
        String checkResult = null;
        try {
            checkResult = CheckObj.checkObjIsNull(registerReq, null);
        } catch (IllegalAccessException e) {
            logger.error("添加用户--判断对象为空异常，param:{},error:{}", JSONObject.toJSONString(registerReq), e.getMessage());
        }
        if (checkResult != null) {
            return new BaseResult(false, ResponseCodeEnum.ERROR_CODE_LACK_PARAM.getCode(), ResponseCodeEnum.ERROR_CODE_LACK_PARAM.getValue(), checkResult);
        }
        String tempVerifyCode = (String) redisTemplate.opsForValue().get(RedisConstant.REGISTER_VERIFY_CODE + registerReq.getAccount());
        if (tempVerifyCode == null) {
            return new BaseResult(false, ResponseCodeEnum.ERROR_CODE_CODE_DUE.getCode(), ResponseCodeEnum.ERROR_CODE_CODE_DUE.getValue(), "验证码过期，请重新获取！");
        }
        if (!registerReq.getVerificationCode().equals(tempVerifyCode)) {
            return new BaseResult(false, ResponseCodeEnum.ERROR_CODE_CODE_ERROR.getCode(), ResponseCodeEnum.ERROR_CODE_CODE_ERROR.getValue(), "验证码错误，请重新输入！");
        } else {
            redisTemplate.delete(RedisConstant.REGISTER_VERIFY_CODE + registerReq.getAccount());
            String oid = OIDGennerator.getOID();
            String password = registerReq.getAccount().substring(registerReq.getAccount().length() - 6);
            String name = StringUtil.getStringRandom();
            RegisterParam registerParam = new RegisterParam(oid, registerReq.getAccount(), password, name);
            userDAO.addUser(registerParam);
            return new BaseResult(true, ResponseCodeEnum.ERROR_CODE_REGISTER_SUCCESS.getCode(), ResponseCodeEnum.ERROR_CODE_REGISTER_SUCCESS.getValue(), "注册成功！");
        }
    }

    /**
     * @Description: 修改密码
     * @Author: luoheng
     * @CreateDate: 2019/4/29 18:54
     * @Version: 1.0
     */
    @Override
    public BaseResult modifyPasswordByLoginPhone(ModifyPasswordReq modifyPasswordReq) {
        String success = null;
        try {
            String param[] = {"loginPhone", "loginPassword", "newPassword", "secondPassword"};
            success = CheckObj.checkObjIsNull(modifyPasswordReq, param);
        } catch (IllegalAccessException e) {
            logger.error("修改密码--判断参数为空异常，param:{},error:{}", JSONObject.toJSONString(modifyPasswordReq), e.getMessage());
        }
        if (success != null) {
            return new BaseResult(false, ResponseCodeEnum.ERROR_CODE_LACK_PARAM.getCode(), ResponseCodeEnum.ERROR_CODE_LACK_PARAM.getValue(), success);
        }
        if (!modifyPasswordReq.getNewPassword().equals(modifyPasswordReq.getSecondPassword())) {
            return new BaseResult(false, ResponseCodeEnum.ERROR_CODE_MODIFY_ERROR.getCode(), ResponseCodeEnum.ERROR_CODE_MODIFY_ERROR.getValue(), "重新输入的新密码与第一次不一致!");
        }
        ModifyPasswordParam modifyPasswordParam = JSON.parseObject(JSON.toJSONString(modifyPasswordReq), ModifyPasswordParam.class);
        UserDO userDO = userDAO.findUsersByLoginPhone(modifyPasswordParam);
        if (userDO == null) {
            return new BaseResult(false, ResponseCodeEnum.ERROR_CODE_PHONE_PASSWORD_ERROR.getCode(), ResponseCodeEnum.ERROR_CODE_PHONE_PASSWORD_ERROR.getValue(), "请确定账号密码是否正确");
        }
        userDAO.modifyPasswordByLoginPhone(modifyPasswordParam);
        return new BaseResult(true, ResponseCodeEnum.ERROR_CODE_MODIFY_SUCCESS.getCode(), ResponseCodeEnum.ERROR_CODE_MODIFY_SUCCESS.getValue(), "修改密码成功！");
    }
   /**
    * @Description:    忘记密码
    * @Author:         luoheng
    * @CreateDate:     2019/5/29 21:24
    * @Version:        1.0
    */
   @Override
   public BaseResult forgetPasswordByLoginPhone(ModifyPasswordReq modifyPasswordReq) {
       String success = null;
       try {
           String param[] = {"loginPhone", "verificationCode", "newPassword", "secondPassword"};
           //success = CheckObj.checkObjIsNull(modifyPasswordReq,null);
           success = CheckObj.checkObjIsNull(modifyPasswordReq, param);
       } catch (IllegalAccessException e) {
           logger.error("忘记密码--判断参数为空异常，param:{},error:{}", JSONObject.toJSONString(modifyPasswordReq), e.getMessage());
       }
       if (success != null) {
           return new BaseResult(false, ResponseCodeEnum.ERROR_CODE_LACK_PARAM.getCode(), ResponseCodeEnum.ERROR_CODE_LACK_PARAM.getValue(), success);
       }
       if (!modifyPasswordReq.getNewPassword().equals(modifyPasswordReq.getSecondPassword())) {
           return new BaseResult(false, ResponseCodeEnum.ERROR_CODE_MODIFY_ERROR.getCode(), ResponseCodeEnum.ERROR_CODE_MODIFY_ERROR.getValue(), "重新输入的新密码与第一次不一致!");
       }
       String tempVerifyCode = (String) redisTemplate.opsForValue().get(RedisConstant.REGISTER_VERIFY_CODE + modifyPasswordReq.getLoginPhone());
       if (tempVerifyCode == null) {
           return new BaseResult(false, ResponseCodeEnum.ERROR_CODE_CODE_DUE.getCode(), ResponseCodeEnum.ERROR_CODE_CODE_DUE.getValue(), "验证码过期，请重新获取！");
       }
       ModifyPasswordParam modifyPasswordParam = JSON.parseObject(JSON.toJSONString(modifyPasswordReq), ModifyPasswordParam.class);
       UserDO userDO = userDAO.findUsersByLoginPhone(modifyPasswordParam);
       if (userDO == null) {
           return new BaseResult(false, ResponseCodeEnum.ERROR_CODE_PHONE_PASSWORD_ERROR.getCode(), ResponseCodeEnum.ERROR_CODE_PHONE_PASSWORD_ERROR.getValue(), "请确定账号密码是否正确");
       }
       if (!modifyPasswordReq.getVerificationCode().equals(tempVerifyCode)) {
           return new BaseResult(false, ResponseCodeEnum.ERROR_CODE_CODE_ERROR.getCode(), ResponseCodeEnum.ERROR_CODE_CODE_ERROR.getValue(), "验证码错误，请重新输入！");
       }else{
           userDAO.modifyPasswordByLoginPhone(modifyPasswordParam);
           return new BaseResult(true, ResponseCodeEnum.ERROR_CODE_MODIFY_SUCCESS.getCode(), ResponseCodeEnum.ERROR_CODE_MODIFY_SUCCESS.getValue(), "修改密码成功！");
       }


   }

    /**
     * <pre>
     * Explain: 修改背景图片
     * Author: holennnnnn_
     * Create_Time: 2019/5/9 14:24
     * Version: V1.0
     * </pre>
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResult updateBackGround(CommonsMultipartFile[] background, UserReq userReq) {
        if (background == null) {
            return new BaseResult(false, ResponseCodeEnum.ERROR_CODE_LACK_PARAM.getCode(), ResponseCodeEnum.ERROR_CODE_LACK_PARAM.getValue(), "图片不能为空！");
        }
        String checkResult = null;
        try {
            checkResult = CheckObj.checkObjIsNull(userReq, null);
        } catch (IllegalAccessException e) {
            logger.error("修改背景图片--判断对象为空异常，param:{},error:{}", JSONObject.toJSONString(userReq), e.getMessage());
        }
        if (checkResult != null) {
            return new BaseResult(false, ResponseCodeEnum.ERROR_CODE_LACK_PARAM.getCode(), ResponseCodeEnum.ERROR_CODE_LACK_PARAM.getValue(), checkResult);
        }
        UserParam userParam = JSON.parseObject(JSON.toJSONString(userReq), UserParam.class);
        UserDO userDO = userDAO.getUser(userParam);
        if (userDO == null) {
            return new BaseResult(false, ResponseCodeEnum.ERROR_CODE_PARAM.getCode(), ResponseCodeEnum.ERROR_CODE_PARAM.getValue(), "不存在用户信息!");
        }
        List<String> pathList = null;
        try {
            pathList = UploadFileUtil.uploadFile(background, userBackPath, onlinePath);
        } catch (IOException e) {
            logger.error("上传图片--业务异常，param:{},error:{}", e.getMessage());
        }
        String oid = OIDGennerator.getOID();
        UserPictureParam userPictureParam = new UserPictureParam(oid, userDO.getUserId(), 1, pathList.get(0));
        //查询用户是否有背景图
        UserPictureDO userPictureDO = userPictureDAO.getUserPicture(userPictureParam);
        if (userPictureDO != null) {
            UserPictureParam updateUserPictureParam = new UserPictureParam(userPictureDO.getId(), pathList.get(0));
            userPictureDAO.updateUserPicture(updateUserPictureParam);
            File file = new File(userPictureDO.getUserPictureUrl());
            //删除历史文件
            file.delete();
        } else {
            userPictureDAO.addUserPicture(userPictureParam);
            userParam.setBackId(oid);
            userDAO.updateUser(userParam);
        }
        return new BaseResult(true, ResponseCodeEnum.ERROR_CODE_SUCCESS.getCode(), ResponseCodeEnum.ERROR_CODE_SUCCESS.getValue(), "修改背景成功!");
    }

}
