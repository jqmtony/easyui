<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>

<script type="text/javascript">

    //当前页面editForm名称
    var editForm = "editUserForm";

    function doEditCommit() {
        ajaxPostAdReloadDg2Form(editForm, grid, "window");
    }

</script>

<!-- 表单 -->
<div class="popupWindowContainer">
    <eu:form action="${pageContext.request.contextPath}/easyui/user/edit" name="editUserForm" id="editUserForm">
        <table class="editTable">
            <tr>
                <td class="columnTitle" >用户名</td>
                <td class="columnData">
                    <eu:text id="editName" name="name" required="true" />
                    <input type="hidden" name="flag" value="${flag}"/>
                </td>
            </tr>
            <tr>
                <td class="columnTitle" >密码</td>
                <td class="columnData">
                    <eu:text id="editPassword" name="password" />
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