package src.Simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import src.utils.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName sender
 * @Description: TODO
 * @Author hl
 * @Date 2020/5/31
 * @Version V1.0
 **/
public class sender {
    private static final String Queue_NAME="test_simple_queue";
    public static void main(String[] args) throws IOException, TimeoutException {



        //获取队列
        Connection connection= ConnectionUtils.getConnrction();
        //从连接中获取一个通道
        Channel channel=connection.createChannel();
        //创建并声明一个队列
        channel.queueDeclare(Queue_NAME,false,false,false,null);

        String msg="hello simple";

        channel.basicPublish("",Queue_NAME,null,msg.getBytes());

        System.out.println("发送成功");

        channel.close();
        connection.close();

    }
}
