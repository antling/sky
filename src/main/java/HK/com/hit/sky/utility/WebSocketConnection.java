package HK.com.hit.sky.utility;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

public class WebSocketConnection extends WebSocketClient {
    private static final String WEBSOCKET_URI = "ws://localhost:8080"; // WebSocket服务器的URI

    public WebSocketConnection(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println("WebSocket连接已打开");
    }

    @Override
    public void onMessage(String message) {
        System.out.println("收到消息: " + message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("WebSocket连接已关闭");
        reconnect();
    }

    @Override
    public void onError(Exception ex) {
        System.err.println("WebSocket发生错误: " + ex.getMessage());
        reconnect();
    }

    public void reconnect() {
        try {
            System.out.println("尝试重新连接...");
            this.connect();
        } catch (Exception e) {
            System.err.println("重新连接失败: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        try {
            WebSocketConnection webSocketConnection = new WebSocketConnection(new URI(WEBSOCKET_URI));
            webSocketConnection.connect();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
