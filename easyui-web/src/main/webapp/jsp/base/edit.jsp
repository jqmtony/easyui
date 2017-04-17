<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@page import="com.zen.easyui.common.constant.GlobalConstant"%>
<jsp:include page="/easyui/common/toEditPage"></jsp:include>

<script type="text/javascript">
var loadJsonTempForm = new Array();
$(document).ready(function(){
	var lastForm = getLastWindowCurrentForm();
	//alert("loadJsonTempForm=" + loadJsonTempForm + "   ,  document getLastWindowCurrentForm=" + lastForm);
	//easyui所有对象解析完成后，触发事件
    $.parser.onComplete = function () {
		if (isEmpty(loadJsonTempForm) || loadJsonTempForm.indexOf(lastForm)<0) {
			//alert("execute editCommon begin" );
			var formJson = ${requestScope.formJson!= null? requestScope.formJson : "''"};
			//为修改界面，自动赋值到文本框
			if(${requestScope.editFlag != null} && ${requestScope.editFlag == "update"} && !isEmpty(formJson)){
				loadJsonTempForm.push(lastForm);
				//alert("unloadCombogridNum="+unloadCombogridNum + "--------unloadCombotreeNum="+unloadCombotreeNum);
				if (unloadCombotreeNum>0) {
					//alert("unloadCombotreeNum>0");
					setIntervalIdLoadFormJson = window.setInterval("readyLoadFormJson4Combotree()",200);
				} else if (unloadCombogridNum>0) {
					//alert("window.setInterval ");
					setIntervalIdLoadFormJson = window.setInterval("readyLoadFormJson4Combogrid()",200);
				} else {
					//alert("unloadCombogridNum loadFormJson4EditPage begin==========");
					loadFormJson4EditPage();
				}

				//alert("setTimeout('isAutoReloadDatagrid()')");
				//检查是否刷新（修改页面）
				setTimeout('isAutoReloadDatagrid()',200);
			}else{
				//alert("editFlag=" + '${requestScope.editFlag}' +"fromJson=" + '${requestScope.formJson}');
				//alert("setTimeout('isAutoCombogrid()',200)");
				//下拉框根据父界面自动定位(添加时),定位完后，再进行页面数据列表加载。
				setTimeout('isAutoCombogrid()',200);
			}
			//alert("setTimeout('executeReflectFun()',500)");
			setTimeout('executeReflectFun()',500);
			//编辑页面弹出时，光标定位第一文本框
			$(".popupWindowContainer").find(".columnData input[type=text]:not([readonly])").not($(".searchbox-text")).first().focus();

		}
}
});
/**
 * 加载formJson到编辑页面
 * 注意：此处formJson不能去掉evalObj，简易功能构建会出错。原因暂时不知道。
 */
function loadFormJson4EditPage() {
	//alert("execute loadFormJson4EditPage begin");
	var formJson = ${requestScope.formJson!= null? requestScope.formJson : "''"};
	if (!isEmpty(formJson)) {
		//alert("loadFormJson4EditPage formJson is not null ===="+formJson);

		if(${requestScope.editFormId != null}){
			loadFormJson("${requestScope.editFormId}", formJson);
		}else if (${requestScope.formNum != null}) {
			if (${requestScope.formNum == -1}) {
				//循环所有form赋值
				$(".popupWindowContainer form").each(function(index){
					loadFormJsonObj($(".popupWindowContainer form")[index], formJson);
				});
			} else {
				//parseInt("${requestScope.formNum}")加载第几个FORM
				//alert("加载第几个FORM");
				loadFormJsonObj($(".popupWindowContainer form")[parseInt("${requestScope.formNum}")], formJson);
			}
		} else {
			var currentForm = getLastWindowCurrentForm();
			if (!isEmpty(currentForm) && formJson!= null) {
				//alert("loadFormJson=============begin! currentForm="+currentForm);
				//loadFormJson(currentForm, evalObj(formJson));
				loadFormJson(currentForm, formJson);
				//alert("loadFormJson=============end!");
			}
			//当数据赋值完成后，自动触发一次已选中对象的change事件//$("input:radio||checkbox:checked").change();
			$(currentForm).find("input:checked").each(function(){
					$(this).change();
			});
		}
	}
}
/**
 * 自动触发datagrid或treegrid刷新
 */
function isAutoReloadDatagrid(){
	//下拉框定位数据后，执行一次查询
	var searchButton = getCurrentPageSearchButton();
	//alert("searchButton.size()="+searchButton.size() + ",filterSearchButtonRecord="+filterSearchButtonRecord);
	searchButton.each(function(i){
		//alert("each searchButton."+i+" id="+$(this).attr("id"));
	})
	//判断是否存在“查询”按钮，存在，则执行
	if((${requestScope.doSearch == null} || ${requestScope.doSearch != "false"}) && searchButton.size()!=0
			&& filterSearchButtonRecord.indexOf(searchButton.attr("id"))<0){
		//alert("unloadCombogridNum="+unloadCombogridNum+",unloadDatagridIds="+unloadDatagridIds + "searchButton.id=" + searchButton.attr("id") + ", searchButton.size()="+searchButton.size());
		if (unloadCombogridNum<=0) {
			searchButton.each(function(i){
			    filterSearchButtonRecord += $(this).attr("id") + ",";
			})
			//alert("excutePageSearchButton ========searchButton.id=" + searchButton.attr("id"));
			excutePageSearchButton(searchButton);
		} else {
			setIntervalId = window.setInterval("runPageSearchFun()",200);
		}
	} else {
		//不存在“查询”按钮，判断是否存在datagrid或treegrid，如果存在，则自动刷新
		//alert("unloadDatagridIds="+unloadDatagridIds + "and !!!!  searchButton.size()="+searchButton.size());
		//datagrid url加载
		if (!isEmpty(unloadDatagridIds)) {
			var datagrids = unloadDatagridIds.split(",");
			for(i=0;i<datagrids.length;i++) {
				if (!isEmpty(datagrids[i])) {
					//alert("datagrid.length="+datagrids.length+",datagrids[i]="+datagrids[i]);
					reloadDatagrid(datagrids[i]);
					//$("#" + datagrids[i]).datagrid("reload");
					}
				}
		}
		//treegrid url加载
		if (!isEmpty(unloadTreegridIds)) {
				var treegrids = unloadTreegridIds.split(",");
				for(i=0;i<treegrids.length;i++) {
					if (!isEmpty(treegrids[i])) {
						//alert("treegrids="+treegrids[i]);
						reloadTreegrid(treegrids[i]);
						}
					}
			}
	}	//完成后初始全局变量值
	unloadCombotreeIds = "";
	unloadCombogridIds = "";
	unloadCombogridNum = 0;
	//alert("after unloadCombogridNum="+unloadCombogridNum);
}






