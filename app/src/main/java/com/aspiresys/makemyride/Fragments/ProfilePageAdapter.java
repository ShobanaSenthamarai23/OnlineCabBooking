package com.aspiresys.makemyride.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.aspiresys.makemyride.Activities.MainActivity;
import com.aspiresys.makemyride.Activities.UserAuthenticationActivity;
import com.aspiresys.makemyride.R;

import java.util.ArrayList;


import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;

public class ProfilePageAdapter  extends RecyclerView.Adapter<ProfilePageAdapter.MyViewHolder> {
    private final Activity mActivity;
    private ArrayList mProfileDetails;
    private Context mContext;
    public int position;

    public ProfilePageAdapter(FragmentActivity activity, ArrayList profileDetails ) {
        this.mContext = activity;
        this.mProfileDetails = profileDetails;
        mActivity = activity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_list_items, parent, false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        this.position = position;
        holder.profileDetailList.setText((CharSequence) mProfileDetails.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeText(mContext, (CharSequence) mProfileDetails.get(position), LENGTH_SHORT).show();
                switch (position) {
                   case 0:
                        Fragment newFragment = new PersonalDetailsFragment();
                        ( (MainActivity) mActivity).replaceFragment(newFragment, false);
//                        ((MainActivity) mActivity).replaceFragment(newFragment);
                        break;
                    case 1:
                        Fragment myRewardsFragment = new OffersFragment();
                        ( (MainActivity) mActivity).replaceFragment(myRewardsFragment, false);
//                        ((MainActivity) mActivity).replaceFragment(myRewardsFragment);
                        break;
                    case 2:
                        Fragment myBookingFragment = new MyBookingFragment();
                        ( (MainActivity) mActivity).replaceFragment(myBookingFragment, false);
//                        ((MainActivity) mActivity).replaceFragment(myBookingFragment);

                        break;
                    case 3:
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel:" + "+919976564431"));
                        mActivity.startActivity(intent);
                    case 4:
                        Fragment aboutUsFragment = new AboutUsFragment();
                        ( (MainActivity) mActivity).replaceFragment(aboutUsFragment, false);
//                        ((MainActivity) mActivity).replaceFragment(aboutUsFragment);

                        break;
                    case 5:
                        SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager((MainActivity) mActivity);
                        sharedPreferenceManager.logoutUser();
                        makeText(mContext, "User logged Out Successfully", LENGTH_SHORT).show();
                        intent = new Intent(mActivity, UserAuthenticationActivity.class);
                        mActivity.startActivity(intent);
                        mActivity.finish();

                        break;


                }
            }
        });
    }

    /**
     * The getItemCount() method should return the number of list items
     * @return size of the list Items
     */
    @Override
    public int getItemCount() {

        return mProfileDetails.size();
    }

    /**
     * Inner class which is to initialise the views
     */
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView profileDetailList;

        public MyViewHolder(View view) {
            super(view);
            profileDetailList = itemView.findViewById(R.id.profile_listView);
        }
    }
    }
