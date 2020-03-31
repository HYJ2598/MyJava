package yjs.tyust.edu.cn.jiewei.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import yjs.tyust.edu.cn.jiewei.entity.StuMessage;
import yjs.tyust.edu.cn.jiewei.mapper.StuMessageMapper;
import yjs.tyust.edu.cn.jiewei.service.StuMessageService;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 166
 * @since 2019-06-17
 */
@Service
public class StuMessageServiceImpl extends ServiceImpl<StuMessageMapper, StuMessage> implements StuMessageService {

    @Override
    public List<StuMessage> getByStuId(Long messId) {
        return baseMapper.getByStuId(messId);
    }
}
