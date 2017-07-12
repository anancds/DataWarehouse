/**
 * Created by laiqin on 2016/12/28.
 */


//页面传值
function queryString(sParam){
    var sBase = window.location.href;
    var re = eval("/" + sParam + "=([^&]*)/");
    if(re.test(sBase)){
        return RegExp.$1;
    }else{
        return null;
    }
}

//日期格式化
// 对Date的扩展，将 Date 转化为指定格式的String
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
// 例子：
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18
// Date.prototype.format = function (fmt) {
//     if(fmt == undefined)fmt = 'yyyy-MM-dd';//默认格式为yyyy-MM-dd
//     var o = {
//         "M+": this.getMonth() + 1, //月份
//         "d+": this.getDate(), //日
//         "h+": this.getHours(), //小时
//         "m+": this.getMinutes(), //分
//         "s+": this.getSeconds(), //秒
//         "q+": Math.floor((this.getMonth() + 3) / 3), //季度
//         "S": this.getMilliseconds() //毫秒
//     };
//     if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
//     for (var k in o)
//         if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
//     return fmt;
// }

//监听div等空间大小变化
(function($,h,c){var a=$([]),e=$.resize=$.extend($.resize,{}),i,k="setTimeout",j="resize",d=j+"-special-event",b="delay",f="throttleWindow";e[b]=250;e[f]=true;$.event.special[j]={setup:function(){if(!e[f]&&this[k]){return false}var l=$(this);a=a.add(l);$.data(this,d,{w:l.width(),h:l.height()});if(a.length===1){g()}},teardown:function(){if(!e[f]&&this[k]){return false}var l=$(this);a=a.not(l);l.removeData(d);if(!a.length){clearTimeout(i)}},add:function(l){if(!e[f]&&this[k]){return false}var n;function m(s,o,p){var q=$(this),r=$.data(this,d);r.w=o!==c?o:q.width();r.h=p!==c?p:q.height();n.apply(this,arguments)}if($.isFunction(l)){n=l;return m}else{n=l.handler;l.handler=m}}};function g(){i=h[k](function(){a.each(function(){var n=$(this),m=n.width(),l=n.height(),o=$.data(this,d);if(m!==o.w||l!==o.h){n.trigger(j,[o.w=m,o.h=l])}});g()},e[b])}})(jQuery,this);

//获取两个时间内的所有日期
function utilGetDate(date1,date2) {
    var startTime = date1.split('-');
    var endTime = date2.split('-');
    var allDate = [];
    startTime = new Date(date1);
    endTime = new Date(date2);

    while((endTime.getTime()-startTime.getTime())>=0){
        var year = startTime.getFullYear();
        //var month = startTime.getMonth().toString().length==1?"0"+startTime.getMonth().toString():startTime.getMonth();
        //var day = startTime.getDate().toString().length==1?"0"+startTime.getDate():startTime.getDate();
        var month = startTime.getMonth().toString().length==1?startTime.getMonth().toString():startTime.getMonth();
        var day = startTime.getDate().toString().length==1?startTime.getDate():startTime.getDate();
        allDate.push(year+"-"+(parseInt(month)+1)+"-"+day);
        startTime.setDate(startTime.getDate()+1);
    }

    return allDate;
}

//加载成功弹窗
function loadSuccess() {
    $.confirm({
        title: '加载成功',
        content: '数据已成功加载...',
        autoClose: '确定|2000',
        animation: 'left',
        closeAnimation: 'right',
        backgroundDismiss: true,
        columnClass: 'col-md-4 col-md-offset-8 col-xs-4 col-xs-offset-8',
        //containerFluid: true,
        buttons: {
            确定: function () {

            }
        }
    });
}

//加载失败
function loadFailed() {
    $.confirm({
        title: '加载失败',
        content: '数据加载失败,请稍后重试...',
        autoClose: '确定|2000',
        animation: 'left',
        closeAnimation: 'right',
        backgroundDismiss: true,
        buttons: {
            确定: function () {

            }
        }
    });
}