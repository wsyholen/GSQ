package com.sixtyrobbers.GSQ.fourm.service.entity.forum.response;


import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <pre>
 * Explain:
 * Author: holennnnnn_
 * Create_Time: 2019/5/20 11:21
 * Version: V1.0
 * </pre>
 */
@Data
public class MenuRes implements Serializable {

    private static final long serialVersionUID = -9009037043326798975L;

    /**
     * 菜单id
     */
    private Integer menuId;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 菜单url
     */
    private String menuUrl;

    /**
     * 图标url
     */
    private String iconUrl;

    /**
     * 父级id
     */
    private Integer parentId;

    /**
     * 排序
     */
    private Integer order;

    /**
     * 标识
     */
    private Integer flag = 0;

    /**
     * 子节点
     */
    private List<MenuRes> children;

}
