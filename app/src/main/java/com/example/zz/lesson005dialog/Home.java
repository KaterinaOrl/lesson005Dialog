package com.example.zz.lesson005dialog;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.Toast;


public class Home extends Activity implements View.OnClickListener {

    Button btnDialog, btnMyDial, btnPopup, btnThemeDialog, btnCloseMenu;
    AlertDialog alDial, alDial2;
    AlertDialog.Builder adb;
    String data[] = { "Yellow", "Green", "Blue", "Red" };
    int color1, i, color3, color4, menuAlert;
    Point p;
    MyCustomView btnMenu;
    View homeLayout;
    LinearLayout slideMenu;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_menu);
        btnDialog = (Button) findViewById(R.id.btnDialog);
        btnDialog.setOnClickListener(this);
        btnMyDial = (Button) findViewById(R.id.btnMyDial);
        btnMyDial.setOnClickListener(this);
        btnPopup = (Button) findViewById(R.id.btnPopup);
        btnPopup.setOnClickListener(this);
        btnThemeDialog = (Button) findViewById(R.id.btnThemeDialog);
        btnThemeDialog.setOnClickListener(this);
        btnMenu = (MyCustomView) findViewById(R.id.btnMenu);
        menuAlert=0;
        btnMenu.setText(menuAlert);
       // btnMenu.setOnClickListener(Home.this);


        adb = new AlertDialog.Builder(Home.this);
        adb.setSingleChoiceItems(data, 0, dialogListener);
        adb.setPositiveButton("SetColor", dialogListener);

        slideMenu = (LinearLayout) findViewById(R.id.slideMenu);
        homeLayout = findViewById(R.id.homeLayout);
        btnMenu.showMenu(homeLayout);

        btnCloseMenu = (Button) slideMenu.findViewById(R.id.btnCloseMenu);
        btnCloseMenu.setOnClickListener(Home.this);





    }


    DialogInterface.OnClickListener dialogListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int which) {
            color1 = Color.YELLOW;



            if (which == Dialog.BUTTON_POSITIVE) {
                switch (i){
                    case 0: color1 = Color.YELLOW; break;
                    case 1: color1 = Color.GREEN; break;
                    case 2: color1 = Color.BLUE; break;
                    case 3: color1 = Color.RED; break;

                }
                if (dialogInterface == alDial)
                    btnDialog.setBackgroundColor(color1);
                else
                    btnMyDial.setBackgroundColor(color1);
                menuAlert++;
                btnMenu.setText(menuAlert);
            }
            else i=which;
        }
    };


      @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the btnMenu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnCloseMenu:
                Toast.makeText(this, "slideMenu", Toast.LENGTH_SHORT).show();
                ObjectAnimator animClose = ObjectAnimator.ofFloat(homeLayout, "x" , 200, 0);
                animClose.setDuration(2000);
                animClose.start();
                break;

            /*case R.id.btnMenu:
                Toast.makeText(this, "btnMenu", Toast.LENGTH_SHORT).show();
                ObjectAnimator animShow = ObjectAnimator.ofFloat(homeLayout, "x" , 0, 200);
                animShow.setDuration(2000);
                animShow.start();
            break;*/

            case R.id.btnDialog:
                alDial = adb.create();
                alDial.setTitle("Выберите цвет :");
                alDial.show();

            break;

            case R.id.btnMyDial:
                alDial2 = adb.create();
                alDial2.setTitle("Выберите цвет :");
                alDial2.show();

                break;

            case R.id.btnPopup:
                createPopup();

            break;

            case R.id.btnThemeDialog:
                Intent intent = new Intent(this, ThemeDialog.class);
                startActivityForResult(intent, 1);
                overridePendingTransition(R.anim.enter_anim,R.anim.exit_anim);

            break;




        }

    }

    private void createPopup() {

        if (p!=null) {
            int popupW = 300;
            int popupH = 350;

            LinearLayout viewGroup = (LinearLayout) findViewById(R.id.popup);
            //viewGroup.setBackgroundColor(Color.GRAY);
            LayoutInflater layoutInflater = getLayoutInflater();
            final View layoutPopup = layoutInflater.inflate(R.layout.popup_layout, viewGroup);

            final PopupWindow popup = new PopupWindow(this);
            popup.setContentView(layoutPopup);
            popup.setWidth(popupW);
            popup.setHeight(popupH);
            popup.setFocusable(true);
            // Some offset to align the popup a bit to the right, and a bit down, relative to button's position.
            int OFFSET_X = 30;
            int OFFSET_Y = 30;

            popup.showAtLocation(layoutPopup, Gravity.NO_GRAVITY, p.x + OFFSET_X, p.y + OFFSET_Y);


            Button btnSetColor = (Button) layoutPopup.findViewById(R.id.btnSetColor);
            btnSetColor.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    color3 = Color.BLUE;
                    RadioGroup rg = (RadioGroup) layoutPopup.findViewById(R.id.rgPopup);
                    switch (rg.getCheckedRadioButtonId()) {
                        case R.id.rbBlue:
                            color3 = Color.BLUE;
                            break;
                        case R.id.rbGreen:
                            color3 =Color.GREEN;
                            break;
                        case R.id.rbRed:
                            color3 = Color.RED;
                            break;
                        case R.id.rbYellow:
                            color3 = Color.YELLOW;
                            break;

                    }
                    btnPopup.setBackgroundColor(color3);
                    menuAlert++;
                    btnMenu.setText(menuAlert);
                    popup.dismiss();


                }
            });
        }

    }

    public void onWindowFocusChanged(boolean hasFocus) {

        int[] location = new int[2];
        Button button = (Button) findViewById(R.id.btnPopup);

        // Get the x, y location and store it in the location[] array
        // location[0] = x, location[1] = y.
        button.getLocationOnScreen(location);

        //Initialize the Point with x, and y positions
        p = new Point();
        p.x = location[0];
        p.y = location[1];
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data){
        if (data == null) {return;}
        color4 = Color.MAGENTA;
        color4 = data.getIntExtra("color", Color.CYAN);
        btnThemeDialog.setBackgroundColor(color4);
        menuAlert++;
        btnMenu.setText(menuAlert);
    }

}
