///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/**
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  * 
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  * 
 *  说明：easyui.treegrid控件通用操作JS
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  * 
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  * 
 */
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/**
 * 清空TreegridId列表
 * 
 * @param treegridId
 * @return
 */
function clearTreegrid(treegridId) {
	$("#" + treegridId).treegrid("clearAll");
}
/**
 * 专门用于查询刷新树形表单的提交操作
 * 
 * @param treegridId
 *            easyui的treegridId编号
 * @param formId
 *            表单编号
 * @param url
 */
function treegridFormSearch(treegridId, formId, url) {
	if ($("#" + formId).form("validate")) {
		var fields = $("#" + formId).serializeArray();
		$.each(fields, function(i, field) {
			if (field.value != null && field.value != '') {
				url += "&" + field.name + "="
						+ field.value.replace(/(^[\s]*)|([\s]*$)/g, "");
				;
			}
		});
		reloadTreegrid(treegridId, url);
	}
}

/**
 * 回调函数
 * 
 * @param result
 *            后台返回值
 * @param reloadGridId
 *            需要刷新的grid id
 * @param closeObj
 *            关闭的对象
 */
function retResultAdReloadTd4Call(result, reloadGridId, closeObj, reloadUrl) {
	showProcess(false);
	if (!isEmpty(result)) {

		if ("true" == result.split(',')[0]) {
			euShow("操作成功！");
			// 刷新grid
			if (!isEmpty(reloadGridId)) {
				reloadTreegrid(reloadGridId, reloadUrl);
			}
			// 关闭子窗口
			if (!isEmpty(closeObj)) {
				closeThisPopWindow();
			}
		} else {
			euShow("操作失败   原因:" + result, null, 10);
		}
	}
}
/**
 * Ajax提交url请求后，如果选中一个节点，那么对当前treegrid的这个节点执行刷新；否则全部刷新
 * 
 * @param url
 * @param treegridId
 * @param closeObj
 *            是否关闭
 * @param nodeId
 *            当前选中节点
 */
function ajaxPostAdReload2TgNode(url, treegridId, closeObj, nodeId) {
	showProcess(true, titleinfo, submitTitle);
	var resultArr = getJson4Url(url);
	$.post(resultArr[0], (resultArr[1].length > 0 ? evalObj(resultArr[1])
			: null), function(result) {
		if (!isEmpty(nodeId)) {
			retResultAdReloadTd4Call(result, null, closeObj);// 重加载数据定义到下行,
			// 2参数为null代表不执行公共的刷新
			reload2TgNode(treegridId, nodeId);
		} else {
			retResultAdReloadTd4Call(result, treegridId, closeObj);
		}
	});
}
/**
 * 使用Ajax的post方式获取数据 并自动刷新以及执行回调方法
 * 
 * @param url
 * @param treegridId
 * @param closeObj
 * @return
 */
function ajaxPostAdReloadCall2Tg(url, treegridId, closeObj, callback) {
	showProcess(true, titleinfo, submitTitle);
	var resultArr = getJson4Url(url);
	$.post(resultArr[0], (resultArr[1].length > 0 ? evalObj(resultArr[1])
			: null), function(result) {
		retResultAdReloadTd4Call(result, treegridId, closeObj);
		reflectFun(callback);
	});

}

/**
 * 获得树列表中，复选框选中的所有id字段，存放在数组中。返回数组。
 * 
 * @param treegridId
 * @param isGetParentId
 *            是否获取parentId字段 如果是 数组中存放的数据如：id值-parentId值
 * @returns {Array}
 */
function getCheckIdValue4Tg(treegridId, isGetParentId) {
	var ids = [];
	var nodes = $('#' + treegridId).tree('getChecked');
	for (var i = 0; i < nodes.length; i++) {
		if (isGetParentId != null && isGetParentId != undefined
				&& isGetParentId == true) {
			ids.push(nodes[i].id + "-" + nodes[i].parentId);
		} else {
			ids.push(nodes[i].id);
		}
	}
	return ids;
}

/**
 * 获得树列表中，复选框选中的所有id字段，存放在数组中。返回数组。
 * 
 * @param treegridId
 * @param isGetParentId
 *            是否获取parentId字段 如果是 数组中存放的数据如：id值-parentId值
 * @returns {Array}
 */
function getCheckIdValue4Tg(treegridId, isGetParentId) {
	var ids = [];
	var nodes = $('#' + treegridId).tree('getChecked');
	for (var i = 0; i < nodes.length; i++) {
		if (isGetParentId != null && isGetParentId != undefined
				&& isGetParentId == true) {
			ids.push(nodes[i].id + "-" + nodes[i].parentId);
		} else {
			ids.push(nodes[i].id);
		}
	}
	return ids;
}

/**
 * 判断是否选中树状列表复选框 执行callback
 * 
 * @param treeId
 *            树ID
 * @param callback
 *            回调方法
 */
function isCheckedRowAdCall2Tg(treegridId, callback) {
	var nodes = $("#" + treegridId).tree("getChecked");
	if (nodes == null || nodes == undefined || nodes.length <= 0) {
		euAlert(selectOneNode);
	} else {
		callback();
	}
}
/**
 * 判断当前树节点是否为叶子节点
 * 
 * @param treeId
 * @returns
 */
