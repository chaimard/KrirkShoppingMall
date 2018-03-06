package chaimard.krirk.ac.th.krirkshoppingmall.utility;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import chaimard.krirk.ac.th.krirkshoppingmall.R;

/**
 * Created by hero on 6/3/2561.
 */

public class MyAlert  {

    private Context context;

    public MyAlert(Context context) {
        this.context = context;

    }

    public void myDialog(String titleString,String messageString) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);  //กำหนดให้ระบบ unknow หยุดทำงานชั่วคราว ป้องกันผู้ใ้ชกดยกเลิก
        builder.setIcon(R.drawable.ic_action_alert);  // ไปแอดรูปกระดิ่งที่ App -> Image Asset
        builder.setTitle(titleString);
        builder.setMessage(messageString);

        //ตรงนี้พอพิพม์บรรทัด 31 แล้วให้กด alt enter ที่หยักสีแดง onclick... ให้ระบบ implement public void 33-36 เอง
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss();
            }
        });
        builder.show();


    }


} // main class
