package demo.util;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class HttpUtil {
  public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
  public static final MediaType PROTOBUF = MediaType.parse("application/x-protobuf");

  private static final long CONNECT_TIMEOUT = 60L;
  private static final long READ_TIMEOUT = 60L;

  private static final OkHttpClient okHttpClient =
      new OkHttpClient.Builder().connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
          .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS).build();

  public static String post(String url, String requestBody) {
    Request request = new Request.Builder()
        //.addHeader("Content-Encoding", "snappy")
        .header("X-Prometheus-Remote-Write-Version", "0.1.0").url(url)
        .post(RequestBody.create(PROTOBUF, requestBody)).build();
    Response response;
    try {
      response = okHttpClient.newCall(request).execute();
    } catch (IOException e) {
      System.err.println(e.getLocalizedMessage());
      return null;
    }

    int code = response.code();

    try {
      String body = response.body().string();
      if (code != 200) {
        System.err.println(
            "Failed to send http post request! Unexpected code: " + code + ", message " + body);
        return null;
      }
      return body;
    } catch (IOException e) {
      System.err.println(e.getLocalizedMessage());
      return null;
    }
  }

}

