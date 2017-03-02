package com.qcloud.cos;

public class ClientConfig {
    // cos server的上传域名前缀, 用来表明使用的协议
    private static final String UPLOAD_COS_ENDPOINT_PREFIX = "http://";
    // cos server的上传域名地址
    private static final String UPLOAD_COS_ENDPOINT_DOMAIN = "sh.file.myqcloud.com";
    // cos server的上传域名的后缀
    private static final String UPLOAD_COS_ENDPOINT_SUFFIX = "/files/v2";
    // cos server的下载域名的前缀
    private static final String DOWN_COS_ENDPOINT_PREFIX = "http://";
    // cos server的下载域名地址
    private static final String DOWN_COS_ENDPOINT_DOMAIN = "cossh.myqcloud.com";
    // 多次签名的默认过期时间,单位秒
    private static final int DEFAULT_SIGN_EXPIRED = 300;
    // 默认的最大重试次数(发生了socketException时)
    private static final int DEFAULT_MAX_RETRIES = 3;
    // 默认的获取连接的超时时间
    private static final int DEFAULT_CONNECTION_REQUEST_TIMEOUT = -1;
    // 默认连接超时, 单位ms
    private static final int DEFAULT_CONNECTION_TIMEOUT = 30 * 1000;
    // 默认的SOCKET读取超时时间, 默认毫秒
    private static final int DEFAULT_SOCKET_TIMEOUT = 30 * 1000;
    // 默认的维护最大HTTP连接数
    private static final int DEFAULT_MAX_CONNECTIONS_COUNT = 100;
    // 默认的user_agent标识
    private static final String DEFAULT_USER_AGENT = "cos-java-hadoop-sdk-v4.2";

    private String uploadCosEndPointPrefix = UPLOAD_COS_ENDPOINT_PREFIX;
    private String uploadCosEndPointDomain = UPLOAD_COS_ENDPOINT_DOMAIN;
    private String uploadCosEndPointSuffix = UPLOAD_COS_ENDPOINT_SUFFIX;
    private String downCosEndPointPrefix = DOWN_COS_ENDPOINT_PREFIX;
    private String downCosEndPointDomain = DOWN_COS_ENDPOINT_DOMAIN;
    private int signExpired = DEFAULT_SIGN_EXPIRED;
    private int maxFailedRetry = DEFAULT_MAX_RETRIES;
    private int connectionRequestTimeout = DEFAULT_CONNECTION_REQUEST_TIMEOUT;
    private int connectionTimeout = DEFAULT_CONNECTION_TIMEOUT;
    private int socketTimeout = DEFAULT_SOCKET_TIMEOUT;
    private int maxConnectionsCount = DEFAULT_MAX_CONNECTIONS_COUNT;
    private String userAgent = DEFAULT_USER_AGENT;
    private boolean useCDN = true; // 默认通过CDN进行下载, 如果为false, 则从源站下载

    public int getMaxFailedRetry() {
        return maxFailedRetry;
    }

    public void setMaxFailedRetry(int maxFailedRetry) {
        this.maxFailedRetry = maxFailedRetry;
    }


    public int getSignExpired() {
        return signExpired;
    }

    public void setSignExpired(int signExpired) {
        this.signExpired = signExpired;
    }

    public int getConnectionRequestTimeout() {
        return connectionRequestTimeout;
    }

    public void setConnectionRequestTimeout(int connectionRequestTimeout) {
        this.connectionRequestTimeout = connectionRequestTimeout;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public int getSocketTimeout() {
        return socketTimeout;
    }

    public void setSocketTimeout(int socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

    public int getMaxConnectionsCount() {
        return maxConnectionsCount;
    }

    public void setMaxConnectionsCount(int maxConnectionsCount) {
        this.maxConnectionsCount = maxConnectionsCount;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public String getUploadCosEndPointDomain() {
        return uploadCosEndPointDomain;
    }

    public String getDownCosEndPointDomain() {
        return downCosEndPointDomain;
    }

    public String getUploadCosEndPointPrefix() {
        return uploadCosEndPointPrefix;
    }

    public String getUploadCosEndPointSuffix() {
        return uploadCosEndPointSuffix;
    }

    public String getDownCosEndPointPrefix() {
        return downCosEndPointPrefix;
    }
    
    public boolean isUseCDN() {
        return useCDN;
    }

    // 设置存储所在的园区
    public void setRegion(String region) {
        this.uploadCosEndPointDomain = region + ".file.myqcloud.com";
        this.downCosEndPointDomain = "cos" + region + ".myqcloud.com";
    }

    // 设置上传的行为
    public void setUploadAction(boolean enableHttps) {
        if (enableHttps) {
            this.uploadCosEndPointPrefix = "https://";
        } else {
            this.uploadCosEndPointPrefix = "http://";
        }
    }

    public void setDownloadAction(boolean enableHttps, boolean useCDN) {
        if (enableHttps) {
            this.downCosEndPointPrefix = "https://";
        } else {
            this.downCosEndPointPrefix = "http://";
        }

        this.useCDN = useCDN;
    }
    
}
