package yjs.tyust.edu.cn.jiewei.service;

import com.baomidou.mybatisplus.extension.service.IService;
import yjs.tyust.edu.cn.jiewei.entity.AdminMessage;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 166
 * @since 2019-06-17
 */
public interface AdminMessageService extends IService<AdminMessage> {

    List<AdminMessage> listByMessId(Long messId);
}
