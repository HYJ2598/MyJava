package yjs.tyust.edu.cn.jiewei.entity;

    import java.io.Serializable;

/**
* <p>
    * 
    * </p>
*
* @author 徐超
* @since 2019-07-01
*/
    public class Testname implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

        public Integer getId() {
        return id;
        }

            public void setId(Integer id) {
        this.id = id;
        }
        public String getName() {
        return name;
        }

            public void setName(String name) {
        this.name = name;
        }

    @Override
    public String toString() {
    return "Testname{" +
            "id=" + id +
            ", name=" + name +
    "}";
    }
}
