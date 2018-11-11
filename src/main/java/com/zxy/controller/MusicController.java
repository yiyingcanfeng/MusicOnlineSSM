package com.zxy.controller;

import com.google.gson.Gson;
import com.zxy.model.Music;
import com.zxy.model.User;
import com.zxy.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/music")
public class MusicController {

    @Autowired
    private MusicService musicService;
    @Resource
    private Gson gson;
    @Resource
    private Music music;
    
    @RequestMapping("test")
    public String music(){
        return "test";
    }
    
    @RequestMapping("login")
    public String login(){
        return "login";
    }
    
    @RequestMapping("register")
    public String register(){
        return "register";
    }
    
    @RequestMapping("index")
    public String index(){
        return "index";
    }
    
    @RequestMapping("logout")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:index";
    }
    
    @RequestMapping("getAllMusic")
    @ResponseBody
    public List getAllMusic(){
        return musicService.getAllMusic();
    }
    
    @RequestMapping("deleteMusic")
    @ResponseBody
    public Object deleteMusic(@RequestBody Music music,HttpServletRequest request) throws IOException {
        HashMap<String, Object> map = new HashMap<>();
        boolean b = musicService.deleteMusic(music);
        if (b) {
            //删除磁盘中对应文件
            String path = request.getServletContext().getRealPath("music")+"/"+music.getName()+".mp3";
            File file=new File(path);
            boolean delete = file.delete();
            if (delete){
                String msg="删除成功";
                map.put("result", true);
                map.put("msg", msg);
            }else{
                String msg="文件不存在或已被删除";
                map.put("result", true);
                map.put("msg", msg);
            }
        }else{
            String  msg="文件不存在或已被删除";
            map.put("result", true);
            map.put("msg", msg);
        }
        return map;
    }
    
    @RequestMapping("download")
    @ResponseBody
    public Object download(HttpServletResponse response, HttpServletRequest request) throws IOException {
        HashMap<String, Object> map = new HashMap<>();
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String msg="";
        //得到要下载的文件名
        String fileName = request.getParameter("filename");
        System.out.println("请求参数中的filename："+fileName);
        //获取相对路径music的真实路径
        String webRealPath=request.getServletContext().getRealPath("music");
        //文件的完整路径
        String fullPath = webRealPath+"/"+fileName;
        System.out.println("文件完整路径："+fullPath+"\n文件名编码后：\n"+URLEncoder.encode(fileName, "UTF-8"));
        File file = new File(fullPath);
        if(!file.exists()){
            msg="您要下载的资源不存在或已被删除！";
            map.put("result", false);
            map.put("msg", msg);
            return map;
        }
        //设置响应头，控制浏览器下载该文件，URL必须使用URLEncoder编码，否则浏览器地址栏中的URL会乱码     空格被URLEncoder编码后会变成+，替换，空格对应的URL编码表：%20
        String fileFullPath="";
        if (fileName.contains("+")) {
            fileFullPath=URLEncoder.encode(fileName, "UTF-8");
        }
        response.setHeader("content-disposition",
                "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8").replace("+"," ").replace("%2B","+"));
        //读取要下载的文件，保存到文件输入流
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(fullPath));
        OutputStream out = response.getOutputStream();
        byte buffer[] = new byte[2048];
        int len = 0;
        while((len=in.read(buffer))>0){
            out.write(buffer, 0, len);
        }
        in.close();
        out.close();
        return map;
    }
    
    @RequestMapping(value = "uploadMusic",method = RequestMethod.POST)
    @ResponseBody
    public Object uploadMusic(@RequestBody @RequestParam("file1") CommonsMultipartFile[] commonsMultipartFile,HttpServletRequest request,HttpServletResponse response) {
        HashMap<String, Object> map = new HashMap<>();
        response.setContentType("text/html;charset=utf-8");
        String msg;
        try {
            for (CommonsMultipartFile items : commonsMultipartFile) {
                if (items.getSize()>0){
                    String filename = items.getOriginalFilename();
                    if (filename.endsWith(".mp3")){
                        music.setName(filename.substring(0,filename.length()-4));
                        if (!musicService.isExist(music)){
                            String fullpath=request.getServletContext().getRealPath("music")+"/"+filename;
                            music.setLocation("music/"+filename);
                            File file = new File(fullpath);
                            items.transferTo(file);
                            musicService.insert(music);
                            msg = "上传成功！";
                            map.put("result", true);
                            map.put("msg", msg);
                        }else{
                            msg = "你上传的文件已存在！";
                            map.put("result", false);
                            map.put("msg", msg);
                        }
                    }else{
                        msg = "你上传的文件不合法！";
                        map.put("result", false);
                        map.put("msg", msg);
                    }
                }else {
                    msg = "请选择文件！";
                    map.put("result", false);
                    map.put("msg", msg);
                }
        
            }
    
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }
    
    @RequestMapping("updateMusic")
    @ResponseBody
    public Object updateMusic(HttpServletRequest request) {
        HashMap<String, Object> map = new HashMap<>();
        musicService.updateMusic(request.getServletContext().getRealPath("music"));
        map.put("result", false);
        map.put("msg", "ok");
        return map;
    }
    
}
