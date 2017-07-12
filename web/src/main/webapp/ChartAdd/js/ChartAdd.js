/**
 * Created by laiqin on 2016/12/27.
 */

var xTime = [[], [], [], []];     //横坐标时间   移动语音业务
var yTime = [[], [], [], []];     //纵坐标时间
var zTime = [[], [], [], []];     //通话时间

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
            for (var i = 0; i < ojson.data.length; i++) {
                if (i==587){
                    debugger
                }
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
            console.log(xTime)
            console.log(yTime)
            console.log(zTime)
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
            text: '某楼盘销售情况',
            subtext: '纯属虚构'
        },
        tooltip : {
            trigger: 'axis'
        },
        legend: {
            data:['意向','预购','成交']
        },
        toolbox: {
            show : true,
            feature : {
                mark : {show: true},
                dataView : {show: true, readOnly: false},
                magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        calculable : true,
        xAxis : [
            {
                type : 'time',
                boundaryGap : true,
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        series : [
            {
                name:'成交',
                type:'bar',
                smooth:true,
                itemStyle: {normal: {areaStyle: {type: 'default'}}},
                data:function (){
                    var list = [];
                    for (var i = 1; i <= 30; i++) {
                        list.push(xTime[0][i],yTime[0][i],zTime[0][i]);
                    }
                    return list;
                }()
            },
            {
                name:'预购',
                type:'bar',
                smooth:true,
                itemStyle: {normal: {areaStyle: {type: 'default'}}},
                data:function (){
                    var list = [];
                    for (var i = 1; i <= 30; i++) {
                        list.push(xTime[0][i],yTime[0][i],zTime[0][i]);
                    }
                    return list;
                }()
            },
            {
                name:'意向',
                type:'bar',
                smooth:true,
                itemStyle: {normal: {areaStyle: {type: 'default'}}},
                data:function (){
                    var list = [];
                    for (var i = 1; i <= 30; i++) {
                        list.push(xTime[0][i],yTime[0][i],zTime[0][i]);
                    }
                    return list;
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