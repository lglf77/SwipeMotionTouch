package com.lglf77.swipemotion;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.github.pwittchen.swipe.library.rx2.Swipe;
import com.github.pwittchen.swipe.library.rx2.SwipeListener;

public class SwipeActivity extends AppCompatActivity {

    protected TextView info;
    private Swipe swipe;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        info = (TextView) findViewById(R.id.info);
        swipe = new Swipe();

        swipe.setListener(new SwipeListener() {
            @SuppressLint("SetTextI18n")
            @Override public void onSwipingLeft(final MotionEvent event) {
                info.setText("INDO À ESQUERDA");
            }

            @SuppressLint("SetTextI18n")
            @Override public boolean onSwipedLeft(final MotionEvent event) {
                info.setText("CHEGOU À ESQUERDA");
                return false;
            }

            @SuppressLint("SetTextI18n")
            @Override public void onSwipingRight(final MotionEvent event) {
                info.setText("INDO À DIREITA");
            }

            @SuppressLint("SetTextI18n")
            @Override public boolean onSwipedRight(final MotionEvent event) {
                info.setText("CHEGOU À DIREITA");
                return false;
            }

            @SuppressLint("SetTextI18n")
            @Override public void onSwipingUp(final MotionEvent event) {
                info.setText("INDO PARA CIMA");
            }

            @SuppressLint("SetTextI18n")
            @Override public boolean onSwipedUp(final MotionEvent event) {
                info.setText("CHEGOU PARA CIMA");
                return false;
            }

            @SuppressLint("SetTextI18n")
            @Override public void onSwipingDown(final MotionEvent event) {
                info.setText("INDO PARA BAIXO");
            }

            @SuppressLint("SetTextI18n")
            @Override public boolean onSwipedDown(final MotionEvent event) {
                info.setText("CHEGOU PARA BAIXO");
                return false;
            }
        });
    }

    @Override public boolean dispatchTouchEvent(MotionEvent event) {
        return swipe.dispatchTouchEvent(event) || super.dispatchTouchEvent(event);
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.listener:
                break;
            case R.id.rx:
                final Intent intent = new Intent(this, SwipeRxActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }

}