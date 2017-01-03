/**
 * Created by laiqin on 2016/12/27.
 */

var aa = decodeURI(queryString("aa"));
var phonedays=[];//星期几
var phonedata=[];//通话数据

window.onload = function () {
    dataRead();
    //alert(new Date().getDay());
    //chartInit();
}

//时间选择
function dateChooseFirst(aInitdate) {
    $('#first').datetimepicker({
        language:  'zh-CN',
        weekStart: 1,
        todayBtn:  1,   //显示今天
        autoclose: 1,   //选择一个日期后自动关闭
        todayHighlight: true,  //高亮当前日期
        startView: 2,   //重日开始选择,3是月份,4是月份,1是小时
        minView: 2,     //最精确的时间,数字代表含义同上
        //maxView:4,    //最高能选择的时间范围
        forceParse: 0   //当选择器关闭的时候，是否强制解析输入框中的值。也就是说，当用户在输入框中输入了不正确的日期，选择器将会尽量解析输入的值，并将解析后的正确值按照给定的格式format设置到输入框中。
        //endDate:'2017-8-30'
        ,pickerPosition:'bottom-right'   //选择器位置
        ,format:'yyyy-mm-dd'
        ,initialDate:aInitdate
    });
    $('#first').datetimepicker().on('changeDate', function(ev){
        dateChange()
    });
}

//图标初始化
function chartInit() {
    // 基于准备好的dom，初始化echarts图表
    var myChart2 = echarts.init(document.getElementById('chart2'));

    var hours = ['12a', '1a', '2a', '3a', '4a', '5a', '6a',
        '7a', '8a', '9a','10a','11a',
        '12p', '1p', '2p', '3p', '4p', '5p',
        '6p', '7p', '8p', '9p', '10p', '11p'];
    // phonedays = ['1', '2', '3',
    //     '4', '5', '6', '7'];
    //
    // //[x,y,z] x星期几 y小时 z通话时间
    // phonedata = [[0,0,5,10],[0,1,1],[0,2,0],[0,3,0],[0,4,0],[0,5,0],[0,6,0],[0,7,0],[0,8,0],[0,9,0],[0,10,0],[0,11,2],[0,12,4],[0,13,1],[0,14,1],[0,15,3],[0,16,4],[0,17,6],[0,18,4],[0,19,4],[0,20,3],[0,21,3],[0,22,2],[0,23,5],[1,0,7],[1,1,0],[1,2,0],[1,3,0],[1,4,0],[1,5,0],[1,6,0],[1,7,0],[1,8,0],[1,9,0],[1,10,5],[1,11,2],[1,12,2],[1,13,6],[1,14,9],[1,15,11],[1,16,6],[1,17,7],[1,18,8],[1,19,12],[1,20,5],[1,21,5],[1,22,7],[1,23,2],[2,0,1],[2,1,1],[2,2,0],[2,3,0],[2,4,0],[2,5,0],[2,6,0],[2,7,0],[2,8,0],[2,9,0],[2,10,3],[2,11,2],[2,12,1],[2,13,9],[2,14,8],[2,15,10],[2,16,6],[2,17,5],[2,18,5],[2,19,5],[2,20,7],[2,21,4],[2,22,2],[2,23,4],[3,0,7],[3,1,3],[3,2,0],[3,3,0],[3,4,0],[3,5,0],[3,6,0],[3,7,0],[3,8,1],[3,9,0],[3,10,5],[3,11,4],[3,12,7],[3,13,14],[3,14,13],[3,15,12],[3,16,9],[3,17,5],[3,18,5],[3,19,10],[3,20,6],[3,21,4],[3,22,4],[3,23,1],[4,0,1],[4,1,3],[4,2,0],[4,3,0],[4,4,0],[4,5,1],[4,6,0],[4,7,0],[4,8,0],[4,9,2],[4,10,4],[4,11,4],[4,12,2],[4,13,4],[4,14,4],[4,15,14],[4,16,12],[4,17,1],[4,18,8],[4,19,5],[4,20,3],[4,21,7],[4,22,3],[4,23,0],[5,0,2],[5,1,1],[5,2,0],[5,3,3],[5,4,0],[5,5,0],[5,6,0],[5,7,0],[5,8,2],[5,9,0],[5,10,4],[5,11,1],[5,12,5],[5,13,10],[5,14,5],[5,15,7],[5,16,11],[5,17,6],[5,18,0],[5,19,5],[5,20,3],[5,21,4],[5,22,2],[5,23,0],[6,0,1],[6,1,0],[6,2,0],[6,3,0],[6,4,0],[6,5,0],[6,6,0],[6,7,0],[6,8,0],[6,9,0],[6,10,1],[6,11,0],[6,12,2],[6,13,1],[6,14,3],[6,15,4],[6,16,0],[6,17,0],[6,18,0],[6,19,0],[6,20,1],[6,21,2],[6,22,2],[6,23,6]];

    var option2 = {
        title: {
            text: '通话时间',
            link: 'https://github.com/pissang/echarts-next/graphs/punch-card'
        },
        legend: {
            data: ['Punch Card'],
            left: 'right'
        },
        polar: {},
        tooltip: {
            formatter: function (params) {
                return params.value[4] +',在' + params.value[1] + '点钟,通话' + params.value[2] + '分钟,' + '共通话' + params.value[3] + '次';
                return params.value[2] + ' 分钟 in ' + hours[params.value[1]] + ' of ' + phonedays[params.value[0]];
            }
        },
        angleAxis: {
            type: 'category',
            data: hours,
            boundaryGap: false,
            splitLine: {
                show: true,
                lineStyle: {
                    color: '#999',
                    type: 'dashed'
                }
            },
            axisLine: {
                show: false
            }
        },
        radiusAxis: {
            type: 'category',
            data: phonedays,
            axisLine: {
                show: false
            },
            axisLabel: {
                rotate: 45
            }
        },
        series: [{
            name: 'Punch Card',
            type: 'scatter',
            coordinateSystem: 'polar',
            symbolSize: function (val) {
                return val[2] * 2;
            },
            data: phonedata,
            animationDelay: function (idx) {
                return idx * 5;
            }
        }]
    };

    require(
        [
            'echarts'
        ],
        function (ec) {
            var ecConfig = require('echarts/config');

            // 过渡---------------------
            myChart2.showLoading({
                text: '正在努力的读取数据中...'
            });
            var ecConfig = require('echarts/config');
            function focus(param) {
                var data = param.phonedata;
                var links = option2.series[0].links;
                var nodes = option2.series[0].nodes;
                if (
                    data.source !== undefined
                    && data.target !== undefined
                ) { //点击的是边
                    var sourceNode = nodes.filter(function (n) {return n.name == data.source})[0];
                    var targetNode = nodes.filter(function (n) {return n.name == data.target})[0];
                    console.log("选中了边 " + sourceNode.name + ' -> ' + targetNode.name + ' (' + data.weight + ')');
                } else { // 点击的是点
                    alert(data.name)//添加原点点击时间
                    console.log("选中了" + data.name + '(' + data.value + ')');
                }
            }
            myChart2.on(ecConfig.EVENT.CLICK, focus)

            myChart2.on(ecConfig.EVENT.FORCE_LAYOUT_END, function () {
                console.log(myChart2.chart.force.getPosition());
            });

            // 为echarts对象加载数据
            myChart2.setOption(option2);

            myChart2.hideLoading({
                text: '正在努力的读取数据中...'
            });
        }
    );
}

