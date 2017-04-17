function HashMap()
 {
     /** Map 大小 **/
     var size = 0;
     /** 对象 **/
     var entry = new Object();
     
     /** 存 **/
     this.put = function (key , value)
     {
         if(!this.containsKey(key))
         {
             size ++ ;
             
         }
         entry[key] = value;
     }
     
     /** 取 **/
     this.get = function (key)
     {
         if( this.containsKey(key) )
         {
             return entry[key];
         }
         else
         {
             return null;
         }
     }
     
     /** 删除 **/
     this.remove = function ( key )
     {
         if( delete entry[key] )
         {
             size --;
         }
     }
     
     /** 是否包含 Key **/
     this.containsKey = function ( key )
     {
         return (key in entry);
     }
     
     /** 是否包含 Value **/
     this.containsValue = function ( value )
     {
         for(var prop in entry)
         {
             if(entry[prop] == value)
             {
                 return true;
             }
         }
         return false;
     }
     
     /** 所有 Value **/
     this.values = function ()
     {
         var values = new Array(size);
         for(var prop in entry)
         {
             values.push(entry[prop]);
         }
         return values;
     }
     
     /** 所有 Key **/
     this.keys = function ()
     {
         var keys = new Array(size);
         for(var prop in entry)
         {
             keys.push(prop);
         }
         return keys;
     }
     
     /** 装换成对象 **/
     this.toObject = function ()
     {
    	 return entry;
     }
     
     
     /** Map Size **/
     this.size = function ()
     {
         return size;
     }
 }

//专门用户EasyUI的一些扩展脚本

$.extend($.fn.validatebox.defaults.rules, {
  	float: {  
      validator: function(value, param){  
          return !isNaN(value);  
      },  
      message: "请输入数字"  
  	},
    minLength : { // 判断最小长度
        validator : function(value, param) {
            return value.length >= param[0];
        },
        message : '最少输入 {param} 个字符。'
    },
	date: {  
			validator: function(value){
			    var reg = /^[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}$/
		        return reg.test(value);  
			},  
			message: '此項必須為yyyy-mm-dd格式的日期'  
	} ,
    length:{validator:function(value,param){
        var len=$.trim(value).length;
            return len>=param[0]&&len<=param[1];
        },
            message:"输入内容长度必须介于{0}和{1}之间."
        },
    phone : {// 验证电话号码
        validator : function(value) {
            return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value);
        },
        message : '格式不正确,请使用下面格式:020-88888888'
    },
    mobile : {// 验证手机号码
        validator : function(value) {
            return /^(13|15|18)\d{9}$/i.test(value);
        },
        message : '手机号码格式不正确'
    },
    idcard : {// 验证身份证
        validator : function(value) {
            return /^\d{15}(\d{2}[A-Za-z0-9])?$/i.test(value);
        },
        message : '身份证号码格式不正确'
    },
    intOrFloat : {// 验证整数或小数
        validator : function(value) {
            return /^\d+(\.\d+)?$/i.test(value);
        },
        message : '请输入数字，并确保格式正确'
    },
    currency : {// 验证货币
        validator : function(value) {
            return /^\d+(\.\d+)?$/i.test(value);
        },
        message : '货币格式不正确'
    },
    qq : {// 验证QQ,从10000开始
        validator : function(value) {
            return /^[1-9]\d{4,9}$/i.test(value);
        },
        message : 'QQ号码格式不正确'
    },
    integer : {// 验证整数
        validator : function(value) {
      	return /^[0-9]*[1-9][0-9]*$/i.test(value);
        },
        message : '请输入整数'
    },
    intOrZero : { // 非负整数
        validator : function(value, param) {
            return /^[0-9]+$/.test(value);
        },
        message : '请输入整数或0'
    },
    chinese : {// 验证中文
        validator : function(value) {
            return /^[\u0391-\uFFE5]+$/i.test(value);
        },
        message : '请输入中文'
    },
    notChinese : {// 验证中文
        validator : function(value) {
            return !/[\u4E00-\u9FA5]/i.test(value);
        },
        message : '输入值不能包含中文'
    },
    english : {// 验证英语
        validator : function(value) {
            return /^[A-Za-z]+$/i.test(value);
        },
        message : '请输入英文'
    },
    unnormal : {// 验证是否包含空格和非法字符
        validator : function(value) {
            return /.+/i.test(value);
        },
        message : '输入值不能为空和包含其他非法字符'
    },
    username : {// 验证用户名
        validator : function(value) {
            return /^[a-zA-Z][a-zA-Z0-9_]{5,15}$/i.test(value);
        },
        message : '用户名不合法（字母开头，允许6-16字节，允许字母数字下划线）'
    },
    faxno : {// 验证传真
        validator : function(value) {
//            return /^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$/i.test(value);
            return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value);
        },
        message : '传真号码不正确'
    },
    zip : {// 验证邮政编码
        validator : function(value) {
            return /^[1-9]\d{5}$/i.test(value);
        },
        message : '邮政编码格式不正确'
    },
    ip : {// 验证IP地址
        validator : function(value) {
            return /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/i.test(value);
        },
        message : 'IP地址格式不正确'
    },
    name : {// 验证姓名，可以是中文或英文
            validator : function(value) {
                return /^[\u0391-\uFFE5]+$/i.test(value)|/^\w+[\w\s]+\w+$/i.test(value);
            },
            message : '请输入姓名'
    },
    carNo:{
        validator : function(value){
            return /^[\u4E00-\u9FA5][\da-zA-Z]{6}$/.test(value); 
        },
        message : '车牌号码无效（例：粤J12350）'
    },
    carenergin:{
        validator : function(value){
            return /^[a-zA-Z0-9]{16}$/.test(value); 
        },
        message : '发动机型号无效(例：FG6H012345654584)'
    },
    email:{
        validator : function(value){
        return /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(value); 
    },
    message : '请输入有效的电子邮件账号(例：abc@126.com)'    
    },
    msn:{
        validator : function(value){
        return /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(value); 
    },
    message : '请输入有效的msn账号(例：abc@hotnail(msn/live).com)'
    },
    same:{
        validator : function(value, param){
            if($("#"+param[0]).val() != "" && value != ""){
                return $("#"+param[0]).val() == value; 
            }else{
                return true;
            }
        },
        message : '两次输入的密码不一致！'    
    }
});

