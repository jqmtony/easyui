<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/jsp/common/taglibs.jsp" %>

<html>
<head>
    <jsp:include page="/jsp/common/jspTop.jsp"/>

    <script type="text/javascript">
        //页面grid名称
        var grid = "userGrid";
        //页面form名称
        var form = "userForm";
        //打开编辑子页面url定义
        var toEditUrl = "${pageContext.request.contextPath}/easyui/user/toEditPage";
        //删除URL
        var deleteUrl = "${pageContext.request.contextPath}/easyui/user/delete";
        //选中行数据的主键
        var singleRecordParams = ["id"];

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
            var url = toEditUrl + "?editFlag=update" + getSingleRecordParams(grid, singleRecordParams);
            doOpenWindow(url, "修改");
        }
        /**
         * 点击"添加"
         */
        function doAdd() {
            doOpenWindow(toEditUrl + "?editFlag=add", "新增");
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

        /**
         * 格式化密码
         * @param val
         * @param node
         * @returns {string}
         */
        function formatPassword(val, node){
            return "******";
        }
    </script>
</head>

<body class="easyui-layout" fit="true" style="width:100%;height:100%;">
<div data-options="region:'center'" style="width:90%;height:auto; overflow: auto;">
    <!-- 工具栏 -->
    <eu:toolbar id="toolBarId">
        <eu:form id="userForm">
            <table class="formTable">
                <tr>
                    <td class="columnTitle">用户名:</td>
                    <td class="columnData">
                        <eu:text id="name" name="name" />
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
    <eu:table id="userGrid" url="${pageContext.request.contextPath}/easyui/user/listByPage" pagination="true" fit="true"
              singleSelect="true" fitColumns="true" toolbarId="toolBarId">
        <eu:column field="id" title="用户主键" width="100" align="center" hidden="true"/>
        <eu:column field="name" title="用户名" width="100" align="center"/>
        <eu:column field="password" title="密码" width="100" align="center" formatter="formatPassword" />
        <eu:column field="createUser" title="创建人" width="100" align="center"/>
        <eu:column field="createTm" title="创建时间" width="100" align="center"/>
        <eu:column field="updateUser" title="修改人" width="100" align="center"/>
        <eu:column field="updateTm" title="修改时间" width="100" align="center"/>
    </eu:table>
</div>
</body>
</html>