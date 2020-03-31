package yjs.tyust.edu.cn.jiewei.entity;

    import com.baomidou.mybatisplus.annotation.IdType;
    import com.baomidou.mybatisplus.annotation.TableId;
    import java.io.Serializable;

/**
* <p>
    * 视频表
    * </p>
*
* @author 徐超
* @since 2019-05-28
*/
    public class Video implements Serializable {

    private static final long serialVersionUID = 1L;



    /**
            * 视频编号
            */
            @TableId(value = "vid_id", type = IdType.AUTO)
    private Integer vidId;

            /**
            * 视频名称
            */
    private String vidName;

            /**
            * 视频时长
            */
    private String hour;

            /**
            * 视频地址
            */
    private String path;

            /**
            * 所属课程编号
            */
    private Integer couId;

            /**
            * 删除
            */
    private Integer del;

            /**
            * 排序
            */
    private Integer sort;



    /**
    * pos_time
    * */
    private String posTime;


        public Integer getVidId() {
        return vidId;
        }

            public void setVidId(Integer vidId) {
        this.vidId = vidId;
        }
        public String getVidName() {
        return vidName;
        }

            public void setVidName(String vidName) {
        this.vidName = vidName;
        }
        public String getHour() {
        return hour;
        }

            public void setHour(String hour) {
        this.hour = hour;
        }
        public String getPath() {
        return path;
        }

            public void setPath(String path) {
        this.path = path;
        }
        public Integer getCouId() {
        return couId;
        }

            public void setCouId(Integer couId) {
        this.couId = couId;
        }
        public Integer getDel() {
        return del;
        }

            public void setDel(Integer del) {
        this.del = del;
        }
        public Integer getSort() {
        return sort;
        }

            public void setSort(Integer sort) {
        this.sort = sort;
        }
    public String getPosTime() {
        return posTime;
    }

    public void setPosTime(String posTime) {
        this.posTime = posTime;
    }

    @Override
    public String toString() {
        return "Video{" +
                "vidId=" + vidId +
                ", vidName='" + vidName + '\'' +
                ", hour='" + hour + '\'' +
                ", path='" + path + '\'' +
                ", couId=" + couId +
                ", del=" + del +
                ", sort=" + sort +
                ", posTime='" + posTime + '\'' +
                '}';
    }
}
