package com.example.registerlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.registerlogin.api.ApiClient;
import com.example.registerlogin.api.ApiInterface;
import com.example.registerlogin.model.update.Update;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateActivity extends AppCompatActivity implements View.OnClickListener {
    EditText etName, etGmail, etpass,etid;
    SessionManager sessionManager;
    String name, gmail,pass,id;
    ApiInterface apiInterface;
    Button btnUpdate,btnDelete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        sessionManager = new SessionManager(UpdateActivity.this);

        etName = findViewById(R.id.etUpdateName);
        etGmail = findViewById(R.id.etUpdateGmail);
        etpass = findViewById(R.id.etUpdatePass);
        etid = findViewById(R.id.etUpdateId);


        name = sessionManager.getUserDetail().get(SessionManager.NAME);
        gmail = sessionManager.getUserDetail().get(SessionManager.USERNAME);
        pass = sessionManager.getUserDetail().get(SessionManager.PASS);
        id = sessionManager.getUserDetail().get(SessionManager.USER_ID);


        etGmail.setText(gmail);
        etName.setText(name);
        etpass.setText(pass);
        etid.setText(id);

        btnUpdate = findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(this);
        btnDelete = findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnUpdate:
                id = etid.getText().toString();
                gmail = etGmail.getText().toString();
                pass = etpass.getText().toString();
                name = etName.getText().toString();
                update(id,gmail, pass, name);
                break;
            case R.id.btnDelete:
                id = etid.getText().toString();
                delete(id);
                break;

        }
    }

    private void update(String id,String username, String password, String name) {

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Update> call = apiInterface.updateResponse(id,username, password, name);
        call.enqueue(new Callback<Update>() {
            @Override
            public void onResponse(Call<Update> call, Response<Update> response) {
                if(response.body() != null && response.isSuccessful() && response.body().isStatus()){
                    Toast.makeText(UpdateActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(UpdateActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(UpdateActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Update> call, Throwable t) {
                Toast.makeText(UpdateActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
    private void delete(String id) {

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Update> call = apiInterface.deleteResponse(id);
        call.enqueue(new Callback<Update>() {
            @Override
            public void onResponse(Call<Update> call, Response<Update> response) {
                if(response.body() != null && response.isSuccessful() && response.body().isStatus()){
                    Toast.makeText(UpdateActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(UpdateActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(UpdateActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Update> call, Throwable t) {
                Toast.makeText(UpdateActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}