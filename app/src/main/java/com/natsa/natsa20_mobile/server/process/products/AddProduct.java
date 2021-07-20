package com.natsa.natsa20_mobile.server.process.products;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.natsa.natsa20_mobile.activity.BackActivity;
import com.natsa.natsa20_mobile.helper.Preferences;
import com.natsa.natsa20_mobile.model.products.product.Product;
import com.natsa.natsa20_mobile.server.RetrofitBuilder;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Part;

public class AddProduct {

    Activity activity;

    public AddProduct(Activity activity) {
        this.activity = activity;
    }

    public void addProductToServer(
            RequestBody title,
            RequestBody harga,
            RequestBody luas,
            RequestBody alamat,
            RequestBody maps,
            RequestBody deskripsi,
            RequestBody sertifikasi,
            RequestBody tipe,
            RequestBody vestige,
            RequestBody irigation,
            RequestBody region,
            List<MultipartBody.Part> photo
    ) {
        Log.d("TAG", "addProductToServer: "+photo.size());
        RetrofitBuilder.endPoint().addRiceFields(
                "Bearer " + Preferences.getToken(activity),
                title, harga, luas, alamat, maps, deskripsi, sertifikasi, tipe, vestige, irigation,
                region, photo
        ).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                Log.d("TAG", "onResponse: "+response.code()+" "+response.message());
                if (response.isSuccessful()){
                Log.d("TAG", "onResponse: "+ response.body().getTes().size());
                    Intent i = new Intent(activity, BackActivity.class);
                    i.putExtra("page", "detailSawah");
                    i.putExtra("id", response.body().getRiceField().getId());
                    activity.startActivity(i);
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
