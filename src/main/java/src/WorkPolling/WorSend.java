package src.WorkPolling;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import src.utils.ConnectionUtils;

import java.io.IOException;

import java.util.concurrent.TimeoutException;

/**
 * @ClassName WorSend
 * @Description: TODO
 * @Author hl
 * @Date 2020/5/31
 * @Version V1.0
 **/
public class WorSend {
    private final static  String Queue_Name="test_work_queue";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        //创建一个连接
        Connection connection = ConnectionUtils.getConnrction();

        //创建一个通道
        Channel channel= connection.createChannel();
        //声明并创建一个队列
        channel.queueDeclare(Queue_Name,false,false,false,null);

        for(int i=0;i<50;i++){
            String msg="work"+i;
            System.out.println("send 已经发送"+msg);
            channel.basicPublish("",Queue_Name,null,msg.getBytes());
            Thread.sleep(i*20);
        }
        channel.close();
        connection.close();

    }
}
