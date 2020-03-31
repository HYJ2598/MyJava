package yjs.tyust.edu.cn.jiewei.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* <p>
    * 课程表
    * </p>
*
* @author 徐超
* @since 2019-05-28
*/

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
    public class Course implements Serializable {

    private static final long serialVersionUID = 1L;


    @TableField(exist = false)
    private SelCou selCou;


    /**
     * 课程编号
     */
    @TableId(value = "cou_id", type = IdType.AUTO)
    private Integer couId;

            /**
            * 课程名称
            */
    private String couName;

            /**
            * 视频个数
            */
    private Integer vidNum;

            /**
            * 主讲老师
            */
    private String teacher;

            /**
            * 课程简介
            */
    private String couInf;

            /**
            * 老师简介
            */
    private String teaInf;

            /**
            * 讲义文件地址
            */
    private String note;

            /**
            * 删除
            */
    private Integer del;

    /**
     * 讲义介绍
     */
    private String notInf;

    /**
     * 学分
     */
    private Integer credit;
    /**
    * 标记
    */
    private Integer flag;
    /**
    *年份
    * */
    private Integer year;
    /**
    * 课程种类
    * */
    private String kind;
    /**
    * 课程图片
    * */
    private String src;

    /**
    * 公需还是专业课
    * */
    private String kind2;
}
