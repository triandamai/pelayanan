package com.tdn.data.service;

import com.tdn.domain.model.KomentarModel;
import com.tdn.domain.model.UserModel;
import com.tdn.domain.serialize.req.ChatPostReq;
import com.tdn.domain.serialize.req.ConversationPostReq;
import com.tdn.domain.serialize.req.KomentarPostReq;
import com.tdn.domain.serialize.req.LaporanPostReq;
import com.tdn.domain.serialize.req.LoginPostReq;
import com.tdn.domain.serialize.req.UserPostReq;
import com.tdn.domain.serialize.res.ResponseGetChat;
import com.tdn.domain.serialize.res.ResponseGetConversation;
import com.tdn.domain.serialize.res.ResponseGetKomentar;
import com.tdn.domain.serialize.res.ResponseGetLaporan;
import com.tdn.domain.serialize.res.ResponseGetUser;
import com.tdn.domain.serialize.res.ResponsePostLogin;
import com.tdn.domain.serialize.res.ResponsePostPutDel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface ApiService {
    //String BASE_URL = "http://192.168.1.19/laporan-karyawan-api/v1/";
//    String BASE = "http://192.168.100.5/apipengaduan/";
    String BASE = "https://apipengaduan.000webhostapp.com/";
    String BASE_URL = BASE + "api/v1/";
    String BASE_URL_IMAGE = BASE + "assets/pengaduan/";
    // String BASE_URL = "http://192.168.100.5/apipengaduan/api/v1/";
    String USER_KEY = "";

    String accept_urlencoded = "Content-Type: application/x-www-form-urlencoded";
    String accept_json = "Accept: application/json;charset=utf-8";
    String content_type = "Content-Type: application/json;charset=utf-8";
    String api_key = "X-API-KEY: your api key";

    @Headers({accept_json, content_type, api_key})
    @POST("user/login")
    Call<ResponsePostLogin> login(@Body LoginPostReq loginPostReq);

    @Headers({accept_json, content_type, api_key})
    @GET("user/users")
    Call<ResponseGetUser> getAllUser();

    @Headers({accept_json, content_type, api_key})
    @GET("user/users")
    Call<ResponseGetUser> getUserById(@Query("id") String id);

    @Headers({accept_json, content_type, api_key})
    @GET("pengaduan/laporan")
    Call<ResponseGetLaporan> getAllLaporan();

    @Headers({accept_json, content_type, api_key})
    @GET("pengaduan/laporan")
    Call<ResponseGetLaporan> getLaporanById(@Query("id") String id);

    @Headers({accept_json, content_type, api_key})
    @GET("komentar/komentar")
    Call<ResponseGetKomentar> getAllKomentar();

    @Headers({accept_json, content_type, api_key})
    @GET("komentar/komentar")
    Call<ResponseGetKomentar> getKomentarById(@Query("id") String id);

    @Headers({accept_json, content_type, api_key})
    @POST("user/users")
    Call<ResponsePostPutDel> postUser(@Body UserPostReq userModel);

    @Headers({accept_json, content_type, api_key})
    @PUT("user/users/{id}")
    Call<ResponsePostPutDel> putUser(@Body UserModel userModel);

    @Headers({accept_json, content_type, api_key})
    @POST("user/users_hapus")
    Call<ResponsePostPutDel> deleteUser(@Field("id") String id);

    @Headers({accept_json, content_type, api_key})
    @POST("pengaduan/laporan")
    Call<ResponsePostPutDel> postLaporan(@Body LaporanPostReq laporanPostReq);

    @Headers({accept_json, content_type, api_key})
    @PUT("pengaduan/laporan")
    Call<ResponsePostPutDel> putLaporan(@Body LaporanPostReq laporanPostReq);

    @Headers({accept_json, content_type, api_key})
    @DELETE("pengaduan/laporan")
    Call<ResponsePostPutDel> deleteLaporan(@Body LaporanPostReq laporanPostReq);

    @Headers({accept_json, content_type, api_key})
    @POST("komentar/komentar")
    Call<ResponsePostPutDel> postKomentar(@Body KomentarPostReq komentarModel);

    @Headers({accept_json, content_type, api_key})
    @PUT("komentar/komentar")
    Call<ResponsePostPutDel> putKomentar(@Body KomentarModel komentarModel);

    @Headers({accept_json, content_type, api_key})
    @DELETE("komentar/komentar")
    Call<ResponsePostPutDel> deleteKomentar(@Body KomentarModel komentarModel);

    @Headers({accept_json, content_type, api_key})
    @GET("chat/chat")
    Call<ResponseGetChat> getAllChat();

    @Headers({accept_json, content_type, api_key})
    @GET("chat/chat")
    Call<ResponseGetChat> getChatById(@Query("id") String id);

    @Headers({accept_json, content_type, api_key})
    @POST("chat/chat")
    Call<ResponsePostPutDel> postChat(@Body ChatPostReq chatPostReq);

    @Headers({accept_json, content_type, api_key})
    @GET("conversation/conversation")
    Call<ResponseGetConversation> getAllConversation();

    @Headers({accept_json, content_type, api_key})
    @GET("conversation/conversation")
    Call<ResponseGetConversation> getConversationById(@Query("id") String id);

    @Headers({accept_json, content_type, api_key})
    @POST("conversation/conversation")
    Call<ResponsePostPutDel> postConversation(@Body ConversationPostReq conversationPostReq);

    class Factory {
        public static ApiService create() {
            return RepoFactory.createService(ApiService.class);
        }
    }
}
