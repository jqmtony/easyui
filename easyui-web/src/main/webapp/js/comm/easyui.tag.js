/**
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *  *  *  *  *  *  * JS说明：easyui.form表单操作JS汇总  *  *  *  *  *  *  *
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 */

/**
 * 清空FORM表单
 *
 * @param formId
 * @return
 */
function clearForm(formId) {
    $("#" + formId).form("clear");
}

/**
 * 获取控件id所在的form表单编号
 */
function getParentFormId(formId) {
    return $("#" + formId).parents("form").attr("id");
}
/**
 * 获取FORM第一个input元素的name
 */
function getFirstInput4Form(formId) {
    if (formId != null) {
        var form = document.getElementById(formId);
        for (var i = 0; i < form.length; ++i) {
            var element = form.elements[i];
            if (element != null) {
                if (element.nodeName == "INPUT" && !element.disabled
                    && element.type != "hidden" && element.type != "hidden"
                    && !element.readOnly) {
                    return element.name;
                }
            }
        }
    }
}
/**
 * 禁用FORM
 */
function disableForm(formId) {
    disableEnableForm(formId, true);
}

function disableEnableForm(formId, isDisabled) {
    var attr = "disable";
    if (!isDisabled) {
        attr = "enable";
    }

    $("form[id='" + formId + "'] :text").attr("disabled", isDisabled);
    $("form[id='" + formId + "'] textarea").attr("disabled", isDisabled);
    $("form[id='" + formId + "'] select").attr("disabled", isDisabled);
    $("form[id='" + formId + "'] :radio").attr("disabled", isDisabled);
    $("form[id='" + formId + "'] :checkbox").attr("disabled", isDisabled);

    //禁用jquery easyui中的下拉选 
    $("#" + formId).find(".combo-f.textbox-f").each(function () {
        if (this.id) {
            $("#" + this.id).combobox(attr);
        }
    });
}

/**
 * 启用FORM
 */
function enableForm(formId) {
    disableEnableForm(formId, false);
}
/**
 * 专门用与easyui表单的提交，提交之前会判断字段是否合法
 *
 * @param formId表单编号
 */
function formSubmit(formId) {
    if (validate2Form(formId)) {
        $("#" + formId).submit();
    }
}

/**
 * 使用Ajax的方式提交表单，提交之前验证页面字段是否合法
 *
 * @param formId
 *            表单编号
 * @param callBack
 *            回调函数
 */
function ajaxFormCommit(formId, callback) {
    if ($("#" + formId).form("validate")) {
        showProcess(true, titleinfo, submitTitle);
        $.post($("#" + formId).attr("action"),
            $("#" + formId).serializeArray(), function (data, status) {
                showProcess(false);
                callback(data, status);
            });
    }
}

/**
 * 使用Ajax的方式提交表单，提交之前验证页面字段是否合法，自动刷新reloadGridId,然后关闭closeObj弹出框
 *
 * @param formId
 *            表单编号
 * @param reloadGridId
 *            grid id
 * @param closeObj
 *            是否关闭
 * @param reloadGridParams
 *            参数 funId:1,funName:2
 */
function ajaxPostAdReloadDg2Form(formId, reloadGridId, closeObj, reloadGridParams) {
    if ($("#" + formId).form("validate")) {
        showProcess(true, titleinfo, submitTitle);
        $.post($("#" + formId).attr("action"),
            $("#" + formId).serializeArray(), function (data, status) {
                showProcess(false);
                if (!isEmpty(reloadGridParams)) {
                    retResultAdReloadByParams4Call(data, reloadGridId, closeObj, reloadGridParams);
                } else {
                    retResultAdReload4Call(data, reloadGridId, closeObj);
                }
            });
    }
}

/**
 * 使用Ajax的方式提交表单，提交之前验证页面字段是否合法
 *
 * @param formId
 *            表单编号
 * @param reloadGridId
 *            grid id
 * @param closeObj
 *            是否关闭
 */
function ajaxUploadFormCommitAndAutoCallback(formId, reloadGridId, funName, closeObj) {
    if ($("#" + formId).form("validate")) {
        showProcess(true, titleinfo, submitTitle);
        $.post($("#" + formId).attr("action"),
            $("#" + formId).serializeArray(), function (data, status) {
                showProcess(false);
                commitUploadFileCallback(data, reloadGridId, funName, closeObj);
            });
    }
}

/**
 * 验证form表单是否通过
 *
 * @param formId
 * @returns {Boolean}
 */
function validate2Form(formId) {
    if ($("#" + formId).form("validate")) {
        return true;
    } else {
        return false;
    }
}

