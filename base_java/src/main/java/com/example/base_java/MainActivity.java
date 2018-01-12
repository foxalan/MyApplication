package com.example.base_java;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.base_java.objectoriented.Student;
import com.example.base_java.util.L;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            t_15_4();
            t_15_5();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 类的属性存放在堆内存中,对象则在栈中,向指针一样指向堆
     */

    private void t_5_1() {

        Student tom = new Student();
        tom.setId(1);
        tom.setAge(15);
        tom.setName("tom");

        Student jerry = tom;
        jerry.setName("jerry");

        L.d(tom.toString());

    }

    private void t_5_2() {
        Student.test("tom", "jerry");
    }

    /**
     * 包装类可以实现基本类型和字符串之间的转换
     * byte,char,short,int,long,double,boolean,float
     */
    private void t_5_3() {
        String str1 = "123";
        int i = Integer.parseInt(str1);

        String str2 = "123.245";
        double d = Double.parseDouble(str2);

        String string = String.valueOf(25);
    }

    /**
     * HashSet
     *
     * TreeSet
     */

    /**
     * File的操作
     */

    private void t_15_1() {

        File file = new File(".");
        Log.d("TANG", file.getName());
        Log.d("TANG", file.getAbsolutePath());




        try {
            File file1 = File.createTempFile("test", ".txt", file);
            Log.d("TANG", file1.exists() + "");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * IO流
     */

    public void t_15_4() throws IOException {

        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("tang.txt");
            fileWriter.write("我们斩提是蚯蚓是蚯蚓昌困,无据明治膛脑满肠肥脑满肠肥逍遥脸");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileWriter != null) {

                fileWriter.close();
            }
        }
    }

    private void t_15_5() throws IOException {
        FileReader fileReader = null;

        fileReader = new FileReader("tang.txt");
        char[] chars = new char[128];
        int len = 0;
        while ((len =fileReader.read(chars))!=-1){
            L.d(new String(chars,0,len));
        }

        if (fileReader != null){
            fileReader.close();
        }
    }
}
