function getUsbkeySatrt(userName) {
	var DevicePath, ret, n, mylen;
	try {
		// 建立操作我们的锁的控件对象，用于操作我们的锁
		var s_simnew61;
		// 创建插件或控件
		if (navigator.userAgent.indexOf("MSIE") > 0
				&& !navigator.userAgent.indexOf("opera") > -1) {
			s_simnew61 = new ActiveXObject("Syunew6A.s_simnew6");
		} else {
			s_simnew61 = document.getElementById('s_simnew61');
		}
		for ( var i = 0; i < 10; i++) {
			DevicePath = s_simnew61.FindPort(i);
			ret = s_simnew61.YRead(0, 1, "ffffffff", "ffffffff", DevicePath);
			mylen = s_simnew61.GetBuf(0);

			var ukeyName = s_simnew61.YReadString(1, mylen, "ffffffff",
					"ffffffff", DevicePath);
			if (ukeyName == userName) {
				return i;
			}
		}
	} catch (e) {
		euConfirm("失败：可能是没有安装相应的控件或插件,是否下载控件", dowloadFlie);
		return -1;
	}
	euShow("未查找到当前用户Usbkey");
	return -1;
}

function getSimnew61() {

	try {
		// 建立操作我们的锁的控件对象，用于操作我们的锁
		var s_simnew61;
		// 创建插件或控件
		if (navigator.userAgent.indexOf("MSIE") > 0
				&& !navigator.userAgent.indexOf("opera") > -1) {
			s_simnew61 = new ActiveXObject("Syunew6A.s_simnew6");
		} else {
			s_simnew61 = document.getElementById('s_simnew61');
		}

		return s_simnew61;
	} catch (e) {
		euConfirm("失败：可能是没有安装相应的控件或插件,是否下载控件", dowloadFlie);
	}

}

function dowloadFlie() {
	window.location = "/PeopleWork/plug-in/SetUp.exe";
	euShow("驱动下载成功后,依然提示下载附件时,请重启浏览器.");
}

function getUserkeyName() {
	var DevicePath, ret, n, mylen;
	try {
		// 建立操作我们的锁的控件对象，用于操作我们的锁
		var s_simnew61;
		// 创建插件或控件
		if (navigator.userAgent.indexOf("MSIE") > 0
				&& !navigator.userAgent.indexOf("opera") > -1) {
			s_simnew61 = new ActiveXObject("Syunew6A.s_simnew6");
		} else {
			s_simnew61 = document.getElementById('s_simnew61');
		}
			DevicePath = s_simnew61.FindPort(0);
			
			if (s_simnew61.LastError != 0) {
				euShow("未发现UEKY，请插入UEKY");
				return null;
			} else {
				ret = s_simnew61.YRead(0, 1, "ffffffff", "ffffffff", DevicePath);
				mylen = s_simnew61.GetBuf(0);
				var ukeyName = s_simnew61.YReadString(1, mylen, "ffffffff",
						"ffffffff", DevicePath);
				return ukeyName;
			}
	} catch (e) {
		euConfirm("失败：可能是没有安装相应的控件或插件,是否下载控件", dowloadFlie);
		return null;
	}
	//euShow("未查找到Usbkey设备");
	//return null;
}