/**
 * 获取FORM字段以参数方式
 *
 * @param formId
 * @param escapeFields
 *            需要转译的字段，以逗号隔开。
 * @return
 */
function getFormParams(formId, escapeFields) {
    if ($("#" + formId).form("validate")) {
        var urlParams = getUrlParamStr4Arr($("#" + formId).serializeArray(), escapeFields);
        if (!isEmpty(urlParams)) {
            urlParams = "&" + urlParams;
        }
        return urlParams;
    }
}

/**
 * 获取FORM字段以参数方式（以横杠为分隔符，逗号为连接符）
 *
 * @param formId
 * @returns {String} 如： id-123,name-43
 */
function getLineAdCommaStr4Form(formId) {
    return getDefineStr4Form(formId, "-", ",");
}
/**
 * 获取FORM字段以参数方式 自定义连接符和等于符号
 *
 * @param formId
 * @param splitChar
 *            如： :
 * @param linkChar
 *            如： ,
 * @returns {String} 如： id:123,name:43
 */
function getDefineStr4Form(formId, splitChar, linkChar) {
    if ($("#" + formId).form("validate")) {
        return getDefineStr4Arr($("#" + formId).serializeArray(), splitChar,
            linkChar);
    }
}

/**
 * 将Form表单({name:value})对象
 *
 * @param formId
 * @returns
 */
function getFormParamJsonObj(formId) {
    var objStr = getFormParamJsonObjStr(formId);
    return evalObj(objStr);
}

/**
 * 将Form表单({name:value})对象
 *
 * @param formId
 * @returns
 */
function getFormParamJsonObjStr(formId) {
    var objStr = "{";
    if ($("#" + formId).form("validate")) {
        var fields = $("#" + formId).serializeArray();

        jQuery.each(fields, function (i, field) {
            var value = isEmpty(field.value) ? "" : replaceAll(field.value, '\'', '\\\'');
            if (i == 0) {
                objStr += field.name + ":'" + value + "'";
            } else {
                objStr += "," + field.name + ":'" + value + "'";
            }
        });
    }
    objStr += "}";

    return objStr;
}

/**
 * 获取无需验证form数据对象
 *
 */
function getNoValidateFormJsonObj(formId) {
    var objStr = "{";
    // if($("#" + formId).form("validate")) {
    var fields = $("#" + formId).serializeArray();
    jQuery.each(fields, function (i, field) {
        if (i == 0) {
            objStr += field.name + ":'" + field.value + "'";
        } else {
            objStr += "," + field.name + ":'" + field.value + "'";
        }
    });
    // }
    objStr += "}";

    return evalObj(objStr);
}

/**
 * 加载FORM数据(obj)
 *
 * @param formId
 * @param obj
 * @return
 */
function loadFormObject(formId, obj) {
    obj = eval("(" + obj + ")");
    $("#" + formId).form("load", obj);
}
/**
 * 加载FORM数据(json字符串）
 *
 * @param formId
 * @param jsonData
 * @return
 */
function loadFormJson(formId, jsonData) {
    $("#" + formId).form("load", jsonData);
}
function loadFormJsonObj(obj, jsonData) {
    $(obj).form("load", jsonData);
}

/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * JS说明：easyui.combogrid下拉列表操作JS汇总 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */

/**
 * 获取combogrid单行记录参数值,返回字符串。
 *
 * @param combogrid
 * @param fieldName
 * @returns
 */
function getSingleRecordFieldByCombogrid(combogrid, fieldName) {
    var row = $('#' + combogrid).combogrid('grid').datagrid('getSelected'); // 得到datagrid
    // 对象
    if (row) {
        return row[fieldName];
    } else {
        return null;
    }
}

/**
 * 加载combogrid数据（json字符串）
 *
 * @param combogridId
 * @param jsonData
 * @return
 */
function loadCombogridJson(combogridId, jsonData) {
    $("#" + combogridId).combogrid("grid").datagrid("loadData",
        eval("(" + jsonData + ")"));

}

/**
 * 根据url，重载combogridId
 *
 * @param combogridId
 * @param url
 * @return
 */
function reloadCombogrid(combogridId, url) {
    $("#" + combogridId).combogrid("clear");
    var grid = $("#" + combogridId).combogrid("grid");
    $(grid).datagrid("options").url = url;
    $(grid).datagrid("reload");
}


/**
 * 获得下拉框idField
 *
 * @param tagId
 * @returns
 */
function getCombogridIdField(tagId) {
    if (tagId == null) {
        return;
    }
    return $("#" + tagId).combogrid("options").idField;
}

/**
 * 获得下拉框textField
 *
 * @param tagId
 * @returns
 */
