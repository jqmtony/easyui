package com.zen.easyui.tag;

import com.zen.easyui.util.RegulationUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;


@Data
@Slf4j
@EqualsAndHashCode(callSuper = false)
public class EasyUiUploadifyTag extends TagSupport {

    protected String id;// ID

    protected String url = "fileList.do?method=fileUpload";// url默认值

    protected String name;// 控件名称

    protected String formData;// 参数名称

    protected String extend = "";// 上传文件的扩展名

    protected String buttonText = "浏览文件";// 按钮文本

    protected boolean multi = true;// 是否多文件

    protected String queueID = "filediv";// 文件容器ID

    protected boolean windowOrDialog = true;// 是否是弹出窗口模式

    protected boolean autoUpload = false;// 是否自动上传

    protected String uploadNum = "50";// 上传数量

    protected String onQueueComplete;// 所有文件上传成功处理函数

    protected boolean uploadFileView = false;// 生成查看删除链接

    protected String fileSizeLimit = "50MB";// 文件大小

    protected String uploadSingleSuccessCallback;// 每一个文件上传成功处理函数

    protected String uploadSuccessCallback = ""; // 所有文件上传成功回调方法

    protected String uploadFailCallback = ""; // 所有文件上传失败回调方法

    protected boolean linkScript = true;// 是否引用js脚本

    public int doStartTag() throws JspTagException {
        return EVAL_PAGE;
    }

