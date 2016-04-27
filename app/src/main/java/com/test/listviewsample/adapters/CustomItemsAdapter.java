package com.test.listviewsample.adapters;


import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.test.listviewsample.BR;
import com.test.listviewsample.R;
import com.test.listviewsample.models.Fact;

import java.util.ArrayList;
import java.util.List;


public class CustomItemsAdapter extends RecyclerView.Adapter<CustomItemsAdapter.CustomViewHolder> {
    private ArrayList<Fact> facts;
    private Context mContext;
    FragmentActivity activity;


    public CustomItemsAdapter(Context context, ArrayList<Fact> facts) {
        this.mContext = context;
        this.facts = facts;

    }

    @BindingAdapter("bind:imageUrl")
    public static void loadImage(ImageView imageView, String url) {
        Picasso.with(imageView.getContext()).load(url).placeholder(R.drawable.no_image).resize(300, 300).into(imageView);
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_layout, viewGroup, false);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final CustomViewHolder customViewHolder, int position) {
        final Fact fact = facts.get(position);

        customViewHolder.getBinding().setVariable(BR.fact, fact);
        customViewHolder.getBinding().executePendingBindings();

    }

    @Override
    public int getItemCount() {
        return facts.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding binding;

        public CustomViewHolder(View view) {
            super(view);
            binding = DataBindingUtil.bind(view);
        }

        public ViewDataBinding getBinding() {
            return binding;
        }
    }

    public void addAll(List<Fact> list) {
        facts.clear();
        facts.addAll(list);
        notifyDataSetChanged();
    }

}
