package sszg.com.lesson1;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/**
 * Created by Azeem on 12/26/2017.
 */

public class Test {
    public static void main(String[] args) {
        MainActivity.Companion.sendCountdown(50, 50, new Function0<Unit>() {
            @Override
            public Unit invoke() {
                // put code here
                return null;
            }
        });
    }
}
