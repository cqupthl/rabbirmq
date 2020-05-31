package src.Simple;

import com.rabbitmq.client.*;
import src.utils.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName Reciver
 * @Description: TODO
 * @Author hl
 * @Date 2020/5/31
 * @Version V1.0
 **/
public class Reciver {

    private static final String Queue_NAME="test_simple_queue";

    public static void main(String[] args) throws IOException, TimeoutException {

        Connection connection= ConnectionUtils.getConnrction();

        Channel channel = connection.createChannel();

        //队列声明
        channel.queueDeclare(Queue_NAME,false,false,false,null);

        DefaultConsumer consumer = new DefaultConsumer(channel) {
            //这是一个回调函数，当消息队列中消息时，就会触发这个函数去获取消息
            @Override
            public void handleDelivery(String consumerTag,
                                       Envelope envelope,
                                       AMQP.BasicProperties properties,
                                       byte[] body) throws IOException {
                String msg = new String(body, "utf-8");
                System.out.println("收到: " + msg);
            }
        };
        //监听队列
        channel.basicConsume(Queue_NAME,true,consumer);


    }

}