function setUkeyUserNameAndKey(start, name, key,password) {

	try {
		var DevicePath, mylen, ret, keyid, username, mykey, outstring, address, mydata, i, InString, versionex, version;
		if (navigator.userAgent.indexOf("MSIE") > 0
				&& !navigator.userAgent.indexOf("opera") > -1) {
			s_simnew1 = new ActiveXObject("Syunew6A.s_simnew6");
		} else {
			s_simnew1 = document.getElementById("s_simnew61");
		}
		DevicePath = s_simnew1.FindPort(start);// '查找加密锁
		// DevicePath = s_simnew1.FindPort_2(0,1, 70967193);//'查找指定的加密锁（使用普通算法一）
		// DevicePath = s_simnew1.FindPort_3(0,1, 462616181);//'查找加密锁（使用普通算法二）
		if (s_simnew1.LastError != 0) {
			euShow("未发现UEKY，请插入UEKY");
		} else {
			// 普通算法操作
			{
				ret = s_simnew1.sWrite(2, DevicePath);
				if (ret != 0) {
					euShow("sWrite错误,错误码是" + s_simnew1.LastError.toString());
					return false;
				}
				ret = s_simnew1.sRead(DevicePath);
				if (s_simnew1.LastError != 0) {
					euShow("sRead,错误码是" + s_simnew1.LastError.toString());
					return false;
				}
				// window.alert ( ret);
				ret = s_simnew1.sWrite_2(2, DevicePath);
				if (ret != 0) {
					euShow("sWrite_2错误,错误码是" + s_simnew1.LastError.toString());
					return false;
				}
				ret = s_simnew1.sRead(DevicePath);
				if (s_simnew1.LastError != 0) {
					euShow("sRead,错误码是" + s_simnew1.LastError.toString());
					return false;
				}
				// window.alert ( ret);

				ret = s_simnew1.sWriteEx(1, DevicePath);
				if (s_simnew1.LastError != 0) {
					euShow("sWriteEx错误");
					return false;
				}
				// window.alert ( ret);
				ret = s_simnew1.sWrite_2Ex(1, DevicePath);
				if (s_simnew1.LastError != 0) {
					euShow("sWrite_2Ex错误");
					return false;
				}
				// window.alert ( ret);
				ret = s_simnew1.sWriteEx_New(1, DevicePath);
				if (s_simnew1.LastError != 0) {
					euShow("sWriteEx_New错误");
					return false;
				}
				// window.alert ( ret);
				ret = s_simnew1.sWrite_2Ex_New(1, DevicePath);
				if (s_simnew1.LastError != 0) {
					euShow("sWrite_2Ex_New错误");
					return false;
				}
				// window.alert ( ret);
			}

			// 设置增强算法密钥一
			{
				mykey = key;
				ret = s_simnew1.SetCal_2(mykey, DevicePath);
				if (ret != 0) {
					euShow("设置增强算法密钥错误");
					return false;
				}
				// euShow("已成功设置了增强算法密钥一");

			}
			// 使用增强算法一对字符串进行加密
			/*
			 * { outstring = s_simnew1.EncString("加密锁", DevicePath); if (ret !=
			 * 0) { window.alert("加密字符串出现错误"); return; }
			 * window.alert("已成功成功使用增强算法一对字符串进行加密，加密后的字符串为：" + outstring); }
			 */
			// 对加密后的字符串进行解密
			/*
			 * { outstring= s_simnew1.DecString(outstring,
			 * "1234567890ABCDEF1234567890ABCDEF"); if (ret != 0) {
			 * window.alert("解密字符串出现错误"); return; }
			 * window.alert("已成功成功对字符串进行解密，解密后的字符串为：" + outstring); }
			 */
			// 使用增强算法一对二进制数据进行加密
			{

				mydata = 0;
				for (i = 0; i < 8; i++) {
					s_simnew1.SetEncBuf(i, mydata);
					mydata++;
				}
				ret = s_simnew1.Cal(DevicePath);
				if (ret != 0) {
					s_simnew1("加密数据时失败");
					return false;
				}
				outstring = "";
				for (i = 0; i < 8; i++) {
					outstring = outstring + s_simnew1.GetEncBuf(i) + ",";
				}
				// euShow("已成功使用增强算法一对二进制数据进行了加密"+outstring);
			}

			// 写入字符串带长度
			{
				// 写入字符串带长度，,使用默认的读密码
				InString = name;

				// 写入字符串到地址1
				nlen = s_simnew1.YWriteString(InString, 1, "ffffffff",
						"ffffffff", DevicePath);
				if (s_simnew1.LastError != 0) {
					euShow("写入字符串(带长度)错误。");
					return false;
				}
				// 写入字符串的长度到地址0
				s_simnew1.SetBuf(nlen, 0);
				ret = s_simnew1
						.YWrite(0, 1, "ffffffff", "ffffffff", DevicePath);
				if (ret != 0){
					euShow("写入字符串长度错误。错误码：");
					return false;
				}
				// else
				// euShow("写入字符串(带长度)成功");
			}
			// 读取字符串带长度
			{

				// 先从地址0读到以前写入的字符串的长度
				ret = s_simnew1.YRead(0, 1, "ffffffff", "ffffffff", DevicePath);
				nlen = s_simnew1.GetBuf(0);
				if (ret != 0) {
					euShow("读取字符串长度错误。错误码：");
					return false;
				}
				// 再读取相应长度的字符串
				outString = s_simnew1.YReadString(1, nlen, "ffffffff",
						"ffffffff", DevicePath);
				if (s_simnew1.LastError != 0){
					euShow("读取字符串(带长度)错误。错误码：");
					return false;
				}
				// else
				// euShow("已成功读取字符串(带长度)：" + outString);

			}
			{
				  //写入用户密码到地址21，使用默认的写密码"FFFFFFFF","FFFFFFFF"
				    mylen = s_simnew1.YWriteString(password, 21, "ffffffff", "ffffffff", DevicePath);
				    if( s_simnew61.LastError!= 0 )
				    { 
				    	euShow ( "写入用户密码失败。错误码是："+s_simnew1.LastError.toString());return false;
				    }
				    s_simnew61.SetBuf( mylen, 0);
				    //写入用户名密码的字符串长度到地址20，使用默认的写密码"FFFFFFFF","FFFFFFFF"
				    ret = s_simnew1.YWrite(20, 1, "ffffffff", "ffffffff",DevicePath);
				    if( ret != 0 )
				    { 
				    	euShow( "写入用户密码长度失败。错误码是："+s_simnew1.LastError.toString());return false;
				    }
				   // window.alert ( "写入成功");

				}

		}
		return true;
	}

	catch (e) {
		euConfirm("失败：可能是没有安装相应的控件或插件,是否下载控件", dowloadFlie);
		return false;
	}
}

