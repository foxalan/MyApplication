package com.example.mybulter.fragment;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;


import com.example.mybulter.R;
import com.example.mybulter.activity.HomeActivity;
import com.example.mybulter.constant.Constant;
import com.example.mybulter.presenter.inter.LoginPresenter;
import com.example.mybulter.presenter.impl.LoginPresenterImpl;
import com.example.mybulter.util.L;
import com.example.mybulter.util.ShareUtils;
import com.example.mybulter.view.CustomDialog;
import com.example.mybulter.view.MyToast;
import com.example.mybulter.view.inter.LoginView;


public class LoginFragment extends Fragment implements LoginView {
    private EditText et_username;
    private EditText et_password;
    private Button bt_register;
    private Button bt_sign;
    private CheckBox cb_rem_password;

    private android.support.v4.app.FragmentTransaction transaction;

    private Fragment fragment_register;

    private String username;
    private String password;

    private LoginPresenter presenter;
    private CustomDialog customDialog;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, null);

        initData();
        initViews(view);
        initEvent();

        return view;
    }

    private void initData() {

        presenter = new LoginPresenterImpl(this);

        customDialog = new CustomDialog(getContext(), 100, 100, R.layout.dialog_login, R.style.pop_anim_style, Gravity.CENTER, R.anim.pop_in);
        customDialog.setCanceledOnTouchOutside(false);

    }

    private void initViews(View view) {

        MyToast.init(getActivity(), getActivity().getLayoutInflater());

        fragment_register = new RegisterFragment();

        et_username = (EditText) view.findViewById(R.id.et_username);
        et_password = (EditText) view.findViewById(R.id.et_password);
        bt_register = (Button) view.findViewById(R.id.bt_register);
        bt_sign = (Button) view.findViewById(R.id.bt_login);
        cb_rem_password = (CheckBox) view.findViewById(R.id.cb_rem_password);

        bt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fl_contain, fragment_register);
                transaction.addToBackStack(null);
                transaction.hide(new LoginFragment());
                transaction.commit();
            }
        });


        bt_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = et_username.getText().toString();
                password = et_password.getText().toString();

                ConnectivityManager connMgr = (ConnectivityManager)
                        getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
                    // fetch data
                } else {
                    navigateToHome();
                    return;
                }


          //      presenter.startLogin(username, password);
          //      customDialog.show();

                startActivity(new Intent(getActivity(), HomeActivity.class));
                getActivity().finish();

            }
        });


    }

    private void initEvent() {

        if (ShareUtils.getBoolean(getContext(), Constant.ISREMBER, false)) {
            et_username.setText(ShareUtils.getString(getContext(), Constant.USER_NAME, null));
            et_password.setText(ShareUtils.getString(getContext(), Constant.PASSWORD, null));
            cb_rem_password.setChecked(ShareUtils.getBoolean(getContext(), Constant.ISREMBER, false));
        }
    }


    @Override
    public void setUsernameError() {
        customDialog.dismiss();
        L.d("usernameError");
        customDialog.dismiss();
        MyToast.showMessage("用户名错误");
    }

    @Override
    public void setPasswordError() {
        customDialog.dismiss();
        MyToast.showMessage("密码错误");
        L.d("passwordError");
    }

    @Override
    public void navigateToHome() {
        MyToast.showMessage("网络异常,请先检查网络是否良好");

    }

    @Override
    public void setSuccess() {
        customDialog.dismiss();

        if (cb_rem_password.isChecked()) {
            ShareUtils.putString(getContext(), Constant.USER_NAME, username);
            ShareUtils.putString(getContext(), Constant.PASSWORD, password);
            ShareUtils.putBoolean(getContext(), Constant.ISREMBER, true);
        } else {
            ShareUtils.putBoolean(getContext(), Constant.ISREMBER, false);
        }

        startActivity(new Intent(getActivity(), HomeActivity.class));
        getActivity().finish();
        L.d("success");

    }

    @Override
    public void setContentNull() {
        MyToast.showMessage("用户名或密码错误");

    }
}
