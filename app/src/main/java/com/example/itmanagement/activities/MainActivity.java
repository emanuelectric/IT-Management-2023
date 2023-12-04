package com.example.itmanagement.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

import com.example.itmanagement.R;
import com.example.itmanagement.data.DBHelper;

public class MainActivity extends AppCompatActivity {
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.main_layout);

            initializeApp();
        }

    private DBHelper dbHelper;

        private void initializeApp() {
            // Inicializa la aplicación aquí

            dbHelper = new DBHelper(this);


            // Delay transition by 2 seconds (2000 milliseconds)
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(MainActivity.this, MenuInicialActivity.class);
                    startActivity(intent);
                    finish(); // Finish MainActivity
                }
            }, 2000); // 2000 milliseconds = 2 seconds
        }

}

