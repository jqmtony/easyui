<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>

<script type="text/javascript">

    //当前页面editForm名称
    var editForm = "editResourceForm";

    function doEditCommit() {
        ajaxPostAdReloadTgNode2Form(editForm, treeGrid, "window");
    }

</script>

<!-- 表单 -->
<div class="popupWindowContainer">
    <eu:form action="${pageContext.request.contextPath}/easyui/resource/edit" name="editResourceForm" id="editResourceForm">
        <table class="editTable">
            <tr>
                <td class="columnTitle" >父资源</td>
                <td class="columnData">
                    <eu:comboTree id="editPid" name="pid" url="${pageContext.request.contextPath}/easyui/resource/listTreeNode"/>
                    <input type="hidden" name="id" />
                    <input type="hidden" name="editFlag" value="${editFlag}"/>
                </td>
            </tr>
            <tr>
                <td class="columnTitle" >资源名</td>
                <td class="columnData">
                    <eu:text id="editName" name="name" required="true" />
                </td>
            </tr>
            <tr>
                <td class="columnTitle" >资源类型</td>
                <td class="columnData">
                    <eu:select id="editType" name="type" options="菜单:0,按钮:1"/>
                </td>
            </tr>
            <tr>
                <td class="columnTitle" >资源标识</td>
                <td class="columnData">
                    <eu:text id="editFlag" name="flag"/>
                </td>
            </tr>
            <tr>
                <td class="columnTitle" >资源路径</td>
                <td class="columnData">
                    <eu:text id="editUrl" name="url" />
                </td>
            </tr>
        </table>
    </eu:form>
</div>
<!-- 提交、关闭 按钮 -->
<div id="popupPanelBottom">
    <div id="buttonSection">
        <eu:button id="commitButton" onceEnterKey="true" title="提交" iconClass="icon-save" onClick="javascript: doEditCommit();" />
        <eu:button title="关闭" iconClass="icon-cancel" onClick="javascript: closeThisPopWindow();" />
    </div>
</div>