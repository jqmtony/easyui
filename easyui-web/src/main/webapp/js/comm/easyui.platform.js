/**
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  * 
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  * 
 *  说明：平台中涉及具体各类业务操作处理通用操作JS
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  * 
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  * 
 */

/**
 * 获取业务自动命名结果
 * 
 * @param tagId
 *            标签id
 * @param recordFlag
 */
function setBusinessAutoName(tagId, recordFlag, formId,tagName) {
	var url = "businessNameCommon.do?method=getBusinessDefineAutoName&recordFlag="
			+ recordFlag;
	if (!isEmpty(formId)) {
		var param = getFormParams(formId);
		url += !isEmpty(param) ? param : "";
	}
	
	ajaxPost(url, function(data) {
		if (data != "false") {
			tagName=isEmpty(tagName) ? "text" :tagName;
			setValue2TagId(tagId, tagName, data);
			//$("#" + tagId).val(data);
		} else {
			euShow("获取自动编号失败！");
		}
	});
}

/**
 * 获取业务自动命名结果
 * 
 * @param recordFlag
 */
function getBusinessAutoName(recordFlag) {
	ajaxPost(
			"businessNameCommon.do?method=getBusinessDefineAutoName&recordFlag="
					+ recordFlag, function(data) {
				if (data != "false") {
					return data;
				} else {
					euShow("获取自动编号失败！");
				}
			});
}

/**
 * EXCEL导出
 */
function excelExport(reacordFlag, formId) {

	var url = "jsonDo.do?method=toJsonDo&businessName=EXCEL_EXPORT&recordFlag="
			+ reacordFlag;
	if (!isEmpty(formId)) {
		// url += getFormParams(formId);
		// url += "&params="+getLineAdCommaStr4Form(formId, "=", ",");
		// 拼接查询条件为:大写key(用于sqlWhereFieldDelete) param=name=valule,name=valule

		var paramStr = "";
		$.each($("#" + formId).serializeArray(), function(i, field) {
			if (paramStr == "") {
				paramStr = (field.name.toUpperCase() + "=" + field.value);
			} else {
				paramStr = paramStr + ","
						+ (field.name.toUpperCase() + "=" + field.value);
			}
		});
		url += "&params=" + paramStr;
	}

	window.open(url);
	// var url =
	// "commExcel.do?method=exportExcelByFlag&recordFlag=employee_temp";
	// window.location.href = url;
}

/**
 * excel导入
 */
function excelImport(recordFlag) {
	var url = "jsonDo.do?method=toJsonDoPage&businessName=TO_EXCELIMPORT_PAGE&recordFlag="
			+ recordFlag;
	// var url="commExcel.do?method=importExcelByFlag&recordFlag="+recordFlag;
	// alert(url);
	openWindow(url, "导入", "400", "200");
}

/**
 * 根据JSON模型生成JSON文件
 */
function createJsonFileByModel(objFlag, objParams) {
	var url = "commJsonModel.do?method=generateJsonFile&objFlag=" + objFlag
			+ objParams;
	showProcess(true);
	ajaxPost(url, function(data) {
		showProcess(false);
		if ("true" == data) {
			euShow("JSON生成成功!")
		} else {
			euShow("JSON生成失败,失败原因:" + data);
		}
	});
}
