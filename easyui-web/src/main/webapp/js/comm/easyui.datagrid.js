///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/**
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  * 
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  * 
 *  说明：easyui.datagrid控件通用操作JS
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  * 
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  * 
 */
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/**
 * 验证是否只选择了一条记录
 * 
 * @param datagridId
 * @param callback
 */
function isSelectedSingleRecord(datagridId, callback) {
	var rows = $("#" + datagridId).datagrid("getSelections");
	if (rows.length == 1) {
		callback();
	} else {
		euAlert(selectOnlyRow);
	}
}

/**
 * 验证是否有选择记录
 * 
 * @param datagridId
 * @param callback
 */
function isSelectedRecord(datagridId, callback) {
	var rowData = $("#" + datagridId).datagrid("getSelected");
	if (rowData) {
		callback(rowData);
	} else {
		euAlert(selectOneRow);
	}
}

/**
 * 验证是否有选择记录，并且提示是否进行操作
 * 
 * @param datagridId
 * @param callback
 * @confirmMsg 确认消息
 */
function isSelectedRecordAdConfirm(datagridId, callback, confirmMsg) {
	var rowData = $("#" + datagridId).datagrid("getSelected");
	if (rowData) {
		$.messager.confirm(titleinfo, confirmMsg, function(r) {
			if (r) {
				callback(rowData);
			}
		});
	} else {
		euAlert(selectOneRow);
	}
}
/**
 * 检查名称为name的属性值为value是否已在datagrid中存在
 * @param datagrid
 * @param name
 * @param value
 * @param errorMsg
 * @return
 */
function isExistInDatagridList(datagrid,name,value,errorMsg) {
	
	var gridRows = $("#" + datagrid).datagrid("getRows");
	for(var i=0;i<gridRows.length;i++){
		if (gridRows[i][name]== value) {
			if (errorMsg ==null) {
				errorMsg = "common.alert.datagrid.exist.current.data";
			}
			euShow("<spring:message code='"+errorMsg+"'/>");
			return ;
		}
	}
}
/**
 * 根据target对象获取datagrid中组件的处于的行数
 * @param target
 * @returns
 */
function getRowIndex4DgTarget(target){
	return parseInt($(target).closest('tr.datagrid-row').attr('datagrid-row-index'));
}



/**
 * 字段改变事件绑定
 */
 function getDGEditorValue(e){
	 var value="";
	 //alert($(e.target).getValue());
	  var editorType=e.type;//编辑类型
	  switch (editorType) {
		case "textbox":
			value=$(e.target).textbox("getValue");
			break;
		case "select":
			value=$(e.target).val();
			break;
		case "numberbox":
			value=(e.target).numberbox('getValue');
			break;
		case "timespinner":
			value=(e.target).timespinner('getValue');
			break;	
		case "dateboxgrid":
			value=$(e.target).find(".combo-text").first().val();
			break;
		case "datebox":
			value=(e.target).datebox('getValue');
			break;
		case "searchInput":
			 value=$(e.target).find(".searchbox-text").first().val();
			break;
		case "checkbox":
			value=(e.target).val();
			break;
		default:
			value=$(e.target).val();
			break;
	}
	  return value;
 }
 
/**
 * 获取行数据的行号
 * 
 * @param datagridId
 * @param row
 */
function getRowIndex4DgRow(datagridId, row) {
	return $("#" + datagridId).datagrid("getRowIndex", row);
}

/**
 * 获取datagrid选中行的序号
 */
function getRowIndex4DgSelectRow(datagridId) {
	var row = $("#" + datagridId).datagrid('getSelected');
	return $("#" + datagridId).datagrid('getRowIndex', row);
}

/**
 * 获取datagrid选中行的序号
 */
function getRowData4DgSelectRow(datagridId) {
	var row = $("#" + datagridId).datagrid('getSelected');
	return row;
}

/**
 * 根据行号获取行数据
 * @param datagridId
 * @param rowIndex
 * @param ignoreEd 是否忽略编辑
 * @returns
 */
function getRowData4RowIndex(datagridId, rowIndex,ignoreEd){
	 
	var rowDatas=$("#" + datagridId).datagrid("getRows");
	if(rowDatas.length>rowIndex) {
		var rowData=rowDatas[rowIndex];
		if(!isEmpty(rowData) &&(isEmpty(ignoreEd)||ignoreEd==false)) {
			// 获取编辑中的数据
			var eds=$("#" + datagridId).datagrid("getEditors",rowIndex);
			if(!isEmpty(eds) &&eds.length>0) {
				for(var i=0;i<eds.length;i++) {
				  var value=getDGEditorValue(eds[i]);
				   rowData[eds[i].field]=value;
				  //alert(edRowData[i].field+":"+edRowData[i].type+":"+rowData[edRowData[i].field]);
				}
			}
		}
		return rowData;
	} else {
		return null;
	}
}


/**
 * 判断某行数据是否属于新增数据
 * @param datagridId
 * @param rowIndex
 * @returns {Boolean}
 */
function fieldIsInsert4Dg(datagridId,rowIndex){
	var isInsert=false;
	var rowdata=getRowData4RowIndex(datagridId,rowIndex,true);
	if(!isEmpty(rowdata)) {
		var insertRows=$("#"+datagridId).datagrid("getChanges","inserted");
		for(var i=0;i<insertRows.length;i++) {
			var currentRow=insertRows[i];
			if(currentRow==rowdata){
				isInsert=true;
				break;
			}
		}
	}
	return isInsert;
}

/**
 * 选中datagrid行
 * 
 * @param datagridId
 * @param rowIndex
 */
function selectRowByDg(datagridId, rowIndex) {
	$("#" + datagridId).datagrid("selectRow", rowIndex);
}

/**
 * 结束行验证
 * @param datagridId
 * @param rowIndex
 * @param showWarninfo	验证失败后，是否需要显示错误消息。如：信息不完整等
 * @returns {Boolean}
 */
function validateRow2Dg(datagridId, rowIndex, showWarninfo){
	if ($("#" + datagridId).datagrid('validateRow', rowIndex)) {
		return true;
	} else {
		if (!isEmpty(showWarninfo) && isBoolean(showWarninfo) && showWarninfo) {
    		euShow(endEditWarninfo);
		}
	}
}

/**
 * 完成所有行编辑
 * @param datagridId
 * @param showWarninfo	验证失败后，是否需要显示错误消息。如：信息不完整等
 * @return
 */
function validateAllRows2Dg(datagridId, showWarninfo){
	
	var flag=true;
    var rows = $("#" + datagridId).datagrid('getRows');
    for ( var i = 0; i < rows.length; i++) {
    	flag= validateRow2Dg(datagridId, i, showWarninfo);
    	if(!flag) {
        	break;
    	}
    }
    return flag;
}

/**
 * 取消DataGrid中的行选择
 * 解决了无法取消"全选checkbox"的选择,不过，前提是必须将列表展示数据的DataGrid所依赖的Table放入html文档的最全面，至少该table前没有其他checkbox组件。
 * 
 * @param datagridId
 *            将要取消所选数据记录的目标grid列表id
 * @return
 */
function clearSelect2Dg(datagridId) {
	$("#" + datagridId).datagrid("clearSelections");
	// 取消选择DataGrid中的全选
	$("input[type='checkbox']").eq(0).prop("checked", false);
}

/**
 * 清空datagridId列表
 * 
 * @param datagridId
 * @return
 */
function clearDatagrid(datagridId) {
	$("#" + datagridId).datagrid("clearAll");
}
/**
 * 获取datagrid所有记录中，字段为fieldName的最大值
 * 
 * @param dataGridId
 * @param filedName
 *            如：orderNum
 */
function getFieldMaxValue4DgRows(datagridId, filedName) {
	var tempMaxValue = 0;
	var rows = $("#" + datagridId).datagrid("getRows");

	for (var i = 0; i < rows.length; i++) {
		tempMaxValue = parseFloat(eval("rows[i]." + filedName)) > tempMaxValue ? parseFloat(eval("rows[i]."
				+ filedName))
				: tempMaxValue;
	}
	return tempMaxValue;
}

/**
 * 获取datagrid 序号或顺序号等字段的下一个递增值。 dataGridId:数据来源的数据数据列表 filedName:来源的数据列表
 * 序号字段（index/rowNo） incrementNum:权量 （每次递增的数量 如：1/10等） 默认为1
 * currentMaxValue:当前最大值 （当前列表中序号字段的最大值)
 * 
 */
