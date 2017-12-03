// Null Value Check Function
function gfn_isNull(str){
	if (str==null) return true;
	if (str=="NaN") return true;
	if (new String(str).valueOf() == "undefined") return true;
	var chkStr = new String(str);
	if (chkStr.valueOf() == "undefined") return true;
	if (chkStr == null) return true;
	if (chkStr.toString().length == 0) return true;
	return false;
}

// Ajax Common Function : START
var gfv_ajaxCallback = "";
function ComAjax(opt_formId){
    this.url = "";     
    this.formId = gfn_isNull(opt_formId) == true ? "commonForm" : opt_formId;
    this.param = "";
     
    if(this.formId == "commonForm"){
        var frm = $("#commonForm");
        if(frm.length > 0){
            frm.remove();
        }
        var str = "<form id='commonForm' name='commonForm'></form>";
        $('body').append(str);
    }
     
    this.setUrl = function setUrl(url){
        this.url = url;
    };
     
    this.setCallback = function setCallback(callBack){
        fv_ajaxCallback = callBack;
    };
 
    this.addParam = function addParam(key,value){
        this.param = this.param + "&" + key + "=" + value;
    };
     
    this.ajax = function ajax(){
        if(this.formId != "commonForm"){
            this.param += "&" + $("#" + this.formId).serialize();
        }
        $.ajax({
            url : this.url,   
            type : "POST",  
            data : this.param,
            async : false,
            success : function(data, status) {
                if(typeof(fv_ajaxCallback) == "function"){
                    fv_ajaxCallback(data);
                }
                else {
                    eval(fv_ajaxCallback + "(data);");
                }
            }
        });
    };
}
// Ajax Common Function : END

// Cookie: START
function setCookie(cookieName, value, exdays){
	var exdate = new Date();
	exdate.setDate(exdate.getDate()+exdays);
	var cookieValue = escape(value)+((exdays==null)? "": "; expires="+exdate.toGMTString());
	document.cookie = cookieName+"="+cookieValue;
}

function deleteCookie(cookieName){
	var expireDate = new Date();
	expireDate.setDate(expireDate.getDate()-1);
	document.cookie = cookieName+"= "+"; expires="+expireDate.toGMTString();
}

function getCookie(cookieName){
	cookieName = cookieName + '=';
	var cookieData = document.cookie;
	var start = cookieData.indexOf(cookieName);
	var cookieValue = '';
	if(start != -1){
		start += cookieName.length;
		var end = cookieData.indexOf(';', start);
		if(end == -1) end = cookieData.length;
		cookieValue = cookieData.substring(start, end);
	}
	return unescape(cookieValue);
	
}
// Cookie: END
//Ajax Common Function : END