function getCombogridTextField(tagId) {
    if (tagId == null) {
        return;
    }
    return $("#" + tagId).combogrid("options").textField;
}

/**
 * 获取下拉列表控件Label
 *
 * @param tagId
 * @return
 */
function getCombogridName(tagId) {
    if (tagId == null) {
        return;
    }
    return $("#" + tagId).combogrid("getText");
}
/**
 * 获取下拉列表控件Value
 *
 * @param tagId
 * @return
 */
function getCombogridValue(tagId) {
    if (tagId == null) {
        return;
    }
    return $("#" + tagId).combogrid("getValue");
}

/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * JS说明：easyui.combobox下拉框操作JS汇总 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */

/**
 * 加载combobox数据（json字符串）
 *
 * @param comboboxId
 * @param jsonData
 * @return
 */
function loadJson2Combobox(comboboxId, jsonData) {
    $("#" + comboboxId).combobox("loadData", eval("(" + jsonData + ")"));
}

/**
 * 下拉框（值：对应值的备注）
 *
 * @param row
 * @returns {String}
 */
function formatComboboxItem(row, text, desc) {
    var s = '<span style="font-weight:bold">' + row.text + '</span><br/>'
        + '<span style="color:#888">' + row.desc + '</span>';
    return s;
}

/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * JS说明：easyui.tree树操作JS汇总 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */

/**
 * 重新加载树
 *
 * @param treeId
 *            名称
 */
function reloadTree(treeId) {
    // 刷新tree之前，如果树有选中节点，先保存在页面map对象中。
    if ($("#" + treeId).tree('getSelected') != null) {
        if (isEmpty(relationObjParamMap)) {
            relationObjParamMap = new HashMap();
        }
        relationObjParamMap.put(treeId + "NodeId", $("#" + treeId).tree(
            'getSelected').id);
    }
    $("#" + treeId).tree("reload");
}

/**
 * 验证是否选择了树节点
 *
 * @param treeId
 * @param callback
 */
function isSelectedTreeNode(treeId, callback) {
    var node = $("#" + treeId).tree("getSelected");
    if (node == null || node == undefined) {
        euAlert(selectOneNode);
    } else {
        callback();
    }
}

/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * JS说明：easyui.combotree下拉树操作JS汇总 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */

/**
 * 刷新combotree
 *
 * @param treegridId
 * @param url
 * @return
 */
function reloadCombotree(combotreeId, url) {
    if (!isEmpty(combotreeId) && !isEmpty(url)) {
        $('#' + combotreeId).combotree('clear');
        $('#' + combotreeId).combotree('reload', url);
    }
}

/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * JS说明：easyui.linkbutton按钮操作JS汇总 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */

/**
 * 设置按钮的文字
 *
 * @param btnId
 * @param text
 */
function setLinkbuttonText(btnId, text) {
    $("#" + btnId).linkbutton({
        text: text
    });
}

/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * JS说明：easyui.tag标签集合操作JS汇总 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */

/**
 * 判断radio或checkbox是否选中
 */
function isChecked(tagId) {
    return $("#" + tagId).is(":checked");
}
/**
 * 判断checkbox是否选中
 *
 * @param tagName
 * @returns {Boolean}
 */
function isCheckedByTagName(tagName) {
    return $("input[name='" + tagName + "']:checkbox:checked").length > 0;
}

/**
 * 禁用FORM elementIds 控件ID数组["materialId","materialName"]
 */
function disableElements(elementIds) {
    if (elementIds != null) {
        for (var j = 0; j < elementIds.length; j++) {
            var element = document.getElementById(elementIds[j]);
            if (element != null) {
                if (element.className != null
                    && element.className == "easyui-searchbox") {
                    disable(elementIds[j], "searchbox");
                } else {
                    switch (element.nodeName) {
                        case "INPUT":
                            if (element.name != "return") {
                                element.disabled = "true";
                                element.readOnly = true;
                            }
                            break;
                        case "SELECT":
                            element.disabled = true;
                            break;
                        case "TEXTAREA":
                            element.disabled = true;
                            break;
                        default:
                            break;
                    }
                }
            }
        }
    }
}

/**
 * 设置控件是否只读 tagId 控件ID tagName 控件类型名称
 */
