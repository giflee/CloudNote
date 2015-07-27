/**
 * 页面初始化后，绑定函数。
 */
$(function(){
	//注册
	$("#regist_button").click(function(){
		register();
	});
	
	//登录
	$("#login").click(function(){
		login();
	});
	
	//登出
	$("#logout").click(function(){
		logout();
	});
	
	//修改密码
	$("#changePassword").click(function(){
		changepwd();
	})
	
});

//注册
function register() {
//	alert("注册成功.");
//	$("#zc").attr("class","sig sig_out");
//	$("#dl").attr("class","log log_in");
	var user_name = $("#regist_username").val();
	var nick_name = $("#nickname").val();
	var password = $("#regist_password").val();
	var password2 = $("#final_password").val();
//客户端校验
	var reg = /^\w{3,20}$/;
	if(reg.test(user_name)){
		//去掉错误提示
		$("#warning_1").hide();
	}else{
		//给予错误提示
		$("#warning_1").text("请输入3-20字母数字或下划线").show();
		return;
	}
	
	if(password.length<6){
		$("#warning_2").text("密码长度不能小于6位").show();
		return;
	}else{
		$("#warning_2").hide();
	}
	
	if(password != password2){
		$("#warning_3").text("密码长度不能小于6位").show();
	}else{
		$("#warning_3").hide();
	}
	
	$.post(
			"/CloudNote/login/register.do",
			{"cn_user_name":user_name,
				"cn_user_password":password,
				"cn_user_desc":nick_name},
				function(result){
					if(result.success){
						var data = result.data;
						if(data){
							alert("注册成功");
							$("#back").trigger("click");
						}else{
							alert("用户名已存在")
						}
					}else{
						alert(result.message)
					}
				}
	);
}

//登陆
function login() {
	var name = $("#count").val();
	var pwd = $("#password").val();
	var reg = /^\w{3,20}$/;
	if(!reg.test(name)){
		alert("用户名格式错");
		return;
	}
	if(pwd.length < 6){
		alert("密码太短");
		return;
	}
	
	$.post(
	"/CloudNote/login/check.do",
	{"userName":name,
		"password":pwd
	},
	function(result){
		if(result.success){
			var data = result.data;
			if(data == "1"){
				alert("账号错误");
			}else if(data=="2"){
				alert("密码错误");
			}else{
				
				location.href = "edit.html";
				addCookie("user_id",data.cn_user_id,5)
				addCookie("user_name",data.cn_user_name,5);
			}
		}
	}
	
	);
	
}

/**
 * 退出登录
 */
function logout(){
	$.post(
	"/CloudNote/login/logout.do",
	{},
	function(result){
		if(result.success){
			location.href="login.html";
		}else{
			return;
		}
	}
	);
	
}

/**
 * 修改密码
 */
function changepwd(){
	var pwd1 = $("#last_password").val();
	var pwd2 = $("#new_password").val();
	var pwd3 = $("#final_password").val();
	$.post(
	"/CloudNote/login/update.do",
	{"newpassword":pwd2,
		"oldpassword":pwd1},
	function(result){
		if(result.success){
			alert("修改成功.");
			logout();
		}else{
			alert("原密码错误");
		}
	}
	);
}