//获取数据
function dataRead() {
    var j = 0;
    $.ajax({
        async:true,
        type:'GET',
        url:'./data/phone.json',
        complete:function(data){
            var ojson = data.responseJSON;//获取返回数据
            var initdate = ojson.data[0].date;
            $("#choosetime").val(initdate);//默认时间为数据第一天
            dateChooseFirst(initdate);
            // for(var i=0; phonedays.length<7; i++){
            //     var tempday = new Date(ojson.data[i].date).getDay();
            //     if(phonedays.indexOf(tempday) == -1){//加载7天数据
            //         phonedays.push(tempday);
            //     }
            //     if((Date.parse(ojson.data[j].date) - Date.parse(ojson.data[0].date))/(1000*60*60*24) <=7){
            //         var tempdata = [
            //             new Date(ojson.data[i].date).getDay(),
            //             ojson.data[i].hours,
            //             parseInt(ojson.data[i].time)/200,
            //             ojson.data[i].number
            //         ];
            //         phonedata.push(tempdata);
            //         j++;
            //     }
            // }

            for (var i=0; i<ojson.data.length; i++){
                var tempday = new Date(ojson.data[i].date).getDay();
                if(phonedays.indexOf(tempday) == -1){//加载7天数据
                    phonedays.push(tempday);
                }
                if((Date.parse(ojson.data[i].date) - Date.parse(ojson.data[0].date))/(1000*60*60*24) <=6){
                    var tempdata = [
                        new Date(ojson.data[i].date).getDay(),
                        ojson.data[i].hours,
                        parseInt(ojson.data[i].time)/200,
                        ojson.data[i].number,
                        ojson.data[i].date
                    ];
                    phonedata.push(tempdata);
                }
            }
            chartInit();
        },
        error:function () {
            alert('数据获取失败!');
        }
    });
}

function dateChange() {//时间改变后,重新加载数据.
    phonedays.splice(0,phonedays.length);
    phonedata.splice(0,phonedata.length);
    var nowdate = $("#choosetime").val();//当前文本框时间
    $.ajax({
        async:true,
        type:'GET',
        url:'./data/phone.json',
        complete:function(data) {
            var ojson = data.responseJSON;//获取返回数据
            for (var i=0; i<ojson.data.length; i++){
                if((Date.parse(ojson.data[i].date) - Date.parse(nowdate))/(1000*60*60*24) <=7 && (Date.parse(ojson.data[i].date) - Date.parse(nowdate))/(1000*60*60*24) >=0){
                    var tempday = new Date(ojson.data[i].date).getDay();
                    if(phonedays.indexOf(tempday) == -1){//加载7天数据
                        phonedays.push(tempday);
                    }
                    var tempdata = [
                        new Date(ojson.data[i].date).getDay(),
                        ojson.data[i].hours,
                        parseInt(ojson.data[i].time)/200,
                        ojson.data[i].number,
                        ojson.data[i].date
                    ];
                    phonedata.push(tempdata);
                }
            }
            debugger
            chartInit();
        }
    });
}