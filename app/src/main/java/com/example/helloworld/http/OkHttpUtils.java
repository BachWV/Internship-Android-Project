//package com.example.helloworld.http;
//
//import android.util.Log;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
//import okhttp3.FormBody;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.RequestBody;
//import okhttp3.Response;
//
//public class OkHttpUtils {
//
//    private static final String TAG = OkHttpUtils.class.getSimpleName();
//
//    public static String sendPostMethod(String path, String encoding) {
//        String result = "";
//        try {
//            OkHttpClient client = new OkHttpClient();
//            Request request = new Request.Builder().url(path).build();
//            Response response = client.newCall(request).execute();
//            result = response.body().string();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        Log.d("OkHttpUtils------->>>", result);
//        return result;
//    }
//
//
//    public static String loginPost(String url, String ScreenName, String pwd) throws IOException, JSONException {
//        OkHttpClient client = new OkHttpClient();
//        String token="";
//
//        RequestBody formBody = new FormBody.Builder()
//                .add("screen_name", ScreenName)
//                .add("name", pwd)
//
//                .build();
//        Request request = new Request.Builder()
//                .url(url)
//                .post(formBody)
//                .build();
//        Response response = client.newCall(request).execute();
//        if (response.isSuccessful()) {
//            JSONObject ele = new JSONObject(response.body().toString());
//
//          int errcode= ele.getInt("errorCode");
//
//
//
//          if(errcode==0){
//            token=ele.getString("datas");
//              Log.d(TAG,token);
//              return token;
//            }
//
//
//        }
//        return null;
//    }
//
//
///*{
//"errorCode": 0,
//"errorMsg": "",
//"datas":{
//"code": "200",
//"message": "认证成功",
//"user":{"id": 6, "screen_name": "user5", "profile_image_url": "", "description": ""…},
//"token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJhdXRoMCIsImV4cCI6MTYyNTkwMDQ2MiwidXNlcm5hbWUiOiJ1c2VyNSJ9.hZE1XyZ_qoPmH9a7xEQHQnTR148Fe0vDwx4mgno6-hU"
//}
//}*?
//
//            String errorCode=formBody.
//            if(formBody)
//            return response.body().string();
//
//        }
//    }*/
//    public static byte[] getImageView(String path){
//        byte[] data=null;
//        try {
//            OkHttpClient client=new OkHttpClient();
//            Request request=new Request.Builder().url(path).build();
//            Response response=client.newCall(request).execute();
//            data=response.body().bytes();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return data;
//    }
//
//
//}
package com.example.helloworld.http;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class OkHttpUtils {

    private static TokenInterceptor tokenInterceptor = new TokenInterceptor();
    private static final OkHttpClient CLIENT = new OkHttpClient.Builder().build();
    private static final OkHttpClient CLIENTTOKRN = new OkHttpClient.Builder().addInterceptor(tokenInterceptor).build();
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");



    /**
     * 封装get请求(不带拦截器)
     * @param url
     * @param callback
     */
    public static void get(String url, OkHttpCallback callback){
        callback.url = url;
        Request request = new Request.Builder().url(url).build();
        CLIENT.newCall(request).enqueue(callback);

    }


    /**
     * 封装post请求(不带拦截器)
     * @param url
     * @param body
     * @param callback
     */
    public static void post(String url, RequestBody body, OkHttpCallback callback){
        callback.url = url;
//        RequestBody body = RequestBody.create(JSON,json);
        Request request = new Request.Builder().url(url).post(body).build();
        CLIENT.newCall(request).enqueue(callback);
    }

    /**
     * 封装get请求(带拦截器)
     * @param url
     * @param callback
     */
    public static void getWithToken(String url,String token, OkHttpCallback callback){
        callback.url = url;
        Request request = new Request.Builder().url(url).addHeader("token",token).build();
        CLIENTTOKRN.newCall(request).enqueue(callback);

    }

    /**
     * 封装post请求(带拦截器)
     * @param url
     * @param body
     * @param callback
     */
    public static void postWithToken(String url, RequestBody body, String token,OkHttpCallback callback){
        callback.url = url;
//        RequestBody body = RequestBody.create(JSON,json);
        Request request = new Request.Builder().url(url).addHeader("token",token).post(body).build();
        CLIENTTOKRN.newCall(request).enqueue(callback);
    }


}

