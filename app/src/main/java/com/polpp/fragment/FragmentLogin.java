package com.polpp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.github.gorbin.asne.core.SocialNetwork;
import com.github.gorbin.asne.core.SocialNetworkManager;
import com.github.gorbin.asne.core.listener.OnLoginCompleteListener;
import com.github.gorbin.asne.core.listener.OnRequestSocialPersonCompleteListener;
import com.github.gorbin.asne.core.persons.SocialPerson;
import com.github.gorbin.asne.facebook.FacebookSocialNetwork;
import com.polpp.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Ahmad Hartaji on 12/12/2014.
 */
public class FragmentLogin extends Fragment implements SocialNetworkManager.OnInitializationCompleteListener, OnLoginCompleteListener {
    public static final String SOCIAL_NETWORK_TAG = "SocialIntegrationMain.SOCIAL_NETWORK_TAG";
    SocialNetworkManager mSocialNetworkManager;
    SocialNetwork socialNetwork;
    private RelativeLayout relFb;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.frag_login, container, false);
        declareView(rootView);
        mSocialNetworkManager = (SocialNetworkManager) getFragmentManager().findFragmentByTag(SOCIAL_NETWORK_TAG);
        if (mSocialNetworkManager == null) {
            ArrayList<String> fbScope = new ArrayList<String>();
            fbScope.addAll(Arrays.asList("public_profile, email, user_friends"));
            FacebookSocialNetwork fbNetwork = new FacebookSocialNetwork(this, fbScope);
            mSocialNetworkManager = new SocialNetworkManager();
            mSocialNetworkManager.addSocialNetwork(fbNetwork);
            getFragmentManager().beginTransaction().add(mSocialNetworkManager, SOCIAL_NETWORK_TAG).commit();
            mSocialNetworkManager.setOnInitializationCompleteListener(this);
        } else {
            if (!mSocialNetworkManager.getInitializedSocialNetworks().isEmpty()) {
                List<SocialNetwork> socialNetworks = mSocialNetworkManager.getInitializedSocialNetworks();
                for (SocialNetwork socialNetwork : socialNetworks) {
                    socialNetwork.setOnLoginCompleteListener(this);
                }
            }
        }
        SocialNetwork socialNetwork = mSocialNetworkManager.getSocialNetwork(FacebookSocialNetwork.ID);
        if (socialNetwork.isConnected()) {
            socialNetwork.requestCurrentPerson(new OnRequestSocialPersonCompleteListener() {
                @Override
                public void onRequestSocialPersonSuccess(int i, SocialPerson socialPerson) {
                    Toast.makeText(getActivity(), socialPerson.name, Toast.LENGTH_LONG).show();
                }

                @Override
                public void onError(int i, String s, String s2, Object o) {
                    Toast.makeText(getActivity(), s2, Toast.LENGTH_LONG).show();

                }
            });
        }


        return rootView;
    }

    private void declareView(View v) {
        relFb = (RelativeLayout) v.findViewById(R.id.rel_fb_login);
        final OnLoginCompleteListener a = this;
        relFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SocialNetwork socialNetwork = mSocialNetworkManager.getSocialNetwork(FacebookSocialNetwork.ID);
                if (!socialNetwork.isConnected()) {
                    socialNetwork.requestLogin(a);
                } else {

                }
            }
        });
    }


    @Override
    public void onSocialNetworkManagerInitialized() {
        for (SocialNetwork socialNetwork : mSocialNetworkManager.getInitializedSocialNetworks()) {
            socialNetwork.setOnLoginCompleteListener(this);
            if (!socialNetwork.isConnected()) {
                socialNetwork.requestLogin(this);
            }
            this.socialNetwork = socialNetwork;
        }

    }

    @Override
    public void onLoginSuccess(int i) {
        Toast.makeText(getActivity(), "Toast", Toast.LENGTH_LONG).show();
        socialNetwork.setOnRequestCurrentPersonCompleteListener(new OnRequestSocialPersonCompleteListener() {
            @Override
            public void onRequestSocialPersonSuccess(int i, SocialPerson socialPerson) {
                Toast.makeText(getActivity(), socialPerson.name, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onError(int i, String s, String s2, Object o) {
                Toast.makeText(getActivity(), s2, Toast.LENGTH_LONG).show();
                System.out.println("Error" + s2);

            }
        });
        socialNetwork.requestCurrentPerson();
    }

    @Override
    public void onError(int i, String s, String s2, Object o) {
        System.out.println("Error" + s2);

    }
}
