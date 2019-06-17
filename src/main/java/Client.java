
import com.ice.hello.HelloServicePrx;
import com.ice.user.UserServicePrx;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.SpringConfig;

/**
 * created by Jay on 2019/6/17
 */
public class Client
{
    public static void main(String[] args)
    {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        HelloServicePrx helloServicePrx1 = (HelloServicePrx) context.getBean("helloServicePrx1");
        String result1 = helloServicePrx1.hello("dj");
        System.out.println("result1:" + result1);



        HelloServicePrx helloServicePrx2 = (HelloServicePrx) context.getBean("helloServicePrx2");
        String result2 = helloServicePrx2.hello("dj");
        System.out.println("result2:" + result2);
        UserServicePrx userServicePrx = (UserServicePrx) context.getBean("userServicePrx");
        String name = userServicePrx.getUserName("1");
        System.out.println("name:" + name);
    }
}
