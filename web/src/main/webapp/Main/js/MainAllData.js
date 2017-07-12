/**
 * Created by laiqin on 2017/1/13.
 */

//存放所有的散点图数据,包括话单、出行等数据，然后根据筛选条件往xTime、yTime、zTime放数据;
var all_xAxis = [];
var all_yAxis = [];
var all_zAxis = [];
var tempType = [];

//散点图
var xTime = [];     //横坐标时间
var yTime = [];     //纵坐标时间
var zTime = [];     //通话时间

//时间轴
var xAxis = [];
var yAxis = [];
var zAxis = [];

//散点图
var chartType = [];      //所有业务类型
var typeValue = [];      //业务数据
var chartData = [];   //散点图数据初始化,即option中的series

//时间轴
var chartTypeTime = [];      //所有业务类型
var typeValueTime = [];          //业务数据
var chartDataTime = [];   //时间轴数据初始化,即option中的series


var chartBig = false;    //chart窗口大小开关,false表示chart默认大小,true表示放大

var myChartOne;     //容器初始化 散点图
var myChartTwo;     //容器初始化 时间轴

//时间轴方式2加载的x轴数据
var xATime = [];

//图例颜色 形状数组
var legendColor = [/*'#36CA2F','#FFBCD7','#4771FF','lightblue','#DC5E5E'*/];
var legendSymbol = ['circle','rectangle','triangle','diamond','star'];


//存放根据输入条件的搜索结果
var searchRlt;

//记录双击人员请求数据失败次数,若等于总的请求次数,则表示无该人员的话单、出行、航班、火车、上网等信息
var ajaxTimes = 0;

window.onload = function () {
    initTree();
    xATime = utilGetDate('2016-1-17','2016-6-5');    //时间轴横轴坐标数据
    dateControl();   //时间选择器初始化
}

//日期选择初始化
function dateControl() {
    $('#first').datetimepicker({
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1,   //显示今天
        autoclose: 1,   //选择一个日期后自动关闭
        todayHighlight: true,  //高亮当前日期
        startView: 2,   //重日开始选择,3是月份,4是月份,1是小时
        minView: 2,     //最精确的时间,数字代表含义同上
        //maxView:4,    //最高能选择的时间范围
        forceParse: 0   //当选择器关闭的时候，是否强制解析输入框中的值。也就是说，当用户在输入框中输入了不正确的日期，选择器将会尽量解析输入的值，并将解析后的正确值按照给定的格式format设置到输入框中。
        //endDate:'2017-8-30'
        , pickerPosition: 'bottom-right'   //选择器位置
        , format: 'yyyy-mm-dd'
    });

    $('#second').datetimepicker({
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1,   //显示今天
        autoclose: 1,   //选择一个日期后自动关闭
        todayHighlight: true,  //高亮当前日期
        startView: 2,   //重日开始选择,3是月份,4是月份,1是小时
        minView: 2,     //最精确的时间,数字代表含义同上
        //maxView:4,    //最高能选择的时间范围
        forceParse: 0   //当选择器关闭的时候，是否强制解析输入框中的值。也就是说，当用户在输入框中输入了不正确的日期，选择器将会尽量解析输入的值，并将解析后的正确值按照给定的格式format设置到输入框中。
        //endDate:'2017-8-30'
        , pickerPosition: 'bottom-right'   //选择器位置
        , format: 'yyyy-mm-dd'
    });
}

//加载该标签页时大小调整
function formInit() {
    var a = $(window).width();
    $("#chartleft").animate({width: a - 400}, 'slow');
    $("#chartright").animate({width: 400}, 'slow');
}

//chart窗口  和   交互窗口  大小调整
function formResize() {
    if (chartBig == false) {
        $("#chartleft").animate({width: '0px'}, 'slow');
        $("#chartright").animate({width: '100%'}, 'slow');
        $("#chartright").css("margin-left","0px");
        $("#chartleft").hide();
        chartBig = true;
    } else {
        var windowWidth = $(window).width();
        $("#chartleft").animate({width: '400'}, 'slow');
        $("#chartright").animate({width: windowWidth - 400}, 'slow');
        $("#chartright").css("margin-left","400px");
        $("#chartleft").show();
        chartBig = false;
    }
}

//全量数据获取
function chartAllData() {
    var xhrurl = 'http://127.0.0.1:8100/service/info/phone-commuication?callerNum=18580382828';
    $.ajax(
        {
            type:'get',
            url : xhrurl,
            dataType : 'jsonp',
            success  : function(data) {
                var ojson = data;

                chartType = Object.keys(ojson);    //获取业务类型
                typeValue = Object.values(ojson);  //获取业务值
                xTime.splice(0, xTime.length);//    清空原有数据
                yTime.splice(0, yTime.length);
                zTime.splice(0, zTime.length);
                chartData.splice(0,chartData.length);
                for (var i = 0; i < chartType.length; i++) {//     根据获取的数据的长度push等长的[]
                    xTime.push([]);
                    yTime.push([]);
                    zTime.push([]);
                }

                for (var i = 0; i < chartType.length; i++) {//      散点图数据初始化
                    for (var j = 0; j < typeValue[i].length; j++) {
                        xTime[i][xTime[i].length] = typeValue[i][j].startTime.substring(0 , typeValue[i][j].startTime.indexOf(' '));
                        var tempHour = typeValue[i][j].startTime.substring(typeValue[i][j].startTime.indexOf(' ') + 1, typeValue[i][j].startTime.indexOf(':'));
                        var tempMin = typeValue[i][j].startTime.substring(typeValue[i][j].startTime.indexOf(':') + 1);
                        yTime[i][yTime[i].length] = parseInt(tempMin) + parseInt(tempHour*60);
                        zTime[i][zTime[i].length] = typeValue[i][j].callDuration;
                    }
                    chartData.push(
                        {
                            name: chartType[i],
                            type: 'scatter',
                            tooltip: {
                                trigger: 'item',
                                formatter: function (params) {
                                    var date = new Date(params.value[0]);
                                    return params.seriesName
                                        + ' （'
                                        + date.getFullYear() + '-'
                                        + (date.getMonth() + 1) + '-'
                                        + date.getDate() + ' '
                                        + date.getHours() + ':'
                                        + date.getMinutes()
                                        + '）<br/>'
                                        + params.value[1] + ', '
                                        + params.value[2] + ', '
                                        + params.value[3];
                                },
                                axisPointer: {
                                    type: 'shadow',
                                    lineStyle: {
                                        type: 'dashed',
                                        width: 1
                                    }
                                }
                            },
                            symbolSize: function (value) {
                                return 6;    //设置圆大小
                            },
                            itemStyle: {
                                normal: {
                                    color: legendColor[i],
                                    label : {show: false},
                                    borderColor: '#240513',
                                    borderWidth: '0.2'
                                },
                                emphasis: {
                                    color: legendColor[i],
                                    borderColor: 'black',
                                    borderWidth: '0.5'
                                }
                            },
                            symbol : 'circle',
                            data: (function () {
                                var d = [];
                                for (var k = 0; k < xTime[i].length; k++) {
                                    d.push([new Date(xTime[i][k]), parseInt(yTime[i][k]), zTime[i][k], k])
                                }
                                return d;
                            })()
                        }
                    )
                }

                chartInit();
            },
            error : function() {
                alert('数据获取失败!');
            },
            complete : function () {

            }
        }
    );
    // $.ajax({
    //     async: true,
    //     type: 'GET',
    //     url: 'data/phone.json',
    //     complete: function (data) {
    //         var ojson = data.responseJSON;//获取返回数据
    //         // xTime.splice(0, xTime.length);
    //         // yTime.splice(0, yTime.length);
    //         // zTime.splice(0, zTime.length);
    //         $("#info-name").html(ojson.name);
    //         for (var i = 0; i < ojson.data.length; i++) {
    //             if (ojson.data[i].type == 1) {
    //                 xTime[0][xTime[0].length] = ojson.data[i].date.substring(0, ojson.data[i].date.indexOf(' '));
    //                 var tempHour = ojson.data[i].date.substring(ojson.data[i].date.indexOf(' ') + 1, ojson.data[i].date.indexOf(':'));
    //                 var tempMin = ojson.data[i].date.substring(ojson.data[i].date.indexOf(':') + 1)
    //                 //yTime[0][xTime[0].length] = parseFloat((tempMin / 60).toFixed(2)) + parseInt(tempHour);
    //                 yTime[0][xTime[0].length] = parseInt(tempMin) + parseInt(tempHour*60);
    //                 zTime[0][xTime[0].length] = ojson.data[i].hours;
    //             } else if (ojson.data[i].type == 2) {
    //                 xTime[1][xTime[1].length] = ojson.data[i].date.substring(0, ojson.data[i].date.indexOf(' '));
    //                 var tempHour = ojson.data[i].date.substring(ojson.data[i].date.indexOf(' ') + 1, ojson.data[i].date.indexOf(':'));
    //                 var tempMin = ojson.data[i].date.substring(ojson.data[i].date.indexOf(':') + 1)
    //                 //yTime[1][xTime[1].length] = parseFloat((tempMin / 60).toFixed(2)) + parseInt(tempHour);
    //                 yTime[1][xTime[1].length] = parseInt(tempMin) + parseInt(tempHour*60);
    //                 zTime[1][xTime[1].length] = ojson.data[i].hours;
    //             } else if (ojson.data[i].type == 3) {
    //                 xTime[2][xTime[2].length] = ojson.data[i].date;
    //                 yTime[2][xTime[2].length] = ojson.data[i].hours;
    //                 zTime[2][xTime[2].length] = ojson.data[i].hours;
    //             }
    //         }
    //         ;
    //         chartInit();
    //     },
    //     error: function () {
    //         alert('数据获取失败!');
    //     }
    // });
}

