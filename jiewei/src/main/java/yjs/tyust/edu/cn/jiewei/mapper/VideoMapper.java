package yjs.tyust.edu.cn.jiewei.mapper;

import yjs.tyust.edu.cn.jiewei.entity.Video;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 视频表 Mapper 接口
 * </p>
 *
 * @author 徐超
 * @since 2019-05-28
 */
public interface VideoMapper extends BaseMapper<Video> {

    List<Video> selectvideonamelist(Integer couid);
}
