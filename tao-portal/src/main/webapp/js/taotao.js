var t;  //成员变量
var TT = TAOTAO = {

    checkLogin : function(){
        var _ticket = $.cookie("TT_TOKEN");

        if(!_ticket){
            return ;
        }
        t=_ticket;
        $.ajax({
            url : "http://localhost:8084/user/token/" + _ticket,
            dataType : "jsonp",
            type : "GET",
            success : function(data){
                if(data.status == 200){
                    var username = data.data.username;
                    var html = username + "，欢迎来到淘淘！<a onclick='exit()'  class=\"link-logout\">[退出]</a>";
                    $("#loginbar").html(html);

                }
            }
        });


    }
}
//退出按钮
function exit(){
    // 退出功能  ， callback =回调函数
    $.ajax({
        url: 'http://localhost:8084/user/logout/'+t+'?callback=e',
        type: 'get',
        dataType: 'jsonp',
        jsonpCallback: 'e'
    });

}
//回调函数
function e(){
    window.location="/index.html";
}


$(function(){
    // 查看是否已经登录，如果已经登录查询登录信息
    TT.checkLogin();
});
