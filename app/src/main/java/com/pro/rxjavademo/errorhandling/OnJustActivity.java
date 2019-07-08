package com.pro.rxjavademo.errorhandling;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.pro.rxjavademo.BaseActivity;
import com.pro.rxjavademo.R;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author ShenZhenWei
 * @date 2019-06-04
 */
public class OnJustActivity extends BaseActivity {
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLButton.setText(getString(R.string.on_error_return));
        mLButton.setOnClickListener(v -> createObserver()
                .map(integer -> {
                    String s = "map2";
                    String[] s1;
                    String s2 = null;
                    try {
                        s1 = s.split("2");
                        s2 = s1[2];
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return s2;
                })
                .map(integer -> {
                    String s = "map3";
                    String[] s1 = s.split("3");
                    return s1[3];
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        log("=================onSubscribe");
                    }

                    @Override
                    public void onNext(String integer) {
                        log("=================onNext " + integer);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        log("=================onError " + throwable.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        log("=================onComplete ");
                    }

                }));

        mRButton.setText(getString(R.string.on_error_resume));
        mRButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private Observable<Integer> createObserver() {
        return Observable.just(1);
    }

}