function readonlyTrueById(tagId, tagName) {
    if (tagName == null) {
        return;
    }
    switch (tagName) {
        case "text":
            $("#" + tagId).textbox('readonly', true);
            $('input', $('#' + tagId).next('span')).addClass('textbox-disabled-style');

            break;
        case "combobox":
            $("#" + tagId).combobox({
                'readonly': true
            });
            break;
        case "combogrid":
            $("#" + tagId).combogrid({
                'readonly': true
            });
            break;
        case "combotree":
            $("#" + tagId).combotree({
                'readonly': true
            });
            break;
        case "validateText":
            $("#" + tagId).prop({
                'readonly': true
            });
            $("#" + tagId).attr({
                'class': "readonlyText"
            });
            break;
        case "button":
            $("#" + tagId).linkbutton("disable");
            break;
        case "searchPage":
            $("#" + tagId + "SearchBox").prop({
                'readonly': true
            });
            $("#" + tagId + "SearchBox").attr({
                'class': "readonlyText"
            });
            $("#" + tagId + "SearchButtonSpan").attr({
                'hidden': true
            });

            break;
        case "input":
            $("#" + tagId).prop({
                'readonly': true
            });
            $("#" + tagId).attr({
                'class': "readonlyText"
            });
            break;
        default:
            break;
    }
}

/**
 * 设置控件是否只读 tagname 控件name tagName 控件类型名称
 */
function readonlyTrueByName(name, tagName) {
    if (tagName == null) {
        return;
    }
    switch (tagName) {
        case "checkbox":
            // prop("readonly",false) 无效
            $("input[name='" + name + "']").prop("disabled", true);
            break;
        case "radio":
            // prop("readonly",false) 无效
            $("input[name='" + name + "']").prop("disabled", true);
            break;
        default:
            break;
    }
}

/**
 * 设置控件可用 tagId 控件ID tagName 控件类型名称
 */
function readonlyFalseById(tagId, tagName) {
    if (tagName == null) {
        return;
    }
    switch (tagName) {
        case "text":
            $("#" + tagId).textbox('readonly', false);
            $('input', $('#' + tagId).next('span')).removeClass('textbox-disabled-style');
            break;
        case "combobox":
            $("#" + tagId).combobox({
                'disabled': false
            });
            break;
        case "combogrid":
            $("#" + tagId).combogrid({
                disabled: false
            });
            break;
        case "validateText":
            var tempStyle = typeof ($("#" + tagId).attr("style")) == "undefined" ? ""
                : $("#" + tagId).attr("style").replace(
                    "background-color: #EBEBE4;border: #a4bed4 1px solid;",
                    "");
            $("#" + tagId).attr({
                style: ""
            });
            $("#" + tagId).prop("readonly", false);
            break;
        case "input":
            $("#" + tagId).prop({
                'readonly': false
            });
            $("#" + tagId).attr({
                'class': ""
            });
            break;
        case "combotree":
            $("#" + tagId).combotree({
                'disabled': false
            });
            break;
        case "button":
            $("#" + tagId).linkbutton("enable");
            break;
        case "searchPage":
            $("#" + tagId + "SearchBox").prop({
                'readonly': false
            });
            $("#" + tagId + "SearchBox")
                .attr(
                    {
                        'class': "searchbox-text easyui-validatebox validatebox-text validatebox-f"
                    });
            $("#" + tagId + "SearchButtonSpan").attr({
                'hidden': false
            });

            break;
        default:
            break;
    }
}
/**
 * 设置控件可用 name 控件name tagName 控件类型名称
 */
function readonlyFalseByName(name, tagName) {
    if (tagName == null) {
        return;
    }
    switch (tagName) {
        case "checkbox":
            $("input[name='" + name + "']").prop("disabled", false);
            break;
        case "radio":
            $("input[name='" + name + "']").prop("disabled", false);
            break;
        default:
            break;
    }
}
/**
 * 设置控件是否只读 tagId 控件ID tagName 控件类型名称
 */
function disable(tagId, tagName) {
    if (tagName == null) {
        return;
    }
    switch (tagName) {
        case "text":
            $("#" + tagId).textbox("disable");
            break;
        case "combogrid":
            $("#" + tagId).combogrid({
                disabled: true,
                required: false
            });
            break;
        case "validateText":
            $("#" + tagId).validatebox({
                disabled: true,
                required: false
            });
            break;
        case "button":
            $("#" + tagId).linkbutton({
                disabled: true,
                required: false
            });
            break;
        case "datebox":
            $("#" + tagId).datebox({
                disabled: true,
                required: false
            });
            break;
        case "datetimebox":
            $("#" + tagId).datetimebox({
                disabled: true,
                required: false
            });
            break;
        case "timespinner":
            $("#" + tagId).timespinner("disable");
            break;
        case "searchbox":
            var box = $("#" + tagId).searchbox('textbox');// 获取控件文本框对象
            box.prop('disabled', true);// 禁用输入
            break;
        case "combotree":
            $("#" + tagId).combotree("disable");
            break;
        default:
            break;
    }
}

/**
 * 设置控件可用 tagId 控件ID tagName 控件类型名称
 */