//获取检索结果
function searchData() {
    $("#searchTab  tr:not(:first)").html("");

    var name = $("#name").val();
    var cardid = $("#cardid").val();
    var phone = $("#phone").val();
    var gender = $('#gender option:selected').val();
    if (name == '' && cardid == '' && phone == '') {
        $('#myModal').modal('show');
        $('#myModalLabel').html('温馨提示')
        $("#modalText").html('查询条件为空,请先输入查询条件.')
        return;
    }
    name = encodeURIComponent(name)
    gender == '不详' ? gender = '' : gender == '男' ? gender = 'M' : gender = 'F';   //若为不详  则为空  若为难  则为M   若为女  则为F
    var xhrurl = 'http://10.16.128.107:8100/service/people/identity-info?'+Date.parse(new Date());
    $.ajax({
        async: true,
        type: 'GET',
        dataType: "jsonp",
        url: xhrurl,
        data: {name: name, phoneNum: phone, cardid: cardid, sex: gender},
        statusCode: {
            200: function (data) {
                var ojson = data;
                searchRlt = data;
                for (var i = 0; i < ojson.length; i++) {
                    var tabHtml;
                    var imgSrc;
                    ojson[i].name == '努尔买买提-玉苏甫' ? imgSrc = 'img/1.PNG' : imgSrc = 'img/2.png';
                    tabHtml = tabHtml + '<tr ondblclick="imgdblclick(this)"><td>'+ojson[i].name+'</td><td><img src='+imgSrc+' width="108" alt=""></td></tr>'
                }
                $("#searchTab").append(tabHtml);
            },
            404: function () {
                $('#myModal').modal('show');
                $('#myModalLabel').html('温馨提示');
                $("#modalText").html('对不起,没有找到匹配结果.');
                $("#searchTab").append('<tr><td colspan="2">对不起，没有找到匹配结果。</td></tr>');
            },
            500: function () {
                $('#myModal').modal('show');
                $('#myModalLabel').html('温馨提示');
                $("#modalText").html('服务器出错,请稍后再试.');
            }
        }
    })
}

//双击检索结果行获取数据
function getData(e) {//e = {phone: strPhone, name: strName, pinyin: strPinyin}   手机号   姓名  姓名拼音
    all_xAxis.splice(0,all_xAxis.length);
    all_yAxis.splice(0,all_yAxis.length);
    all_zAxis.splice(0,all_zAxis.length);
    getPhone(e);//获取话单数据
}

//获取话单数据
function getPhone(e) {
    var xhrurl = 'http://127.0.0.1:8100/service/info/phone-commuication?'+Date.parse(new Date());
    $.ajax({
        async: true,
        type: 'GET',
        dataType: "jsonp",
        url: xhrurl,
        data: {callerNum: e.phone},
        statusCode: {
            200: function (data) {
                var ojson = data;

                tempType = Object.keys(ojson);    //获取业务类型
                var jsonValue = Object.values(ojson);  //获取业务值
                all_xAxis.splice(0, all_xAxis.length);//   数据清空
                all_yAxis.splice(0, all_yAxis.length);
                all_zAxis.splice(0, all_zAxis.length);
                for (var i = 0; i < tempType.length+1; i++) {//加1 是为了存放话单数据合集;   放入与数据等长的[],用以存放获取的数据
                    all_xAxis.push([]);
                    all_yAxis.push([]);
                    all_zAxis.push([]);
                }

                for (var i = 0; i < tempType.length; i++) {//    组装获取的数据
                    for (var j = 0; j < jsonValue[i].length; j++) {
                        all_xAxis[i][all_xAxis[i].length] = jsonValue[i][j].startTime.substring(0, jsonValue[i][j].startTime.indexOf(' '));
                        var tempHour = jsonValue[i][j].startTime.substring(jsonValue[i][j].startTime.indexOf(' ') + 1, jsonValue[i][j].startTime.indexOf(':'));
                        var tempMin = jsonValue[i][j].startTime.substring(jsonValue[i][j].startTime.indexOf(':') + 1);
                        all_yAxis[i][all_yAxis[i].length] = parseInt(tempMin) + parseInt(tempHour * 60);
                        all_zAxis[i][all_zAxis[i].length] = jsonValue[i][j].callDuration;
                    }
                }

                tempType.push('话单信息');
                var temp_xAxis_length = all_xAxis.length;
                for (var i = 0; i < all_xAxis.length - 1; i++) {//     将所有话单数据拼接在一个数组里
                    for (var j = 0; j < all_xAxis[i].length; j++) {
                        all_xAxis[temp_xAxis_length - 1].push(all_xAxis[i][j]);
                        all_yAxis[temp_xAxis_length - 1].push(all_yAxis[i][j]);
                        all_zAxis[temp_xAxis_length - 1].push(all_zAxis[i][j]);
                    }
                }

                getAccommodation(e);//住宿信息获取
            },
            404: function () {
                getAccommodation(e);
                ajaxTimes++;
            },
            500: function () {
                getAccommodation(e);
                ajaxTimes++;
            }
        }
    })
}

