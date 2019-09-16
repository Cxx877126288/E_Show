
function checkHaveChinese(str) {
    if(/.*[\u4e00-\u9fa5]+.*$/.test(str))
    {
        alert("不能含有汉字！");
        return false;
    }
    return true;
}

function checkBorntime(str) {
    var flag = false;
    var timearr = str.split('-');
    if(timearr.length != 3){
        alert("格式输入有误")
    }else{
        if(timearr[0].length != 4){
            alert("年份有误")
        }else{
            if(timearr[0] < 0 || timearr[0] > 2999){
                alert("年份范围应在0 - 3000之间（不包含300）")
            }else{
                if(timearr[1].length != 2){
                    alert("月份有误")
                }else{
                    if(timearr[1] < 00 || timearr[1] > 12){
                        alert("月份范围应在1 - 12月")
                    }else {
                        if(timearr[2].length != 2){
                            alert("日期有误")
                        }else{
                            if(timearr[1] == '01' || timearr[1] == '03' || timearr[1] == '05' || timearr[1] == '07' || timearr[1] == '08' || timearr[1] == '10' || timearr[1] == '12' ){
                                if(timearr[2] <= 00 || timearr[2] > 31){
                                    alert("日期范围应在1 - 31日")
                                }else {
                                    flag = true;
                                }
                            }else if(timearr[1] == '04' || timearr[1] == '06' || timearr[1] == '09' || timearr[1] == '11'){
                                if(timearr[2] <= 00 || timearr[2] > 30){
                                    alert("日期范围应在1 - 30日")
                                }else {
                                    flag = true;
                                }
                            }else if(timearr[1] == '02') {
                                if ((timearr[0] % 4 == 0 || timearr[0] % 100 == 0) && timearr[0] % 400 != 0) {
                                    if(timearr[2] <= 00 || timearr[2] > 29){
                                        alert(timearr[0] + "为闰年，日期范围应在1 - 29日")
                                    }else {
                                        flag = true;
                                    }
                                }else{
                                    if(timearr[2] <= 00 || timearr[2] > 28){
                                        alert(timearr[0] + "为平年，日期范围应在1 - 28日")
                                    }else {
                                        flag = true;
                                    }
                                }

                            }
                        }
                    }
                }
            }
        }
    }
    return flag;
}

function checkEmail(str) {
    if(str.search("@") != -1 && str.search(".") != -1){
        return true;
    }else {
        return false;
    }
}

function checkPwdLength(str) {
    if(str.length <= 6 || str.length >= 16){
        alert("密码长度有误");
        return false;
    }
    else {
        return true;
    }
}

function checkSurePwd(pwd, surepwd) {
    if(pwd == surepwd){
        return true;
    }else {
        alert("两次密码不相同，请重新输入");
        return false;
    }
}