function enable(tagId, tagName, required) {
    if (tagName == null) {
        return;
    }
    switch (tagName) {
        case "text":
            $("#" + tagId).textbox("enable");
            break;
        case "combogrid":
            $("#" + tagId).combogrid({
                disabled: false
            });
            break;
        case "validateText":
            break;
        case "button":
            $("#" + tagId).linkbutton("enable");
            break;
        case "datebox":
            $("#" + tagId).datebox({
                disabled: false,
                required: !isEmpty(required) ? true : false
            });
            break;
        case "timespinner":
            $("#" + tagId).timespinner("enable");
            break;
        case "datetimebox":
            $("#" + tagId).datetimebox({
                disabled: false,
                required: !isEmpty(required) ? true : false
            });
            break;
        case "searchbox":
            $("#" + tagId).searchbox({
                disabled: false
            });
            break;
        case "combotree":
            $("#" + tagId).combotree("enable");
            break;
        default:
            break;
    }
}

/**
 * 判断是否属于Easy TextBox 标签
 * 不属于TextBox组件 却可使用text标签的类型：
 * 1.隐藏域
 * 2.showonly=true;
 * @param tagId
 */
function isTextboxTag(tagId) {
    var flag = true;
    if (flag && !isEmpty($("#" + tagId).attr("class"))) {
        // showonly=true判断条件
        flag = $("#" + tagId).attr("class") != "input-label-style";
    }
    if (flag && !isEmpty($("#" + tagId).attr("type"))) {
        // 隐藏域
        flag = $("#" + tagId).attr("type").toUpperCase() != 'HIDDEN';
    }
    return flag;
}
/**
 * 获取标签控件的Label 显示值
 *
 * @param tagId    标签Id
 * @param tagType    控件类型
 * @return
 */
function getLabel2TagId(tagId, tagType) {
    if (tagType == null) {
        return;
    }
    switch (tagType) {
        case "searchbox":
            return $("#" + tagId).searchbox("getValue");
        case "validateText":
            return $("#" + tagId).val();
        case "tree":
            return $("#" + tagId).tree('getSelected').name;
        case "combogrid":
            return $("#" + tagId).combogrid("getText");
        case "select":
            return $("#" + tagId + "option:selected").text();
        case "searchInput":
            return $("#" + tagId + "SearchBox").val();
        default:
            break;
    }
}

function setLabel2TagId(tagId, tagName, value) {
    if (tagName == null) {
        return;
    }
    switch (tagName) {
        case "combogrid":
            $("#" + tagId).combogrid('setValue', value);
            break;
        case "validateText":
            setValue2Input(tagId, value);
            break;
        case "searchbox":
            $("#" + tagId).searchbox("setValue", value);
        case "input":
            setValue2Input(tagId, value);
            break;
        case "searchInput":
            // $("#" + tagId + "SearchBox").val(value);
            setValue2Input(tagId + "SearchBox", value);
            break;
        default:
            break;
    }
}
/**
 * 获取标签控件的value
 *
 * @param tagName
 * @param tagType    控件类型
 * @return
 */
function getValue2TagName(tagName, tagType) {
    if (tagType == null) {
        return;
    }
    var value = "";
    switch (tagType) {
        case "textarea":
            var value = $("input:textarea" + tagName).val();
            break;
        case "checkbox":
            var result = new Array();
            $("input[name='" + tagName + "']").prop("checked", function (i, val) {
                if ($(this).is(":checked")) {
                    result.push($(this).val());
                }
            });
            var value = result.join(",");
            break;
        case "radio":
            var value = $('input:radio[name="' + tagName + '"]:checked').val();
            break;
        case "combogrid":
            var value = $("input:hidden[name=" + tagName + "]").val();
            break;
        case "validateText":
            var value = $("input:text[name=" + tagName + "]").val();
            break;
        case "hidden":
            var value = $("input:hidden[name=" + tagName + "]").val();
            break;
        case "text":
            var value = $("input:text[name=" + tagName + "]").val();
            break;
        default:
            break;
    }
    if (value == undefined) {
        value = "";
    }
    return value;

}

/**
 * 获取标签控件的value
 *
 * @param tagId
 * @param tagName
 * @return
 */
