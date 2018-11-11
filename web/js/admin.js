var contextPath = "";
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

window.onload=function () {
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
                for (var i = 0; i < text.length; i++) {
                    // var oli=document.createElement("li");
                    // oli.innerHTML="<a href='#'>删除</a><span>"+text[i].name+"</span>";
                    // oli.className="oli";
                    // mul.appendChild(oli);
                    //建议第二种写法
                    html+="<li id='musicLi' class='list-group-item'><a href='#' class='btn btn-info btn-sm'>删除</a><span>"+text[i].name+"</span>" +
                        "<input id='checkbox' type='checkbox'>" +
                        "</li>";
                }
                mul.innerHTML=html;
                document.getElementById("musicLi").onclick=function () {
                    if (document.getElementById("musicLi").style) {

                    }
                };
                deleteMusic();
            }
        }
    };
    //2.建立一个连接
    req.open("POST",contextPath+"/music/getAllMusic");
    //设置请求头
    req.setRequestHeader("Content-type","application/json");
    //3.发送请求
    req.send();
};
//遮罩层锁屏函数
function lockscreen(oscreen) {
    document.documentElement.style.overflow="hidden";
    oscreen.style.width=Base.getClientSize().w+"px";
    oscreen.style.top=Base.getClientSize().h+"px";
    oscreen.style.left=Base.getScroll().l+"px";
    oscreen.style.top=Base.getScroll().t+"px";
    oscreen.style.display="block";
}
//上传
function upload() {
    var screen=document.getElementById("screen");
    lockscreen(screen);
    var form = document.getElementById("form1");
    var fd = new FormData(form);
    var req=getXMLHttpRequest();
    req.onreadystatechange=function () {
        // alert(req.readyState);
        if(req.readyState===4){
            // alert(req.status);//查看服务器端响应状态
            if(req.status===200){
                var text = eval("("+req.responseText+")");
                if(text.result==="true"){
                    screen.innerText="上传成功";
                    setTimeout(function () {
                        window.location.href=contextPath+"/user/admin";
                        document.documentElement.style.overflow="visible";
                    },1000);
                }else{
                    screen.innerText=text.msg;
                    setTimeout(function () {
                        screen.style.display="none";
                        document.documentElement.style.overflow="visible";
                    },1000);
                }
            }
        }
    };
    req.open("POST",contextPath+"/music/uploadMusic");
    // req.setRequestHeader("Content-type","application/json");
    req.send(fd);
}
//删除歌曲
function deleteMusic() {
    var omusic=Base.getId("musicList");
    var musicname=Base.getTagName(omusic,"span");
    var del=Base.getTagName(omusic,"a");
    // alert(musicname[0].innerText);
    for (var i = 0; i < del.length; i++) {
        del[i].index=i;
        del[i].onclick =function () {
            //1.获取XMLHttpRequest对象
            var req1=getXMLHttpRequest();
            //4.处理响应结果
            req1.onreadystatechange=function () {
                // alert(req.readyState);
                if(req1.readyState===4){
                    // alert(req.status);//查看服务器端响应状态
                    if(req1.status===200){
                        var text = eval("("+req1.responseText+")");
                        if(text.result==="true"){
                            alert(text.msg);
                            window.location.href=contextPath+"/user/admin";
                        }else{
                            alert(text.msg);
                            window.location.href=contextPath+"/user/admin";
                        }
                    }
                }
            };
            //2.建立一个连接
            req1.open("POST",contextPath+"/music/deleteMusic");
            //设置请求头
            req1.setRequestHeader("Content-type","application/json");
            //3.发送请求
            req1.send(JSON.stringify({"name":musicname[this.index].innerText}));

        };
    }
}
//更新数据库
function update() {
    var req1=getXMLHttpRequest();
    req1.onreadystatechange=function () {
        if(req1.readyState===4){
            // alert(req.status);//查看服务器端响应状态
            if(req1.status===200){
                var text = eval("("+req1.responseText+")");
                if(text.result==="true"){
                    window.location.href=contextPath+"/user/admin";
                }else{
                    window.location.href=contextPath+"/user/admin";
                }
            }
        }
    };
    //2.建立一个连接
    req1.open("POST",contextPath+"/music/updateMusic");
    //设置请求头
    req1.setRequestHeader("Content-type","application/json");
    //3.发送请求
    req1.send();
}
