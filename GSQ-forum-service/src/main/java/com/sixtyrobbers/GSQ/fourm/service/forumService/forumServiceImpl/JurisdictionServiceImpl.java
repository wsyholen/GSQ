package com.sixtyrobbers.GSQ.fourm.service.forumService.forumServiceImpl;

import com.alibaba.fastjson.JSON;
import com.sixtyrobbers.GSQ.fourm.common.util.CheckObj;
import com.sixtyrobbers.GSQ.fourm.dao.entity.fourm.dbdo.MenuDO;
import com.sixtyrobbers.GSQ.fourm.dao.entity.fourm.dbdo.RoleMenuDO;
import com.sixtyrobbers.GSQ.fourm.dao.entity.fourm.param.JurisdictionParam;
import com.sixtyrobbers.GSQ.fourm.dao.forum.JurisdictionDAO;
import com.sixtyrobbers.GSQ.fourm.service.entity.forum.request.JurisdictionReq;
import com.sixtyrobbers.GSQ.fourm.service.entity.forum.response.JurisdictionRes;
import com.sixtyrobbers.GSQ.fourm.service.forumService.JurisdictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    public List<JurisdictionRes> getJurisdictionByRoleId(JurisdictionReq JurisdictionReq) {
        JurisdictionParam jurisdictionParam = JSON.parseObject(JSON.toJSONString(JurisdictionReq), JurisdictionParam.class);
        //查询全部菜单
        List<MenuDO> allMenuList = jurisdictionDAO.getJurisdictionByRoleId(null);
        //查询该角色菜单
        List<MenuDO> menuDOList = jurisdictionDAO.getJurisdictionByRoleId(jurisdictionParam);
        String [] nodeNumer = new String[0];
        //把该角色的节点存到数组内
        for (MenuDO menuDO : menuDOList) {
             nodeNumer = CheckObj.addArray(nodeNumer,menuDO.getNodeNumer());
        }
        List<MenuDO> sonMenuList = new ArrayList<>();
        //筛选出子节点
        for (MenuDO menu : allMenuList) {
            if (menu.getParent() == 1) {
                sonMenuList.add(menu);
            }
        }
        //筛选出父类
        allMenuList.removeAll(sonMenuList);
        List<JurisdictionRes> jurisdictionResList = new ArrayList<>();
        JurisdictionRes jurisdictionRes = null;
        for (MenuDO menu : allMenuList) {
            List<JurisdictionRes> jurisdictionResSonList = new ArrayList<>();
            //判断该角色有没有该菜单
            boolean result = CheckObj.checkArray(nodeNumer,menu.getNodeNumer());
            //筛选出子类的父类
            for (MenuDO menuDO : sonMenuList) {
                String parent = menu.getNodeNumer().substring(0, 1);
                String son = menuDO.getNodeNumer().substring(0, 1);
                if (parent.equals(son)) {
                    //判断该角色有没有该菜单
                    boolean sonResult = CheckObj.checkArray(nodeNumer,menuDO.getNodeNumer());
                    jurisdictionRes = JSON.parseObject(JSON.toJSONString(menuDO), JurisdictionRes.class);
                    if (sonResult){
                        jurisdictionRes.setFlag(0);
                    }
                    jurisdictionResSonList.add(jurisdictionRes);
                }
            }
            jurisdictionRes = JSON.parseObject(JSON.toJSONString(menu), JurisdictionRes.class);
            if (result){
                jurisdictionRes.setFlag(0);
            }
            if (jurisdictionResSonList.size() > 0) {
                jurisdictionRes.setJurisdictionResList(jurisdictionResSonList);
            }
            jurisdictionResList.add(jurisdictionRes);
        }
        return jurisdictionResList;
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
    public void updateJurisdictionByRoleId(JurisdictionReq JurisdictionReq) {
        JurisdictionParam jurisdictionParam = JSON.parseObject(JSON.toJSONString(JurisdictionReq), JurisdictionParam.class);
        jurisdictionDAO.deleteJurisdictionByRoleId(jurisdictionParam);
        List<RoleMenuDO> roleMenuDOList = new ArrayList<>();
        for (MenuDO menuDO : JurisdictionReq.getMenuDOList()) {
            RoleMenuDO roleMenuDO = new RoleMenuDO();
            roleMenuDO.setRoleId(JurisdictionReq.getRoleId());
            roleMenuDO.setMenuNodeNumer(menuDO.getNodeNumer());
            roleMenuDOList.add(roleMenuDO);
        }
        jurisdictionDAO.addJurisdiction(roleMenuDOList);
    }
}
