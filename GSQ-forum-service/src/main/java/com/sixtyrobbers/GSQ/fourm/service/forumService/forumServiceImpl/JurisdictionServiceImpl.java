package com.sixtyrobbers.GSQ.fourm.service.forumService.forumServiceImpl;

import com.alibaba.fastjson.JSON;
import com.sixtyrobbers.GSQ.fourm.common.util.CheckObj;
import com.sixtyrobbers.GSQ.fourm.dao.entity.fourm.dbdo.MenuDO;
import com.sixtyrobbers.GSQ.fourm.dao.entity.fourm.dbdo.RoleMenuDO;
import com.sixtyrobbers.GSQ.fourm.dao.entity.fourm.param.JurisdictionParam;
import com.sixtyrobbers.GSQ.fourm.dao.forum.JurisdictionDAO;
import com.sixtyrobbers.GSQ.fourm.service.entity.BaseResult;
import com.sixtyrobbers.GSQ.fourm.service.entity.ResponseCodeEnum;
import com.sixtyrobbers.GSQ.fourm.service.entity.forum.request.JurisdictionReq;
import com.sixtyrobbers.GSQ.fourm.service.entity.forum.response.JurisdictionRes;
import com.sixtyrobbers.GSQ.fourm.service.entity.forum.response.MenuRes;
import com.sixtyrobbers.GSQ.fourm.service.forumService.JurisdictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.util.*;

/**
 * <pre>
 * Explain:
 * Author: holennnnnn_
 * Create_Time: 2019/5/7 13:25
 * Version: V1.0
 * </pre>
 */
@Service
public class JurisdictionServiceImpl implements JurisdictionService {

    @Autowired
    private JurisdictionDAO jurisdictionDAO;

    /**
     * <pre>
     * Explain: 权限查询
     * Author: holennnnnn_
     * Create_Time: 2019/5/7 13:49
     * Version: V1.0
     * </pre>
     */
    @Override
    public BaseResult getJurisdictionByRoleId(JurisdictionReq JurisdictionReq) {
        if (JurisdictionReq.getRoleId() == null || JurisdictionReq.getRoleId() == "") {
            return new BaseResult(false, ResponseCodeEnum.ERROR_CODE_LACK_PARAM.getCode(), ResponseCodeEnum.ERROR_CODE_LACK_PARAM.getValue(), "角色不能为空!");
        }
        JurisdictionParam jurisdictionParam = JSON.parseObject(JSON.toJSONString(JurisdictionReq), JurisdictionParam.class);
        //查询全部菜单
        List<MenuDO> allMenuList = jurisdictionDAO.getJurisdictionByRoleId(null);
        //查询该角色菜单
        List<MenuDO> menuList = jurisdictionDAO.getJurisdictionByRoleId(jurisdictionParam);
        //添加该角色菜单
        List<MenuRes> menuResList = new ArrayList<>();
        for (MenuDO menuDO : menuList) {
            MenuRes menuRes = JSON.parseObject(JSON.toJSONString(menuDO), MenuRes.class);
            menuResList.add(menuRes);
        }
        //添加全部菜单
        List<MenuRes> allmenuResList = new ArrayList<>();
        for (MenuDO menuDO : allMenuList) {
            MenuRes menuRes = JSON.parseObject(JSON.toJSONString(menuDO), MenuRes.class);
            allmenuResList.add(menuRes);
        }
        //对该角色的菜单进行标识
        for (MenuRes menuRes : allmenuResList) {
            for (MenuRes menuRes1 : menuResList) {
                if (menuRes1.getMenuId().equals(menuRes.getMenuId())) {
                    menuRes.setFlag(1);
                }
            }
        }
        //根节点
        List<MenuRes> rootMenu = new ArrayList<>();
        for (MenuRes menuRes : allmenuResList) {
            //父节点是0的为根节点
            if (menuRes.getParentId() == 0) {
                rootMenu.add(menuRes);
            }
        }
        //根据Menu的order排序
        Collections.sort(rootMenu, order());
        //为根菜单设置子菜单 getClild是递归调用的
        for (MenuRes nav : allmenuResList) {
            //获取根节点下的所有子节点
            List<MenuRes> childList = getChild(nav.getMenuId(), allmenuResList);
            //给根节点设置子节点
            nav.setChildren(childList);
        }
        Map<String, Object> result = new HashMap<>();
        result.put("jurisdictionList", rootMenu);
        return new BaseResult(true, ResponseCodeEnum.ERROR_CODE_SUCCESS.getCode(), ResponseCodeEnum.ERROR_CODE_SUCCESS.getValue(), result);
    }