//住宿信息获取
function getAccommodation(e) {
    var xhrurl = 'http://127.0.0.1:8100/service/info/accommodationInfo?'+Date.parse(new Date());
    debugger
    $.ajax({
        async: true,
        type: 'GET',
        dataType: "jsonp",
        url: xhrurl,
        data: {name: e.pinyin},
        statusCode: {
            200: function (data) {
                var ojson = data;

                tempType.push('住宿信息');

                all_xAxis.push([]);//   初始化,放入[]以存放数据
                all_yAxis.push([]);
                all_zAxis.push([]);

                var length = all_xAxis.length;//    获取已有数据的长度
                for (var i = 0; i <ojson.length; i++) {//     组装数据
                    all_xAxis[length - 1][all_xAxis[length - 1].length] = ojson[i].checkInDate;
                    var tempHour = ojson[i].checkInTime.substring(0,ojson[i].checkInTime.indexOf(':'));
                    var tempMin = ojson[i].checkInTime.substring(ojson[i].checkInTime.indexOf(':') + 1);
                    all_yAxis[length - 1][all_yAxis[length - 1].length] = parseInt(tempMin) + parseInt(tempHour * 60);
                    all_zAxis[length - 1][all_zAxis[length - 1].length] = 1;
                }

                getFlight(e);//获取飞机出行数据
            },
            404: function () {
                getFlight(e);//获取飞机出行数据
                ajaxTimes++;
            },
            500: function () {
                getFlight(e);//获取飞机出行数据
                ajaxTimes++;
            }
        }
    })
}

//获取飞机出行数据
function getFlight(e) {
    var xhrurl = 'http://127.0.0.1:8100/service/info/flight-traveling?'+Date.parse(new Date());
    $.ajax({
        async: true,
        type: 'GET',
        dataType: "jsonp",
        url: xhrurl,
        data: {name_spell: e.pinyin},
        statusCode: {
            200: function (data) {
                var ojson = data;

                tempType.push('航班信息');

                all_xAxis.push([]);
                all_yAxis.push([]);
                all_zAxis.push([]);

                var length = all_xAxis.length;
                for (var i = 0; i <ojson.length; i++) {
                    all_xAxis[length - 1][all_xAxis[length - 1].length] = ojson[i].flightCheckinDate;
                    var tempHour = ojson[i].flightCheckinTime.substring(0,ojson[i].flightCheckinTime.indexOf(':'));
                    var tempMin = ojson[i].flightCheckinTime.substring(ojson[i].flightCheckinTime.indexOf(':') + 1);
                    all_yAxis[length - 1][all_yAxis[length - 1].length] = parseInt(tempMin) + parseInt(tempHour * 60);
                    all_zAxis[length - 1][all_zAxis[length - 1].length] = 1;
                }

                getTrain(e);//获取火车出行数据
            },
            404: function () {
                getTrain(e);//获取火车出行数据
                ajaxTimes++;
            },
            500: function () {
                getTrain(e);//获取火车出行数据
                ajaxTimes++;
            }
        }
    })
}

//获取火车出行数据
function getTrain(e) {
    var xhrurl = 'http://127.0.0.1:8100/service/info/train-traveling?'+Date.parse(new Date());
    $.ajax({
        async: true,
        type: 'GET',
        dataType: "jsonp",
        url: xhrurl,
        data: {name_spell: e.pinyin},
        statusCode: {
            200: function (data) {
                var ojson = data;

                tempType.push('火车信息');

                all_xAxis.push([]);
                all_yAxis.push([]);
                all_zAxis.push([]);

                var length = all_xAxis.length;
                for (var i = 0; i <ojson.length; i++) {
                    all_xAxis[length - 1][all_xAxis[length - 1].length] = ojson[i].trainCheckinDate;
                    var tempHour = ojson[i].trainCheckinTime.substring(0,ojson[i].trainCheckinTime.indexOf(':'));
                    var tempMin = ojson[i].trainCheckinTime.substring(ojson[i].trainCheckinTime.indexOf(':') + 1);
                    all_yAxis[length - 1][all_yAxis[length - 1].length] = parseInt(tempMin) + parseInt(tempHour * 60);
                    all_zAxis[length - 1][all_zAxis[length - 1].length] = 1;
                }

                getNet(e);//获取上网数据
            },
            404: function () {
                getNet(e);//获取上网数据
                ajaxTimes++;
            },
            500: function () {
                getNet(e);//获取上网数据
                ajaxTimes++;
            }
        }
    })
}

//获取上网数据
function getNet(e) {
    var xhrurl = 'http://127.0.0.1:8100/service/info/internet?'+Date.parse(new Date());
    $.ajax({
        async: true,
        type: 'GET',
        dataType: "jsonp",
        url: xhrurl,
        data: {name: e.pinyin},
        statusCode: {
            200: function (data) {
                var ojson = data;

                tempType.push('上网信息');

                all_xAxis.push([]);
                all_yAxis.push([]);
                all_zAxis.push([]);

                var length = all_xAxis.length;
                for (var i = 0; i <ojson.length; i++) {
                    all_xAxis[length - 1][all_xAxis[length - 1].length] = ojson[i].startDate;
                    var tempHour = ojson[i].startTime.substring(0,ojson[i].startTime.indexOf(':'));
                    var tempMin = ojson[i].startTime.substring(ojson[i].startTime.indexOf(':') + 1);
                    all_yAxis[length - 1][all_yAxis[length - 1].length] = parseInt(tempMin) + parseInt(tempHour * 60);
                    all_zAxis[length - 1][all_zAxis[length - 1].length] = 1;
                }

                initData();   //拼接散点图数据
            },
            404: function () {
                var chartClear = function () {
                    myChartOne.clear();
                    myChartTwo.clear();
                    $('#myModal').modal('show');
                    $('#myModalLabel').html('温馨提示');
                    $("#modalText").html('对不起,没有更多相关数据.');
                }
                ajaxTimes++;
                ajaxTimes == 5 ? chartClear() : initData();
            },
            500: function () {
                var chartClear = function () {
                    myChartOne.clear();
                    myChartTwo.clear();
                    $('#myModal').modal('show');
                    $('#myModalLabel').html('温馨提示');
                    $("#modalText").html('对不起,没有更多相关数据.');
                }
                ajaxTimes++;
                ajaxTimes == 5 ? chartClear() : initData();
            }
        }
    })
}

