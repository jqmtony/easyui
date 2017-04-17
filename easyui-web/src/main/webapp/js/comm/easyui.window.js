///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/**
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  * 
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  * 
 *  说明：窗口WINDOW操作处理通用操作JS
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  * 
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  * 
 */
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/**
 * 重新设置easyui-accordion面板大小
 */
function redrawPanel() {
	$('.easyui-accordion').accordion('resize');//
	// $('#tabs').tabs('resize');
}

/**
 * 定义容器高度
 * 
 * @param tagId
 * @param headHeight
 */
function definedTagHeight(tagId, headHeight) {
	var height = document.body.offsetHeight - headHeight;
	$("#" + tagId).css("height", height);
}

/**
 * 弹出框底部的”是否连续添加“是否选中
 * 
 * @returns
 */
function isContinueAdd() {
	return isCheckedByTagName("isContinueAddCheck");
}

/**
 * 收缩toolbar
 * 
 * @param obj
 */
function showToolbarForm(obj) {
	$("#" + obj).children("form").toggle();

	$('.sign-datagrid').datagrid('resize', {
	// width:function(){return document.body.clientWidth;},
	// height:function(){return document.body.clientHeight;}
	});
	$('.sign-treegrid').treegrid('resize', {
	// width:function(){return document.body.clientWidth;},
	// height:function(){return document.body.clientHeight;}
	});
};

// ============弹窗方法============
/**
 * 弹出对话窗口
 * 
 * @param url
 *            地址
 * @param title
 *            标题
 * @param width
 *            宽度
 * @param height
 *            高度
 * @param closeCallback
 *            关闭时的回调函数
 */
function openDialog(url, title, width, height, closeCallback) {
	width = typeof (width) == "undefined" ? 400 : width;
	height = typeof (height) == "undefined" ? 300 : height;
	$("#popDialog").window({
		modal : true,
		resizable : true,
		draggable : true,
		cache : false,
		width : width,
		height : height,
		autoRestore : true,
		autoCloseOnEsc : true,
		title : typeof (title) == "undefined" ? "" : title,
		top : ($(window).height() - height) * 0.5,
		left : ($(window).width() - width) * 0.5,
		href : url,
		onResize : function(width, height) {
			setTimeout('redrawPanel()', 300);
		},
		onClose : function() {
			eval(closeCallback);
		},
		minimizable : false,
		maximizable : false,
		maximized : false,
		collapsible : false,
		loadingMessage : loadingMsg
	});
	// 新版本不需要额外调用open,否则会执行2次
	// $("#popDialog").window("open");

}

/**
 * 弹出模态窗口
 * 
 * @param url
 *            地址
 * @param title
 *            标题
 * @param width
 *            宽度
 * @param height
 *            高度
 * @param closeCallback
 *            关闭时的回调函数
 */
function openWindow(url, title, width, height, closeCallback, maximizable,
		maximized, collapsible) {
	// url = encodeURI(encodeURI(url));//开启后，地址栏中文会乱码
	width = typeof (width) == "undefined" ? 400 : width;
	height = typeof (height) == "undefined" ? 300 : height;

	// $("#popWindowDiv").html("<div id='popWindow' ></div>");
	// alert($("#popWindowDiv").html());
	$("#popWindow").window({
		modal : true,
		resizable : true,
		draggable : true,
		cache : false,
		width : width,
		height : height,
		autoRestore : true,
		autoCloseOnEsc : true,
		title : typeof (title) == "undefined" ? "" : title,
		top : ($(window).height() - height) * 0.5,
		left : ($(window).width() - width) * 0.5,
		href : url,
		onResize : function(width, height) {
			setTimeout('redrawPanel()', 300);
		},
		onClose : function() {
			eval(closeCallback);
		},
		minimizable : false,
		maximizable : !isEmpty(maximizable) ? maximizable : true,
		maximized : !isEmpty(maximized) ? maximized : false,
		collapsible : !isEmpty(collapsible) ? collapsible : false,
		loadingMessage : loadingMsg
	});

	// 新版本不需要额外调用open,否则会执行2次
	// $("#popWindow").window("open");
}

/**
 * 弹出模态窗口
 * 
 * @param url
 *            地址
 * @param title
 *            标题
 * @param closeCallback
 *            关闭时的回调函数
 */
