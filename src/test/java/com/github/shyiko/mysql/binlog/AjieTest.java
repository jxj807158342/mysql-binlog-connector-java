package com.github.shyiko.mysql.binlog;

import org.junit.Test;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

public class AjieTest {
    public static void main(String[] args) throws NoSuchAlgorithmException, KeyManagementException, IOException {
//        Socket socket =null;
//        SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
//        sslContext.init(null,null,null);
//        SSLSocketFactory socketFactory = sslContext.getSocketFactory();
//        socketFactory.createSocket(socket,socket.getInetAddress().getHostName(),socket.getPort(),true);
//        BinaryLogClient binaryLogClient = new BinaryLogClient();
//        binaryLogClient.connect();
        String hostname="";
        int port =0;
        Socket socket = new Socket();
        socket.connect((SocketAddress) new InetSocketAddress(hostname,port), 3000);
    }
    @Test
    public void test() throws IOException {
//        int value=557572;
//        int length =4;
//        for (int i = 0; i < length; i++) {
//            System.out.println("i << 3=="+(i << 3));
//           System.out.println("value>>>(i<<3)===="+(value>>>(i<<3)));
//           System.out.println("0x000000FF &(value>>>(i<<3))="+(0x000000FF &(value>>>(i<<3))));
//
//        }
        BinaryLogClient client = new BinaryLogClient("47.92.89.144", 3306, "root", "Hive@20221101!");
        client.setServerId(12345);
        client.connect();
    }
}
