package com.qcloud.cos.http;

import java.io.InputStream;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.impl.conn.SchemeRegistryFactory;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.exception.AbstractCosException;
import com.qcloud.cos.exception.ParamException;

public abstract class AbstractCosHttpClient {
    protected ClientConfig config;
    protected HttpClient httpClient;

    protected PoolingClientConnectionManager conMan;
    protected IdleConnectionMonitorThread idleConnectionMonitor;

    public AbstractCosHttpClient(ClientConfig config) {
        super();
        this.config = config;
        this.conMan = new PoolingClientConnectionManager(SchemeRegistryFactory.createDefault());
        conMan.setMaxTotal(config.getMaxConnectionsCount());
        conMan.setDefaultMaxPerRoute(config.getMaxConnectionsCount());
        this.httpClient = new DefaultHttpClient(conMan);
        HttpParams httpParams = this.httpClient.getParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, config.getConnectionTimeout());
        HttpConnectionParams.setSoTimeout(httpParams, config.getSocketTimeout());

        this.idleConnectionMonitor = new IdleConnectionMonitorThread(conMan);
        this.idleConnectionMonitor.setDaemon(true);
        this.idleConnectionMonitor.start();
    }

    protected abstract String sendPostRequest(HttpRequest httpRequest) throws AbstractCosException;

    protected abstract String sendGetRequest(HttpRequest httpRequest) throws AbstractCosException;

    public String sendHttpRequest(HttpRequest httpRequest) throws AbstractCosException {

        HttpMethod method = httpRequest.getMethod();
        if (method == HttpMethod.POST) {
            return sendPostRequest(httpRequest);
        } else if (method == HttpMethod.GET) {
            return sendGetRequest(httpRequest);
        } else {
            throw new ParamException("Unsupported Http Method");
        }
    }

    public abstract InputStream getFileInputStream(HttpRequest httpRequest)
            throws AbstractCosException;

    public void shutdown() {
        this.idleConnectionMonitor.shutdown();
    }
}
