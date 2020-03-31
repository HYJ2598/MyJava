package yjs.tyust.edu.cn.jiewei.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import yjs.tyust.edu.cn.jiewei.entity.AdminMessage;
import yjs.tyust.edu.cn.jiewei.mapper.AdminMessageMapper;
import yjs.tyust.edu.cn.jiewei.service.AdminMessageService;

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
public class AdminMessageServiceImpl extends ServiceImpl<AdminMessageMapper, AdminMessage> implements AdminMessageService {

    @Override
    public List<AdminMessage> listByMessId(Long messId) {
        return baseMapper.listByMessId(messId);
    }
}
