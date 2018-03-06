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

import chaimard.krirk.ac.th.krirkshoppingmall.MainActivity;
import chaimard.krirk.ac.th.krirkshoppingmall.R;

/**
 * Created by hero on 6/3/2561.
 */

public class RegisterFragment extends Fragment {


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Crete Toolbar

        creteToolbar();


    } //Main Method


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()==R.id.itemUpload){

            //สร้างเมธอดเอง  พิมพ์ชื่อเสร็จแล้ว คลิก alt+enter สร้าง
            uploadToserver();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //ถูกสร้างอัตโนมัติจาก ชื่อ uploadToServer ข้างบน จากกดคีย์ลัด
    private void uploadToserver() {

//        Get Value From EditText  (กลับไปตั้งชื่อ ช่องต่างๆ ที่ fragment_register.xml ให้เรียบร้อยก่อนทำ)












    } // uploadToServer

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_register,menu);
        super.onCreateOptionsMenu(menu, inflater);

    }

    private void creteToolbar() {

        setHasOptionsMenu(true);

        Toolbar toolbar = getView().findViewById(R.id.toolbarRegister);
        ((MainActivity)getActivity()).setSupportActionBar(toolbar);

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
