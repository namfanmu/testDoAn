package com.example.testdoan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


public class activity_register extends AppCompatActivity {
    EditText edtUsername, edtPassword, edtPasswornconfirm, edtLicenseplate;
    Button btnRegister;
    String url="http://27.66.6.9/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        anhXa();
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=edtUsername.getText().toString().trim();
                String password=edtPassword.getText().toString().trim();
                String passwordconfirm=edtPasswornconfirm.getText().toString().trim();
                String licenseplate=edtLicenseplate.getText().toString().trim();
                if(username.isEmpty()||password.isEmpty()||passwordconfirm.isEmpty()||licenseplate.isEmpty())
                {
                    Toast.makeText(activity_register.this,"Nhap day du thong tin", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    register(url);
                }

            }
        });
    }
    private void register(String url){
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("success"))
                        {
                            Toast.makeText(activity_register.this,"Complete", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(activity_register.this,MainActivity.class));
                        }
                        else{
                            Toast.makeText(activity_register.this,"Failled", Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(activity_register.this,"Error", Toast.LENGTH_SHORT).show();

                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("username",edtUsername.getText().toString().trim());
                params.put("password",edtPassword.getText().toString().trim());
                params.put("password_confirm",edtPasswornconfirm.getText().toString().trim());
                params.put("licensePlate",edtLicenseplate.getText().toString().trim());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void anhXa() {
        edtUsername=(EditText)findViewById(R.id.edt_username);
        edtPassword=(EditText)findViewById(R.id.edt_password);
        edtPasswornconfirm=(EditText)findViewById(R.id.edt_passwordconfirm);
        edtLicenseplate=(EditText)findViewById(R.id.edt_licenseplate);
        btnRegister=(Button)findViewById(R.id.btn_register);
    }

}
