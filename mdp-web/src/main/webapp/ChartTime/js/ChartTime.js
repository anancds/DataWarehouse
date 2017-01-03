/**
 * Created by laiqin on 2016/12/27.
 */

var xTime = [];     //横坐标时间
var yTime = [];     //纵坐标时间
var zTime = [];     //通话时间

window.onload = function () {
    readData();
}

function readData() {
    $.ajax({
        async:true,
        type:'GET',
        url:'data/phone.json',
        complete:function(data){
            var ojson = data.responseJSON;//获取返回数据
            for(var i=0; i<ojson.data.length; i++){
                xTime.push(ojson.data[i].date.substring(0,ojson.data[i].date.indexOf(' ')));
                yTime.push(ojson.data[i].date.substring(ojson.data[i].date.indexOf(' ')+1,ojson.data[i].date.indexOf(':')));
                zTime.push(ojson.data[i].hours);
            }
            debugger
            myChart1Init();
        },
        error:function () {
            alert('数据获取失败!');
        }
    });
}

function myChart1Init() {
    var myChart1 = echarts.init(document.getElementById('chart1'));

    var option1 = {
        title : {
            text : '时间坐标散点图',
            subtext : 'dataZoom支持'
        },
        tooltip : {
            trigger: 'axis',
            axisPointer:{
                show: true,
                type : 'cross',
                lineStyle: {
                    type : 'dashed',
                    width : 1
                }
            }
        },
        toolbox: {
            show : true,
            feature : {
                mark : {show: true},
                dataView : {show: true, readOnly: false},
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        dataZoom: {
            show: true,
            start : 0,
            end : 100
        },
        legend : {
            data : ['移动语音业务'/*,'series2'*/]
        },
        dataRange: {
            min: 0,
            max: 100,
            orient: 'horizontal',
            y: 30,
            x: 'center',
            //text:['高','低'],           // 文本，默认为数值文本
            color:['lightgreen','orange','red'],
            splitNumber: 5
        },
        grid: {
            y2: 80
        },
        xAxis : [
            {
                type : 'time',
                splitNumber:10
            }
        ],
        yAxis : [
            {
                type : 'values'
            }
        ],
        animation: false,
        series : [
            {
                name:'移动语音业务',
                type:'scatter',
                tooltip : {
                    trigger: 'axis',
                    formatter : function (params) {
                        var date = new Date(params.value[0]);
                        return params.seriesName
                            + ' （'
                            + date.getFullYear() + '-'
                            + (date.getMonth() + 1) + '-'
                            + date.getDate() + ' '
                            + date.getHours() + ':'
                            + date.getMinutes()
                            +  '）<br/>'
                            + params.value[1] + ', '
                            + params.value[2];
                    },
                    axisPointer:{
                        type : 'shadow',
                        lineStyle: {
                            type : 'dashed',
                            width : 1
                        }
                    }
                },
                symbolSize: function (value){
                    debugger
                    return value[2]/60;
                },
                data: (function () {
                    var d = [];
                    for(var i=0; i<xTime.length; i++){
                        d.push([new Date(xTime[i]),parseInt(yTime[i]),zTime[i]])
                    }
                    debugger
                    return d;
                    /*var d = [];
                    var len = 0;
                    var now = new Date();
                    var value;
                    while (len++ < 1500) {
                        d.push([
                            new Date(2014, 10, 1, 0, Math.round(Math.random()*10000)),    //x轴
                            (Math.random()*30).toFixed(2) - 0,     //y轴
                            (Math.random()*100).toFixed(2) - 0
                        ]);
                    }
                    return d;*/
                })()
            }/*,
            {
                name:'series2',
                type:'scatter',
                tooltip : {
                    trigger: 'axis',
                    formatter : function (params) {
                        var date = new Date(params.value[0]);
                        return params.seriesName
                            + ' （'
                            + date.getFullYear() + '-'
                            + (date.getMonth() + 1) + '-'
                            + date.getDate() + ' '
                            + date.getHours() + ':'
                            + date.getMinutes()
                            +  '）<br/>'
                            + params.value[1] + ', '
                            + params.value[2];
                    },
                    axisPointer:{
                        type : 'cross',
                        lineStyle: {
                            type : 'dashed',
                            width : 1
                        }
                    }
                },
                symbolSize: function (value){
                    return Math.round(value[2]/10);
                },
                data: (function () {
                    var d = [];
                    var len = 0;
                    var now = new Date();
                    var value;
                    while (len++ < 1500) {
                        d.push([
                            new Date(2014, 10, 1, 0, Math.round(Math.random()*10000)),
                            (Math.random()*30).toFixed(2) - 0,
                            (Math.random()*100).toFixed(2) - 0
                        ]);
                    }
                    return d;
                })()
            }*/
        ]
    };

    require(
        [
            'echarts'
        ],
        function (ec) {
            var ecConfig = require('echarts/config');

            // 过渡---------------------
            myChart1.showLoading({
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
                    var sourceNode = nodes.filter(function (n) {return n.name == data.source})[0];
                    var targetNode = nodes.filter(function (n) {return n.name == data.target})[0];
                    console.log("选中了边 " + sourceNode.name + ' -> ' + targetNode.name + ' (' + data.weight + ')');
                } else { // 点击的是点
                    sendData()//添加原点点击时间
                    console.log("选中了" + data.name + '(' + data.value + ')');
                }
            }
            myChart1.on(ecConfig.EVENT.CLICK, focus)

            myChart1.on(ecConfig.EVENT.FORCE_LAYOUT_END, function () {
                console.log(myChart1.chart.force.getPosition());
            });

            // 为echarts对象加载数据
            myChart1.setOption(option1);

            myChart1.hideLoading({
                text: '正在努力的读取数据中...'
            });
        }
    );
}

function sendData(){
    var bb = "传值";
    var temp_ifr = $("#ifr_chart2").attr("src",encodeURI("../ChartPhonePie/ChartPhonePie.html?aa="+bb))
}