package com.aspiresys.makemyride.Fragments;

import android.animation.ArgbEvaluator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.aspiresys.makemyride.Adapter.OfferPageAdapter;
import com.aspiresys.makemyride.ModelClass.OfferItemList;
import com.aspiresys.makemyride.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OffersFragment extends Fragment {
    private OfferPageAdapter offerPageAdapter;
    private Integer[] color = null;
    private ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    private ViewPager viewPager;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.offers_fragment, container, false);
        List<OfferItemList> offerItemLists = new ArrayList<>();
        offerItemLists.add(new OfferItemList(R.drawable.of1, "Special Offer", "Get Special offer for your first BookingFragment in our cab"));
        offerItemLists.add(new OfferItemList(R.drawable.of2, "Special Offer", "Get Special offer for your first BookingFragment in our cab"));
        offerItemLists.add(new OfferItemList(R.drawable.coupon_envelope, "Special Offer", "Get Special offer for your first BookingFragment in our cab"));
        offerPageAdapter = new OfferPageAdapter(offerItemLists, getContext());
        viewPager = mView.findViewById(R.id.viewPager);
        viewPager.setAdapter(offerPageAdapter);
        viewPager.setPadding(130, 0, 130, 0);
        color = new Integer[]{ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.color1), ContextCompat.getColor(getContext(), R.color.color2), ContextCompat.getColor(getContext(), R.color.color3)};
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position < (offerPageAdapter.getCount() - 1) && position < (color.length - 1)) {
                    viewPager.setBackgroundColor((Integer) argbEvaluator.evaluate(positionOffset, color[position], color[position + 1]));
                } else {
                    viewPager.setBackgroundColor(color[color.length - 1]);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        return mView;
    }
}
