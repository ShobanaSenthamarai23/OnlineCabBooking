package com.aspiresys.makemyride.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.aspiresys.makemyride.ModelClass.OfferItemList;
import com.aspiresys.makemyride.R;

import java.util.List;

public class OfferPageAdapter extends PagerAdapter {
    private List<OfferItemList> offerItemLists;
    private LayoutInflater layoutInflater;
    private Context context;
    TextView txtView_title, description;
    ImageView imageView;
    View view;

    public OfferPageAdapter(List<OfferItemList> offerItemLists, Context context) {
        this.offerItemLists = offerItemLists;
        this.context = context;
    }

    @Override
    public int getCount() {
        return offerItemLists.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.offer_items, container, false);
        imageView = view.findViewById(R.id.image);
        txtView_title = view.findViewById(R.id.title);
        description = view.findViewById(R.id.description);
        imageView.setImageResource(offerItemLists.get(position).getImage());
        txtView_title.setText(offerItemLists.get(position).getTitle());
        description.setText(offerItemLists.get(position).getDescription());
        container.addView(view, 0);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.destroyItem(container, position, object);
    }
}
