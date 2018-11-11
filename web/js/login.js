var contextPath = "";
window.onload=function () {
    //1.获取XMLHttpRequest对象
    var req=getXMLHttpRequest();
    var login=document.getElementById("submit");
    //4.处理响应结果
    login.onclick=function () {
        /**
         readyState
         存有 XMLHttpRequest 的状态。从 0 到 4 发生变化。
         0: 请求未初始化
         1: 服务器连接已建立
         2: 请求已接收
         3: 请求处理中
         4: 请求已完成，且响应已就绪
         onreadystatechange()每当 readyState 属性改变时，就会调用该函数
         */
        req.onreadystatechange=function () {
            // alert(req.readyState);
            if(req.readyState===4){
                // alert(req.status);//查看服务器端响应状态
                if(req.status===200){
                    // alert(req.responseText);
                    var text = eval("("+req.responseText+")");
                    var msg=document.getElementById("error").getElementsByTagName("span")[0];
                    if (text.result===true) {
                        //检查用户权限，如果是1跳转至后台管理界面， 2跳转至首页
                        if(text.permission===1){
                            var name=document.getElementById("username").value;
                            var password=document.getElementById("password").value;
                            var form=new FormData();
                            post(contextPath+"/user/admin",{name:name,password:password});
                        }else {
                            window.location.href=contextPath+"/music/index";
                        }
                    }else{
                        document.getElementById("error").style.visibility="visible";
                        msg.innerHTML=text.msg;
                    }
                }
            }
        };
        //2.建立一个连接
        req.open("POST",contextPath+"/user/login");
        //设置请求头
        req.setRequestHeader("Content-type","application/json");
        //3.发送请求
        req.send(JSON.stringify(GetJsonData()));
    };
};

function GetJsonData() {
    var json={
        "name":document.getElementById("username").value,
        "password":document.getElementById("password").value,
        "code":document.getElementById("code").value
    };
    return json;
}

//使用post方式向后台发送数据
function post(URL, PARAMS) {
    var temp_form = document.createElement("form");
    temp_form .action = URL;
    temp_form .target = "_self";
    temp_form .method = "post";
    temp_form .style.display = "none";
    for (var x in PARAMS) {
        var opt = document.createElement("textarea");
        opt.name = x;
        opt.value = PARAMS[x];
        temp_form .appendChild(opt);
    }
    document.body.appendChild(temp_form);
    temp_form .submit();
}