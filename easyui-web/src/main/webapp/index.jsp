<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/jsp/common/taglibs.jsp" %>

<html>
<head>
    <jsp:include page="/jsp/common/jspTop.jsp"/>
</head>
<body class="easyui-layout" fit="true" style="width:100%;height:100%;">

<div data-options="region:'center'" style="width:90%;height:auto;">
    <a href="${pageContext.request.contextPath}/easyui/user/toListPage">用户管理</a>
    <br>
    <a href="${pageContext.request.contextPath}/easyui/resource/toListPage">资源管理</a>
    <br>
    <a href="${pageContext.request.contextPath}/easyui/resource/toExtListPage">EXT资源管理</a>

    <eu:form id="demoFrom" name="demoForm" method="post">
        <div>
            <label for="url">URL(1字符串):</label>
            <eu:validateText id="url" name="url" required="true" validType="'url'"/>
        </div>
        <div>
            <label for="email">Email(2数组，应用多个验证):</label>
            <eu:validateText id="email" name="email" required="true" validType="['email','length[0,5]']"/>
        </div>
        <div>
            <label for="name">Name(3对象，每个key是一个验证名称value是验证的数组参数):</label>
            <eu:validateText id="name" name="name" required="true" invalidMessage="该工号已存在！" validType="{remote:['${pageContext.request.contextPath}/easyui/user/validateName','name']}"/>
        </div>
    </eu:form>
</div>
</body>
</html>