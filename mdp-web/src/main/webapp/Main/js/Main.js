/**
 * Created by laiqin on 2017/1/9.
 */

/*$(function () {
    $('#myTab li:eq(1) a').tab('show')
    $('#chartTabs li:eq(1) a').tab('show')
})*/   // 按序号来选择active窗口

var xTime = [];     //横坐标时间   移动语音业务
var yTime = [];     //纵坐标时间
var zTime = [];     //通话时间

var xTimeGPRS = [];     //横坐标时间   GPRS业务
var yTimeGPRS = [];     //纵坐标时间
var zTimeGPRS = [];     //通话时间

function aa() {
    // $("#test").fadeIn(200, function () {
    // });
    //$("#test").show()
    $("#test").animate({width:'10%'},'slow');
}

function bb() {
    // $("#test").fadeOut(200,function () {
    //
    // });
    $("#test").hide()
}

window.onload = function () {
    formResize()
    $(function(){
        $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
            // 获取已激活的标签页的名称
            var activeTab = $(e.target).text();
            // 获取前一个激活的标签页的名称
            var previousTab = $(e.relatedTarget).text();
            // switch (activeTab){
            //     case '全量':
            //         chartAllData();
            // }
        });
    });
}

function formResize() {
    $("#mainalladta").height($(window).height()-200);
}

function searchDialog() {
    $('#myModal').modal('show')
}

function showData() {
    $.confirm({
        title: '数据筛选!',
        content: '点击确定查看详情!',
        autoClose: '取消|3000',
        buttons: {
            确定: function () {
                $('#chartTabs li:eq(3) a').tab('show');
            },
            取消: function () {

            }
        }
    });
}

//全量数据获取
function chartAllData() {
    $.ajax({
        async:true,
        type:'GET',
        url:'data/phone.json',
        complete:function(data){
            var ojson = data.responseJSON;//获取返回数据
            xTime.splice(0, xTime.length);
            yTime.splice(0, yTime.length);
            zTime.splice(0, zTime.length);
            for(var i=0; i<ojson.data.length; i++){
                if(ojson.data[i].type == 1){
                    xTime.push(ojson.data[i].date/*.substring(0,ojson.data[i].date.indexOf(' '))*/);
                    var tempHour = ojson.data[i].date.substring(ojson.data[i].date.indexOf(' ')+1,ojson.data[i].date.indexOf(':'));
                    var tempMin = ojson.data[i].date.substring(ojson.data[i].date.indexOf(':')+1)
                    yTime.push(parseFloat((tempMin/60).toFixed(2)) + parseInt(tempHour));
                    zTime.push(ojson.data[i].hours);
                }else if(ojson.data[i].type == 2){
                    xTimeGPRS.push(ojson.data[i].date/*.substring(0,ojson.data[i].date.indexOf(' '))*/);
                    var tempHour = ojson.data[i].date.substring(ojson.data[i].date.indexOf(' ')+1,ojson.data[i].date.indexOf(':'));
                    var tempMin = ojson.data[i].date.substring(ojson.data[i].date.indexOf(':')+1)
                    yTimeGPRS.push(parseFloat((tempMin/60).toFixed(2)) + parseInt(tempHour));
                    zTimeGPRS.push(ojson.data[i].hours);
                }
            }
            chartAllDataInit();
        },
        error:function () {
            alert('数据获取失败!');
        }
    });
}

//绘制chart
function chartAllDataInit() {
    var myChart1 = echarts.init(document.getElementById('chartAllData'));

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
                dataZoom : {show: true},
                dataView : {show: true, readOnly: false},
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        dataZoom: {
            show: true,
            start : 0,
            end : 100,
            handleSize: 20
        },
        legend : {
            data : ['移动语音业务','GPRS业务']
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
        xAxis : [
            {
                type : 'time',
                splitNumber:10
            }
        ],
        yAxis : [
            {
                type : 'values',
                splitNumber: 12
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
                    return 7;    //设置圆大小
                },
                data: (function () {
                    var d = [];
                    for(var i=0; i<xTime.length; i++){
                        d.push([new Date(xTime[i]),parseInt(yTime[i]),zTime[i]])
                    }
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
            },{
                name:'GPRS业务',
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
                    return 7;    //设置圆大小
                },
                data: (function () {
                    var d = [];
                    for(var i=0; i<xTime.length; i++){
                        d.push([new Date(xTimeGPRS[i]),parseInt(yTimeGPRS[i]),zTimeGPRS[i]])
                    }
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
                    //sendData()//添加原点点击时间
                    console.log("选中了" + data.name + '(' + data.value + ')');
                }
            }
            myChart1.on(ecConfig.EVENT.CLICK, focus)

            myChart1.on(ecConfig.EVENT.FORCE_LAYOUT_END, function () {
                console.log(myChart1.chart.force.getPosition());
            });
            myChart1.on(ecConfig.EVENT.DATA_ZOOM, function (e) {
                if(e.zoom.start != 0 && e.zoom.end != 100){
                    showData()
                }
                debugger
            });
            // 为echarts对象加载数据
            myChart1.setOption(option1);

            myChart1.hideLoading({
                text: '正在努力的读取数据中...'
            });
        }
    );
}

function dataSearch() {
    //alert('--+--+--+-+-+-+-+')
    allData.window.getParent()
}