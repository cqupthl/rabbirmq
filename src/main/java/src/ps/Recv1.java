package src.ps;

import com.rabbitmq.client.*;
import src.utils.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName Recv1
 * @Description: TODO
 * @Author hl
 * @Date 2020/6/7
 * @Version V1.0
 **/
public class Recv1 {
    private static final String QUEUE_NAME="test_queue_fanout_email";
    private static final String EXCHANGE_NAME="test_exchange_fanout";
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection= ConnectionUtils.getConnrction();
        final Channel channel=connection.createChannel();
        //  队列声明
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        //队列绑定交换器
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"");

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
        channel.basicConsume(QUEUE_NAME,autoAck,consumer);




    }
}
