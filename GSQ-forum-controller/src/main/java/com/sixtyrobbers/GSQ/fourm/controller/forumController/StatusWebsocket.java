package com.sixtyrobbers.GSQ.fourm.controller.forumController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <pre>
 * 说    明:
 * 涉及版本: V1.0.0
 * 创 建 者: Holen
 * 日    期: 2019/3/12 13:32
 * 联系方式: 317776764
 * </pre>
 */
@ServerEndpoint("/statusWebsocket/{equipmentId}")
@Component
public class StatusWebsocket {

    /**
     * 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
     */
    private static int onlineCount = 0;

    public static Map<String, StatusWebsocket> clients = new ConcurrentHashMap<String, StatusWebsocket>();

    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;

    private String equipmentId;

    Logger logger = LoggerFactory.getLogger(StatusWebsocket.class);

    /**
     * 连接建立成功调用的方法
     *
     * @param session 可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    @OnOpen
    public void onOpen(@PathParam("equipmentId") String equipmentId, Session session) {

        this.equipmentId = equipmentId;
        this.session = session;
        //在线数加1
        addOnlineCount();
        //加入map中
        clients.put(equipmentId, this);
        logger.info("有新连接加入！当前在线人数为" + getOnlineCount());

    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        //在线数减1
        subOnlineCount();
        //从map中删除
        clients.remove(equipmentId);
        logger.info("有一连接关闭！当前在线人数为" + getOnlineCount());

    }

    @OnMessage
    public void onMessage(String message) throws IOException {


    }

    /**
     * 发生错误时调用
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }


    public static void sendMessage(String message, String equipmentId) throws IOException {
        if (clients.get(equipmentId) != null){
            clients.get(equipmentId).session.getBasicRemote().sendText(message);
        }
        //this.session.getAsyncRemote().sendText(message);
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        StatusWebsocket.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        StatusWebsocket.onlineCount--;
    }
}
