package src.WorkFail;

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
public class Rec1 {
    private final static  String Queue_Name="test_work_queue";
    public static void main(String[] args) throws IOException, TimeoutException {

        Connection connrction = ConnectionUtils.getConnrction();
        final Channel channel = connrction.createChannel();
        boolean durable=false;
        channel.queueDeclare(Queue_Name,durable,false,false,null);
        channel.basicQos(1);//保证每次只发送一个

    Consumer consumer= new DefaultConsumer(channel){
            //有消息就会触发
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg=new String(body,"utf-8");
                System.out.println("[1] 收到："+msg);

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    System.out.println("[1] 已经完成");
                    //发送完成以后，要告诉发送者已经发送完成
                    channel.basicAck(envelope.getDeliveryTag(),false);

                }
            }
        };
        boolean autoAck=false;
        channel.basicConsume(Queue_Name,autoAck,consumer);

    }
}
