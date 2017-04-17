///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * 说明：全局变量 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */
// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
var UNLIMITED_DATE = "9999-01-01"; // 无限制日期
var waitSecond = 3000; // 窗口弹出后等待时间
var currentOpenDiv = []; // 当前打开的div列表
var currentEnterKeyIds = []; // 当前响应ENTER键按钮ID数组
var defaultEnterKeyId = ""; // 默认响应ENTER键按钮ID
var pageFunctionArr = []; // page页面反射调用方法：方法名,控件id,控件类型 可以不指定控件id,控件类型
var currentPageFlag = ""; // 当前页面编辑状态：add/edit/
var MaxNum2DgOpenEdit = 40; // 支持datagrid插入或追加时，自动开启编辑功能。如果大于这个记录时，建议不要开启。
// 关联对象参数数组{'被关联对象id'：'关联参数:参数值'} 对应传到后台参数名：relationObjParamList
var relationObjParamMap = null;

var unloadCombogridNum = 0; // 剩余combogrid没有加载的数量
var unloadCombotreeNum = 0; // 剩余combotree没有加载的数量
var unloadCombogridIds = ""; // 未执行加载的combogridId
var unloadCombotreeIds = ""; // 未执行加载的combotreeId
var unloadDatagridIds = ""; // 未执行加载的datagridId
var unloadTreegridIds = ""; // 未执行加载的treegridId
var filterSearchButtonRecord = ""; // 过滤查询按钮（已经查询过的记录在案）
var trySearchNum = 0; // 页面执行自动查询 尝试次数
var setIntervalId;

var continuousAddFlag = true;//连续添加是否有效

// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * 说明：系统system通用操作JS * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */
// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/**
 * 休眠
 * 
 * @param numberMillis
 */
function sleep(numberMillis) {
	var now = new Date();
	var exitTime = now.getTime() + numberMillis;
	while (true) {
		now = new Date();
		if (now.getTime() > exitTime)
			return;
	}
}

// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * 说明：键盘通用操作JS * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */
// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
document.onkeydown = keyDownFun;

// 处理键盘事件 禁止后退键（Backspace）密码或单行、多行文本框除外
function keyDownFun(e) {
	var ev = e || window.event;// 获取event对象
	if (ev.keyCode == 8 || ev.keyCode == 13) {
		var obj = ev.target || ev.srcElement;// 获取事件源
		var t = obj.type || obj.getAttribute('type');// 获取事件源类型
		// 获取作为判断条件的事件类型
		var vReadOnly = obj.getAttribute('readonly');
		var vEnabled = obj.getAttribute('enabled');
		// 处理null值情况
		vReadOnly = (vReadOnly == null) ? false : vReadOnly;
		vEnabled = (vEnabled == null) ? true : vEnabled;

		if (ev.keyCode == 8) {
			var flag1 = ((t == "password" || t == "text" || t == "textarea") && (vReadOnly == true || vEnabled != true)) ? true
					: false;
			var flag2 = (t != "password" && t != "text" && t != "textarea") ? true
					: false;
			// 判断
			if (flag2 || flag1) {
				return false;
			}
		} else if (ev.keyCode == 13) {
			var flag = (t == "textarea" && (vReadOnly != true && vEnabled == true)) ? true
					: false;
			if (!flag) {
				if (currentEnterKeyIds != null && currentEnterKeyIds.length > 0) {
					$("#" + currentEnterKeyIds[currentEnterKeyIds.length - 1])
							.click();
				} else {
					if (defaultEnterKeyId != "") {
						$("#" + defaultEnterKeyId).click();
					}
				}
				return false;
			}

			return false;
		}

	} else if (ev.keyCode == 27) {
		closeThisPopWindowCompel();
		return true;
	}
}

// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * 说明：浏览器通用操作JS * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */
// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/**
 * 根据浏览器版本获取frame对象(用于Chrome35版本以上取frame不同)
 */
