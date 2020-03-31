package yjs.tyust.edu.cn.jiewei.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yjs.tyust.edu.cn.jiewei.entity.Video;
import yjs.tyust.edu.cn.jiewei.mapper.VideoMapper;
import yjs.tyust.edu.cn.jiewei.service.VideoService;

import java.util.List;

/**
 * <p>
 * 视频表 服务实现类
 * </p>
 *
 * @author 徐超
 * @since 2019-05-28
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

    @Autowired
    private VideoMapper videoMapper;

    @Override
    public List<Video> selectvideonamelist(Integer couid) {
        List<Video> video = videoMapper.selectvideonamelist(couid);
        return video;
    }
}