$.extend($.fn.combo.methods, {

  /**
* 禁用combo文本域
* @param {Object} jq
* @param {Object} param stopArrowFocus:是否阻止点击下拉按钮时foucs文本域 为true的话，下拉面板显示的时候，将不会把焦点放到输入框上
* stoptype:禁用类型，包含disable和readOnly两种方式 禁用text的方式，可以为'disabled'或者'readonly'.
*/

  disableTextbox : function(jq, param) {
   return jq.each(function() {
       param = param || {};
       var textbox = $(this).combo("textbox");
       var that = this;
       var panel = $(this).combo("panel");
       var data = $(this).data('combo');
       if (param.stopArrowFocus) {
           data.stopArrowFocus = param.stopArrowFocus;
           var arrowbox = $.data(this, 'combo').combo.find('span.combo-arrow');
           arrowbox.unbind('click.combo').bind('click.combo', function() {
               if (panel.is(":visible")) {
                   $(that).combo('hidePanel');
               } else {
                   $("div.combo-panel").panel("close");
                   $(that).combo('showPanel');
               }
           });
           textbox.unbind('mousedown.mycombo').bind('mousedown.mycombo', function(e) {
                   e.preventDefault();
           });
       }
       textbox.prop(param.stoptype?param.stoptype:'disabled', true);
       data.stoptype = param.stoptype?param.stoptype:'disabled';
   });

  },

  /**
* 还原文本域
* @param {Object} jq
*/
  enableTextbox : function(jq) {
   return jq.each(function() {
       var textbox = $(this).combo("textbox");
       var data = $(this).data('combo');
       if (data.stopArrowFocus) {
           var that = this;
           var panel = $(this).combo("panel");
           var arrowbox = $.data(this, 'combo').combo.find('span.combo-arrow');
           arrowbox.unbind('click.combo').bind('click.combo', function() {
               if (panel.is(":visible")) {
                   $(that).combo('hidePanel');
               } else {
                   $("div.combo-panel").panel("close");
                   $(that).combo('showPanel');
               }
               textbox.focus();
           });
           textbox.unbind('mousedown.mycombo');
           data.stopArrowFocus = null;
       }
       textbox.prop(data.stoptype, false);
       data.stoptype = null;
   });

  }

});
/**
 * datagrid添加组件编辑功能
 */