function getFrameObj(frameObj) {
	var chromeIndex = window.navigator.userAgent.indexOf("Chrome");
	var isChrome = chromeIndex !== -1
	if (isChrome) {
		var chromeVersion = window.navigator.userAgent.substring(
				chromeIndex + 7, chromeIndex + 9);
		if (chromeVersion >= 35) {
			return frameObj.contentWindow;
		} else {
			return frameObj;
		}
	}
}

/**
 * JS操作cookies
 */
// 写cookies
function setCookie(name, value) {
	var Days = 1;// 设置保存天数
	var exp = new Date();
	exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
	document.cookie = name + "=" + escape(value) + ";expires="
			+ exp.toGMTString();
}
// 读取cookies
function getCookie(name) {
	var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
	if (arr = document.cookie.match(reg))
		return unescape(arr[2]);
	else
		return null;
}
// 删除cookies
function delCookie(name) {
	var exp = new Date();
	exp.setTime(exp.getTime() - 1);
	var cval = getCookie(name);
	if (cval != null)
		document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString();
}

// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * 说明：字符串处理通用操作JS * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */
// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/**
 * 替换value字符串中所有空格
 * 
 * @param value
 * @return
 */
function replaceSpace(value) {
	if (typeof (value) != undefined && value != "" && value != null) {
		return value.replace(/ /g, '');
	}
}
/**
 * 替换value字符串中
 * 
 * @param value		1234567
 * @param fromStr	123
 *            需替换的字符串
 * @param toStr		000
 *            替换的字符串
 * @returns			0004567
 */
function replaceAll(value, findStr, toStr) {
	if (typeof (value) != undefined && value != "" && value != null) {
		return value.replace(new RegExp(findStr, 'g'), toStr);
	}
}

/**
 * 获取指定字符串的个数
 * 
 * @param str
 * @param split
 * @returns
 */
function getSplitCount(str, split) {
	var r = new RegExp('\\' + str2, "gi");

	return str1.match(r).length;
}

/**
 * 从url中，获取参数组织成数组
 * 
 * @param url
 * @returns {___anonymous2917_2918}
 */
function getJson4Url(url) {
	var resultArr = {};
	if (url.indexOf("&") > 0) {
		var params = url.substring(url.indexOf("&") + 1, url.length).split("&");

		resultArr[0] = url.substring(0, url.indexOf("&"));
		if (params.length > 0) {
			data = "{"
			for (var i = 0; i < params.length; i++) {
				data += (data.length > 1) ? "," : "";
				data += "'" + params[i].split("=")[0] + "':'"
						+ params[i].split("=")[1] + "'";
			}
			data += "}";
		} else {
			data = "";
		}
		resultArr[1] = data;

	} else {
		resultArr[0] = url;
		resultArr[1] = '';
	}
	return resultArr;

}
/**
 * 从数组中获取参数及参数值 采用后台传参模式
 * 
 * @param arr
 * @returns {String} &deptId=1802&userid=01
 */
function getUrlParamStr4Arr(arr) {
	return getDefineStr4Arr(arr, "=", "&");
}

/**
 * 按自定义方式，把数组的字段参数及字段值用连接符拼装成字符串
 * 
 * @param arr
 *            数组
 * @param splitChar分隔符
 *            如： :
 * @param linkChar连接符
 *            如： ,
 * @returns {String} 如： id:123,name:43
 */
function getDefineStr4Arr(arr, splitChar, linkChar) {
	var paramStr = "";
	if (isEmpty(splitChar)) {
		splitChar = ":";
	}
	if (isEmpty(linkChar)) {
		linkChar = ",";
	}
	$.each(arr, function(i, field) {
		if (paramStr == "") {
			paramStr = (field.name + splitChar + field.value);
		} else {
			paramStr = paramStr + linkChar
					+ (field.name + splitChar + field.value);
		}
	});
	return paramStr;
}

/**
 * 字符串转url参数字符串 如： craft:1,id:2——————》 &craft=1&id=2
 * 
 * @param str
 * @returns {String}
 */
function str2UrlParams(str) { // 主要是推荐这个函数。它将jquery系列化后的值转为name=value的形式。
	var params = "";
	if (!isEmpty(str)) {
		var objArr = str.split(",");
		for (var i = 0; i < objArr.length; i++) {
			params += "&" + objArr[i].split(":")[0] + "=" + objArr[i].split(":")[1];
		}
	}
	return params;
}

