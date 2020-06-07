package src.WorkPolling;

import com.rabbitmq.client.*;
import src.utils.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName Rec1
 * @Description: TODO
 * @Author hl
 * @Date 2020/5/31
 * @Version V1.0
 **/
public class Rec2 {
    private final static  String Queue_Name="test_work_queue";
    public static void main(String[] args) throws IOException, TimeoutException {

        Connection connrction = ConnectionUtils.getConnrction();
        Channel channel = connrction.createChannel();

        channel.queueDeclare(Queue_Name,false,false,false,null);

    Consumer consumer= new DefaultConsumer(channel){
            //有消息就会触发
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg=new String(body,"utf-8");
                System.out.println("[2] 收到："+msg);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    System.out.println("[2] 已经完成");
                }
            }
        };
        boolean autoAck=true;
        channel.basicConsume(Queue_Name,autoAck,consumer);

    }
}
