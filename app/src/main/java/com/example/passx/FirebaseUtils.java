package com.example.passx;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class FirebaseUtils {

    public interface DataCallback {
        void onDataReceived(DataSnapshot dataSnapshot);
        void onCancelled(DatabaseError databaseError);
    }

    public static void fetchDataByEmail(String email, final DataCallback callback) {
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("USERS");
        databaseRef.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (callback != null) {
                    callback.onDataReceived(dataSnapshot);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                if (callback != null) {
                    callback.onCancelled(databaseError);
                }
            }
        });
    }
}