function getUserkeyPassword(start) {
	var DevicePath, ret, n, mylen;
	try {
		// 建立操作我们的锁的控件对象，用于操作我们的锁
		var s_simnew61;
		// 创建插件或控件
		if (navigator.userAgent.indexOf("MSIE") > 0
				&& !navigator.userAgent.indexOf("opera") > -1) {
			s_simnew61 = new ActiveXObject("Syunew6A.s_simnew6");
		} else {
			s_simnew61 = document.getElementById('s_simnew61');
		}
			DevicePath = s_simnew61.FindPort(start);
			if (s_simnew61.LastError != 0) {
				euShow("未发现UEKY，请插入UEKY");
				return null;
			} else {
		
				ret=s_simnew61.YRead(20,1,"ffffffff","ffffffff",DevicePath);
				mylen =s_simnew61.GetBuf(0);
				//再从地址21读取相应的长度的字符串，,使用默认的读密码"FFFFFFFF","FFFFFFFF"
				var password =s_simnew61.YReadString(21,mylen,"ffffffff", "ffffffff", DevicePath);
				if( s_simnew61.LastError!= 0 )
				{
					window.alert( "获取用户密码错误,错误码是:"+s_simnew61.LastError.toString());
					return ;
				}
				
				return password;
			}
	} catch (e) {
		euConfirm("失败：可能是没有安装相应的控件或插件,是否下载控件", dowloadFlie);
		return null;
	}
	//euShow("未查找到Usbkey设备");
	//return null;
}

function usbKeyEnc(start, value) {
	var DevicePath, mylen, ret;
	// 建立操作我们的锁的控件对象，用于操作我们的锁
	var s_simnew61 = getSimnew61();
	// 查找是否存在锁,这里使用了FindPort函数
	DevicePath = s_simnew61.FindPort(start);
	// 这里返回对随机数的HASH结果
	var encString = s_simnew61.EncString(value, DevicePath);
	if (s_simnew61.LastError != 0) {
		euShow("加密失败:" + s_simnew61.LastError.toString());
		return;
	}
	// euShow(encString);
	return encString;

}
