<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.tristone.platform.general.constant.GlobalConstant"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html>

<%-- <jsp:include page="/jsp/common/jspTop.jsp"/>	 --%>

<script type="text/javascript">

//页面grid名称
var gridWindowSearch = "${requestScope.tableBean.tableName}";
var gridMainWindowSearch = "${requestScope.tableBeanMain.tableName}";

//页面form名称
var formWindowSearch = "listFormWindowSearch";
var formMainWindowSearch = "listFormMainWindowSearch";
/*
* 主表 点击"查询"
* @param grid 页面grid名称
* @param form 页面form名称
*/
function doSearchMainWindowSearch() {
	<c:choose>
	<c:when test="${requestScope.tableBeanMain.dataType =='1'}">
		datagridFormSearch(gridMainWindowSearch, formMainWindowSearch);
	</c:when>
	<c:when test="${requestScope.tableBeanMain.dataType =='2'}">
		//treegridFormSearch(gridMainWindowSearch, formMainWindowSearch);	
	</c:when>
</c:choose>
}
/*
* 从表 点击"查询"
* @param grid 页面grid名称
* @param form 页面form名称
*/
function doSearchWindowSearch() {
	<c:choose>
	<c:when test="${requestScope.tableBean.dataType =='1'}">
		datagridFormSearch(gridWindowSearch, formWindowSearch);
	</c:when>
	<c:when test="${requestScope.tableBean.dataType =='2'}">
		treegridFormSearch(gridWindowSearch, formWindowSearch);	
	</c:when>
</c:choose>
}

/**
 * 确定
 */