function getValue2TagId(tagId, tagName) {
    if (tagName == null) {
        return;
    }
    switch (tagName) {
        case "text":
            return getValue2Textbox(tagId);
        case "textarea":
            return getValue2Input(tagId);
        case "input":
            return getValue2Input(tagId);
        case "combogrid":
            return getValue2Combogrid(tagId);
        case "validateText":
            return getValue2Input(tagId);
        case "tree":
            return getValue2Tree(tagId);
        case "searchbox":
            return getValue2Searchbox(tagId);
        case "select":
            return getValue2Combobox(tagId);		//需要测试！！！
        case "combobox":
            return getValue2Combobox(tagId);
        case "combotree":
            return getValue2Combotree(tagId);
        case "MultiCombotree":
            return getValue2MultiCombotree(tagId);
        case "timespinner":
            return getValue2Timespinner(tagId);
        case "numberbox":
            return getValue2Numberbox(tagId);
            break;
        case "radio":
            return getValue2Radio(tagId);  // 对应的tagId是radio的name属性
            break;
        case "checkbox":
            return getValue2Checkbox(tagId);  // 对应的tagId是radio的name属性
            break;
        case "datetimebox":
            return getValue2Datetimebox(tagId);
            break;
        case "datebox":
            return getValue2Datebox(tagId);
            break;
        case "searchInput"://获取的是搜索输入框id值
            return getValue2SearchInput(tagId);
            break;
    }
}

/**
 * 获取普通input输入框的值
 *
 * @param tagId

 */
function getValue2Input(tagId) {
    return $("#" + tagId).val();
}
/**
 * 获取textbox值
 * @param tagId

 */
function getValue2Textbox(tagId) {
    if (isTextboxTag(tagId)) {
        return $("#" + tagId).textbox("getValue");
    } else {
        return getValue2Input(tagId);
    }
}

/**
 * 获取Combobox值
 *
 * @param tagId

 */
function getValue2Combobox(tagId) {
    return $("#" + tagId).combobox("getValue");
}
/**
 * 获取combogrid值
 *
 * @param tagId

 */
function getValue2Combogrid(tagId) {
    return $("#" + tagId).combogrid("getValue");
}

/**
 * 获取tree值
 * @param tagId

 */
function getValue2Tree(tagId) {
    return $("#" + tagId).tree("getSelected").id;
}
/**
 * 获取combotree值
 * @param tagId

 */
function getValue2Combotree(tagId) {
    return $('#' + tagId).combotree('getValue');
}
/**
 * 获取MultiCombotree值
 * @param tagId

 */
function getValue2MultiCombotree(tagId) {
    return $('#' + tagId).combotree('getValues');
}
/**
 * 获取Numberbox值
 * @param tagId

 */
function getValue2Numberbox(tagId) {
    return $("#" + tagId).numberbox("getValue");
}
/**
 * 获取timespinner的值
 * @param tagId
 * @returns
 */
function getValue2Timespinner(tagId) {
    return $("#" + tagId).numberbox("getValue");
}
/**
 * 获取Datebox值
 * @param tagId

 * @returns
 */
function getValue2Datebox(tagId) {
    return $('#' + tagId).datebox('getValue');
}
/**
 * 获取Datetimebox值
 * @param tagId

 * @returns
 */
function getValue2Datetimebox(tagId) {
    return $('#' + tagId).datetimebox('getValue');
}

/**
 * 给获取查询框的显示值和隐藏值
 *
 * @param tagId
 */
function getValue2SearchInput(tagId) {
    return $('#' + tagId + 'text').val();
}

/**
 * 获取searchbox值
 * @param tagId

 */
function getValue2Searchbox(tagId) {
    return $('#' + tagId).searchbox('getValue');
}

/**
 * 获取radioBox被选中的值
 *
 * @param tagName
 */
function getValue2Radio(tagName) {
    return $("input:radio[name='" + tagName + "'][type='radio']:checked").val();
}
/**
 * 获取checkBox被选中的值
 *
 * @param tagName
 */
function getValue2Checkbox(tagName) {
    var result = new Array();
    $("input[name='" + tagName + "']").prop("checked", function (i, val) {
        if ($(this).is(":checked")) {
            result.push($(this).val());
        }
    });

    return result.join(",");
}
/**
 * =========================================================getValue2TagId==========================================================================
 */

/**
 * 设置控件的 value值
 *
 * @param tagId
 * @param tagName
 * @param value    控件值
 * @param textValue    针队特殊控件。如searchInput中显示文本框text的值
 * @return
 */
