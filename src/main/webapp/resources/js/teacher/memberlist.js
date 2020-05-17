/*
$(function () {
    ajaxTransmitCourseId();//向ajax传递courseId
    ergodicTeacher();//遍历教师
    ergodicTeacher();//遍历学生
});

// 遍历教师
function ergodicTeacher() {
    $.getJSON("地址", function (data) {
        if (data.success) {
            let teacherMemberList = data.teacherMemberList;
            var teacherHtml = "";
            $.each(teacherMemberList, function (n, value) {
                teacherHtml += '<div class="teacher">'
                    + '<img src="../img/seeStudent/34.png" class="touxiang1">'
                    + '<span class="span4">' + value.teacherName + '</span>'
                    + '<span class="span5">' + value.teacherId + '&nbsp;&nbsp;&nbsp;（管理员）</span>'
                    + '<a href="#">'
                    + '<img src="../img/seeStudent/opt1.png" class="opt1">'
                    + '</a>'
                    + '<a href="#">'
                    + '<img src="../img/seeStudent/sixin.png" class="sixin">'
                    + '</a>'
                    + '</div>'
            });
            // 遍历形式 在div中 向前遍历
            $("#teacherBox").prepend(teacherHtml);
        }
    });
}

// 遍历学生
function ergodicTeacher() {
    $.getJSON("/teacgercourse/coursemember", function (data) {
        if (data.success) {
            let studentMemberList = data.studentMemberList;
            var studentHtml = "";
            $.each(studentMemberList, function (n, value) {
                studentHtml += '<tr>'
                    + '<td class="checkBox">'
                    + '<input type="checkbox" name="gg" class="tableCheck">'
                    + '</td>'
                    + '<td>'
                    + '<img src="../img/seeStudent/34.png" class="touxiang2">'
                    + '</td>'
                    + '<td>' + value.userId + '</td>'
                    + '<td>' + value.userName + '</td>'
                    + '<td>' + value.account + '</td>'
                    + '<td>' + value.time + '</td>'
                    + '<td>'
                    + '<a href="#">'
                    + '<img src="../img/seeStudent/opt1.png" class="opt1">'
                    + '</a>'
                    + '<a href="#">'
                    + '<img src="../img/seeStudent/sixin.png" class="sixin">'
                    + '</a>'
                    + ' </td>'
                    + '</tr>'
            });
            // 遍历形式 在div中 向前遍历
            $("#studentBox").prepend(studentHtml);
        }
    });
}

//向ajax传递courseId
function ajaxTransmitCourseId() {
    let a = GetRequest();
    let courseId = a['courseId'];
    console.log("courseId:" + courseId);
    $.ajax({
        url: "/teacgercourse/getcourseId",
        async: true,
        cache: false,
        type: "post",
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
                //   window.location.reload();
            } else {
                alert('传递失败！' + data.errMsg);
            }
        }
    });
}

//获取 curseId
function GetRequest() {
    const url = location.search; //获取url中"?"符后的字串
    const theRequest = {};
    if (url.indexOf("?") !== -1) {
        const str = url.substr(1);
        strs = str.split("&");
        for (let i = 0; i < strs.length; i++) {
            theRequest[strs[i].split("=")[0]] = decodeURIComponent(strs[i].split("=")[1]);
        }
    }
    return theRequest;
}*/
$(function () {
    ajaxTransmitCourseId();//向ajax传递courseId
    ergodicTeacher();//遍历教师
    ergodicStudent();//遍历学生
});

//遍历教师
function ergodicTeacher() {
    $.getJSON("/teacgercourse/coursememberofteacher", function (data) {
        if (data.success) {
            let teacherMemberList = data.teacherMemberList;
            var teacherHtml = "";
            $.each(teacherMemberList, function (n, value) {
                teacherHtml += '<div class="teacher">'
                    + '<img src="../resources/img/seeStudent/34.png" class="touxiang1">'
                    + '<span class="span4">' + value.username + '</span>'
                    + '<span class="span5">' + value.userId + '&nbsp;&nbsp;&nbsp;（管理员）</span>'
                    + '<a href="#">'
                    + '<img src="../resources/img/seeStudent/opt1.png" class="opt1">'
                    + '</a>'
                    + '<a href="#">'
                    + '<img src="../resources/img/seeStudent/sixin.png" class="sixin">'
                    + '</a>'
                    + '</div>'
            });
            // 遍历形式 在div中 向前遍历
            $("#teacherBox").prepend(teacherHtml);
        }
    });
}

//遍历学生
function ergodicStudent() {
    $.getJSON("/teacgercourse/coursememberofstudent", function (data) {
        if (data.success) {
            let studentMemberList = data.studentMemberList;
            console.log("列表是："+studentMemberList);
            var studentHtml = "";
            $.each(studentMemberList, function (n, value) {
                studentHtml += '<tr>'
                    + '<td class="checkBox">'
                    + '<input type="checkbox" name="gg" class="tableCheck">'
                    + '</td>'
                    + '<td>'
                    + '<img src="../resources/img/seeStudent/34.png" class="touxiang2">'
                    + '</td>'
                    + '<td>' + value.userId + '</td>'
                    + '<td>' + value.username + '</td>'
                    + '<td>' + value.account + '</td>'
                    + '<td>' + value.createTime + '</td>'
                    + '<td>'
                    + '<a href="#">'
                    + '<img src="../resources/img/seeStudent/opt1.png" class="opt1">'
                    + '</a>'
                    + '<a href="#">'
                    + '<img src="../resources/img/seeStudent/sixin.png" class="sixin">'
                    + '</a>'
                    + ' </td>'
                    + '</tr>'
            });
            // 遍历形式 在div中 向前遍历
            $("#studentBox").prepend(studentHtml);
        }
    });
}

//向ajax传递courseId
function ajaxTransmitCourseId() {
    let a = GetRequest();
    let courseId = a['courseId'];
    console.log("courseId:" + courseId);
    $.ajax({
        url: "/teacgercourse/getcoursebyid",
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