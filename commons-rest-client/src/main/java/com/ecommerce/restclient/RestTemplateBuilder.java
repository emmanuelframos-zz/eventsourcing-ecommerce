package com.ecommerce.restclient;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

final class RestTemplateBuilder {

    /** Determines the timeout in milliseconds until a connection is established **/
    private static final int CONNECT_TIMEOUT = 10000;

    /** The timeout when requesting a connection from the connection manager **/
    private static final int REQUEST_TIMEOUT = 10000;

    /** The timeout for waiting for data **/
    private static final int SOCKET_TIMEOUT = 10000;

    public static RestTemplate build() {
        return new RestTemplate(getClientHttpRequestFactory());
    }

    private static ClientHttpRequestFactory getClientHttpRequestFactory() {
        return new HttpComponentsClientHttpRequestFactory(httpClient());
    }

    private static CloseableHttpClient httpClient() {
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(REQUEST_TIMEOUT)
                .setConnectTimeout(CONNECT_TIMEOUT)
                .setSocketTimeout(SOCKET_TIMEOUT).build();

        return HttpClients.custom()
                .setDefaultRequestConfig(requestConfig)
                .build();
    }
}