$.extend($.fn.datagrid.methods, {
	addEditor : function(jq, param) {
		if (param instanceof Array) {
			$.each(param, function(index, item) {
				var e = $(jq).datagrid('getColumnOption', item.field);
				e.editor = item.editor;
			});
		} else {
			var e = $(jq).datagrid('getColumnOption', param.field);
			e.editor = param.editor;
		}
	},
	removeEditor : function(jq, param) {
		if (param instanceof Array) {
			$.each(param, function(index, item) {
				var e = $(jq).datagrid('getColumnOption', item);
				e.editor = {};
			});
		} else {
			var e = $(jq).datagrid('getColumnOption', param);
			e.editor = {};
		}
	},clearAll:function(jq){
        return jq.each(function(){
            $(this).datagrid('loadData',{total:0,rows:[]});
        });
    },
	//根据列属性相同合并datagrid单元格
	autoMergeCells : function (jq, fields) {
        return jq.each(function () {
            var target = $(this);
            if (!fields) {
                fields = target.datagrid("getColumnFields");
            }
            var rows = target.datagrid("getRows");
            var i = 0,
            j = 0,
            temp = {};
            for (i; i < rows.length; i++) {
                var row = rows[i];
                j = 0;
                for (j; j < fields.length; j++) {
                    var field = fields[j];
                    var tf = temp[field];
                    if (!tf) {
                        tf = temp[field] = {};
                        tf[row[field]] = [i];
                    } else {
                        var tfv = tf[row[field]];
                        if (tfv) {
                            tfv.push(i);
                        } else {
                          tfv = tf[row[field]] = [i];
                        }
                    }
                }
            }
            $.each(temp, function (field, colunm) {
                $.each(colunm, function () {
                    var group = this;
                    if (group.length > 1) {
                      var before,
                        after,
                        megerIndex = group[0];
                        for (var i = 0; i < group.length; i++) {
                            before = group[i];
                            after = group[i + 1];
                            if (after && (after - before) == 1) {
                                continue;
                            }
                            var rowspan = before - megerIndex + 1;
                            if (rowspan > 1) {
                                target.datagrid('mergeCells', {
                                    index : megerIndex,
                                    field : field,
                                    rowspan : rowspan
                                });
                            }
                            if (after && (after - before) != 1) {
                                megerIndex = after;
                            }
                        }
                    }
               });
            });
        });
    }
});
/**
 * easyui combobox扩展可以默认选择第一行
 */
$.extend($.fn.combobox.methods, {
    selectedIndex: function (jq, index) {
        if (!index) {
            index = 0;
        }
        $(jq).combobox({
            onLoadSuccess: function () {
                var opt = $(jq).combobox('options');
                var data = $(jq).combobox('getData');
 
                for (var i = 0; i < data.length; i++) {
                    if (i == index) {
                        $(jq).combobox('setValue', eval('data[index].' + opt.valueField));
                        break;
                    }
                }
            }
        });
    }
});

$.extend($.fn.combobox.defaults, {
	getValue : function(jq) {
		var opts = $(jq).combobox('options');
		if (opts.multiple) {
			var values = $(jq).combobox('getValues');
			if (values.length > 0) {
				if (values[0] == '' || values[0] == ' ') {
					return values.join(',').substring(1);// 新增的时候会把空白当成一个值了，去掉
				}
			}
			return values.join(',');
		} else
			return $(jq).combobox("getValue");
	},
	setValue : function(jq, value) {
		var opts = $(jq).combobox('options');
		if (opts.multiple && value.indexOf(opts.separator) != -1) {// 多选且不只一个值
			var values = value.split(opts.separator);
			$(jq).combobox("setValues", values);
		} else
			$(jq).combobox("setValue", value);
	}
});


