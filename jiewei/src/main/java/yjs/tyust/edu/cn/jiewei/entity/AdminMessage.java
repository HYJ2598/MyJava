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
 * 管理员的 我的消息表对应实体
 * 对应数据库中admin_message表
 * </p>
 *
 * @author 贾焱鑫
 * @since 2019-06-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AdminMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    /**
     * 问题id
     */
    private Long messId;

    /**
     * 学生id
     */
    private Integer stuId;

    /**
     * 管理员id
     */
    private Integer adId;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 0未回复 1回复 多余字段
     */
    private int flag;
    /**
    * 当前时间
    * */
    private Date times =new Date();

    @TableField(exist = false)
    private Student student;

    @TableField(exist = false)
    private Admin admin;
}
