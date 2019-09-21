package com.hk.discount_calculator;

import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
private Button cal,clear;
private TextView show;
private EditText t1,t2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        operate();
    }



    public void operate(){
        cal=(Button)findViewById(R.id.calculateID);
        clear=(Button) findViewById(R.id.clearID);
        show=(TextView)findViewById(R.id.DisplayID);
        t1=(EditText)findViewById(R.id.price_ID);
        t2=(EditText)findViewById(R.id.percent_ID);

        //calculate button activity
        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //logical calculation
                double price;
                int percent;
                //when pressed calculate Button
                if(t1.getText().toString().isEmpty()||t2.getText().toString().isEmpty()){

                    Toast.makeText(getApplicationContext(),"Empty field",Toast.LENGTH_SHORT).show();
                }
                else{
                    price=Double.parseDouble(t1.getText().toString());
                    percent=Integer.parseInt(t2.getText().toString());
                    double discount=((percent*price)/100);
                    double amount=price-discount;
                    show.setText(""+String.format("%.2f",amount));     //set final amount in 2 decimal precision
                }
            }
        });

        //clear button activity
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t1.setText("");
                t2.setText("");
                show.setText("");
            }
        });
    }


    //impliments Context Menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.about:
                //push a notification
                NotificationCompat.Builder mBuilder=(NotificationCompat.Builder) new NotificationCompat.Builder(this).setSmallIcon(R.drawable.app_icon).setContentTitle("Developed by").setContentText("Md. Humayunn Kobir(Dept. of CSE)");
                NotificationManager notificationManager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(0,mBuilder.build());



                //loading about activity
                Intent intent=new Intent(getApplicationContext(),About_Activity.class);
                startActivity(intent);
                return true;
            case R.id.exit:
                //exit confirmation Dialog create

                AlertDialog.Builder alertDialogBuilder=new AlertDialog.Builder(this);
                alertDialogBuilder.setTitle("Exit Application?");
                alertDialogBuilder.setMessage("Click Yes to Exit!").setCancelable(false).setPositiveButton("Yes",new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        moveTaskToBack(true);
                        android.os.Process.killProcess(android.os.Process.myPid());
                        System.exit(1);
                    }
                }).setNegativeButton("No",new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();


                    }
                });
                AlertDialog alertDialog=alertDialogBuilder.create();
                alertDialog.show();
                return true;


                default:
                    return super.onOptionsItemSelected(item);

        }


    }

    //when pressed on back button


    @Override
    public void onBackPressed() {
        //exit confirmation Dialog create

        AlertDialog.Builder alertDialogBuilder=new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Exit Application?");
        alertDialogBuilder.setMessage("Click Yes to Exit!").setCancelable(false).setPositiveButton("Yes",new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int id) {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        }).setNegativeButton("No",new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();


            }
        });
        AlertDialog alertDialog=alertDialogBuilder.create();
        alertDialog.show();
    }
}
