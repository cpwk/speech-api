package com.mdtech.zyedu.api.admin.authority;

import com.mdtech.zyedu.common.entity.Constants;

public enum AdminPermission {

    //    none
    NONE("", ""),

    /* 功能模块 */
    // admin&role
    ROLE_EDIT("管理组管理", Constants.LEVEL_IMPORTANT),
    ADMIN_LIST("管理员列表", Constants.LEVEL_IMPORTANT),
    ADMIN_EDIT("编辑管理员", Constants.LEVEL_IMPORTANT),

    TRAINER_EDIT("导师管理", Constants.LEVEL_WARNING),

    BANNER_EDIT("Banner管理", Constants.LEVEL_PRIMARY),
    CAMPUS_EDIT("校区管理", Constants.LEVEL_PRIMARY),
    ARTICLE_EDIT("文章管理", Constants.LEVEL_PRIMARY),
    FORM_QUERY("表单查询", Constants.LEVEL_PRIMARY),
    VIDEO_EDIT("视频查询", Constants.LEVEL_PRIMARY)
    ;


    private String val;
    private String level;

    AdminPermission(String val, String level) {
        this.val = val;
        this.level = level;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

}
