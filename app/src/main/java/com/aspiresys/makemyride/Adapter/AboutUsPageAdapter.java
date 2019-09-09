
package com.aspiresys.makemyride.Adapter;

import android.app.Activity;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.aspiresys.makemyride.R;

import java.util.ArrayList;

import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;


public class AboutUsPageAdapter extends RecyclerView.Adapter<AboutUsPageAdapter.MyViewHolder>  {
    private final Activity mActivity;
    private ArrayList mAboutItems;
    private Context mContext;
    private View mItemView;

    public AboutUsPageAdapter(FragmentActivity activity,ArrayList mAboutItems) {
        this.mAboutItems = mAboutItems;
        this.mActivity=activity;
    }

    @NonNull
    @Override
    public AboutUsPageAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.about_list, parent, false);
        return new AboutUsPageAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AboutUsPageAdapter.MyViewHolder holder,  int position) {
        holder.aboutItem.setText((CharSequence) mAboutItems.get(position));
        final int pos=position;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeText(mActivity, (CharSequence) mAboutItems.get(pos), LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mAboutItems.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
    TextView aboutItem;
        public MyViewHolder(View itemView) {
            super(itemView);
            aboutItem = itemView.findViewById(R.id.about_list_items);
            mItemView=itemView;
        }
    }
}






