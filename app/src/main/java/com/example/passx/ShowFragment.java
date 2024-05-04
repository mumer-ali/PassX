package com.example.passx;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ShowFragment extends Fragment {

    private ListView list;
    private String email, g_email;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show, container, false);
        list = view.findViewById(R.id.list);
        Bundle data = this.getArguments();
        email = data.getString("Email");
        g_email = data.getString("G_Email");


//        ArrayList<String> arr_list = new ArrayList<String>();
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),R.layout.items,arr_list);
//        list.setAdapter(adapter);
        FirebaseDatabase.getInstance().getReference().child("USERS").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
//                    arr_list.clear();
                    for (DataSnapshot snapshot_1: snapshot.getChildren()) {
                        Users users = snapshot_1.getValue(Users.class);
                        String str = users.getEmail();
                        if (str.equals(email) || str.equals(g_email)){
                            String everything = snapshot_1.getValue().toString();
                            Toast.makeText(getContext(),everything+"",Toast.LENGTH_LONG).show();
                        }
//                        arr_list.add(str);
                    }
//                    adapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return view;
    }
}