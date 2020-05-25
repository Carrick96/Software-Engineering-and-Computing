$(function () {
    onC();//作业导航  点击切换板块
    tool();// 工具 点击事件
    nitice();// 通知 点击事件
    info();// 通知 点击事件
    leftMenu();//左滑出菜单
    courseList();// JQuery 左侧滑动 课程遍历
    JQergodichomework();//JQuery遍历  个人作业
    ajaxTransmitCourseId();//向ajax传递courseId
})
//作业导航  点击切换板块
function onC() {
    var Alla = document.getElementById('banner1-nav').getElementsByTagName('a');
    var i = 0;
    for (i = 0; i < Alla.length; i++) {
        Alla[i].index = i;
        Alla[i].onclick = function () {
            for (i = 0; i < Alla.length; i++) {
                Alla[i].className = '';
            }
            this.className = 'active';
        }
    }
}
// 工具 点击事件
function tool() {
    var Tool = document.getElementById('tool');
    var Ultool = document.getElementById('ul-tool');
    Tool.onclick = function (aaa) {
        if (Ultool.style.display == 'none') {
            Ultool.style.display = 'block';
        } else {
            Ultool.style.display = 'none';
        }
    }
}
// 通知 点击事件
function nitice() {
    var Nitice = document.getElementById('notice');
    var Divnotice = document.getElementById('divnotice');
    Nitice.onclick = function (bbb) {
        if (Divnotice.style.display == 'none') {
            Divnotice.style.display = 'block';
        } else {
            Divnotice.style.display = 'none';
        }
    }
}
// 个人信息  点击事件
function info() {
    var Info = document.getElementById('info');
    var Userinfo = document.getElementById('userinfo');
    Info.onclick = function (ccc) {
        if (Userinfo.style.display == 'none') {
            Userinfo.style.display = 'block';
        } else {
            Userinfo.style.display = 'none';
        }
    }
}
//左滑出菜单
function leftMenu() {
    var divLeftMenu = document.getElementById("div-left-menu")//获取菜单div
    var liNavItem = document.getElementById("li-nav-item")//获取点击按钮
    $('#li-nav-item').click(function () {
        $('#div-left-menu').css('display','block');
        if (divLeftMenu.style.display == 'block'){
            $('#li-nav-item').click(function(event) {
                event.stopPropagation();
            })
            $(document).click(function(){
                $('#div-left-menu').css('display','none');
            })
            $('#div-left-menu').click(function(event) {
                event.stopPropagation();
            })
        }
    });
}


//向ajax传递courseId
function ajaxTransmitCourseId() {
    let a = GetRequest();
    let courseId = a['courseId'];
    console.log("courseId:" + courseId);
    $.ajax({
        url: "接口",
        async: true,
        cache: false,
        type: "get",
        dataType: 'json',
        data: {
            courseId: courseId
        },
        beforeSend: function (xmlHttp) {
            xmlHttp.setRequestHeader("If-Modified-Since", "0");
            xmlHttp.setRequestHeader("Cache-Control", "no-cache");
        },
        success: function (data) {
            if (data.success) {
                console.log("success");

            } else {
                alert('传递失败！' + data.errMsg);
            }

        }
    });
}
//获取 curseId
function GetRequest() {
    var url = location.search; //获取url中"?"符后的字串
    var theRequest = {};
    if (url.indexOf("?") !== -1) {
        var str = url.substr(1);
        strs = str.split("&");
        for (var i = 0; i < strs.length; i++) {
            theRequest[strs[i].split("=")[0]] = decodeURIComponent(strs[i].split("=")[1]);
        }
    }
    return theRequest;
}

