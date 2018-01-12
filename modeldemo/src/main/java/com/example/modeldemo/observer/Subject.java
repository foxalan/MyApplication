package com.example.modeldemo.observer;

import com.example.modeldemo.observer.inter.Observer;

/**
 * Function Name : TODO
 * Author : Alan
 * Modify Date : 2/8/17$
 * Input Parameter 主题的接口,主要实现三个方法,注册 ,删除,更新
 */

public interface Subject {


    void registerObservers(Observer observer);

    void removeObservers(Observer observer);

    void notifyObservers();
}
