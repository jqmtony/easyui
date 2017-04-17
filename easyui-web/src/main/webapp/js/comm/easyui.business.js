/**
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  * 
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  * 
 *  说明：每个项目中涉及的各自的业务通用操作JS
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  * 
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  * 
 */
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