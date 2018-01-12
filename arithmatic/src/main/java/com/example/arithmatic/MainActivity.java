package com.example.arithmatic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    /**
     * java 常见算法
     *
     * @param savedInstanceState
     */

    private static final String TAG = "tang";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //    int num = getAll(6);
        //    Log.d(TAG, num + "-----------------------------");

        // a_2();

        // int total_count = a_3(5);
        // Log.d(TAG, total_count + "");

        // a_4();

        // a_5();

        a_6(1200);

    }

    /**
     * 编写一个程序，输入n,求n！（用递归的方式实现）。
     */

    private int getAll(int n) {

        if (n == 1) {
            return 1;
        } else {
            return getAll(n - 1) * n;

        }

    }

    /**
     * 编写一个程序，有1，2,3,4个数字，能组成多少个互不相同且无重复数字的三位数？都是多少？
     */

    private void a_2() {
        int count = 0;

        for (int i = 1; i <= 4; i++) {

            for (int j = 1; j <= 4; j++) {
                if (j == i) {
                    continue;
                } else {

                    for (int m = 1; m <= 4; m++) {
                        if (m == j || m == i) {

                            continue;
                        } else {
                            Log.d(TAG, i + "" + j + "" + m);
                            count++;

                        }
                    }

                }

            }

        }
        Log.d(TAG, count + "");
    }

    /**
     * 古典问题：有一对兔子，从出生后第3个月起每个月都生一对兔子，
     * 小兔子长到第四个月后每个月又生一对兔子，假如兔子都不
     * 死，问每个月的兔子总数为多少?????????????????????
     */

    private int a_3(int month) {
        if (month == 1 || month == 2) {
            return 1;
        } else {
            return a_3(month - 1) + a_3(month - 2);
        }
    }

    /**
     * 题目：判断101-200之间有多少个素数，并输出所有素数。
     */

    private void a_4() {

        for (int i = 101; i <= 200; i++) {
            for (int j = 2; j < i; j++) {

                if (j == i - 1) {
                    Log.d(TAG, i + "--");
                }

                if (i % j == 0) {
                    break;
                } else {
                    continue;
                }

            }
        }

    }


    /**
     * 打印出所有的 "水仙花数 "，所谓 "水仙花数 "是指一个三位数，
     * 其各位数字立方和等于该数本身。例如：153是一个 "水仙花
     * 数 "，因为153=1的三次方＋5的三次方＋3的三次方。
     */

    private void a_5() {

        for (int i = 100; i <= 999; i++) {
            int hen = i / 100;
            int ten = (i - hen * 100) / 10;
            int gen = i - hen * 100 - ten * 10;

            int total = hen * hen * hen + ten * ten * ten + gen * gen * gen;

            if (total == i) {
                Log.d(TAG, i + ":a_5");
            }
        }
    }


    /**
     * 题目：将一个正整数分解质因数。例如：输入90,打印出90=2*3*3*5。
     */
    private void a_6(int num) {

        StringBuffer stringBuffer = new StringBuffer();

        while (a_6_1(num) != num) {
            int a = a_6_1(num);
            num = num / a;
            stringBuffer.append(a + "*");

        }

        stringBuffer.append(num);

        Log.d(TAG, stringBuffer.toString());
    }

    private int a_6_1(int n) {
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return i;

            }
        }

        return n;
    }


}
