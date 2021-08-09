package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn0,btndiv,btnequal,btnplus,btndel,btndot,btnmulti,btnminus,btnAC;
    private TextView textviewHistory,textviewResult;
    private String number=null;

    double lastnumber=0;
    double firstnumber=0;

    String status=null;
    String history,result;

    boolean operator=false;
    boolean dot=true;
    boolean ACcontrol=true;
    boolean equal=false;
    boolean isEqual=false;

    //cứ 3 số nguyên thì ngăn cách bằng dáu phẩy, phần nguyên với phần thập phân ngăn nhau bằng dấu chấm
    String pattern ="###,###.#####";
    DecimalFormat decimalFormat=new DecimalFormat(pattern); //Định dạng số thập phân

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //gắn các button vào các biến
        btn0= this.findViewById(R.id.btn0);
        btn1= this.findViewById(R.id.btn1);
        btn2= this.findViewById(R.id.btn2);
        btn3= this.findViewById(R.id.btn3);
        btn4= this.findViewById(R.id.btn4);
        btn5= this.findViewById(R.id.btn5);
        btn6= this.findViewById(R.id.btn6);
        btn7= this.findViewById(R.id.btn7);
        btn8= this.findViewById(R.id.btn8);
        btn9= this.findViewById(R.id.btn9);

        //gắn các button phép tính vào các biến
        btnplus= this.findViewById(R.id.btnPlus);
        btnminus= this.findViewById(R.id.btnMinus);
        btnmulti= this.findViewById(R.id.btnMUTIL);
        btndiv= this.findViewById(R.id.btnDiv);

        //gắn các button phụ vào các biến
        btnAC= this.findViewById(R.id.btnAC);
        btndel= this.findViewById(R.id.btnDEl);
        btndot= this.findViewById(R.id.btnDot);
        btnequal= this.findViewById(R.id.btnEqual);

        //gắn các textview vào các biến
        textviewHistory=this.findViewById(R.id.textviewHistory);
        textviewResult=this.findViewById(R.id.textviewResult);

        //bắt các sự kiện khi cick vào các nút số
        btn0.setOnClickListener(view -> numberClick("0"));
        btn1.setOnClickListener(view -> numberClick("1"));
        btn2.setOnClickListener(view -> numberClick("2"));
        btn3.setOnClickListener(view -> numberClick("3"));
        btn4.setOnClickListener(view -> numberClick("4"));
        btn5.setOnClickListener(view -> numberClick("5"));
        btn6.setOnClickListener(view -> numberClick("6"));
        btn7.setOnClickListener(view -> numberClick("7"));
        btn8.setOnClickListener(view -> numberClick("8"));
        btn9.setOnClickListener(view -> numberClick("9"));

        //bắt các sự kiện khi cick vào các nút phép tính
        btnplus.setOnClickListener(view -> {

            if(isEqual){
                history=textviewResult.getText().toString();
                result=textviewResult.getText().toString();
                textviewHistory.setText(history+"+");
            } else {
                history=textviewHistory.getText().toString();
                result=textviewResult.getText().toString();
                textviewHistory.setText(history+result+"+");
            }

            if(operator){
                if(status=="multi"){
                    multi();
                } else {
                    if(status=="div"){
                        div();
                    }else {
                        if(status=="minus"){
                            minus();
                        }else {
                            plus();
                        }
                    }
                }
            }

            isEqual=false;
            status="sum";
            operator=false;
            number=null;
        });

        btnminus.setOnClickListener(view -> {

            if(isEqual){
                history=textviewResult.getText().toString();
                result=textviewResult.getText().toString();
                textviewHistory.setText(history+"-");
            } else {
                history=textviewHistory.getText().toString();
                result=textviewResult.getText().toString();
                textviewHistory.setText(history+result+"-");
            }

            if(operator){
                if(status=="multi"){
                    multi();
                } else {
                    if(status=="div"){
                        div();
                    }else {
                        if(status=="sum"){
                            plus();
                        }else {
                            minus();
                        }
                    }
                }
            }

            isEqual=false;
            status="minus";
            operator=false;
            number=null;
        });

        btnmulti.setOnClickListener(view -> {

            if(isEqual){
                history=textviewResult.getText().toString();
                result=textviewResult.getText().toString();
                textviewHistory.setText(history+"*");
            } else {
                history=textviewHistory.getText().toString();
                result=textviewResult.getText().toString();
                textviewHistory.setText(history+result+"*");
            }

            if(operator){
                if(status=="sum"){
                    plus();
                } else {
                    if(status=="div"){
                        div();
                    }else {
                        if(status=="minus"){
                            minus();
                        }else {
                            multi();
                        }
                    }
                }
            }

            isEqual=false;
            status="multi";
            operator=false;
            number=null;
        });

        btndiv.setOnClickListener(view -> {

            if(isEqual){
                history=textviewResult.getText().toString();
                result=textviewResult.getText().toString();
                textviewHistory.setText(history+"/");
            } else {
                history=textviewHistory.getText().toString();
                result=textviewResult.getText().toString();
                textviewHistory.setText(history+result+"/");
            }

            if(operator){
                if(status=="multi"){
                    multi();
                } else {
                    if(status=="sum"){
                        plus();
                    }else {
                        if(status=="minus"){
                            minus();
                        }else {
                            div();
                        }
                    }
                }
            }

            isEqual=false;
            status="div";
            operator=false;
            number=null;
        });

        btnequal.setOnClickListener(view -> {

            history=textviewHistory.getText().toString();
            result=textviewResult.getText().toString();
            textviewHistory.setText(history+result);

            if(operator){
                if(status==("sum")){
                    plus();
                } else {
                    if(status=="div"){
                        div();
                    }else {
                        if(status=="minus"){
                            minus();
                        }else {
                            if(status=="multi") {
                                multi();
                            }else {
                                firstnumber=Double.parseDouble(textviewResult.getText().toString());
                            }
                        }
                    }
                }
            }

            isEqual=true;
            operator=false;
            equal=true;
        });

        btnAC.setOnClickListener(view -> {

            isEqual=false;
            number=null;
            operator=false;
            textviewResult.setText("0");
            textviewHistory.setText("");
            firstnumber=0;
            lastnumber=0;
            dot=true;
            ACcontrol=true;
        });

        btndel.setOnClickListener(view ->{

            if(ACcontrol){
                textviewResult.setText("0");
            }else {
                number=number.substring(0,number.length()-1); //tạo chuỗi mới bắt đầu từ 0, đến vị trí cuối trừ 1

                if(number.length()==0){
                    btndel.setClickable(false); //ngăn người dùng ấn button
                }else {
                    if(number.contains(".")){
                        dot=false;
                    }else {
                        dot=true;
                    }
                }

                textviewResult.setText(number);
            }
        });

        btndot.setOnClickListener(view -> {

            if(dot){
                if(number==null){
                    number="0.";
                } else {
                    number=number+".";
                }
            }

            textviewResult.setText(number);
            dot=false;
        });
    }

    //tạo method hiển thị khi click vào button
    public void numberClick(String view){

        if(number==null){
            number=view;
        } else{
            if(equal){
                firstnumber=0;
                lastnumber=0;
                number=view;
            }else
            {
                number=number+view;
            }
        }

        textviewResult.setText(number);
        operator=true;
        ACcontrol=false;
        btndel.setClickable(true);
        equal=false;
    }

    //tạo method các phép tính
    public void plus(){

        lastnumber=Double.parseDouble(textviewResult.getText().toString());  //chuyen doi string thanh double
        firstnumber=firstnumber+lastnumber;
        textviewResult.setText(decimalFormat.format(firstnumber));
        dot=true;
    }

    public void minus(){

        if(firstnumber==0){
            firstnumber=Double.parseDouble(textviewResult.getText().toString());
        } else
        {
            lastnumber=Double.parseDouble(textviewResult.getText().toString());
            firstnumber=firstnumber-lastnumber;
        }
        textviewResult.setText(decimalFormat.format(firstnumber));
        dot=true;

    }

    public void multi(){

        if (firstnumber==0){
            firstnumber=1;
        }
        lastnumber=Double.parseDouble(textviewResult.getText().toString());
        firstnumber=firstnumber*lastnumber;
        textviewResult.setText(decimalFormat.format(firstnumber));
        dot=true;
    }

    public void div(){

        if (firstnumber==0){
            lastnumber=Double.parseDouble(textviewResult.getText().toString());
            firstnumber=lastnumber;
        } else
        {
            lastnumber=Double.parseDouble(textviewResult.getText().toString());
            firstnumber=firstnumber/lastnumber;
        }
        
        textviewResult.setText(decimalFormat.format(firstnumber));
        dot=true;
    }
}
