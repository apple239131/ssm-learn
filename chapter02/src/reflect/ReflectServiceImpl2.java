package reflect;

import java.sql.SQLSyntaxErrorException;

public class ReflectServiceImpl2 {
    private String name;

    public ReflectServiceImpl2(String name) {
        this.name = name;
    }

    public void sayHello() {
        System.out.println("hello "+name);
    }
}
