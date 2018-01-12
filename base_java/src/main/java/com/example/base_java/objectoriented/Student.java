package com.example.base_java.objectoriented;

import com.example.base_java.util.L;

/**
 * Function Name : TODO
 * Author : Alan
 * Modify Date : 2/8/17$
 * Input Parameter &
 */

public class Student {

    private int id;
    private String name;
    private int age;

    public Student(){

    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public Student(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public  static void test(String... name){
        for (String str :name){
            L.d(str);
        }
    }



}
