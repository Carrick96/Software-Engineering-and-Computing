$(function () {
    addCourseSureBut();// 添加课程框   确认按钮

})
// 加入课程框   确认按钮
function addCourseSureBut() {
    let bg = document.getElementById("bg");
    let addCoursePopup = document.getElementById("add-course-popup");
    let addCoursePopupInput = document.getElementById("add-course-popup-input");
    $('#add-course-popup-but-sure').click(function () {
        let extrCode = $('#add-course-popup-input').val(); //获取输入的  课程码
        $.ajax({
            url: "/studentcourse/joincourse",
            async: true,
            cache: false,
            type: "post",
            dataType: 'json',
            data: {
                extrCode: extrCode
            },
            success: function (data) {
                if (data.success) {
                    bg.style.position = "fixed";
                    addCoursePopup.style.display = "none";
                    addCoursePopupInput.innerText = "";
                    alert("加入课程成功");
                    window.location.reload();
                }
            }
        });
    })
}
//课程  更多  点击退课
function moreDropOutA(courseId){
    // console.log(courseId);
    $.ajax({
        url: "",
        type: 'POST',
        data: JSON.stringify({            //发送到服务器的数据
            courseId: courseId,
        }),
        contentType: false,
        processData: false,
        cache: false,
        success: function (data) {
            if (data.success) {
                console.log("aa");
                alert('删除成功！');
                window.location.reload();
            } else {
                alert('删除失败！' + data.errMsg);
                window.location.reload();
            }
        }
    })
}
//课程  更多  点击归档
function moreArchiveA(courseId){
    $.ajax({
        url: "",
        type: 'POST',
        data: JSON.stringify({            //发送到服务器的数据
            courseId: courseId,
        }),
        contentType: false,
        processData: false,
        cache: false,
        success: function (data) {
            if (data.success) {
                console.log("aa");
                alert('归档成功！');
                window.location.reload();
            } else {
                alert('归档失败！' + data.errMsg);
                window.location.reload();
            }
        }
    })
}
// 归档 更多  恢复
function fileMoreBoxRecovery(courseId) {

}
//  归档 更多  点击退课
function fileMoreBoxDel(courseId){
    console.log(courseId);
    $.ajax({
        url: "/teacgercourse/deletecourse",
        type: 'POST',
        data: JSON.stringify({            //发送到服务器的数据
            courseId: courseId,
        }),
        contentType: false,
        processData: false,
        cache: false,
        success: function (data) {
            if (data.success) {
                console.log("aa");
                alert('删除成功！');
                window.location.reload();
            } else {
                alert('删除失败！' + data.errMsg);
                window.location.reload();
            }
        }
    })
}
// 课程  点击  置顶
function courseTop(id) {
    $.ajax({
        url: "",
        type: 'POST',
        data: JSON.stringify({            //发送到服务器的数据
            置顶: 置顶,
        }),
        contentType: false,
        processData: false,
        cache: false,
        success: function (data) {
            if (data.success) {
                alert('置顶成功！');
                window.location.reload();
            } else {
                alert('置顶失败！' + data.errMsg);
            }
        }
    })
}
// 课程  点击  取消置顶
function courseUnpin(id) {
    $.ajax({
        url: "",
        type: 'POST',
        data: JSON.stringify({            //发送到服务器的数据
            取消置顶: 取消置顶,
        }),
        contentType: false,
        processData: false,
        cache: false,
        success: function (data) {
            if (data.success) {
                alert('取消置顶成功！');
                window.location.reload();
            } else {
                alert('取消置顶失败！' + data.errMsg);
            }
        }
    })
}
//点击课程名 跳转
function olikCourseName(courseId) {

}
//点击最后一个作业 跳转
function workAFirst(courseId) {

}
//点击倒数第二个作业 跳转
function workASecond(courseId) {

}
