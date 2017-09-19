package com.jy.common.utils;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.X509Certificate;

/**
 * @文件名:HttpClientUtil.java
 * @功能描述：
 * @创建日期:2017年3月28日下午5:54:49
 * @创建人：lijunke
 * @Copyright（c） 2017, all rights reserved by days
 */
public class HttpClientUtil {

    protected static final Logger log = LoggerFactory.getLogger(HttpClientUtil.class);

    public static String htttpPost(String json, String urlApi) {
        StringBuffer sb = null;
        sb = getStringBuffer(json, urlApi, sb);
        return sb.toString();
    }

    private static StringBuffer getStringBuffer(String json, String urlApi, StringBuffer sb) {
        try {
            //创建连接
            URL url = new URL(urlApi);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
            connection.connect();
            //POST请求
            DataOutputStream out = new DataOutputStream(
                    connection.getOutputStream());
            out.writeBytes(json);
            out.flush();
            out.close();
            //读取响应
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String lines;
            sb = new StringBuffer();
            while ((lines = reader.readLine()) != null) {
                sb.append(lines);
            }
            System.out.println(sb);
            reader.close();
            // 断开连接
            connection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb;
    }


    public static String htttpPost(String urlApi) {
        StringBuffer sb = null;
        InputStream in = null;
        BufferedReader reader = null;
        try {
            //创建连接
            URL url = new URL(urlApi);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.connect();
            connection = (HttpURLConnection) url.openConnection();

            connection.setConnectTimeout(6 * 5000);
            in = connection.getInputStream();
            connection.setReadTimeout(10 * 3000);
            log.debug(connection.getResponseCode() + " " + connection.getResponseMessage());

            //读取响应
            reader = new BufferedReader(new InputStreamReader(in,
                    "utf-8"));
            String lines;
            sb = new StringBuffer();
            while ((lines = reader.readLine()) != null) {
                sb.append(lines);
            }
            reader.close();
            in.close(); // 断开连接
            connection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return sb.toString();
    }


    @SuppressWarnings("deprecation")
    public static String post(String json, String url) {
        String response = null;
        try {
            HttpClient httpClient = new HttpClient();
            PostMethod post = new PostMethod(url);
            post.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
            post.setRequestBody(json);
            HttpMethod method = post;
            httpClient.executeMethod(method);
            response = method.getResponseBodyAsString();
            post.releaseConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    final static HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {

        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    };

    public static String httpRequest(String requestUrl) {
        if (!requestUrl.contains("https")) {
            return htttpPost(requestUrl);
        }
        StringBuffer tempStr = new StringBuffer();
        String responseContent = null;
        HttpURLConnection conn = null;
        InputStream in = null;
        BufferedReader rd = null;
        try {
            // Create a trust manager that does not validate certificate chains
            trustAllHosts();

            URL url = new URL(requestUrl);

            HttpsURLConnection https = (HttpsURLConnection) url.openConnection();
            if (url.getProtocol().toLowerCase().equals("https")) {
                https.setHostnameVerifier(DO_NOT_VERIFY);
                conn = https;
            } else {
                conn = (HttpURLConnection) url.openConnection();
            }
            conn.connect();
            log.debug(conn.getResponseCode() + " " + conn.getResponseMessage());
            conn = (HttpURLConnection) url.openConnection();

            conn.setConnectTimeout(20 * 5000);
            conn.setDoOutput(true);

            in = conn.getInputStream();
            conn.setReadTimeout(30 * 3000);
            rd = new BufferedReader(new InputStreamReader(in,"UTF-8"));
            String tempLine="";
            while ((tempLine = rd.readLine()) != null) {
                tempStr.append(tempLine);
            }
            responseContent = tempStr.toString();
            rd.close();
            in.close();
            conn.disconnect();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rd != null) {
                    rd.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        return responseContent;
    }

    /**
     * Trust every server - dont check for any certificate
     */
    private static void trustAllHosts() {

        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {

            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new java.security.cert.X509Certificate[]{};
            }

            public void checkClientTrusted(X509Certificate[] chain, String authType) {

            }

            public void checkServerTrusted(X509Certificate[] chain, String authType) {

            }
        }};

        // Install the all-trusting trust manager
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
