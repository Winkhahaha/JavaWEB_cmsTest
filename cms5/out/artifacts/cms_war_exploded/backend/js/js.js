function form_submit(){
    /*var nameTxt = document.getElementById("user");
    var nameStr = nameTxt.value;
    var passwordStr = document.getElementById("pwd").value;

    if(isNotNull("帐户",nameStr) &&
        length("帐户",nameStr,6,16)&&
        isNotNull("密码",passwordStr) &&
        length("密码",passwordStr,6,16) &&
        regulex("密码",passwordStr))*/
	    document.getElementById("login").submit();

}
function form_reset(){
	document.getElementById("login").reset();
}

//点击验证码图片更换验证码
//需要添加随机数才能使服务器重新请求
function reloadcode(){
    var verify=document.getElementById('safecode');
    verify.src = "/backend/images/checkcode.png?" + Math.random();
}
/*
非空校验
 */
function isNotNull(name,c){
    if(c == null || c.length <= 0){
        alert(name + "字段不能为空！");
        return false;
    }
    return true;
}

/*
长度校验
 */
function length(name,c,minlen,maxlen){
    if(c.length < minlen || c.length > maxlen){
        alert(name + "字段字符个数必须在" + minlen + "到" + maxlen + "个之间");
        return false;
    }
    return true;
}

/*
正则表达式校验
参数 name 要校验的字段名
参数 cc 要校验的字段的值
 */
function  regulex(name,cc) {
    var a = /^.*[0-9]+.*$/;
    var b = /^.*[a-z]+.*$/;
    var c = /^.*[A-Z]+.*$/;
    var d = /^.*[\*\&\^\%\!\$\~\(\)]+.*$/;

    if(a.test(cc) && b.test(cc)&& c.test(cc)&&d.test(cc)){
        return true;
    }
    alert(name + "不符合规则！字段值必须包括数字大小写字母及符号" );
    return false;
}