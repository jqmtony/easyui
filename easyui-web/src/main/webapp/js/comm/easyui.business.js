/**
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  * 
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  * 
 *  说明：每个项目中涉及的各自的业务通用操作JS
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  * 
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  * 
 */

/**
 * 格式化订单类型
 * 
 * @param val
 * @param node
 * @returns
 */
function formatOrderBillType(val, node) {
	if ("plan" == val) {
		return "预测订单";
	} else if ("sale" == val) {
		return "销售订单";
	}
}

/**
 * 格式化时栅来源类型
 * 
 * @param val
 * @param node
 * @returns {String}
 */
function formatMrpTimesSource(val, node) {
	if ("0" == val) {
		return "预测订单";
	} else if ("1" == val) {
		return "客户订单";
	} else if ("2" == val) {
		return "预测+客户订单";
	}
}


/**
 * 格式化MRP订单消耗类型
 * 
 * @param val
 * @param node
 * @returns
 */
function formatMrpOrderConsumeType(val, node) {
	var str=val;
	switch (val) {
		case "1":
			str="预计出";
			break;
		case "2":
			str="预计入";
				break;
		case "3":
			str="现存量";
			break;
		case "4":
			str="安全库存";
			break;
	
		default:
			break;
	}
	 return str;
}

/**
 * 格式化MRP订单消耗单据类型
 * 
 * @param val
 * @param node
 * @returns
 */
function formatMrpOrderConsumeBillType(val, node) {
	var str=val;
	switch (val) {
		case "100":
			str="材料出库单";
			break;
		case "200":
			str="生产订单";
				break;
		case "201":
			str="采购订单";
			break;
		case "202":
			str="委外订单";
			break;
		case "300":
			str="现存量";
			break;
		case "301":
			str="替代料";
			break;
		case "400":
			str="安全库存";
			break;
		default:
			break;
	}
	 return str;
}

/**
 * 格式化生产订单来源
 * 
 */
function formatterWorkOrderType(val, node){
	if("sale"==val) {
		return "销售订单";
	} else if("plan"==val) {
		return "计划订单";
	} else if("manual"==val) {
		return "手工订单";
	}  else if("repair"==val) {
		return "返修订单";
	}   
	
	return val;
	
}

function formatSex(val, node) {
	if ("0" == val) {
		return "女";
	} else if ("1" == val) {
		return "男";
	}
}

function formatExpOrNo(val, node) {
	if ("1" == val) {
		return "<span class='l-btn-text icon-ok' style='padding-left: 30px;'>"
				+ formatYes + "</span>";
	} else {
		return "<span class='l-btn-text icon-no' style='padding-left: 30px;'>"
				+ formatNo + "</span>";
	}
}

function formatHisOrNo(val, node) {
	if ("0" == val) {
		return "<span class='l-btn-text icon-no' style='padding-left: 30px;'>"
				+ formatNo + "</span>";
	} else {
		return "<span class='l-btn-text icon-ok' style='padding-left: 30px;'>"
				+ formatYes + "</span>";
	}
}

// -----质量------
function formatterSpotQcRule(value, rowData, rowIndex) {// 抽检规则
	if (value == "1") {
		return "按比例抽检";
	} else if (value == "2") {
		return "定量抽检";
	} else if (value == "3") {
		return "按自定义规则抽检";
	}
}

function formatterQcRule(value, rowData, rowIndex) {// 质检规则
	if (value == "1") {
		return "按零部件质检";
	} else if (value == "2") {
		return "按指标质检";
	}
}

function formatterDefectLevel(value, rowData, rowIndex) {
	if (value == "1") {
		return "严重";
	} else if (value == "2") {
		return "重要";
	} else if (value == "3") {
		return "一般";
	}
}

function formatterNormType(value, rowData, rowIndex) {
	if (value == "1") {
		return "记数型 ";
	} else if (value == "2") {
		return "计量型";
	}
}

/**
 * 根据云南项目，保留产品检，工序检
 * 
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {String}
 */
function formatterQcType(value, rowData, rowIndex) {
	if (value == "1") {
		 return "采购检验";
	}else if(value=="2"){
		 return "委外检验";
	}else if(value=="3"){
		 return "产品检验";
	}else if(value=="4"){
		 return "在库检验";
	}else if(value=="5"){
		 return "发货检验";
	}else if(value=="6"){
		 return "退货检验";
	} else if (value == "7") {
		 return "工序过程检验";
	} else if (value == "8") {
		 return "工序过程返修检验";
	}
}

function formatterQcWay(value, rowData, rowIndex) {
	if (value == "1") {
		return "全检";
	} else if (value == "2") {
		return "免检";
	} else if (value == "3") {
		return "破坏性抽检";
	} else if (value == "4") {
		return "非破坏性抽检";
	}
}

