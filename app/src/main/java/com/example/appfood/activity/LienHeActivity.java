package com.example.appfood.activity;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.appfood.R;
import com.example.lib.common.NetworkConnection;
import com.example.lib.common.Show;

public class LienHeActivity extends AppCompatActivity {
    Toolbar toolbar_Lienhe;
    TextView thongbao_soluong,text_phone,mess_label,mess_error;
    EditText text_mess;
    Button btn_sent_mess;
    ScrollView scroll_mess;
    ImageView img_mess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lien_he);
        getViewId();
        actionToolbar();

        //check network
        if(NetworkConnection.isConnected(this)) {
            Show.thayDoiSoLuongGioHangNho(thongbao_soluong);
        }else{
            Show.Notify(this,getString(R.string.error_network));
            finish();
        }
    }

    private void actionToolbar() {
        setSupportActionBar(toolbar_Lienhe);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar_Lienhe.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void getViewId() {
        toolbar_Lienhe = findViewById(R.id.toolbar_Lienhe);
        thongbao_soluong = findViewById(R.id.thongbao_soluong);
        text_phone = findViewById(R.id.text_phone);
        btn_sent_mess = findViewById(R.id.btn_sent_mess);
        text_mess = findViewById(R.id.text_mess);
        mess_label = findViewById(R.id.mess_label);
        scroll_mess = findViewById(R.id.scroll_mess);
        img_mess = findViewById(R.id.img_mess);
        mess_error = findViewById(R.id.mess_error);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Show.thayDoiSoLuongGioHangNho(thongbao_soluong);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Show.thayDoiSoLuongGioHangNho(thongbao_soluong);
    }


    public void ToHome(View view) {
        Intent trangchu = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(trangchu);
    }

    public void openCart(View view) {
        Intent giohang = new Intent(getApplicationContext(),GioHangActivity.class);
        startActivity(giohang);
    }

    public void DitalPhone(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL);

        Uri uri = Uri.parse("tel:"+text_phone.getText().toString().trim());
        intent.setData(uri);

        startActivity(intent);
    }

        public void sentMess(View view) {
        SmsManager smsManager = SmsManager.getDefault();

        Intent intent = new Intent("ACTION_MSG_SENT");
        PendingIntent pendingIntent
                = PendingIntent.getBroadcast(LienHeActivity.this,0, intent,0);

        String phone = text_phone.getText().toString().replaceAll(" ", "");
        String mess = text_mess.getText().toString();
        if(mess.trim().length() > 0) {
            smsManager.sendTextMessage(
                    phone,null,
                    mess,pendingIntent,null);
        }else{
            mess_error.setVisibility(View.VISIBLE);
        }


        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int result = getResultCode();
                if(result == RESULT_OK) {
                    Show.Notify(getApplicationContext(),getString(R.string.mess_send_success));
                    scroll_mess.setVisibility(View.GONE);
                    img_mess.setVisibility(View.VISIBLE);
                    text_mess.clearComposingText();
                }else{
                    Show.Notify(getApplicationContext(),getString(R.string.mess_send_error));
                }
            }
        },new IntentFilter("ACTION_MSG_SENT"));
    }

    public void openMess(View view) {
        scroll_mess.setVisibility(View.VISIBLE);
        img_mess.setVisibility(View.GONE);
    }

}