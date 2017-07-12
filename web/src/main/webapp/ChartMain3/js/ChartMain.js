/**
 * Created by laiqin on 2016/12/27.
 */

window.onload = function () {
    myChart1Init();
    myChart2Init();
}

function myChart1Init() {
    var myChart1 = echarts.init(document.getElementById('chart1'));

    var option1 = {
        title : {
            text: '人物关系：乔布斯',
            subtext: '数据来自人立方',
            x:'right',
            y:'bottom'
        },
        tooltip : {
            trigger: 'item',
            formatter: '{a} : {b}'
        },
        toolbox: {
            show : true,
            feature : {
                restore : {show: true},
                magicType: {show: true, type: ['force', 'chord']},
                saveAsImage : {show: true}
            }
        },
        legend: {
            x: 'left',
            data:['家人','朋友']
        },
        series : [
            {
                type:'force',
                name : "人物关系",
                ribbonType: false,
                categories : [
                    {
                        name: '人物'
                    },
                    {
                        name: '家人',
                        symbol: 'diamond'
                    },
                    {
                        name:'朋友'
                    }
                ],
                itemStyle: {
                    normal: {
                        label: {
                            show: true,
                            textStyle: {
                                color: '#333'
                            }
                        },
                        nodeStyle : {
                            brushType : 'both',
                            borderColor : 'rgba(255,215,0,0.4)',
                            borderWidth : 1
                        }
                    },
                    emphasis: {
                        label: {
                            show: false
                            // textStyle: null      // 默认使用全局文本样式，详见TEXTSTYLE
                        },
                        nodeStyle : {
                            //r: 30
                        },
                        linkStyle : {}
                    }
                },
                minRadius : 15,
                maxRadius : 25,
                gravity: 1.1,
                scaling: 1.2,
                draggable: true,
                linkSymbol: 'arrow',
                steps: 1,
                coolDown: 0.9,
                //preventOverlap: true,
                nodes:[
                    {
                        category:0, name: '乔布斯', value : 10,
                        symbol: 'image://img/steve-jobs.jpg'/*'image://http://www.damndigital.com/wp-content/uploads/2010/12/steve-jobs.jpg'*/,
                        symbolSize: [60, 35],
                        draggable: true,
                        itemStyle: {
                            normal: {
                                label: {
                                    position: 'right',
                                    textStyle: {
                                        color: 'black'
                                    }
                                }
                            }
                        }
                    },
                    {category:1, name: '丽萨-乔布斯',value : 2},
                    {category:1, name: '保罗-乔布斯',value : 3},
                    {category:1, name: '克拉拉-乔布斯',value : 3},
                    {category:1, name: '劳伦-鲍威尔',value : 7},
                    {category:2, name: '史蒂夫-沃兹尼艾克',value : 5},
                    {category:2, name: '奥巴马',value : 8},
                    {category:2, name: '比尔-盖茨',value : 9},
                    {category:2, name: '乔纳森-艾夫',value : 4},
                    {category:2, name: '蒂姆-库克',value : 4},
                    {category:2, name: '龙-韦恩',value : 1},
                ],
                links : [
                    {source : '丽萨-乔布斯', target : '乔布斯', weight : 1, name: '女儿', itemStyle: {
                        normal: {
                            width: 1.5,
                            color: 'red'
                        }
                    }},
                    {source : '乔布斯', target : '丽萨-乔布斯', weight : 1, name: '父亲', itemStyle: {
                        normal: { color: 'red' }
                    }},
                    {source : '保罗-乔布斯', target : '乔布斯', weight : 2, name: '父亲'},
                    {source : '克拉拉-乔布斯', target : '乔布斯', weight : 1, name: '母亲'},
                    {source : '劳伦-鲍威尔', target : '乔布斯', weight : 2},
                    {source : '史蒂夫-沃兹尼艾克', target : '乔布斯', weight : 3, name: '合伙人'},
                    {source : '奥巴马', target : '乔布斯', weight : 1},
                    {source : '比尔-盖茨', target : '乔布斯', weight : 6, name: '竞争对手'},
                    {source : '乔纳森-艾夫', target : '乔布斯', weight : 1, name: '爱将'},
                    {source : '蒂姆-库克', target : '乔布斯', weight : 1},
                    {source : '龙-韦恩', target : '乔布斯', weight : 1},
                    {source : '克拉拉-乔布斯', target : '保罗-乔布斯', weight : 1},
                    {source : '奥巴马', target : '保罗-乔布斯', weight : 1},
                    {source : '奥巴马', target : '克拉拉-乔布斯', weight : 1},
                    {source : '奥巴马', target : '劳伦-鲍威尔', weight : 1},
                    {source : '奥巴马', target : '史蒂夫-沃兹尼艾克', weight : 1},
                    {source : '比尔-盖茨', target : '奥巴马', weight : 6},
                    {source : '比尔-盖茨', target : '克拉拉-乔布斯', weight : 1},
                    {source : '蒂姆-库克', target : '奥巴马', weight : 1}
                ]
            }
        ]
    }

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

            myChart1.on(ecConfig.EVENT.HOVER, mHover);

            function mHover(e) {
                e.seriesName
            }

            // 为echarts对象加载数据
            myChart1.setOption(option1);

            myChart1.hideLoading({
                text: '正在努力的读取数据中...'
            });
        }
    );
}

