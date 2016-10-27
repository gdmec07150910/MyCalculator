package com.example.administrator.mycalculator;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button btn1;
    private EditText et1;
    private RadioButton mrb;
    private RadioButton wrb;
    private TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = (Button) findViewById(R.id.calculate);
        et1 = (EditText) findViewById(R.id.weight);
        mrb = (RadioButton) findViewById(R.id.man);
        wrb = (RadioButton) findViewById(R.id.women);
        tv1 = (TextView) findViewById(R.id.result);
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerEvent();
    }

    private  void registerEvent(){
        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(!et1.getText().toString().trim().equals("")){
                    if(mrb.isChecked()||wrb.isChecked()){
                        Double weight = Double.parseDouble(et1.getText().toString());
                        StringBuilder sb = new StringBuilder();
                        sb.append("-----------评估结果----------\n");
                        if(mrb.isChecked()){
                            sb.append("男的标准身高是:");
                            double result = evaluateHeight(weight,"男");
                            sb.append((int)result+"厘米");
                        }else if(wrb.isChecked()){
                            sb.append("女的标准身高是:");
                            double result = evaluateHeight(weight,"女");
                            sb.append((int)result+"厘米");
                        }
                        tv1.setText(sb.toString());
                    }else {
                        showMessage("请选择性别！");
                    }
                }else {
                    showMessage("请输入体重");
                }
            }
        });

    }

    private double evaluateHeight(double weight, String sex){
        double height;
        if(sex=="男"){
            height=170-(62-weight)/0.6;
        }else {
            height=158-(52-weight)/0.5;
        }
        return height;
    }

    private void showMessage(String message) {
        AlertDialog ad = new AlertDialog.Builder(this).create();
        ad.setTitle("系统信息");
        ad.setMessage(message);
        ad.setButton(DialogInterface.BUTTON_POSITIVE,"确定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        ad.show();
    }

    public boolean onCreateOptionMenu(Menu menu){
        menu.add(0,1,1,"退出");
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case 1:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
