<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<jsp:include page="/easyui/common/toListPage"></jsp:include>

<script type="text/javascript">

$(document).ready(function(){
	//alert("execute listCommon begin");
	//反射执行需要自动加载的事件
	setTimeout('executeReflectFun()',200);
	
	setTimeout('isAutoSearch()',10);
 	
	//页面弹出时，光标定位第一文本框
	$(".formTable").find(".columnData input[type=text]:not([readonly])").first().focus();
});

/**
 * 执行自动查询
 */
function isAutoSearch(){
	//alert("isAutoSearch,unloadDatagridIds="+unloadDatagridIds);
	//下拉框定位数据后，执行一次查询
	var searchButton = getCurrentPageSearchButton();
	//alert("requestScope.doSearch=${requestScope.doSearch},searchButton.size="+searchButton.size());
	//if((${requestScope.doSearch == null} || ${requestScope.doSearch != "false"}) && searchButton.size()!=0){		//&& filterSearchButtonRecord.indexOf(searchButton.attr("id"))<0
	//如果存在datagrid或treegrid，而且页面也存在“查询”按钮
		if((${requestScope.doSearch == null} || ${requestScope.doSearch != "false"}) && searchButton.size()!=0 
			&& (!isEmpty(unloadDatagridIds) || !isEmpty(unloadTreegridIds))){
		//未正常加载的标签数量   如果没有，则正常执行页面“查询”按钮			
		if (unloadCombogridNum==0) {
			//alert('unloadCombogridNum==0,unloadDatagridIds='+unloadDatagridIds);
			//执行页面查询按钮
			runPageSearchFun();
		} else {
			//alert('unloadCombogridNum='+unloadCombogridNum+',unloadDatagridIds='+unloadDatagridIds);
			//等待200毫秒，让其它未加载的标签加载完成，再执行查询按钮
			setIntervalId = window.setInterval("runPageSearchFun()",200);
		}
		unloadDatagridIds = "";
		unloadTreegridIds = "";
	} else if (!isEmpty(unloadDatagridIds)) {
		//alert("unloadDatagridIds="+unloadDatagridIds);
		var datagrids = unloadDatagridIds.split(",");
		for(i=0;i<datagrids.length;i++) {
			if (!isEmpty(datagrids[i])) {
				//alert("datagrid.length="+datagrids.length+",datagrids[i]="+datagrids[i]);
				reloadDatagrid(datagrids[i]);
				}
			}
	} else if (!isEmpty(unloadTreegridIds)) {
		var treegrids = unloadTreegridIds.split(",");
		for(i=0;i<treegrids.length;i++) {
			if (!isEmpty(treegrids[i])) {
				reloadTreegrid(treegrids[i]);
				}
			}
	}
}

</script>


