<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.tristone.platform.general.constant.GlobalConstant"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html>

<script type="text/javascript">

//页面grid名称
var gridWindowSearch = "${requestScope.tableBean.tableName}";

//页面form名称
var formWindowSearch = "listFormWindowSearch";

/**requestScope.pkBean.tablePks
 * 点击"查询"
 * @param grid 页面grid名称
 * @param form 页面form名称
 */
function doSearchWindowSearch() {
		var url="jsonDo.do?method=toJsonDo&businessName=GETSEARCHPAGE_PAGELISTER&recordFlag=${requestScope.recordFlag }&searchPageId=${requestScope.formListBean.searchPageId }&tableId=${requestScope.tableBean.id}&dataType=${requestScope.tableBean.dataType}&idField=${requestScope.tableBean.idField }&sqlNum=${requestScope.tableBean.sqlNum }&sqlParams=${requestScope.tableBean.sqlParams }";
		<c:choose>
			<c:when test="${requestScope.tableBean.dataType =='1'}">
				datagridFormSearch(gridWindowSearch, formWindowSearch,url);
			</c:when>
			<c:when test="${requestScope.tableBean.dataType =='2'}">
				treegridFormSearch(gridWindowSearch, formWindowSearch,url);	
			</c:when>
		</c:choose>
}


/**
 * 确定
 */
function onDbClickRowDataSelect(){
	var row;	//当前选中行
		<c:choose>
			<c:when test="${requestScope.tableBean.dataType =='1'}">
				row = $("#"+gridWindowSearch).datagrid("getSelected");
				dataType = "dataGrid";
			</c:when>
			<c:when test="${requestScope.tableBean.dataType =='2'}">
				row = $("#"+gridWindowSearch).treegrid("getSelected");
				dataType = "treeGrid";	
			</c:when>
			<c:when test="${requestScope.tableBean.dataType =='3'}">
				row = $("#"+gridWindowSearch).tree("getSelected");	
				dataType = "tree";
			</c:when>
		</c:choose>
 
	 //判断有没有主键字段，有：则对它赋值（隐藏idField字段赋值）
	 if (!isEmpty("${formListBean.tagId}") && !isEmpty("${formListBean.idField}")) {
		 setValue2TagId("${formListBean.tagId}"+"${formListBean.idField}","input", eval("row.${formListBean.idField}"));
	 }
	 var returnObj;//返回对象
	 if('${formListBean.inputValidate}'=='true'){//业务类型：true手工输入后，自动开启验证调用；false是弹出框查询条用。
		 //不需要转换也能实现
		 //returnObj = json2Str(row);
		 returnObj = row;
	 } else {
		 returnObj = row;
	 }
	 var map = new HashMap();
	 map.put("rowObj",returnObj);
	 map.put("paramsObj",'${formListBean.customMethodParams}');//父页面参数：如   rowIndex
	 eval('${formListBean.customMethod}')(map);
	 //执行回调函数
	 
 if('${formListBean.isCustomPage}'=='false'){//功能构建界面调用
	//关联字段赋值   根据配置的关联关系表自动赋值
	<c:forEach var="bean" items="${relationListBean}" >
	var ${bean.paramObj} = "";
		
		if(${bean.paramObj}==""){
			${bean.paramObj}=row.${bean.paramObj};	
		}else{			
			${bean.paramObj}+=","+row.${bean.paramObj};
		}
		
	
	//传参
	<c:if test="${bean.relationWay=='1'}">
	var inputType = $("#${bean.toFieldId}").attr("class");

	if(inputType=='sign-combobox'){
		setLabel2TagId("${bean.toFieldId}", "combogrid",${bean.paramObj});	
	}else{
		setLabel2TagId("${bean.toFieldId}", "input",${bean.paramObj});
	}
	</c:if>
	//只读
	<c:if test="${bean.relationWay=='2'}">
	 $("#${bean.toFieldId}").prop("readonly",true);
	</c:if>
	//隐藏
	<c:if test="${bean.relationWay=='3'}">
	 $("#${bean.toFieldId}").hide();
	</c:if>
	//传参级联(为url赋值)
	<c:if test="${bean.relationWay=='4'}">
	var url = $("#${bean.toFieldId}").combogrid('options').url;
	//alert(url); 每次新请求居然还是保留的原有url,不需截取url参数.
		<c:if test="${bean.isconstant=='1'}">//常量赋值
		url+="&${bean.paramObj}"+"=${bean.paramValue}"//输出为字符串
		</c:if>
		<c:if test="${bean.isconstant=='0' and bean.paramValue!=''}">//字段赋值
		url+="&${bean.paramObj}"+"="+row.${bean.paramValue};//输出为变量
		</c:if>
	reloadCombogrid('${bean.toFieldId}',url);  // 使用新的URL重新载入Combogrid列表数据
	</c:if>
	//dataGrid赋值参数拼装
	<c:if test="${bean.relationWay=='5'}">		
 	<c:set  value="${rowParam}${bean.toFieldId}:row.${bean.paramObj}," var="rowParam"/>
 	<c:set  value="${bean.datagridId}" var="datagridId"/> 	
	</c:if>
	</c:forEach>
	//根据rowParam为dataGrid赋值
	if('${rowParam}'!=''){
	 	$('#${datagridId}').datagrid('appendRow',{
	 		${rowParam}
		});	
	}	
 }
 
 $('#popWindowThree').window('close');
}

var isqueryFlag = true;//是否执行默认查询
/**
 * 选中已选择记录
 */
