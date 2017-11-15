package com.learn2crack.loginregistration;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class ServiceFragment extends Fragment implements View.OnClickListener{

    private AppCompatButton btn_register1, btn_register2;
    private TextView tv_register1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_service,container,false);
        initViews(view);
        return view;
    }

    private void initViews(View view){

        btn_register1 = (AppCompatButton)view.findViewById(R.id.btn_register1);
        btn_register2 = (AppCompatButton)view.findViewById(R.id.btn_register2);
        tv_register1 = (TextView)view.findViewById(R.id.tv_register1);

        btn_register1.setOnClickListener(this);
        btn_register2.setOnClickListener(this);
        tv_register1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btn_register1:
                goToRegister();
                break;

            case R.id.btn_register2:
               goToRegister1();
               break;

            case R.id.tv_register1:
                goToLogin();
                break;
        }
    }

    private void goToRegister(){

        Fragment register = new RegisterFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_frame,register);
        ft.commit();
    }

    private void goToRegister1(){

        Fragment register1 = new Register1Fragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_frame,register1);
        ft.commit();
    }
    private void goToLogin(){

        Fragment login = new LoginFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_frame,login);
        ft.commit();
    }


}

