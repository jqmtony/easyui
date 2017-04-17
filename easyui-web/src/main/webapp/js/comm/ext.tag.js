/**
 * 
 */

 
/**
 * 判断是否选择并且确认执行callcak方法
 * @param 
 * 	componentId 组件ID
 * @param 
 * 	callback 回调方法
 * @param
 * 	confirmMsg 确认提示信息
 * 	
 */
function isSelectedExtRecordAdConfirm(componentId, callback, confirmMsg){
	isSelectedExtSingleRecord(componentId,function (){
		$.messager.confirm(titleinfo, confirmMsg, function(r) {
			if (r) {
				callback();
			}
		});
	});
}


/**
 * 判断是否选择唯一一条记录
 * @param id
 * @param callBack
 */
function isSelectedExtSingleRecord(id, callBack) {
	// 获取选择模型
	var thisSelectModel = Ext.getCmp(id).getSelectionModel();

	// 判断选择条数
	if (thisSelectModel && thisSelectModel.getCount() == 1) {
		callBack();
	} else {
		euAlert(selectOnlyRow);
	}
}


/**
 * 判断是否选择记录
 * @param id
 * @param callBack
 */
function isSelectedExtRecord(id, callBack) {
	// 获取选择模型
	var thisSelectModel = Ext.getCmp(id).getSelectionModel();

	// 判断选择条数
	if (thisSelectModel && thisSelectModel.getCount()>0) {
		callBack();
	} else {
		euAlert(selectOneRow);
	}
}


/**
 * 验证是否有选择记录，并且提示是否进行操作
 * 
 * @param componentId
 * @param callback
 * @confirmMsg 确认消息
 */
function isSelectedExtRecordAdConfirm(treePlanId, callback, confirmMsg) {
	isSelectedExtRecord(treePlanId,function(){
		$.messager.confirm(titleinfo, confirmMsg, function(r) {
			if (r) {
				callback();
			}
		});
	});
}



/**
 * 获取componentId单行记录参数值,返回字符串。 格式："&fieldName=" + fieldName
 * 
 * @param componentId
 *            数据列表
 * @param checkParamArr
 *            行记录中需要当参数的字段数组（支持多字段）
 */
function getExtSingleRecordParams(componentId, checkParamArr) {
	var params = "";
	var rows = Ext.getCmp(componentId).getSelectionModel().getSelection();

	for (var i = 0; i < checkParamArr.length; i++) {
		params += "&" + checkParamArr[i] + "="
				+ rows[i].get(checkParamArr[i]);
	}

	return params;
}


/**
 * 获取Ext选择行数据模型
 * @param componentId
 * @returns
 */
function getExtSelectionModel(componentId){
	var rows = Ext.getCmp(componentId).getSelectionModel().getSelection();
	return rows;
}

/**
 * 获取componentId选择的记录,多字段以-分开，返回数组。
 * 格式：["fieldName1-fieldName2","fieldName11-fieldName22"]
 * 
 * @param componentId
 *            名称
 * @param checkFieldArr
 *            行记录中需要当参数的字段数组（支持多字段）
 * @param split
 *            分隔符
 */
function getCheckFieldValue4Ext(componentId, checkFieldArr, split) {
	if (isEmpty(split)) {
		split = "-";
	}
	var ids = [];
	var rows = Ext.getCmp(componentId).getSelectionModel().getSelection();
	for (var i = 0; i < rows.length; i++) {
		var fieldNames = "";
		for (var j = 0; j < checkFieldArr.length; j++) {
			fieldNames += (isEmpty(rows[i].get(checkFieldArr[j]))) ? ""
					: rows[i].get(checkFieldArr[j])
							+ (j == checkFieldArr.length - 1 ? "" : split);
		}
		ids.push(fieldNames);
	}
	return ids;
}

 
/**
 * 刷新Ext.tree.PanelView 刷新成功执行选择对应的节点
 * @param id
 */ 
function reloadExtSelectNode(componentId,nodeValue,nodeFiled){
	// 查询字段默认为Id
	nodeFiled = isEmpty(nodeFiled) ? "id" :nodeFiled;
	// 刷新前获取选中行数据
	var selectRow=null;
	// 如果未指定行则取当前选择行作为刷新后选择行
	if(isEmpty(nodeValue)) {
		selectRow= Ext.getCmp(componentId).getSelectionModel().getLastSelected();
	} 
	
	Ext.getCmp(componentId).store.reload(
			{
				callback:function(data){
					//alert(data.findchild(nodeFiled,nodeValue));
					// 回调事件 
					if(!isEmpty(nodeValue)) {
						// 如果指定选中某个节点 这查找到当前节点
						selectRow=getExtStoreModel(componentId,nodeFiled,nodeValue);
					} 
					
					if(!isEmpty(selectRow)) {
						Ext.getCmp(componentId).selectPath(selectRow.getPath());
					}
			}
		});
}


/**
 * 获取Ext model
 * @param componentId
 * @param filed
 * @param value
 */
function getExtStoreModel(componentId,filed,value){
	// 此方法存在tree 子数据查找不到的BUG...
	var _model = null;
	if(!isEmpty(componentId) && !isEmpty(filed) && !isEmpty(value)) {
		_model=Ext.getCmp(componentId).store.findRecord(filed,value);
	}
	
	 
	/*alert("getExtStoreModel"+_model+'-'+filed+'-'+value);
	alert(Ext.getCmp(componentId).store.getById(value)),
	alert(Ext.getCmp(componentId).getSelectionModel().getLastSelected().get("id"));*/
	return _model;
}

 /**
  * 根据form表单刷新Ext.store
  * @param id
  * @param formId
  */
function formSearch4ExtStore(componentId,formId){
	var params=getFormParamJsonObj(formId);
	reloadExtStore4Params(componentId,params);
}

/**
 * 根据参数刷新Ext.store
 * @param id
 * @param formId
 */
function reloadExtStore4Params(componentId,params){
	Ext.getCmp(componentId).store.reload({
		params:params
	});
}



/**
 * ajax请求成功之后关闭并且刷新请求结果
 * @param componentId 	组件ID
 * @param url        	请求地址
 * @param closeObj   	关闭对象
 * @param nodeValue 	nodel值
 * @param nodeFiled		nodel对应的字段
 */
function ajaxPostAdReload2ExtNode(componentId,url,closeObj,nodeValue,nodeFiled){
	showProcess(true);
	var resultArr = getJson4Url(url);
	 
	$.post(resultArr[0], (resultArr[1].length > 0 ? evalObj(resultArr[1])
			: null), function(result) {
		showProcess(false);
		if("true"==result) {
			reloadExtSelectNode(componentId,nodeValue,nodeFiled);
		} else {
			euShow("执行失败,失败原因:"+result);
		}
		// 关闭子窗口
		if (!isEmpty(closeObj)) {
			closeThisPopWindow();
		}		
	});
}


/**
 * 获取布局容器的高度，treegrid设置高度时使用
 */
function getLayoutHeight(){
	var layoutHeight = $("div[class='panel-body panel-body-noheader layout-body']:last").css("height");
	return layoutHeight;
}