function setValue2TagId(tagId, tagName, value, textValue) {
    if (tagName == null) {
        return;
    }
    switch (tagName) {
        case "combogrid":
            setValue2Combogrid(tagId, value);
            break;
        case "combotree":
            setValue2Combotree(tagId, value);
            break;
        case "input":
            setValue2Input(tagId, value);
            break;
        case "text":
            setValue2Textbox(tagId, value);
            break;
        case "textarea":
            setValue2Input(tagId, value);
            break;
        case "numberbox":
            setValue2Numberbox(tagId, value);
            break;
        case "radio": // 对应的tagId是radio的name属性
            setValue2Radio(tagId, value);
            break;
        case "checkbox": // 对应的tagId是checkbox的name属性
            setValue2Checkbox(tagId, value);
            break;
        case "searchbox":
            setValue2Searchbox(tagId, value);
        case "select":
            setValue2Combobox(tagId, value);
            break;
        case "combobox":	//与select一样
            setValue2Combobox(tagId, value);
            break;
        case "searchInput":
            setValue2SearchInput(tagId, value, textValue);
            break;
        case "datebox":
            setValue2Datebox(tagId, value);
            break;
        default:
            setValue2Input(tagId, value);
            break;
    }
}

/**
 * 设置普通input输入框的值
 *
 * @param tagId
 * @param value
 */
function setValue2Input(tagId, value) {
    $("#" + tagId).val(value);
}
/**
 * 设置textbox值
 * @param tagId
 * @param value
 */
function setValue2Textbox(tagId, value) {
    if (isTextboxTag(tagId)) {
        $("#" + tagId).textbox("setValue", value);
    } else {
        setValue2Input(tagId, value);
    }
}

/**
 * 设置Combobox值
 *
 * @param tagId
 * @param value
 */
function setValue2Combobox(tagId, value) {
    if (value.indexOf(",") >= 0) {
        var values = value.split(",");
        $("#" + tagId).combobox('setValues', values);

    } else {
        $("#" + tagId).combobox('setValue', value);
    }
}
/**
 * 设置combogrid值
 *
 * @param tagId
 * @param value
 */
function setValue2Combogrid(tagId, value) {
    if (value.indexOf(",") >= 0) {
        var values = value.split(",");
        $("#" + tagId).combogrid('setValues', values);

    } else {
        $("#" + tagId).combogrid('setValue', value);
    }
}

/**
 * 设置combotree值
 * @param tagId
 * @param value
 */
function setValue2Combotree(tagId, value) {
    if (value.indexOf(",") >= 0) {
        var values = value.split(",");
        $("#" + tagId).combotree('setValues', values);

    } else {
        $("#" + tagId).combotree('setValue', value);
    }
}
/**
 * 设置Numberbox值
 * @param tagId
 * @param value
 */
function setValue2Numberbox(tagId, value) {
    $("#" + tagId).numberbox("setValue", value);
}
/**
 * 设置Datebox值
 * @param tagId
 * @param value
 * @returns
 */
function setValue2Datebox(tagId, value) {
    return $('#' + tagId).datebox('setValue', value);
}

/**
 * 给设置查询框的显示值和隐藏值
 *
 * @param tagId
 * @param textValue 隐藏值
 * @param searchValue 显示值
 */
function setValue2SearchInput(tagId, textValue, searchValue) {
    setValue2Input(tagId + "text", textValue);// 隐藏字段
    setValue2Input(tagId + "SearchBox", searchValue);// 可见输入框
}

/**
 * 设置searchbox值
 * @param tagId
 * @param value
 */
function setValue2Searchbox(tagId, value) {
    $("#" + tagId).searchbox("setValue", value);
}
/**
 * 设置指定的checkbox值被选中
 * @param tagName    checkbox name属性
 * @param value    选中的值 可以多个逗号隔开
 */
function setValue2Checkbox(tagName, value) {
    var boxes = document.getElementsByName(tagName);
    var val = value.split(",");
    for (i = 0; i < boxes.length; i++) {
        boxes[i].checked = false;	//先初始化
        for (j = 0; j < val.length; j++) {
            if (boxes[i].value == val[j]) {
                boxes[i].checked = true;
                break;
            }
        }
    }
}
/**
 * 设置指定的raido值被选中
 * @param tagName
 * @param value
 */
function setValue2Radio(tagName, value) {
    var boxes = document.getElementsByName(tagName);  //获取所有name=tagName的元素
    for (var i = 0; i < boxes.length; i++) { //对所有结果进行遍历，如果状态是被选中的，则将其选择取消
        if (boxes[i].value == value) {
            boxes[i].checked = true;
            break;
        }
    }
}
/**
 * 清空checkbox被选中的项
 * @param tagName
 */
function setEmpty2Checkbox(tagName) {
    var boxes = document.getElementsByName(tagName);
    for (i = 0; i < boxes.length; i++) {
        if (boxes[i].checked) {
            boxes[i].checked = false;
            break;
        }
    }
}
/**
 * 清空radio被选中的项
 * @param tagName
 */
