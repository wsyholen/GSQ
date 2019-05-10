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
import com.sixtyrobbers.GSQ.fourm.service.entity.ServiceResult;
import com.sixtyrobbers.GSQ.fourm.service.entity.forum.request.ModifyPasswordReq;
import com.sixtyrobbers.GSQ.fourm.service.entity.forum.request.RegisterReq;
import com.sixtyrobbers.GSQ.fourm.service.entity.forum.request.UserReq;
import com.sixtyrobbers.GSQ.fourm.service.forumService.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public void addUser(RegisterReq registerReq) {
        String oid = OIDGennerator.getOID();
        String password = registerReq.getAccount().substring(registerReq.getAccount().length() - 6);
        String name = StringUtil.getStringRandom();
        RegisterParam registerParam = new RegisterParam(oid, registerReq.getAccount(), password, name);
        userDAO.addUser(registerParam);
    }

    /**
     * @Description: 修改密码
     * @Author: luoheng
     * @CreateDate: 2019/4/29 18:54
     * @Version: 1.0
     */
    @Override
    public String modifyPasswordByLoginPhone(ModifyPasswordReq modifyPasswordReq) {
        ModifyPasswordParam modifyPasswordParam = JSON.parseObject(JSON.toJSONString(modifyPasswordReq),ModifyPasswordParam.class);
        //UserParam userParam = JSON.parseObject(JSON.toJSONString(modifyPasswordReq),UserParam.class);
        //UserDO userDO = userDAO.getUser(userParam);
        UserDO userDO = userDAO.findUsersByLoginPhone(modifyPasswordParam);
        if (userDO == null){
            return "请确定账号或密码是否正确！";
        }
        int result = userDAO.modifyPasswordByLoginPhone(modifyPasswordParam);
        return String.valueOf(result);
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
    public ServiceResult updateBackGround(CommonsMultipartFile[] background, UserReq userReq) {
        if (background == null){
            return new ServiceResult(false,"图片不能为空！");
        }
        String flag = null;
        try {
            flag = CheckObj.checkObjIsNull(userReq,null);
        } catch (IllegalAccessException e) {
            logger.error("修改背景图片--判断对象为空异常，param:{},error:{}", JSONObject.toJSONString(userReq), e.getMessage());
        }
        if (flag != null){
            return new ServiceResult(false,flag);
        }
        UserParam userParam = JSON.parseObject(JSON.toJSONString(userReq),UserParam.class);
        UserDO userDO = userDAO.getUser(userParam);
        if (userDO == null){
            return new ServiceResult(false,"不存在用户信息!");
        }
        List<String> pathList = null;
        try {
            pathList = UploadFileUtil.uploadFile(background,userBackPath,onlinePath);
        } catch (IOException e) {
            logger.error("上传图片--业务异常，param:{},error:{}", e.getMessage());
        }
        String oid = OIDGennerator.getOID();
        UserPictureParam userPictureParam = new UserPictureParam(oid,userDO.getUserId(),1,pathList.get(0));
        //查询用户是否有背景图
        UserPictureDO userPictureDO = userPictureDAO.getUserPicture(userPictureParam);
        if (userPictureDO != null){
            UserPictureParam updateUserPictureParam = new UserPictureParam(userPictureDO.getId(),pathList.get(0));
            userPictureDAO.updateUserPicture(updateUserPictureParam);
            File file = new File(userPictureDO.getUserPictureUrl());
            //删除历史文件
            file.delete();
        }else {
            userPictureDAO.addUserPicture(userPictureParam);
            userParam.setBackId(oid);
            userDAO.updateUser(userParam);
        }
        return new ServiceResult(true,"修改背景成功！");
    }


}
