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
            subtext: '',
            x:'center',
            y:'top'
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
                        name: '家人'
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
                        },
                        linkStyle: {
                            type: 'curve'
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
                useWorker: false,
                minRadius : 15,
                maxRadius : 25,
                gravity: 1.1,
                scaling: 1.1,
                roam: 'move',
                nodes:[
                    {category:0, name: '乔布斯', value : 10, label: '乔布斯\n（主要）'/*,fixX:true,fixY:true,initial:[200,200]*/},
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
                    {category:2, name: '龙-韦恩1'	,value : 1},
                    {category:2, name: '龙-韦恩2'	,value : 1},
                    {category:2, name: '龙-韦恩3'	,value : 1},
                    {category:2, name: '龙-韦恩4'	,value : 1},
                    {category:2, name: '龙-韦恩5'	,value : 1},
                    {category:2, name: '龙-韦恩6'	,value : 1},
                    {category:2, name: '龙-韦恩7'	,value : 1},
                    {category:2, name: '龙-韦恩8'	,value : 1},
                    {category:2, name: '龙-韦恩9'	,value : 1},
                    {category:2, name: '龙-韦恩10'	,value : 1},
                    {category:2, name: '龙-韦恩11'	,value : 1},
                    {category:2, name: '龙-韦恩12'	,value : 1},
                    {category:2, name: '龙-韦恩13'	,value : 1},
                    {category:2, name: '龙-韦恩14'	,value : 1},
                    {category:2, name: '龙-韦恩15'	,value : 1},
                    {category:2, name: '龙-韦恩16'	,value : 1},
                    {category:2, name: '龙-韦恩17'	,value : 1},
                    {category:2, name: '龙-韦恩18'	,value : 1},
                    {category:2, name: '龙-韦恩19'	,value : 1},
                    {category:2, name: '龙-韦恩20'	,value : 1},
                    {category:2, name: '龙-韦恩21'	,value : 1},
                    {category:2, name: '龙-韦恩22'	,value : 1},
                    {category:2, name: '龙-韦恩23'	,value : 1},
                    {category:2, name: '龙-韦恩24'	,value : 1},
                    {category:2, name: '龙-韦恩25'	,value : 1},
                    {category:2, name: '龙-韦恩26'	,value : 1},
                    {category:2, name: '龙-韦恩27'	,value : 1},
                    {category:2, name: '龙-韦恩28'	,value : 1},
                    {category:2, name: '龙-韦恩29'	,value : 1},
                    {category:2, name: '龙-韦恩30'	,value : 1},
                    {category:2, name: '龙-韦恩31'	,value : 1},
                    {category:2, name: '龙-韦恩32'	,value : 1},
                    {category:2, name: '龙-韦恩33'	,value : 1},
                    {category:2, name: '龙-韦恩34'	,value : 1},
                    {category:2, name: '龙-韦恩35'	,value : 1},
                    {category:2, name: '龙-韦恩36'	,value : 1},
                    {category:2, name: '龙-韦恩37'	,value : 1},
                    {category:2, name: '龙-韦恩38'	,value : 1},
                    {category:2, name: '龙-韦恩39'	,value : 1},
                    {category:2, name: '龙-韦恩40'	,value : 1},
                    {category:2, name: '龙-韦恩41'	,value : 1},
                    {category:2, name: '龙-韦恩42'	,value : 1},
                    {category:2, name: '龙-韦恩43'	,value : 1},
                    {category:2, name: '龙-韦恩44'	,value : 1},
                    {category:2, name: '龙-韦恩45'	,value : 1},
                    {category:2, name: '龙-韦恩46'	,value : 1},
                    {category:2, name: '龙-韦恩47'	,value : 1},
                    {category:2, name: '龙-韦恩48'	,value : 1},
                    {category:2, name: '龙-韦恩49'	,value : 1},
                    {category:2, name: '龙-韦恩50'	,value : 1},
                    {category:2, name: '龙-韦恩51'	,value : 1},
                    {category:2, name: '龙-韦恩52'	,value : 1},
                    {category:2, name: '龙-韦恩53'	,value : 1},
                    {category:2, name: '龙-韦恩54'	,value : 1},
                    {category:2, name: '龙-韦恩55'	,value : 1},
                    {category:2, name: '龙-韦恩56'	,value : 1},
                    {category:2, name: '龙-韦恩57'	,value : 1},
                    {category:2, name: '龙-韦恩58'	,value : 1},
                    {category:2, name: '龙-韦恩59'	,value : 1},
                    {category:2, name: '龙-韦恩60'	,value : 1},
                    {category:2, name: '龙-韦恩61'	,value : 1},
                    {category:2, name: '龙-韦恩62'	,value : 1},
                    {category:2, name: '龙-韦恩63'	,value : 1},
                    {category:2, name: '龙-韦恩64'	,value : 1},
                    {category:2, name: '龙-韦恩65'	,value : 1},
                    {category:2, name: '龙-韦恩66'	,value : 1},
                    {category:2, name: '龙-韦恩67'	,value : 1},
                    {category:2, name: '龙-韦恩68'	,value : 1},
                    {category:2, name: '龙-韦恩69'	,value : 1},
                    {category:2, name: '龙-韦恩70'	,value : 1},
                    {category:2, name: '龙-韦恩71'	,value : 1},
                    {category:2, name: '龙-韦恩72'	,value : 1},
                    {category:2, name: '龙-韦恩73'	,value : 1},
                    {category:2, name: '龙-韦恩74'	,value : 1},
                    {category:2, name: '龙-韦恩75'	,value : 1},
                    {category:2, name: '龙-韦恩76'	,value : 1},
                    {category:2, name: '龙-韦恩77'	,value : 1},
                    {category:2, name: '龙-韦恩78'	,value : 1},
                    {category:2, name: '龙-韦恩79'	,value : 1},
                    {category:2, name: '龙-韦恩80'	,value : 1},
                    {category:2, name: '龙-韦恩81'	,value : 1},
                    {category:2, name: '龙-韦恩82'	,value : 1},
                    {category:2, name: '龙-韦恩83'	,value : 1},
                    {category:2, name: '龙-韦恩84'	,value : 1},
                    {category:2, name: '龙-韦恩85'	,value : 1},
                    {category:2, name: '龙-韦恩86'	,value : 1},
                    {category:2, name: '龙-韦恩87'	,value : 1},
                    {category:2, name: '龙-韦恩88'	,value : 1},
                    {category:2, name: '龙-韦恩89'	,value : 1},
                    {category:2, name: '龙-韦恩90'	,value : 1},
                    {category:2, name: '龙-韦恩91'	,value : 1},
                    {category:2, name: '龙-韦恩92'	,value : 1},
                    {category:2, name: '龙-韦恩93'	,value : 1},
                    {category:2, name: '龙-韦恩94'	,value : 1},
                    {category:2, name: '龙-韦恩95'	,value : 1},
                    {category:2, name: '龙-韦恩96'	,value : 1},
                    {category:2, name: '龙-韦恩97'	,value : 1},
                    {category:2, name: '龙-韦恩98'	,value : 1},
                    {category:2, name: '龙-韦恩99'	,value : 1},
                    {category:2, name: '龙-韦恩100'	,value : 1},

                ],
                links : [
                    {source : '丽萨-乔布斯', target : '乔布斯', weight : 1, name: '女儿'},
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
                    {source : '蒂姆-库克', target : '奥巴马', weight : 1},
                    {source : '龙-韦恩1', target : '乔布斯',	 weight : 1},
                    {source : '龙-韦恩2', target : '乔布斯',	 weight : 2},
                    {source : '龙-韦恩3', target : '乔布斯',	 weight : 3},
                    {source : '龙-韦恩4', target : '乔布斯',	 weight : 4},
                    {source : '龙-韦恩5', target : '乔布斯',	 weight : 5},
                    {source : '龙-韦恩6', target : '乔布斯',	 weight : 6},
                    {source : '龙-韦恩7', target : '乔布斯',	 weight : 7},
                    {source : '龙-韦恩8', target : '乔布斯',	 weight : 8},
                    {source : '龙-韦恩9', target : '乔布斯',	 weight : 9},
                    {source : '龙-韦恩10', target : '乔布斯',	 weight : 10},
                    {source : '龙-韦恩11', target : '乔布斯',	 weight : 11},
                    {source : '龙-韦恩12', target : '乔布斯',	 weight : 12},
                    {source : '龙-韦恩13', target : '乔布斯',	 weight : 13},
                    {source : '龙-韦恩14', target : '乔布斯',	 weight : 14},
                    {source : '龙-韦恩15', target : '乔布斯',	 weight : 15},
                    {source : '龙-韦恩16', target : '乔布斯',	 weight : 16},
                    {source : '龙-韦恩17', target : '乔布斯',	 weight : 17},
                    {source : '龙-韦恩18', target : '乔布斯',	 weight : 18},
                    {source : '龙-韦恩19', target : '乔布斯',	 weight : 19},
                    {source : '龙-韦恩20', target : '乔布斯',	 weight : 20},
                    {source : '龙-韦恩21', target : '乔布斯',	 weight : 21},
                    {source : '龙-韦恩22', target : '乔布斯',	 weight : 22},
                    {source : '龙-韦恩23', target : '乔布斯',	 weight : 23},
                    {source : '龙-韦恩24', target : '乔布斯',	 weight : 24},
                    {source : '龙-韦恩25', target : '乔布斯',	 weight : 25},
                    {source : '龙-韦恩26', target : '乔布斯',	 weight : 26},
                    {source : '龙-韦恩27', target : '乔布斯',	 weight : 27},
                    {source : '龙-韦恩28', target : '乔布斯',	 weight : 28},
                    {source : '龙-韦恩29', target : '乔布斯',	 weight : 29},
                    {source : '龙-韦恩30', target : '乔布斯',	 weight : 30},
                    {source : '龙-韦恩31', target : '乔布斯',	 weight : 31},
                    {source : '龙-韦恩32', target : '乔布斯',	 weight : 32},
                    {source : '龙-韦恩33', target : '乔布斯',	 weight : 33},
                    {source : '龙-韦恩34', target : '乔布斯',	 weight : 34},
                    {source : '龙-韦恩35', target : '乔布斯',	 weight : 35},
                    {source : '龙-韦恩36', target : '乔布斯',	 weight : 36},
                    {source : '龙-韦恩37', target : '乔布斯',	 weight : 37},
                    {source : '龙-韦恩38', target : '乔布斯',	 weight : 38},
                    {source : '龙-韦恩39', target : '乔布斯',	 weight : 39},
                    {source : '龙-韦恩40', target : '乔布斯',	 weight : 40},
                    {source : '龙-韦恩41', target : '乔布斯',	 weight : 41},
                    {source : '龙-韦恩42', target : '乔布斯',	 weight : 42},
                    {source : '龙-韦恩43', target : '乔布斯',	 weight : 43},
                    {source : '龙-韦恩44', target : '乔布斯',	 weight : 44},
                    {source : '龙-韦恩45', target : '乔布斯',	 weight : 45},
                    {source : '龙-韦恩46', target : '乔布斯',	 weight : 46},
                    {source : '龙-韦恩47', target : '乔布斯',	 weight : 47},
                    {source : '龙-韦恩48', target : '乔布斯',	 weight : 48},
                    {source : '龙-韦恩49', target : '乔布斯',	 weight : 49},
                    {source : '龙-韦恩50', target : '乔布斯',	 weight : 50},
                    {source : '龙-韦恩51', target : '乔布斯',	 weight : 51},
                    {source : '龙-韦恩52', target : '乔布斯',	 weight : 52},
                    {source : '龙-韦恩53', target : '乔布斯',	 weight : 53},
                    {source : '龙-韦恩54', target : '乔布斯',	 weight : 54},
                    {source : '龙-韦恩55', target : '乔布斯',	 weight : 55},
                    {source : '龙-韦恩56', target : '乔布斯',	 weight : 56},
                    {source : '龙-韦恩57', target : '乔布斯',	 weight : 57},
                    {source : '龙-韦恩58', target : '乔布斯',	 weight : 58},
                    {source : '龙-韦恩59', target : '乔布斯',	 weight : 59},
                    {source : '龙-韦恩60', target : '乔布斯',	 weight : 60},
                    {source : '龙-韦恩61', target : '乔布斯',	 weight : 61},
                    {source : '龙-韦恩62', target : '乔布斯',	 weight : 62},
                    {source : '龙-韦恩63', target : '乔布斯',	 weight : 63},
                    {source : '龙-韦恩64', target : '乔布斯',	 weight : 64},
                    {source : '龙-韦恩65', target : '乔布斯',	 weight : 65},
                    {source : '龙-韦恩66', target : '乔布斯',	 weight : 66},
                    {source : '龙-韦恩67', target : '乔布斯',	 weight : 67},
                    {source : '龙-韦恩68', target : '乔布斯',	 weight : 68},
                    {source : '龙-韦恩69', target : '乔布斯',	 weight : 69},
                    {source : '龙-韦恩70', target : '乔布斯',	 weight : 70},
                    {source : '龙-韦恩71', target : '乔布斯',	 weight : 71},
                    {source : '龙-韦恩72', target : '乔布斯',	 weight : 72},
                    {source : '龙-韦恩73', target : '乔布斯',	 weight : 73},
                    {source : '龙-韦恩74', target : '乔布斯',	 weight : 74},
                    {source : '龙-韦恩75', target : '乔布斯',	 weight : 75},
                    {source : '龙-韦恩76', target : '乔布斯',	 weight : 76},
                    {source : '龙-韦恩77', target : '乔布斯',	 weight : 77},
                    {source : '龙-韦恩78', target : '乔布斯',	 weight : 78},
                    {source : '龙-韦恩79', target : '乔布斯',	 weight : 79},
                    {source : '龙-韦恩80', target : '乔布斯',	 weight : 80},
                    {source : '龙-韦恩81', target : '乔布斯',	 weight : 81},
                    {source : '龙-韦恩82', target : '乔布斯',	 weight : 82},
                    {source : '龙-韦恩83', target : '乔布斯',	 weight : 83},
                    {source : '龙-韦恩84', target : '乔布斯',	 weight : 84},
                    {source : '龙-韦恩85', target : '乔布斯',	 weight : 85},
                    {source : '龙-韦恩86', target : '乔布斯',	 weight : 86},
                    {source : '龙-韦恩87', target : '乔布斯',	 weight : 87},
                    {source : '龙-韦恩88', target : '乔布斯',	 weight : 88},
                    {source : '龙-韦恩89', target : '乔布斯',	 weight : 89},
                    {source : '龙-韦恩90', target : '乔布斯',	 weight : 90},
                    {source : '龙-韦恩91', target : '乔布斯',	 weight : 91},
                    {source : '龙-韦恩92', target : '乔布斯',	 weight : 92},
                    {source : '龙-韦恩93', target : '乔布斯',	 weight : 93},
                    {source : '龙-韦恩94', target : '乔布斯',	 weight : 94},
                    {source : '龙-韦恩95', target : '乔布斯',	 weight : 95},
                    {source : '龙-韦恩96', target : '乔布斯',	 weight : 96},
                    {source : '龙-韦恩97', target : '乔布斯',	 weight : 97},
                    {source : '龙-韦恩98', target : '乔布斯',	 weight : 98},
                    {source : '龙-韦恩99', target : '乔布斯',	 weight : 99},
                    {source : '龙-韦恩100', target : '乔布斯',	 weight : 100},

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