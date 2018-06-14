//请求前缀
var baseURL = "/basic-framework/";

//登录token
var token = localStorage.getItem("token");
if(token == 'null'){
    parent.location.href = baseURL + 'login.html';
}
$(function(){
    $.ajax({
        type: "POST",
        url: baseURL + "sys/menu/perms",
        dataType: "json",
        data: {token: token},
        success: function (r) {
            if (r.code == 1){
                window.permissions = r.data;
            }
        }
    });
});


//权限判断
function hasPermission(permission) {
    if (window.parent.permissions.indexOf(permission) > -1) {
        return true;
    } else {
        return false;
    }
}


function logout() {
    $.ajax({
        type: "POST",
        url: baseURL + "sys/logout",
        dataType: "json",
        success: function (r) {
            //删除本地token
            localStorage.removeItem("token");
            //跳转到登录页面
            location.href = baseURL + 'login.html';
        }
    });
}