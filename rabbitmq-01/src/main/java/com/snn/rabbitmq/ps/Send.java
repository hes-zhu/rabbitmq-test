package com.snn.rabbitmq.ps;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.snn.rabbitmq.util.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @className Send
 * @Author SNN
 * @Date 2019/7/19 15:45
 * 发布订阅模式
 **/
public class Send {

    private static final String EXCHANGE_NAME = "test_exchange_queue";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {

        Connection connection = ConnectionUtils.getConnection();

        Channel channel = connection.createChannel();

        // 声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout"); //分发

        String msg = "hello EXChange";
        channel.basicPublish(EXCHANGE_NAME, "", null, msg.getBytes());

        System.out.println("Send: "+msg);

        channel.close();
        connection.close();
    }
}
