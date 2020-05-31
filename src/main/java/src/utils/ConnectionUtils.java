package src.utils;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName ConnectionUtils
 * @Description: TODO
 * @Author hl
 * @Date 2020/5/31
 * @Version V1.0
 **/
public class ConnectionUtils {

    public static Connection getConnrction() throws IOException, TimeoutException {
        //创建连接工厂
        ConnectionFactory factory=new ConnectionFactory();
        //设置ip地址
        factory.setHost("127.0.0.1");
        //设置端口号
        factory.setPort(5672);
        //声明那个vhost
        factory.setVirtualHost("/vhost_mmr");

        factory.setUsername("user_hl");
        factory.setPassword("123");

        return   factory.newConnection();
    }

}
