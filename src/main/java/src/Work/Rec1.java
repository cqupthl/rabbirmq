package src.Work;

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
        Channel channel = connrction.createChannel();

        channel.queueDeclare(Queue_Name,false,false,false,null);

        new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                //这里
            }
        };


    }
}
