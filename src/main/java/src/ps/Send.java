package src.ps;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import src.utils.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName Send
 * @Description: TODO
 * @Author hl
 * @Date 2020/6/7
 * @Version V1.0
 **/
public class Send {
    private static final String EXCHANGE_NAME="test_exchange_fanout";

    public static void main(String[] args) throws IOException, TimeoutException {

        Connection connection=ConnectionUtils.getConnrction();
        Channel channel=connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");//声明交换机

        String msg="hello ps";
        channel.basicPublish(EXCHANGE_NAME,"",
                null,msg.getBytes());
        System.out.println("发送 ："+msg);

        channel.close();
        connection.close();

    }
}
