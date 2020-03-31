package yjs.tyust.edu.cn.jiewei.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* <p>
    * 管理员表
    * </p>
*
* @author woyuno
* @since 2019-05-31
*/

    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;

            /**
            * 管理员编号
            */
            @TableId(value = "ad_id", type = IdType.AUTO)
    private Integer adId;

            /**
            * 姓名
            */
    private String adName;

            /**
            * 超级管理员：0不是；1是
            */
    private Integer authority;

            /**
            * 密码
            */
    private Integer adPwd;

            /**
            * 删除
            */
    private Integer del;


}
