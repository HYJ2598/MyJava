package yjs.tyust.edu.cn.jiewei.Result;

import lombok.Data;

import java.util.List;

@Data
public class TableResult<T> {

    private int code;
    private String msg;
    private Integer count;
    private List<T> data;
}