/**
 * @author hexuming
 * 
 * @requires jQuery,EasySSH
 * 
 * 扩展datagrid的editor
 * 
 * 增加带复选框的下拉树
 * 
 * 增加日期时间组件editor
 * 
 * 增加多选combobox组件
 */
$.extend($.fn.datagrid.defaults.editors, {
	combocheckboxtree : {
		init : function(container, options) {
			var editor = $('<input />').appendTo(container);
			options.multiple = true;
			editor.combotree(options);
			return editor;
		},
		destroy : function(target) {
			$(target).combotree('destroy');
		},
		getValue : function(target) {
			return $(target).combotree('getValues').join(',');
		},
		setValue : function(target, value) {
			$(target).combotree('setValues', sy.getList(value));
		},
		resize : function(target, width) {
			$(target).combotree('resize', width);
		}
	},
	datetimebox : {
		init : function(container, options) {
			var editor = $('<input />').appendTo(container);
			editor.datetimebox(options);
			return editor;
		},
		destroy : function(target) {
			$(target).datetimebox('destroy');
		},
		getValue : function(target) {
			return $(target).datetimebox('getValue');
		},
		setValue : function(target, value) {
			$(target).datetimebox('setValue', value);
		},
		resize : function(target, width) {
			$(target).datetimebox('resize', width);
		}
	},
	timespinner : {
		init : function(container, options) {
			var editor = $('<input />').appendTo(container);
			editor.timespinner(options);
			return editor;
		},
		destroy : function(target) {
			$(target).timespinner('destroy');
		},
		getValue : function(target) {
			return $(target).timespinner('getValue');
		},
		setValue : function(target, value) {
			$(target).timespinner('setValue', value);
		},
		resize : function(target, width) {
			$(target).timespinner('resize', width);
		}
	},
	multiplecombobox : {
		init : function(container, options) {
			var editor = $('<input />').appendTo(container);
			options.multiple = true;
			editor.combobox(options);
			return editor;
		},
		destroy : function(target) {
			$(target).combobox('destroy');
		},
		getValue : function(target) {
			return $(target).combobox('getValues').join(',');
		},
		setValue : function(target, value) {
			$(target).combobox('setValues', sy.getList(value));
		},
		resize : function(target, width) {
			$(target).combobox('resize', width);
		}
	},
	searchInput : {//配置型弹窗
		init : function(container, options) {
			//var editor = $('<input   class=\"searchbox-text\"   name=\''+options.tagId+'\'   id=\''+options.tagId+'\' style=\"width: 120px;line-height: 20px;\" readonly=\"readonly\"/><span><span <span class=\"searchbox-button\" style=\"height: 20px;\" onclick=\"openSearchWindow2DgColumn(this,\''+options.datagridId+'\',\''+options.tagId+'\',\''+options.recordFlag+'\',\''+options.selectType+'\',\''+(isEmpty(options.maSqlNum)? 1 : options.maSqlNum)+'\',\''+(isEmpty(options.miSqlNum)? 1 : options.miSqlNum)+'\',\''+(isEmpty(options.maSqlParams)? "" : options.maSqlParams)+'\',\''+(isEmpty(options.miSqlParams)? "" : options.miSqlParams)+'\',\''+options.callback+'\',\''+options.inputValidate+'\',\''+options.title+'\',\''+options.width+'\',\''+options.height+'\')\">').appendTo(container);
			var editor = $('<span class=\"searchbox\" style=\"width: 150px;\"><input type=\"text\" class=\"searchbox-text\" name=\''+options.tagId+'\'   id=\''+options.tagId+'\' style=\"width: 120px;line-height: 20px;\" readonly=\"readonly\"/><span><span <span class=\"searchbox-button\" style=\"height: 20px;\" onclick=\"openSearchWindow2DgColumn(this,\''+options.datagridId+'\',\''+options.tagId+'\',\''+options.recordFlag+'\',\''+options.selectType+'\',\''+(isEmpty(options.maSqlNum)? 1 : options.maSqlNum)+'\',\''+(isEmpty(options.miSqlNum)? 1 : options.miSqlNum)+'\',\''+(isEmpty(options.maSqlParams)? "" : options.maSqlParams)+'\',\''+(isEmpty(options.miSqlParams)? "" : options.miSqlParams)+'\',\''+options.callback+'\',\''+options.inputValidate+'\',\''+options.title+'\',\''+options.width+'\',\''+options.height+'\')\"></span></span></span>').appendTo(container);
			//editor.searchbox(options);
			return editor;
		},
		destroy : function(target) {
			//$(target).searchbox('destroy');
		},
		getValue : function(target) {
			//alert('$(target).val()='+$(target).val());			
			return $(target).find(".searchbox-text").first().val();
		},
		setValue : function(target, value) {
			$(target).find(".searchbox-text").first().val(value);
		},
		resize : function(target, width) {
			 var input =$(target);
			 input.width(width);
			 $(target).find(".searchbox-text").width(width-20);
		}
	},
	searchInputSelf : {//自定义url型弹窗
		init : function(container, options) {				
			var editor = $('<span class=\"searchbox\" style=\"width: 150px;\"><input type=\"text\" class=\"searchbox-text\" name=\''+options.tagId+'\'   id=\''+options.tagId+'\' style=\"width: 120px;line-height: 20px;\" readonly=\"readonly\"/><span><span class=\"searchbox-button\" style=\"height: 20px;\" onclick=\"openSearchWindowSelf2DgColumn(\''+options.url+'\',\''+options.title+'\',\''+options.width+'\',\''+options.height+'\')\"></span></span></span>').appendTo(container);
			return editor;
		},
		destroy : function(target) {			
		},
		getValue : function(target) {				
			return $(target).find(".searchbox-text").first().val();
		},
		setValue : function(target, value) {			
			$(target).find(".searchbox-text").first().val(value);
		},
		resize : function(target, width) {	
			 var input =$(target);
			 input.width(width);
			 $(target).find(".searchbox-text").width(width-20);
		}
	},	
	/*editarea : {//多行文本编辑弹窗 试用
		init : function(container, options) {				
			var editor = $('<input name=\''+options.id+'\'   id=\''+options.id+'\' type=\"text\" readonly=\"readonly\" onclick=\"openDialog(\'common.do?method=toEditPage&page=js/editarea/edit_area&id='+options.id+'&height='+options.height+'&syntax='+options.syntax+'&gridId='+options.gridId+'\',\''+options.title+'\',\''+options.width+'\',\''+options.height+'\')\"/>').appendTo(container);
			return editor;
		},
		destroy : function(target) {			
		},
		getValue : function(target) {				
			return $(target).val(); 
		},
		setValue : function(target, value) {			
			$(target).val(value);
		},
		resize : function(target, width) {	
			var input = $(target);    
            input.width(width);
		}
	},*/	
	datetimeboxgrid : {
		init : function(container, options) {
			var editor = $('<span class="combo datebox" style="width: 120px; height: 20px;"><input type="text" class="Wdate combo-text easyui-validatebox" style="width: 115px; height: 20px; line-height: 20px;" readonly="readonly" onclick="WdatePicker({dateFmt:\'yyyy-MM-dd HH:mm:ss\'})"/></span>').appendTo(container);
			return editor;
		},
		destroy : function(target) {
		},
		getValue : function(target) {
			return $(target).find(".combo-text").first().val();
		},
		setValue : function(target, value) {
			$(target).find(".combo-text").first().val(value);
		},
		resize : function(target, width) {
			var targetH =$(target);
			 targetH.width(width);
			 $(targetH).find("input").width(width-10);
		}
	},
	/*datebox : {
		init : function(container, options) {
			var editor = $('<input />').appendTo(container);
			editor.datebox(options);
		},
		destroy : function(target) {
			$(target).datebox('destroy');
		},
		getValue : function(target) {
			return $(target).datebox('getValue');
		},
		setValue : function(target, value) {
			$(target).datebox('setValue', value);
		},
		resize : function(target, width) {
			$(target).datebox("resize",width);
		}
	},*/
	dateboxgrid : {
		init : function(container, options) {
			 
			var editor = $('<span class="combo datebox" style="width: 100px; height: 20px;"><input type="text" class="Wdate combo-text easyui-validatebox" style="width: 115px; height: 20px; line-height: 20px;" readonly="readonly" onclick="WdatePicker()"/></span>').appendTo(container);
			return editor;
		},
		destroy : function(target) {
		},
		getValue : function(target) {
			return $(target).find(".combo-text").first().val();
		},
		setValue : function(target, value) {
			$(target).find(".combo-text").first().val(value);
		},
		resize : function(target, width) {
			 var targetH =$(target);
			 targetH.width(width);
			 $(targetH).find("input").width(width-10);
		}
	},
	select : {
		init : function(container, options) {
			
			if(!isEmpty(options.data)){
				return createOptionStr(container, options);				
			}else if(!isEmpty(options.url)){
				var htmlTarget="";
					$.ajax({
						url:options.url,
						async:false, //此处必须同步请求,否则将异步造成当前组件在异步线程中生成,而主线程返回并未生成
						success: function(json){					
							if(!isEmpty(json)){
								  //selectDataMap.put(options.url,json);
								  options.data=evalObj(json);
								  
								  htmlTarget= createOptionStr(container, options);
								}
							}
					});
			/*	$.getJSON(options.url, function(json){					
						if(!isEmpty(json)){
						  //selectDataMap.put(options.url,json);
						  options.data=json;
						  
						  return createOptionStr(container, options);
						}
					});*/
				 
				return htmlTarget;
			}
			
		},
		destroy : function(target) {
		},
		getValue : function(target) {
			//alert("target:"+target);
			//alert($(target).val());
			return $(target).val();
		},
		setValue : function(target, value) {
			if(!isEmpty(value)){
			
			var sObj=$(target);
			var sOptions = sObj.find("option");
			
			sOptions.each(function (i){
				
				if($(this).val() === value){
					
					$(this).prop("selected",true);
				}				
			});
			}
		},
		resize : function(target, width) {
			var span=$(target);
			span.width(width);
		}
	}
});

