package com.snn.rabbitmq.confirm;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.snn.rabbitmq.util.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @className Send
 * @Author SNN
 * @Date 2019/7/19 15:45
 * Confirm批量模式
 **/
public class Send1 {

    private static final String QUEUE_NAME = "test_queue_confirm";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {

        Connection connection = ConnectionUtils.getConnection();

        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null); //分发

        String msg = "hello confirm \n";

        // 生产者调用confirmSelect将chanel设置为confirm模式
        channel.confirmSelect();

        for (int i = 0; i < 10; i++) {
            channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
        }



        if(!channel.waitForConfirms()) {
            System.out.println("smg send Falid!!!");
        } else {
            System.out.println("smg send Success!!!");
        }

        channel.close();
        connection.close();
    }
}