function openMaxWindowByTagId(tagId, url, title, closeCallback, maximizable,
		collapsible) {
	// url = encodeURI(encodeURI(url));//开启后，地址栏中文会乱码
	$("#" + tagId).window({
		modal : true,
		resizable : true,
		draggable : true,
		cache : false,
		autoRestore : true,
		autoCloseOnEsc : true,
		title : typeof (title) == "undefined" ? "" : title,
		href : url,
		onClose : function() {
			eval(closeCallback);
		},
		width : $(window).width() / 2, // 默认缩放宽度为窗口一半
		height : $(window).height() / 2, // 默认缩放高度为窗口一半
		minimizable : false,
		maximizable : !isEmpty(maximizable) ? maximizable : true,
		maximized : true,
		collapsible : !isEmpty(collapsible) ? collapsible : false,
		loadingMessage : loadingMsg
	});

	// 新版本不需要额外调用open,否则会执行2次
	// $("#popWindow").window("open");
}

/**
 * 弹出模态窗口
 * 
 * @param url
 *            地址
 * @param title
 *            标题
 * @param closeCallback
 *            关闭时的回调函数
 */
function openMaxWindow(url, title, closeCallback, maximizable, collapsible) {
	openMaxWindowByTagId("popWindow", url, title, closeCallback, maximizable,
			collapsible);
}
/**
 * 弹出对话窗口
 * 
 * @param url
 *            地址
 * @param title
 *            标题
 * @param width
 *            宽度
 * @param height
 *            高度
 * @param closeCallback
 *            关闭时的回调函数
 */
function openMaxWindowSearch(url, title, closeCallback, maximizable,
		collapsible) {
	openMaxWindowByTagId("popWindowSearch", url, title, closeCallback,
			maximizable, collapsible);
}

function openMaxWindowThree(url, title, closeCallback, maximizable, collapsible) {
	openMaxWindowByTagId("popWindowThree", url, title, closeCallback,
			maximizable, collapsible);
}
/**
 * 弹出模态Iframe窗口
 * 
 * @param url
 *            地址
 * @param title
 *            标题
 * @param width
 *            宽度
 * @param height
 *            高度
 * @param closeCallback
 *            关闭时的回调函数
 */
function openMaxIframeWindow(url, title, width, height, closeCallback,
		maximizable, collapsible) {

	$("#windowIframe").attr("src", url);
	$("#windowIframe").attr("hidden", false);
	$("#popWindowIframe").window({
		modal : true,
		resizable : true,
		draggable : true,
		// cache : false, iframe无法使用该参数
		// width : width,
		// height : height,
		autoRestore : true,
		autoCloseOnEsc : true,
		title : typeof (title) == "undefined" ? "" : title,
		top : ($(window).height() - height) * 0.5,
		left : ($(window).width() - width) * 0.5,
		onClose : function() {
			$("#windowIframe").attr("src", "");
			$("#windowIframe").attr("hidden", true);
			eval(closeCallback);
		},
		// href: url,
		// iniframe: true, //iframe (插件扩展功能)
		minimizable : false,
		maximizable : !isEmpty(maximizable) ? maximizable : true,
		maximized : true,
		collapsible : !isEmpty(collapsible) ? collapsible : false,
		loadingMessage : loadingMsg
	});
	// var opts = $("#popWindowIframe").window('options');
	// opts.content = "<iframe id=\"windowIframe\" name=\"windowIframe\"
	// class=\"panel-iframe\" frameborder=\"0\" width=\"100%\" height=\"100%\"
	// marginwidth=\"0px\" marginheight=\"0px\" scrolling=\"auto\"></iframe>";

	// 新版本不需要额外调用open,否则会执行2次
	// $("#popWindowIframe").window("open");
}


/**
 * 弹出对话窗口
 * 
 * @param url
 *            地址
 * @param title
 *            标题
 * @param width
 *            宽度
 * @param height
 *            高度
 * @param closeCallback
 *            关闭时的回调函数
 */
