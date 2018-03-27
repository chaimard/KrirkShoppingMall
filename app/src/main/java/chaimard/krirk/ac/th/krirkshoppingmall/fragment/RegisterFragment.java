package chaimard.krirk.ac.th.krirkshoppingmall.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import chaimard.krirk.ac.th.krirkshoppingmall.MainActivity;
import chaimard.krirk.ac.th.krirkshoppingmall.R;
import chaimard.krirk.ac.th.krirkshoppingmall.utility.AddNewUserToServer;
import chaimard.krirk.ac.th.krirkshoppingmall.utility.MyAlert;
import chaimard.krirk.ac.th.krirkshoppingmall.utility.MyConstant;

/**
 * Created by hero on 6/3/2561.
 */

public class RegisterFragment extends Fragment {

    //Explicit ประกาศตัวแปร
    private String nameString,userString,passwordString, modeString;
    private boolean aBoolean = true;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Crete Toolbar

        creteToolbar();

//        Radio Controller
        // พิพม์ RadioGroup radioGroup = getView().findViewById(R.id.ragMode); แล้วไฮไลน์ กด ctl+alt+M
        radioController();

        //RadioGroup radioGroup = getView().findViewById(R.id.ragMode);


    } //Main Method

    private void radioController() {
        RadioGroup radioGroup = getView().findViewById(R.id.ragMode);
        //บรรทดข้างล่างพอพิพม์ new แล้วให้กด ctrl+space จะขึ้นตัวเลือกให้เอง แล้วก็ implement โดย Alt+Enter
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                aBoolean = false;
                switch (i) {
                    case R.id.radOwnerShop:
                        modeString = "OwnerShop";
                        break;
                    case R.id.radCustomer:
                        modeString = "Customer";
                        break;
                }

            }
        });


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.itemUpload) {

            //สร้างเมธอดเอง  พิมพ์ชื่อเสร็จแล้ว คลิก alt+enter สร้าง
            uploadToserver();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //ถูกสร้างอัตโนมัติจาก ชื่อ uploadToServer ข้างบน จากกดคีย์ลัด
    private void uploadToserver() {

    // Get Value From EditText  (กลับไปตั้งชื่อ ช่องต่างๆ ที่ fragment_register.xml ให้เรียบร้อยก่อนทำ
    // ประกาศตัวแปร explicit ก่อนดูที่บรรทัดประมาณ 25 สำหรับเก็บค่าจากช่องต่างๆ

        EditText nameEditText = getView().findViewById(R.id.edtName);
        EditText userEditText = getView().findViewById(R.id.edtUser);
        EditText passwordEditText = getView().findViewById(R.id.edtPassword);

        nameString = nameEditText.getText().toString().trim();
        userString = userEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();

//      Check Space
        if (nameString.isEmpty() || userString.isEmpty() || passwordString.isEmpty()) {
            //Have space
            MyAlert myAlert = new MyAlert(getActivity());
            myAlert.myDialog(getString(R.string.title_have_space)
                    ,getString(R.string.message_have_space));
        } else if (aBoolean) {
//            None Choose Mode
            MyAlert myAlert = new MyAlert(getActivity());
            myAlert.myDialog("None Choose Mode",
                    "Please Choose Mode");

        } else {
//            Choose Mode Ok

            try {

                MyConstant myConstant = new MyConstant();
                AddNewUserToServer addNewUserToServer = new AddNewUserToServer(getActivity());
                addNewUserToServer.execute(nameString,userString,
                        passwordString,modeString,myConstant.getUrlAddUser());

                String result = addNewUserToServer.get();
                if (Boolean.parseBoolean(result)) {
                    getActivity().getSupportFragmentManager().popBackStack();
                } else {
                    Toast.makeText(getActivity(),"Please Try again",
                            Toast.LENGTH_LONG).show();

                }

            } catch (Exception e) {
                e.printStackTrace();
            }


        } // if





    } // uploadToServer

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_register, menu);
        super.onCreateOptionsMenu(menu, inflater);

    }

    private void creteToolbar() {

        setHasOptionsMenu(true);

        Toolbar toolbar = getView().findViewById(R.id.toolbarRegister);
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);

        //ใส่ข้อความบน toolbar
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.register));
        ((MainActivity) getActivity()).getSupportActionBar().setSubtitle(getString(R.string.message_have_space));

        //ใส่ปุ่ม back บน toolbar
        ((MainActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //ใส่โค้ดให้กลับไป fragment ก่อนหน้า
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();

            }
        });


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        return view;


    }
}
