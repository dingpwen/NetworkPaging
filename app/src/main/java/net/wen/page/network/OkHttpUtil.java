package net.wen.page.network;

import android.text.TextUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpUtil {
    public static OkHttpClient sHttpClient = new OkHttpClient();

    public static String addParamToUrl(String url, Map params){
        HttpUrl.Builder httpUrl = Objects.requireNonNull(HttpUrl.parse(url)).newBuilder();
        Map<String, String> map = new HashMap<String, String>(params);
        for(Map.Entry<String,String> entry:map.entrySet()){
            httpUrl.addQueryParameter(entry.getKey(),entry.getValue());
        }
        return httpUrl.build().toString();
    }

    public static void baseGet(final String url, final Map params, final Callback callback) {
        OkHttpClient client = sHttpClient;
        String httpUrl = addParamToUrl(url, params);
        Request request = new Request.Builder()
                .url(httpUrl)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public static String baseSyncGet(final String url, final Map params) {
        String result = null;
        OkHttpClient client = sHttpClient;
        String httpUrl = addParamToUrl(url, params);
        Request request = new Request.Builder()
                .url(httpUrl)
                .build();
        //client.newCall(request).enqueue(callback);
        Response response = null;
        try{
            response = client.newCall(request).execute();
            if(response.isSuccessful()){
                result = Objects.requireNonNull(response.body()).string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void basePost(final String url, final Map params, final Callback callback) {
        OkHttpClient client = sHttpClient;
        FormBody.Builder formBody = new FormBody.Builder();
        Map<String, String> map = new HashMap<String, String>(params);
        for(Map.Entry<String,String> entry:map.entrySet()){
            formBody.add(entry.getKey(),entry.getValue());
        }
        FormBody requestBody = formBody.build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(callback);
    }

    private OkHttpUtil() {}
}
