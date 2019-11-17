import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class Test {
    public static void main(String[] args){
        try {
            Class c = Class.forName("java.lang.String");
            c = java.lang.String.class;
            c = "asdfsdaf".getClass();

//            c.getName();
//            c.getSuperclass().getName();
//            c.getInterfaces();
            Object o = c.newInstance();
            Constructor[] t = c.getConstructors();

            Method[] m = c.getMethods();
            Method mm = c.getMethod("split",String.class);
            mm.invoke("","asdfa,sdf");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
