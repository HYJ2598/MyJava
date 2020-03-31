package yjs.tyust.edu.cn.jiewei.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import yjs.tyust.edu.cn.jiewei.entity.Admin;
import yjs.tyust.edu.cn.jiewei.mapper.AdminMapper;
import yjs.tyust.edu.cn.jiewei.service.AdminService;

/**
 * <p>
 * 管理员表 服务实现类
 * </p>
 *
 * @author woyuno
 * @since 2019-05-31
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

}
