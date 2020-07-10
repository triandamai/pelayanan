package com.tdn.data.repository;

import android.content.Context;
import android.util.Log;

import com.tdn.data.service.ApiService;
import com.tdn.domain.model.KomentarModel;
import com.tdn.domain.model.LaporanModel;
import com.tdn.domain.model.UserModel;
import com.tdn.domain.realmobject.KomentarObject;
import com.tdn.domain.realmobject.LaporanObject;
import com.tdn.domain.realmobject.UserObject;
import com.tdn.domain.serialize.res.ResponseGetKomentar;
import com.tdn.domain.serialize.res.ResponseGetLaporan;
import com.tdn.domain.serialize.res.ResponseGetUser;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.tdn.data.service.ApiHandler.cek;

public class Repository {
    public static final String TAG = "LAPORAN :: ";
    private static ApiService service;
    private static Repository repository;
    private static Realm realm;
    private Context context;

    private Repository(Context ctx) {
        realm = Realm.getDefaultInstance();
        service = ApiService.Factory.create();
        context = ctx;
    }

    public synchronized static Repository getInstance(Context context) {
        if (repository == null) {
            repository = new Repository(context);
        }
        return repository;
    }

    public static ApiService getService() {
        if (service == null) {
            service = ApiService.Factory.create();
        }
        return service;
    }

    public void getAllUser() {
        service.getAllUser().enqueue(new Callback<ResponseGetUser>() {
            @Override
            public void onResponse(Call<ResponseGetUser> call, Response<ResponseGetUser> response) {
                Log.e(TAG, response.toString());

                if (cek(response.code())) {
                    Log.e(TAG, response.body().toString());
                    if (cek(response.body().getResponseCode()) || response.body().getData().size() >= 1) {
                        realm.beginTransaction();
                        realm.delete(UserObject.class);
                        realm.commitTransaction();
                        for (UserModel data : response.body().getData()) {
                            UserObject o = (UserObject) data.ToObject();
                            realm.executeTransaction(realm -> {
                                realm.copyToRealmOrUpdate(o);
                            });
                        }

                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseGetUser> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }

    public void getAllUser(String id) {
        service.getUserById(id).enqueue(new Callback<ResponseGetUser>() {
            @Override
            public void onResponse(Call<ResponseGetUser> call, Response<ResponseGetUser> response) {
                Log.e(TAG, response.toString());

                if (cek(response.code())) {
                    Log.e(TAG, response.body().toString());
                    if (cek(response.body().getResponseCode()) || response.body().getData().size() >= 1) {
                        realm.beginTransaction();
                        realm.delete(UserObject.class);
                        realm.commitTransaction();
                        for (UserModel data : response.body().getData()) {
                            UserObject o = (UserObject) data.ToObject();
                            realm.executeTransaction(realm -> {
                                realm.copyToRealmOrUpdate(o);
                            });
                        }

                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseGetUser> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }

    public void getAllLaporan() {
        service.getAllLaporan().enqueue(new Callback<ResponseGetLaporan>() {
            @Override
            public void onResponse(Call<ResponseGetLaporan> call, Response<ResponseGetLaporan> response) {
                Log.e(TAG, response.toString());

                if (cek(response.code())) {
                    Log.e(TAG, response.body().toString());

                    if (cek(response.body().getResponseCode()) || response.body().getData() != null) {
                        realm.beginTransaction();
                        realm.delete(LaporanObject.class);
                        realm.commitTransaction();
                        for (LaporanModel data : response.body().getData()) {
                            if (data.getStatusLaporan().equalsIgnoreCase("ada")) {
                                LaporanObject o = (LaporanObject) data.ToObject();
                                Log.e("tes", o.toString());
                                realm.executeTransaction(realm -> {
                                    realm.copyToRealmOrUpdate(o);
                                });
                            }
                        }

                    } else {
                        realm.beginTransaction();
                        realm.delete(LaporanObject.class);
                        realm.commitTransaction();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseGetLaporan> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }

    public void getAllLaporan(String id) {
        service.getLaporanById(id).enqueue(new Callback<ResponseGetLaporan>() {
            @Override
            public void onResponse(Call<ResponseGetLaporan> call, Response<ResponseGetLaporan> response) {
                Log.e(TAG, response.toString());

                if (cek(response.code())) {
                    Log.e(TAG, response.body().toString());
                    if (cek(response.body().getResponseCode()) || response.body().getData().size() >= 1) {
                        realm.beginTransaction();
                        realm.delete(LaporanObject.class);
                        realm.commitTransaction();
                        for (LaporanModel data : response.body().getData()) {
                            LaporanObject o = (LaporanObject) data.ToObject();
                            realm.executeTransaction(realm -> {
                                realm.copyToRealmOrUpdate(o);
                            });
                        }

                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseGetLaporan> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }

    public void getAllKomentar() {
        service.getAllKomentar().enqueue(new Callback<ResponseGetKomentar>() {
            @Override
            public void onResponse(Call<ResponseGetKomentar> call, Response<ResponseGetKomentar> response) {
                Log.e(TAG, response.toString());

                if (cek(response.code())) {
                    Log.e(TAG, response.body().toString());
                    if (cek(response.body().getResponseCode()) || response.body().getData() != null) {
                        realm.beginTransaction();
                        realm.delete(KomentarObject.class);
                        realm.commitTransaction();
                        for (KomentarModel data : response.body().getData()) {
                            KomentarObject o = (KomentarObject) data.ToObject();
                            realm.executeTransaction(realm -> {
                                realm.copyToRealmOrUpdate(o);
                            });
                        }

                    } else {
                        realm.beginTransaction();
                        realm.delete(KomentarObject.class);
                        realm.commitTransaction();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseGetKomentar> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }

    public void getAllKomentar(String id) {
        service.getAllKomentar().enqueue(new Callback<ResponseGetKomentar>() {
            @Override
            public void onResponse(Call<ResponseGetKomentar> call, Response<ResponseGetKomentar> response) {
                Log.e(TAG, response.toString());

                if (cek(response.code())) {
                    Log.e(TAG, response.body().toString());
                    if (cek(response.body().getResponseCode()) || response.body().getData() != null) {
                        realm.beginTransaction();
                        realm.delete(KomentarObject.class);
                        realm.commitTransaction();
                        for (KomentarModel data : response.body().getData()) {
                            KomentarObject o = (KomentarObject) data.ToObject();
                            realm.executeTransaction(realm -> {
                                realm.copyToRealmOrUpdate(o);
                            });
                        }

                    } else {
                        realm.beginTransaction();
                        realm.delete(KomentarObject.class);
                        realm.commitTransaction();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseGetKomentar> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }
}
