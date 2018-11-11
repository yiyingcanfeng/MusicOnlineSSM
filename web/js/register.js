var contextPath = "";
window.onload=function () {
    var check=document.getElementById("checkbox");
    var submit=document.getElementById("submit");

    submit.className="submit-disabled";
    submit.disabled=true;
    check.onclick=function () {
        if(check.checked){
            submit.className="submit";
            submit.disabled=false;

        }else{
            submit.className="submit-disabled";
            submit.disabled=true;
        }
    };
    var registerReq=getXMLHttpRequest();
    var register=document.getElementById("submit");
    register.onclick=function () {
        registerReq.onreadystatechange=function () {
            // alert(req.readyState);
            if(registerReq.readyState===4){
                // alert(req.status);//查看服务器端响应状态
                if(registerReq.status===200){
                    // alert(req.responseText);
                    var text = eval("("+registerReq.responseText+")");
                    var msg = document.getElementsByClassName("msg");

                    if(text.hasOwnProperty("name")){
                        msg[0].style.visibility = "visible";
                        msg[0].style.background="url("+contextPath+"/img/error.png) no-repeat 4px center";
                        msg[0].innerHTML=text.name;
                    } else {
                        msg[0].style.visibility = "hidden";
                    }
                    if(text.hasOwnProperty("password")){
                        msg[1].style.visibility = "visible";
                        msg[1].style.background="url("+contextPath+"/img/error.png) no-repeat 4px center";
                        msg[1].innerHTML=text.password;
                    } else {
                        msg[1].style.visibility = "hidden";
                    }
                    if(text.hasOwnProperty("password2")){
                        msg[2].style.visibility = "visible";
                        msg[2].style.background="url("+contextPath+"/img/error.png) no-repeat 4px center";
                        msg[2].innerHTML=text.password2;
                    } else {
                        msg[2].style.visibility = "hidden";
                    }
                    if(text.hasOwnProperty("email")){
                        msg[3].style.visibility = "visible";
                        msg[3].style.background="url("+contextPath+"/img/error.png) no-repeat 4px center";
                        msg[3].innerHTML=text.email;
                    } else {
                        msg[3].style.visibility = "hidden";
                    }
                    if (text.hasOwnProperty("result")) {
                        // setTimeout(function () {
                            window.location.href=contextPath+"/music/login"
                        // },2000);
                    }
                }
            }
        };
        //2.建立一个连接
        registerReq.open("POST",contextPath+"/user/register");
        //设置请求头
        registerReq.setRequestHeader("Content-type","application/json");
        //3.发送请求
        registerReq.send(JSON.stringify(GetJsonData()));
    };

    var name=document.getElementById("username");
    name.onblur=function () {
        restoreStyle("username-div");
        userIsExist();
    }

};
function GetJsonData() {
    return {
        "name": document.getElementById("username").value,
        "password": document.getElementById("password").value,
        "password2": document.getElementById("re").value,
        "email": document.getElementById("email").value
    };
}

//验证用户名是否已被使用
function userIsExist() {
    var req = getXMLHttpRequest();
    req.onreadystatechange = function () {
        // alert(req.readyState);
        if (req.readyState === 4) {
            // alert(req.status);//查看服务器端响应状态
            if (req.status === 200) {
                // alert(req.responseText);
                var text = eval("(" + req.responseText + ")");
                var msg = document.getElementsByClassName("msg")[0];
                if(document.getElementById("username").value===""){
                    msg.style.visibility = "visible";
                    msg.style.background="url("+contextPath+"/img/error.png) no-repeat 4px center";
                    msg.innerHTML = "请输入用户名！";
                }else if (text.result === true) {
                    msg.style.visibility = "visible";
                    msg.style.background="url("+contextPath+"/img/error.png) no-repeat 4px center";
                    msg.innerHTML = "用户名已存在！";
                }else{
                    msg.style.visibility = "visible";
                    msg.style.background="url("+contextPath+"/img/reg_succ.png) no-repeat 4px center";
                    msg.innerHTML = "用户名可用！";
                }
            }
        }
    };
    //2.建立一个连接
    req.open("POST", contextPath+"/user/userIsExist");
    //设置请求头
    req.setRequestHeader("Content-type", "application/json");
    //3.发送请求
    req.send(JSON.stringify({"name":document.getElementById("username").value}));
}
//获得焦点时改变样式
function changeStyle(div) {
    var odiv=document.getElementById(div);
    odiv.className="div-change";
}
//失去焦点时改变样式
function restoreStyle(div) {
    var odiv=document.getElementById(div);
    odiv.className="div-default";
}