function selectRow(){
	//根据配置的查询value对应的字段名,自动查询框赋值
	if(isqueryFlag && "${formListBean.queryInputObj}"!=""){	
			//如果常量有值 优先常量值赋值
			if("${formListBean.queryConstantValue}"!=""){
				setValue2TagId("${formListBean.queryInputObj}", "text","${formListBean.queryConstantValue}")//取常量值
			   //$("#${formListBean.queryInputObj}").val("");
			}else if("${formListBean.queryValue}"!=""){			
			   //$("#${formListBean.queryInputObj}").val($("#${formListBean.queryValue}").val());//取指定对象的值
			   setValue2TagId("${formListBean.queryInputObj}", "text",getValue2TagId("${formListBean.queryValue}", "text"))//取指定对象的值
			}
			//doSearchWindowSearch();
			isqueryFlag = false;
	}	
	
<c:choose>
	<c:when test="${requestScope.tableBean.dataType =='1'}">
		if(getValue2TagId("${formListBean.fieldValue}", "text")!=''){//只针对datagrid进行已选值选中。
			$("#"+gridWindowSearch).datagrid('selectRecord',getValue2TagId("${formListBean.fieldValue}", "text"));
		}
	</c:when>
</c:choose>
	
}

</script>

<div class="easyui-layout" fit="true">
<div data-options="region:'center'">
	<c:if test="${requestScope.tableBean.dataType =='2' || requestScope.tableBean.dataType =='1'}">
	<eu:toolbar id="gridToolbarWindowSearch"  showToolbarForm="false">
		<eu:form id="listFormWindowSearch"  >
		<!-- table	最多设计为三列<td></td> -->
		<table class="formTable">
			<jsp:include page="/WEB-INF/plugin/report/commFormTagView.jsp" />	
						<td class="button"  align="center">
							<eu:button id="searchButtonWindowSearch" titleKey="common.button.search" 
								iconClass="icon-search" onClick="javascript: doSearchWindowSearch();" />
						</td>
		</table>
		</eu:form>
	</eu:toolbar>
	</c:if>
<c:if test="${requestScope.tableBean.dataType =='1'}">
<eu:table id="${requestScope.tableBean.tableName}"   idField="${requestScope.tableBean.idField}" 
	url="jsonDo.do?method=toJsonDo&businessName=GETSEARCHPAGE_PAGELISTER&recordFlag=${requestScope.recordFlag }&searchPageId=${requestScope.formListBean.searchPageId }&tableId=${requestScope.tableBean.id}&dataType=${requestScope.tableBean.dataType}&idField=${requestScope.tableBean.idField }&sqlNum=${requestScope.tableBean.sqlNum }&sqlParams=${requestScope.tableBean.sqlParams }" pagination="${requestScope.tableBean.pagination=='1' }"  
		fit="${requestScope.tableBean.fit=='1' }" fitColumns="${requestScope.tableBean.fitColumns=='1'}"	toolbarId="gridToolbarWindowSearch" autoReload="${requestScope.tableBean.isautoReload=='1' }" stopReload="${requestScope.tableBean.isstopReload=='1' }"
		singleSelect="true" mergeCellsKeys="${requestScope.tableBean.mergeCellsKeys }"	mergeCellsFields="${requestScope.tableBean.mergeCellsFields }"  onDbClickRow="onDbClickRowDataSelect" onLoadSuccess="selectRow">
		<c:forEach var="bean" 	 items="${requestScope.columnList }" >
				<eu:column	field="${bean.columnName }"  title="${bean.columnTitle }"	 width="${bean.width }" 
						align="${bean.align }" hidden="${bean.ishidden == '1'}"  formatter="${bean.formatter}" />
		</c:forEach>
</eu:table>
</c:if>

<c:if test="${requestScope.tableBean.dataType =='2' }">
<eu:treetable id="${requestScope.tableBean.tableName }"  
 					url="jsonDo.do?method=toJsonDo&businessName=GETSEARCHPAGE_PAGELISTER&recordFlag=${requestScope.recordFlag }&searchPageId=${requestScope.formListBean.searchPageId }&tableId=${requestScope.tableBean.id}&dataType=${requestScope.tableBean.dataType}&idField=${requestScope.tableBean.idField }&sqlNum=${requestScope.tableBean.sqlNum }&sqlParams=${requestScope.tableBean.sqlParams }" 	pagination="${requestScope.tableBean.pagination=='1' }" 
 					 collapsible="${requestScope.tableBean.collapsible=='1' }" fit="${requestScope.tableBean.fit=='1' }" idField="${requestScope.tableBean.idField }" treeField="${requestScope.tableBean.treeField }"  
 					singleSelect="true" fitColumns="${requestScope.tableBean.fitColumns=='1' }" toolbarId="gridToolbarWindowSearch"   autoReload="${requestScope.tableBean.isautoReload=='1' }"  onDbClickRow="onDbClickRowDataSelect" stopReload="${requestScope.tableBean.isstopReload=='1' }">
	<c:forEach var="bean" 	 items="${requestScope.columnList }" >
	 	<eu:column	 field="${bean.columnName }" 	 title="${bean.columnTitle }"	 width="${bean.width }"  
					 align="${bean.align }" hidden="${bean.ishidden == '1'}"  formatter="${bean.formatter}" />
	</c:forEach> 
</eu:treetable>  

</c:if>


</div>

<div data-options="region:'south'"  style="height:33px;">
<div id="popupPanelBottom" style="left: 0px;bottom: 0px;right: 0px;">
		<div id="buttonSection">
			<eu:button id="commitButton" title="确认" iconClass="icon-save"
 				onClick="onDbClickRowDataSelect();" /> 
 			<eu:button titleKey="common.button.close" iconClass="icon-cancel" 
 				onClick="javascript:$('#popWindowThree').window('close');" />
		</div>
</div>
</div>

</div>



