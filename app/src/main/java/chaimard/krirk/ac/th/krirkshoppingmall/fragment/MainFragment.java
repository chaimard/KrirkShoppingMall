package chaimard.krirk.ac.th.krirkshoppingmall.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import chaimard.krirk.ac.th.krirkshoppingmall.R;
import chaimard.krirk.ac.th.krirkshoppingmall.ServiceActivity;
import chaimard.krirk.ac.th.krirkshoppingmall.utility.GetAllDataByURL;
import chaimard.krirk.ac.th.krirkshoppingmall.utility.MyAlert;
import chaimard.krirk.ac.th.krirkshoppingmall.utility.MyConstant;

/**
 * Created by hero on 6/3/2561.
 */

public class MainFragment extends Fragment {

    //สร้างอันนี้อันที่ 2 หลังจากที่ไปสร้าง id จาก fragment_main.xml แล้ว
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



//        Register Controler    สร้างอันนี้อันที่ 3 โดยไฮไลน์ทำบรรทัด 33 แล้วกด
        registerControler();

//        Login Controller    // ก่อนอื่นพิพม์ด้านล่างคอมเมนต์นี้ว่า Button button = getView().findViewById(R.id.btnlogin);
// แล้วเลือกทั้งหมด กดคีย์ ctrl+alt+M
        loginController();

    }//Main Method

    private void loginController() {
        Button button = getView().findViewById(R.id.btnlogin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText userEditText = getView().findViewById(R.id.btnUser);
                EditText passwordEditText = getView().findViewById(R.id.btnPassword);

                String userString = userEditText.getText().toString().trim();
                String passwordString = passwordEditText.getText().toString().trim();

                if (userString.isEmpty() || passwordString.isEmpty()) {
//                    Have Space
                    MyAlert myAlert = new MyAlert(getActivity());
                    myAlert.myDialog(getString(R.string.title_have_space),
                            getString(R.string.message_have_space));


                } else {
//                    No space
                    try {
                        MyConstant myConstant = new MyConstant();
                        boolean statusAboolean = true;
                        GetAllDataByURL getAllDataByURL = new GetAllDataByURL(getActivity());
                        getAllDataByURL.execute(myConstant.getUrlGetAllUserString());
                        String jsonString = getAllDataByURL.get();
                        Log.d("27MarchV1", "JSON==>" + jsonString);

                        JSONArray jsonArray = new JSONArray(jsonString);
                        String[] columnStrings = myConstant.getColumnUser();
                        String[] loginStrings = new String[columnStrings.length];

                        for (int i=0;i<jsonArray.length();i++) {

                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            if (userString.equals(jsonObject.getString(columnStrings[2]))) {

                                statusAboolean = false;
                                for (int i1=0;i1 < columnStrings.length; i1+=1) {
                                    loginStrings[i1] = jsonObject.getString(columnStrings[i1]);


                                }  //for i1
                            } // if

                        }//for i

                        if (statusAboolean) {
//                            User false
                            MyAlert myAlert = new MyAlert(getActivity());
                            myAlert.myDialog("User False",
                                    "No This User in my DB");

                        } else if (passwordString.equals(loginStrings[3])) {
//                            Password True
                            Toast.makeText(getActivity(),"Welcome " + loginStrings[1],
                                    Toast.LENGTH_SHORT).show();

//                            MOVE to ServiceActivity
                            Intent intent = new Intent(getActivity(), ServiceActivity.class);
                            intent.putExtra("Login", loginStrings);
                            startActivity(intent);  //ให้ไปใช้ service ใหม่ กระโดดไปหน้าอื่น
                            getActivity().finish();  //สั่งให้ล้าง service เก่า

                        } else {
//                            Password False
                            MyAlert myAlert = new MyAlert(getActivity());
                            myAlert.myDialog("Password False",
                                    "Please Try again Password False");

                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }//try


                } //if

            }
        });
    }

    private void registerControler() {
        TextView textView = getView().findViewById(R.id.txtNewRegister);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Replace Fragment
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentMainFragment, new RegisterFragment())
                        .addToBackStack(null)
                        .commit();


            }
        });

    }

    @Nullable
    @Override

    //สร้างอันนี้อันแรกก่อน
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        return view;

    }
}
