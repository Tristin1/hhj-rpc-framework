package com.hhj.rpc.server;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hhj.rpc.registry.ServiceRegistry;

/**
 * @author yourname <huanghaijin@kuaishou.com>
 * Created on 2023-03-25
 */
public class RpcServer {
    private static final Logger logger = LoggerFactory.getLogger(RpcServer.class);

    private static final int CORE_POOL_SIZE = 5;
    private static final int MAXIMUM_POOL_SIZE = 50;
    private static final int KEEP_ALIVE_TIME = 60;
    private static final int BLOCKING_QUEUE_CAPACITY = 100;
    private RequestHandler requestHandler = new RequestHandler();
    private final ExecutorService threadPool;
    private final ServiceRegistry serviceRegistry;

    @SuppressWarnings("checkstyle:MagicNumber")
    public RpcServer(ServiceRegistry serviceRegistry) {
        this.serviceRegistry = serviceRegistry;
        BlockingQueue<Runnable> workingQueue = new ArrayBlockingQueue<>(BLOCKING_QUEUE_CAPACITY);
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        threadPool = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_TIME, SECONDS, workingQueue,
                threadFactory);
    }

//    public void register(Object service, int port) {
//        try (ServerSocket serverSocket = new ServerSocket(port)) {
//            logger.info("服务器正在启动...");
//            Socket socket;
//            while ((socket = serverSocket.accept()) != null) {
//                logger.info("客户端了解IP为" + socket.getInetAddress());
//                threadPool.execute(new WorkerThread(socket, service));
//            }
//        } catch (IOException e) {
//            logger.error("链接有错误发生");
//        }
//    }

    public void start(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            logger.info("服务器启动");
            Socket socket;
            while ((socket = serverSocket.accept()) != null) {
                logger.info("消费者链接：{}:{}", socket.getInetAddress(), socket.getPort());
                threadPool.execute(new RequestHandlerThread(socket, requestHandler, serviceRegistry));
            }
            threadPool.shutdown();
        } catch (IOException e) {
            logger.error("服务器启动时有错误发生:", e);
        }

    }
}
