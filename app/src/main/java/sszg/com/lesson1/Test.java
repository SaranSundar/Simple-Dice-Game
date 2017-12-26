package sszg.com.lesson1;

import android.os.CountDownTimer;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/**
 * Created by Azeem on 12/26/2017.
 */

public class Test {
    public static void main(String[] args) {
        // can alternately call MainActivity.Companion.sendCountdown() for static call. We need a
        // companion object {} for static in Kotlin, it's sort of annoying.
        sendCountdown(50, 50, new Function0<Unit>() {
            @Override
            public Unit invoke() {
                // put code here
                return null;
            }
        });
    }

    /**
     * Here's an implementation of the sendCountdown inline function in Java.
     */
    private static void sendCountdown(long millisInFuture, long countDownInterval, final Function0<Unit> function0) {
        new CountDownTimer(millisInFuture, countDownInterval) {
            @Override
            public void onTick(long l) {}

            @Override
            public void onFinish() {
                function0.invoke();
            }
        }.start();
    }
}
