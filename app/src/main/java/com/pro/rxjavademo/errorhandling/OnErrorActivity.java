package com.pro.rxjavademo.errorhandling;

import android.os.Bundle;
import android.view.View;

import com.pro.rxjavademo.BaseActivity;
import com.pro.rxjavademo.R;

import io.reactivex.Observable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author ShenZhenWei
 * @date 2019-06-04
 */
public class OnErrorActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLButton.setText(getString(R.string.on_error_return));
        mLButton.setOnClickListener(v -> onErrorReturnObserver()
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        log("onErrorReturn-onNext:" + s);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        log("onErrorReturn-onError:" + throwable.getMessage());
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        log("onErrorReturn-onCompleted");
                    }
                }));

        mRButton.setText(getString(R.string.on_error_resume));
        mRButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private Observable<String> onErrorReturnObserver() {
        return createObserver().onErrorReturn(throwable -> getString(R.string.on_error_return));
    }

    private Observable<String> createObserver() {
        return Observable.create(emitter -> {
            for (int i = 1; i <= 6; i++) {
                if (i == 3) {
                    emitter.onError(new Throwable("Throw error"));
                } else {
                    emitter.onNext("onNext:" + i);
                }
            }

        });
    }

}
