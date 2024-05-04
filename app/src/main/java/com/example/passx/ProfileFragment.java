package com.example.passx;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.chip.Chip;

public class ProfileFragment extends Fragment {

    Chip contact_us, about_us, rate_us, share, help;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        contact_us = view.findViewById(R.id.contact_us);
        about_us = view.findViewById(R.id.about_us);
        rate_us = view.findViewById(R.id.rate_us);
        share = view.findViewById(R.id.share);
        help = view.findViewById(R.id.help);

        contact_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change_fragment(new ContactUsFragment());
            }
        });

        about_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change_fragment(new AboutUsFragment());
            }
        });

        rate_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change_fragment(new RateUsFragment());
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change_fragment(new ShareFragment());
            }
        });

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change_fragment(new HelpFragment());
            }
        });

        return view;
    }

    public void change_fragment(Fragment fragment){
        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}