    /**
     * <pre>
     * Explain: 编辑权限
     * Author: holennnnnn_
     * Create_Time: 2019/5/7 15:39
     * Version: V1.0
     * </pre>
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResult updateJurisdictionByRoleId(JurisdictionReq JurisdictionReq) {
        if (JurisdictionReq.getRoleId() == null || JurisdictionReq.getRoleId() == "") {
            return new BaseResult(false, ResponseCodeEnum.ERROR_CODE_LACK_PARAM.getCode(), ResponseCodeEnum.ERROR_CODE_LACK_PARAM.getValue(), "角色不能为空!");
        }
        if (JurisdictionReq.getMenuDOList().size() == 0) {
            return new BaseResult(false, ResponseCodeEnum.ERROR_CODE_LACK_PARAM.getCode(), ResponseCodeEnum.ERROR_CODE_LACK_PARAM.getValue(), "菜单不能为空!");
        }
        JurisdictionParam jurisdictionParam = JSON.parseObject(JSON.toJSONString(JurisdictionReq), JurisdictionParam.class);
        jurisdictionDAO.deleteJurisdictionByRoleId(jurisdictionParam);
        List<RoleMenuDO> roleMenuDOList = new ArrayList<>();
        for (MenuDO menuDO : JurisdictionReq.getMenuDOList()) {
            RoleMenuDO roleMenuDO = new RoleMenuDO();
            roleMenuDO.setRoleId(JurisdictionReq.getRoleId());
            roleMenuDO.setMenuId(menuDO.getMenuId());
            roleMenuDOList.add(roleMenuDO);
        }
        jurisdictionDAO.addJurisdiction(roleMenuDOList);
        return new BaseResult(true, ResponseCodeEnum.ERROR_CODE_SUCCESS.getCode(), ResponseCodeEnum.ERROR_CODE_SUCCESS.getValue(), "编辑成功！");
    }

    /**
     * <pre>
     * Explain: 排序
     * Author: holennnnnn_
     * Create_Time: 2019/5/15 16:15
     * Version: V1.0
     * </pre>
     */
    public Comparator<MenuRes> order() {
        Comparator<MenuRes> comparator = new Comparator<MenuRes>() {
            @Override
            public int compare(MenuRes o1, MenuRes o2) {
                if (!o1.getOrder().equals(o2.getOrder())) {
                    return o1.getOrder() - o2.getOrder();
                }
                return 0;
            }
        };
        return comparator;
    }

    /**
     * <pre>
     * Explain: 获取子节点
     * Author: holennnnnn_
     * Create_Time: 2019/5/15 16:17
     * Version: V1.0
     * </pre>
     */
    public List<MenuRes> getChild(int id, List<MenuRes> allMenu) {
        //子菜单
        List<MenuRes> childList = new ArrayList<MenuRes>();
        // 遍历所有节点，将所有菜单的父id与传过来的根节点的id比较
        for (MenuRes menuRes : allMenu) {
            //相等说明：为该根节点的子节点。
            if (menuRes.getParentId().equals(id)) {
                childList.add(menuRes);
            }
        }
        //递归
        for (MenuRes nav : childList) {
            nav.setChildren(getChild(nav.getMenuId(), allMenu));
        }
        //排序
        Collections.sort(childList, order());
        //如果节点下没有子节点，返回一个空List（递归退出）
        if (childList.size() == 0) {
            return new ArrayList<MenuRes>();
        }
        return childList;
    }
}
