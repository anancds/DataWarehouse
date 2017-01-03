/**
 * Created by laiqin on 2016/12/28.
 */

function queryString(sParam){
    var sBase = window.location.href;
    var re = eval("/" + sParam + "=([^&]*)/");
    if(re.test(sBase)){
        return RegExp.$1;
    }else{
        return null;
    }
}
