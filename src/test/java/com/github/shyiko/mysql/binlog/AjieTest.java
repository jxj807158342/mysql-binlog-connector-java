package com.github.shyiko.mysql.binlog;

import com.github.shyiko.mysql.binlog.event.Event;
import com.github.shyiko.mysql.binlog.io.ByteArrayOutputStream;
import org.junit.Test;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
        String hostname = "";
        int port = 0;
        Socket socket = new Socket();
        socket.connect((SocketAddress) new InetSocketAddress(hostname, port), 3000);
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();
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
        client.registerEventListener(new BinaryLogClient.EventListener() {
            @Override
            public void onEvent(Event event) {
                System.out.println("event.getHeader()==="+event.getHeader());
                System.out.println("event.getData()====="+event.getData());
            }
        });
        client.connect();
    }

    @Test
    public void readBufferTest() {
        int length = 4;
        byte[] buffer = new byte[]{79, 60, 1, 0};
        //                         4F   3c 01  00
        // 左移 i<<3  是因为8位
        // 1 左移3位是 8
        // 60 左移 8位
        //    第一步 i==0
        // i=0  00 00 4F     0000 0000 0000 0000 0100 1111
        // i=1  00 3C 00     0000 0000 0011 1100 0000 0000
        // i=2  01 00 00     0001 0000 0000 0000 0000 0000
        long result = 0;
        for (int i = 0; i < length; ++i) {
            System.out.println("result=" + result);
            result |= (((long) buffer[i]) << (i << 3));
            System.out.println(" result |= (((long) buffer[i]) << (i << 3));="+result);
        }
        System.out.println(result);
        //  ox 00 0134cf
        //       80975
        int result2 = 0;
        for (byte buffer1 : buffer) {
            result2 |= buffer1;
        }
        // 7F
        // 127
        System.out.println(result2);
    }
    @Test
    public void writeBufferTest(){
        // 0x 08 82 04       557572
       // 0x04  0x82  0x08  295432

        int length = 3;
       int value =557572;
        //                         4F   3c 01  00
        // 左移 i<<3  是因为8位
        // 1 左移3位是 8
        // 60 左移 8位
        //    第一步 i==0
        // i=0  00 00 4F     0000 0000 0000 0000 0100 1111
        // i=1  00 3C 00     0000 0000 0011 1100 0000 0000
        // i=2  01 00 00     0001 0000 0000 0000 0000 0000

        for (int i = 0; i < length; ++i) {
            System.out.println("value=" + value);
            int result = 0X000000FF & (value>>> (i << 3));
            System.out.println(" result ="+result);
        }

        //  ox 00 0134cf
        //       80975

    }


    @Test
    public void test12345() throws IOException {
        byte[] bytes = new byte[]{53,46,55,46,52,48,45,108,111,103};
        ByteArrayOutputStream s = new ByteArrayOutputStream();
        for (int i=0; i<bytes.length;i++ ) {
            s.writeInteger((int)bytes[i], 1);
        }
        byte[] bytes1 = s.toByteArray();
        System.out.println(new String(bytes1));
        System.out.println(new String(bytes));
    }
}
