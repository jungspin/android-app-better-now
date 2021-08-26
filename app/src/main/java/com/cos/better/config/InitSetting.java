package com.cos.better.config;

public interface InitSetting {
    void init();
    void initLr();
    default void initAdapter(){};
    default void initNavigation(){};
    default void initSetting(){};
    void initData();

}
