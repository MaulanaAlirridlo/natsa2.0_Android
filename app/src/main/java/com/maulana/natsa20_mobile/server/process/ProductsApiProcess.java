package com.maulana.natsa20_mobile.server.process;

import com.maulana.natsa20_mobile.adapter.ProductsAdapter;
import com.maulana.natsa20_mobile.model.products.Data;
import com.maulana.natsa20_mobile.model.products.Products;
import com.maulana.natsa20_mobile.server.API.Products.ProductsEndPoint;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsApiProcess {

    public static List<Data> productsDataList = new ArrayList<Data>();

    public static List<Data> getProductsDataList() {
        return productsDataList;
    }

    public void getDataFromApi(ProductsAdapter adapter) {
        ProductsEndPoint.productEndPoint().getRiceFields()
                .enqueue(new Callback<Products>() {
                    @Override
                    public void onResponse(Call<Products> call, Response<Products> response) {
                        if (response.isSuccessful()) {
                            List<Data> data = response.body().getRiceField().getData();
                            setProducts(data, adapter);
                        }
                    }

                    @Override
                    public void onFailure(Call<Products> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }

    public void setProducts(List<Data> products, ProductsAdapter adapter) {
        productsDataList.clear();
        productsDataList.addAll(products);
        adapter.notifyDataSetChanged();
    }

}
