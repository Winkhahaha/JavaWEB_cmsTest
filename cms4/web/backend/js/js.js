function form_submit() {
    var nameTxt= document.getElementsById("user");
    var nameStr=nameTxt.value;
    var passwordStr=document.getElementsById("pwd").value;

    if((isNotNull("账户",nameStr)) &&
        length("账户",nameStr,6,16) &&
        isNotNull("密码",passwordStr) &&
        length("密码",passwordStr,6,16) &&
        regulex("密码",passwordStr)){
        document.getElementsById("login").submit();
    }

}

function form_reset() {
    document.getElementById("login").reset();
}

function reloadcode() {
    var verify = document.getElementById('safecode');
    verify.setAttribute('src', 'code.php?' + Math.random());
}

//非空校验
function isNotNull(name) {
    if (c == null || c.length <= 0) {
        alert(name + "不能为空")

        return false;
    }
    return true;
}

//长度校验
function length(name, c, minlen, maxlen) {
    if (c.length < minlen || c.length > maxlen) {
        alert(name + "字段字符个数必须在 " + minlen + " 到 " + maxlen + " 之间")
    }

}

//正则表达式校验
//eval:将串解析为表达式
//var a="10+20";
//alert(eval(a));
// alert(a.test("23eddf"));
    function  regulex(name,cc) {
        var a = /^.*[0-9]+.*$/;
        var b = /^.*[a-z]+.*$/;
        var c = /^.*[A-Z]+.*$/;
        var d = /^.*[\*\&\^\%\!\$\~\(\)]+.*$/;

        if(a.test(cc) && b.test(cc)&& c.test(cc)&&d.test(cc)){
            alert("校验成功");
            return true;
        }
        alert(name + "不符合规则！字段值必须包括数字大小写字母及符号" );
        return false;
}
