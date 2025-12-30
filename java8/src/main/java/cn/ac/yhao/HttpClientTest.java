package cn.ac.yhao;

import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustAllStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.junit.jupiter.api.Test;

import javax.net.ssl.SSLContext;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

/**
 * <span>
 *
 * </span>
 *
 * @author: Daniel Young
 * @create: 2023-10-07 17:42:04
 */
public class HttpClientTest {

    public static final int maxTotal = 100;
    public static final int maxPerRoute = 100;

    public static final int connectTimeout = 50000;  // ms,建立连接超时时间
    public static final int socketTimeout = 20000;  // ms,读取超时时间
    public static final int connectRequestTimeout = 10000;  // ms,从池中获取连接超时时间

    public HttpClient httpClient() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        //忽略https证书验证
        SSLContext sslContext = SSLContextBuilder.create().loadTrustMaterial(new TrustAllStrategy()).build();

        SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);

        //设置不同协议对应的处理socket连接工厂对象
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", socketFactory).build();

        //创建已经注册的连接管理器
        PoolingHttpClientConnectionManager phccm = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        //最大持久连接数，最多接收到的请求数量
        phccm.setMaxTotal(maxTotal);
        //每次并行接收的请求数量
        phccm.setDefaultMaxPerRoute(maxPerRoute);

        SocketConfig defaultSocketConfig = SocketConfig.custom().setSoTimeout(50000).build();
        phccm.setDefaultSocketConfig(defaultSocketConfig);
        ConnectionConfig connectionConfig = ConnectionConfig.custom().setCharset(Consts.UTF_8).build();
        phccm.setDefaultConnectionConfig(connectionConfig);

        //HttpHost proxy = new HttpHost("127.0.0.1", 8880);

        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(connectTimeout)
                .setConnectionRequestTimeout(connectRequestTimeout).setSocketTimeout(socketTimeout)
                //.setProxy(proxy)
                .build();
        return HttpClients.custom().setConnectionManager(phccm).setDefaultRequestConfig(requestConfig).build();
    }

    @Test
    public void test() {
        try {
            HttpClient httpClient = httpClient();
            URIBuilder builder = new URIBuilder("https://127.0.0.1:8880");
            //builder.addParameter("t", "123");

            HttpPost httpPost = new HttpPost(builder.build());
            httpPost.addHeader("X-SCHEME", "https");
            HttpResponse httpResponse = httpClient.execute(httpPost);
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            System.out.println(statusCode);
            InputStream inputStream = httpResponse.getEntity().getContent();

            ByteArrayOutputStream result = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }
            String str = result.toString(StandardCharsets.UTF_8.name());
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
