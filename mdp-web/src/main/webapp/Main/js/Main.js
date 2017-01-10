/**
 * Created by laiqin on 2017/1/9.
 */

window.onload = function () {
    $(function(){
        $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
            debugger
            // 获取已激活的标签页的名称
            var activeTab = $(e.target).text();
            // 获取前一个激活的标签页的名称
            var previousTab = $(e.relatedTarget).text();
            if(activeTab == '关系图'){
                chartInit();
                chartInit1();
            }else if(activeTab == '统计'){
                chartInit2();
                chartInit3();
            }
        });
    });
}


function chartInit() {
    var myChart = echarts.init(document.getElementById('chartRelation'));
    var option = {
        tooltip: {
            show: true
        },
        legend: {
            data:['销量']
        },
        xAxis : [
            {
                type : 'category',
                data : ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        series : [
            {
                "name":"销量",
                "type":"bar",
                "data":[5, 20, 40, 10, 10, 20]
            }
        ]
    };

    // 为echarts对象加载数据
    myChart.setOption(option);
}

function chartInit1() {
    var myChart = echarts.init(document.getElementById('chartRelation1'));
    var option = {
        tooltip: {
            show: true
        },
        legend: {
            data:['销量']
        },
        xAxis : [
            {
                type : 'category',
                data : ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        series : [
            {
                "name":"销量",
                "type":"bar",
                "data":[5, 20, 40, 10, 10, 20]
            }
        ]
    };

    // 为echarts对象加载数据
    myChart.setOption(option);
}

function chartInit2() {
    var myChart = echarts.init(document.getElementById('chartRelation2'));
    var option = {
        tooltip: {
            show: true
        },
        legend: {
            data:['销量']
        },
        xAxis : [
            {
                type : 'category',
                data : ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        series : [
            {
                "name":"销量",
                "type":"bar",
                "data":[5, 20, 40, 10, 10, 20]
            }
        ]
    };

    // 为echarts对象加载数据
    myChart.setOption(option);
}

function chartInit3() {
    var myChart = echarts.init(document.getElementById('chartRelation3'));
    var option = {
        tooltip: {
            show: true
        },
        legend: {
            data:['销量']
        },
        xAxis : [
            {
                type : 'category',
                data : ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        series : [
            {
                "name":"销量",
                "type":"bar",
                "data":[5, 20, 40, 10, 10, 20]
            }
        ]
    };

    // 为echarts对象加载数据
    myChart.setOption(option);
}