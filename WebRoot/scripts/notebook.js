/***
 * 加载普通笔记本
 */
function loadNormalNoteBook(){
	
	$.post(
			"/CloudNote/notebook/load_normal.do",
			{},
			function(result){
				
				if(result.success){
					var list = result.data;
					
					$(list).each(function(){
						//创建笔记本li插入到列表下
						
						var $li = '<li class="online"><a class="unchecked"><i class="fa fa-book" title="笔记本" rel="tooltip-bottom"></i> '+this.cn_notebook_name+'<button type="button" class="btn btn-default btn-xs btn_position btn_delete"><i class="fa fa-times"></i></button></a></li>';
						$("#first_side_right ul").append($li);
						//将笔记本数据绑定到li上
						$("#first_side_right ul li:last").data("notebook",this);
					});
				}else{
					alert("message");
				}
			}
			);
	
}

/***
 * 加载特殊笔记本
 */
function loadSpecialNoteBook(){
	$.post(
	"/CloudNote/notebook/load_special.do",
	{},
	function(result){
		if(result.success){
			var list = result.data;
			$(list).each(function(){
				var tid = this.cn_notebook_type_id;
				if(tid=="1"){
					$("#like_button").data("notebook",this);
				}else if(tid=="2"){
					$("#rollback_button").data("notebook",this);
				}else if(tid=="3"){
					$("#action_button").data("notebook",this);
				}else{
					$("#first_side_right ul li:first").data("notebook",this);
				}
			});
		}else{
			alert(result.message);
			setTimeout(function(){
				if(result.message=="没有登陆"){
					window.location.href="login.html";
				}
			},2000);
		}
	}
	);
}

/****
 * 添加笔记本
 */
function addNoteBook(){
	var inputname = $("#input_notebook").val();
	console.log(inputname);
	$.post(
	"/CloudNote/notebook/save.do",
	{"name":inputname},
	function(result){
		if(result.success){
			var $li = '<li class="online"><a class="unchecked"><i class="fa fa-book" title="笔记本" rel="tooltip-bottom"></i>'+inputname+'<button type="button" class="btn btn-default btn-xs btn_position btn_delete"><i class="fa fa-times"></i></button></a></li>';
			$(".close,.cancle").click();
			$("#first_side_right ul").append($li);
			$("#first_side_right ul li:last").data("notebook",result.data);
		}else{
			alert(result.message);
			setTimeout(function(){
				if(result.message=="没有登陆"){
					window.location.href="login.html";
				}
			},2000);
		}
	}
	);
	
}

/***
 * 重命名笔记本
 */
function updateNoteBook(){
	var rename = $("#input_notebook_rename").val();
	if(rename == ""){
		return;
	}else{
		var $li = $("#first_side_right .checked").parent();
		var notebook = $li.data("notebook");
		notebook.cn_notebook_name = rename;
		
		$.post(
		"/CloudNote/notebook/update.do",
		notebook,
		function(result){
			if(result.success){
				alert("修改");
			}else{
				
			}
		}
		);
	}
	alert("重命名笔记本");
}

/***
 * 删除笔记本
 */
function deleteNoteBook(){
	var $li = $("#first_side_right .checked").parent();
	var notebook = $li.data("notebook");
	$.post(
	"/CloudNote/notebook/delete.do",
	{"id":notebook.cn_notebook_id},
	function(result){
		if(result.success){
			$(".close,.cancle").click();
			$li.remove();
		}else{
			
		}
	}
	);
}

/**
 * 将笔记本列表放置到select组件中
 */
function setNoteBookToSelect(){
	//默认笔记本
	var push = 
	$("#first_side_right ul li:first").data("notebook");
	var option = '<option value="'+push.cn_notebook_id+'">默认笔记本</option>';
	$("#moveSelect").append(option);
	
	$.post(
	"/CloudNote/notebook/load_normal.do",
	{},
	function(result){
		if(result.success){
			var list = result.data;
			$(list).each(function(){
				var option1 = '<option value="'+this.cn_notebook_id+'" name="notebookId">'+this.cn_notebook_name+'</option>';
				$("#moveSelect").append(option1);
			});
		}
	}
	);
}