function setEmpty2Radio(tagName) {
    var boxes = document.getElementsByName(tagName);  //获取所有name=tagName的元素
    for (var i = 0; i < boxes.length; i++) { //对所有结果进行遍历，如果状态是被选中的，则将其选择取消
        if (boxes[i].checked) {
            boxes[i].checked = false;
            break;
        }
    }
}
/**
 * 批量清空标签为空值 tagIds多控件用逗号隔开
 *
 * @param tagName
 * @param tagIds
 */
function setEmpty2TagId(tagIds, tagName) {
    if (isEmpty(tagName)) {
        tagName = "input";
    }
    var value = "";
    var tagId = tagIds.split(",");

    if (!isEmpty(tagId) && tagId.length > 0) {
        switch (tagName) {
            case "combogrid":
                for (var i = 0; i < tagId.length; i++) {
                    setValue2Combogrid(tagId[i], value);
                }
                break;
            case "combotree":
                for (var i = 0; i < tagId.length; i++) {
                    setValue2Combotree(tagId[i], value);
                }
                break;
            case "input":
                for (var i = 0; i < tagId.length; i++) {
                    setValue2Input(tagId[i], value);
                }
                break;
            case "text":
                for (var i = 0; i < tagId.length; i++) {
                    setValue2Input(tagId[i], value);
                }
                break;
            case "textarea":
                for (var i = 0; i < tagId.length; i++) {
                    setValue2Input(tagId[i], value);
                }
                break;
            case "numberbox":
                for (var i = 0; i < tagId.length; i++) {
                    setValue2Numberbox(tagId[i], value);
                }
                break;
            case "radio": // 对应的tagId是radio的name属性
                for (var i = 0; i < tagId.length; i++) {
                    setEmpty2Radio(tagId[i]);
                }
                break;
            case "checkbox": // 对应的tagId是checkbox的name属性
                for (var i = 0; i < tagId.length; i++) {
                    setEmpty2Checkbox(tagId[i]);
                }
                break;
            case "searchbox":
                for (var i = 0; i < tagId.length; i++) {
                    setValue2Searchbox(tagId[i], value);
                }
            case "select":
                for (var i = 0; i < tagId.length; i++) {
                    setValue2Combobox(tagId[i], value);
                }
                break;
            case "combobox":	//与select一样
                for (var i = 0; i < tagId.length; i++) {
                    setValue2Combobox(tagId[i], value);
                }
                break;
            case "searchInput":
                for (var i = 0; i < tagId.length; i++) {
                    setValue2SearchInput(tagId[i], value, value);
                }
                break;
            case "datebox":
                for (var i = 0; i < tagId.length; i++) {
                    setValue2Datebox(tagId[i], value);
                }
                break;
            case "datetimebox":
                for (var i = 0; i < tagId.length; i++) {
                    setValue2Datetimebox(tagId[i], value);
                }
                break;
            default:
                break;
        }
    }

}
/**
 * 判断checkbox或radiobox是否被选中
 *
 * @param tagId
 * @returns
 */
function isCheckedById(tagId) {
    return $("#" + tagId).prop("checked");
}
/**
 * 点击字符串时，选中该对象（radioBox,checkbox)
 *
 * @param tagId
 */
function selectCheckBoxByName(tagName) {
    $("input[name='" + tagName + "']").prop("checked", true);
}

/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * JS说明：upload上传操作JS汇总 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */

/**
 * 设置上传文件类型
 *
 * @param id
 * @param type
 */
function setUploadFileType(id, type) {

    $('#' + id).uploadify('settings', 'fileTypeExts', type);
}

/**
 * 设置上传文件参数
 *
 * @param id
 * @param type
 */
function setUploadformData(id, data) {

    $('#' + id).uploadify('settings', 'formData', data);
}
/**
 * 判断控件值是否为空
 * @param tagId
 * @param tagName
 * @returns {Boolean}
 */
function isEmpty2Tag(tagId, tagName) {
    var tempValue = getValue2TagId(tagId, tagName);
    if (isEmpty(tempValue)) {
        return true;
    } else {
        return false;
    }
}
/**
 * 判断控件的值是否相等
 * @param tagId
 * @param tagName
 * @param value
 * @returns {Boolean}
 */
function equals2Tag(tagId, tagName, value) {
    var tempValue = getValue2TagId(tagId, tagName);
    if (tempValue == value) {
        return true;
    } else {
        return false;
    }
}
/**
 * 判断控件的值是否相等
 * @param tagId
 * @param tagName
 * @param value
 * @returns {Boolean}
 */
function equalsIgnoreCase2Tag(tagId, tagName, value) {
    var tempValue = getValue2TagId(tagId, tagName);
    if (tempValue == value || tempValuea.toUpperCase() == value) {
        return true;
    } else {
        return false;
    }
}