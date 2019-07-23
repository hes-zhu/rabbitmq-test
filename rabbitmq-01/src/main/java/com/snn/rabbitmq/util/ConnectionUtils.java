package com.snn.rabbitmq.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @className ConnectionUtils
 * @Author SNN
 * @Date 2019/7/19 14:48
 **/
public class ConnectionUtils {

    public static Connection getConnection() throws IOException, TimeoutException {
        // 定义一个连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        // AMQP协议端口
        factory.setPort(5672);
        factory.setVirtualHost("/vhost");
        factory.setUsername("snn");
        factory.setPassword("@huqiao123");

        return factory.newConnection();
    }
}