function getFieldNextValue4DgRows(datagridId, filedName, incrementNum,
		currentMaxValue) {
	var tempMaxValue = 0;
	if (isEmpty(currentMaxValue)) {
		tempMaxValue = getMaxValue4DgFieldName(datagridId, filedName);
	} else {
		tempMaxValue = currentMaxValue;
	}
	currentMaxValue = tempMaxValue
			+ (isEmpty(incrementNum) ? 1 : parseFloat(incrementNum));

	return currentMaxValue;
}


/**
 * 获取datagrid 字段最大数值
 * @param datagridId
 * @param filedName
 * @returns {Number}
 */
function getMaxValue4DgFieldName(datagridId,filedName){
	var currentMaxNum=0;
	var rows = $("#"+datagridId).datagrid("getRows");
	for(var i=0;i<rows.length;i++){
		currentMaxNum=parseFloat(eval("rows[i]."+filedName))>currentMaxNum?parseFloat(eval("rows[i]."+filedName)):currentMaxNum;
	}
	return currentMaxNum;
}

/**
 * 专门用于查询刷新表单的提交操作
 * 
 * @param datagridId
 *            easyui的datagrid编号
 * @param formId
 *            表单编号
 */
function datagridFormSearch(datagridId, formId, url) {
	if ($("#" + formId).form("validate")) {
		var params = $("#" + datagridId).datagrid("options").queryParams;
		var fields = $("#" + formId).serializeArray();
		var paramsStr = "";
		$.each(fields, function(i, field) {
			params[field.name] = field.value.replace(/(^[\s]*)|([\s]*$)/g, "");
			// alert(field.name +"=" + field.value.replace(/(^[\s]*)|([\s]*$)/g,
			// ""));
		});
		reloadDatagrid(datagridId, url);
	}
}

/**
 * 使用Ajax的post方式获取数据 并自动刷新
 * 
 * @param url
 * @param reloadGridId
 * @param closeObj
 * @param reloadUrl
 *            数据列表刷新Url,追加原因:当datagrid url会人为变化的时候此时刷新数据列表datagrid url会取最开始值
 * @return
 */
function ajaxPostAndReloadDatagrid(url, reloadGridId, closeObj, reloadUrl) {
	showProcess(true, titleinfo, submitTitle);
	var resultArr = getJson4Url(url);
	$.post(resultArr[0], (resultArr[1].length > 0 ? evalObj(resultArr[1])
			: null), function(result) {
		retResultAdReload4Call(result, reloadGridId, closeObj, reloadUrl);
	});
}

// ajax提交时参数在url后,解决上面的方法不能提交数组问题()
function ajaxGetAndReloadDatagrid(url, reloadGridId, closeObj, reloadUrl) {
	showProcess(true, titleinfo, submitTitle);
	$.post(url, null, function(result) {
		retResultAdReload4Call(result, reloadGridId, closeObj, reloadUrl);
	});
}

/**
 * 使用Ajax的post方式获取数据 并自动刷新
 * 
 * @param url
 * @param reloadGridId
 * @param closeObj
 * @return
 */
function ajaxPostAndReloadParent(url, reloadGridId, closeObj) {
	var resultArr = getJson4Url(url);
	$.post(resultArr[0], (resultArr[1].length > 0 ? evalObj(resultArr[1])
			: null), function(result) {
		showProcess(true, titleinfo, submitTitle);
		retResultAdReload4Call(result, reloadGridId, closeObj);
	});
	parent.document.location.reload(); // 等同按F5刷新
	// window.location.href //当前菜单重新加载
}

/**
 * 获取grid选择的记录,多字段以-分开，返回数组。
 * 格式：["fieldName1-fieldName2","fieldName11-fieldName22"]
 * 
 * @param grid
 *            名称
 * @param checkFieldArr
 *            行记录中需要当参数的字段数组（支持多字段）
 * @param split
 *            分隔符
 */
function getCheckFieldValue4Dg(datagridId, checkFieldArr, split) {
	if (isEmpty(split)) {
		split = "-";
	}
	var ids = [];
	var rows = $("#" + datagridId).datagrid("getSelections");
	for (var i = 0; i < rows.length; i++) {
		var fieldNames = "";
		for (var j = 0; j < checkFieldArr.length; j++) {
			fieldNames += (isEmpty(rows[i][checkFieldArr[j]])) ? ""
					: rows[i][checkFieldArr[j]]
							+ (j == checkFieldArr.length - 1 ? "" : split);
		}
		ids.push(fieldNames);
	}
	return ids;
}

/**
 * 把grid选择的记录行中字段用-分开，多条记录之间用,分割，返回字符串。
 * 
 * @param grid
 * @param checkFieldArr
 * @param split -
 * @param splitOut ,
 * @returns {String}
 */
function getDatagridAllRowsStr(grid, checkFieldArr, split, splitOut) {
	if (isEmpty(split)) {
		split = "-";
	}
	if (isEmpty(splitOut)) {
		splitOut = ",";
	}
	var ids = "";
	var rows = $("#" + grid).datagrid("getRows");
	for (var i = 0; i < rows.length; i++) {
		var fieldNames = "";
		for (var j = 0; j < checkFieldArr.length; j++) {
			fieldNames += rows[i][checkFieldArr[j]]
					+ (j == checkFieldArr.length - 1 ? "" : split);
		}

		ids += (isEmpty(ids) ? fieldNames : splitOut + fieldNames);
	}
	return ids;
}

/**
 * 获取grid单行记录所有参数值,返回字符串。 格式："&fieldName=" + fieldName
 * 
 * @param grid
 *            名称
 */
function getSingleRecordAllParams(grid) {
	var params = "";
	var fields = $("#" + grid).datagrid('getColumnFields');
	var rows = $("#" + grid).datagrid("getSelected");
	for (var i = 0; i < fields.length; i++) {
		params += "&" + fields[i] + "=" + rows[fields[i]];
	}

	return params;
}

/**
 * 获取grid单行记录参数值,返回字符串。 格式："&fieldName=" + fieldName
 * 
 * @param grid
 *            名称
 * @param checkParamArr
 *            行记录中需要当参数的字段数组（支持多字段）
 */
function getSingleRecordParams(grid, checkParamArr) {
	var params = "";
	var rows = $("#" + grid).datagrid("getSelected");
	for (var i = 0; i < checkParamArr.length; i++) {
		params += "&" + checkParamArr[i] + "=" + rows[checkParamArr[i]];
	}

	return params;
}

/**
 * 获取grid单行记录参数值,返回字符串。
 * 
 * @param grid
 *            名称
 * @param checkParamArr
 *            行记录中需要当参数的字段数组（支持多字段）
 */
function getSingleRecordFieldValue(grid, fieldName) {
	var row = $("#" + grid).datagrid("getSelected");
	return row[fieldName];
}

/**
 * 以下方式为从单选记录中获取FORM load数据
 * 
 * @param grid
 *            名称
 * @param selectParamArr
 *            行记录中需要当参数的字段数组（支持多字段）
 */
function getLoadFormFromSingleRecord(grid, selectParamArr) {
	var params = "{";
	var row = $("#" + grid).datagrid("getSelected");
	for (var i = 0; i < selectParamArr.length; i++) {
		params += selectParamArr[i] + ":'" + row[selectParamArr[i]] + "',";
	}
	params += "}";

	return params;
}

/**
 * 自动选中datagrid列表，通过列表中的主键 [1,4,5]
 * 
 * @param datagridId
 * @param pks
 * @return
 */
function setDatagridAutoSelectByPks(datagridId, pks) {
	for (var int = 0; int < pks.length; int++) {
		$("#" + datagridId).datagrid("selectRecord", pks[int]);
	}
}

/**
 * @param datagridId
 * @param refColArr
 *            合并参考列数组，及这些列都相等则合并rowFildNames指定的列
 * @param fieldNameArr
 *            和并列的field属性值及要合并的列数组
 */
