package com.codepay.register.sdk.client;

import static com.codepay.register.sdk.util.Constants.ECR_HUB_TOPIC_PAIR;
import static com.codepay.register.sdk.util.Constants.ECR_HUB_TOPIC_UNPAIR;
import static com.codepay.register.sdk.util.Constants.HEART_BEAT_TOPIC;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.codepay.register.sdk.client.payment.Payment;
import com.codepay.register.sdk.device.ECRHubDevice;
import com.codepay.register.sdk.listener.ECRHubConnectListener;
import com.codepay.register.sdk.listener.ECRHubResponseCallBack;
import com.codepay.register.sdk.util.ECRHubMessageData;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

/**
 * @author pupan
 */
public class ECRHubClient {

    private static final String TAG = "ECRHubClient";
    //server config
    private ECRHubConfig config;
    // listener
    private ECRHubConnectListener connectListener;
    // server ip
    private String ipAddress;
    private WebSocketClient webSocketClient;
    public Payment payment;
    ECRHubResponseCallBack pairCallBack;

    private static ECRHubClient instance;

    public static ECRHubClient getInstance() {
        if (null == instance) {
            instance = new ECRHubClient();
        }
        return instance;
    }

    public void init(ECRHubConfig config, ECRHubConnectListener listener) {
        this.config = config;
        this.connectListener = listener;
    }

    public void requestUnPair(ECRHubDevice deviceData, ECRHubResponseCallBack callBack) {
        pairCallBack = callBack;
        if (isConnected()) {
            ECRHubMessageData data = new ECRHubMessageData();
            data.getDevice_data().setDevice_name(deviceData.getName());
            data.getDevice_data().setMac_address(deviceData.getWs_address());
            data.getDevice_data().setPort(deviceData.getPort());
            data.getDevice_data().setIp_address(deviceData.getIp_address());
            data.getDevice_data().setAlias_name(deviceData.getName());
            data.setTopic(ECR_HUB_TOPIC_UNPAIR);
            webSocketClient.send(JSON.toJSON(data).toString());
        }
    }

    /**
     * 初始化websocket连接
     */
    private void initSocketClient() {
        URI uri = URI.create(ipAddress);
        webSocketClient = new WebSocketClient(uri) {
            @Override
            public void onMessage(String message) {
                ECRHubMessageData data = JSON.parseObject(message, ECRHubMessageData.class);
                if (data.getTopic().equals(ECR_HUB_TOPIC_PAIR) || data.getTopic().equals(ECR_HUB_TOPIC_UNPAIR)) {
                    pairCallBack.onSuccess(JSON.toJSON(data).toString());
                } else if (!data.getTopic().equals(HEART_BEAT_TOPIC)) {
                    if (null != payment && null != payment.getResponseCallBack()) {
                        payment.getResponseCallBack().onSuccess(JSON.toJSON(data).toString());
                    }
                }
            }

            @Override
            public void onOpen(ServerHandshake handshakedata) {
                Log.e(TAG, "websocket connect success");
                if (null != connectListener) {
                    connectListener.onConnect();
                }
            }

            @Override
            public void onError(Exception ex) {
                Log.e(TAG, "websocket connect error：" + ex);
                if (null != connectListener) {
                    connectListener.onError(ex.getLocalizedMessage(), ex.getMessage());
                }
            }

            @Override
            public void onClose(int code, String reason, boolean remote) {
                Log.e(TAG, "websocket disconect：·code:" + code + "·reason:" + reason + "·remote:" + remote);
                if (null != connectListener) {
                    connectListener.onDisconnect();
                }
            }
        };
        if (config.getConnectionTimeout() != 0) {
            webSocketClient.setConnectionLostTimeout(config.getConnectionTimeout());
        }
        payment = new Payment((webSocketClient));
    }

    public boolean isConnected() {
        return null != webSocketClient && webSocketClient.isOpen();
    }

    /**
     * connect
     */
    public void connect() {
        if (null == webSocketClient) {
            initSocketClient();
        }
        if (webSocketClient.isOpen()) {
            return;
        }
        webSocketClient.connect();
    }

    public void connect(String ip) {
        ipAddress = ip;
        connect();
    }

    public void disConnect() {
        if (null == webSocketClient || !webSocketClient.isOpen()) {
            return;
        }
        webSocketClient.close();
    }
}
