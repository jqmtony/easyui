<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>

<script type="text/javascript">

    //当前页面editForm名称
    var editForm = "editEmployeeForm";

    function doEditCommit() {
        ajaxPostAdReloadDg2Form(editForm, grid, "window");
    }

</script>

<!-- 表单 -->
<div class="popupWindowContainer">
    <eu:form action="${pageContext.request.contextPath}/easyui/employee/edit" name="editEmployeeForm" id="editEmployeeForm">
        <table class="editTable">
            <tr>
                <td class="columnTitle" >员工工号</td>
                <td class="columnData">
                    <eu:text id="editEmpCode" name="empCode" required="true" />
                    <input type="hidden" name="flag" value="${flag}"/>
                </td>
            </tr>
            <tr>
                <td class="columnTitle" >员工姓名 </td>
                <td class="columnData">
                    <eu:text id="empName" name="empName" />
                </td>
            </tr>
            <tr>
                <td class="columnTitle" >手机号码</td>
                <td class="columnData">
                    <eu:text id="empMobile" name="empMobile" />
                </td>
            </tr>
            <tr>
                <td class="columnTitle" >是否内部员工</td>
                <td class="columnData">
                    <eu:radioCheckBox name="innerFlg" type="radio" text="是,否" value="1,0" id="idYes,idNo" checked="idYes"/>
                </td>
            </tr>
        </table>
    </eu:form>
</div>
<!-- 提交、关闭 按钮 -->
<div id="popupPanelBottom">
    <div id="buttonSection">
        <eu:button id="commitButton" onceEnterKey="true" title="提交" iconClass="icon-save" onClick="javascript: doEditCommit();" />
        <eu:button title="关闭" iconClass="icon-cancel" onClick="javascript: closeThisPopWindowCompel();" />
    </div>
</div>
<script type="text/javascript">
    /**
     * 修改页面时做一些事
     */
    $(document).ready(function(){
        if("update" == '${requestScope.flag}'){
            $('#editEmpCode').textbox('readonly',true);
        }
    });
</script>