function mergeColCells2Dg(datagridId, refColArr, fieldNameArr) {
	var grid = $("#" + datagridId);
	var rows = grid.datagrid('getRows');
	var flag = false;
	var startIndex = 0;
	var endIndex = 0;
	if (rows.length < 1) {
		return;
	}
	$.each(rows, function(i, row) {
		$.each(refColArr, function(j, refCol) {
			if (row[refCol] != rows[startIndex][refCol]) {
				flag = false;
				return false;
			} else {
				flag = true;
			}
		});
		// if(row[rowFildName]==rows[startIndex][rowFildName])
		if (flag) {
			endIndex = i;
		} else {
			$.each(fieldNameArr, function(k, rowFildName) {
				grid.datagrid('mergeCells', {
					index : startIndex,
					field : rowFildName,
					rowspan : endIndex - startIndex + 1,
					colspan : null
				});
			});

			startIndex = i;
			endIndex = i;
		}
	});
	$.each(fieldNameArr, function(k, rowFildName) {
		grid.datagrid('mergeCells', {
			index : startIndex,
			field : rowFildName,
			rowspan : endIndex - startIndex + 1,
			colspan : null
		});
	});
}

/**
 * 过滤掉（删除掉）已存在于datagrid中的selectRows记录
 * 
 * @param datagridId
 * @param datagridField
 * @param selectRows
 * @param rowField
 */
function filterRowsExistInDg(datagridId, datagridField, selectRows, rowField) {
	var tempRows = [];
	var allRows = $("#" + datagridId).datagrid("getRows");
	
	for (var i = 0; i < selectRows.length; i++) {
		if (!rowIsExistDg(allRows, datagridField, selectRows[i], rowField)) {
			tempRows.push(selectRows[i]);
		}
	}
	return tempRows;
}
/**
 * 新插入的行数据，是否存在于datagrid已有数据中。
 * 
 * @param datagridId
 *            插入的datagird
 * @param datagridField
 *            插入的datagrid需要判断是否存在的字段。多字段用道号隔开
 * @param rowData
 *            新插入的行数据
 * @param rowField
 *            新插入的行数据对应到datagrid的映射字段。多字段用道号隔开
 * @returns {Boolean}
 */
function rowDataIsExistDg(datagridId, datagridField, rowData, rowField) {
	var allRows = $("#" + datagridId).datagrid("getRows");
	return rowIsExistDg(allRows, datagridField, rowData, rowField);
}

/**
 * 新插入的行数据，是否存在于datagrid已有数据中。
 * 
 * @param datagridRows
 *            已有datagird的所有数据列表
 * @param datagridField
 *            插入的datagrid需要判断是否存在的字段。多字段用逗号隔开
 * @param rowData
 *            新插入的行数据
 * @param rowField
 *            新插入的行数据对应到datagrid的映射字段。多字段用逗号隔开
 * @returns {Boolean}
 */
function rowIsExistDg(datagridRows, datagridField, rowData, rowField) {
	var isExist = false;
	var isMulti = false;
	var datagridFieldArr, rowFieldArr;
	if (datagridField.indexOf(",") >= 0
			&& rowField.indexOf(",") >= 0
			&& getSplitCount(datagridField, ",") == getSplitCount(rowField, ",")) {
		isMulti = true;
		datagridFieldArr = datagridField.split(",");
		rowFieldArr = rowField.split(",");
	}
	for (var i = 0; i < datagridRows.length; i++) {
		if (isMulti) {
			var tempIsExist = false;
			var temp = false;
			for (var j = 0; j < datagridFieldArr.length; j++) {
				if (eval("rowData." + rowField[j]) == eval("datagridRows[i]."
						+ datagridField[j])) {
					tempIsExist = true;
				} else {
					tempIsExist = false;
					break;
				}
			}
			if (tempIsExist) {
				isExist = true;
				break;
			}
		} else if (eval("rowData." + rowField) == eval("datagridRows[i]."
				+ datagridField)) {
			isExist = true;
			break;
		}
	}
	return isExist;
}
/**
 * 插入一行记录到datagrid
 * 
 * @param datagridId
 * @param rowData
 *            行对象
 */
function insertRow2Dg(datagridId, rowData) {
	if (!isEmpty(rowData)) {
		$("#" + datagridId).datagrid("insertRow", {
			index : 0,
			row : rowData
		});

	}
}
/**
 * 注意：开启编辑速度会很慢，慎重选择 插入一行记录到datagrid
 * 
 * @param datagridId
 * @param rowData
 *            行对象
 * @param isOpenEdit
 *            是否开启行编辑（默认为:是）
 */
function insertRowAdEdit2Dg(datagridId, rowData, isOpenEdit) {
	if (!isEmpty(rowData)) {
		$("#" + datagridId).datagrid("insertRow", {
			index : 0,
			row : rowData
		});
		if (isEmpty(isOpenEdit) || (isBoolean(isOpenEdit) && isOpenEdit)) {
			beginEditRow2Dg(datagridId,0);
		}
		return 0;
	} else {
		$("#" + datagridId).datagrid("insertRow", {
			index : 0,
			row : {
				"rowIndexDefault" : randomNum()
			}
		});
		if (isEmpty(isOpenEdit) || (isBoolean(isOpenEdit) && isOpenEdit)) {
			beginEditRow2Dg(datagridId,0);
		}
		return 0;
	}
}

/**
 * 插入一行记录到datagrid
 * 
 * @param datagridId
 * @param rowData
 *            行对象
 */
function appendRow2Dg(datagridId, rowData) {
	if (!isEmpty(rowData)) {
		$("#" + datagridId).datagrid("appendRow", rowData);
	}
}
/**
 * 插入一行记录到datagrid
 * 
 * @param datagridId
 * @param rowData
 *            行对象
 * @param rowIndex
 *            行序号
 */
function appendRowAdEdit2Dg(datagridId, rowData, rowIndex) {
	if (!isEmpty(rowData)) {
		$("#" + datagridId).datagrid("appendRow", rowData);
		beginEditRow2Dg(datagridId, rowIndex);
	}
}
/**
 * 更新行纪录
 * 
 * @param datagridId
 * @param rowIndex
 * @param rowData
 */
function updateRow2Dg(datagridId, rowIndex, rowData) {
	if (!isEmpty(rowData)) {
		$("#" + datagridId).datagrid("updateRow", {
			index : rowIndex,
			row : rowData
		});
	}
}
/**
 * 更新行记录后，并开启编辑
 * 
 * @param datagridId
 * @param rowIndex
 * @param rowData
 */
function updateRowAdEdit2Dg(datagridId, rowIndex, rowData) {
	if (!isEmpty(rowData)) {

		//$("#" + datagridId).datagrid("endEdit", rowIndex);
		//cz: easyUi1.4 如果不endEdit 处于beginEdit状态时候执行Update 再开启编辑将无法再开启编辑的BUG
		//   easyUi1.4验证不通过无法接收编辑 此处应该忽略验证？
		endValidateEdit(datagridId, rowIndex)
		$("#" + datagridId).datagrid("updateRow", {
			index : rowIndex,
			row : rowData
		});
		beginEditRow2Dg(datagridId, rowIndex);
	}
}
/**
 * 开启行编辑datagrid
 * 
 * @param datagridId
 * @param rowIndex
 */
function beginEditRow2Dg(datagridId, rowIndex) {
	$("#" + datagridId).datagrid("beginEdit", rowIndex);
}

/**
 * 开始所有行编辑
 * 
 * @param datagridId
 * @return
 */
function beginEditAllRows(datagridId) {
	var rows = $("#" + datagridId).datagrid('getRows');
	for (var i = 0; i < rows.length; i++) {
		beginEditRow2Dg(datagridId, i);
	}
}

/**
 * 完成行编辑
 * 
 * @param datagridId
 * @return
 */
function endEditRow2Dg(datagridId, rowIndex, showWarninfo) {
	if (validateRow2Dg(datagridId, rowIndex, showWarninfo)) {
		$("#" + datagridId).datagrid('endEdit', rowIndex);
		return true;
	} else {
		return false;
	}
}
/**
 * 结束所有行编辑
 * 
 * @param datagridId
 * @param showWarninfo
 *            是否显示警告信息 true/false
 * @return
 */
function endEditAllRows(datagridId, showWarninfo) {
	var rows = $("#" + datagridId).datagrid('getRows');
	for (var i = 0; i < rows.length; i++) {
		//alert(validateRow2Dg(datagridId, i, showWarninfo));
		if (validateRow2Dg(datagridId, i, showWarninfo)) {
			$("#" + datagridId).datagrid('endEdit', i);
		} else {
			return false;
		}
	}
	return true;
}


