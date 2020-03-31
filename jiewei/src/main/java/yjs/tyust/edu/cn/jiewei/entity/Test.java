package yjs.tyust.edu.cn.jiewei.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
* <p>
    * 
    * </p>
*
* @author gaoqiang
* @since 2019-05-30
*/
    @Data
    @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    public class Test implements Serializable {

    private static final long serialVersionUID = 1L;

            /**
            * 试题编号
            */
            @TableId(value = "test_id", type = IdType.AUTO)
    private Integer testId;

            /**
            * 题干
            */
    private String ques;

            /**
            * 选项内容
            */
    private String optiongroup;

            /**
            * 答案
            */
    private String answer;

            /**
            * 课程编号
            */
    private Integer couId;

            /**
            * 删除
            */
    private Integer del;

            /**
            * 解析
            */
    private String ana;

    private Integer sort;

    @TableField(exist = false)
    private List<Course> course;
}