    public int doEndTag() throws JspTagException {
        try {
            JspWriter out = this.pageContext.getOut();
            out.print(end().toString());
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return EVAL_PAGE;
    }

    public StringBuffer end() {
        StringBuffer sb = new StringBuffer();
        if ("jpg".equals(this.getExtend())) {
            extend = "*.jpg";
        } else if ("pic".equals(this.getExtend())) {
            extend = "*.jpg;*.jpeg;*.png;*.gif;*.bmp;*.ico;*.tif";
        } else if ("office".equals(this.getExtend())) {
            extend = "*.doc;*.docx;*.txt;*.ppt;*.xls;*.xlsx;*.html;*.htm";
        } else if (RegulationUtil.isEmpty(this.getExtend()) || "all".equals(this.getExtend())) {
            extend = "*.*";
        }
        if (RegulationUtil.isEmpty(id)) {
            id = "uploadify";
        }
        if (RegulationUtil.isEmpty(fileSizeLimit)) {
            fileSizeLimit = "50MB";
        }
        if (RegulationUtil.isEmpty(buttonText)) {
            buttonText = "浏览文件";
        }
        if (RegulationUtil.isEmpty(uploadNum)) {
            uploadNum = "50";
        }
        if (RegulationUtil.isEmpty(url)) {
            url = "fileList.do?method=fileUpload";
        }

        if (this.isLinkScript()) {
            sb.append("<link rel=\"stylesheet\" href=\"plugin/uploadify/css/uploadify.css\" type=\"text/css\"></link>");
            sb.append("<script type=\"text/javascript\" src=\"plugin/uploadify/jquery.uploadify-3.1.js\"></script>");
        }
        // sb.append("<script type=\"text/javascript\" src=\"js/tools/Map.js\"></script>");
        sb.append("<script type=\"text/javascript\">" + "var flag = false;" + "var fileitem=\"\";" + "var fileKey=\"\";"
                // +"var m = new Map();"
                + "$(function(){" + "$(\'#" + id + "\').uploadify({" + "buttonText:\'" + buttonText + "\'," + "auto:" + this.isAutoUpload() + "," + "progressData:\'speed\'," + "multi:" + multi + ","
                + "height:20," + "width:100,"
                + "buttonImg:'images/bootnbg.jpg',"
                // +"method:'post',"
                + "overrideEvents:[\'onDialogClose\']," + "fileTypeDesc:\'文件格式:\'," + "queueID:\'" + queueID + "\'," + "fileTypeExts:\'" + extend + "\'," + "uploadLimit:" + uploadNum + ","
                + "queueSizeLimit:" + ((multi) ? uploadNum : 1) + "," + "removeTimeout:1," + "fileSizeLimit:\'" + fileSizeLimit + "\'," + "swf:\'plugin/uploadify/uploadify.swf\',  " + "uploader:\'" + url
                + "&jsessionid='+$(\"#sessionUID\").val()+'\'," + "onUploadStart : function(file) { ");

        sb.append("$(\'#" + id + "\').uploadify(\"settings\", \"formData\", {");
        sb.append("'multi':encodeURI('" + multi + "')");
        if (formData != null && formData.length() > 0) {
            String[] paramnames = formData.split(",");
            for (int i = 0; i < paramnames.length; i++) {
                sb.append(",");
                sb.append("'" + paramnames[i].split(":")[1] + "':encodeURI(getValue2TagName('" + ((paramnames[i].split(":").length > 2) ? paramnames[i].split(":")[2] : paramnames[i].split(":")[1]) + "','"
                        + paramnames[i].split(":")[0] + "'))");
            }
        }
        sb.append("});");

        sb.append("} ," + "onQueueComplete : function(queueData) { ");
        if (RegulationUtil.isEmpty(uploadSuccessCallback) && this.isWindowOrDialog()) {
            sb.append("var win = frameElement.api.opener;" + "win.reloadTable();" + "euShow(\'上传完成\');" + "frameElement.api.close();");
        } else {
            if (uploadSuccessCallback != null)
                sb.append("" + uploadSuccessCallback + "();");
        }
        if (uploadFileView) {
            sb.append("$(\"#viewmsg\").html(m.toString());");
            sb.append("$(\"#fileKey\").val(fileKey);");
        }
        sb.append("},");
        // 上传成功处理函数
        sb.append("onUploadSuccess : function(file, data, response) {");
        if (!RegulationUtil.isEmpty(this.getUploadSingleSuccessCallback())) {
            sb.append(this.getUploadSingleSuccessCallback() + "(data,file,response);");
        }
        sb.append("var d=$.parseJSON(data);");
        if (uploadFileView) {

            sb.append("var fileitem=\"<span id=\'\"+d.attributes.id+\"\'><a href=\'#\' onclick=openwindow(\'文件查看\',\'\"+d.attributes.viewhref+\"\',\'70%\',\'80%\') title=\'查看\'>\"+d.attributes.name+\"</a><img border=\'0\' onclick=confuploadify(\'\"+d.attributes.delurl+\"\',\'\"+d.attributes.id+\"\') title=\'删除\' src=\'plugin/uploadify/img/uploadify-cancel.png\' widht=\'15\' height=\'15\'>&nbsp;&nbsp;</span>\";");
            sb.append("m.put(d.attributes.id,fileitem);");
            sb.append("fileKey=d.attributes.fileKey;");
        }

        sb.append("if(d.success){");
        sb.append("var win = frameElement.api.opener");
        sb.append("euShow(d.msg);");
        sb.append("}");
        sb.append("},");
        sb.append("onFallback : function(){euShow(\"您未安装FLASH控件，无法上传图片！请安装FLASH控件后再试\")},");
        sb.append("onSelectError : function(file, errorCode, errorMsg){");
        sb.append("switch(errorCode) {");
        sb.append("case -100:");
        sb.append("euShow(\"上传的文件数量已经超出系统限制的\"+$(\'#" + id + "\').uploadify(\'settings\',\'queueSizeLimit\')+\"个文件！\");");
        sb.append("break;");
        sb.append("case -110:" + "euShow(\"文件 [\"+file.name+\"] 大小超出系统限制的\"+$(\'#" + id + "\').uploadify(\'settings\',\'fileSizeLimit\')+\"大小！\");" + "break;" + "case -120:"
                + "euShow(\"文件 [\"+file.name+\"] 大小异常！\");" + "break;" + "case -130:" + "euShow(\"文件 [\"+file.name+\"] 类型不正确！\");" + "break;" + "}");
        sb.append("}," + "onUploadProgress : function(file, bytesUploaded, bytesTotal, totalBytesUploaded, totalBytesTotal) { "
                + "euShow('<span>文件上传成功:'+totalBytesUploaded/1024 + ' KB 已上传 ,总数' + totalBytesTotal/1024 + ' KB.</span>');" + "}" + "});" + "});" + "function upload() {" + "	$(\'#" + id
                + "\').uploadify('upload', '*');" + "		return flag;" + "}" + "function cancel() {" + "$(\'#" + id + "\').uploadify('cancel', '*');" + "}" + "</script>");
        sb.append("<span id=\"" + id + "span\"><input type=\"file\" name=\"" + name + "\" id=\"" + id + "\" /></span>");
        if (uploadFileView) {
            sb.append("<span id=\"viewmsg\"></span>");
            sb.append("<input type=\"hidden\" name=\"fileKey\" id=\"fileKey\" />");
        }

        return sb;
    }

}
