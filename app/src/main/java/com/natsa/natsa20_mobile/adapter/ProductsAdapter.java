package com.natsa.natsa20_mobile.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.badoo.mobile.util.WeakHandler;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.natsa.natsa20_mobile.R;
import com.natsa.natsa20_mobile.helper.GlideLoader;
import com.natsa.natsa20_mobile.model.products.products.Data;
import com.natsa.natsa20_mobile.server.Server;

import java.util.List;

public class ProductsAdapter extends PagedListAdapter<Data, ProductsAdapter.ProductsViewHolder> {

    private final Context context;
    private showDetailSawahListener showDetailSawahListener;

    public ProductsAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.context = context;
    }

    @NonNull
    @Override
    public ProductsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.products_card, parent, false);
        return new ProductsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsViewHolder holder, int position) {
        if (getItem(position).getPhoto() != null) {
            new GlideLoader().glideLoader(holder.itemView, holder.productsImage,
                    Server.storage + getItem(position).getPhoto()
                            .getPhoto_path());
        } else {
            holder.productsImage.setImageResource(R.drawable.no_image);
        }
        holder.productsTitle.setText(getItem(position).getTitle());
        holder.productsPrice.setText(String.valueOf(getItem(position).getHarga()));

        try {
            showDetailSawahListener = (ProductsAdapter.showDetailSawahListener) context;
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    public class ProductsViewHolder extends RecyclerView.ViewHolder {

        private final ImageView productsImage;
        private final TextView productsTitle, productsPrice;
        private int position;
        private int id;
        View itemView;

        public ProductsViewHolder(View v) {
            super(v);
            productsImage = v.findViewById(R.id.productsImage);
            productsTitle = v.findViewById(R.id.productsTitle);
            productsPrice = v.findViewById(R.id.productsPrice);

            v.setOnClickListener(v1 -> {
                position = getAdapterPosition();
                id = getItem(position).getId();
                show(id);
            });

            itemView = v;
        }
    }

    private void show(final int id) {
        showDetailSawahListener.showDetailSawah(id);
    }

    public interface showDetailSawahListener {
        void showDetailSawah(int id);
    }

    private static DiffUtil.ItemCallback<Data> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Data>() {
                @Override
                public boolean areItemsTheSame(Data oldData, Data newData) {
                    return oldData.getId() == newData.getId();
                }

                @Override
                public boolean areContentsTheSame(Data oldData, Data newData) {
                    return oldData.equals(newData);
                }
            };
}
