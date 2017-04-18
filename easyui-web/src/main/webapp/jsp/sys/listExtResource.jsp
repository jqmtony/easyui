<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/jsp/common/taglibs.jsp" %>

<html>
<head>
    <jsp:include page="/jsp/common/jspTop.jsp"/>
    <link href="${pageContext.request.contextPath}/js/ext/ext-theme-classic/ext-theme-classic-all.css"	rel="stylesheet" />
    <script src="${pageContext.request.contextPath}/js/ext/ext-all.js"></script>
    <script	src="${pageContext.request.contextPath}/js/ext/locale/ext-locale-zh_CN.js"></script>
    <script src="${pageContext.request.contextPath}/js/ext/ext-theme-classic/ext-theme-classic.js"></script>
    <script src="${pageContext.request.contextPath}/js/comm/ext.tag.js" ></script>
    <!-- 为tree增加自定义样式，垂直居中 -->
    <style>
        .tdValign{vertical-align:middle}
    </style>
    <script type="text/javascript">
        //页面treeGrid名称
        var treeGrid = "resourceTreeGrid";
        //页面form名称
        var form = "resourceForm";
        //打开编辑子页面url定义
        var toEditUrl = "${pageContext.request.contextPath}/easyui/resource/toEditPage";
        //删除URL
        var deleteUrl = "${pageContext.request.contextPath}/easyui/resource/delete";
        //选中行数据的主键
        var singleRecordParams = ["id"];

        /**
         * 点击"查询"
         */
        function doSearch() {
            formSearch4ExtStore(treeGrid, form);
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

    </script>
</head>

<body class="easyui-layout" fit="true" style="width:100%;height:100%;">
    <div data-options="region:'north'" style="height:35px;background:#eee;">
        <!-- 工具栏 -->
        <exu:toolbar id="toolBarId">
            <eu:form id="resourceForm">
                <table class="formTable">
                    <tr>
                        <td class="columnTitle">资源名称:</td>
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
            <exu:button title="添加" iconClass="icon-add" onClick="javaScript: doAdd();"/>
            <!-- 修改 -->
            <exu:button title="修改" iconClass="icon-edit" onClick="javaScript: isSelectedSingleRecord(grid, doUpdate);"/>
            <!-- 删除 -->
            <exu:button title="删除" iconClass="icon-remove" onClick="javaScript: doDelete();"/>
        </exu:toolbar>
    </div>

    <div data-options="region:'center'" >
        <exu:treeGrid  url="${pageContext.request.contextPath}/easyui/resource/listExtTreeNode" idField="id" fitColumns="true"
                       id="resourceTreeGrid"  fit="true" toolbarId="toolBarId" >
            <exu:column field="id"  hidden="true"  title="ID" tdCls="tdValign"/>
            <exu:column field="pid" hidden="true" title="parentId" tdCls="tdValign"/>

            <exu:column field="name" title="资源名称"   width="200" tdCls="tdValign" treecolumn="true"/>
            <exu:column field="url"  title="资源路径"   width="200" tdCls="tdValign"/>
            <exu:column field="type"  title="资源类型"   width="100" tdCls="tdValign"/>
            <exu:column field="flag"  title="资源标识"  width="100" tdCls="tdValign"/>
            <exu:column field="seq"  title="序号"  width="100" tdCls="tdValign"/>
            <exu:column field="remarks" title="描述"   width="200" tdCls="tdValign"/>
            <exu:column field="createUser" title="创建人" width="100" tdCls="tdValign"/>
            <exu:column field="createTm" title="创建时间" width="100" tdCls="tdValign"/>
            <exu:column field="updateUser" title="修改人" width="100" tdCls="tdValign"/>
            <exu:column field="updateTm" title="修改时间" width="100" tdCls="tdValign"/>
        </exu:treeGrid>
    </div>
</body>
</html>