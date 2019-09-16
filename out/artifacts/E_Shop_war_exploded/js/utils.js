var appName="webDemo";

function show(msg){
	alert(msg);
}

/**
 * 输出一行
 * @param msg
 */
function writeLine(msg){
	document.write("<br/>"+msg);
}

/*ｓｔｒｉｎｇ增加trim()方法，去除头尾的空格*/
String.prototype.trim=function(){
	//return this.substring(2,this.length-2);
	return this.replace(/(^\s*)|(\s*$)/g, ""); 
};

/**
 * 修改ｄｉｖ显示的ｈｔｍｌ内容
 * @param divObjId
 * @param infoHtml
 */
function showDivInfo(divObjId,infoHtml){
	var displayDivObj=document.getElementById(divObjId); //获取ｄｉｖ对象
	displayDivObj.innerHTML=infoHtml; //修改ｄｉｖ对象内的ｈｔｍｌ内容
}


function isEmpty(str) {
	if(str=="" || str == null)
		return true;
	else
		return false;
}