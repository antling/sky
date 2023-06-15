package HK.com.hit.sky.utility;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

public class WebSocketConnectionTest {

    public static void main(String[] args) {
        String serverUrl = "ws://localhost:8080"; // WebSocket服务器的URL

        try {
            WebSocketClient client = new WebSocketClient(new URI(serverUrl)) {
                @Override
                public void onOpen(ServerHandshake handshake) {
                    System.out.println("Connected to WebSocket server");
                }

                @Override
                public void onMessage(String message) {
                    System.out.println("Received message: " + message);
                }

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    System.out.println("WebSocket connection closed");
                }

                @Override
                public void onError(Exception ex) {
                    ex.printStackTrace();
                }
            };

            client.connect();

            // 发送一条消息到服务器
            client.send("Hello, WebSocket Server!");

            // 在连接建立后等待一段时间，然后关闭连接
            TimeUnit.SECONDS.sleep(10);
//            client.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
