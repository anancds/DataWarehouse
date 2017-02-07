/**
 * Created by laiqin on 2017/1/20.
 */


var chartBig = false;    //chart窗口大小开关

var myChartOne;     //容器初始化
var myChartTwo;

var personArray = [];   //人物
var personRelation = [];   //人物关系

var initFlag = false;

window.onload = function () {
    dataControl()
}

function formResize(e) {
    if (chartBig == false && e == 1) {
        $("#chartleft").animate({width: '100%'}, 'slow');
        $("#chartright").animate({width: '0'}, 'slow');
        $("#chartright").hide();
        chartBig = true;
    } else if (chartBig == true && e == 1) {
        $("#chartleft").animate({width: '70%'}, 'slow');
        $("#chartright").animate({width: '30%'}, 'slow');
        $("#chartright").show();
        chartBig = false;
    } else if (chartBig == false && e == 2) {
        $("#chartleft").animate({width: '30%'}, 'slow');
        $("#chartright").animate({width: '70%'}, 'slow');
        $("#chartright").show();
        chartBig = true;
    } else if (chartBig == true && e == 2) {
        $("#chartleft").animate({width: '70%'}, 'slow');
        $("#chartright").animate({width: '30%'}, 'slow');
        $("#chartright").show();
        chartBig = false;
    }
}

//chart动态改变宽度
function divResize() {
    $("#chartleft").resize(function () {
        myChartOne.resize;
        // myChartTwo.resize;
    })
}

//日期选择初始化
function dataControl() {
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

//时空关系数据获取
function chartRelationData() {

    personArray = [
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
        {category:2, name: '龙-韦恩',value : 1}
    ];

    personRelation = [
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
        {source : '蒂姆-库克', target : '奥巴马', weight : 1,time:'2016-3-3'}
    ];

    //关系拓扑图初始化
    chartInit();

    //时间轴chart初始化
    chartTimeInit();
}

function chartInit() {
    myChartOne = echarts.init(document.getElementById('chartinit'));
    divResize()

    var option1 = {

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
            x: 'center',
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
                linkSymbol: 'triangle',//线上加形状
                steps: 1,
                coolDown: 0.9,
                //preventOverlap: true,
                nodes: personArray,
                links : personRelation
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
            myChartOne.on(ecConfig.EVENT.CLICK, focus)

            myChartOne.on(ecConfig.EVENT.FORCE_LAYOUT_END, function () {
                console.log(myChartOne.chart.force.getPosition());
            });

            myChartOne.on(ecConfig.EVENT.HOVER, mHover);

            function mHover(e) {
                e.seriesName
            }

            // 为echarts对象加载数据
            myChartOne.setOption(option1);

            //初始化chart根据窗口大小来同步改变大小
            window.onresize = myChartOne.resize;

            myChartOne.hideLoading({
                text: '正在努力的读取数据中...'
            });

            initFlag = true;

        }
    );
}

//时间轴chart初始化
function chartTimeInit() {
    myChartTwo = echarts.init(document.getElementById('chartinittime'));

    var option = {
        title: {
            text: '全量明细数据',
            subtext: 'dataZoom支持'
        },
        tooltip : {
            trigger: 'axis'
        },
        legend: {
            data:['通话','短信']
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
            start : 0,
            end : 100,
            handleSize: 20
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
                name:'通话',
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
                name:'短信',
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
            myChartTwo.showLoading({
                text: '正在努力的读取数据中...'
            });
            function focus(param) {
                var data = param.data;
                var links = option.series[0].links;
                var nodes = option.series[0].nodes;
                if (
                    data.source !== undefined
                    && data.target !== undefined
                ) { //点击的是边
                    var sourceNode = nodes.filter(function (n) {return n.name == data.source})[0];
                    var targetNode = nodes.filter(function (n) {return n.name == data.target})[0];
                    console.log("选中了边 " + sourceNode.name + ' -> ' + targetNode.name + ' (' + data.weight + ')');
                } else { // 点击的是点
                    console.log("选中了" + data.name + '(' + data.value + ')');
                }
            }
            myChartTwo.on(ecConfig.EVENT.CLICK, focus)

            myChartTwo.on(ecConfig.EVENT.HOVER, mHover);

            function mHover(e) {
                e.seriesName
            }

            myChartTwo.on(ecConfig.EVENT.DATA_ZOOM, dataZoom);

            function dataZoom() {
                chartInit();
            }

            // 为echarts对象加载数据
            myChartTwo.setOption(option);

            //初始化chart根据窗口大小来同步改变大小
            window.onresize = myChartTwo.resize;

            myChartTwo.hideLoading({
                text: '正在努力的读取数据中...'
            });
        }
    );
}

//增加人物
function moreData() {
    var perObj = {
        category:2,
        name: '张三',
        value : 1
    };
    personArray.push(perObj);

    var relation = {
        source : '比尔-盖茨',
        target : '张三',
        weight : 1
    }
    personRelation.push(relation);

    chartInit()
}

//点击tab标签页显示对应的form表单
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

function returnInitFlag() {
    return initFlag;
}