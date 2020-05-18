$(function () {
    var createCourseUrl = "/teacgercourse/createcourse";
    var modifyUrl = "/teachercourse/modifycourse";


    let establishCourse = document.getElementById("establish-course");     //创建弹出框
    let inputEstablishCourseName = document.getElementById("input-establish-course-name");//课程名
    let inputEstablishClassName = document.getElementById("input-establish-class-name");//班级名
    let establishCourseYearBox = document.getElementById("establish-course-year-box"); //得到 学年  select
    let establishCourseYearBoxopts = establishCourseYearBox.getElementsByTagName("option");//得到学年数组option
    let establishCourseStudyDataBox = document.getElementById("establish-course-studyData-box"); //得到 学期  select
    let establishCourseStudyDataBoxpts = establishCourseStudyDataBox.getElementsByTagName("option");//得到学期数组option

//更多  点击退课
//     function moreDropOutA(courseId){
//
//     }


    $('#establish-course-sure').click(function () {
        let establish = {};
        establish.courseName = $("#input-establish-course-name").val();
        establish.className = $("#input-establish-class-name").val();
        establish.year = $("#establish-course-year-box").find("option:selected").text();
        establish.term = $("#establish-course-studyData-box").find("option:selected").text();
        let extraInfor = document.getElementsByName("extra-infor"); //额外信息
        // 生成表单对象，用于接收参数并传递给后台
        let formData = new FormData();
        formData.append("establishStr", JSON.stringify(establish));
        $.ajax({
            url: createCourseUrl,
            type: 'POST',
            data: formData,
            contentType: false,
            processData: false,
            async: true,
            cache: false,
            beforeSend: function (xmlHttp) {
                xmlHttp.setRequestHeader("If-Modified-Since", "0");
                xmlHttp.setRequestHeader("Cache-Control", "no-cache");
            },
            success: function (data) {
                if (data.success) {
                    bg.style.position = "";
                    establishCourse.style.display = "none";
                    inputEstablishCourseName.value = "";//课程名
                    inputEstablishClassName.value = "";//班级名
                    establishCourseYearBoxopts[9].selected = true; //学年恢复默认
                    establishCourseStudyDataBoxpts[1].selected = true; //学期恢复默认
                    for (let i = 0; i < extraInfor.length; i++) {  //额外信息恢复默认
                        extraInfor[i].checked = false;
                    }
                    alert('提交成功！');
                    window.location.reload();

                } else {
                    alert('提交失败！' + data.errMsg);
                    inputEstablishCourseName.value = "";
                    inputEstablishClassName.value = "";//班级名
                    establishCourseYearBoxopts[9].selected = true; //学年恢复默认
                    establishCourseStudyDataBoxpts[1].selected = true; //学期恢复默认
                    for (let i = 0; i < extraInfor.length; i++) {  //额外信息恢复默认
                        extraInfor[i].checked = false;
                    }
                }
            }
        });
    });

    //编辑课程 修改按钮
    $("#edit-course-sure").click(function () {


        let courseid = $('#edit-course-sure').attr('title');
        let bg = document.getElementById("bg");
        let divContentEdit = document.getElementById("div-content-edit");//点击创建课程
        let editCourse = document.getElementById("edit-course");     //创建弹出框
        // 重置数据
        let inputEditCourseName = document.getElementById("input-edit-course-name");//课程名
        let inputEditClassName = document.getElementById("input-edit-class-name");//班级名
        let editCourseYearBox = document.getElementById("edit-course-year-box"); //得到 学年  select
        let editCourseYearBoxopts = editCourseYearBox.getElementsByTagName("option");//得到学年数组option
        let editCourseStudyDataBox = document.getElementById("edit-course-studyData-box"); //得到 学期  select
        let editCourseStudyDataBoxpts = editCourseStudyDataBox.getElementsByTagName("option");//得到学期数组option
        let editExtraInfor = document.getElementsByName("edit-extra-infor"); //额外信息
        let edit = {};
        edit.courseId = courseid; //课程id
        edit.courseName = $("#input-edit-course-name").val();
        edit.className = $("#input-edit-class-name").val();
        //  edit.year = $("#edit-course-year-box").find("option:selected").text();
        // 生成表单对象，用于接收参数并传递给后台
        let editData = new FormData();
        editData.append("editStr", JSON.stringify(edit));
        $.ajax({
            url: "/teacgercourse/modifycourse",
            type: 'POST',
            data: editData,
            contentType: false,
            processData: false,
            cache: false,
            async: true,
            beforeSend: function (xmlHttp) {
                xmlHttp.setRequestHeader("If-Modified-Since", "0");
                xmlHttp.setRequestHeader("Cache-Control", "no-cache");
            },
            success: function (data) {
                if (data.success) {
                    bg.style.position = "";
                    editCourse.style.display = "none";
                    inputEditCourseName.value = "";//课程名
                    inputEditClassName.value = "";//班级名
                    editCourseYearBoxopts[9].selected = true; //学年恢复默认
                    editCourseStudyDataBoxpts[1].selected = true; //学期恢复默认
                    for (let i = 0; i < editExtraInfor.length; i++) {  //额外信息恢复默认
                        editExtraInfor[i].checked = false;
                    }
                    alert('提交成功！');
                    window.location.reload();

                } else {
                    alert('提交失败！' + data.errMsg);
                    inputEditCourseName.value = "";//课程名
                    inputEditClassName.value = "";//班级名
                    editCourseYearBoxopts[9].selected = true; //学年恢复默认
                    editCourseStudyDataBoxpts[1].selected = true; //学期恢复默认
                    for (let i = 0; i < editExtraInfor.length; i++) {  //额外信息恢复默认
                        editExtraInfor[i].checked = false;
                    }
                }
            }
        });
    })


    let bg = document.getElementById("bg");
    let addCoursePopup = document.getElementById("add-course-popup");
    let addCoursePopupInput = document.getElementById("add-course-popup-input");
    $('#add-course-popup-but-sure').click(function () {
        let extrCode = $('#add-course-popup-input').val(); //获取输入的  课程码
        $.ajax({
            url: "/teacgercourse/joincourse",
            async: true,
            cache: false,
            type: "post",
            dataType: 'json',
            data: {
                extrCode: extrCode
            },
            beforeSend: function (xmlHttp) {
                xmlHttp.setRequestHeader("If-Modified-Since", "0");
                xmlHttp.setRequestHeader("Cache-Control", "no-cache");
            },
            success: function (data) {
                if (data.success) {
                    //  $.toast('登录成功！');
                    //后续操作
                    alert("添加成功");
                    bg.style.position = "";
                    addCoursePopup.style.display = "none";
                    addCoursePopupInput.innerText = "";
                    window.location.reload();
                } else {
                    alert("添加失败，原因是" + data.errMsg);
                }
            }
        })
    });

});

