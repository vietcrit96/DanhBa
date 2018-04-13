package com.example.viet.danhba;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.viet.danhba.CustomContact.CustomAdapter;
import com.example.viet.danhba.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText edtName;
    private EditText edtNumber;
    private RadioButton rbtnMale;
    private RadioButton rbtnFemale;
    private Button btnAddContact;
    private ListView lvMain;

    private List<Contact> arrayContact;
    private CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        initWidget();
        arrayContact = new ArrayList<>();
        setOnClickListener();
        lvMain.setAdapter(adapter);

    }

    public void initWidget() {
        edtName = (EditText) findViewById( R.id.edt_name );
        edtNumber = (EditText) findViewById( R.id.edt_number );
        rbtnMale = (RadioButton) findViewById( R.id.rbtn_male );
        rbtnFemale = (RadioButton) findViewById( R.id.rbtn_female );
        btnAddContact = (Button) findViewById( R.id.btn_addcontact );
        lvMain = (ListView) findViewById( R.id.lv_main );
    }

    public void setOnClickListener() {
        btnAddContact.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isMale = true;
                String name = edtName.getText().toString().trim();
                String number = edtNumber.getText().toString().trim();
                if (rbtnMale.isChecked()) {
                    isMale = true;
                }else isMale = false;
                if (TextUtils.isEmpty(name)||TextUtils.isEmpty(number)) {
                    Toast.makeText( MainActivity.this, "Please input fill name or number phone", Toast.LENGTH_SHORT ).show();
                } else {
                    Contact contact = new Contact( isMale,name,number);
                    arrayContact.add( contact );
                }
                adapter.notifyDataSetChanged();
            }
        } );
        adapter = new CustomAdapter( this, R.layout.custom_listview, arrayContact );
    }
}
