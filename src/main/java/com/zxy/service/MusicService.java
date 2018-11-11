package com.zxy.service;

import com.zxy.model.Music;

import java.util.List;

public interface MusicService {
    
    void insert(Music music);//增加
    List<Music> getAllMusic();//获取音乐列表
    boolean deleteMusic(Music music);//删除音乐
    boolean isExist(Music music);//查询音乐是否存在
    void updateMusic(String path);
}
