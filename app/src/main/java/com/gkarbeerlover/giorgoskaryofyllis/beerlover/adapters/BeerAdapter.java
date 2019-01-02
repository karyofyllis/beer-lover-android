package com.gkarbeerlover.giorgoskaryofyllis.beerlover.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gkarbeerlover.giorgoskaryofyllis.beerlover.R;
import com.gkarbeerlover.giorgoskaryofyllis.beerlover.models.Beer;

import java.util.ArrayList;

public class BeerAdapter extends RecyclerView.Adapter<BeerAdapter.BeerViewHolder> {

    private ArrayList<Beer> mBeersList;
    private final OnItemClickListener listener;

    public BeerAdapter(ArrayList<Beer> beers, OnItemClickListener listener) {
        this.mBeersList = beers;
        this.listener = listener;
    }


    public interface OnItemClickListener {
        void onItemClick(Beer item, ImageView imageView);
    }

    public void updateData(ArrayList<Beer> viewModels) {
        mBeersList.clear();
        mBeersList.addAll(viewModels);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BeerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_beer, viewGroup, false);
        return new BeerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BeerViewHolder beerViewHolder, int i) {
        beerViewHolder.bindBeer(mBeersList.get(i), listener);
    }

    @Override
    public int getItemCount() {
        return mBeersList.size();
    }

    public static class BeerViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName;
        private TextView tvTagline;
        private ImageView imageView;

        public BeerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_title);
            tvTagline = itemView.findViewById(R.id.tv_tagline);
            imageView = itemView.findViewById(R.id.img_beer);

        }

        public void bindBeer(final Beer beer, final OnItemClickListener listener){
            tvName.setText(beer.getName());
            tvTagline.setText(beer.getTagline());
            Glide.with(imageView.getContext())
                    .load(beer.getImgUrl())
                    .into(imageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(beer, imageView);
                }
            });
        }
    }
}