function openWindowSearch(url, title, width, height, closeCallback,
		maximizable, maximized, collapsible) {
	// url = encodeURI(encodeURI(url));//开启后，地址栏中文会乱码
	width = typeof (width) == "undefined" ? 400 : width;
	height = typeof (height) == "undefined" ? 300 : height;
	$("#popWindowSearch").window({
		modal : true,
		resizable : true,
		draggable : true,
		cache : false,
		width : width,
		height : height,
		autoRestore : true,
		autoCloseOnEsc : true,
		title : typeof (title) == "undefined" ? "" : title,
		top : ($(window).height() - height) * 0.5,
		left : ($(window).width() - width) * 0.5,
		href : url,
		onClose : function() {
			eval(closeCallback);
		},
		minimizable : false,
		maximizable : !isEmpty(maximizable) ? maximizable : true,
		maximized : !isEmpty(maximized) ? maximized : false,
		collapsible : !isEmpty(collapsible) ? collapsible : false,
		loadingMessage : loadingMsg
	});
	// 新版本不需要额外调用open,否则会执行2次
	// $("#popWindowSearch").window("open");

}

function openWindowThree(url, title, width, height, closeCallback, maximizable,
		maximized, collapsible) {
	// url = encodeURI(encodeURI(url));//开启后，地址栏中文会乱码
	width = typeof (width) == "undefined" ? 400 : width;
	height = typeof (height) == "undefined" ? 300 : height;
	$("#popWindowThree").window({
		modal : true,
		resizable : true,
		draggable : true,
		cache : false,
		width : width,
		height : height,
		autoRestore : true,
		autoCloseOnEsc : true,
		title : typeof (title) == "undefined" ? "" : title,
		top : ($(window).height() - height) * 0.5,
		left : ($(window).width() - width) * 0.5,
		href : url,
		onClose : function() {
			eval(closeCallback);
		},
		minimizable : false,
		maximizable : !isEmpty(maximizable) ? maximizable : true,
		maximized : !isEmpty(maximized) ? maximized : false,
		collapsible : !isEmpty(collapsible) ? collapsible : false,
		loadingMessage : loadingMsg
	});

	// 新版本不需要额外调用open,否则会执行2次
	// $("#popWindowThree").window("open");
}

/**
 * 带有连续添加条件判断
 * 从子页面中关闭当前弹出窗口() continuousAddFlag 标记是否判断连续添加
 */
function closeThisPopWindow() {
	if (currentEnterKeyIds != null && currentEnterKeyIds.length > 0) {
		currentEnterKeyIds.pop();
	}
	
	var ic2 = $("#isContinueAddCheck2").is(":checked");// isChecked("isContinueAddCheck2");
	var ic3 = $("#isContinueAddCheck3").is(":checked");
	
	
	if (continuousAddFlag && ic2) {		
		$(".popupWindowContainer form:last")[0].reset();
	} else if (continuousAddFlag && ic3) {
		// 提交数据不变
	} else {
		closeThisPopWindowCompel();
	}
}
/**
 * 从子页面中关闭当前弹出窗口（强制关闭，不做其他判断）
 */
function closeThisPopWindowCompel(winId) {
	
	if(!isEmpty(winId)){
		$('#'+winId).window('close');  
	}
	//var jq = top.JQuery;		
	//alert(jq("a.panel-tool-close:visible:last").length);		
	//$('a.panel-tool-close:visible:last').click();	
	//jq('#popWindowIframe',parent.document).window('close');		
	//parent.$('#popWindowIframe').window('close');		
	
	var maxZ = Math.max.apply(null, $.map($('div.window-mask:visible'), function (e, n) {
        if ($(e).css('position') == 'absolute')
            return parseInt($(e).css('z-index')) || 1;
        })
    );
	//alert(maxZ);
	
	var winNum = $("div.window-mask:visible:last").prevAll("div.panel.window:first")
	.children(".panel-body.window-body").length;
	// 关闭顶层的 easyui-window 或者 easyui-dialog 对象
	// 意思为查找easyui弹窗最后一个可见的元素
	
	//$("div.window-mask").css({"z-index":maxZ}).html();
	
	//alert($("div.window-mask[style*='z-index:"+maxZ+"']:visible:last").prevAll("div.panel.window:first").children(".panel-body.window-body"));
	
	if(winNum>0){
	$("div.window-mask:visible:last").prevAll("div.panel.window:first")
			.children(".panel-body.window-body").each(
					function() {
						var win = $(this);							
						var opts = win.window("options");
						if (opts
								&& opts.closable
								&& !win.window("header").find(
										".panel-tool a").attr("disabled")) {
							//$.util.exec(function() {//1.4.3版本不兼容此写法
								win.window("close");
							//});
						}
					});
	}else{
		//Iframe弹窗特殊处理，Iframe弹窗同时只能存在一层
		parent.$('#popWindowIframe').window('close');
	}
}


