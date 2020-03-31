package yjs.tyust.edu.cn.jiewei.service;

import yjs.tyust.edu.cn.jiewei.entity.Video;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 视频表 服务类
 * </p>
 *
 * @author 徐超
 * @since 2019-05-28
 */
public interface VideoService extends IService<Video> {
    List<Video> selectvideonamelist(Integer couid);
}
