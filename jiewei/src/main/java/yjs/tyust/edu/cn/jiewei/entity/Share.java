package yjs.tyust.edu.cn.jiewei.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author 徐超
 * @since 2019-06-23
 */
public class Share implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "share_id", type = IdType.AUTO)
    private Integer shareId;

    private Integer stuId;

    private Integer vidId;

    /**
     * 平台编号
     */
    private String plat;

    /**
     * 分享时间
     */
    private Date shareTime=new Date();

    private Integer del;

    public Integer getShareId() {
        return shareId;
    }

    public void setShareId(Integer shareId) {
        this.shareId = shareId;
    }
    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }
    public Integer getVidId() {
        return vidId;
    }

    public void setVidId(Integer vidId) {
        this.vidId = vidId;
    }
    public String getPlat() {
        return plat;
    }

    public void setPlat(String plat) {
        this.plat = plat;
    }
    public Date getShareTime() {
        return shareTime;
    }

    public void setShareTime(Date shareTime) {
        this.shareTime = shareTime;
    }
    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }

    @Override
    public String toString() {
        return "Share{" +
                "shareId=" + shareId +
                ", stuId=" + stuId +
                ", vidId=" + vidId +
                ", plat=" + plat +
                ", shareTime=" + shareTime +
                ", del=" + del +
                "}";
    }
}
