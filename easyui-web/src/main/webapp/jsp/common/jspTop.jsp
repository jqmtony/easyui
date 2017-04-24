<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${UI_TITLE}</title>
<%--
<link rel="icon" href="${pageContext.request.contextPath}/images/favicon.ico" type="image/x-icon"/>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.ico" type="image/x-icon"/>
--%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">
<%--
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/icons/icon-all.min.css" rel="stylesheet" type="text/css" />
--%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/jquery-easyui-1.5.2/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/jquery-easyui-1.5.2/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/jquery-easyui-extensions/jeasyui.extensions.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/jquery-easyui-extensions/icons/icon-standard.css">

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.11.3.min.js"></script>

<%--
<script type="text/javascript" src="${pageContext.request.contextPath}/js/editarea/edit_area_full.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
--%>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui-1.5.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui-1.5.2/locale/easyui-lang-zh_CN.js"></script>
<!-- 开启datagrid菜单功能，必须引用以下4个js -->
<script src="${pageContext.request.contextPath}/js/jquery-easyui-extensions/jquery.jdirk.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-easyui-extensions/jeasyui.extensions.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-easyui-extensions/jeasyui.extensions.validatebox.js"></script>
<%--
<script src="${pageContext.request.contextPath}/js/jquery-easyui-extensions/jeasyui.extensions.form.js"></script>
--%>
<script src="${pageContext.request.contextPath}/js/jquery-easyui-extensions/jeasyui.extensions.menu.js"></script>

<script src="${pageContext.request.contextPath}/js/jquery-easyui-extensions/jeasyui.extensions.datagrid.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-easyui-extensions/jeasyui.extensions.tree.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-easyui-extensions/jeasyui.extensions.treegrid.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/comm/easyui.business.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/comm/easyui.comm.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/comm/easyui.datagrid.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/comm/easyui.extend.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/comm/easyui.platform.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/comm/easyui.tag.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/comm/easyui.treegrid.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/comm/easyui.window.js"></script>

<jsp:include page="jspResource.jsp"/>


<div id="popWindow" >
</div>

<div id="popWindowSearch" >
</div>

<div id="popWindowThree" >
</div>

<div id="popDialog" >
</div>

<div id="popWindowIframe" >
<iframe id="windowIframe"  name="windowIframe" class="panel-iframe" hidden="true" frameborder="0" width="100%" height="100%" marginwidth="0px" marginheight="0px" scrolling="auto"></iframe>
</div>

<div id="showProcessId" style="width:300px"></div> 
