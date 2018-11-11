package com.zxy.service;

import com.zxy.dao.MusicMapper;
import com.zxy.model.Music;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class MusicServiceImpl implements MusicService {
    
    @Resource
    private MusicMapper musicMapper;
    
    @Override
    public List<Music> getAllMusic() {
        return  musicMapper.getAllMusic();
    }
    
    @Override
    public void insert(Music music) {
        musicMapper.insert(music);
    }
    
    
    @Override
    public boolean deleteMusic(com.zxy.model.Music music) {
        boolean flag;
        Integer i = musicMapper.deleteMusic(music);
        flag = i > 0;
        return flag;
    }
    
    @Override
    public boolean isExist(com.zxy.model.Music music) {
        return musicMapper.isExist(music)==1;
    }
    
    @Override
    public void updateMusic(String path) {
        List<Music> oldList=getAllMusic();//旧的音乐列表
        List<String> pathList=new ArrayList<>();
    
        File fList[] = new File(path).listFiles();
        for (File file : fList) {
            pathList.add(file.getPath());
        }
    
        for (String s : pathList) {
            Pattern p=Pattern.compile("(?<=music.).+(?=.mp3)");//验证文件名是否合法
            Matcher m=p.matcher(s);
            String name;
            if (m.find()){
                name=m.group();
                Music music=new Music(name,"music/"+name+".mp3");
                if (oldList.contains(music)){
                    continue;//如果旧的音乐列表已有此音乐对象，跳过
                }
                insert(music);
            }
        }
    }
    
    
}