function isTreeLeafNode(node) {
	var isNodeLeaf = false;
	if (!isEmpty(node)) {
		isNodeLeaf = $("#").tree("isLeaf", node.target);
	}

	return isNodeLeaf;
}
/**
 * 判断当前树选中节点是否为叶子节点
 * 
 * @param treeId
 * @returns
 */
function isTreeLeaf(treegridId) {
	var node = $("#" + treegridId).tree("getSelected");
	var isNodeLeaf = false;
	if (!isEmpty(node)) {
		isNodeLeaf = $("#").tree("isLeaf", node.target);
	}

	return isNodeLeaf;
}

/**
 * 刷新treegrid
 * 
 * @param treegridId
 * @param url
 * @param currentNodeId
 *            当前选择的节点
 * @return
 */
function reloadTreegrid(treegridId, url, currentNodeId) {
	if (!isEmpty(treegridId)) {
		if (!isEmpty(url)) {
			$('#' + treegridId).treegrid('options').url = url;
		} else {
			if (isEmpty($("#" + treegridId).treegrid("options").url)) {
				$('#' + treegridId).treegrid('options').url = eval(treegridId + "URL");
			}
		}
		// 初始化对象
		if (isEmpty(relationObjParamMap)) {
			relationObjParamMap = new HashMap();
		}
		// 去掉《不合适采用这样的参数传值，会导致参数始终存在与relationObjParamMap参数中》
		if (!isEmpty(relationObjParamMap)
				&& !isEmpty(relationObjParamMap.get(treegridId + "NodeId"))) {
//			alert(1);
			var nodeId = relationObjParamMap.get(treegridId + "NodeId");
			// alert("nodeId======"+nodeId)
			$('#' + treegridId).treegrid('reload', nodeId);
			// $("#" + treegridId).treegrid("select", nodeId);
			// 删除当前选中值，不然出现选中值始终存在。
			relationObjParamMap.remove(treegridId + "NodeId");
		} else if (!isEmpty(currentNodeId)) {
			// alert("currentNodeId=" + currentNodeId);
			$('#' + treegridId).treegrid('reload', currentNodeId);
		} else {
//			alert(3);
			// 针对简易数据维护中，DIV之间的关联映射（如：树点击事件对多个tab对象传值）实现。
			if (!isEmpty(relationObjParamMap)
					&& !isEmpty(relationObjParamMap.containsKey(treegridId))) {
				//涉及到参数类型问题，只能采取urlTemp中间变量转换
				var urlTemp = $('#' + treegridId).treegrid('options').url;
				$('#' + treegridId).treegrid('options').url = urlTemp + str2UrlParams(relationObjParamMap.get(treegridId));
			}
			//alert("url="+$('#' + treegridId).treegrid('options').url);
			$('#' + treegridId).treegrid('reload');
		}
	}
}

/**
 * 重新加载列表数据
 * 
 * @param treegridId
 * @param params
 *            参数 funId:1,funName:2
 */
function reloadTreegridByParams(treegridId, paramStr) {
	if (paramStr != null || paramStr != undefined) {
		var params = $("#" + treegridId).treegrid("options").queryParams;
		var paramsArr = new Array();
		paramsArr = paramStr.split(",");
		for (i = 0; i < paramsArr.length; i++) {
			var paramArr = new Array();
			paramArr = paramsArr[i].split(":");
			params[paramArr[0]] = paramArr[1];
		}
	}
	reloadTreegrid(treegridId);
}
/**
 * 刷新treegrid，当selectNodeId不为空时，局部刷新树节点
 * 
 * @param treegridId
 * @param selectNodeId
 * @return
 */
function reload2TgNode(treegridId, selectNodeId) {
	if (!isEmpty(selectNodeId)) {
		reloadTreegrid(treegridId, "", selectNodeId);
		// $('#' + treegridId).treegrid('reload',selectNodeId);
		// treeGrid标签会自动选择这个节点
		// $("#" + treegridId).treegrid("select", selectNodeId);
	} else {
		// 为空时刷所有数据 Cz
		$('#' + treegridId).treegrid('reload');
	}
}
/**
 * 全部收拢，然后选中并展开selectNodeId节点
 * 
 * @param treegridId
 * @param selectNodeId
 *            需要选中并展开的节点
 */
function collapseAdExpand2Tg(treegridId, selectNodeId) {
	$("#" + treegridId).treegrid("collapseAll");
	if (!isEmpty(selectNodeId)) {
		// $("#" + treegridId).treegrid("collapse", selectNodeId);
		$("#" + treegridId).treegrid("expandTo", selectNodeId);
		$("#" + treegridId).treegrid("select", selectNodeId);
	}
}

/**
 * 如果selectNodeId不为空，则只展开到selectNodeId节点。否则，全部展开
 * 
 * @param treegridId
 * @param selectNodeId
 * @return
 */
function expand2TgNode(treegridId, selectNodeId) {
	if (selectNodeId != "" && selectNodeId != undefined) {

		$("#" + treegridId).treegrid('expand', selectNodeId);
		$("#" + treegridId).treegrid('select', selectNodeId);
	} else {
		$("#" + treegridId).treegrid("expandAll");
	}
}

/**
 * 选中
 * 
 * @param treegridId
 * @param selectNodeId
 * @return
 */
function selectTreegrid(treegridId, selectNodeId) {
	$("#" + treegridId).treegrid('select', selectNodeId);
}
