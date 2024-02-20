package com.example.lab1;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    protected Button btnTest;
    protected Spinner spinner;
    protected EditText hoten;
    protected EditText sdt;

    protected Button btnAdd;

    protected FloatingActionButton addImg;
    protected ArrayList arrayList;

    protected ListView list;

    protected CheckBox ck1;
    protected CheckBox ck2;

    protected RadioButton Nam;
    protected RadioButton Nu;

    protected ImageView img;
    protected Drawable drawable;

    protected ArrayList<Contact> contactList;

    protected ActivityResultLauncher<Intent> resultLauncher;

    protected String[] tinh={"Hà Nội" , "TP. Hồ Chí Minh" , "Đà Nẵng" , "Hải Phòng" , "Cần Thơ" ,
            "An Giang" , "Bà Rịa - Vũng Tàu" , "Bắc Giang" , "Bắc Kạn" , "Bạc Liêu" ,
            "Bắc Ninh" , "Bến Tre" , "Bình Định" , "Bình Dương" , "Bình Phước" ,
            "Bình Thuận" , "Cà Mau" , "Cao Bằng" , "Đắk Lắk" , "Đắk Nông" ,
            "Điện Biên" , "Đồng Nai" , "Đồng Tháp" , "Gia Lai" , "Hà Giang" ,
            "Hà Nam" , "Hà Tĩnh" , "Hải Dương" , "Hậu Giang" , "Hòa Bình" ,
            "Hưng Yên" , "Khánh Hòa" , "Kiên Giang" , "Kon Tum" , "Lai Châu" ,
            "Lâm Đồng" , "Lạng Sơn" , "Lào Cai" , "Long An" , "Nam Định" ,
            "Nghệ An" , "Ninh Bình" , "Ninh Thuận" , "Phú Thọ" , "Phú Yên" ,
            "Quảng Bình" , "Quảng Nam" , "Quảng Ngãi" , "Quảng Ninh" , "Quảng Trị" ,
            "Sóc Trăng" , "Sơn La" , "Tây Ninh" , "Thái Bình" , "Thái Nguyên" ,
            "Thanh Hóa" , "Thừa Thiên - Huế" , "Tiền Giang" , "Trà Vinh" , "Tuyên Quang" ,
            "Vĩnh Long" , "Vĩnh Phúc"};

    protected ArrayAdapter arrayAdapter;
    protected ArrayAdapter adapterlist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner);
        hoten = findViewById(R.id.hoten);
        sdt = findViewById(R.id.Sdt);
        ck1= findViewById(R.id.checkBox);
        ck2= findViewById(R.id.checkBox2);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(MainActivity.this, "Selected item:"+item, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        arrayAdapter = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                tinh);

        // set simple layout resource file
        // for each item of spinner
        arrayAdapter.setDropDownViewResource(
                android.R.layout
                        .simple_spinner_dropdown_item);

        // Set the ArrayAdapter (ad) data on the
        // Spinner which binds data to spinner
        spinner.setAdapter(arrayAdapter);

        arrayList = new ArrayList();
        arrayList.add("Nguyễn Thị Phương Anh- Nữ - 0123456789- Hà Nội");
        list = findViewById(R.id.list);
        //adapterlist = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, arrayList);
        adapterlist = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, contactList);
        btnAdd = findViewById(R.id.button);
        list.setAdapter(adapterlist);
        Nam = findViewById(R.id.rbtNam);
        Nu = findViewById(R.id.rbtNu);

        contactList.add(new Contact(1,"img1", " Nguyễn Văn An ", "0312200345"));
        contactList.add(new Contact(1,"img1", " Nguyễn Văn An ", "0312200345"));
        contactList.add(new Contact(1,"img1", " Nguyễn Văn An ", "0312200345"));

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item ="";
                String gender="";
                if(Nam.isChecked()) gender= "Nam" ;else gender="Nữ";

                item= hoten.getText().toString()+ "-"+gender+"-"+ sdt.getText().toString()+"-"+ spinner.getSelectedItem().toString();

                if(ck1.isChecked()) item += "-"+ ck1.getText().toString();
                if(ck2.isChecked()) item += "-"+ ck2.getText().toString();

                arrayList.add(item);
                adapterlist.notifyDataSetChanged();
            }
        });

        btnTest= findViewById(R.id.button4);
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1.tao 1 intent de mo sub act
                Intent intent = new Intent(MainActivity.this, testAct.class);
                //2. truyen du lieu sang sub act bang bundle
                //3. mo sub bang cach goi ham
                //startActivity = startActivityForResult
                startActivityIfNeeded(intent,100);//100 la ma cua so lan gui yeu cau
            }
        });

        img = findViewById(R.id.imageView4);
        addImg = findViewById(R.id.floatingActionButton);
        registerResult();
        addImg.setOnClickListener(view-> pickImg());

    }

    private void pickImg(){
        Intent intent = new Intent(MediaStore.ACTION_PICK_IMAGES);
        resultLauncher.launch(intent);
    }
    private void registerResult(){
        resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult o) {
                try{
                    Uri imageUri = o.getData().getData();
                    img.setImageURI(imageUri);
                }catch (Exception e){
                    Toast.makeText(MainActivity.this, "Failed",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        Bundle b = data.getExtras();
        int id = b.getInt("Id");
        String name = b.getString("Name");
        String phone = b.getString("Phone");
        Contact newcontact = new Contact(id, "images", name, phone);
        if(requestCode==100 && resultCode==150){
            contactList.add(newcontact);
            arrayAdapter.notifyDataSetChanged();
        }else if(requestCode==200 && resultCode==150){
            //trung hop sub
            contactList.set(SelectedItemId, newcontact);
            arrayAdapter.notifyDataSetChanged();
        }

    }
}