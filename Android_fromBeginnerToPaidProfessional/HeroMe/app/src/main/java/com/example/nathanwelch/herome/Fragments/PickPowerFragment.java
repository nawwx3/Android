package com.example.nathanwelch.herome.Fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.nathanwelch.herome.Activities.MainActivity;
import com.example.nathanwelch.herome.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PickPowerFragment.PickPowerInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PickPowerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PickPowerFragment extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    PickPowerInteractionListener mCallback;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private String activePower;

    private Button turtleBtn;
    private Button lightningBtn;
    private Button flightBtn;
    private Button webBtn;
    private Button laserBtn;
    private Button strengthBtn;
    private Button storyBtn;


    private PickPowerInteractionListener mListener;

    public PickPowerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PickPowerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PickPowerFragment newInstance(String param1, String param2) {
        PickPowerFragment fragment = new PickPowerFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pick_power, container, false);
        final String enabledBtn = "";

        turtleBtn = (Button) view.findViewById(R.id.turtleBtn);
        lightningBtn = (Button) view.findViewById(R.id.lightningBtn);
        flightBtn = (Button)view.findViewById(R.id.flightBtn);
        webBtn = (Button) view.findViewById(R.id.webBtn);
        laserBtn = (Button) view.findViewById(R.id.laserBtn);
        strengthBtn = (Button) view.findViewById(R.id.strengthBtn);
        storyBtn = (Button) view.findViewById(R.id.storyBtn);

        turtleBtn.setOnClickListener(this);
        lightningBtn.setOnClickListener(this);
        flightBtn.setOnClickListener(this);
        webBtn.setOnClickListener(this);
        laserBtn.setOnClickListener(this);
        strengthBtn.setOnClickListener(this);



        //when powers button is clicked, mainActivity is loading "Pick Power" fragment (screen)
        //because MANAGE YOUR FRAGMENTS FROM THE ACTIVITY THAT IS HOSTING THEM
        storyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity =(MainActivity)getActivity();
                mainActivity.loadBackstoryScreen();
            }
        });

        //disabled the button so that the user can't choose that button until one of the
        //other buttons have been selected
        storyBtn.setEnabled(false);
        storyBtn.getBackground().setAlpha(128);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onPickPowerFragmentInteraction("");
        }
    }

    @Override
    public void onClick(View v) {
        storyBtn.setEnabled(true);
        storyBtn.getBackground().setAlpha(255);

        Button btn = (Button) v;

        //clears out the checkmarks
        turtleBtn.setCompoundDrawablesWithIntrinsicBounds(R.drawable.turtlepower, 0, R.drawable.itemunselected, 0);
        lightningBtn.setCompoundDrawablesWithIntrinsicBounds(R.drawable.thorshammer, 0, R.drawable.itemunselected, 0);
        flightBtn.setCompoundDrawablesWithIntrinsicBounds(R.drawable.supermancrest, 0, R.drawable.itemunselected, 0);
        webBtn.setCompoundDrawablesWithIntrinsicBounds(R.drawable.spiderweb, 0, R.drawable.itemunselected, 0);
        laserBtn.setCompoundDrawablesWithIntrinsicBounds(R.drawable.laservision, 0, R.drawable.itemunselected, 0);
        strengthBtn.setCompoundDrawablesWithIntrinsicBounds(R.drawable.superstrength, 0, R.drawable.itemunselected, 0);

        //then places it where it has been selected
        if(btn == turtleBtn) {
            turtleBtn.setCompoundDrawablesWithIntrinsicBounds(R.drawable.turtlepower, 0, R.drawable.itemselected, 0);
            mCallback.onPickPowerFragmentInteraction("turtleBtn");
//            activePower = "turtleBtn";
        }
        else if(btn == lightningBtn) {
            lightningBtn.setCompoundDrawablesWithIntrinsicBounds(R.drawable.thorshammer, 0, R.drawable.itemselected, 0);
//            activePower = "lightningBtn";
        }
        else if(btn == flightBtn) {
            flightBtn.setCompoundDrawablesWithIntrinsicBounds(R.drawable.supermancrest, 0, R.drawable.itemselected, 0);
//            activePower = "flightBtn";
        }
        else if(btn == webBtn) {
            webBtn.setCompoundDrawablesWithIntrinsicBounds(R.drawable.spiderweb, 0, R.drawable.itemselected, 0);
//            activePower = "webBtn";
        }
        else if(btn == laserBtn) {
            laserBtn.setCompoundDrawablesWithIntrinsicBounds(R.drawable.laservision, 0, R.drawable.itemselected, 0);
//            activePower = "laserBtn";
        }
        else if(btn == strengthBtn) {
            strengthBtn.setCompoundDrawablesWithIntrinsicBounds(R.drawable.superstrength, 0, R.drawable.itemselected, 0);
//            activePower = "strengthBtn";
        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Activity activity = null;
        if(context instanceof Activity)
            activity = (Activity) context;

        try {
            mCallback = (PickPowerInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement PickPowerInteractionListener");
        }



    }

    public void someMethond() {
        mListener.sendText("My Text");
    }


        @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
    public interface PickPowerInteractionListener {
        // TODO: Update argument type and name
        void onPickPowerFragmentInteraction(String button);
        void sendText(String text);

    }
}
