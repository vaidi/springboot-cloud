package xxh.config;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Configurable;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author: elyuan
 * @Date: 2020/8/26 10:50 上午
 */
@Configurable
public class FactoryConfigure {


    public ConnectionFactory getConnectionFactory() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("");
        factory.setPassword("");
        factory.setVirtualHost("");
        factory.setHost("");
        factory.setPort(1570);
        return factory;
    }

    public Connection getConnection(ConnectionFactory factory) throws IOException, TimeoutException {
        return factory.newConnection();
    }






}
