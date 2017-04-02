package com.venky97vp.android.dietjanitor;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link UserInfoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link UserInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserInfoFragment extends Fragment implements EditUserFragment.OnFragmentInteractionListener{

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    FloatingActionButton changeButton;
    DatabaseReference mDatabase;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    User value;
    private FirebaseAuth mAuth;

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public UserInfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UserInfoFragment.
     */
    public static UserInfoFragment newInstance(String param1, String param2) {
        UserInfoFragment fragment = new UserInfoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    TextView name,age,weight,height,gender,activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_info, container, false);
        changeButton  = (FloatingActionButton)view.findViewById(R.id.change_button);

        name = (TextView) view.findViewById(R.id.name_text);
        age = (TextView) view.findViewById(R.id.age_text);
        weight = (TextView) view.findViewById(R.id.weight_text);
        height = (TextView) view.findViewById(R.id.height_text);
        gender = (TextView) view.findViewById(R.id.gender_text);
        activity = (TextView) view.findViewById(R.id.activity_text);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = database.getReference().child("users").child(mAuth.getCurrentUser().getUid());

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                value = dataSnapshot.getValue(User.class);
                //Log.d(TAG, "Value is: " + value);
                if(value!=null) {
                    name.setText(value.name);
                    age.setText(Integer.toString(value.age));
                    weight.setText(Double.toString(value.weight));
                    height.setText(Double.toString(value.height));
                    if (value.gender == 0) {
                        gender.setText("Male");
                    } else {
                        gender.setText("Female");
                    }
                    switch (value.activity) {
                        case 0:
                            activity.setText("Sedentary");
                            break;
                        case 1:
                            activity.setText("Low Active");
                            break;
                        case 2:
                            activity.setText("Active");
                            break;
                        case 3:
                            activity.setText("Very Active");
                            break;
                    }
                }
                else{
                    startActivity(new Intent(getContext(),UserInfoActivity.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getContext(),UserInfoActivity.class));

//                Fragment fragment = null;
//                fragment = new EditUserFragment();
//                if (fragment != null) {
//                    FragmentManager fragmentManager = getFragmentManager();
//                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                    fragmentTransaction.replace(R.id.user_info, fragment);
//                    fragmentTransaction.commit();
////                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
////                    ft.replace(R.id.content_frame, fragment);
////                    ft.commit();
//                    changeButton.setVisibility(View.GONE);
//                }
            }
        });
        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
