package yjs.tyust.edu.cn.jiewei.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* <p>
    * 选课表（关系表）
包含：编号，学生编号，课程编号，最后观看到的视频位置
    * </p>
*
* @author HYJ
* @since 2019-06-04
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
    public class SelCou implements Serializable {

    private static final long serialVersionUID = 1L;

            /**
            * 学生编号
            */
    private Integer stuId;

            /**
            * 课程编号
            */
    private Integer couId;

            /**
            * 最后观看的视频
            */
    private Integer vidId;

            /**
            * 最后观看的视频位置
            */
    private String posTime;

    private Boolean del;

            /**
            * 学习状态（3种）未听，正在听，已完成
            */
    private Integer stuStatu;

            /**
            * 2种状态:通过，未通过
            */
    private Integer situation;


}
