/**
 * Created by laiqin on 2017/1/13.
 */
//散点图
var xTime = [];     //横坐标时间
var yTime = [];     //纵坐标时间
var zTime = [];     //通话时间
//时间轴
var xAxis = [];
var yAxis = [];
var zAxis = [];


//散点图
var chartType;      //所有业务类型
var typeValue;      //业务数据
var chartData = [];   //散点图数据初始化,即option中的series
//时间轴
var chartTypeTime = [];      //所有业务类型
var typeValueTime = [];          //业务数据
var chartDataTime = [];   //时间轴数据初始化,即option中的series


var chartBig = false;    //chart窗口大小开关

var myChartOne;     //容器初始化
var myChartTwo;

//时间轴方式2加载的x轴数据
var xATime = [];

//图例颜色 形状数组
var legendColor = ['#36CA2F','#F2994E','#4771FF','#990000','#7A57FF'];
var legendSymbol = ['circle','rectangle','triangle','diamond','star'];

window.onload = function () {
    xATime = utilGetDate('2016-1-17','2016-6-5');
    dateControl();
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

function formResize() {
    if (chartBig == false) {
        $("#chartleft").animate({width: '100%'}, 'slow');
        $("#chartright").animate({width: '0'}, 'slow');
        $("#chartright").hide();
        chartBig = true;
    } else {
        $("#chartleft").animate({width: '70%'}, 'slow');
        $("#chartright").animate({width: '30%'}, 'slow');
        $("#chartright").show();
        chartBig = false;
    }
}

//全量数据获取
function chartAllData() {
    var xhrurl = 'http://10.16.128.107:8100/service/info/phone-commuication?callerNum=18580382828';
    $.ajax(
        {
            type:'get',
            url : xhrurl,
            dataType : 'jsonp',
            success  : function(data) {
                var ojson = data;

                chartType = Object.keys(ojson);    //获取业务类型
                typeValue = Object.values(ojson);  //获取业务值
                xTime.splice(0, xTime.length);
                yTime.splice(0, yTime.length);
                zTime.splice(0, zTime.length);
                chartData.splice(0,chartData.length);
                for (var i = 0; i < chartType.length; i++) {
                    xTime.push([]);
                    yTime.push([]);
                    zTime.push([]);
                }

                for (var i = 0; i < chartType.length; i++) {
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

//绘制散点图
function chartInit() {
    myChartOne = echarts.init(document.getElementById('chartinit'));
    divResize()
    var option1 = {
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
                    showMenu();                // 左键点击弹窗
                    //sendData()//添加原点点击时间
                    console.log("选中了" + data.name + '(' + data.value + ')');
                }
            }

            myChartOne.on(ecConfig.EVENT.CLICK, focus)

            myChartOne.on(ecConfig.EVENT.FORCE_LAYOUT_END, function () {
                console.log(myChartOne.chart.force.getPosition());
            });
            myChartOne.on(ecConfig.EVENT.DATA_ZOOM, function (e) {
                if (e.zoom.start != 0 && e.zoom.end != 100) {
                    //showData()   弹出窗口
                }
            });

            myChartOne.on(ecConfig.EVENT.HOVER, function (e) {

            });

            //双击事件
            myChartOne.on(ecConfig.EVENT.DBLCLICK, function () {
                getParentTabs(1);
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
    var xhrurl = 'http://10.16.128.107:8100/service/info/phone-communication-hour-count?callerNum=18580382828';
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
            myChartTwo.setOption(option2);

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
    var xhrurl = 'http://10.16.128.107:8100/service/info/phone-communication-hour-count?callerNum=18580382828';
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
                    yAxis.push([]);
                    for (var j = 0; j < xATime.length; j++) {
                        yAxis[i].push(0)
                    }
                }

                for (var i = 0; i < tempArray.length; i++) {
                    switch (tempArray[i]){
                        case '0004' :
                            chartTypeTime[i] = ('GPRS业务');
                            for (var j = 0; j < typeValueTime[i].length; j++) {
                                yAxis[i][xATime.indexOf(typeValueTime[i][j].day)] = yAxis[i][xATime.indexOf(typeValueTime[i][j].day)] + parseInt(typeValueTime[i][j].connCount);
                            }
                            break;
                        case '0003' :
                            chartTypeTime[i] = ('漫出业务');
                            for (var j = 0; j < typeValueTime[i].length; j++) {
                                yAxis[i][xATime.indexOf(typeValueTime[i][j].day)] = yAxis[i][xATime.indexOf(typeValueTime[i][j].day)] + parseInt(typeValueTime[i][j].connCount);
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
                            }
                            break;
                    }
                }

                chartDataTime.splice(0 , chartDataTime.length);

                for (var i = 0; i < chartTypeTime.length; i++) {
                    chartDataTime.push(
                        {
                            name:chartTypeTime[i],
                            type:'bar',
                            data:yAxis[i]
                        }
                    )
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

//绘制下方时间轴方式2
function chartTimeInit1() {
    myChartTwo = echarts.init(document.getElementById('chartinittime'));
    var option2 = {
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
            myChartTwo.setOption(option2);

            window.onresize = myChartTwo.resize;

            myChartTwo.hideLoading({
                text: '正在努力的读取数据中...'
            });

            //加载成功
            loadSuccess();

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

//隐藏左键菜单
function menuHide() {
    menu.style.display = "none";
}

//散点图点击显示菜单
function showMenu() {
    var e = event || window.event;
    //单击显示div
    menu.style.display = "block";
    //设置定义
    //判断鼠标坐标是否大于视口宽度-块本身宽度
    var cakLeft = (e.clientX > document.documentElement.clientWidth - menu.offsetWidth)?(document.documentElement.clientWidth - menu.offsetWidth):e.clientX;
    var cakTop = (e.clientY > document.documentElement.clientHeight - menu.offsetHeight)?(document.documentElement.clientHeight - menu.offsetHeight):e.clientY;
    menu.style.left = cakLeft + "px";
    menu.style.top = cakTop + "px";
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
    window.parent.changeTabs(e)
    menu.style.display = "none";
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
            $("#tab-one").slideUp(500, function () {});
            $("#tab-two").slideUp(1000, function () {});
            $("#tab-three").slideUp(1000, function () {});
            $("#tab-four").slideDown(1000, function () {});
            break;
    }
}


