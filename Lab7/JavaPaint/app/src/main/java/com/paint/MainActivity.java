package com.paint;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    PaintView paintView;
    Context ctx;
    RadioGroup radioGroup;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        paintView = (PaintView) findViewById(R.id.paintView);
        ctx = getApplicationContext();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.paint, menu);
        return true;
    }



    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {
            case R.id.menu_kolor:

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setTitle("Wybor koloru");
                final CharSequence[] items = { "Czerwony", "Zielony", "Niebieski" };

                alertDialogBuilder.setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        switch (item) {
                            case 0:
                                paintView.setColor(Color.RED);
                                break;
                            case 1:
                                paintView.setColor(Color.GREEN);
                                break;
                            case 2:
                                paintView.setColor(Color.BLUE);
                                break;
                        }
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

                break;
            case R.id.menu_typ:

                AlertDialog.Builder alertDialogBuilder2 = new AlertDialog.Builder(this);
                alertDialogBuilder2.setTitle("Wybor typu");
                final CharSequence[] items2 = { "Koło", "Okrag", "Kwadrat" };

                alertDialogBuilder2.setItems(items2, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        switch (item) {
                            case 0:
                                paintView.setType(0);
                                break;
                            case 1:
                                paintView.setType(1);
                                break;
                            case 2:
                                paintView.setType(2);
                                break;
                        }
                    }
                });

                AlertDialog alertDialog2 = alertDialogBuilder2.create();
                alertDialog2.show();


                break;
        }

        return true;
    }
}