/* 生成datagrid下拉框 */
function createOptionStr(container, options){
	var optionStr="";
	for(var i=0;i<options.data.length;i++){
		optionStr += "<option value='"+eval("options.data[i]."+options.valueField)+"'>"+eval("options.data[i]."+options.textField)+"</option>";
	}
	var editor = $("<select class='combo combo-text' style='height: 22px;width:100px;"+options.style+"' onchange='"+options.onChange+"' >"+optionStr+"</select>").appendTo(container);
	return editor;
}

/* 定义全局对象，类似于命名空间或包的作用 */
var sy = $.extend({}, sy);

sy.fs = function(str) {
	for ( var i = 0; i < arguments.length - 1; i++) {
		str = str.replace("{" + i + "}", arguments[i + 1]);
	}
	return str;
};

sy.json2String = function(o) {
	var r = [];
	if (typeof o == "string")
		return "\"" + o.replace(/([\'\"\\])/g, "\\$1").replace(/(\n)/g, "\\n").replace(/(\r)/g, "\\r").replace(/(\t)/g, "\\t") + "\"";
	if (typeof o == "object") {
		if (!o.sort) {
			for ( var i in o)
				r.push(i + ":" + obj2str(o[i]));
			if (!!document.all && !/^\n?function\s*toString\(\)\s*\{\n?\s*\[native code\]\n?\s*\}\n?\s*$/.test(o.toString)) {
				r.push("toString:" + o.toString.toString());
			}
			r = "{" + r.join() + "}";
		} else {
			for ( var i = 0; i < o.length; i++)
				r.push(obj2str(o[i]));
			r = "[" + r.join() + "]";
		}
		return r;
	}
	return o.toString();
};
/**
 * 防止panel/window/dialog组件超出浏览器边界
 * @param left
 * @param top
 * @return
 */
var easyuiPanelOnMove = function(left, top) {
	var l = left;
	var t = top;
	if (l < 1) {
		l = 1;
	}
	if (t < 1) {
		t = 1;
	}
	var width = parseInt($(this).parent().css('width')) + 14;
	var height = parseInt($(this).parent().css('height')) + 14;
	var right = l + width;
	var buttom = t + height;
	var browserWidth = $(document).width();
	var browserHeight = $(document).height();
	if (right > browserWidth) {
		l = browserWidth - width;
	}
	if (buttom > browserHeight) {
		t = browserHeight - height;
	}
	$(this).parent().css({/* 修正面板位置 */
		left : l,
		top : t
	});
};
$.fn.dialog.defaults.onMove = easyuiPanelOnMove;
$.fn.window.defaults.onMove = easyuiPanelOnMove;
$.fn.panel.defaults.onMove = easyuiPanelOnMove;

$.extend($.fn.tabs.methods, {

	  /**
	   * 绑定双击事件
	   * @param {Object} jq
	   * @param {Object} caller 绑定的事件处理程序
	   */
	  bindDblclick: function(jq, caller){
	      return jq.each(function(){
	          var that = this;
	          $(this).children("div.tabs-header").find("ul.tabs").undelegate('li', 'dblclick.tabs').delegate('li', 'dblclick.tabs', function(e){
	              if (caller && typeof(caller) == 'function') {
	                  var title = $(this).text();
	                  var index = $(that).tabs('getTabIndex', $(that).tabs('getTab', title));
	                  caller(index, title);
	              }
	          });
	      });
	  },

	  /**
	   * 解除绑定双击事件
	   * @param {Object} jq
	   */
	  unbindDblclick: function(jq){
	      return jq.each(function(){
	          $(this).children("div.tabs-header").find("ul.tabs").undelegate('li', 'dblclick.tabs');
	      });
	  }
	});

//未验证：Combo,Combobox添加清除值功能
//http://blog.csdn.net/WoXiaoXingXing/article/details/17002525
(function($){
	    
	    //初始化清除按钮
	    function initClear(target){
	        var jq = $(target);
	        var opts = jq.data('combo').options;
	        var combo = jq.data('combo').combo;
	        var arrow = combo.find('span.combo-arrow');
	        
	        var clear = arrow.siblings("span.combo-clear");
	        if(clear.size()==0){
	            //创建清除按钮。
	            clear = $('<span class="combo-clear" style="height: 20px;"></span>');
	            
	            //清除按钮添加悬停效果。
	            clear.unbind("mouseenter.combo mouseleave.combo").bind("mouseenter.combo mouseleave.combo",
	                function(event){
	                    var isEnter = event.type=="mouseenter";
	                    clear[isEnter ? 'addClass' : 'removeClass']("combo-clear-hover");
	                }
	            );
	            //清除按钮添加点击事件，清除当前选中值及隐藏选择面板。
	            clear.unbind("click.combo").bind("click.combo",function(){
	                jq.combo("setValue","").combo("setText","");
	                jq.combo('hidePanel');
	            });
	            arrow.before(clear);
	        };
	        var input = combo.find("input.combo-text");
	        input.outerWidth(input.outerWidth()-clear.outerWidth());
	        
	        opts.initClear = true;//已进行清除按钮初始化。
	    }
	    
	    //扩展easyui combo添加清除当前值。
	    var oldResize = $.fn.combo.methods.resize;
	    $.extend($.fn.combo.methods,{
	        initClear:function(jq){
	            return jq.each(function(){
	                 initClear(this);
	            });
	        },
	        resize:function(jq){
	            //调用默认combo resize方法。
				if(!isEmpty(oldResize)) {
			      var returnValue = oldResize.apply(this,arguments);
				            var opts = jq.data("combo").options;
				            if(opts.initClear){
				                jq.combo("initClear",jq);
				            }
				            return returnValue;
				}
	        }
	    });
	}(jQuery));
