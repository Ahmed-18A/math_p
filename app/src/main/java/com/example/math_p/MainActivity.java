package com.example.math_p;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int level=1,m;
    Button lv;
    TextView num1,num2,calc,ch_box;
    EditText answer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv=findViewById(R.id.lv);
        num1=findViewById(R.id.n1);
        num2=findViewById(R.id.n2);
        calc=findViewById(R.id.calc);
        answer=findViewById(R.id.answer);
        ch_box=findViewById(R.id.ch_box);
    }

    public void ch_level(View view) {
        if(level==1) {
            level++;
            lv.setText("Level: 2");
        }
        else {
            if (level == 2) {
                level++;
                lv.setText("Level: 3");
            }
            else {
                level=1;
                lv.setText("Level: 1");
            }
        }
    }

    public void reset(View view) {
        m=random(1,5);
        if (m==1)
            calc.setText("+");
        if (m==2)
            calc.setText("-");
        if (m==3)
            calc.setText("*");
        if (m==4)
            calc.setText("/");
        if(level==1) {
            num1.setText(String.valueOf(random(0,11)));
            num2.setText(String.valueOf(random(0,11)));
            if(m==4)
                num2.setText(String.valueOf(random(1,11)));
        }
        else {
            if (level == 2) {
                num1.setText(String.valueOf(random(10,101)));
                num2.setText(String.valueOf(random(10,101)));
            }
            else {
                num1.setText(String.valueOf(random(101,1001)));
                num2.setText(String.valueOf(random(101,1001)));
            }
        }
    }

    public int random (int n1,int n2){
        Random ra = new Random();
        return ra.nextInt((n2 - n1) + 1) + n1;
    }

    public void ch_answer(View view) {
        if (!(num1.getText().toString().isEmpty() || num2.getText().toString().isEmpty() || answer.getText().toString().isEmpty())) {
            int n1 = Integer.parseInt(num1.getText().toString());
            int n2 = Integer.parseInt(num2.getText().toString());
            double userAnswer = Double.parseDouble(answer.getText().toString());
            double correctAnswer = 0;

            if (m == 1) // +
                correctAnswer = n1 + n2;
            if (m == 2)  // -
                correctAnswer = n1 - n2;
            if (m == 3)  // *
                correctAnswer = n1 * n2;
            if (m == 4)  // /
                correctAnswer = (double) n1 / n2;
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            if (Math.abs(userAnswer - correctAnswer) < 0.01) {
                ch_box.setText("أحسنت");
                ch_box.setTextColor(0xFF388E3C);
            } else {
                ch_box.setText("خطأ, الاجابة هي: " + correctAnswer);
                ch_box.setTextColor(0xFFD32F2F);
            }
        }
        else
            Toast.makeText(this, "يجب ادخال الاجابة وتوليد ارقام", Toast.LENGTH_SHORT).show();
    }
}