function myChart2Init() {
    var myChart3 = echarts.init(document.getElementById('chart3'));
    var option3 = {
        tooltip : {
            trigger: 'axis'
        },
        legend: {
            data:['最高','最低']
        },
        toolbox: {
            show : true,
            feature : {
                mark : {show: true},
                dataView : {readOnly:false},
                magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        calculable : true,
        dataZoom : {
            show : true,
            realtime : true,
            start : 0     //数据缩放，选择起始比例，默认为0（%），从首个数据起选择。
            //,end : 100    //数据缩放，选择结束比例，默认为100（%），到最后一个数据选择结束。
            ,handleSize:20
        },
        xAxis : [
            {
                type : 'category',
                boundaryGap : true,
                data : function (){
                    var list = [];
                    for (var i = 1; i <= 30; i++) {
                        list.push('2013-03-' + i);
                    }
                    return list;
                }()
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        series : [
            {
                name:'最高',
                type:'bar',
                data:function (){
                    var list = [];
                    for (var i = 1; i <= 30; i++) {
                        list.push(Math.round(Math.random()* 30) + 30);
                    }
                    return list;
                }()
            },
            {
                name:'最低',
                type:'bar',
                data:function (){
                    var list = [];
                    for (var i = 1; i <= 30; i++) {
                        list.push(Math.round(Math.random()* 10));
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
            myChart3.showLoading({
                text: '正在努力的读取数据中...'
            });
            var ecConfig = require('echarts/config');
//        function focus(param) {
//            var data = param.data;
//            var links = option3.series[0].links;
//            var nodes = option3.series[0].nodes;
//            if (
//                data.source !== undefined
//                && data.target !== undefined
//                ) { //点击的是边
//                var sourceNode = nodes.filter(function (n) {return n.name == data.source})[0];
//                var targetNode = nodes.filter(function (n) {return n.name == data.target})[0];
//                console.log("选中了边 " + sourceNode.name + ' -> ' + targetNode.name + ' (' + data.weight + ')');
//            } else { // 点击的是点
//                sendData()//添加原点点击时间
//                console.log("选中了" + data.name + '(' + data.value + ')');
//            }
//        }
//        myChart3.on(ecConfig.EVENT.CLICK, focus)
//
//        myChart3.on(ecConfig.EVENT.FORCE_LAYOUT_END, function () {
//            console.log(myChart3.chart.force.getPosition());
//        });
            function eConsole(param) {
                var mes = '【' + param.type + '】';
                if (typeof param.seriesIndex != 'undefined') {
                    mes += '  seriesIndex : ' + param.seriesIndex;
                    mes += '  dataIndex : ' + param.dataIndex;
                }
                if (param.type == 'hover') {
                    document.getElementById('hover-console').innerHTML = 'Event Console : ' + mes;
                }
                else {
                    document.getElementById('console').innerHTML = mes;
                }
                console.log(param);
            }

            myChart3.on(ecConfig.EVENT.CLICK, eConsole);
            myChart3.on(ecConfig.EVENT.DBLCLICK, eConsole);
//myChart.on(ecConfig.EVENT.HOVER, eConsole);
            myChart3.on(ecConfig.EVENT.DATA_ZOOM, eConsole);
            myChart3.on(ecConfig.EVENT.LEGEND_SELECTED, eConsole);
            myChart3.on(ecConfig.EVENT.MAGIC_TYPE_CHANGED, eConsole);
            myChart3.on(ecConfig.EVENT.DATA_VIEW_CHANGED, eConsole);
            // 为echarts对象加载数据
            myChart3.setOption(option3);

            myChart3.hideLoading({
                text: '正在努力的读取数据中...'
            });
        }
    );
}

function sendData(){
    var bb = "传值";
    var temp_ifr = $("#ifr_chart2").attr("src",encodeURI("../ChartPhonePie/ChartPhonePie.html?aa="+bb))
}