//JQuery 遍历作业界面  标题
function title() {
    $.getJSON("studenthomework/getcourse", function(data) {
        if (data.success) {
            let course = data.course;
            $('#title-courseName').html(course.courseName);//课程名
            $('#title-courseClass').html(course.className);//班级
            $('#title-courseId').html(course.courseId);//课程码
            $('#courseStudent').html(data.stuCount);//学生人数
        }
    });
}
// JQuery 左侧滑动 课程遍历
function courseList() {
    $.getJSON("/studentcourse/getcourselist", function(data) {
        if (data.success) {
            let leftCourseList = data.courseList;
            var tempHtml = '';
            console.log(leftCourseList);
            $.each(leftCourseList,function (n,value) {
                tempHtml += '<a href="/studenthomework?courseId=' + value.courseId + '">' + value.courseName + '';
                tempHtml += '</a>';
            });
            // 遍历形式 在div中 向前遍历
            $("#courseList").prepend(tempHtml);
        }
    });

}
//JQuery遍历  个人作业
/*
function JQergodichomework() {
    $.getJSON("/studenthomework/getstuhomeworklist", function(data) {
        if (data.success) {
            let studentHomeworkList = data.homeworkList;
            console.log("条数是： " + studentHomeworkList.size());
            var tempHtml = '';
            console.log(studentHomeworkList);
            $.each(studentHomeworkList,function (n,value) {
                tempHtml += '<div class="homework-box">';
                tempHtml += '<div class="homework-box-mian">';
                tempHtml += '<div class="homework-box-header">'
                    + '<span class="homework-title-work">个人作业</span>'
                    + '<span id="homework-release-data">'+ value.data +'</span>'
                    + '<span id="homework-release-time">'+ value.time +'</span>';
                tempHtml += '</div>';
                tempHtml += '<div class="homework-box-body">'
                    + '<div class="homework-body">'
                    + '<h3>'
                    + '<a href="#" id="homework-body-title">'+ value.title +'</a>'
                    + '</h3>'
                    + '<div class="homework-body-content">'
                    + '<div class="homework-body-content-p" id="homework-body-content-p">'+ value.content +''
                    +'<a href="#" id="view-text">查看全文></a>'
                    + '</div>'
                    + '</div>'
                    + '</div>'
                    + '<div class="homework-body-but">'
                    + '<a class="upload-work" id="upload-work" >上传作业</button>'
                    + '</div>';
                tempHtml += '</div>';
                tempHtml += '<div class="foot">'
                    + '<img src="../resources/img/assets/ic_card_time@2x.png">'
                    + '<p class="end-da">截止日期：</p>'
                    + '<span class="end-data">'+ value.endData +'</span>'
                    + '<b class="end-time">'+ value.endTime +'</b>'
                    + '<a href="#">'
                    + '<p class="discuss-num">'+ value.discuss +'</p>'
                    + '<p class="discuss-tit">条讨论</p>'
                    + '</a>';
                tempHtml += '</div>';
                tempHtml += '</div>';
                tempHtml += '</div>';
            });

            // 遍历形式 在div中 向前遍历
            $("#div-homework").prepend(tempHtml);
        }
    });
}*/
function JQergodichomework() {
    $.getJSON("接口", function(data) {
        if (data.success) {
            let studenthomeworkList = data.courseList;
            var tempHtml = '';
            console.log(studenthomeworkList);
            $.each(studenthomeworkList,function (n,value) {
                tempHtml += '<div class="homework-box">';
                tempHtml += '<div class="homework-box-mian">';
                tempHtml += '<div class="homework-box-header">'
                    + '<span class="homework-title-work">个人作业</span>'
                    + '<span id="homework-release-data">'+ value.modifyDate +'</span>'
                    + '<span id="homework-release-time">'+  +'</span>';
                tempHtml += '</div>';
                tempHtml += '<div class="homework-box-body">'
                    + '<div class="homework-body">'
                    + '<h3>'
                    + '<a href="/studentuphomework?" id="homework-body-title">'+ value.title +'</a>'
                    + '</h3>'
                    + '<div class="homework-body-content">'
                    + '<div class="homework-body-content-p" id="homework-body-content-p">'+ value.detail +''
                    +'<a href="#" id="view-text">查看全文></a>'
                    + '</div>'
                    + '</div>'
                    + '</div>'
                    + '<div class="homework-body-but">'
                    + '<a class="upload-work" id="upload-work" >上传作业</button>'
                    + '</div>';
                tempHtml += '</div>';
                tempHtml += '<div class="foot">'
                    + '<img src="../resources/img/assets/ic_card_time@2x.png">'
                    + '<p class="end-da">截止日期：</p>'
                    + '<span class="end-data">'+ value.deadline +'</span>'
                    + '<b class="end-time">'+  +'</b>'
                    + '<a href="#">'
                    + '<p class="discuss-num">'+  +'</p>'
                    + '<p class="discuss-tit">条讨论</p>'
                    + '</a>';
                tempHtml += '</div>';
                tempHtml += '</div>';
                tempHtml += '</div>';
            });

            // 遍历形式 在div中 向前遍历
            $("#div-homework").prepend(tempHtml);
        }
    });
}
