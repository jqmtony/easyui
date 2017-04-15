<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>

<html>
<head>
<jsp:include page="/jsp/common/jspTop.jsp"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/buttons.css">
<style type="text/css">
<!--
body {
	background:#3c7fb5 url(${pageContext.request.contextPath}/images/bg_login.jpg) repeat-x left top;
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
body,table,td,div {
	font-size: 12px;
	line-height: 24px;
}
.textfile {background:url(${pageContext.request.contextPath}/images/bg_login_textfile.gif) no-repeat left top; padding: 0px 2px; height: 29px; width: 140px; border: 0; }
-->
</style>

<script type="text/javascript">
function doLogin() {
	formSubmit("loginForm");
}

function resetForm() {
	clearForm("loginForm");
}
function refresh(){
    //重新获取验证码图片的src 属性
    document.getElementById("authImg").src = "servlet/AuthImg?now="+new Date();//加上now 是为了防止服务器缓存
   }
   
   function getSectionInfo(obj){
	   var userId = getValue2TagId("userId", "text"); 
       var url="login.do?method=getUserSectioninfoList&userId="+userId;
//        var url="cacheManager.do?method=getTriStoneCacheSqlList4System&cacheName=${requestScope.clientCacheName}";
       
//        url = "cacheManager.do?method=getTriStoneCacheSqlList4System&cacheName=${requestScope.clientCacheName}";
//        alert(url);
       //$('#sectionId').combobox('clear');
       
       
       $('#sectionId').combobox('reload',url);
   }
   

   
function  detectCapsLock(event){	
	var e = event||window.event;
	var o = e.target||e.srcElement;
	var oTip = o.nextSibling;
	var keyCode  =  e.keyCode||e.which; // 获取按键的keyCode
	var isShift  =  e.shiftKey ||(keyCode  ==   16 ) || false ;
	
	// 判断shift键是否按住
	if (
	((keyCode >=   65   &&  keyCode  <=   90 )  &&   !isShift)
	 	// Caps Lock 打开，且没有按住shift键
	|| ((keyCode >=   97   &&  keyCode  <=   122 )  &&  isShift)
		// Caps Lock 打开，且按住shift键
	){
		//oTip.style.display = '';
		$("#tip").html("大写锁定已打开!");
		$("#tip").show();
	 }
	else{
		//oTip.style.display  =  'none';
		$("#tip").hide();
		}
	}
	
</script>
</head>

<body>

<form:form action="${pageContext.request.contextPath}/login.do?method=login" commandName="loginForm" method="POST">
 <div style="margin-top: 10px;margin-right: 15px;text-align: right;">
<%-- <eu:combogrid id="language" name="language"    url="cacheManager.do?method=getTriStoneCacheList4Language&cacheName=${requestScope.languageTypeCacheName}" idField="value" textField="label"  >
							<eu:combogridcolumn field="value" title="编码"  titleKey="common.page.combogrid.id" hidden="true"/>
							<eu:combogridcolumn field="label" title="多语言" titleKey="common.page.combogrid.language" />
</eu:combogrid> --%>
</div> 

<table width="95" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td><img src="${pageContext.request.contextPath}/images/${UI_IMG}/top_login.jpg" width="595" height="321" /></td>
  </tr>
  <tr>
    <td>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="99"><img src="${pageContext.request.contextPath}/images/login_06.jpg" width="99" height="109" /></td>
        <td background="${pageContext.request.contextPath}/images/bg_form.jpg">
        <table width="250" border="0" align="center" cellpadding="0" cellspacing="0">
          
          <%-- <tr style="display: ${requestScope.clientController=='true'? '' : 'none'};">
            <td align="right"><spring:message code="login.client"/>：</td>
            <td>
            <eu:combogrid id="clientId" name="clientId" width="148"  url="cacheManager.do?method=getTriStoneCacheSqlList4System&cacheName=${requestScope.clientCacheName}" idField="ID" textField="CLENT_NAME"  >
							<eu:combogridcolumn field="ID" title="编码"  titleKey="common.page.combogrid.id" hidden="true"/>
							<eu:combogridcolumn field="CLENT_NAME" title="客户端版本" titleKey="common.page.combogrid.client"/>
			</eu:combogrid>
			</td>
		 </tr> --%>
          <tr>
            <td height="35" align="right">账  号&nbsp;</td>
            <td>
              	<eu:text name="userId" id="userId" onblur="getSectionInfo"></eu:text>
             </td>
          </tr>
          <tr>
            <td height="35" align="right">密  码&nbsp;</td>
            <td>
            <eu:text name="password" id="password" type="password" onkeypress="detectCapsLock"></eu:text>
            </td>
          </tr>
          <tr>
            <td height="35" align="right">部  门&nbsp;</td>
            <td>
            <input id="sectionId" name="sectionId" class="easyui-combobox" data-options="    
			        valueField: 'ID',    
			        textField: 'SECTION_NAME',    
			        url: 'cacheManager.do?method=getTriStoneCacheSqlList4System&cacheName=${requestScope.clientCacheName}'    
			        " 
			        />   
            
<%--             <eu:combogrid id="sectionId" name="sectionId" width="148"  url="cacheManager.do?method=getTriStoneCacheSqlList4System&cacheName=${requestScope.clientCacheName}" idField="ID" textField="SECTION_NAME"  > --%>
<%-- 							<eu:combogridcolumn field="ID" title="编码"   hidden="true"/> --%>
<%-- 							<eu:combogridcolumn field="SECTION_NAME" title="部门名称" /> --%>
<%-- 			</eu:combogrid> --%>

<%--             <eu:text name="sectionId" id="sectionId"  ></eu:text> --%>
            </td>
          </tr>
          <tr>
<!--             <td height="35">&nbsp;</td> -->
            <td height="30" colspan="2" align="center">
           <button id="btnLogin" class="download-itunes"  onClick="javascript: doLogin();" ><spring:message code="login.login"/></button>
           <button class="download-itunes"   onClick="javascript: resetForm();"><spring:message code="login.reset"/></button>

            </td>
          </tr>
          <tr>
            <td height="30" colspan="2" align="center" style="color: red;" id="mesDiv">&nbsp;<span id="tip" style="color: red;"></span>
            <form:errors path="userId" cssStyle="color: red;"/>
            <form:errors path="password" cssStyle="color: red;"/>
            <form:errors cssStyle="color: red;"/></td>
            
          </tr>
        </table></td>
        <td width="98" align="right"><img src="${pageContext.request.contextPath}/images/login_08.jpg" width="98" height="109" /></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td><img src="${pageContext.request.contextPath}/images/bottom_login.jpg" width="596" height="39" /></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td align="center">${UI_VERSION_INFO}</td>
  </tr>
</table>
</form:form>
<script type="text/javascript">
<!--
$(document).ready(function(){
	$("#client").combogrid("setValue", "${requestScope.loginForm.clientId}");
	$("#language").combogrid("setValue", "${requestScope.loginForm.language}");
	$("#userId").val("${requestScope.loginForm.userId}");
	$("#userId").focus();

	if("Chrome"!="${browser}"){
		
		euShow("<a href='${pageContext.request.contextPath}/login.do?method=getChrome'>点击下载浏览器</a>","请下载Chrome浏览器登录系统!",60);
	}
});


document.onkeydown=function()
{
    if ((event.keyCode == 13)) {
        var btn = document.getElementById("btnLogin");
        btn.focus();
        btn.click();
    }
}
//-->
</script>
</body>
</html>