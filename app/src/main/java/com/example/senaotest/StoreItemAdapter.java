package com.example.senaotest;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

public class StoreItemAdapter extends RecyclerView.Adapter<StoreItemAdapter.ViewHolder> implements Filterable {
    private String TAG = getClass().getSimpleName();
    private Context mCtx;
    private ArrayList<StoreItem> storeItems = new ArrayList<>();
    private ArrayList<StoreItem> tempItems = new ArrayList<>();
    private AdapterCallBack adapterCallBack;
    private TextFilter textFilter;

    public StoreItemAdapter(Context context) {
        mCtx = context;
    }

    public void updateList(ArrayList<StoreItem> items){
        storeItems = items;
        tempItems = storeItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mCtx).inflate(R.layout.store_item_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(mCtx).load(storeItems.get(position).imageUrl).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.ivPic);
        holder.tvMainName.setText(storeItems.get(position).martName);
        holder.tvPrice.setText("$" + storeItems.get(position).finalPrice);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapterCallBack.onClick(storeItems.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return storeItems.size();
    }

    @Override
    public Filter getFilter() {
        if (textFilter == null) {
            textFilter = new TextFilter();
        }
        return textFilter;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivPic;
        private TextView tvMainName;
        private TextView tvPrice;
        public ViewHolder(View itemView) {
            super(itemView);
            ivPic = itemView.findViewById(R.id.iv_picture);
            tvMainName = itemView.findViewById(R.id.tv_main_name);
            tvPrice = itemView.findViewById(R.id.tv_final_price);
        }
    }

    class TextFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            Log.d(TAG, "constraint = " + constraint);
            ArrayList<StoreItem> filter_items = new ArrayList<>();
            if (constraint != null && constraint.toString().trim().length() > 0) {
                for (int i = 0; i < tempItems.size(); i++) {
                    String content = tempItems.get(i).martName;
                    Log.d(TAG, "test, content = " + content);
                    if (content.contains(constraint)) {
                        filter_items.add(tempItems.get(i));
                        Log.d(TAG, "add size = " + filter_items.size());
                    }
                }
            } else {
                filter_items = tempItems;
            }
            FilterResults filterResults = new FilterResults();
            filterResults.count = filter_items.size();
            filterResults.values = filter_items;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            storeItems = (ArrayList<StoreItem>) results.values;
            Log.d(TAG, "test, storeItems.size() = " + storeItems.size());
            if (results.count > 0) {
                notifyDataSetChanged();
            } else {
                storeItems = new ArrayList<>();
                notifyDataSetChanged();
            }
        }
    }

    public void registerAdapterCallBack(AdapterCallBack callBack){
        adapterCallBack = callBack;
    }

    public void unregisterAdapterCallBack(){
        adapterCallBack = null;
    }

    public interface AdapterCallBack{
        void onClick(StoreItem storeItem);
    }

}