//----------------------------自动赋值定时循环判断--------------
var tempNumCombogrid = 0;	//combogrid加载的临时变量
var tempNumCombotree = 0;	//combotree加载的临时变量
var setIntervalIdCombogrid ;
var setIntervalIdLoadFormJson ;
var cycleNum = 6;
function isAutoCombogrid(){
	//alert("execute function isAutoCombogrid");
	var comp = $(".editTable select[class^='sign-combobox'][autoValueCombo='true']");
	if(${requestScope.autoCombogrid != "false"} && comp.size()!=0 && unloadCombogridNum>0){//是否有需要自动赋值的下拉框  出现isloadSuccess会为负数的情况，所以isloadSuccess>0
		if (unloadCombogridNum<=0) {
			//alert("unloadCombogridNum="+unloadCombogridNum + " execute autoCombogrid");
			autoCombogrid();
		} else {
			setIntervalIdCombogrid = window.setInterval("startAutoCombogridValidate()",200);
		}
	} else {
		//alert("unloadCombogridNum<0 execute isAutoReloadDatagrid");
		//检查是否刷新
		isAutoReloadDatagrid();
	}
}

/**
 * 自动设置下拉框的值（根据父页面的值自动定位子页面值）
 */
function startAutoCombogridValidate() {
	//alert("execute function startAutoCombogridValidate");
	if (tempNumCombogrid<=cycleNum) {
		if (unloadCombogridNum<=0) {
			window.clearInterval(setIntervalIdCombogrid);
			autoCombogrid();
		}
		 ++tempNumCombogrid;
		} else {
			window.clearInterval(setIntervalIdCombogrid);
			autoCombogrid();
		}
}

/**
 * 准备加载formJson到页面
 */
 function readyLoadFormJson4Combogrid() {
		if (tempNumCombogrid<=cycleNum) {
			//alert("tempNumCombogrid="+tempNumCombogrid+",unloadCombogridNum="+unloadCombogridNum);
			if (unloadCombogridNum<=0) {
				loadFormJson4EditPage();
				window.clearInterval(setIntervalIdLoadFormJson);
			}
			 ++tempNumCombogrid;
			} else {
				loadFormJson4EditPage();
				window.clearInterval(setIntervalIdLoadFormJson);
			}
	}
 /**
  * 准备加载formJson到页面
  */
  function readyLoadFormJson4Combotree() {
 		if (tempNumCombotree<=cycleNum) {
 			//alert("tempNumCombotree="+tempNumCombotree+",unloadCombotreeNum="+unloadCombotreeNum);
 			if (unloadCombotreeNum<=0) {
 				loadFormJson4EditPage();
 				window.clearInterval(setIntervalIdLoadFormJson);
 			}
 			 ++tempNumCombotree;
 			} else {
 				loadFormJson4EditPage();
 				window.clearInterval(setIntervalIdLoadFormJson);
 			}
 	}

//下拉框根据父界面自动定位
function autoCombogrid(){
	var comp = $(".editTable select[class^='sign-combobox'][autoValueCombo='true']");
	comp.each(function(i){
		var compid = $(".formTable  select[class^='sign-combobox'][comboname='"+$(this).attr('comboname')+"']").attr('id');
		if (!isEmpty(compid) && !isEmpty($("#"+compid).combogrid('getValue'))) {
			$(this).combogrid('setValue', $("#"+compid).combogrid('getValue'));
			$(this).attr("autoValueCombo","false");
			//alert(compid+"   "+$("#"+compid).combogrid('getValue'));
		}
	});
	//alert("after");
	//检查是否刷新
	isAutoReloadDatagrid();
}

</script>

<c:if test="${requestScope.editFlag == 'add' and (requestScope.continueAdd == null or requestScope.continueAdd == 'true')}">
<!-- 连续添加按钮 -->
<div id="popupPanelAddBottom" >
</div>
<script>
if($('#popupPanelBottom').size()>=1 && $('#buttonAddSection').size()==0){
	$('#popupPanelAddBottom').html(
	'<div id="buttonAddSection" ><div style="float: left;" >'
	+'<input type="radio" name="isContinueAddCheck" id="isContinueAddCheck"  value="1" checked/><label for="isContinueAddCheck">无</label>'
	+'<input type="radio" name="isContinueAddCheck" id="isContinueAddCheck2" value="2"/><label for="isContinueAddCheck2">连续新增</label>'
	+'<input type="radio" name="isContinueAddCheck" id="isContinueAddCheck3" value="3"/><label for="isContinueAddCheck3">复制新增</label>'
	+'</div></div>');
}
</script>
</c:if>