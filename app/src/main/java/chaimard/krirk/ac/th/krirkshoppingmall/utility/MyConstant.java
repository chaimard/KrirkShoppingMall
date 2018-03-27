package chaimard.krirk.ac.th.krirkshoppingmall.utility;

/**
 * Created by hero on 27/3/2561.
 */

public class MyConstant {

    //    About  Url
    private String urlAddUser = "http://androidthai.in.th/kir/addDataKama.php";
    private String urlGetAllUserString = "http://androidthai.in.th/kir/getAllUserKama.php";


    //    About Array
    private String[] columnUser = new String[]{"id","Name", "User", "Password", "Mode"};

    public String[] getColumnUser() {

        return columnUser;
    }

    //ตรงนี้สร้างจากคำสั่งตัวแปร private ๅๅ  แล้วกด alt+insert เลือก getter  เมื่ออยู่ใต้ตัวแปรใด  มันจะสร้าง getter ของตัวนั้นให้
    public String getUrlGetAllUserString() {
        return urlGetAllUserString;
    }

    public String getUrlAddUser() {
        return urlAddUser;
    }

} //Main
