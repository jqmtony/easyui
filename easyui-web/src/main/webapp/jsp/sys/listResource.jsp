<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/jsp/common/taglibs.jsp" %>

<html>
<head>
    <jsp:include page="/jsp/common/jspTop.jsp"/>
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
            treegridFormSearch(treeGrid, form);
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
            var url = toEditUrl + "?editFlag=update" + getSingleTgRecordParams(treeGrid, singleRecordParams);
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
            isSelectedTgRecordAdConfirm(treeGrid, deleteGrid, "确认删除？");
        }

        /**
         * 删除 处理操作
         */
        function deleteGrid() {
            var url = deleteUrl + getSingleTgRecordParams(treeGrid, singleRecordParams);
            ajaxPostAdReloadCall2Tg(url, treeGrid);
        }

        function formatterType(val, node) {
            if("0" == val){
                return "菜单";
            }else if("1" == val){
                return "按钮";
            }else{
                return val;
            }
        }

    </script>
</head>

<body class="easyui-layout" fit="true" style="width:100%;height:100%;">
    <div data-options="region:'center'">
        <!-- 工具栏 -->
        <eu:toolbar id="toolBarId">
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
            <eu:button title="添加" iconClass="icon-add" plain="true" onClick="javaScript: doAdd();"/>
            <!-- 修改 -->
            <eu:button title="修改" iconClass="icon-edit" plain="true" onClick="javaScript: isSelectedSingleTgRecord(treeGrid, doUpdate);"/>
            <!-- 删除 -->
            <eu:button title="删除" iconClass="icon-remove" plain="true" onClick="javaScript: doDelete();"/>
        </eu:toolbar>
        <eu:treetable  url="${pageContext.request.contextPath}/easyui/resource/listByPage"
                       treeField="name" idField="id" dataPlain="true" parentField="pid"
                       fitColumns="true"
                       id="resourceTreeGrid"  fit="true" toolbarId="toolBarId" pagination="false">
            <eu:column field="id"  hidden="true"  title="ID" />
            <eu:column field="pid" hidden="true" title="parentId" align="center"/>

            <eu:column field="name" title="资源名称"   width="200" align="left" />
            <eu:column field="url"  title="资源路径"   width="200" align="center"/>
            <eu:column field="type"  title="资源类型"   width="100" align="center" formatter="formatterType"/>
            <eu:column field="flag"  title="资源标识"  width="100" align="center"/>
            <eu:column field="seq"  title="序号"  width="100" align="center"/>
            <eu:column field="remarks" title="描述"   width="200" align="center"/>
            <eu:column field="createUser" title="创建人" width="100" align="center"/>
            <eu:column field="createTm" title="创建时间" width="100" align="center"/>
            <eu:column field="updateUser" title="修改人" width="100" align="center"/>
            <eu:column field="updateTm" title="修改时间" width="100" align="center"/>
        </eu:treetable>
    </div>
</body>
</html>