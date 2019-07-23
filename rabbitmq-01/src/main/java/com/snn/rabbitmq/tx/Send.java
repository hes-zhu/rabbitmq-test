package com.snn.rabbitmq.tx;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.snn.rabbitmq.util.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @className Send
 * @Author SNN
 * @Date 2019/7/19 15:45
 * 消息确认模式，降低了消息的吞吐量
 **/
public class Send {

    private static final String QUEUE_NAME = "test_queue_tx";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {

        Connection connection = ConnectionUtils.getConnection();

        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null); //分发

        String msg = "hello tx msg。。。";

        try {
            channel.txSelect();
            channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
//            int x = 1/0;
            System.out.println("Send: "+msg);
            channel.txCommit();
        } catch (Exception e) {
            channel.txRollback();
            System.out.println("Send: msg txrollback!!!");
        }

        channel.close();
        connection.close();
    }
}
