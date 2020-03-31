package yjs.tyust.edu.cn.jiewei.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 学生的 我的消息表对应实体
 * 对应数据库中stu_message表
 * </p>
 *
 * @author 贾焱鑫
 * @since 2019-06-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class StuMessage implements Serializable {

    private static final long serialVersionUID = 1L;


    @TableId(value = "mess_id",type = IdType.AUTO)
    private Long messId;

    /**
     * 学生id
     */
    private Integer stuId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 0未回复 1回复
     */
    private int flag;
    /**
     * 当前时间
     * */
    private Date times =new Date();

    @TableField(exist = false)
    private Student student;

}