// 课程  点击  置顶
function courseTop(id) {
    console.log("置顶" + id);
    $.ajax({
        url: "/teacgercourse/topcourse",
        async: true,
        cache: false,
        type: "get",
        dataType: 'json',
        data: {
            id: id,
        },
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
    console.log("取消置顶" + id);
    $.ajax({
        url: "/teacgercourse/untopcourse",
        async: true,
        cache: false,
        type: "get",
        dataType: 'json',
        data: {
            id: id,
        },
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

// 更多  点击归档
function moreArchiveA(id) {
    console.log("更多 归档" + id);
    $.ajax({
        url: '/teacgercourse/archivecourse',
        async: true,
        cache: false,
        type: "get",
        dataType: 'json',
        data: {
           id: id,
        },
        success: function (data) {
            if (data.success) {
                alert('归档成功！');
                window.location.reload();
            } else {
                alert('归档失败！' + data.errMsg);
            }
        }
    })
}

// 更多  点击退课
function moreDropOutA(courseId) {
    console.log("更多 退课" + courseId);
    $.ajax({
        url: "/teacgercourse/deletecourse",
        async: true,
        cache: false,
        type: "get",
        dataType: 'json',
        data: {
            courseId: courseId,
        },
        success: function (data) {
            if (data.success) {
                alert('删除成功！');
                window.location.reload();
            } else {
                alert('删除失败！' + data.errMsg);
                /* window.location.reload();*/
            }
        }
    })
}

//归档  点击恢复
function fileMoreBoxRecovery(id) {
    $.ajax({
        url: "/teacgercourse/unarchivecourse",
        async: true,
        cache: false,
        type: "get",
        dataType: 'json',
        data: {
            id: id,
        },
        success: function (data) {
            if (data.success) {
                alert('恢复成功！');
                /* window.location.reload();*/
            } else {
                alert('恢复失败！' + data.errMsg);
                /*  window.location.reload();*/
            }
        }
    })
}

//归档  退课
function fileMoreBoxDel(courseId) {
    console.log("归档 退课" + courseId);
    $.ajax({
        url: "/teacgercourse/deletecourse",
        async: true,
        cache: false,
        type: "get",
        dataType: 'json',
        data: {
            courseId: courseId,
        },
        success: function (data) {
            if (data.success) {
                alert('删除成功！');
                window.location.reload();
            } else {
                alert('删除失败！' + data.errMsg);
                window.location.reload();
            }
        }
    })
}