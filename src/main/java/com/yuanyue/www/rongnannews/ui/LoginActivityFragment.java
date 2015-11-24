package com.yuanyue.www.rongnannews.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.yuanyue.www.rongnannews.NewsListActivity;
import com.yuanyue.www.rongnannews.R;
import com.yuanyue.www.rongnannews.ui.http.HttpUtils;
import com.yuanyue.www.rongnannews.ui.jsonparse.JsonTools;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class LoginActivityFragment extends Fragment {

    @Bind(R.id.register_login_status_message)
    TextView registerLoginStatusMessage;
    @Bind(R.id.register_login_status)
    LinearLayout registerLoginStatus;
    @Bind(R.id.register_button)
    Button registerButton;
    @Bind(R.id.register_signin_button)
    Button registerSigninButton;
    @Bind(R.id.register_password_edit)
    EditText registerPasswordEdit;
    @Bind(R.id.login_password_input)
    TextView loginPasswordInput;
    @Bind(R.id.register_login_user_input)
    TextView registerLoginUserInput;
    @Bind(R.id.register_username_edit)
    EditText registerUsernameEdit;
    @Bind(R.id.login_div)
    RelativeLayout loginDiv;
    @Bind(R.id.register_login_form)
    ScrollView registerLoginForm;



    public LoginActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);

        setUpView();
        return view;
    }

    private void setUpView() {
        registerButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                String username_string = registerUsernameEdit.getText().toString();
                String password_string = registerPasswordEdit.getText().toString();


                if (register(username_string, password_string)) {
                    save(username_string);
                    Intent intent = new Intent(getActivity(), NewsListActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), "注册失败，已经存在该用户名或者别的……", Toast.LENGTH_LONG).show();
                }
            }


        });
        registerSigninButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub


                String username_string = registerUsernameEdit.getText().toString();
                String password_string = registerPasswordEdit.getText().toString();

                if (login(username_string, password_string)) {
                    save(username_string);
                    Intent intent = new Intent(getActivity(), NewsListActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), "登陆失败，密码出错后者别的……", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private boolean register(String param0, String param1) {
        boolean flag = false;
        String url = "servlet/RegisterAction?userName="+param0+"&passWord="+param1;
        String jsonString = HttpUtils.getJsonContent(url);
        flag= JsonTools.getResult("result",jsonString);
        Log.i("registerACtion", "the result is " + flag);
        return flag;
    }



    private boolean login(String param0, String param1) {
        boolean flag = false;
        String url = "servlet/LoginAction?userName="+param0+"&passWord="+param1;
        String jsonString = HttpUtils.getJsonContent(url);
        flag= JsonTools.getResult("result",jsonString);
        Log.i("loginAction", "the result is " + flag);

        return flag;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public void save(String inputText) {
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try {
            out = getActivity().openFileOutput("theusername", Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(inputText);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



}
