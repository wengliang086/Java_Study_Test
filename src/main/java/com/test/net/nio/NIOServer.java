package com.test.net.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

// TODO 还需要好好理解
public class NIOServer {

    public static void main(String[] args) {
        new NIOServer().init();
    }

    private static final int port = 8888;
    private static final int backlog = 1024;
    private ServerSocketChannel serverSocketChannel = null;

    private void init() {
        Selector selector = null;
        try {
            /*创建选择器*/
            selector = Selector.open();
            /*创建服务器通道*/
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            /*设置监听服务器的端口，设置最大连接缓冲数为1024*/
//            serverSocketChannel.socket().bind(new InetSocketAddress(port), backlog);
            serverSocketChannel.bind(new InetSocketAddress(port), backlog);

            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("服务器启动成功");
            listen(selector);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (selector != null) {
                    selector.close();
                }
                if (serverSocketChannel != null) {
                    serverSocketChannel.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void listen(Selector selector) {
        while (true) {
            try {
                int select = selector.select();
                if (select == 0) {
                    continue;
                }
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    /*防止下次select方法返回已处理过的通道*/
                    iterator.remove();

                    try {
                        if (selectionKey.isAcceptable()) {
                            ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();
                            System.out.println("ServerSocketChannel.open() == selectionKey.channel() ? : " + (serverSocketChannel == server));

                            SocketChannel socketChannel = server.accept();
                            socketChannel.configureBlocking(false);
                            System.out.println("客户端连接：" + socketChannel.getLocalAddress());
                            System.out.println("客户端连接：" + socketChannel.getRemoteAddress());

                            socketChannel.register(selector, SelectionKey.OP_READ);
                        }
                        /*（普通）通道感兴趣读事件且有数据可读*/
                        if (selectionKey.isReadable()) {
                            System.out.println("selectionKey.isReadable()");
                            SocketChannel client = (SocketChannel) selectionKey.channel();
                            ByteBuffer buffer = ByteBuffer.allocate(1024);
                            int len = client.read(buffer);
                            if (len > 0) {
                                buffer.flip();
                                String content = new String(buffer.array(), 0, len);
                                System.out.println(content);
                                client.write(ByteBuffer.wrap("Hello World".getBytes()));
                                client.register(selector, SelectionKey.OP_WRITE);
                            }
                        }
                        /*通道感兴趣写事件且底层缓冲区有空闲*/
                        if (selectionKey.isWritable()) {
                            System.out.println("selectionKey.isWritable()");
                            SocketChannel client = (SocketChannel) selectionKey.channel();
                            client.write(ByteBuffer.wrap("Hello World".getBytes()));
//                        client.register(selector, SelectionKey.OP_READ);
                            client.close();
                        }
                    } catch (Exception e) {
                        /*若客户端连接出现异常，从Seletcor中移除这个key*/
                        selectionKey.cancel();
                        selectionKey.channel().close();
                        System.out.println("---------客户端掉线---------");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
