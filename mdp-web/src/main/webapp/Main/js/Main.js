/**
 * Created by laiqin on 2017/1/9.
 */

/*$(function () {
    $('#myTab li:eq(1) a').tab('show')
}) */  // 按序号来选择active窗口

var xTime = [];     //横坐标时间   移动语音业务
var yTime = [];     //纵坐标时间
var zTime = [];     //通话时间

var xTimeGPRS = [];     //横坐标时间   GPRS业务
var yTimeGPRS = [];     //纵坐标时间
var zTimeGPRS = [];     //通话时间

var initFlag = false;   //false:chart还未初始化  true:图像已经初始化
var changeFlag = false;    //判断全量->关联

window.onload = function () {
    //窗口大小调整
    formResize();

    var burger = $('.burger');
    burger.on('click',function () {
        burger.toggleClass('burger--close');
        $("#pills").slideUp(800,function () {
            
        })
    })
};

function formResize() {
    $("#mainalladta").height($(window).height()-170);
    $("#relationdata").height($(window).height()-170);
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

//显示对应页面
function changeTabs(flag , num) {
    if (flag == 1) {
        $(function () {
            $('#myTab li:eq(1) a').tab('show')
            $('#chartTabs li:eq(2) a').tab('show');
            changeTab(3)
            if (num == 1) relationdata.window.chartRelationData(num);
            //relationdata.window.returnInitFlag() == false ? relationdata.window.chartRelationData() : relationdata.window.moreData();
        });
    }
}

//点击tab标签页显示对应的form表单
function changeTab(tabnum) {
    switch (tabnum){
        case 1:
            $("#childTabOne").slideDown(800, function () {
            });
            $("#childTabTwo").slideUp(800, function () {
            });
            $("#childTabThree").slideUp(800, function () {
            });
            $("#childTabFour").slideUp(800, function () {
            });
            $("#childTabFive").slideUp(800, function () {
            });
            break;
        case 2:
            $("#childTabOne").slideUp(800, function () {
            });
            $("#childTabTwo").slideDown(800, function () {
                //alldata.window.formInit();
                //document.getElementById("childTabTwo").style.visibility = '';
            });
            $("#childTabThree").slideUp(800, function () {
            });
            $("#childTabFour").slideUp(800, function () {
            });
            $("#childTabFive").slideUp(800, function () {
            });
            break;
        case 3:
            $("#childTabOne").slideUp(800, function () {
            });
            $("#childTabTwo").slideUp(800, function () {
            });
            $("#childTabThree").slideDown(800, function () {
               // document.getElementById("childTabThree").style.visibility = '';
            });
            $("#childTabFour").slideUp(800, function () {
            });
            $("#childTabFive").slideUp(800, function () {
            });
            break;
        case 4:
            $("#childTabOne").slideUp(800, function () {
            });
            $("#childTabTwo").slideUp(800, function () {
            });
            $("#childTabThree").slideUp(800, function () {
            });
            $("#childTabFour").slideDown(800, function () {
                //document.getElementById("childTabFour").style.visibility = '';
            });
            $("#childTabFive").slideUp(800, function () {
            });
            break;
        case 5:
            $("#childTabOne").slideUp(800, function () {
            });
            $("#childTabTwo").slideUp(800, function () {
            });
            $("#childTabThree").slideUp(800, function () {
            });
            $("#childTabFour").slideUp(800, function () {
            });
            $("#childTabFive").slideDown(800, function () {
                //document.getElementById("childTabFive").style.visibility = '';
            });
            break;
    }
}