/**
 * 移动窗体
 * 
 * @param left
 * @param top
 */
function moveDialog(left, top) {
	$("#popDialog").dialog("mymove", {
		left : left,
		top : top
	});
}
/**
 * 显示DIV
 * 
 * @param divName
 * @return
 */
function showDiv(divName) {
	$("#" + divName).css("display", "");
}

/**
 * 隐藏DIV
 * 
 * @param divName
 * @return
 */
function hideDiv(divName) {
	$("#" + divName).css("display", "none");
}

// ===================================消息弹出框======begin============================================
/**
 * 提交操作进度条
 * 
 * @param isShow
 * @param title
 * @param msg
 * @return
 */
function showProcess(isShow, title, msg) {
	
	if (!isShow) {
		$.messager.progress('close');
		return;
	}

//	var win = $.messager.progress({
//		title : title,
//		msg : msg
//	});
}
/**
 * 动态显示进度条
 * 
 * @param sumValue
 * @param currentValue
 * @param refreshTime
 */
function showProcessDynamic(sumValue, currentValue, refreshTime) {
	var getProcessValueUrl = "";
	$.post(url, null, function(result) {

	});
	if (currentValue < sumValue) {
		value += Math.floor(Math.random() * 10);
		$('#p').progressbar('setValue', value);
		setTimeout(arguments.callee, 200);
	}
};
/**
 * 直接弹出
 * 
 * @param msg
 * @param title
 * @param callBack
 */
function euAlert(msg, title, callBack) {
	$.messager.alert(typeof (title) == "undefined" ? titleinfo : title, msg,
			"info", function() {
				callBack();
			});
}
/**
 * 操作确认提示框
 * 
 * @param msg
 * @param callBack
 */
function euConfirm(msg, callBack) {
	$.messager.confirm(titleinfo, msg, function(r) {
		if (r) {
			callBack();
		}
	});
}
/**
 * 操作输入框
 * 
 * @param msg
 * @param callBack
 * @return
 */
function euPrompt(msg, callBack) {
	$.messager.prompt(titleinfo, msg, callBack);
}
/**
 * 右下角显示提示
 * 
 * @param msg
 * @param title
 * @param waitTime
 *            停顿时间(秒)
 */
function euShow(msg, title, waitTime) {
	$.messager.show({
		height : msg.length > 20 ? 200 : 100,
		width : msg.length > 20 ? 350 : 250,
		title : typeof (title) == "undefined" || title == null ? titleinfo
				: title,
		msg : msg,
		timeout : typeof (waitTime) == "undefined" ? waitSecond
				: waitTime * 1000,
		showType : "show",
		position : "bottomRight"
	});
}
/**
 * 右下角滑出提示
 * 
 * @param msg
 * @param title
 * @param waitTime
 *            停顿时间(秒)
 */
function euSlide(msg, title, waitTime) {
	$.messager.show({
		height : msg.length > 20 ? 200 : 100,
		width : msg.length > 20 ? 350 : 250,
		title : typeof (title) == "undefined" ? titleinfo : title,
		msg : msg,
		timeout : typeof (waitTime) == "undefined" ? waitSecond
				: waitTime * 1000,
		showType : "slide"
	});
}
/**
 * 右下角淡入提示
 * 
 * @param msg
 * @param title
 * @param waitTime
 *            停顿时间(秒)
 */
function euFade(msg, title, waitTime) {
	$.messager.show({
		height : msg.length > 20 ? 200 : 100,
		width : msg.length > 20 ? 350 : 250,
		title : typeof (title) == "undefined" ? titleinfo : title,
		msg : msg,
		timeout : typeof (waitTime) == "undefined" ? waitSecond
				: waitTime * 1000,
		showType : "fade"
	});
}
// ===================================消息弹出框======end============================================

