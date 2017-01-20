/**
 * Created by laiqin on 2017/1/13.
 */

var xTime = [[], [], [], []];     //横坐标时间   移动语音业务
var yTime = [[], [], [], []];     //纵坐标时间
var zTime = [[], [], [], []];     //通话时间

var chartBig = false;    //chart窗口大小开关

var myChartOne;     //容器初始化
var myChartTwo;

window.onload = function () {
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
    $.ajax({
        async: true,
        type: 'GET',
        url: 'data/phone.json',
        complete: function (data) {
            var ojson = data.responseJSON;//获取返回数据
            // xTime.splice(0, xTime.length);
            // yTime.splice(0, yTime.length);
            // zTime.splice(0, zTime.length);
            $("#info-name").html(ojson.name);
            for (var i = 0; i < ojson.data.length; i++) {
                if (ojson.data[i].type == 1) {
                    xTime[0][xTime[0].length] = ojson.data[i].date.substring(0, ojson.data[i].date.indexOf(' '));
                    var tempHour = ojson.data[i].date.substring(ojson.data[i].date.indexOf(' ') + 1, ojson.data[i].date.indexOf(':'));
                    var tempMin = ojson.data[i].date.substring(ojson.data[i].date.indexOf(':') + 1)
                    yTime[0][xTime[0].length] = parseFloat((tempMin / 60).toFixed(2)) + parseInt(tempHour);
                    zTime[0][xTime[0].length] = ojson.data[i].hours;
                } else if (ojson.data[i].type == 2) {
                    xTime[1][xTime[1].length] = ojson.data[i].date.substring(0, ojson.data[i].date.indexOf(' '));
                    var tempHour = ojson.data[i].date.substring(ojson.data[i].date.indexOf(' ') + 1, ojson.data[i].date.indexOf(':'));
                    var tempMin = ojson.data[i].date.substring(ojson.data[i].date.indexOf(':') + 1)
                    yTime[1][xTime[1].length] = parseFloat((tempMin / 60).toFixed(2)) + parseInt(tempHour);
                    zTime[1][xTime[1].length] = ojson.data[i].hours;
                } else if (ojson.data[i].type == 3) {
                    xTime[2][xTime[2].length] = ojson.data[i].date;
                    yTime[2][xTime[2].length] = ojson.data[i].hours;
                    zTime[2][xTime[2].length] = ojson.data[i].hours;
                }
            }
            debugger
            ;
            chartAllDataInit();
        },
        error: function () {
            alert('数据获取失败!');
        }
    });
}

//绘制chart
function chartAllDataInit() {
    myChartOne = echarts.init(document.getElementById('chartinit'));
    divResize()
    var option1 = {
        title: {
            text: '时间坐标散点图',
            subtext: 'dataZoom支持'
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
            data: ['移动语音业务', 'GPRS业务']
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
                splitNumber: 12
            }
        ],
        animation: false,
        series: [
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
                symbolSize: function (value) {
                    return 7;    //设置圆大小
                },
                data: (function () {
                    var d = [];
                    for (var i = 0; i < xTime[0].length; i++) {
                        d.push([new Date(xTime[0][i]), parseInt(yTime[0][i]), zTime[0][i]])
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
                symbolSize: function (value) {
                    return 7;    //设置圆大小
                },
                data: (function () {
                    var d = [];
                    for (var i = 0; i < xTime[1].length; i++) {
                        d.push([new Date(xTime[1][i]), parseInt(yTime[1][i]), zTime[1][i]])
                    }
                    return d;
                })()
            }
        ]
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
            var ecConfig = require('echarts/config');

            function focus(param) {
                var data = param.data;
                var links = option1.series[0].links;
                var nodes = option1.series[0].nodes;
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
            // 为echarts对象加载数据
            myChartOne.setOption(option1);

            window.onresize = myChartOne.resize;

            myChartOne.hideLoading({
                text: '正在努力的读取数据中...'
            });
            chartTimeInit();
        }
    );
}

function getParent() {
    alert('fudiaozi')
    return
    window.parent.dataSearch()
}

//获取数据
function chartTimeData() {

}
//绘制下方的chart
function chartTimeInit() {
    myChartTwo = echarts.init(document.getElementById('chartinittime'));
    var option2 = {
        tooltip: {
            trigger: 'item'
        },
        legend: {
            data: ['移动语音业务', 'GPRS业务']
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
        series: [
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
        ]
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
            var ecConfig = require('echarts/config');

            function focus(param) {
                var data = param.data;
                var links = option2.series[0].links;
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

            myChartTwo.on(ecConfig.EVENT.CLICK, focus)

            myChartTwo.on(ecConfig.EVENT.FORCE_LAYOUT_END, function () {
                console.log(myChartTwo.chart.force.getPosition());
            });

            // 为echarts对象加载数据
            myChartTwo.setOption(option2);

            window.onresize = myChartTwo.resize;

            myChartTwo.hideLoading({
                text: '正在努力的读取数据中...'
            });

            myChartOne.connect([myChartTwo]);
            //myChartTwo.connect([myChartOne]);

            setTimeout(function () {
                window.onresize = function () {
                    myChartOne.resize();
                    myChartTwo.resize();
                }
            }, 200);
        }
    );
}

//chart动态改变
function divResize() {
    $("#chartleft").resize(function () {
        myChartOne.resize;
        myChartTwo.resize;
    })
}

function changeTab(tabnum) {
    if(tabnum == 1){
        $("#tab-one").slideDown(500, function () {
        });
        $("#tab-two").slideUp(1000, function () {
        });
        $("#tab-three").slideUp(1000, function () {
        });
    }else if (tabnum == 2){
        $("#tab-one").slideUp(500, function () {
        });
        $("#tab-two").slideDown(1000, function () {
        });
        $("#tab-three").slideUp(1000, function () {
        });
    }else if (tabnum == 3){
        $("#tab-one").slideUp(500, function () {
        });
        $("#tab-two").slideUp(1000, function () {
        });
        $("#tab-three").slideDown(1000, function () {
        });
    }
}
