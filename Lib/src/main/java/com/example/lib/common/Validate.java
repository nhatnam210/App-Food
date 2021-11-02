package com.example.lib.common;

import android.view.View;
import android.widget.TextView;

public class Validate {
    public static boolean isValidName(String value, TextView textView) {
        if(value.trim().length() <= 0) {
            textView.setVisibility(View.VISIBLE);
            textView.setText("Vui lòng nhập tên khách hàng!");
            return false;
        }
            textView.setVisibility(View.GONE);
            textView.setText(" ");
            return true;
    }

    static boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isValidEmail(String value, TextView textView) {
        if(value.trim().length() <= 0) {
            textView.setVisibility(View.VISIBLE);
            textView.setText("Vui lòng nhập email!");
            return false;
        }else if(!isEmailValid(value.trim())) {
            textView.setVisibility(View.VISIBLE);
            textView.setText("Email chưa đúng định dạng!");
            return false;
        }
            textView.setVisibility(View.GONE);
            textView.setText(" ");
            return true;
    }

    public static boolean isValidPhone(String value, int max, TextView textView) {
        if(value.trim().length() <= 0) {
            textView.setVisibility(View.VISIBLE);
            textView.setText("Vui lòng nhập số điện thoại!");
            return false;
        }else if(value.trim().length() > max) {
            textView.setVisibility(View.VISIBLE);
            textView.setText("Số điện thoại không được vượt quá "+max+" ký tự!");
            return false;
        }
            textView.setVisibility(View.GONE);
            textView.setText(" ");
            return true;
    }
}
