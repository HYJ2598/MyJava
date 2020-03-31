package yjs.tyust.edu.cn.jiewei.entity;

    import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* <p>
    * 学生表
    * </p>
*
* @author yuanshuai
* @since 2019-05-30
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @TableName("student")
    public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

            /**
            * 编号
            */
            @TableId(value = "stu_id", type = IdType.AUTO)
    private Integer stuId;

            /**
            * 姓名
            */
    private String stuName;

            /**
            * 性别
            */
    private String sex;

            /**
            * 身份证号
            */
    private String idcard;

            /**
            * 分享次数
            */
    private Integer shareNum;

            /**
            * 学分
            */
    private Integer credit;

            /**
            * 删除
            */
    private Integer del;

    private String phoneNum;

    private String company;

            /**
            * 所选学分
            */
    private Float selectCredit;

            /**
            * 完成学分
            */
    private Float finishCredit;

            /**
            * 选课数
            */
    private Integer courNum;
    /**
     * 最后观看课程Id
     */
    private Integer lastCouid;
    /**
    * 最后参加考试Id
    * */
    private Integer lastTestid;

    /**
    * 归属地
    * */
    private String area;

    /**
    * 通过继续教育的时间
    * */
    private String passDate ;

    /**
    * 网络培训年度
    * */
    private String eduYear;
}