/**
*
* 去除验证结束结束编辑行:
* 此方法用于解决Easy Ui 1.4.3版本中只允许行验证通过才能结束编辑
*/
function endValidateEdit(datagridId, rowIndex){
	 if(!isEmpty(datagridId) &&rowIndex>=0) {
		 // 去除验证
		 disableEditorValidate(datagridId, rowIndex);
		 // 结束编辑
		 $("#" + datagridId).datagrid('endEdit', rowIndex);
	 }
}

/**
 * datagrid去除编辑单元格验证功能
 * @param datagridId
 * @param rowIndex
 * @returns
 */
function disableEditorValidate(datagridId, rowIndex){
	// 获取所有编辑单元格
	var eds=$("#" + datagridId).datagrid("getEditors",rowIndex);
	for(var i=0;i<eds.length;i++) {
		switch(eds[i].type) {
			case "validatebox":
				if(!$(eds[i].target).validatebox('isValid')) {
			 		 $(eds[i].target).validatebox('disableValidation');
			 	  }
			break;
			case "textbox":
				 if(!$(eds[i].target).textbox('isValid')) {
			 		 $(eds[i].target).textbox('disableValidation');
			 	   }
			break;
			case "numberbox":
				 if(!$(eds[i].target).numberbox('isValid')) {
			 		 $(eds[i].target).numberbox('disableValidation');
			 	   }
			break;
		}
	}
}

/**
 * datagrid去除编辑单元格验证功能
 * @param datagridId
 * @param rowIndex
 * @returns
 */
function enableEditorValidate(datagridId, rowIndex){
	// 获取所有编辑单元格
	var eds=$("#" + datagridId).datagrid("getEditors",rowIndex);
	for(var i=0;i<eds.length;i++) {
		switch(eds[i].type) {
			case "validatebox":
				 $(eds[i].target).validatebox('enableValidation');
			break;
			case "textbox":
				 
			 		 $(eds[i].target).textbox('enableValidation');
			 	   
			break;
			case "numberbox":
				  $(eds[i].target).numberbox('enableValidation');
			break;
		}
	}
}

/**
 * 取消行编辑
 * 
 * @param datagridId
 * @return
 */
function cancelEditRow2Dg(datagridId, rowIndex) {
	$("#" + datagridId).datagrid('cancelEdit', rowIndex);
}
/**
 * 取消所有行编辑
 * 
 * @param datagridId
 * @return
 */
function cancelEditAllRows(datagridId) {
	var rows = $("#" + datagridId).datagrid('getRows');
	for (var i = 0; i < rows.length; i++) {
		$("#" + datagridId).datagrid('cancelEdit', i);
	}
}

/**
 * 删除dataGrid指定行节点
 * 
 * @param datagridId
 * @param row
 *            行对象
 */
function deleteRow2Dg(datagridId, row) {
	for (var i = 0; i < rows.length; i++) {
		$("#" + datagridId).datagrid("deleteRow",
				getRowIndex4DgRow(datagridId, row));
	}
}
/**
 * 删除dataGrid选中的所有行
 * 
 * @param datagridId
 */
function deleteSelectRows2Dg(datagridId) {
	var rows = $("#" + datagridId).datagrid("getSelections");
	for (var i = 0; i < rows.length; i++) {
		$("#" + datagridId).datagrid("deleteRow",
				getRowIndex4DgRow(datagridId, rows[i]));
	}
}
/**
 * 判断是否datagrid是否有变动的数据,false无更改, ture有更改
 * 
 * @param datagridId
 * @param idFiled
 *            行主键；默认为id 作用：某些行中含有searchInput,
 *            当弹框callback后，datagrid的updated中无法获取这条记录。这能通过editStatus='update'来获取
 * @return
 */
function isChange2Dg(datagridId, idFiled) {
	var inserted = $("#" + datagridId).datagrid("getChanges", "inserted");
	var deleted = $("#" + datagridId).datagrid("getChanges", "deleted");
	var updated = $("#" + datagridId).datagrid("getChanges", "updated");

	if (isEmpty(idFiled)) {
		idFiled = "id";
	}

	if (inserted != '' || deleted != '' || updated != '') {
		return true;
	} else {
		for (var i = 0; i < alldata.length; i++) {
			var alldata = $("#" + datagridId).datagrid('getRows');
			var isExist = false;
			if (!isEmpty(eval("alldata[i]." + idFiled))
					&& alldata[i].editStatus == 'update') {
				return true;
			}
		}
		return false;
	}
}
/**
 * 获取datagrid所有修改的行数据
 * 
 * @param datagridId
 * @param idFiled
 *            行主键；默认为id 作用：某些行中含有searchInput,
 *            当弹框callback后，datagrid的updated中无法获取这条记录。这能通过editStatus='update'来获取
 * @return
 */
function getChangeRows2Dg(datagridId, idFiled) {
	if (isEmpty(idFiled)) {
		idFiled = "id";
	}
	var inserted = $("#" + datagridId).datagrid("getChanges", "inserted");
	var deleted = $("#" + datagridId).datagrid("getChanges", "deleted");
	var updated = $("#" + datagridId).datagrid("getChanges", "updated");
	var alldata = $("#" + datagridId).datagrid('getRows');

	for (var i = 0; i < alldata.length; i++) {
		var isExist = false;
		if (!isEmpty(eval("alldata[i]." + idFiled))
				&& alldata[i].editStatus == 'update') {
			var isExist = false;
			for (var j = 0; j < updated.length; j++) {
				if (eval("updated[j]." + idFiled) == eval("alldata[i]."
						+ idFiled)) {
					isExist = true;
					break;
				}
			}
			if (!isExist) {
				updated.push(alldata[i]);
			}
		}
	}

	var effectRow = new Object();
	
	if (inserted.length > 0) {
		effectRow["inserted"] = json2Str(inserted);
	}
	if (deleted.length > 0) {
		effectRow["deleted"] = json2Str(deleted);
	}
	if (updated.length > 0) {
		effectRow["updated"] = json2Str(updated);
	}
	if (alldata.length > 0) {
		effectRow["datalist"] = json2Str(alldata);
	}

	return effectRow;

}
/**
 * 第二个版本：此方法与上面方法的优劣性有待验证，在有结果前，先采用上面方法进行获取。 判断是否datagrid是否有变动的数据,false无更改,
 * ture有更改
 * 
 * @param datagridId
 * @param idFiled
 *            行主键；默认为id 作用：某些行中含有searchInput,
 *            当弹框callback后，datagrid的updated中无法获取这条记录。这能通过editStatus='update'来获取
 * @return
 */
function getChangeRows2DgV2(datagridId, idFiled) {
	if (isEmpty(idFiled)) {
		idFiled = "id";
	}
	var inserted = $("#" + datagridId).datagrid("getChanges", "inserted");
	var deleted = $("#" + datagridId).datagrid("getChanges", "deleted");
	var updated = $("#" + datagridId).datagrid("getChanges", "updated");
	var alldata = $("#" + datagridId).datagrid('getRows');
	// 过滤alldata中的inserted记录
	alldata = filterExistList4OthList(inserted, alldata, idFiled);
	alldata = filterExistList4OthList(updated, alldata, idFiled);

	for (var i = 0; i < alldata.length; i++) {
		updated.push(alldata[i]);
	}

	var effectRow = new Object();
	
	if (inserted.length > 0) {
		effectRow["inserted"] = json2Str(inserted);
	}
	if (deleted.length > 0) {
		effectRow["deleted"] = json2Str(deleted);
	}
	if (updated.length > 0) {
		effectRow["updated"] = json2Str(updated);
	}
	if (alldata.length > 0) {
		effectRow["datalist"] = json2Str(alldata);
	}
	return effectRow;

}

/**
 * 获取多个datagrid所有修改的行数据,传递参数前缀加上datagrid编号
 * 
 * @param datagridId 多表单用逗号隔开
 * @param idFiled 多字段对应表单用逗号隔开
 *            行主键；默认为id 作用：某些行中含有searchInput,
 *            当弹框callback后，datagrid的updated中无法获取这条记录。这能通过editStatus='update'来获取
 * @return
 */
