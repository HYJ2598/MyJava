package yjs.tyust.edu.cn.jiewei.service.impl;

import yjs.tyust.edu.cn.jiewei.entity.SelCou;
import yjs.tyust.edu.cn.jiewei.mapper.SelCouMapper;
import yjs.tyust.edu.cn.jiewei.service.SelCouService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 选课表（关系表）
包含：编号，学生编号，课程编号，最后观看到的视频位置 服务实现类
 * </p>
 *
 * @author 徐超
 * @since 2019-06-04
 */
@Service
public class SelCouServiceImpl extends ServiceImpl<SelCouMapper, SelCou> implements SelCouService {

}
