package com.learn2crack.loginregistration;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.learn2crack.loginregistration.models.ServerRequest;
import com.learn2crack.loginregistration.models.ServerResponse;
import com.learn2crack.loginregistration.models.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mahmudul on 5/19/2017.
 */



    public class Register1Fragment extends Fragment implements View.OnClickListener{

        private AppCompatButton btn_register;
        private EditText et_email,et_password,et_name,et_address,lic_password,nin_password;
        private TextView tv_login1;
        private ProgressBar progress;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            View view = inflater.inflate(R.layout.fragment_register1,container,false);
            initViews(view);
            return view;
        }

        private void initViews(View view){

            et_name = (EditText)view.findViewById(R.id.et_name);
            et_email = (EditText)view.findViewById(R.id.et_email);
            et_address = (EditText)view.findViewById(R.id.et_address);
            et_password = (EditText)view.findViewById(R.id.et_password);
            lic_password = (EditText)view.findViewById(R.id.lic_password);
            nin_password = (EditText)view.findViewById(R.id.nin_password);
            btn_register = (AppCompatButton)view.findViewById(R.id.btn_register);
            tv_login1 = (TextView)view.findViewById(R.id.tv_login1);


            progress = (ProgressBar)view.findViewById(R.id.progress);

            btn_register.setOnClickListener(this);
            tv_login1.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {

            switch (v.getId()){
                case R.id.tv_login1:
                    goToLogin();
                    break;

                case R.id.btn_register:

                    String name = et_name.getText().toString();
                    String email = et_email.getText().toString();
                    String password = et_password.getText().toString();

                    if(!name.isEmpty() && !email.isEmpty() && !password.isEmpty()) {

                        progress.setVisibility(View.VISIBLE);
                        registerProcess(name,email,password);

                    } else {

                        Snackbar.make(getView(), "Fields are empty !", Snackbar.LENGTH_LONG).show();
                    }
                    break;

            }

        }

        private void registerProcess(String name, String email,String password){

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            RequestInterface requestInterface = retrofit.create(RequestInterface.class);

            User user = new User();
            user.setName(name);
            user.setEmail(email);
            user.setPassword(password);
            ServerRequest request = new ServerRequest();
            request.setOperation(Constants.REGISTER_OPERATION);
            request.setUser(user);
            Call<ServerResponse> response = requestInterface.operation(request);

            response.enqueue(new Callback<ServerResponse>() {
                @Override
                public void onResponse(Call<ServerResponse> call, retrofit2.Response<ServerResponse> response) {

                    ServerResponse resp = response.body();
                    Snackbar.make(getView(), resp.getMessage(), Snackbar.LENGTH_LONG).show();
                    progress.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onFailure(Call<ServerResponse> call, Throwable t) {

                    progress.setVisibility(View.INVISIBLE);
                    Log.d(Constants.TAG,"failed");
                    Snackbar.make(getView(), t.getLocalizedMessage(), Snackbar.LENGTH_LONG).show();
                }
            });
        }

        private void goToLogin(){

            Fragment login1 = new LoginFragment();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_frame,login1);
            ft.commit();
        }
    }


