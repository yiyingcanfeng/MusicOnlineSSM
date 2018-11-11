var contextPath = "";
window.onload=function () {
    var Base={
        getId:function (id) {
            return document.getElementById(id);
        },
        getTagName:function (o, tagName) {
            return o.getElementsByTagName(tagName)
        },
        //获取可视区高度和宽度
        getClientHeight:function () {
            return document.documentElement.clientHeight||window.innerHeight;
        },
        getClientWidth:function () {
            return document.documentElement.clientWidth||window.innerWidth;
        },
        //获取可视区大小
        getClientSize:function () {
            return {
                h: document.documentElement.clientHeight||window.innerHeight,
                w:document.documentElement.clientWidth||window.innerWidth
            }
        },

        getScroll:function () {
            return{
                l:document.documentElement.scrollLeft||document.body.scrollLeft,
                t:document.documentElement.scrollTop||document.body.scrollTop
            }
        },
        getStyle:function (o, att) {
            return window.getComputedStyle(o,null)[att];
        }
    };

    //1.获取XMLHttpRequest对象
    var req=getXMLHttpRequest();
    //4.处理响应结果
    req.onreadystatechange=function () {
        // alert(req.readyState);
        if(req.readyState===4){
            // alert(req.status);//查看服务器端响应状态
            if(req.status===200){
                var text = eval("("+req.responseText+")");
                var mul=document.getElementById("musicList").getElementsByTagName("ul")[0];
                var html="";
                // alert(text[text.length-1].name);
                for (var i = 0; i < text.length; i++) {
                    //2种创建子元素的方式，推荐使用第二种
                    // var oli=document.createElement("li");
                    // oli.innerHTML="<a href='#'>删除</a><span>"+text[i].name+"</span>";
                    // oli.className="oli";
                    // mul.appendChild(oli);
                    html+="<li class='list-group-item'>" +
                        "<a class='btn btn-info' href='"+contextPath+"/music/download?filename="+text[i].name.replace(/\+/g,"%2b")+".mp3'"+"><span class='glyphicon glyphicon-download'></span> 下载</a>" +"<a class='btn btn-info ' ><span class='glyphicon glyphicon-play'></span> 播放</a>" +
                        "<span>"+text[i].name+"</span>" +
                        "</li>";
                    // alert("<a href='Download?filename="+text[i].name+"'");
                }
                mul.innerHTML=html;
                var audio=document.getElementById("audio");
                var musicName=document.getElementById("musicName");
                var welcome=document.getElementById("welcome");
                var a=mul.getElementsByTagName("a");
                //‘播放’ 点击事件
                for (var i = 0; i < a.length; i++) {
                    a[i].onclick=function () {
                        // alert(this.innerHTML);
                        // alert(this.nextSibling.innerText);
                        audio.src =contextPath+"/music/"+this.nextSibling.innerText+".mp3";
                        musicName.innerText=this.nextSibling.innerText;
                        audio.load();
                        audio.play();
                    };
                }
                var ouser=document.getElementById("user").getElementsByTagName("span")[0];
                for (var i = 0; i < a.length; i++) {
                    if(i%2===0){
                        a[i].style.display="none";
                    }
                }
                //当用户登陆后显示‘下载’
                if(ouser.innerText!==""){
                    for (var i = 0; i < a.length; i++) {
                        if(i%2===0){
                            a[i].style.display="inline";
                        }
                    }
                }

                $.ajax({
                    type:"POST",
                    url:contextPath+"/user/loginUser",
                    dataType:"json",
                    success:function (data) {
                        if (data!==null){
                            welcome.innerText=data.name;
                            for (var i = 0; i < a.length; i++) {
                                if(i%2===0){
                                    a[i].style.display="inline";
                                }
                            }
                        }
                    }
                });
            }
        }
    };
    //2.建立一个连接
    req.open("POST",contextPath+"/music/getAllMusic");
    //设置请求头
    req.setRequestHeader("Content-type","application/json");
    //3.发送请求
    req.send();
    var audio=document.getElementById("audio");
    var musicName=document.getElementById("musicName");
    var user=document.getElementById("user");
    audio.style.top=Base.getScroll().t+Base.getClientHeight()-audio.offsetHeight+"px";
    musicName.style.top=Base.getScroll().t+Base.getClientHeight()-musicName.offsetHeight-audio.offsetHeight+"px";
    user.style.top=Base.getScroll().t+"px";

    audio.style.marginLeft=Base.getClientWidth()/2-audio.offsetWidth/2+"px";
    musicName.style.marginLeft=Base.getClientWidth()/2-audio.offsetWidth/2+100+"px";
    user.style.marginLeft=Base.getClientWidth()/2-audio.offsetWidth/2+"px";
    //不随页面滚动改变位置
    window.onscroll=function () {
        audio.style.top=Base.getScroll().t+Base.getClientHeight()-audio.offsetHeight+"px";
        musicName.style.top=Base.getScroll().t+Base.getClientHeight()-musicName.offsetHeight-audio.offsetHeight+"px";
        user.style.top=Base.getScroll().t+"px";

        audio.style.marginLeft=Base.getClientWidth()/2-audio.offsetWidth/2+"px";
        musicName.style.marginLeft=Base.getClientWidth()/2-audio.offsetWidth/2+100+"px";
        user.style.marginLeft=Base.getClientWidth()/2-audio.offsetWidth/2+"px";
    };
};