/**
 * 从参数字符串中，获取指定参数对象的值
 * 
 * @param field
 *            指定对象（rowIndex)
 * @param params
 *            "rowIndex:1;rowNum:2"
 * @param split
 *            分隔符，默认为;
 */
function getFieldValue4Params(field, paramStr, split) {
	if (!isEmpty(paramStr)) {
		var arr = paramStr.split(isEmpty(split) ? ";" : split);
		for (var i = 0; i < arr.length; i++) {
			if (arr[i].split(":")[0] == field) {
				return arr[i].split(":")[1];
			}
		}
	}
	return 0;
}

/**
 * 获取返回的参数中，rowIndex参数的值
 * 
 * @param params
 *            如：rowIndex:1;rowNum:2
 * @param split
 */
function getRowIndex4Params(paramStr, split) {
	return getFieldValue4Params("rowIndex", paramStr, split);
}

// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * 说明：对象Object处理通用操作JS * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */
// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/**
 * 判断是否为空
 * 
 * @param obj
 * @returns {Boolean} (typeof(obj)== undefined || obj==undefined || obj==
 *          "undefined" || obj==null || obj=="" || obj=='null' )
 */
function isEmpty(obj) {

	if ((typeof (obj) == undefined || obj == undefined || obj == "undefined"
			|| obj == null || obj == "" || obj == 'null')
			&& !isNumber(obj) && !isBoolean(obj)) {
		// if (!obj && typeof(obj)!="undefined" && obj!=0) {
		return true;
	} else {
		return false;
	}
}
/**
 * JSON（包括JSON）转字符串
 * 
 * @param obj
 */
function json2Str(obj) {
	return JSON.stringify(obj);
}

/**
 * obj转成可用对象（如bean，map)
 * 
 * @param obj
 * @return
 */
function evalObj(obj) {
	if (!isEmpty(obj)) {
		// return eval("(" + obj + ")");
		return (new Function("", "return " + obj))();
	}
}

/**
 * 打印对象的属性方法，调试用
 * 
 * @param Obj
 */
function ShowObjProperty(Obj) {
	var PropertyList = '';
	var PropertyCount = 0;
	for (i in Obj) {
		if (Obj.i != null)
			PropertyList = PropertyList + i + '属性：' + Obj.i + '\r\n';
		else
			PropertyList = PropertyList + i + '方法\r\n';
	}
}

/**
 * 产生一个n位的随机数 返回字符串
 * 
 * @param n
 *            默认值为2
 * @returns {String}
 */
function randomNum(n) {
	var rnd = "";
	if (isEmpty(n)) {
		n = 2;
	}
	for (var i = 0; i < n; i++) {
		rnd += Math.floor(Math.random() * 10);
	}
	return rnd;
}


/**
 * 字符串转换浮点数值
 * @param str
 * @returns
 */
function str2Float(str,defaultValue){
	if(!isEmpty(str)) {
		return parseFloat(str);
	} else if(!isEmpty(defaultValue)) {
		return defaultValue;
	}
	return null;
}

// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * 说明：数据集List处理通用操作JS * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */
// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/**
 * 从allList中，删除存在于sonList的记录。返回一个不存在与sonList中的记录集
 * 
 * @param sonList
 * @param allList
 * @param idFileds
 *            判断记录时，使用的字段。多字段用逗号隔开
 */
function filterExistList4OthList(sonList, allList, idFileds) {
	var tempArr = [];
	for (var i = 0; i < allList.length; i++) {
		var isExist = false;
		for (var j = 0; j < sonList.length; j++) {
			if (eval("sonList[j]." + idFileds) == eval("allList[i]." + idFileds)) {
				isExist = true;
				break;
			}
		}
		if (!isExist) {
			tempArr.push(allList[i]);
		}
	}
	return tempArr;
}

// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * 说明：Ajax处理通用操作JS * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */
// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/**
 * 使用Ajax的post方式获取数据
 * 
 * @param url
 * @param callback
 */