function getChangeRows2Dgs(datagridIds, idFileds) {
	var effectRows= new Object(); 
	for(var i=0;i<datagridIds.split(",").length;i++) {
		// 获取datagrid
		var datagridId=datagridIds.split(",")[i];
	//	alert(datagridId);
		// 获取datagrid对应的idFiled
		var idFiled = (!isEmpty(idFileds) && idFileds.split(",").length>i) ? idFileds.split(",")[i] : ""; 
		// 获取当前grid参数
		var effectRow =getChangeRows2Dg(datagridId,idFiled);
		// 避免与其他grid参数冲突,另起别名
		//alert("datagridId:"+datagridId+":"+effectRow["inserted"]);
		if(!isEmpty(effectRow["inserted"])) {
			effectRows[datagridId+"Inserted"]=effectRow["inserted"];
		}
		if(!isEmpty(effectRow["deleted"])) {
			effectRows[datagridId+"Deleted"]=effectRow["deleted"];
		}
		if(!isEmpty(effectRow["updated"])) {
			effectRows[datagridId+"Updated"]=effectRow["updated"];
		}
		if(!isEmpty(effectRow["datalist"])) {
			effectRows[datagridId+"Datalist"]=effectRow["datalist"];
		}
		//alert(effectRows[datagridId+"Inserted"]);
		//effectRow={};
	}
	return effectRows;

}

/**
 * 绑定编辑datagrid字段事件
 * 
 * @param datagridId
 *            编号
 * @param rowIndex
 *            编辑行号
 * @param field
 *            绑定字段
 * @param event
 *            事件
 * @param funtionName
 *            触发方法名
 */
function bindEvent4DgField(datagridId, rowIndex, field, event, funtionName) {
	// 获取指定编辑器
	var e = $('#' + datagridId).datagrid('getEditor', {
		index : rowIndex,
		field : field
	});
 
	if (event == "change") {
		bindChangeEvent4DgField(datagridId, rowIndex, field, funtionName);
		/*$(e.target).bind("change", function() {
			eval(funtionName)(datagridId, rowIndex, this);
		});*/
	} else if (event == "blur") {
		/*$(e.target).bind("blur", function() {
			eval(funtionName)(datagridId, rowIndex, this);
		});*/
		bindBlurEvent4DgField(datagridId, rowIndex, field, funtionName);
	} else if (event == "click") {
		/*$(e.target).bind("click", function() {
			eval(funtionName)(datagridId, rowIndex, this);
		});*/
		bindClickEvent4DgField(datagridId, rowIndex, field, funtionName);
	}
	// 以下方法是采用遍历的模式，性能相对比较慢
	/**
	 * var ed = $('#'+datagridId).datagrid('getEditors', rowIndex); for (var i =
	 * 0; i < ed.length; i++){ var e = ed[i]; if (e.field == field) { if
	 * (event=="change") { $(e.target).bind("change", function(){
	 * eval(funtionName)(datagridId,rowIndex); }); } else if (event == "blur") {
	 * $(e.target).bind("blur", function(){
	 * eval(funtionName)(datagridId,rowIndex); }); } else if (event == "click") {
	 * $(e.target).bind("click", function(){
	 * 
	 * eval(funtionName)(datagridId,rowIndex); }); } } }
	 */
}


/**
 * 根据不同编辑组件类型绑定改变事件
 * 
 * @param datagridId
 * @param rowIndex
 * @param field
 * @param funtionName
 */
function bindChangeEvent4DgField(datagridId, rowIndex, field, funtionName) {
	// 获取编辑字段对象
	var e = $('#' + datagridId).datagrid('getEditor', {
		index : rowIndex,
		field : field
	});
	switch (e.type) {// 编辑类型
		case "text":
			$(e.target).bind("change", function() {
				// 绑定改变事件
				eval(funtionName)(datagridId, rowIndex, this);
			});
			break;
		case "textbox":
			// 绑定EU事件
			$(e.target).textbox({
				"onChange" : function(newValue, oldValue) {
					eval(funtionName)(datagridId, rowIndex, this);
				}
			});
			break;
		case "select":
			$(e.target).bind("change", function() {
				// 绑定改变事件
				eval(funtionName)(datagridId, rowIndex, this);
			});
			break;
		case "numberbox":
			// 绑定EU事件
			$(e.target).numberbox({
				"onChange" : function(newValue, oldValue) {
					eval(funtionName)(datagridId, rowIndex, this);
				}
			});
			break;
		case "timespinner":
			// 绑定EU事件
			$(e.target).timespinner({
				"onChange" : function() {
					eval(funtionName)(datagridId, rowIndex, this);
				}
			});
	
			break;
		case "dateboxgrid":
			// 暂未实现
			break;
		case "datebox":
			// 绑定EU事件
			$(e.target).datebox('options').onSelect = function(date) {
				// 绑定改变事件
				eval(funtionName)(datagridId, rowIndex, this);
			}
			break;
		case "searchInput":
			// 暂未实现
			break;
		default:
			$(e.target).bind("change", function() {
				// 绑定改变事件
				eval(funtionName)(datagridId, rowIndex, this);
			});
			break;
	}
}
 

/**
 * 根据不同编辑组件类型绑定失去焦点事件
 * 
 * @param datagridId
 * @param rowIndex
 * @param field
 * @param funtionName
 */
function bindBlurEvent4DgField(datagridId, rowIndex, field, funtionName) {
	// 获取编辑字段对象
	var e = $('#' + datagridId).datagrid('getEditor', {
		index : rowIndex,
		field : field
	});
	switch (e.type) {// 编辑类型
		case "textbox":
			$(e.target).closest("td").bind("blur", function() {
				eval(funtionName)(datagridId, rowIndex, this);
			});
			break;
		case "text":
			$(e.target).bind("blur", function() {
				eval(funtionName)(datagridId, rowIndex, this);
			});
			break;	
		case "select":
			$(e.target).bind("blur", function() {
				eval(funtionName)(datagridId, rowIndex, this);
			});
			break;
		case "numberbox":
			//e.target.parent()由于numberbox在easyui上做了样式结构封装将click事件绑定在父对象td上
			$(e.target).closest("td").bind("blur", function() {
				eval(funtionName)(datagridId, rowIndex, this);
			});
			break;
		case "timespinner":
			//由于numberbox在easyui上做了样式结构封装将click事件绑定在父对象td上
			$(e.target).closest("td").bind("blur", function() {
				eval(funtionName)(datagridId, rowIndex, this);
			});
			break;	
		case "dateboxgrid":
			// 无效！！
			$(e.target).closest("td").bind("blur", function() {
				eval(funtionName)(datagridId, rowIndex, this);
			});
			break;
		case "datebox":
			$(e.target).closest("td").bind("blur", function() {
				eval(funtionName)(datagridId, rowIndex, this);
			});
			break;
		case "searchInput":
			$(e.target).closest("td").bind("blur", function() {
				eval(funtionName)(datagridId, rowIndex, this);
			});
			break;
		case "checkbox":
			$(e.target).bind("blur", function() {
				eval(funtionName)(datagridId, rowIndex, this);
			});
			break;
		default:
			$(e.target).bind("blur", function() {
				eval(funtionName)(datagridId, rowIndex, this);
			});
			break;
	}
}



/**
 * 根据不同编辑组件类型绑定点击事件
 * 
 * @param datagridId
 * @param rowIndex
 * @param field
 * @param funtionName
 */
function bindClickEvent4DgField(datagridId, rowIndex, field, funtionName) {
	// 获取编辑字段对象
	var e = $('#' + datagridId).datagrid('getEditor', {
		index : rowIndex,
		field : field
	});
	switch (e.type) {// 编辑类型
		case "textbox":
			$(e.target).closest("td").bind("click", function() {
				eval(funtionName)(datagridId, rowIndex, this);
			});
			break;
		case "text":
			$(e.target).bind("click", function() {
				eval(funtionName)(datagridId, rowIndex, this);
			});
			break;	
		case "select":
			$(e.target).bind("click", function() {
				eval(funtionName)(datagridId, rowIndex, this);
			});
			break;
		case "numberbox":
			//e.target.parent()由于numberbox在easyui上做了样式结构封装将click事件绑定在父对象td上
			$(e.target).closest("td").bind("click", function() {
				eval(funtionName)(datagridId, rowIndex, this);
			});
			break;
		case "timespinner":
			//由于numberbox在easyui上做了样式结构封装将click事件绑定在父对象td上
			$(e.target).closest("td").bind("click", function() {
				eval(funtionName)(datagridId, rowIndex, this);
			});
			break;	
		case "dateboxgrid":
			// 无效！！
			$(e.target).closest("td").bind("click", function() {
				eval(funtionName)(datagridId, rowIndex, this);
			});
			break;
		case "datebox":
			$(e.target).closest("td").bind("click", function() {
				eval(funtionName)(datagridId, rowIndex, this);
			});
			break;
		case "searchInput":
			$(e.target).closest("td").bind("click", function() {
				eval(funtionName)(datagridId, rowIndex, this);
			});
			break;
		case "checkbox":
			$(e.target).bind("click", function() {
				eval(funtionName)(datagridId, rowIndex, this);
			});
			break;
		default:
			$(e.target).bind("click", function() {
				eval(funtionName)(datagridId, rowIndex, this);
			});
			break;
	}
}