function onDbClickRowDataSelect(){
	var mainRow ; //主表选中行
	var rows = $('#selectDataGrid').datagrid('getRows');//已选区域中的行		固定名称datagrid:selectDatagrid
	 
	<c:choose>
		<c:when test="${requestScope.tableBeanMain.dataType =='1'}">
		 	mainRow = $("#"+gridMainWindowSearch).datagrid("getSelected");//主表选中行
		</c:when>
		<c:when test="${requestScope.tableBeanMain.dataType =='2'}">
	 		mainRow = $("#"+gridMainWindowSearch).treegrid("getSelected");//主表选中行
		</c:when>
		<c:when test="${requestScope.tableBeanMain.dataType =='3'}">
			mainRow = $("#"+gridMainWindowSearch).tree("getSelected");//主表选中行
		</c:when>
	</c:choose>
	
	 var returnObj;
	 var returnMainObj;
	

	 if('${formListBean.inputValidate}'=='true'){//自定义开发界面调用
		 //不需要转换也能实现
		 //returnObj = json2Str(rows);
		 returnObj = rows;
		 returnMainObj = mainRow;//主表记录不用转换
	 } else {
		 returnObj = rows;
		 returnMainObj = mainRow;
	 }

	 //执行回调函数
	 var map = new HashMap();
	 map.put("rowObj",returnObj);//多选的子对象行数据
	 map.put("mainObj",returnMainObj);//主对象
	 map.put("paramsObj",'${formListBean.customMethodParams}');//父页面参数：如   rowIndex
	 eval('${formListBean.customMethod}')(map);
	 
  if('${formListBean.isCustomPage}'=='false'){//功能构建界面调用
	
	//关联字段赋值
	<c:forEach var="bean" items="${relationListBean}" >
	var ${bean.paramObj} = "";
	for(var i=0;i<rows.length;i++){		
		if(${bean.paramObj}==""){
			${bean.paramObj}=rows[i].${bean.paramObj};	
		}else{			
			${bean.paramObj}+=","+rows[i].${bean.paramObj};
		}		
	}	
	//alert('${bean.relationWay}');
	//传参
	<c:if test="${bean.relationWay=='1'}">
	var inputType = $("#${bean.toFieldId}").attr("class");	
	if(inputType && inputType.indexOf('sign-combobox')>=0){	
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
		var ${bean.paramValue} = "";
		for(var i=0;i<rows.length;i++){//多选情况,循环赋值,(分隔符,	)
			if(${bean.paramValue}==""){
				${bean.paramValue}=rows[i].${bean.paramValue};	
			}else{			
				${bean.paramValue}+=","+rows[i].${bean.paramValue};
			}			
		}	
		url+="&${bean.paramObj}"+"="+${bean.paramValue};//输出为变量
		</c:if>	
	reloadCombogrid('${bean.toFieldId}',url);  // 使用新的URL重新载入Combogrid列表数据
	</c:if>
	//dataGrid赋值参数拼装
	<c:if test="${bean.relationWay=='5'}">		
 	<c:set value="${rowParam}${bean.toFieldId}:rows[i].${bean.paramObj}," var="rowParam"/>
 	<c:set value="${bean.datagridId}" var="datagridId"/> 	
	</c:if>
	</c:forEach>
	//根据rowParam为dataGrid赋值
	if('${rowParam}'!=''){
	for(var i=0;i<rows.length;i++){//多选情况
	 	$('#${datagridId}').datagrid('appendRow',{
	 		${rowParam}
		});
	}
	}
 }
	
  $('#popWindowThree').window('close');
}

/**
 * 选择
 */
function doDoubleClickAdd(){
	
	//showProcess(true);
	var selectRows = $("#"+gridWindowSearch).datagrid("getSelections");
	var rows = $("#selectDataGrid").datagrid("getRows");
	
	
		
	for(var i=0;i<selectRows.length;i++){
		var data=selectRows[i];
		var flag = true;
		for(var j=0;j<rows.length;j++){			
			//alert(rows[i]["${requestScope.tableBean.idField}"]);
			//alert("data.${requestScope.tableBean.idField}");
			
			if (rows[j]["${requestScope.tableBean.idField}"] == data.${requestScope.tableBean.idField}) {
				euShow("不要重复添加");	
				flag=false;
			}
		}
		if(flag){
			$('#selectDataGrid').datagrid('appendRow',{
					<c:forEach var="bean" items="${requestScope.columnList }" >			
					<c:if test="${bean.isshow==1}">
						${bean.columnName }: data.${bean.columnName },
					</c:if>
					</c:forEach>
			});	
			//alert("unselect")
			//var rowIndex=$("#"+gridWindowSearch).datagrid("getRowIndex",data);
			//alert(rowIndex);
			//$("#"+gridWindowSearch).datagrid("unselectRow",rowIndex);
		
		}
	}
	
	clearSelect2Dg(gridWindowSearch);
	//showProcess(false);
}
/**
 * 删除
 */
function doDeleteSelect(){
	deleteSelectRows2Dg("selectDataGrid");
	
}

/**
 * 选择全部
 */
function doDoubleClickAddAll(){
	$("#"+gridWindowSearch).datagrid('selectAll');
	doDoubleClickAdd();
}

/**
 * 取消全部
 */
function doDeleteSelectAll(){
	$("#selectDataGrid").datagrid('selectAll');
	doDeleteSelect();
}


var isqueryFlag = true;//是否执行默认查询
/**
 * 选中已选择记录（该方法作废）
 */
function selectGridRowBAKBAK(){
	
	
//根据配置的查询value对应的字段名,自动查询框赋值
if(isqueryFlag && "${formListBean.queryInputObj}"!=""){	
		//如果常量有值 优先常量值赋值
		if("${formListBean.queryConstantValue}"!=""){
			setValue2TagId("#${formListBean.queryInputObj}", "text","${formListBean.queryConstantValue}");
		   //$("#${formListBean.queryInputObj}").val("${formListBean.queryConstantValue}");//取常量值
		}else if("${formListBean.queryValue}"!=""){			
			setValue2TagId("${formListBean.queryInputObj}", "text",getValue2TagId("${formListBean.queryValue}", "text"));
		  // $("#${formListBean.queryInputObj}").val($("#${formListBean.queryValue}").val());//取指定对象的值
		}		
		doSearchWindowSearch();
		isqueryFlag = false;
}
	
if(!isEmpty(getValue2TagId("${formListBean.fieldId}", "text")) && !isEmpty(getValue2TagId("${formListBean.fieldValue}", "text"))){
	var fieldId = getValue2TagId("${formListBean.fieldId}", "text").split(",");
	var fieldValue = getValue2TagId("${formListBean.fieldValue}", "text").split(",");
	
	for(var i=0;i<fieldValue.length;i++){
		
		var rows = $("#selectDataGrid").datagrid("getRows");
		var flag = true;
				for(var j=0;j<rows.length;j++){					
					if (rows[j]["${requestScope.tableBean.idField}"]== fieldValue[i]) {						
						flag=false;
					}
				}
				
		if(flag){
			$('#selectDataGrid').datagrid('insertRow',{
				index:i,
				row: {										//要求 关联字段列表参数对象 与弹窗搜索表格字段配置名称一致
					<c:forEach var="bean" items="${relationListBean}" >
						<c:if test="${bean.toFieldId eq formListBean.fieldValue}">
						${bean.paramObj}: fieldValue[i],	//对应具体值(如:001)
						</c:if>	
						<c:if test="${bean.toFieldId eq formListBean.fieldId}">
						${bean.paramObj}: fieldId[i],		//对应具体值(如:001)
						</c:if>	
					</c:forEach>					
					
				}
			});	
		}
	}
}
}


/**
 * 点击主表选择事件
 */
function onClickMainRowSelect(){	
	var row ;
	<c:if test="${!empty tableBeanMain}">
		<c:choose>
		<c:when test="${requestScope.tableBeanMain.dataType =='1'}">
			row = $("#${requestScope.tableBeanMain.tableName}").datagrid("getSelected");
		</c:when>
		<c:when test="${requestScope.tableBeanMain.dataType =='2'}">
			row = $("#${requestScope.tableBeanMain.tableName}").treegrid("getSelected");
		</c:when>
		<c:when test="${requestScope.tableBeanMain.dataType =='3'}">
			row = $("#${requestScope.tableBeanMain.tableName}").tree("getSelected");	
		</c:when>		
		</c:choose>
		
		var fieldMain = "${requestScope.tableBeanMain.relevancyFieldMain}".split(",");		
		var fieldChr  = "${requestScope.tableBeanMain.relevancyFieldChr}".split(",");		
		var params = "";		
		for(i=0;i<fieldMain.length;i++){
		 params += "&"+fieldChr[i]+"="+eval("row."+fieldMain[i]);		
		}
		
		var url = "jsonDo.do?method=toJsonDo&businessName=GETSEARCHPAGE_PAGELISTER&recordFlag=${requestScope.recordFlag }&searchPageId=${requestScope.formListBean.searchPageId }&tableId=${requestScope.tableBean.id}&dataType=${requestScope.tableBean.dataType}"+params;
		
		<c:choose>
		<c:when test="${requestScope.tableBean.dataType =='1'}">
			reloadDatagrid("${requestScope.tableBean.tableName}", url);
		</c:when>
		<c:when test="${requestScope.tableBean.dataType =='2'}">
			reloadTreegrid("${requestScope.tableBean.tableName}", url);	
		</c:when>
		<c:when test="${requestScope.tableBean.dataType =='3'}">
			//以后tree也要支持重新加载url
			reloadTree("${requestScope.tableBean.tableName}");
		</c:when>		
		</c:choose>
		
		
		$("#${requestScope.tableBean.tableName}").datagrid('unselectAll');
		if("${formListBean.singleSelect}"=="1"){//单父节点多选			
			//var mainRow = $("#${requestScope.tableBeanMain.tableName}").datagrid("getSelected");
			//var mid = mainRow.ID;			
			doDeleteSelectAll();
		}
	</c:if>
}
</script>

<div class="easyui-layout" fit="true">  <!-- 多选只能上下布局,左右布局含有3个grid+选择栏div,不够排列 -->

<c:if test="${requestScope.tableBeanMain.laytoutType =='1'}">
<div data-options="region:'north',split:true,title:' ',collapsible:true" style="height:200px;"> 
</c:if>
<c:if test="${requestScope.tableBeanMain.laytoutType =='2'}">
<div data-options="region:'west',split:true,title:' ',collapsible:true" style="width:220px;">
</c:if>

<c:if test="${requestScope.tableBeanMain.dataType =='1'}">
<!-- 主表布局支持dataGrid --><!-- 有主表才有布局 -->

<eu:table id="${requestScope.tableBeanMain.tableName}"   idField="${requestScope.tableBeanMain.idField}" 
	url="jsonDo.do?method=toJsonDo&businessName=GETSEARCHPAGE_PAGELISTER&recordFlag=${requestScope.recordFlag }&searchPageId=${requestScope.formListBeanMain.searchPageId }&tableId=${requestScope.tableBeanMain.id}&dataType=${requestScope.tableBeanMain.dataType}&idField=${requestScope.tableBeanMain.idField}&sqlNum=${requestScope.tableBeanMain.sqlNum }&sqlParams=${requestScope.tableBeanMain.sqlParams }" 
	pagination="${requestScope.tableBeanMain.pagination=='1' }"  
	fit="${requestScope.tableBeanMain.fit=='1' }" 
	fitColumns="${requestScope.tableBeanMain.fitColumns=='1' }"	
	toolbarId="gridToolbarWindowSearch"
	autoReload="${requestScope.tableBeanMain.isautoReload=='1' }" 
	stopReload="${requestScope.tableBeanMain.isstopReload=='1' }"
	mergeCellsKeys="${requestScope.tableBeanMain.mergeCellsKeys }"	
	mergeCellsFields="${requestScope.tableBeanMain.mergeCellsFields }"
	onClickRow="onClickMainRowSelect">
	<c:forEach var="bean" 	 items="${requestScope.columnListMain }" >
			<eu:column	field="${bean.columnName }"  title="${bean.columnTitle }"	 width="${bean.width }" 
					align="${bean.align }" hidden="${bean.ishidden == '1'}"  formatter="${bean.formatter}" />
	</c:forEach>
</eu:table>
</c:if>
<c:if test="${requestScope.tableBeanMain.dataType =='2'}">
<!-- 主表布局支持treeGrid --><!-- 有主表才有布局 -->
<eu:treetable id="${requestScope.tableBeanMain.tableName }"  
 					url="jsonDo.do?method=toJsonDo&businessName=GETSEARCHPAGE_PAGELISTER&recordFlag=${requestScope.recordFlag }&searchPageId=${requestScope.formListBean.searchPageId }&tableId=${requestScope.tableBeanMain.id}&dataType=${requestScope.tableBeanMain.dataType}&idField=${requestScope.tableBeanMain.idField}&sqlNum=${requestScope.tableBeanMain.sqlNum }&sqlParams=${requestScope.tableBeanMain.sqlParams }" 	pagination="${requestScope.tableBeanMain.pagination=='1' }" 
 					 collapsible="${requestScope.tableBeanMain.collapsible=='1' }" fit="${requestScope.tableBeanMain.fit=='1' }" idField="${requestScope.tableBeanMain.idField }" treeField="${requestScope.tableBeanMain.treeField }"
 					autoReload="${requestScope.tableBeanMain.isautoReload=='1' }" fitColumns="${requestScope.tableBeanMain.fitColumns=='1' }" toolbarId="gridToolbarWindowSearch"  onClickRow="onClickMainRowSelect">
	<c:forEach var="bean" 	 items="${requestScope.columnListMain }" >
				<eu:column	field="${bean.columnName }"  title="${bean.columnTitle }"	 width="${bean.width }" 
						align="${bean.align }" hidden="${bean.ishidden == '1'}"  formatter="${bean.formatter}" />
	</c:forEach> 
 </eu:treetable>  
</c:if>
<c:if test="${requestScope.tableBeanMain.dataType =='3'}">
<!-- 主表布局支持tree--><!-- 有主表才有布局 -->
<eu:tree id="${requestScope.tableBeanMain.tableName }"  
 					url="jsonDo.do?method=toJsonDo&businessName=GETSEARCHPAGE_PAGELISTER&recordFlag=${requestScope.recordFlag }&searchPageId=${requestScope.formListBean.searchPageId }&tableId=${requestScope.tableBeanMain.id}&dataType=${requestScope.tableBeanMain.dataType}&idField=${requestScope.tableBeanMain.idField}&sqlNum=${requestScope.tableBeanMain.sqlNum }" 
 					collapseAll="${requestScope.tableBeanMain.collapsible=='1' }" isselectRoot="${requestScope.tableBeanMain.isselectRoot =='1' }" onClick="onClickMainRowSelect" > 
</eu:tree>  
</c:if>

<c:if test="${requestScope.tableBeanMain.laytoutType =='1' || requestScope.tableBeanMain.laytoutType =='2'}">
</div>
</c:if>
<div data-options="region:'center',title:'待选择项'" >

<div class="easyui-layout" fit="true">
    
<div data-options="region:'center'">
<div class="easyui-layout" fit="true">

<div data-options="region:'center'">
<c:if test="${requestScope.tableBean.dataType =='2' || requestScope.tableBean.dataType =='1'}">
<eu:toolbar id="gridToolbarWindowSearch"  showToolbarForm="false">
	<eu:form id="listFormWindowSearch"  >
	<!-- table	最多设计为三列<td></td> -->
	<table class="formTable">
		<jsp:include page="/WEB-INF/plugin/report/commFormTagView.jsp" />
					<td class="button"  align="center">
						<eu:button id="searchButtonWindowSearch" 
							titleKey="common.button.search"
							iconClass="icon-search" onClick="javascript: doSearchWindowSearch();" />
					</td>
	</table>
	</eu:form>
</eu:toolbar>
</c:if>
<c:if test="${requestScope.tableBean.dataType =='1' }">
<eu:table id="${requestScope.tableBean.tableName}"  idField="${requestScope.tableBean.idField}" 
	url="jsonDo.do?method=toJsonDo&businessName=GETSEARCHPAGE_PAGELISTER&recordFlag=${requestScope.recordFlag }&searchPageId=${requestScope.formListBean.searchPageId}&tableId=${requestScope.tableBean.id}&dataType=${requestScope.tableBean.dataType}&idField=${requestScope.tableBean.idField }&sqlNum=${requestScope.tableBean.sqlNum }&sqlParams=${requestScope.tableBean.sqlParams }"
	pagination="${requestScope.tableBean.pagination=='1' }"  
	fit="${requestScope.tableBean.fit=='1' }" 
	fitColumns="${requestScope.tableBean.fitColumns=='1' }"	
	toolbarId="gridToolbarWindowSearch" singleSelect="false"
	autoReload="${requestScope.tableBean.isautoReload=='1' }" 
	stopReload="${requestScope.tableBean.isstopReload=='1' }"
	mergeCellsKeys="${requestScope.tableBean.mergeCellsKeys }"	
	mergeCellsFields="${requestScope.tableBean.mergeCellsFields }"  >
		<eu:column field="pkCheck" checkbox="true" ></eu:column>
		<c:forEach var="bean" 	 items="${requestScope.columnList }" >
				<eu:column	field="${bean.columnName }"  title="${bean.columnTitle }"	 width="${bean.width }" 
						align="${bean.align }" hidden="${bean.ishidden == '1'}"  formatter="${bean.formatter}" />
		</c:forEach>
</eu:table>
</c:if>

<c:if test="${requestScope.tableBean.dataType =='2' }">
<eu:treetable id="${requestScope.tableBean.tableName }" 
 					url="jsonDo.do?method=toJsonDo&businessName=GETSEARCHPAGE_PAGELISTER&recordFlag=${requestScope.recordFlag }&searchPageId=${requestScope.formListBean.searchPageId }&tableId=${requestScope.tableBean.id}&dataType=${requestScope.tableBean.dataType}&idField=${requestScope.tableBean.idField }&sqlNum=${requestScope.tableBean.sqlNum }&sqlParams=${requestScope.tableBean.sqlParams }" 	pagination="${requestScope.tableBean.pagination=='1' }" 
 					collapsible="${requestScope.tableBean.collapsible=='1' }" fit="${requestScope.tableBean.fit=='1' }" idField="${requestScope.tableBean.idField }" treeField="${requestScope.tableBean.treeField }"  singleSelect="false"
 					fitColumns="${requestScope.tableBean.fitColumns=='1' }" toolbarId="gridToolbarWindowSearch"  autoReload="${requestScope.tableBean.isautoReload=='1' }" stopReload="${requestScope.tableBean.isstopReload=='1' }">
	<c:forEach var="bean" 	 items="${requestScope.columnList }" >
	 	<eu:column	 field="${bean.columnName }" 	 title="${bean.columnTitle }"	 width="${bean.width }"  
					 align="${bean.align }" hidden="${bean.ishidden == '1'}"  formatter="${bean.formatter}" />
	</c:forEach> 
 </eu:treetable>  

</c:if>

</div>
<div id="east" region="east" style="width: 34px; padding: 1px; background: #eee; text-align: center; padding-top: 100px;">
		
		<eu:button iconClass="icon-pagination_next" plain="true"
			onClick="javaScript: doDoubleClickAdd();" titleText="选择勾选部分" />
		<eu:button iconClass="icon-pagination_prev" plain="true"
			onClick="javaScript: doDeleteSelect();" titleText="取消勾选部分" />
		<eu:button iconClass="icon-pagination_last" plain="true"
			onClick="javaScript: doDoubleClickAddAll();" titleText="选择全部" />
		<eu:button iconClass="icon-pagination_first" plain="true"
			onClick="javaScript: doDeleteSelectAll();" titleText="取消全部" />
</div>
</div>

	
	
</div>

<div data-options="region:'east',title:'已选择项',split:'true',collapsible:false" style="width:350px;">
	
		<eu:table id="selectDataGrid" idField="${requestScope.tableBean.idField}" url=""   pagination="false" fit="true" singleSelect="false" fitColumns="${requestScope.tableBean.fitColumns=='1' }"  rowTooltip="false" >
			<eu:column field="pkCheck" checkbox="true" ></eu:column>
			<c:forEach var="bean" 	 items="${requestScope.columnList }" >			
				<c:if test="${bean.isshow==1}">
				<eu:column	field="${bean.columnName }"  title="${bean.columnTitle }"	 width="${bean.width }" 
						align="${bean.align }" hidden="${bean.ishidden == '1'}"  formatter="${bean.formatter}" />
				</c:if>
			</c:forEach>
		</eu:table>

</div>

</div>
</div>

<div id="south" region="south" style="height:35px;">
<div id="popupPanelBottom">
		<div id="buttonSection">
			<eu:button id="commitButton" title="确认" iconClass="icon-save"
 				onClick="onDbClickRowDataSelect();" /> 
 			<eu:button titleKey="common.button.close" iconClass="icon-cancel" 
 				onClick="javascript:$('#popWindowThree').window('close');" />
		</div>
</div>


</div>


</div>


