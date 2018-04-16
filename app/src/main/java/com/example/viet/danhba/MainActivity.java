package com.example.viet.danhba;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
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
        lvMain.setAdapter( adapter );
        requestPermissions();
        lvMain.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showDialogs( position );
            }

        } );

    }

    public void initWidget() {
        edtName = findViewById( R.id.edt_name );
        edtNumber = findViewById( R.id.edt_number );
        rbtnMale = findViewById( R.id.rbtn_male );
        btnAddContact = findViewById( R.id.btn_addcontact );
        lvMain = findViewById( R.id.lv_main );
    }

    public void setOnClickListener() {
        btnAddContact.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isMale;
                String name = edtName.getText().toString().trim();
                String number = edtNumber.getText().toString().trim();
                if (rbtnMale.isChecked()) {
                    isMale = true;
                } else {
                    isMale = false;
                }
                if (TextUtils.isEmpty( name ) || TextUtils.isEmpty( number )) {
                    Toast.makeText( MainActivity.this, "Please input fill name or number phone", Toast.LENGTH_SHORT ).show();
                } else {
                    Contact contact = new Contact( isMale, name, number );
                    arrayContact.add( contact );
                }
                adapter.notifyDataSetChanged();
            }
        } );
        adapter = new CustomAdapter( this, R.layout.custom_listview, arrayContact );
    }

    public void showDialogs(final int position) {
        Dialog dialog = new Dialog( this );
        dialog.setContentView( R.layout.custom_dialog_layout );
        Button btnCall = dialog.findViewById( R.id.btn_call );
        Button btnMess = dialog.findViewById( R.id.btn_mess );
        btnCall.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentCall( position );
            }
        } );
        btnMess.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentMess(position);
            }
        } );
        dialog.show();
    }

    public void requestPermissions() {
        String[] permissions = new String[]{
                Manifest.permission.CALL_PHONE, Manifest.permission.SEND_SMS
        };
        List<String> listPermissions = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission( this, permission ) != PackageManager.PERMISSION_GRANTED) {
                listPermissions.add( permission );
            }
        }
        if (!listPermissions.isEmpty()) {
            ActivityCompat.requestPermissions( this, listPermissions.toArray( new String[listPermissions.size()] ), 1 );
        }
    }

    public void intentCall(int position) {
        Intent intent = new Intent();
        intent.setAction( Intent.ACTION_CALL );
        intent.setData( Uri.parse( "tel:" + arrayContact.get( position ).getmNumber() ) );
        startActivity( intent );
    }

    public void intentMess(int position) {
        Intent intent = new Intent();
        intent.setAction( Intent.ACTION_VIEW );
        intent.setData( Uri.parse( "sms:" + arrayContact.get( position ).getmNumber() ) );
        startActivity( intent );
    }
}
