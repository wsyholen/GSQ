package com.sixtyrobbers.GSQ.fourm.service.forumService.forumServiceImpl;

import com.alibaba.fastjson.JSON;
import com.sixtyrobbers.GSQ.fourm.dao.entity.fourm.dbdo.MenuDO;
import com.sixtyrobbers.GSQ.fourm.dao.entity.fourm.dbdo.RoleMenuDO;
import com.sixtyrobbers.GSQ.fourm.dao.entity.fourm.param.JurisdictionParam;
import com.sixtyrobbers.GSQ.fourm.dao.forum.JurisdictionDAO;
import com.sixtyrobbers.GSQ.fourm.service.entity.forum.request.JurisdictionReq;
import com.sixtyrobbers.GSQ.fourm.service.entity.forum.response.JurisdictionRes;
import com.sixtyrobbers.GSQ.fourm.service.forumService.JurisdictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
        List<MenuDO> menuDOList = jurisdictionDAO.getJurisdictionByRoleId(jurisdictionParam);
        List<MenuDO> sonMenuList = new ArrayList<>();
        for (MenuDO menu : menuDOList) {
            if (menu.getParent() == 1) {
                sonMenuList.add(menu);
            }
        }
        menuDOList.removeAll(sonMenuList);
        List<JurisdictionRes> jurisdictionResList = new ArrayList<>();
        JurisdictionRes jurisdictionRes = new JurisdictionRes();
        for (MenuDO menu : menuDOList) {
            List<JurisdictionRes> jurisdictionResSonList = new ArrayList<>();
            for (MenuDO menuDO : sonMenuList) {
                String parent = menu.getNodeNumer().substring(0,1);
                String son = menuDO.getNodeNumer().substring(0,1);
                if (parent.equals(son)){
                    jurisdictionRes = JSON.parseObject(JSON.toJSONString(menuDO), JurisdictionRes.class);
                    jurisdictionResSonList.add(jurisdictionRes);
                }
            }
            jurisdictionRes = JSON.parseObject(JSON.toJSONString(menu), JurisdictionRes.class);
            if (jurisdictionResSonList.size() > 0){
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
    @Transactional
    public void updateJurisdictionByRoleId(JurisdictionReq JurisdictionReq) {
        JurisdictionParam jurisdictionParam = JSON.parseObject(JSON.toJSONString(JurisdictionReq), JurisdictionParam.class);
        jurisdictionDAO.deleteJurisdictionByRoleId(jurisdictionParam);
        List<RoleMenuDO> roleMenuDOList = new ArrayList<>();
        RoleMenuDO roleMenuDO = new RoleMenuDO();
        for (MenuDO menuDO: JurisdictionReq.getMenuDOList()) {
            roleMenuDO.setRoleId(JurisdictionReq.getRoleId());
            roleMenuDO.setMenuNodeNumer(menuDO.getNodeNumer());
            roleMenuDOList.add(roleMenuDO);
        }
        jurisdictionDAO.addJurisdiction(roleMenuDOList);
    }
}