/**
 * 获取当前页面查询按钮
 * 
 * @returns
 */
function getCurrentPageSearchButton() {
	var searchButton = $("a[iconcls='icon-search'][defaultenterkey='true'][autoSearch='true']");

	return searchButton;
}
/**
 * 开启查找页面查询按钮 等待所有combogrid自动加载完毕后，执行查询功能
 */
function runPageSearchFun() {
	var searchButton = getCurrentPageSearchButton();
	// alert("I'm runPageSearchFun searchButton="+searchButton.size());
	var tempSearchButton = null;
	// //alert("filterSearchButtonRecord="+filterSearchButtonRecord);
	if (trySearchNum <= 5) {
		// alert("trySearchNum="+trySearchNum+",unloadCombogridNum="+unloadCombogridNum+",unloadCombogridIds="+unloadCombogridIds);
		if (unloadCombogridNum == 0) {
			window.clearInterval(setIntervalId);
			searchButton.each(function(i) {
				// alert("filterSearchButtonRecord="+filterSearchButtonRecord+",add
				// each
				// id="+$(this).attr("id")+",onClick="+$(this).attr("onClick"));
				if (filterSearchButtonRecord.indexOf($(this).attr("id")) < 0) {
					if (tempSearchButton == null) {
						tempSearchButton = $(this);
					}
					filterSearchButtonRecord += $(this).attr("id") + ",";
					// return false;
				}
			})
			excutePageSearchButton(tempSearchButton);
		} else {
			// 等待其他标签加载(combogrid/deptGrid/sectionGrid)
			++trySearchNum;
		}
	} else {
		window.clearInterval(setIntervalId);
	}
}
/**
 * 执行页面查询按钮功能
 * 
 * @param searchButton
 */
function excutePageSearchButton(searchButton) {
	if (!isEmpty(searchButton) && searchButton.size() == 1) {
		searchButton.trigger("onclick");
	} else if (!isEmpty(searchButton) && searchButton.size() > 1) {
		searchButton.last().trigger("onclick");
	}
}

/**
 * 获取最后弹出框当前的form名称，用于formJson自动赋值操作
 */
function getLastWindowCurrentForm() {
	//alert("execute getLastWindow begin");
	var currentForm = "";
	$("div.window-mask:visible:last").prevAll("div.panel.window:first")
			.children(".panel-body.window-body").each(
					function() {// .find(".popupWindowContainer")
						var win = $(this);
						var panels = $(win).find(".tabs-panels > .panel");
						if (panels.length > 0) {
							panels.each(function() {
								if ($(this).css("display") == 'block') {
									// alert("panels.length = " + panels.length
									// + ",display = " + $(this).css("display")
									// + ",lastName=
									// "+$(this).find(".popupWindowContainer
									// form").attr("name"));
									currentForm = $(this).find(
											".popupWindowContainer form").attr(
											"name");
									return false;
								}
							});
						} else {
							// alert("only form = " +
							// $(win).find(".popupWindowContainer
							// form").attr("name"));
							currentForm = $(win).find(
									".popupWindowContainer form").attr("name");
							return false;
						}

					})
	//alert("getLastWindow is return currentForm==========="+currentForm);
	return currentForm;
}

/**
 * 获取最后的弹窗对象win
 */
function getLastWindow() {	
	$("div.window-mask:visible:last").prevAll("div.panel.window:first")
			.children(".panel-body.window-body").each(
					function() {// .find(".popupWindowContainer")
						var win = $(this);			 
						return win;		
					})
}


/**
 * 
 * @param formName
 * @param name
 * @param value
 *            回调函数名称
 * @return
 */
function showLanguagePanel(formName, name, value) {
	showLanguagePage(formName, name, value, "loadFormObject");
}
/**
 * 显示多语言界面
 * 
 * @param formName
 * @param name
 * @param value
 * @param callback
 */
function showLanguagePage(formName, name, value, callback) {
	var params = "language.do?method=toLanguageSelectPage&callback=" + callback
			+ "&formName=" + formName + "&name=" + name + "&value=" + value
	openDialog(params, "<spring:message code='baseinfo.language'/>", 270, 160,
			function() {
				if (closeLanguageDialog) {
					closeLanguageDialog();
				}
			});
}