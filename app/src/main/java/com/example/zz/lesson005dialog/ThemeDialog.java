package com.example.zz.lesson005dialog;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import com.example.zz.lesson005dialog.R;

public class ThemeDialog extends Activity implements View.OnClickListener {

    Button btnSetColorT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme_dialog);
        DisplayMetrics displaymetrics = getResources().getDisplayMetrics();
        int dw = displaymetrics.widthPixels;
        int dh =displaymetrics.heightPixels;
        getWindow().setLayout(dw-dw/6, dh-dh/6);

        btnSetColorT = (Button) findViewById(R.id.btnSetColorT);
        btnSetColorT.setOnClickListener( ThemeDialog.this);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the btnMenu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.theme_dialog, menu);
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
        int color = Color.BLUE;
                RadioGroup rg = (RadioGroup) findViewById(R.id.rgPopupT);
                switch (rg.getCheckedRadioButtonId()) {
                    case R.id.rbBlueT:
                        color = Color.BLUE;
                        break;
                    case R.id.rbGreenT:
                        color = Color.GREEN;
                        break;
                    case R.id.rbRedT:
                        color = Color.RED;
                        break;
                    case R.id.rbYellowT:
                        color = Color.YELLOW;
                        break;

                }
        Intent intent = new Intent();
        intent.putExtra("color", color);
        setResult(RESULT_OK, intent);
        finish();
        overridePendingTransition(R.anim.enter_anim,R.anim.exit_anim);
    }
}