//拼接散点图数据
function initData() {
    debugger
    chartData.splice(0,chartData.length);//   清空原有数据,防止chart绘制重叠
    chartType.splice(0,chartType.length);//   清空原有数据,防止chart绘制重叠
    for (var i = 0; i < tempType.length; i++) {//      具体话单业务放入二级
        if (tempType[i] != '短信业务' && tempType[i] != '漫出业务' && tempType[i] != '彩信业务' && tempType[i] != 'GPRS业务' && tempType[i] != '移动语音业务') {
            chartType.push(tempType[i]);
            chartData.push(
                {
                    name: tempType[i],
                    type: 'scatter',
                    tooltip: {
                        trigger: 'item',
                        formatter: function (params) {
                            var date = new Date(params.value[0]);
                            return params.seriesName
                                + ' （'
                                + date.getFullYear() + '-'
                                + (date.getMonth() + 1) + '-'
                                + date.getDate() + ' '
                                + date.getHours() + ':'
                                + date.getMinutes()
                                + '）<br/>'
                                + params.value[1] + ', '
                                + params.value[2] + ', '
                                + params.value[3];
                        },
                        axisPointer: {
                            type: 'shadow',
                            lineStyle: {
                                type: 'dashed',
                                width: 1
                            }
                        }
                    },
                    symbolSize: function (value) {
                        return 6;    //设置圆大小
                    },
                    itemStyle: {
                        normal: {
                            color: legendColor[i],
                            label : {show: false},
                            borderColor: '#240513',
                            borderWidth: '0.2'
                        },
                        emphasis: {
                            color: legendColor[i],
                            borderColor: 'black',
                            borderWidth: '0.5'
                        }
                    },
                    symbol : 'circle',
                    data: (function () {
                        var d = [];
                        for (var k = 0; k < all_xAxis[i].length; k++) {
                            d.push([new Date(all_xAxis[i][k]), parseInt(all_yAxis[i][k]), all_zAxis[i][k], k])
                        }
                        return d;
                    })()
                }
            )
        }
    }
    chartInit();
}

var option1; //  散点图数据格式存放

