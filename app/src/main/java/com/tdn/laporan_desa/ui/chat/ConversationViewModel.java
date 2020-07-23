package com.tdn.laporan_desa.ui.chat;

import android.content.Context;
import android.util.Log;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tdn.Static;
import com.tdn.data.local.RealmLiveResult;
import com.tdn.data.persistensi.MyUser;
import com.tdn.data.repository.Repository;
import com.tdn.data.service.ApiService;
import com.tdn.domain.realmobject.ConversationObject;
import com.tdn.domain.serialize.req.ConversationPostReq;
import com.tdn.domain.serialize.res.ResponsePostPutDel;
import com.tdn.laporan_desa.callback.ActionListener;

import java.util.Date;
import java.util.List;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.tdn.data.service.ApiHandler.cek;

public class ConversationViewModel extends ViewModel {
    public String TAG = this.getClass().getSimpleName();
    private Context context;
    private ApiService apiService;
    private ActionListener actionListener;
    private Realm realm;
    public ObservableField<Boolean> isLoading = new ObservableField<>();
    public ObservableField<String> body = new ObservableField<>();
    public LiveData<List<ConversationObject>> userObjectLiveData;

    public ConversationViewModel(Context context, ActionListener actionListener) {
        this.context = context;
        this.apiService = ApiService.Factory.create();
        this.realm = Realm.getDefaultInstance();
        this.actionListener = actionListener;

        fetchfromApi();
        fetchfromLocal();
    }

    public void fetchfromLocal() {

        userObjectLiveData = new RealmLiveResult(realm.where(ConversationObject.class).findAll());
    }

    public void fetchfromApi() {

        Repository.getInstance(context).getAllConversation(MyUser.getInstance(context).getid(Static.KEY_LAST_CHAT_ID, ""));

    }

    public LiveData<List<ConversationObject>> getUserObjectLiveData() {
        if (userObjectLiveData == null) {
            userObjectLiveData = new MutableLiveData<>();
        }
        return userObjectLiveData;
    }

    public void sendChat() {
        actionListener.onStart();
        if (!body.get().isEmpty()) {
            ConversationPostReq post = new ConversationPostReq();
            post.setBody(body.get().toString());
            post.setIdDetailChat(String.valueOf(new Date().getTime()));
            post.setCreatedAt(String.valueOf(new Date().getTime()));
            post.setUpdatedAt(String.valueOf(new Date().getTime()));
            post.setFromSender(MyUser.getInstance(context).getUser().getIdUser());
            post.setIdChat(MyUser.getInstance(context).getid(Static.KEY_LAST_CHAT_ID, "kosong"));
            post.setStatusDetail(Static.STATUS_ADA);
            post.setMedia("kosong");
            post.setToReceiver(MyUser.getInstance(context).getid(Static.KEY_LAST_CHAT_ID_USER, "kosong"));
            post.setType(Static.TIPE_CONV_TEXT);
            Log.e("ini tes", post.toString());
            apiService.postConversation(post).enqueue(new Callback<ResponsePostPutDel>() {
                @Override
                public void onResponse(Call<ResponsePostPutDel> call, Response<ResponsePostPutDel> response) {
                    if (cek(response.code())) {
                        if (cek(response.body().getResponseCode())) {
                            actionListener.onSuccess(response.body().getResponseMessage());
                        } else {
                            actionListener.onError(response.body().getResponseMessage());
                        }
                    } else {
                        actionListener.onError(response.toString());
                    }
                }

                @Override
                public void onFailure(Call<ResponsePostPutDel> call, Throwable t) {
                    actionListener.onError(t.getMessage());
                }
            });
        } else {
            actionListener.onError("Isi Semua Field!");
        }
    }
}