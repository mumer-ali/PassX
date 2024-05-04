package com.example.passx;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class HomeFragment extends Fragment {

    private Button show_id;
    private Button add_id;
    private String email, g_email;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        show_id = view.findViewById(R.id.show_id);
        add_id = view.findViewById(R.id.add_id);

        Bundle data = this.getArguments();
        if (data!=null){
            email = data.getString("Email");
            g_email = data.getString("G_Email");
        }

        show_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle data = new Bundle();
                data.putString("Email",email);
                data.putString("G_Email",g_email);
                ShowFragment showFragment = new ShowFragment();
                showFragment.setArguments(data);

                FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.container, showFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        add_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.container, new AddFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return view;
    }

}