function formatOrderseq(value, row) {
	if ("1" == value) {
		return "正常";
	} else if ("0" == value) {
		return "锁定";
	} else {
		return value;
	}
}

function formatterQcSelfSpotRule(value, rowData, rowIndex) {
	if (value == "1") {
		return "全检";
	} else if (value == "2") {
		return "按计算公式";
	} else if (value == "3") {
		return "按比例";
	} else if (value == "4") {
		return "按固定数量";
	}
}

function formatterisMust(value, rowData, rowIndex) {
	if (value == "1") {
		return "是";
	} else if (value == "0") {
		return "否";
	}
}

/**
 * 工序检验处理
 */
function formatterQcProcessDecide(value, rowData, rowIndex) {
	if (value == "0") {
		return "无";
	} else if (value == "1") {
		return "检验存在合格,下工序按合格数操作";
	} else if (value == "2") {
		return "检验存在不合格,不准许下工序操作";
	} else if (value == "3") {
		return "订单完工前,所有工序检必须合格";
	} else if (value == "4") {
		return "产成品入库前,所有工序检必须合格";
	}
}

function formatterqcTypeScheme(value, rowData, rowIndex) {
	if (value == "1") {
		return "定量";
	} else if (value == "2") {
		return "定性";
	} else if (value == "3") {
		return "记录值";
	}
}
function formatterisOneselfQc(value, rowData, rowIndex) {
	if (value == "1") {
		return "是";
	} else if (value == "0") {
		return "否";
	}
}

/**
 * 是否质检完成
 * 
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {String}
 */
function formatterIscomplete(value, rowData, rowIndex) {
	if (value == "1") {
		return "是";
	} else if (value == "0") {
		return "否";
	}
}
/**
 * 是否需要质检
 * 
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {String}
 */
function formatterIsQc(value, rowData, rowIndex) {
	if (value == "1") {
		return "是";
	} else if (value == "0") {
		return "否";
	}
}

function formatterQcOrderFirst(value, rowData, rowIndex) {
	if (value == "1") {
		return "是";
	} else if (value == "0") {
		return "否";
	}
}

function formatterQcResult(value, rowData, rowIndex) {
	if (value == "1") {
		return "合格";
	} else if (value == "0") {
		return "不合格";
	}

}

/**
 * 不合格数标记
 * 
 * @param data
 * @returns
 */
function formatterBadNum(data) {
	if (data > 0)
		return "<font color='red'>" + data + "</font>";
	else
		return data;
}

function dataTypeFormatter(data) {
	if (data == "1") {
		return "datagrid";
	} else if (data == "2") {
		return "treegrid";
	} else if (data == "3") {
		return "tree";
	} else if (data == "4") {
		return "detailForm";
	}
}

/**
 * 不良品处理方式 1为工序报废 4为零部件报废
 */
function formatterBadWay(value, rowData, rowIndex) {
	if (value == "0") {
		return "不处理";
	} else if (value == "1") {
		return "报废";
	} else if (value == "2") {
		return "拒收";
	} else if (value == "3") {
		return "退货";
	} else if (value == "4") {
		return "报废";
	} else if (value == "5") {
		return "返修";
	}
}

function formatIsproduct(value) {
	if (value == '0') {
		return "联产品";
	} else if (value == '1') {
		return "副产品";
	} else {
		return "未设置";
	}
}

function formatBomType(value) {
	if (value == "1") {
		return "主BOM";
	} else if (value == "2") {
		return "订单BOM";
	}
}

// 订单bom维护状态
function formatBomStatus(value) {
	if (value == "1") {
		return "已维护";
	} else {
		return "未维护";
	}
}

// 仓库审核状态
function formatWmsStatus(value) {
	if (value == "0" || value == null) {
		return "新建";
	} else if (value == "1") {
		return "已审核";
	} else if (value == "2") {
		return "弃审";
	}
}

/**
 * 采购业务类型
 * 
 * @param value
 * @returns {String}
 */
function formatBusinessType(value) {
	if (value == "1") {
		return "普通采购";
	} else if (value == "2") {
		return "进料加工";
	}
}

/**
 * 采购类型
 * 
 * @param value
 * @returns {String}
 */
function formatPcType(value) {
	if (value == "1") {
		return "国内采购";
	} else if (value == "2") {
		return "国外加工";
	}
}
/**
 * 简易功能字段是否配置公式
 * 
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {String}
 */
function formatSfunFormula(value) {
	if (isEmpty(value)) {
		return "否";
	} else {
		return "是";
	}
}
/**
 * 简易功能约束类型
 * 
 * @param value
 * @returns {String}
 */
function formatSfunConstraintType(value) {
	if (value == "0") {
		return "字段";
	} else if (value == "1") {
		return "主键";
	} else if (value == "2") {
		return "外键";
	} else if (value == "3") {
		return "引用字段";
	} else if (value == "4") {
		return "系统常量";
	}
}