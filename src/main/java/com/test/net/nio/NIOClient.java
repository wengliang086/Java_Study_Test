package com.test.net.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

// TODO 还需要好好理解
public class NIOClient {

    private volatile boolean stop = false;

    public static void main(String[] args) {
        new NIOClient().init();
    }

    private void init() {
        try {
            /*创建选择器*/
            Selector selector = Selector.open();
            /*创建TCP通道*/
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            /*注册感兴趣事件*/
            socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);

            /*向服务器发起连接,一个通道代表一条tcp链接*/
            boolean connected = socketChannel.connect(new InetSocketAddress(8888));
            System.out.println("connected: " + connected);

            System.out.println("socketChannel.finishConnect()=" + socketChannel.finishConnect());
            /*等待三次握手完成*/
            while(!socketChannel.finishConnect()){
                ;
            }
            System.out.println("socketChannel.finishConnect()=" + socketChannel.finishConnect());
            socketChannel.write(ByteBuffer.wrap("Hi Server!".getBytes()));

            while (!stop) {
                int select = selector.select();
                if (select == 0) {
                    continue;
                }
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    iterator.remove();/*防止下次select方法返回已处理过的通道*/

                    SocketChannel client = (SocketChannel) selectionKey.channel();
                    System.out.println(socketChannel == client);
                    if (selectionKey.isConnectable()) {
                        client.register(selector, SelectionKey.OP_READ);
                        doWrite(client, "Hi Server!");
                    }
                    if (selectionKey.isReadable()) {
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        int len = client.read(buffer);
                        if (len > 0) {
                            buffer.flip();
                            String content = new String(buffer.array(), 0, len);
                            System.out.println(content);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void doWrite(SocketChannel socketChannel, String msg) {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put(msg.getBytes());
        buffer.flip();
        try {
            socketChannel.write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
