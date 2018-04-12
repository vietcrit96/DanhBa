package com.example.viet.danhba;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.viet.danhba.CustomContact.CustomAdapter;
import com.example.viet.danhba.model.Contact;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText edtName;
    private EditText edtNumber;
    private RadioButton rbtnMale;
    private RadioButton rbtnFemale;
    private Button btnAddContact;
    private ListView lvMain;

    private ArrayList<Contact> arrayContact;
    private CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        initWidget();
        setOnClickListener();
        arrayContact = new ArrayList<>();
        adapter = new CustomAdapter( (View.OnClickListener) this, R.layout.custom_listview, arrayContact );
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
                if (rbtnMale.isChecked()) {
                    isMale = true;
                }
                isMale = false;
                if (edtName == null || edtNumber == null) {
                    Toast.makeText( MainActivity.this, "Please input fill name or number phone", Toast.LENGTH_SHORT ).show();
                } else {
                    Contact contact = new Contact( isMale, edtName.getText().toString().trim(), edtNumber.getText().toString().trim() );
                    arrayContact.add( contact );
                }
                adapter.notifyDataSetChanged();
            }
        } );
    }
}