//绘制散点图
function chartInit() {
    myChartOne = echarts.init(document.getElementById('chartinit'));
    divResize()    //监听窗口大小的改动,使chart能同步改变大小
    option1 = {
        title: {
            text: '全量明细数据',
            subtext: ''
        },
        tooltip: {
            trigger: 'item',
            axisPointer: {
                show: true,
                type: 'cross',
                lineStyle: {
                    type: 'dashed',
                    width: 1
                }
            }
        },
        toolbox: {
            show: true,
            feature: {
                mark: {show: true},
                dataZoom: {show: true},
                dataView: {show: true, readOnly: false},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        dataZoom: {
            show: true,
            start: 0,
            end: 100,
            handleSize: 20
        },
        legend: {
            data: chartType
        }/*,
         dataRange: {
         min: 0,
         max: 500,
         orient: 'horizontal',
         y: 30,
         x: 'center',
         //text:['高','低'],           // 文本，默认为数值文本
         color:['#FF3333','#FFAA33','#FFFF33','#99FF33','#33FFAA','#33FFFF','#5599FF'],
         splitNumber: 10
         }*/,
        grid: {
            y2: 80
        },
        xAxis: [
            {
                type: 'time',
                splitNumber: 10
            }
        ],
        yAxis: [
            {
                type: 'values',
                splitNumber: 15,
                axisLabel : {
                    formatter: function (value) {
                        var d;
                        d = parseInt(value/60);
                        return d;
                    }
                }
            }
        ],
        animation: false,
        series: chartData,/*[
            {
                name: '移动语音业务',
                type: 'scatter',
                tooltip: {
                    trigger: 'item',
                    formatter: function (params) {
                        var date = new Date(params.value[0]);
                        return params.seriesName
                            + ' （'
                            + date.getFullYear() + '-'
                            + (date.getMonth() + 1) + '-'
                            + date.getDate() + ' '
                            + date.getHours() + ':'
                            + date.getMinutes()
                            + '）<br/>'
                            + params.value[1] + ', '
                            + params.value[2]
                            + params.value[3];
                    },
                    axisPointer: {
                        type: 'shadow',
                        lineStyle: {
                            type: 'dashed',
                            width: 1
                        }
                    }
                },
                symbolSize: function (value) {
                    return 6;    //设置圆大小
                },
                data: (function () {
                    var d = [];
                    for (var i = 0; i < xTime[0].length; i++) {
                        d.push([new Date(xTime[0][i]), parseInt(yTime[0][i]), zTime[0][i], i])
                    }
                    return d;
                })()
            }, {
                name: 'GPRS业务',
                type: 'scatter',
                tooltip: {
                    trigger: 'item',
                    formatter: function (params) {
                        var date = new Date(params.value[0]);
                        return params.seriesName
                            + ' （'
                            + date.getFullYear() + '-'
                            + (date.getMonth() + 1) + '-'
                            + date.getDate() + ' '
                            + date.getHours() + ':'
                            + date.getMinutes()
                            + '）<br/>'
                            + params.value[1] + ', '
                            + params.value[2]
                            + params.value[3];
                    },
                    axisPointer: {
                        type: 'shadow',
                        lineStyle: {
                            type: 'dashed',
                            width: 1
                        }
                    }
                },
                symbolSize: function (value) {
                    return 4;    //设置圆大小
                },
                data: (function () {
                    var d = [];
                    for (var i = 0; i < xTime[1].length; i++) {
                        d.push([new Date(xTime[1][i]), parseInt(yTime[1][i]), zTime[1][i], i])
                    }
                    return d;
                })()
            }
        ]*/
    };

    require(
        [
            'echarts'
        ],
        function (ec) {
            var ecConfig = require('echarts/config');

            // 过渡---------------------
            myChartOne.showLoading({
                text: '正在努力的读取数据中...'
            });

            function focus(param) {
                var data = param.data;
                var nodes = option1.series[0].nodes;
                if ( data.source !== undefined && data.target !== undefined ) { //点击的是边
                    var sourceNode = nodes.filter(function (n) {
                        return n.name == data.source
                    })[0];
                    var targetNode = nodes.filter(function (n) {
                        return n.name == data.target
                    })[0];
                    console.log("选中了边 " + sourceNode.name + ' -> ' + targetNode.name + ' (' + data.weight + ')');
                } else { // 点击的是点
                    var e = event || window.event;
                    showMenu(e.pageX, e.pageY);                // 左键点击弹窗
                    //sendData()//添加原点点击时间
                    console.log("选中了" + data.name + '(' + data.value + ')');
                }
            }

            myChartOne.on(ecConfig.EVENT.CLICK, focus)

            myChartOne.on(ecConfig.EVENT.DATA_ZOOM, function (e) {
                if (e.zoom.start != 0 && e.zoom.end != 100) {
                    showData()   //弹出窗口
                }
            });

            myChartOne.on(ecConfig.EVENT.HOVER, function (e) {

            });

            //双击事件
            myChartOne.on(ecConfig.EVENT.DBLCLICK, function () {
                //getParentTabs(1);
            });

            // 为echarts对象加载数据
            myChartOne.setOption(option1,true);

            //初始化chart根据窗口大小来同步改变大小
            window.onresize = myChartOne.resize;

            myChartOne.hideLoading({
                text: '正在努力的读取数据中...'
            });
            chartTimeData1();
        }
    );
}

//获取数据方式1
function chartTimeData() {
    var xhrurl = 'http://127.0.0.1:8100/service/info/phone-communication-hour-count?callerNum=18580382828';
    $.ajax(
        {
            type:'get',
            url : xhrurl,
            dataType : 'jsonp',
            success  : function(data) {
                var ojson = data;
                var tempArray = Object.keys(ojson);
                //chartTypeTime = Object.keys(ojson);
                typeValueTime = Object.values(ojson);

                for (var i = 0; i < tempArray.length; i++) {
                    chartTypeTime.push([]);
                    xAxis.push([]);
                    yAxis.push([]);
                    zAxis.push([]);

                    switch (tempArray[i]){
                        case '0004' :
                            chartTypeTime[i] = ('GPRS业务');
                            for (var j = 0; j < typeValueTime[i].length; j++) {
                                xAxis[i][xAxis[i].length] = typeValueTime[i][j].day;
                                yAxis[i][yAxis[i].length] = typeValueTime[i][j].connCount;
                                zAxis[i][zAxis[i].length] = typeValueTime[i][j].hour;
                            }
                            break;
                        case '0003' :
                            chartTypeTime[i] = ('漫出业务');
                            for (var j = 0; j < typeValueTime[i].length; j++) {
                                xAxis[i][xAxis[i].length] = typeValueTime[i][j].day;
                                yAxis[i][yAxis[i].length] = typeValueTime[i][j].connCount;
                                zAxis[i][zAxis[i].length] = typeValueTime[i][j].hour;
                            }
                            break;
                        case '0003.0002' :
                            break;
                        case '0003.0001' :
                            break;
                        case '0002' :
                            chartTypeTime[i] = ('短信业务');
                            for (var j = 0; j < typeValueTime[i].length; j++) {
                                xAxis[i][xAxis[i].length] = typeValueTime[i][j].day;
                                yAxis[i][yAxis[i].length] = typeValueTime[i][j].connCount;
                                zAxis[i][zAxis[i].length] = typeValueTime[i][j].hour;
                            }
                            break;
                        case '0001.0002' :
                            break;
                        case '0001.0001' :
                            break;
                        case '0001' :
                            chartTypeTime[i] = ('移动语音业务');
                            for (var j = 0; j < typeValueTime[i].length; j++) {
                                xAxis[i][xAxis[i].length] = typeValueTime[i][j].date;
                                yAxis[i][yAxis[i].length] = typeValueTime[i][j].connCount;
                                zAxis[i][zAxis[i].length] = typeValueTime[i][j].hour;
                            }
                            break;
                    }
                }
                for (var i = 0; i < chartTypeTime.length; i++) {
                    chartDataTime.push(
                        {
                            name: chartTypeTime[i],
                            type: 'bar',
                            tooltip: {
                                trigger: 'item',
                                formatter: function (params) {
                                    var date = new Date(params.value[0]);
                                    return params.seriesName
                                        + '<br/>'
                                        + ' （'
                                        + date.getFullYear() + '-'
                                        + (date.getMonth() + 1) + '-'
                                        + date.getDate() + ' '
                                        + date.getHours() + ':'
                                        + date.getMinutes()
                                        + '）<br/>'
                                        + params.value[1] + ', '
                                        + params.value[2];
                                },
                                axisPointer: {
                                    type: 'shadow',
                                    lineStyle: {
                                        type: 'dashed',
                                        width: 1
                                    }
                                }
                            },
                            data: function () {
                                var d = [];
                                for (var k = 0; k < xAxis[i].length; k++) {
                                    d.push([new Date(xAxis[i][k]), parseInt(yAxis[i][k]), zAxis[i][k], k])
                                }
                                return d;
                            }()
                        }
                    )
                }

                chartTimeInit();
            },
            error : function() {
                alert('数据获取失败!');
            },
            complete : function () {

            }
        }
    );
}

//绘制下方时间轴方式1
function chartTimeInit() {
    myChartTwo = echarts.init(document.getElementById('chartinittime'));
    var option2 = {
        tooltip: {
            trigger: 'item'
        },
        legend: {
            data: chartType/*['移动语音业务', 'GPRS业务']*/
        },
        toolbox: {
            show: true,
            feature: {
                mark: {show: true},
                dataView: {readOnly: false},
                magicType: {show: true, type: ['stack', 'tiled']},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        animation: false,
        calculable: true,
        dataZoom: {
            show: true,
            realtime: true,
            start: 0,
            end: 100,
            handleSize: 20
        },
        xAxis: [
            {
                type: 'time',
                splitNumber: 10
            }
        ],
        yAxis: [
            {
                type: 'value'
            }
        ],
        series:chartDataTime/* [
            {
                name: '移动语音业务',
                type: 'bar',
                tooltip: {
                    trigger: 'item',
                    formatter: function (params) {
                        var date = new Date(params.value[0]);
                        return params.seriesName
                            + '<br/>'
                            + ' （'
                            + date.getFullYear() + '-'
                            + (date.getMonth() + 1) + '-'
                            + date.getDate() + ' '
                            + date.getHours() + ':'
                            + date.getMinutes()
                            + '）<br/>'
                            + params.value[1] + ', '
                            + params.value[2];
                    },
                    axisPointer: {
                        type: 'shadow',
                        lineStyle: {
                            type: 'dashed',
                            width: 1
                        }
                    }
                },
                data: function () {
                    var d = [];
                    for (var i = 0; i < xTime[0].length; i++) {
                        d.push([new Date(xTime[0][i]), parseInt(yTime[0][i]), zTime[0][i]])
                    }
                    return d;
                }()
            },
            {
                name: 'GPRS业务',
                type: 'bar',
                tooltip: {
                    trigger: 'item',
                    formatter: function (params) {
                        var date = new Date(params.value[0]);
                        return params.seriesName
                            + '<br/>'
                            + ' （'
                            + date.getFullYear() + '-'
                            + (date.getMonth() + 1) + '-'
                            + date.getDate() + ' '
                            + date.getHours() + ':'
                            + date.getMinutes()
                            + '）<br/>'
                            + params.value[1] + ', '
                            + params.value[2];
                    },
                    axisPointer: {
                        type: 'shadow',
                        lineStyle: {
                            type: 'dashed',
                            width: 1
                        }
                    }
                },
                data: function () {
                    var d = [];
                    for (var i = 0; i < xTime[1].length; i++) {
                        d.push([new Date(xTime[1][i]), parseInt(yTime[1][i]), zTime[1][i]])
                    }
                    return d;
                }()
            }
        ]*/
    };

    require(
        [
            'echarts'
        ],
        function (ec) {
            var ecConfig = require('echarts/config');

            // 过渡---------------------
            myChartTwo.showLoading({
                text: '正在努力的读取数据中...'
            });

            function focus(param) {
                var data = param.data;
                var nodes = option2.series[0].nodes;
                if (
                    data.source !== undefined
                    && data.target !== undefined
                ) { //点击的是边
                    var sourceNode = nodes.filter(function (n) {
                        return n.name == data.source
                    })[0];
                    var targetNode = nodes.filter(function (n) {
                        return n.name == data.target
                    })[0];
                    console.log("选中了边 " + sourceNode.name + ' -> ' + targetNode.name + ' (' + data.weight + ')');
                } else { // 点击的是点
                    sendData()//添加原点点击时间
                    console.log("选中了" + data.name + '(' + data.value + ')');
                }
            }

            // var _ZR = myChartTwo.getZrender();
            // var CircleShape = require('zrender/shape/Circle');
            // var TextShape = require('zrender/shape/Text');
            // _ZR.addShape(new CircleShape({
            //     style: {
            //         x: 100,
            //         y: 100,
            //         r: 50,
            //         brushType: 'both',
            //         color: 'rgba(220, 20, 60, 0.8)',          // rgba supported
            //         lineWidth: 5,
            //         text: 'circle',
            //         textPosition: 'inside'
            //     },
            //     hoverable: true,   // default true
            //     draggable: true,   // default false
            //     clickable: true,   // default false
            // }))

            myChartTwo.on(ecConfig.EVENT.CLICK, focus);

            // 为echarts对象加载数据
            myChartTwo.setOption(option2 , true);

            //初始化chart根据窗口大小来同步改变大小
            window.onresize = myChartTwo.resize;

            myChartTwo.hideLoading({
                text: '正在努力的读取数据中...'
            });

            myChartOne.connect([myChartTwo]);
            myChartTwo.connect([myChartOne]);

            setTimeout(function () {
                window.onresize = function () {
                    myChartOne.resize();
                    myChartTwo.resize();
                }
            }, 200);
        }
    );
}

//获取数据方式2
function chartTimeData1() {
    var xhrurl = 'http://127.0.0.1:8100/service/info/phone-communication-hour-count';
    $.ajax(
        {
            type:'get',
            url : xhrurl,
            dataType : 'jsonp',
            success  : function(data) {
                var ojson = data;
                var tempArray; // 存放所有key值,即0001、0002、0003等

                typeValueTime.splice(0 , typeValueTime.length);
                yAxis.splice(0 , yAxis.length);

                tempArray = Object.keys(ojson);
                typeValueTime = Object.values(ojson);  //存放所有object中key对应的value值

                for (var i = 0; i < tempArray.length + 1; i++) {    //根据key长度加1初始化 ,  加1是为了存放所有话单数据
                    yAxis.push([]);
                    for (var j = 0; j < xATime.length; j++) {     //再根据日期长度初始化
                        yAxis[i].push(0)
                    }
                }

                //单独初始化话单信息
                chartTypeTime[tempArray.length] = '话单信息'

                for (var i = 0; i < tempArray.length; i++) {
                    switch (tempArray[i]){
                        case '0004' :
                            chartTypeTime[i] = ('GPRS业务');
                            for (var j = 0; j < typeValueTime[i].length; j++) {
                                yAxis[i][xATime.indexOf(typeValueTime[i][j].day)] = yAxis[i][xATime.indexOf(typeValueTime[i][j].day)] + parseInt(typeValueTime[i][j].connCount);
                                yAxis[tempArray.length][xATime.indexOf(typeValueTime[i][j].day)] = yAxis[tempArray.length][xATime.indexOf(typeValueTime[i][j].day)] + parseInt(typeValueTime[i][j].connCount);
                            }
                            break;
                        case '0003' :
                            chartTypeTime[i] = ('漫出业务');
                            for (var j = 0; j < typeValueTime[i].length; j++) {
                                yAxis[i][xATime.indexOf(typeValueTime[i][j].day)] = yAxis[i][xATime.indexOf(typeValueTime[i][j].day)] + parseInt(typeValueTime[i][j].connCount);
                                yAxis[tempArray.length][xATime.indexOf(typeValueTime[i][j].day)] = yAxis[tempArray.length][xATime.indexOf(typeValueTime[i][j].day)] + parseInt(typeValueTime[i][j].connCount);
                            }
                            break;
                        case '0003.0002' :
                            break;
                        case '0003.0001' :
                            break;
                        case '0002' :
                            chartTypeTime[i] = ('短信业务');
                            for (var j = 0; j < typeValueTime[i].length; j++) {
                                yAxis[i][xATime.indexOf(typeValueTime[i][j].day)] = yAxis[i][xATime.indexOf(typeValueTime[i][j].day)] + parseInt(typeValueTime[i][j].connCount);
                                yAxis[tempArray.length][xATime.indexOf(typeValueTime[i][j].day)] = yAxis[tempArray.length][xATime.indexOf(typeValueTime[i][j].day)] + parseInt(typeValueTime[i][j].connCount);
                            }
                            break;
                        case '0001.0002' :
                            break;
                        case '0001.0001' :
                            break;
                        case '0001' :
                            chartTypeTime[i] = ('移动语音业务');
                            for (var j = 0; j < typeValueTime[i].length; j++) {
                                yAxis[i][xATime.indexOf(typeValueTime[i][j].day)] = yAxis[i][xATime.indexOf(typeValueTime[i][j].day)] + parseInt(typeValueTime[i][j].connCount);
                                yAxis[tempArray.length][xATime.indexOf(typeValueTime[i][j].day)] = yAxis[tempArray.length][xATime.indexOf(typeValueTime[i][j].day)] + parseInt(typeValueTime[i][j].connCount);
                            }
                            break;
                        case '0005' :     //飞机
                            chartTypeTime[i] = ('航班信息');
                            for (var j = 0; j < typeValueTime[i].length; j++) {
                                yAxis[i][xATime.indexOf(typeValueTime[i][j].day)] = yAxis[i][xATime.indexOf(typeValueTime[i][j].day)] + parseInt(typeValueTime[i][j].connCount);
                            }
                            break;
                        case '0006' :     //火车
                            chartTypeTime[i] = ('火车信息');
                            for (var j = 0; j < typeValueTime[i].length; j++) {
                                yAxis[i][xATime.indexOf(typeValueTime[i][j].day)] = yAxis[i][xATime.indexOf(typeValueTime[i][j].day)] + parseInt(typeValueTime[i][j].connCount);
                            }
                            break;
                    }
                }

                //先清空之前的数据,再放数据
                chartDataTime.splice(0 , chartDataTime.length);

                for (var i = 0; i < chartTypeTime.length; i++) {
                    if (chartTypeTime[i] == '航班信息' || chartTypeTime[i] == '火车信息' || chartTypeTime[i] == '话单信息') {
                        chartDataTime.push(
                            {
                                name:chartTypeTime[i],
                                type:'bar',
                                data:yAxis[i]
                            }
                        )
                    }
                }

                chartTimeInit1();
            },
            error : function() {
                alert('数据获取失败!');
            },
            complete : function () {

            }
        }
    );
}

var option2; //  散点图数据格式存放

//绘制下方时间轴方式2
function chartTimeInit1() {
    myChartTwo = echarts.init(document.getElementById('chartinittime'));
    option2 = {
        title: {
            text: '全量明细数据',
            subtext: 'dataZoom支持'
        },
        tooltip : {
            trigger: 'axis'
        },
        legend: {
            data:chartType
        },
        toolbox: {
            show : true,
            feature : {
                mark : {show: true},
                dataView : {readOnly:false},
                magicType : {show: true, type: ['line', 'bar']},
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        calculable : true,
        dataZoom : {
            show : true,
            realtime : true,
            start : 0,
            end : 100,
            handleSize: 20
        },
        xAxis : [
            {
                type : 'category',
                boundaryGap : true,
                data : xATime
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        series : chartDataTime
    };

    require(
        [
            'echarts'
        ],
        function (ec) {
            var ecConfig = require('echarts/config');

            // 过渡---------------------
            myChartTwo.showLoading({
                text: '正在努力的读取数据中...'
            });

            function focus(param) {
                var data = param.data;
                var nodes = option2.series[0].nodes;
                if (
                    data.source !== undefined
                    && data.target !== undefined
                ) { //点击的是边
                    var sourceNode = nodes.filter(function (n) {
                        return n.name == data.source
                    })[0];
                    var targetNode = nodes.filter(function (n) {
                        return n.name == data.target
                    })[0];
                    console.log("选中了边 " + sourceNode.name + ' -> ' + targetNode.name + ' (' + data.weight + ')');
                } else { // 点击的是点
                    sendData()//添加原点点击时间
                    console.log("选中了" + data.name + '(' + data.value + ')');
                }
            }

            myChartTwo.on(ecConfig.EVENT.CLICK, focus);

            // 为echarts对象加载数据
            myChartTwo.setOption(option2 , true);

            window.onresize = myChartTwo.resize;

            myChartTwo.hideLoading({
                text: '正在努力的读取数据中...'
            });

            //加载成功
            //loadSuccess();

            myChartOne.connect([myChartTwo]);
            myChartTwo.connect([myChartOne]);

            setTimeout(function () {
                window.onresize = function () {
                    myChartOne.resize();
                    myChartTwo.resize();
                }
            }, 200);
        }
    );
}

//双击搜索结果后,显示画像展示,并显示对应人的信息
function showInfo(e) {
    changeTab(2);//展开画像展示tab页
    e.name == '努尔买买提-玉苏甫' ? $("#tabimg").attr('src','img/1.PNG') : $("#tabimg").attr('src','img/2.png');
    for (var i = 0; i < searchRlt.length; i++) {//目前是按名字去匹配
        if (searchRlt[i].name == e.name) {
            $("#tabname").html(searchRlt[i].name);
            $("#tabpinyin").html(searchRlt[i].nameSpell);
            $("#tabcardid").html(searchRlt[i].cardId);
            $("#tabphone").html(searchRlt[i].phoneNum);
            $("#tabgender").html(searchRlt[i].sex == 'F' ? '男' : '女');
            $("#tabnation").html(searchRlt[i].ethnicity);
            $("#tabbirth").html(searchRlt[i].birDay);
            $("#tabmarry").html(searchRlt[i].marriage);
            $("#tabedu").html(searchRlt[i].education);
            $("#tabjob").html(searchRlt[i].job);
            $("#tabarea").html(searchRlt[i].adress);
            $("#tabstate").html(searchRlt[i].state);
        }
    }
}

//隐藏左键菜单
function menuHide() {
    var menu = document.getElementById("myMenu");
    menu.classList.remove('show-menu');
}

//散点图点击显示菜单
function showMenu(x , y) {
    var menu = document.getElementById("myMenu");
    menu.style.left = x + 'px';
    menu.style.top = y + 'px';
    menu.classList.add('show-menu');
}

//chart动态改变chart宽度
function divResize() {
    $("#chartleft").resize(function () {
        myChartOne.resize;
        myChartTwo.resize;
    })
}

//切换到时空关系窗口
function getParentTabs(e) {
    window.parent.changeTabs(e , 1)    //1表示全量在时空关系中展开
    menuHide();
}

//点击tab标签页显示对应的form表单
function changeTab(tabnum) {
    switch (tabnum) {
        case 1 :
            $("#tab-one").slideDown(500, function () {});
            $("#tab-two").slideUp(1000, function () {});
            $("#tab-three").slideUp(1000, function () {});
            $("#tab-four").slideUp(1000, function () {});
            break;
        case 2 :
            $('#myTab li:eq(1) a').tab('show');
            $("#tab-one").slideUp(500, function () {});
            $("#tab-two").slideDown(1000, function () {});
            $("#tab-three").slideUp(1000, function () {});
            $("#tab-four").slideUp(1000, function () {});
            break;
        case 3 :
            $("#tab-one").slideUp(500, function () {});
            $("#tab-two").slideUp(1000, function () {});
            $("#tab-three").slideDown(1000, function () {});
            $("#tab-four").slideUp(1000, function () {});
            break;
        case 4 :
            $('#myTab li:eq(3) a').tab('show');
            $("#tab-one").slideUp(500, function () {});
            $("#tab-two").slideUp(1000, function () {});
            $("#tab-three").slideUp(1000, function () {});
            $("#tab-four").slideDown(1000, function () {});
            break;
    }
    menuHide();
}

//高级查询重新组装数据,并绘制chart
function seniorData(e) {
    var selectVal = $('#seniorselect').val();
    //先清空之前的数据,再放数据
    chartDataTime.splice(0 , chartDataTime.length);
    chartData.splice(0,chartData.length);
    chartType.splice(0,chartType.length);

    if (selectVal == 1) {
        for (var i = 0; i < tempType.length; i++) {
            if (tempType[i] != '短信业务' && tempType[i] != '漫出业务' && tempType[i] != '彩信业务' && tempType[i] != 'GPRS业务' && tempType[i] != '移动语音业务') {
                chartType.push(tempType[i]);
                chartData.push(
                    {
                        name: tempType[i],
                        type: 'scatter',
                        tooltip: {
                            trigger: 'item',
                            formatter: function (params) {
                                var date = new Date(params.value[0]);
                                return params.seriesName
                                    + ' （'
                                    + date.getFullYear() + '-'
                                    + (date.getMonth() + 1) + '-'
                                    + date.getDate() + ' '
                                    + date.getHours() + ':'
                                    + date.getMinutes()
                                    + '）<br/>'
                                    + params.value[1] + ', '
                                    + params.value[2] + ', '
                                    + params.value[3];
                            },
                            axisPointer: {
                                type: 'shadow',
                                lineStyle: {
                                    type: 'dashed',
                                    width: 1
                                }
                            }
                        },
                        symbolSize: function (value) {
                            return 6;    //设置圆大小
                        },
                        itemStyle: {
                            normal: {
                                color: legendColor[i],
                                label : {show: false},
                                borderColor: '#240513',
                                borderWidth: '0.2'
                            },
                            emphasis: {
                                color: legendColor[i],
                                borderColor: 'black',
                                borderWidth: '0.5'
                            }
                        },
                        symbol : 'circle',
                        data: (function () {
                            var d = [];
                            for (var k = 0; k < all_xAxis[i].length; k++) {
                                d.push([new Date(all_xAxis[i][k]), parseInt(all_yAxis[i][k]), all_zAxis[i][k], k])
                            }
                            return d;
                        })()
                    }
                )

            }
        }

        for (var j = 0; j < chartTypeTime.length; j++) {
            if (chartTypeTime[j] == '航班信息' || chartTypeTime[j] == '火车信息' || chartTypeTime[j] == '话单信息') {
                chartDataTime.push(
                    {
                        name:chartTypeTime[j],
                        type:'bar',
                        data:yAxis[j]
                    }
                )
            }
        }
    }else if (selectVal == 2) {
        for (var i = 0; i < tempType.length; i++) {
            if (tempType[i] == '短信业务' || tempType[i] == '漫出业务' || tempType[i] == '彩信业务' || tempType[i] == 'GPRS业务' || tempType[i] == '移动语音业务') {
                chartType.push(tempType[i]);
                chartData.push(
                    {
                        name: tempType[i],
                        type: 'scatter',
                        tooltip: {
                            trigger: 'item',
                            formatter: function (params) {
                                var date = new Date(params.value[0]);
                                return params.seriesName
                                    + ' （'
                                    + date.getFullYear() + '-'
                                    + (date.getMonth() + 1) + '-'
                                    + date.getDate() + ' '
                                    + date.getHours() + ':'
                                    + date.getMinutes()
                                    + '）<br/>'
                                    + params.value[1] + ', '
                                    + params.value[2] + ', '
                                    + params.value[3];
                            },
                            axisPointer: {
                                type: 'shadow',
                                lineStyle: {
                                    type: 'dashed',
                                    width: 1
                                }
                            }
                        },
                        symbolSize: function (value) {
                            return 6;    //设置圆大小
                        },
                        itemStyle: {
                            normal: {
                                color: legendColor[i],
                                label : {show: false},
                                borderColor: '#240513',
                                borderWidth: '0.2'
                            },
                            emphasis: {
                                color: legendColor[i],
                                borderColor: 'black',
                                borderWidth: '0.5'
                            }
                        },
                        symbol : 'circle',
                        data: (function () {
                            var d = [];
                            for (var k = 0; k < all_xAxis[i].length; k++) {
                                d.push([new Date(all_xAxis[i][k]), parseInt(all_yAxis[i][k]), all_zAxis[i][k], k])
                            }
                            return d;
                        })()
                    }
                )
            }
        }
        for (var j = 0; j < chartTypeTime.length; j++) {
            if (chartTypeTime[j] == '短信业务' || chartTypeTime[j] == '漫出业务' || chartTypeTime[j] == '彩信业务' || chartTypeTime[j] == 'GPRS业务' || chartTypeTime[j] == '移动语音业务') {
                chartDataTime.push(
                    {
                        name:chartTypeTime[j],
                        type:'bar',
                        data:yAxis[j]
                    }
                )
            }
        }
    }
    // 为echarts对象加载数据
    myChartOne.setOption(option1,true);
    myChartTwo.setOption(option2,true);
}

//搜索结果行双击事件
function imgdblclick(e) {
    if(myChartOne != undefined){
        myChartOne.clear();
        myChartTwo.clear();
    }
    var strPhone;//手机号
    var strName;//姓名
    var strPinyin;//姓名拼音
    for (var i = 0; i < searchRlt.length; i++) {//目前是按名字去匹配
        if (searchRlt[i].name == $.trim(e.textContent)) {
            strPhone = searchRlt[i].phoneNum;
            strName = searchRlt[i].name;
            strPinyin = searchRlt[i].nameSpell;
        }
    }
    var strObj = {phone: strPhone, name: strName, pinyin: strPinyin};
    getData(strObj);
    showInfo(strObj);//搜索结果行双击事件
}

//初始化高级检索的树
function initTree() {
    var defaultData = [
        {
            text: '类目筛选',
            href: '#parent1',
            tags: ['4'],
            nodes: [
                {
                    text: '通联',
                    href: '#child1',
                    tags: ['2'],
                    nodes: [
                        {
                            text: '漫出',
                            href: '#grandchild1',
                            tags: ['0']
                        },
                        {
                            text: '短信',
                            href: '#grandchild2',
                            tags: ['0']
                        },
                        {
                            text: '语音',
                            href: '#grandchild2',
                            tags: ['0']
                        },
                        {
                            text: '彩信',
                            href: '#grandchild2',
                            tags: ['0']
                        },
                        {
                            text: 'GPRS',
                            href: '#grandchild2',
                            tags: ['0']
                        }
                    ]
                },
                {
                    text: '出行',
                    href: '#child2',
                    tags: ['0'],
                    nodes: [
                        {
                            text: '飞机',
                            href: '#grandchild2',
                            tags: ['0']
                        },
                        {
                            text: '火车',
                            href: '#grandchild2',
                            tags: ['0']
                        }
                    ]
                },
                {
                    text: '住宿',
                    href: '#grandchild2',
                    tags: ['0']
                },
                {
                    text: '上网',
                    href: '#grandchild2',
                    tags: ['0']
                }
            ]
        }
    ];

    var arrayChild = [];
    var $checkableTree = $('#treeview-checkable').treeview({
        data: defaultData,
        showIcon: false,
        showCheckbox: true,
        backColor: "#f5f5f5",
        onhoverColor: "#dddddd",
        //multiSelect: true,
        onNodeChecked: function(event, node) {
            if (arrayChild.length == 0) {
                switch (node.text) {
                    case '类目筛选' :
                        $checkableTree.treeview('checkAll');
                        break;
                    case '通联' :
                        arrayChild = ['漫出','短信','语音','彩信','GPRS'];
                        break;
                    case '出行' :
                        arrayChild = ['飞机','火车'];
                        break;
                }
            }
            chechNodes();
            $('#checkable-output').prepend('<p>' + node.text + ' was checked</p>');
        },
        onNodeUnchecked: function (event, node) {
            if (arrayChild.length == 0) {
                switch (node.text) {
                    case '类目筛选' :
                        $checkableTree.treeview('uncheckAll');
                        break;
                    case '通联' :
                        arrayChild = ['漫出','短信','语音','彩信','GPRS'];
                        break;
                    case '出行' :
                        arrayChild = ['飞机','火车'];
                        break;
                }
            }
            unchechNodes();
            $('#checkable-output').prepend('<p>' + node.text + ' was unchecked</p>');
        },
        onNodeSelected: function(event, node) {
            if (arrayChild.length == 0) {
                switch (node.text) {
                    case '类目筛选' :
                        $checkableTree.treeview('checkAll');
                        break;
                    case '通联' :
                        arrayChild = ['漫出','短信','语音','彩信','GPRS'];
                        break;
                    case '出行' :
                        arrayChild = ['飞机','火车'];
                        break;
                    default :
                        arrayChild.push(node.text);
                        break;
                }
            }
            chechNodes()
            $('#selectable-output').prepend('<p>' + node.text + ' was selected</p>');
        },
        onNodeUnselected: function (event, node) {
            if (arrayChild.length == 0) {
                switch (node.text) {
                    case '类目筛选' :
                        $checkableTree.treeview('uncheckAll');
                        break;
                    case '通联' :
                        arrayChild = ['漫出','短信','语音','彩信','GPRS'];
                        break;
                    case '出行' :
                        arrayChild = ['飞机','火车'];
                        break;
                    default :
                        arrayChild.push(node.text);
                        break;
                }
            }
            unchechNodes()
            $('#selectable-output').prepend('<p>' + node.text + ' was unselected</p>');
        }
    });

    var chechNodes = function () {
        for (var i = 0; i < arrayChild.length; i++) {
            var checkableNodes = $checkableTree.treeview('search', [ arrayChild[i], { ignoreCase: false, exactMatch: false } ]);
            $checkableTree.treeview('checkNode', [ checkableNodes, { silent: $('#chk-check-silent').is(':checked') }]);
            //$checkableTree.treeview('selectNode', [ $checkableTree, { silent: $('#chk-select-silent').is(':checked') }]);
        }
        arrayChild.splice(0,arrayChild.length);
    };

    var unchechNodes = function () {
        for (var i = 0; i < arrayChild.length; i++) {
            var checkableNodes = $checkableTree.treeview('search', [ arrayChild[i], { ignoreCase: false, exactMatch: false } ]);
            $checkableTree.treeview('uncheckNode', [ checkableNodes, { silent: $('#chk-check-silent').is(':checked') }]);
            //$checkableTree.treeview('unselectNode', [ $checkableTree, { silent: $('#chk-select-silent').is(':checked') }]);
        }
        arrayChild.splice(0,arrayChild.length);
    };
}
