package com.zxy.dao;

import com.zxy.model.Music;

import java.util.List;

public interface MusicMapper {
    int insert(Music music);//增加
    List<Music> getAllMusic();//获取音乐列表
    Integer deleteMusic(com.zxy.model.Music music);//删除音乐
    Integer isExist(com.zxy.model.Music music);//查询音乐是否存在
}
