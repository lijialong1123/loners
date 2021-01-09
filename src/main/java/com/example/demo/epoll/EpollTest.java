package com.example.demo.epoll;

import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/**
 * @author : coder
 * @create 2021/1/8 14:37
 */
public class EpollTest {

    private ServerSocketChannel server = null;
    private Selector selector = null;

    public void initServer(){
        try {
            server = ServerSocketChannel.open();
            server.configureBlocking(false);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