/**
 *  禁用datagrid中编辑组件
 * @param datagridId
 * @param editor
 */
function disableEditorTag4DgEditor(datagridId,rowIndex,field){
	
	var editor = $('#' + datagridId).datagrid('getEditor', {
			index : rowIndex,
			field : field
	});
	if(!isEmpty(editor)) {
		 var editorType=editor.type;//编辑类型
		   switch (editorType) {
			case "textbox":
				$(editor.target).textbox("disable");
				break;
			case "numberbox":
				$(editor.target).numberbox("disable");
				break;
			case "timespinner":
				$(editor.target).timespinner("disable");
				break;	
			case "datebox":
				$(editor.target).datebox("disable");
				break;
			case "checkbox":
				$(editor.target).closest("input").attr('disabled','disabled');
				break;
			case "searchInput":
				$(editor.target).closest("span").attr('disabled','disabled');
				break;
			case "dateboxgrid":
				// 暂时无解
				break;
			case "select":
				$(editor.target).closest("select").attr('disabled','disabled');
				break;
			default:
				$(editor.target).closest("input").attr('disabled','disabled');
				break;
		}
	 }
}

/**
 *  启用datagrid中编辑组件
 * @param datagridId
 * @param editor
 */
function enableEditorTag4DgEditor(datagridId,rowIndex,field){
	
	var editor = $('#' + datagridId).datagrid('getEditor', {
			index : rowIndex,
			field : field
	});
	if(!isEmpty(editor)) {
		 var editorType=editor.type;//编辑类型
		   switch (editorType) {
			case "textbox":
				$(editor.target).textbox("enable");
				break;
			case "numberbox":
				$(editor.target).numberbox("enable");
				break;
			case "timespinner":
				$(editor.target).timespinner("enable");
				break;	
			case "datebox":
				$(editor.target).datebox("enable");
				break;
			case "checkbox":
				$(editor.target).closest("input").removeAttr('disabled');
				break;
			case "searchInput":
				$(editor.target).closest("span").removeAttr('disabled');
				break;
			case "dateboxgrid":
				// 暂时无解
				break;
			case "select":
				$(editor.target).closest("select").removeAttr('disabled');
				break;
			default:
				$(editor.target).closest("input").removeAttr('disabled');
				break;
		}
	 }
}

 

/**
 * 重载datagrid，采用级联的方式触发
 * 
 * @param datagridId
 * @param url
 */
function cascadeDatagrid(cascadeDatagridRow, datagridId, url) {
	var cascadeDatagrid = eval(datagridId + "CascadeDatagrid");
	var cascadeDatagridParams = eval(datagridId + "CascadeDatagridParams");
	//alert("cascadeDatagrid="+cascadeDatagrid + "，cascadeDatagridParams="+cascadeDatagridParams);
	if (!isEmpty(cascadeDatagrid) && !isEmpty(cascadeDatagridParams)) {
		if (isEmpty(url)) {
			url = eval(datagridId + "URL");
		} else {
			if (isEmpty(eval(datagridId + "URL"))) {
				this[datagridId + "URL"] = url;
			}
		}
		var cascadeDatagridParamsArr = cascadeDatagridParams.split(",");
		for (var i = 0; i < cascadeDatagridParamsArr.length; i++) {
			var paramsArr = cascadeDatagridParamsArr[i].split(":");
			if (!isEmpty(paramsArr[0]) && !isEmpty(paramsArr[1])) {
				//alert("paramsArr[0]=" + paramsArr[0] + "paramsArr[1]=" + paramsArr[1] + ",selectFieldValue=" + eval("cascadeDatagridRow."+paramsArr[1]));
				url += ("&" + paramsArr[0] + "=" + eval("cascadeDatagridRow." + paramsArr[1]));
			}
		}
	}
	reloadDatagrid(datagridId, url, "true");
}

/**
 * 根据url，重载datagrid
 * 
 * @param datagrid
 * @param url
 * @return
 */
function reloadDatagrid(datagridId, url, isCascade) {
	// alert("datagridId="+datagridId + "," +$('#' +
	// datagridId).datagrid('options').url);
	// /alert(datagridId+"URL=" + this[datagridId+"URL"]);
	if (isEmpty(url)) {
		url = eval(datagridId + "URL");
	} else {
		if (isEmpty(eval(datagridId + "URL"))) {
			this[datagridId + "URL"] = url;
		}
	}

	// 判断是否存在级联
	// if (!isEmpty(isCascade) && isCascade =="true") {
	if (isEmpty(isCascade)) {
		var cascadeDatagrid = eval(datagridId + "CascadeDatagrid");
		var cascadeDatagridParams = eval(datagridId + "CascadeDatagridParams");
		if (!isEmpty(cascadeDatagrid) && !isEmpty(cascadeDatagridParams)) {
			var cascadeDatagridParamsArr = cascadeDatagridParams.split(",");
			for (var i = 0; i < cascadeDatagridParamsArr.length; i++) {
				var paramsArr = cascadeDatagridParamsArr[i].split(":");
				if (!isEmpty(paramsArr[0]) && !isEmpty(paramsArr[1])) {
					url += ("&" + paramsArr[0] + "=" + getSingleRecordFieldValue(
							cascadeDatagrid, paramsArr[1]));
				}
			}
		}
	}

	// 初始化对象
	if (isEmpty(relationObjParamMap)) {
		relationObjParamMap = new HashMap();
	}
	if (!isEmpty(relationObjParamMap)
			&& !isEmpty(relationObjParamMap.containsKey(datagridId))) {
		// 如果url为空，说明可能是form表单查询，需要传递参数。
		// alert("str2UrlParams(relationObjParamMap.get(datagridId))="+str2UrlParams(relationObjParamMap.get(datagridId)));
		$('#' + datagridId).datagrid('options').url = url
				+ str2UrlParams(relationObjParamMap.get(datagridId));
		// if (isEmpty(url)) {
		// var params = $("#" + datagridId).datagrid("options").queryParams;
		// var objArr = relationObjParamMap.get(datagridId).split(",");
		// for (var i = 0; i < objArr.length; i++) {
		// params[objArr[i].split(":")[0]] = objArr[i].split(":")[1];
		// }
		// } else {
		// //针对简易数据维护中，DIV之间的关联映射（如：树点击事件对多个tab对象传值）实现。
		// if (!isEmpty(relationObjParamMap) &&
		// !isEmpty(relationObjParamMap.containsKey(datagridId))) {
		// $('#' + datagridId).datagrid('options').url = $('#' +
		// datagridId).datagrid('options').url +
		// str2UrlParams(relationObjParamMap.get(datagridId));
		// }
		// }
	} else {
		$('#' + datagridId).datagrid('options').url = url;
	}
	 //alert($('#' + datagridId).datagrid('options').url);
	$("#" + datagridId).datagrid("reload");
}
/**
 * 把参数字符串params解析后,存入relationObjParamMap对象中
 * 调用reloadDatagrid方法后，自动解析参数map,加载到url中过滤，重新加载列表数据
 * 
 * @param datagridId
 * @param params
 *            参数 funId:1,funName:2
 */
function reloadDatagridByParams(datagridId, paramStr) {
	if (paramStr != null || paramStr != undefined) {
		var paramsArr = paramStr.split(",");
		var paramStr = "";
		// 初始化对象
		if (isEmpty(relationObjParamMap)) {
			relationObjParamMap = new HashMap();
		}
		for (i = 0; i < paramsArr.length; i++) {
			var paramArr = new Array();
			paramArr = paramsArr[i].split(":");
			paramStr += (isEmpty(paramStr) ? "" : ",") + paramArr[0] + ":"
					+ paramArr[1];
		}
		relationObjParamMap.put(datagridId, paramStr);
	}
	reloadDatagrid(datagridId);
}
/**
 * 重新加载列表数据
 * 
 * @param datagridId
 * @param params
 *            参数 funId:1,funName:2
 */
