package com.lglf77.swipemotion;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.github.pwittchen.swipe.library.rx2.Swipe;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SwipeRxActivity extends AppCompatActivity {

    protected TextView info;
    private Swipe swipe;
    private Disposable disposable;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        info = (TextView) findViewById(R.id.info);
        swipe = new Swipe();
        disposable = swipe.observe()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(swipeEvent -> info.setText(swipeEvent.toString()));
    }

    @Override public boolean dispatchTouchEvent(MotionEvent event) {
        return swipe.dispatchTouchEvent(event) || super.dispatchTouchEvent(event);
    }

    @Override protected void onPause() {
        super.onPause();
        safelyUnsubscribe(disposable);
    }

    private void safelyUnsubscribe(Disposable disposable) {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.listener:
                onBackPressed();
                break;
            case R.id.rx:
                break;
        }
        return true;
    }
}