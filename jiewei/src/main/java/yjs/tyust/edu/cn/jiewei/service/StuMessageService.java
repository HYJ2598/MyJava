package yjs.tyust.edu.cn.jiewei.service;

import com.baomidou.mybatisplus.extension.service.IService;
import yjs.tyust.edu.cn.jiewei.entity.StuMessage;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 166
 * @since 2019-06-17
 */
public interface StuMessageService extends IService<StuMessage> {

    List<StuMessage> getByStuId(Long messId);

}
