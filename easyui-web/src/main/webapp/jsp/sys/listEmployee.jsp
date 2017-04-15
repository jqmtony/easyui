<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/jsp/common/taglibs.jsp" %>

<html>
<head>
    <jsp:include page="/jsp/common/jspTop.jsp"/>

    <script type="text/javascript">
        //页面grid名称
        var grid = "employeeGrid";
        //页面form名称
        var form = "employeeForm";
        //打开编辑子页面url定义
        var toEditUrl = "${pageContext.request.contextPath}/easyui/employee/toEditPage";
        //删除URL
        var deleteUrl = "${pageContext.request.contextPath}/easyui/employee/delete";
        //选中行数据的主键
        var singleRecordParams = ["empCode"];

        /**
         * 点击"查询"
         */
        function doSearch() {
            datagridFormSearch(grid, form);
        }
        /**
         * 弹出框(添加、修改）
         */
        function doOpenWindow(url, title) {
            openWindow(url, title, 600, 300);
        }

        /**
         * 点击"修改"
         */
        function doUpdate() {
            var url = toEditUrl + "?flag=update" + getSingleRecordParams(grid, singleRecordParams);
            doOpenWindow(url, "修改");
        }
        /**
         * 点击"添加"
         */
        function doAdd() {
            doOpenWindow(toEditUrl + "?flag=add", "新增");
        }

        /**
         * 点击"删除"
         */
        function doDelete() {
            isSelectedRecordAdConfirm(grid, deleteGrid, "确认删除？");
        }

        /**
         * 删除 处理操作
         */
        function deleteGrid() {
            var url = deleteUrl + getSingleRecordParams(grid, singleRecordParams);
            ajaxPostAndReloadDatagrid(url, grid);
        }
    </script>
</head>

<body class="easyui-layout" fit="true" style="width:100%;height:100%;">
<div data-options="region:'center'" style="width:90%;height:auto; overflow: auto;">
    <!-- 工具栏 -->
    <eu:toolbar id="toolBarId">
        <eu:form id="employeeForm">
            <table class="formTable">
                <tr>
                    <td class="columnTitle">员工工号:</td>
                    <td class="columnData">
                        <eu:text id="empCode" name="empCode" />
                    </td>
                    <td class="columnTitle">是否内部职工:</td>
                    <td class="columnData">
                        <eu:select id="innerFlg" name="innerFlg" options="请选择:,是:1,否:0"/>
                    </td>
                    <td class="button">
                        <eu:button id="searchButton" defaultEnterKey="true" title="查询" iconClass="icon-search" onClick="javascript: doSearch();"/>
                    </td>
                </tr>
            </table>
        </eu:form>
        <!-- 添加 -->
        <eu:button title="添加" iconClass="icon-add" plain="true" onClick="javaScript: doAdd();"/>
        <!-- 修改 -->
        <eu:button title="修改" iconClass="icon-edit" plain="true" onClick="javaScript: isSelectedSingleRecord(grid, doUpdate);"/>
        <!-- 删除 -->
        <eu:button title="删除" iconClass="icon-remove" plain="true" onClick="javaScript: doDelete();"/>
    </eu:toolbar>
    <eu:table id="employeeGrid" url="${pageContext.request.contextPath}/easyui/employee/listByPage" pagination="true" fit="true"
              singleSelect="true" fitColumns="true" toolbarId="toolBarId">
        <eu:column field="empCode" title="员工工号" width="100" align="center"/>
        <eu:column field="empName" title="员工姓名" width="100" align="center"/>
        <eu:column field="deptName" title="部门名称" width="100" align="center"/>
        <eu:column field="organization" title="组织" width="100" align="center"/>
        <eu:column field="dutyName" title="岗位名称" width="100" align="center"/>
        <eu:column field="empType" title="人员类别" width="100" align="center"/>
        <eu:column field="empMobile" title="手机号码" width="100" align="center"/>
        <eu:column field="empAttribute" title="员工属性" width="100" align="center"/>
        <eu:column field="innerFlg" title="是否内部职工" formatter="formatValOrNo" width="100" align="center"/>
    </eu:table>
</div>
</body>
</html>