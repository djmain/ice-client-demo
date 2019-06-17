package service;


import Ice.Communicator;
import com.ice.hello.HelloService;
import com.ice.hello.HelloServicePrx;
import com.ice.hello.HelloServicePrxHelper;
import com.ice.user.UserService;
import com.ice.user.UserServicePrx;
import com.ice.user.UserServicePrxHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * created by Jay on 2019/6/17
 */
@Configuration
@ComponentScan
public class SpringConfig
{

    @Autowired
    Communicator communicator;


    @Bean
    Communicator communicator()
    {
        Ice.Communicator communicator = Ice.Util.initialize(new String[]{});
        return communicator;
    }

    @Bean
    HelloServicePrx helloServicePrx1() throws Exception
    {
        //传入远程服务单元的名称、网络协议、IP及端口，获取Printer的远程代理，这里使用的stringToProxy方式
        return getHelloServicePrx("1.0");
    }

    @Bean
    HelloServicePrx helloServicePrx2() throws Exception
    {
        return getHelloServicePrx("2.0");

    }

    private HelloServicePrx getHelloServicePrx(String version)
    {
        //传入远程服务单元的名称、网络协议、IP及端口，获取Printer的远程代理，这里使用的stringToProxy方式
        Ice.ObjectPrx objectPrx = communicator.stringToProxy(HelloService.class.getName() + "-" + version + ":default -p 10001");
        //通过checkedCast向下转换，获取Printer接口的远程，并同时检测根据传入的名称获取的服务单元是否Printer的代理接口，如果不是则返回null对象
        HelloServicePrx helloServicePrx = HelloServicePrxHelper.checkedCast(objectPrx);
        if (helloServicePrx == null) throw new Error("Invalid proxy");
        return helloServicePrx;
    }


    @Bean
    UserServicePrx userServicePrx() throws Exception
    {
        Ice.ObjectPrx objectPrx = communicator.stringToProxy(UserService.class.getName() + ":default -p 10001");
        UserServicePrx userServicePrx = UserServicePrxHelper.checkedCast(objectPrx);
        return userServicePrx;
    }
}
