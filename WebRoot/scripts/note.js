/***
 * 加载普通笔记
 */
function getNormalNoteList(){
	var $li = $("#first_side_right .checked").parent();
	var notebook = $li.data("notebook");
	$.post(
	"/CloudNote/note/findNotes.do",
	{"notebookId":notebook.cn_notebook_id},
	function(result){
		if(result.success){
			var list = result.data;
			$(list).each(function(){
				var $noteli = '<li class="online">'+
										'<a class="unchecked">'+
											'<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i> '+this.cn_note_title+'<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>'+
										'</a>'+
										'<div class="note_menu" tabindex="-1">'+
											'<dl>'+
												'<dt><button type="button" class="btn btn-default btn-xs btn_move" title="移动至..."><i class="fa fa-random"></i></button></dt>'+
												'<dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享"><i class="fa fa-sitemap"></i></button></dt>'+
												'<dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt></dl></div></li>';
				
				$("#second_side_right ul").append($noteli);
				$("#second_side_right ul li:last").data("note",this);
			});
		}else{
			
		}
	}
	);
}

/***
 * 查询普通笔记内容
 */
function getNoteDetail(){
	var $lili = $("#second_side_right .checked").parent();
	var note = $lili.data("note");
	$("#input_note_title").val(note.cn_note_title);
	var body = note.cn_note_body;
	um.setContent(body==null?"":body);
}

/***
 * 创建普通笔记
 */
function createNormalNote(){
	var noteName = $("#input_note").val();
	var $li = $("#first_side_right .checked").parent();
	var notebook = $li.data("notebook");
	var notebookId = notebook.cn_notebook_id;
	$.post(
	"/CloudNote/note/save.do",
	{"noteTitle":noteName,
		"notebookId":notebookId},
	function(result){
		if(result.success){
			var $notelili = '<li class="online">'+
			'<a class="unchecked">'+
				'<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i> '+noteName+'<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>'+
			'</a>'+
			'<div class="note_menu" tabindex="-1">'+
				'<dl>'+
					'<dt><button type="button" class="btn btn-default btn-xs btn_move" title="移动至..."><i class="fa fa-random"></i></button></dt>'+
					'<dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享"><i class="fa fa-sitemap"></i></button></dt>'+
					'<dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt></dl></div></li>';
			var note = result.data;
			$("#second_side_right ul").append($notelili);
			$("#second_side_right ul li:last").data("note",note);
			$(".close,.cancle").click();
		}else{
			
		}
		
	}
	);
}

/***
 * 更新普通笔记
 */
function updateNormalNote(){
	//alert("更新普通笔记");
	var $li = $("#second_side_right .checked").parent();
	var note = $li.data("note");
	var noteBody = um.getContent();
	var noteTitle = $("#input_note_title").val();
	if(noteTitle == note.cn_note_body){
		return;
	}
	if(noteBody == note.cn_note_body){
		return;
	}else{
		note.cn_note_title = noteTitle;
		$li.html('<li class="online"><a class="unchecked"><i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i> '+noteTitle+'<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button></a><div class="note_menu" tabindex="-1"><dl><dt><button type="button" class="btn btn-default btn-xs btn_move" title="移动至..."><i class="fa fa-random"></i></button></dt><dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享"><i class="fa fa-sitemap"></i></button></dt><dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt></dl></div></li>');
	}
	note.cn_note_body = noteBody;
	$.post(
	"/CloudNote/note/update.do",
	note,
	function(result){
		if(result.success){
			alert("修改成功");
		}else{
			
		}
	}
	);
}

/***
 * 删除普通笔记
 */
function deleteNormalNote(){
	//alert("删除普通笔记");
	var notebook = $("#rollback_button").data("notebook");
	var notebookId = notebook.cn_notebook_id;
	var $li = $("#second_side_right .checked").parent();
	var note = $li.data("note");
	note.cn_notebook_id = notebookId;
	$.post(
	"/CloudNote/note/update.do",
	note,
	function(result){
		if(result.success){
			alert("成功移到回收站");
			$li.remove();
			$(".close,.cancle").click();
		}else{
			
		}
	}
	);
	
	
}

/***
 * 移动笔记
 */
function moveNote(){
	var  new_book_id = $("#moveSelect").val();
	if(new_book_id==""){
		return;
	}else{
		var $li = $("#second_side_right .checked").parent();
		var note = $li.data("note");
		if(note.cn_notebook_id == new_book_id){
			
		}else{
			note.cn_notebook_id = new_book_id;
			$.post(
			"/CloudNote/note/update.do",
			note,
			function(result){
				if(result.success){
					$li.remove();
					$("#input_note_title").val("");
					var body = "";
					um.setContent(body==null?"":body);
//					$(".cancel").trigger("click");
					$(".close,.cancle").click();
				}
			}
			);
		}
	}
}

/***
 * 分享笔记
 */
function createShareNote(){
	$("footer div strong").text("分享成功").parent().fadeIn(100);
	setTimeout(function(){
		$("footer div").fadeOut(500);
	}, 1500);
}

/***
 * 查询回收站笔记列表
 */
function getRecycleNoteList(){
	alert("查询回收站笔记列表");
}

/***
 * 查看回收站笔记内容
 */
function getRecycleNoteDetail() {
	console.log("查看回收站笔记内容");
}

/***
 * 删除回收站笔记
 */
function deleteRecycleNote(){
	alert("删除回收站笔记");
}

/***
 * 搜索分享笔记列表
 */
function getShareNoteList(cond,currentPage){
	$('#pc_part_2,#pc_part_3,#pc_part_4,#pc_part_7,#pc_part_8').hide();
	$('#pc_part_6,#pc_part_5').show();
	//alert("搜索分享笔记列表");
	$.post(
			"/CloudNote/note/search.do",
			{"cond":cond,
				"currentPage":currentPage},
			function(result){
				if(result.success){
					var list = result.data;
					$(list).each(function(){
						var $li = '<li class="online"><a href="#"><i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>'+this.cn_share_title+'<button type="button" class="btn btn-default btn-xs btn_position btn_like"><i class="fa fa-star-o"></i></button><div class="time"></div></a></li>';
						$("#sixth_side_right ul").append($li);
						$("#sixth_side_right ul li:last").data("share",this);
					});
				}else{
					
				}
			}
	);
	
}

/***
 * 查询分享笔记内容
 */
function getShareNoteDetail(){
	alert("查询分享笔记内容");
}

/***
 * 收藏分享笔记
 */
function likeShareNote(shareId,dom){
	alert("收藏分享笔记");
}

/***
 * 加载收藏笔记
 */
function getLikeNoteList(likeNoteId){
	alert("加载收藏笔记");
}

/***
 * 查看收藏笔记内容
 */
function getLikeNoteDetail(noteId) {
	console.log("查看收藏笔记内容");
}

/***
 * 删除收藏笔记
 */
function deleteLikeNote(noteId,dom){
	alert("删除收藏笔记");
}

/***
 * 加载本用户参加活动笔记列表
 */
function getNoteActivityList(noteBookId){
	alert("加载本用户参加活动笔记列表");
}

/***
 * 查询参加活动的笔记内容
 */
function getActivityNoteDetail(noteId) {
	console.log("查询参加活动的笔记内容");
}