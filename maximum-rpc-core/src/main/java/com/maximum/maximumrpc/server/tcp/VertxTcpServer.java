package com.maximum.maximumrpc.server.tcp;

import com.maximum.maximumrpc.server.HttpServer;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetServer;

public class VertxTcpServer implements HttpServer {
    private byte[] handleRequest(byte[] requestData){
        return "Hello, Client!".getBytes();
    }

    @Override
    public void doStart(int port) {
        // 创建 Vert.x 实例
        Vertx vertx = Vertx.vertx();

        // 创建 TCP 服务器
        NetServer server = vertx.createNetServer();

        // 处理请求
        server.connectHandler(socket -> {
            //处理连接
            socket.handler(buffer -> {
                //处理接收到的字节数组
                byte[] requestData = buffer.getBytes();
                byte[] responseData = handleRequest(requestData);
                //发送响应
                //向连接到服务器的客户端发送数据
                socket.write(Buffer.buffer(responseData));
            });
        });

        // 启动 TCP 服务器并监听指定端口
        server.listen(port, result -> {
            if(result.succeeded()){
                System.out.println("TCP server started on port " + port);
            }else{
                System.err.println("Failed to start TCP server: " + result.cause());
            }
        });
    }

    public static void main(String[] args) {
        new VertxTcpServer().doStart(8888);
    }
}