function ajaxPost(url, callback) {

	var resultArr = getJson4Url(url);

	$.post(resultArr[0], (resultArr[1].length > 0 ? evalObj(resultArr[1])
			: null), function(result) {
		callback(result);
	});
}

/**
 * 使用Ajax的get方式获取数据
 * 
 * @param url
 * @param callback
 *            2012年11月12日修改：由于IE下，get方式导致许多错误，这里全部使用Post方式，经过测试，对原有程序没有影响
 */
function ajaxGet(url, callback) {
	var resultArr = getJson4Url(url);
	$.post(resultArr[0], (resultArr[1].length > 0 ? evalObj(resultArr[1])
			: null), function(result) {
		callback(result);
	});
}

// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * 说明：反射处理通用操作JS * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */
// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/**
 * 反射执行页面控件的方法(供不同的控件使用）
 */
function reflectFun(funName, tagId, tagName) {
	if (tagName == null || tagName == "") {
		tagName = "combogrid";
	}
	if (tagId == null) {
		eval(funName)();
	} else {
		eval(funName)(getValue2TagId(tagId, tagName));
	}
}
/**
 * 反射执行方法，参数支持5个
 * 
 * @param funName
 * @param params
 */
function reflectFunWParams(funName, param1, param2, param3, param4, param5) {
	return eval(funName)(param1, param2, param3, param4, param5);
}

/**
 * 统计函数执行时间
 * 
 * @param funName
 */
function getFunExecuteTime(funName, param1, param2, param3, param4, param5) {
	var beginTime = new Date();
	var returnObj = reflectFunWParams(funName, param1, param2, param3, param4,
			param5);
	var endTime = new Date();
	var executeSecond = diffTime(beginTime, endTime, "SECOND");
	euShow(funName + "执行时间累计为: " + executeSecond + "秒！", "方法执行时间统计", 10);
	return returnObj;
}

// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * 说明：判断验证以及Validate处理通用操作JS * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */
// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/**
 * 检查参数是否是布尔型
 * 
 * @param obj
 * @returns {Boolean}
 */
function isBoolean(obj) {
	return (typeof obj == 'boolean');
	// return (typeof obj=="object" && obj.constructor==Boolean);
}
/**
 * 判断js是否为一个对象
 * 
 * @param obj
 * @returns {Boolean}
 */
function isObject(obj) {
	return (typeof obj == 'object') && obj.constructor == Object;
}

function isString(obj) {
	return (typeof obj == 'string') && obj.constructor == String;
}

function isArray(obj) {
	return (typeof obj == 'object') && obj.constructor == Array;
}
/**
 * 检查参数是否是对象，并且其构造函数是Number对象的构造函数
 * 
 * @param obj
 * @returns {Boolean}
 */
function isNumber(obj) {
	return (typeof obj == 'number') && obj.constructor == Number;
}

function isDate(obj) {
	return (typeof obj == 'object') && obj.constructor == Date;
}
/**
 * 检查参数是否是函数对象
 * 
 * @param obj
 * @returns {Boolean}
 */
function isFunction(obj) {
	return (typeof obj == 'function') && obj.constructor == Function;
}

/**
 * 记录是否已锁定或有效
 * 
 * @param val
 *            值（0，1）
 * @param node
 *            节点
 */
function formatLockOrNot(val, node) {
	if ("0" == val) {
		return "<span class='l-btn-text icon-no' style='padding-left: 40px;'>"
				+ formatLock + "</span>";
	} else {
		return "<span class='l-btn-text icon-ok' style='padding-left: 40px;'>"
				+ formatUnlock + "</span>";
	}
}

function formatInterfaceOrNot(val, node) {
	if ("0" == val) {
		return "<span class='l-btn-text icon-no' style='padding-left: 30px;'>"
				+ formatNo + "</span>";
	} else {
		return "<span class='l-btn-text icon-ok' style='padding-left: 30px;'>"
				+ formatYes + "</span>";
	}
}
function formatValOrNo(val, node) {
	if ("0" == val) {
		return "<span class='l-btn-text icon-no' style='padding-left: 30px;'>"
				+ formatNo + "</span>";
	} else {
		return "<span class='l-btn-text icon-ok' style='padding-left: 30px;'>"
				+ formatYes + "</span>";
	}
}
function formatValueYesOrNo(val, node) {
	if ("0" == val) {
		return "<span class='l-btn-text icon-no' style='padding-left: 30px;'>"
				+ formatNo + "</span>";
	} else {
		return "<span class='l-btn-text icon-ok' style='padding-left: 30px;'>"
				+ formatYes + "</span>";
	}
}

function formatStaticYesOrNo(val, node) {
	if ("1" == val) {
		return "<span class='l-btn-text icon-ok' style='padding-left: 30px;'>"
				+ formatYes + "</span>";
	} else {
		return "<span class='l-btn-text icon-no' style='padding-left: 30px;'>"
				+ formatNo + "</span>";
	}
}
function formatModuleName(val, node) {
	if ("BS" == val) {
		return "基础模块";
	} else if ("ERP" == val) {
		return "工艺模块";
	} else if ("PM" == val) {
		return "生产模块";
	} else if ("DM" == val) {
		return "设备模块";
	} else if ("WMS" == val) {
		return "仓库模块";
	} else if ("QC" == val) {
		return "质量模块";
	} else if ("PC_SA" == val) {
		return "采购销售模块";
	}
}
function formatChartType(val, node) {
	if ("1" == val) {
		return "柱状图";
	} else if ("2" == val) {
		return "饼状图";
	} else {
		return "条形图";
	}
}
function formatResourceType(val, node) {
	if ("1" == val) {
		return "url链接";
	} else if ("2" == val) {
		return "table数据表格";
	} else {
		return "图表";
	}
}

function formatStatus(value, rowData, rowIndex) {
	if (value == "0") {
		return "新建";
	} else if (value == "1") {
		return "审核通过";
	} else if (value == "2") {
		return "弃审";
	} else if (value == "3") {
		return "关闭";
	} else {
		return value;
	}
}

/**
 * 格式化FORM类型
 * @param val
 * @param node
 * @returns {String}
 */
function formatFormType(val, node) {
	if("1" == val) {
		return "独立form";
	} else if("2" == val) {
		return "框架布局form";
	} else if("3" == val) {
		return "布局主form";
	} else if("4" == val) {
		return "布局子form";
	}
}
/**
 * 字段默认类型（无,常量,REQUEST）
 * @param val
 * @param node
 * @returns
 */
function formatFieldDefaultType(val, node) {
	if("0" == val) {
		return "无";
	} else if("1" == val) {
		return "常量";
	} else if("2" == val) {
		return "REQUEST";
	}
}
// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * 说明：日期时间dateTime通用操作JS * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */
// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/**
 * 获取系统当前日期
 * 
 * @returns {String}
 */
function getCurrentDate() {
	var date = new Date();
	return date.getFullYear() + "-" + (date.getMonth() + 1) + "-"
			+ date.getDate();
}
/**
 * 获取系统当前日期
 * 
 * @returns {String}
 */
function getCurrentDate4ZH(date) {
	if (isEmpty(date)) {
		date = new Date();
	}
	return date.getFullYear() + "年" + (date.getMonth() + 1) + "月"
			+ date.getDate() + '日';
}
/**
 * 获取系统当前日期
 * 
 * @returns {String}
 */
function getCurrentDateTime() {
	var date = new Date();
	return date.getFullYear() + "-" + Number(date.getMonth() + 1) + "-"
			+ date.getDate() + " " + date.getHours() + ":" + date.getMinutes()
			+ ":" + date.getSeconds();
}
/**
 * 获取系统当前日期
 * 
 * @returns {String}
 */
function getCurrentDateTimeZH() {
	var date = new Date();
	return date.getFullYear() + "年" + (date.getMonth() + 1) + "月"
			+ date.getDate() + '日 ' + date.getHours() + "时" + date.getMinutes()
			+ "分" + date.getSeconds() + "秒";
}

/**
 * 计算日期差 startDate：检测的日期 endDate：检测的日期 type :返回单位 YEAR:年/MONTH：月/DAY:日 return：
 * 差值
 */
function diffDate(startDate, endDate, type) {
	if (startDate != null && endDate != null) {
		var result;
		var startYear = startDate.getYear();
		var endYeay = endDate.getYear();
		switch (type) {
		case "DAY":
			startTime = startDate.getTime();
			endTime = endDate.getTime();
			result = Math.abs(startTime - endTime) / (1000 * 3600 * 24);
			break;
		case "MONTH":
			var statrMonth = startDate.getMonth();
			var endMonth = endDate.getMonth();
			var yearDiff = Math.abs(startYear - endYeay);
			result = yearDiff * 12 + (statrMonth - endMonth);
			break;
		case "YEAR":
			result = Math.abs(startYear - endYeay);
			break;
		}
		return result;
	} else {
		return null;
	}
}

/**
 * 计算时间差 startDate：检测的日期 endDate：检测的日期 type :返回单位 HOUR:时/MINUTE：分/SECOND: 秒
 * fixed:精确值 默认为2 return： 差值
 */
function diffTime(startDate, endDate, type, fixed) {
	// startDate = formatStrToDate(startDate);
	// endDate = formatStrToDate(endDate);
	if (startDate != null && endDate != null) {
		var result;
		startTime = startDate.getTime();
		endTime = endDate.getTime();
		switch (type) {
		case "HOUR":
			result = Math.abs(startTime - endTime) / (1000 * 60 * 60);
			break;
		case "MINUTE":
			result = Math.abs(startTime - endTime) / (1000 * 60);
			break;
		case "SECOND":
			result = Math.abs(startTime - endTime) / (1000);
			break;
		}
		if (!isEmpty(fixed)) {
			result.toFixed(fixed);
		}
		return result;
	} else {
		return null;
	}

}

/**
 * 计算时间日期差 startDate：检测的日期 endDate：检测的日期 type :返回单位 YEAR:年/MONTH：月/DAY:日
 * HOUR:时/MINUTE：分/SECOND: 秒 return： 差值
 */
function diffDatetime(startDate, endDate, type) {
	var result
	result = diffDate(startDate, endDate, type);
	if (result == null || result == undefined) {
		result = diffTime(startDate, endDate, type);
	}
	return result;
}

/**
 * 判断日期格式 2000-01-01 1:1:1 strDate：检测的日期格式 return： true/false
 */
function isDate(strDate) {
	var strSeparator = "-"; // 日期分隔符
	var strDateArray;
	var intYear;
	var intMonth;
	var intDay;
	var boolLeapYear;
	// var strDate=form1.a.value //表单中的日期值
	var strDateTimeArray;

	if (strDate.indexOf(" ") >= 0) {
		strDateTimeArray = strDate.split(" ");
		strDateArray = strDateTimeArray[0].split(strSeparator);
		strTimeArray = strDate.split(" ")[1].split(":");

		if (strTimeArray.length != 3) {
			euAlert('提示: 日期格式错误! \r\n  请依【YYYY-MM-DD HH:MM:SS】格式输入！');
			return false;
		}

		intHour = parseInt(strTimeArray[0], 10);
		intMinute = parseInt(strTimeArray[1], 10);
		intSecond = parseInt(strTimeArray[2], 10);

		if (intHour > 24 || intHour < 0) {
			euAlert('提示: 日期格式错误! \r\n  请依【YYYY-MM-DD HH:MM:SS】格式输入！');
			return false;
		}

		if (intMinute < 0 || intMinute > 60) {
			euAlert('提示: 日期格式错误! \r\n  请依【YYYY-MM-DD HH:MM:SS】格式输入！');
			return false;
		}

		if (intSecond < 0 || intSecond > 60) {
			euAlert('提示: 日期格式错误! \r\n  请依【YYYY-MM-DD HH:MM:SS】格式输入！');
			return false;
		}
	} else {
		alert(strDate.split("-"))
		strDateArray = strDate.split("-");
		alert(strDateArray.length);
	}

	if (strDateArray.length != 3) {
		euAlert('提示: 日期格式错误! \r\n  请依【YYYY-MM-DD】格式输入！');
		return false;
	}

	intYear = parseInt(strDateArray[0], 10);
	intMonth = parseInt(strDateArray[1], 10);
	intDay = parseInt(strDateArray[2], 10);

	if (isNaN(intYear) || isNaN(intMonth) || isNaN(intDay)) {
		euAlert('提示: 日期格式错误! \r\n  请依【YYYY-MM-DD】格式输入！');
		return false;
	}

	if (intMonth > 12 || intMonth < 1) {
		euAlert('提示: 日期格式错误! \r\n  请依【YYYY-MM-DD】格式输入！');
		return false;
	}

	if ((intMonth == 1 || intMonth == 3 || intMonth == 5 || intMonth == 7
			|| intMonth == 8 || intMonth == 10 || intMonth == 12)
			&& (intDay > 31 || intDay < 1)) {
		euAlert('提示: 日期格式错误! \r\n  请依【YYYY-MM-DD】格式输入！');
		return false;
	}

	if ((intMonth == 4 || intMonth == 6 || intMonth == 9 || intMonth == 11)
			&& (intDay > 30 || intDay < 1)) {
		euAlert('提示: 日期格式错误! \r\n  请依【YYYY-MM-DD】格式输入！');
		return false;
	}

	if (intMonth == 2) {
		if (intDay < 1) {
			euAlert('提示: 日期格式错误! \r\n 请依【YYYY-MM-DD】格式输入！');
			return false;
		}

		boolLeapYear = false;
		if ((intYear % 4 == 0 && intYear % 100 != 0) || (intYear % 400 == 0)) {
			boolLeapYear = true;
		}

		if (boolLeapYear) {
			if (intDay > 29) {
				euAlert('提示: 日期格式错误! \r\n  请依【YYYY-MM-DD】格式输入！');
				return false;
			}
		} else {
			if (intDay > 28) {
				euAlert('提示: 日期格式错误! \r\n  请依【YYYY-MM-DD】格式输入！');
				return false;
			}
		}
	}

	return true;
}

/**
 * 时间格式转换
 */
function formatStrToDate(datestr) {
	var time = Date.parse(datestr);
	var date = new Date(time);
	return date;
}

/**
 * 将date YYYY-MM-DD HH:SS:MM 格式转成字符串格式
 * 
 * @param date
 * @return
 */
function formatDateToStr(date) {
	if (isEmpty(date)) {
		date = new Date();
	}
	if (date.getHours() > 0 && date.getMinutes() > 0 && date.getSeconds() > 0)
		return date.getFullYear() + "-" + (date.getMonth() + 1) + "-"
				+ date.getDate() + " " + date.getHours() + ":"
				+ date.getMinutes() + ":" + date.getSeconds();
	else
		return date.getFullYear() + "-" + (date.getMonth() + 1) + "-"
				+ date.getDate();
}
/**
 * 
 * 判断时间大小 edate 是否 大于 sdate
 * 
 * sdate1: 开始时间 edate2: 结束时间 return: -1/0/1
 * 
 */
function compareToDate(sdate, edate) {
	sdate = formatStrToDate(sdate);
	edate = formatStrToDate(edate);

	if (sdate != null && edate != null) {
		if (sdate < edate) {
			return -1;
		} else if (sdate > edate) {
			return 1;
		} else {
			var l = (edate.getTime() - sdate.getTime());
			var value = l / 1000 / 60;
			return value;
		}
	}
}


/**
 * 执行方法反射
 */
function executeReflectFun() {
	//反射执行需要自动加载的事件
	if (pageFunctionArr!=null && pageFunctionArr.length>0) {
		//alert("execute function executeReflectFun() pageFunctionArr.length="+pageFunctionArr.length);
		for(var i = 0; i < pageFunctionArr.length; i++) { 
			reflectFun(pageFunctionArr[i].split(",")[0],pageFunctionArr[i].split(",")[1],pageFunctionArr[i].split(",")[2]);
		}
	}
	pageFunctionArr = null;
}

function formatUseArea(val, node) {
	if ("0" == val) {
		return "通用";
	} else if ("1" == val) {
		return "FORM编辑";
	} else if ("2" == val) {
		return "列表行编辑";
	} else {
		return "";
	}
}
