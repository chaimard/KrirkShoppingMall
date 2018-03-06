package chaimard.krirk.ac.th.krirkshoppingmall.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import chaimard.krirk.ac.th.krirkshoppingmall.R;

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


    }//Main Method

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