function reloadDatagridByParamsV2(datagridId, paramStr) {
	if (paramStr != null || paramStr != undefined) {
		var params = $("#" + datagridId).datagrid("options").queryParams;
		var paramsArr = new Array();
		paramsArr = paramStr.split(",");
		for (i = 0; i < paramsArr.length; i++) {
			var paramArr = new Array();
			paramArr = paramsArr[i].split(":");
			params[paramArr[0]] = paramArr[1];
		}
	}

	// hxm:2014-12-15 云南修改：执行下面代码，datagrid会刷新两次
	// reloadDatagrid(datagridId);
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
function retResultAdReloadByParams4Call(result, reloadGridId, closeObj,
		reloadGridParams) {
	showProcess(false);
	if (!isEmpty(result)) {
        result = evalObj(result);
		if (result.success) {
			euShow("操作成功！");
			// 刷新grid
			if (!isEmpty(reloadGridId)) {
				reloadDatagridByParams(reloadGridId, reloadGridParams);
			}
			// 关闭子窗口
			if (!isEmpty(closeObj)) {
				closeThisPopWindow();
			}
		} else {
			euShow(result.msg, null, 10);
		}
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
function retResultAdReload4Call(result, reloadGridId, closeObj, reloadUrl) {
	showProcess(false);
	if (!isEmpty(result)) {
        result = evalObj(result);
		if (result.success) {
			euShow("操作成功！");
			// 刷新grid
			if (!isEmpty(reloadGridId)) {
				reloadDatagrid(reloadGridId, reloadUrl);
			}
			// 关闭子窗口
			if (!isEmpty(closeObj)) {
				closeThisPopWindow();
			}
		} else {
			euShow("操作失败   原因:" + result.msg, null, 10);
		}
	}
}

/**
 * 执行上传动作、然后回调函数并刷新datagrid
 * 
 * @param result
 *            后台返回值
 * @param reloadGridId
 *            需要刷新的grid id
 * @param closeObj
 *            关闭的对象
 */

function commitUploadFileCallback(result, reloadGridId, funName, closeObj,
		businessId) {
	showProcess(false);

	var obj = result.split(',');
	if ("true" == obj[0]) {
		if (obj[1]) {
			if (isEmpty(businessId)) {
				businessId = "businessId";
			}
			$('#' + businessId).val(obj[1]);
		}
		$('#uploadify').uploadify('upload', '*');
		var file = $('#uploadify').uploadify('get', 'file');

		// 刷新grid
		if (!isEmpty(reloadGridId)) {
			reloadDatagrid(reloadGridId);
		}

		if (file == null && funName != null) {
			reflectFun(funName);
		}

		if (!isEmpty(closeObj)) {
			closeThisPopWindow();
		}
	} else {
		euShow("操作失败   原因:" + result, null, 10);
	}
}

// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * 说明：easyui.datagrid行编辑中，涉及searchWindow控件时的通用操作JS * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */
// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/**
 * datagrid行编辑 如果为searchInput 自定义型弹出框
 * 
 * @param url
 * @param title
 * @param width
 * @param height
 */
function openSearchWindowSelf2DgColumn(url, title, width, height) {
	if(!isEmpty(width) && !isEmpty(height)) {
		openWindowThree(url, title, width, height, "");
	} else {
		openMaxWindowThree(url, title);
	}
}

/**
 * datagrid行编辑 配置型弹出框 在datagrid中涉及弹出框字段时调用
 * 
 * @param target
 * @param datagridId
 * @param tagId
 * @param recordFlag
 * @param selectType
 * @param maSqlNum
 * @param miSqlNum
 * @param maSqlParams
 * @param miSqlParams
 * @param callback
 * @param inputValidate
 * @param title
 * @param width
 * @param height
 */
function openSearchWindow2DgColumn(target, datagridId, tagId, recordFlag,
		selectType, maSqlNum, miSqlNum, maSqlParams, miSqlParams, callback,
		inputValidate, title, width, height) {
	 
	var paramsMap = new HashMap();
	paramsMap.put("target", target);
	paramsMap.put("datagridId", datagridId);
	paramsMap.put("tagId", tagId);
	paramsMap.put("recordFlag", recordFlag);
	paramsMap.put("idField", tagId);
	paramsMap.put("idTagName", tagId);
	paramsMap.put("textTagName", tagId);
	paramsMap.put("selectType", selectType);
	paramsMap.put("maSqlNum", maSqlNum);
	paramsMap.put("miSqlNum", miSqlNum);
	paramsMap.put("maSqlParams", maSqlParams);
	paramsMap.put("miSqlParams", miSqlParams);
	paramsMap.put("callback", callback);
	paramsMap.put("inputValidate", inputValidate);
	paramsMap.put("title", title);
	paramsMap.put("width", width);
	paramsMap.put("height", height);
	paramsMap.put("queryInputObj", "");// datagrid行编辑所以为空
	paramsMap.put("queryValue", "");// datagrid行编辑所以为空
	paramsMap.put("queryConstantValue", "");// datagrid行编辑所以为空
	paramsMap.put("fromFieldId", "");// datagrid行编辑所以为空
	paramsMap.put("isCustomPage", "true");

	openSearchBusinessWindowByMap(paramsMap);
	// openSearchBusinessWindowByMap(target, tagId, recordFlag, tagId, tagId,
	// tagId, selectType, maSqlNum,miSqlNum, maSqlParams,miSqlParams, callback,
	// inputValidate, title, width, height,"","","","",'true');
}
/**
 * 标签TAG调用弹出框方法 在easyuiSearchInputTag中调用
 * 
 * @param target
 * @param tagId
 * @param recordFlag
 * @param idField
 * @param idTagName
 * @param textTagName
 * @param selectType
 * @param callback
 * @param inputValidate
 * @param title
 * @param width
 * @param height
 * @param queryInputObj
 * @param queryValue
 * @param queryConstantValue
 * @param fromFieldId
 * @param isCustomPage
 */
function openSimpleSearchWindow(tagId, recordFlag, idField, idTagName,
		textTagName, selectType, callback, inputValidate, title, width, height,
		queryInputObj, queryValue, queryConstantValue, fromFieldId,
		isCustomPage) {
	var paramsMap = new HashMap();
	paramsMap.put("target", null);
	paramsMap.put("tagId", tagId);
	paramsMap.put("recordFlag", recordFlag);
	paramsMap.put("idField", idField);
	paramsMap.put("idTagName", idTagName);
	paramsMap.put("textTagName", textTagName);
	paramsMap.put("selectType", selectType);
	paramsMap.put("callback", callback);
	paramsMap.put("inputValidate", inputValidate);
	paramsMap.put("title", title);
	paramsMap.put("width", width);
	paramsMap.put("height", height);
	paramsMap.put("queryInputObj", queryInputObj);
	paramsMap.put("queryValue", queryValue);
	paramsMap.put("queryConstantValue", queryConstantValue);
	paramsMap.put("fromFieldId", fromFieldId);
	paramsMap.put("isCustomPage", isCustomPage);
	paramsMap.put("maSqlNum", 0); // 手工调用所以为0
	paramsMap.put("miSqlNum", 0); // 手工调用所以为0
	paramsMap.put("maSqlParams", ""); // 手工调用所以为空
	paramsMap.put("miSqlParams", ""); // 手工调用所以为空

	// openSearchBusinessWindowByMap(target, tagId, recordFlag, idField,
	// idTagName, textTagName, selectType, "0", "0", "", "", callback,
	// inputValidate, title, width, height, queryInputObj, queryValue,
	// queryConstantValue, fromFieldId, isCustomPage)
	openSearchBusinessWindowByMap(paramsMap);
}
/**
 * 手工编写按钮调用弹出框方法 导出按钮自动生成字段
 * 
 * @param tagId
 * @param recordFlag
 * @param idField
 * @param idTagName
 * @param textTagName
 * @param selectType
 * @param maSqlNum
 * @param miSqlNum
 * @param maSqlParams
 * @param miSqlParams
 * @param callback
 * @param inputValidate
 * @param title
 * @param width
 * @param height
 * @param queryInputObj
 * @param queryValue
 * @param queryConstantValue
 * @param fromFieldId
 * @param isCustomPage
 * 
 */
function openSearchBusinessWindow(tagId, recordFlag, idField, idTagName,
		textTagName, selectType, maSqlNum, miSqlNum, maSqlParams, miSqlParams,
		callback, inputValidate, title, width, height, queryInputObj,
		queryValue, queryConstantValue, fromFieldId, isCustomPage) {
	var paramsMap = new HashMap();
	paramsMap.put("target", null);
	paramsMap.put("tagId", tagId);
	paramsMap.put("recordFlag", recordFlag);
	paramsMap.put("idField", idField);
	paramsMap.put("idTagName", idTagName);
	paramsMap.put("textTagName", textTagName);
	paramsMap.put("selectType", selectType);
	paramsMap.put("maSqlNum", maSqlNum);
	paramsMap.put("miSqlNum", miSqlNum);
	paramsMap.put("maSqlParams", maSqlParams);
	paramsMap.put("miSqlParams", miSqlParams);
	paramsMap.put("callback", callback);
	paramsMap.put("inputValidate", inputValidate);
	paramsMap.put("title", title);
	paramsMap.put("width", width);
	paramsMap.put("height", height);
	paramsMap.put("queryInputObj", queryInputObj);
	paramsMap.put("queryValue", queryValue);
	paramsMap.put("queryConstantValue", queryConstantValue);
	paramsMap.put("fromFieldId", fromFieldId);
	paramsMap.put("isCustomPage", isCustomPage);

	// openSearchBusinessWindowByMap(target, tagId, recordFlag, idField,
	// idTagName, textTagName, selectType, "0", "0", "", "", callback,
	// inputValidate, title, width, height, queryInputObj, queryValue,
	// queryConstantValue, fromFieldId, isCustomPage)
	openSearchBusinessWindowByMap(paramsMap);
}
/**
 * 配置业务查询弹出框
 * 
 * @param tagId
 * @param recordFlag
 * @param idField
 * @param idTagName
 * @param textTagName
 * @param selectType
 *            选择类型（1/单选，2/3多选 2多父节点多选；3单父节点多选）
 * @param maSqlNum
 *            主表选择第几个SQL
 * @param miSqlNum
 *            从表选择第几个SQL
 * @param maSqlParams
 * @param miSqlParams
 * @param callback
 * @param inputValidate
 * @param title
 * @param width
 * @param height
 * @param queryInputObj
 * @param queryValue
 * @param queryConstantValue
 * @param fromFieldId
 * @param isCustomPage
 */
function openSearchBusinessWindowByMap(paramsMap) {
	var target, tagId, recordFlag, idField, idTagName, textTagName, selectType, maSqlNum, miSqlNum, maSqlParams, miSqlParams, callback, inputValidate, title, width, height, queryInputObj, queryValue, queryConstantValue, fromFieldId, isCustomPage;
	target = paramsMap.get("target");
	tagId = paramsMap.get("tagId");
	recordFlag = paramsMap.get("recordFlag");
	idField = paramsMap.get("idField");
	idTagName = paramsMap.get("idTagName");
	textTagName = paramsMap.get("textTagName");
	selectType = paramsMap.get("selectType");
	maSqlNum = paramsMap.get("maSqlNum");
	miSqlNum = paramsMap.get("miSqlNum");
	maSqlParams = paramsMap.get("maSqlParams");
	miSqlParams = paramsMap.get("miSqlParams");
	callback = paramsMap.get("callback");
	inputValidate = paramsMap.get("inputValidate");
	title = paramsMap.get("title");
	width = paramsMap.get("width");
	height = paramsMap.get("height");
	queryInputObj = paramsMap.get("queryInputObj");
	queryValue = paramsMap.get("queryValue");
	queryConstantValue = paramsMap.get("queryConstantValue");
	fromFieldId = paramsMap.get("fromFieldId");
	isCustomPage = paramsMap.get("isCustomPage");

	if (isEmpty(maSqlNum)) {
		maSqlNum = "1";
	}
	if (isEmpty(miSqlNum)) {
		miSqlNum = "1";
	}
	// 解析参数中的变量
	if (!isEmpty(maSqlParams)) {
		maSqlParams = searchWindowParams(maSqlParams);
	}
	if (!isEmpty(miSqlParams)) {
		miSqlParams = searchWindowParams(miSqlParams);
	}
	var idValue = getValue2TagId(tagId + "Hidden", "input");
	// var url = "windowSearch.do?method=toWindowSearchPage&recordFlag=" +
	// recordFlag +"&tagId=" + tagId +"&idField=" + idField +"&idTagName=" +
	// idTagName +"&textTagName=" + textTagName +"&idValue=" + idValue
	// +"&singleSelect=" + selectType + "&maSqlNum=" + maSqlNum + "&miSqlNum=" +
	// miSqlNum + "&maSqlParams=" + (isEmpty(maSqlParams)? "" : maSqlParams) +
	// "&miSqlParams=" + (isEmpty(miSqlParams)? "" : miSqlParams)
	// +"&inputValidate=" + inputValidate +
	// "&isCustomPage="+isCustomPage+"&customMethod=" + callback +
	// "&queryInputObj=" + queryInputObj +"&queryValue=" + queryValue
	// +"&queryConstantValue=" + queryConstantValue + "&fromFieldId=" +
	// fromFieldId;
	// 默认选中的行序号
	var rowIndex = 0;
	if (!isEmpty(target)) {
		rowIndex = getRowIndex4DgTarget(target);
	}
	var url = "jsonDo.do?method=toJsonDoPage&businessName=TO_WINDOWSEARCH_PAGE&recordFlag="
			+ recordFlag
			+ "&tagId="
			+ tagId
			+ "&idField="
			+ idField
			+ "&idTagName="
			+ idTagName
			+ "&textTagName="
			+ textTagName
			+ "&idValue="
			+ idValue
			+ "&singleSelect="
			+ selectType
			+ "&maSqlNum="
			+ maSqlNum
			+ "&miSqlNum="
			+ miSqlNum
			+ "&maSqlParams="
			+ (isEmpty(maSqlParams) ? "" : maSqlParams)
			+ "&miSqlParams="
			+ (isEmpty(miSqlParams) ? "" : miSqlParams)
			+ "&inputValidate="
			+ inputValidate
			+ "&isCustomPage="
			+ isCustomPage
			+ "&customMethod="
			+ callback
			+ "&customMethodParams="
			+ "rowIndex:"
			+ rowIndex
			+ "&queryInputObj="
			+ queryInputObj
			+ "&queryValue="
			+ queryValue
			+ "&queryConstantValue="
			+ queryConstantValue
			+ "&fromFieldId="
			+ fromFieldId;
	if (isEmpty(width) || isEmpty(height)) {
		openMaxWindowThree(url, title);
	} else {
		openWindowThree(url, title, width, height, "");
	}
}

/**
 * 拼装弹窗查询参数 deptId=180&isval=1
 * 
 * @param sqlParams
 * @returns String
 */
function searchWindowParams(sqlParams) {
	// 1、根据,分割.遍历对象（对象以=分割 参数名称=参数值）
	// 2、判断参数值是否为{}。是则判断以下
	// 3、参数值以点号分割，如果.有任何一个异常，则当前参数不传。 [0]标识类型FORM/ROW [1]标识对象ID
	// formId/datagridId [2]标识对象字段名称Name form_userName/datagird_row_field
	// [3]标识对象字段类型 input/combogrid/combo|datagrid无
	// 拼装sqlParams
	// ID={ROW.qcMaterialProcessCheckGrid.qcSchemeId},IDD=123123,materialId={FORM.xxxx.materialId.text}
	var params = sqlParams.split("&");
	sqlParams = "";
	for (var i = 0; i < params.length; i++) {
		var _gp = params[i];
		if (!isEmpty(_gp)) {
			var _p = _gp.split("=");
			if (!isEmpty(_p[1])) {
				if (_p[1].indexOf("{") == -1) {// 如果没有{符号,直接取值
					sqlParams += _p[0] + "=" + _p[1] + ",";
				} else {
					var expression = _p[1].replace("{", "").replace("}", "")
							.split(".");
					if (expression[0] == 'ROW') {
						var row = $("#" + expression[1])
								.datagrid("getSelected");
						sqlParams += _p[0] + "=" + eval("row." + expression[2])
								+ ",";
					} else if (expression[0] == 'FORM') {
						var _type = "text";
						if (!isEmpty(expression[3])) {
							_type = expression[3];
						}
						sqlParams += _p[0] + "="
								+ getValue2TagName(expression[2], _type)
								+ ",";
					}
				}
			}
		}
	}
	return sqlParams;
}