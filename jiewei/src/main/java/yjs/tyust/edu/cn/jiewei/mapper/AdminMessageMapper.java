package yjs.tyust.edu.cn.jiewei.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import yjs.tyust.edu.cn.jiewei.entity.AdminMessage;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 166
 * @since 2019-06-17
 */
public interface AdminMessageMapper extends BaseMapper<AdminMessage> {

    List<AdminMessage> listByMessId(Long messId);
}
