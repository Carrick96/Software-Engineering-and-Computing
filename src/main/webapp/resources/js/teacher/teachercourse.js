$(function () {
    let Alla = document.getElementById('ul-nav-menu').getElementsByTagName('a');
    let i = 0;
    for (i = 0; i < Alla.length; i++) {
        Alla[i].index = i;
        Alla[i].onclick = function () {
            for (i = 0; i < Alla.length; i++) {
                Alla[i].className = '';
            }
            this.className = 'active';
        };
    }
    studentAddCourse();//教师添加课程、传值
    AddEstablish();//添加/创建课程 显示
    teacherEstablishCourse();// 弹出创建课程 框
})
// 工具 隐藏
let Tool = document.getElementById('tool');
let Ultool = document.getElementById('ul-tool');
Tool.onclick = function () {
    if (Ultool.style.display == 'none') {
        Ultool.style.display = 'block';
    } else {
        Ultool.style.display = 'none';
    }

}

// 通知隐藏
let Nitice = document.getElementById('notice');
let Divnotice = document.getElementById('divnotice');
Nitice.onclick = function () {
    if (Divnotice.style.display == 'none') {
        Divnotice.style.display = 'block';
    } else {
        Divnotice.style.display = 'none';
    }
}
// 个人信息隐藏
let Info = document.getElementById('info');
let Userinfo = document.getElementById('userinfo');
Info.onclick = function () {
    if (Userinfo.style.display == 'none') {
        Userinfo.style.display = 'block';
    } else {
        Userinfo.style.display = 'none';
    }
}

// 添加/创建课程  点击
function AddEstablish() {
    let divContentAddEstablish = document.getElementById("div-content-add-establish");
    let divContentAddEstablishList = document.getElementById("div-content-add-establish-list");
    divContentAddEstablish.onclick = function () {
        if (divContentAddEstablishList.style.display === 'none') {
            divContentAddEstablishList.style.display = 'block';
        } else {
            divContentAddEstablishList.style.display = 'none';
        }
    }
}

// 教师创建课程  弹出框
function teacherEstablishCourse() {
    let bg = document.getElementById("bg");
    let divContentEstablish = document.getElementById("div-content-establish");//点击创建课程
    let establishCourse = document.getElementById("establish-course");     //创建弹出框
    //  创建课程  按钮    被点击时弹出  添加课程框
    divContentEstablish.onclick = function () {
        bg.style.position = "fixed";
        establishCourse.style.display = "block"
    }
// 上层（其他课程）点击 弹出 添加课程框
    let onLevelOtherCourseCreateCourseAdd = document.getElementById("onLevel-otherCourse-create-course-add");
    onLevelOtherCourseCreateCourseAdd.onclick = function () {
        bg.style.position = "fixed";
        establishCourse.style.display = "block"
    }
// 下层（其他课程）点击 弹出 添加课程框
    let lowerLevelOtherCourseCreateCourseAdd = document.getElementById("lowerLevel-otherCourse-create-course-add");
    lowerLevelOtherCourseCreateCourseAdd.onclick = function () {
        bg.style.position = "fixed";
        establishCourse.style.display = "block"
    }

    // 重置数据
    let inputEstablishCourseName = document.getElementById("input-establish-course-name");//课程名
    let inputEstablishClassName = document.getElementById("input-establish-class-name");//班级名
    let establishCourseYearBox = document.getElementById("establish-course-year-box"); //得到 学年  select
    let establishCourseYearBoxopts = establishCourseYearBox.getElementsByTagName("option");//得到学年数组option
    let establishCourseStudyDataBox = document.getElementById("establish-course-studyData-box"); //得到 学期  select
    let establishCourseStudyDataBoxpts = establishCourseStudyDataBox.getElementsByTagName("option");//得到学期数组option
    let extraInfor = document.getElementsByName("extra-infor"); //额外信息

    //  创建课程框  取消按钮
    let establishCourseCancel = document.getElementById("establish-course-cancel")// 取消按钮 关闭弹出框
    establishCourseCancel.onclick = function () {
        bg.style.position = "";
        establishCourse.style.display = "none"
        inputEstablishCourseName.value = "";//课程名
        inputEstablishClassName.value = "";//班级名
        establishCourseYearBoxopts[9].selected = true; //学年恢复默认
        establishCourseStudyDataBoxpts[1].selected = true; //学期恢复默认
        for (let i = 0; i < extraInfor.length; i++) {  //额外信息恢复默认
            extraInfor[i].checked = false;
        }
    }
    //  创建课程框  X 关闭
    let closeEstablishCourse = document.getElementById("close-establish-course")//  X 关闭弹出框
    closeEstablishCourse.onclick = function () {
        bg.style.position = "";
        establishCourse.style.display = "none";
        inputEstablishCourseName.value = "";//课程名
        inputEstablishClassName.value = "";//班级名
        establishCourseYearBoxopts[9].selected = true; //学年恢复默认
        establishCourseStudyDataBoxpts[1].selected = true; //学期恢复默认
        for (let i = 0; i < extraInfor.length; i++) {  //额外信息恢复默认
            extraInfor[i].checked = false;
        }
    }

    // 编辑课程 重置数据
    let inputEditCourseName = document.getElementById("input-edit-course-name");//课程名
    let inputEditClassName = document.getElementById("input-edit-class-name");//班级名
    let editCourseYearBox = document.getElementById("edit-course-year-box"); //得到 学年  select
    let editCourseYearBoxopts = editCourseYearBox.getElementsByTagName("option");//得到学年数组option
    let editCourseStudyDataBox = document.getElementById("edit-course-studyData-box"); //得到 学期  select
    let editCourseStudyDataBoxpts = editCourseStudyDataBox.getElementsByTagName("option");//得到学期数组option
    let editExtraInfor = document.getElementsByName("edit-extra-infor"); //额外信息
    let editCourse = document.getElementById("edit-course");
    //  创建课程框  取消按钮
    let editCourseCancel = document.getElementById("edit-course-cancel")// 取消按钮 关闭弹出框
    editCourseCancel.onclick = function () {
        bg.style.position = "";
        editCourse.style.display = "none";
        inputEditCourseName.value = "";//课程名
        inputEditClassName.value = "";//班级名
        editCourseYearBoxopts[9].selected = true; //学年恢复默认
        editCourseStudyDataBoxpts[1].selected = true; //学期恢复默认
        for (let i = 0; i < editExtraInfor.length; i++) {  //额外信息恢复默认
            editExtraInfor[i].checked = false;
        }
    }
    //  创建课程框  X 关闭
    let closeEditCourse = document.getElementById("close-edit-course")//  X 关闭弹出框
    closeEditCourse.onclick = function () {
        bg.style.position = "";
        editCourse.style.display = "none";
        inputEditCourseName.value = "";//课程名
        inputEditClassName.value = "";//班级名
        editCourseYearBoxopts[9].selected = true; //学年恢复默认
        editCourseStudyDataBoxpts[1].selected = true; //学期恢复默认
        for (let i = 0; i < editExtraInfor.length; i++) {  //额外信息恢复默认
            editExtraInfor[i].checked = false;
        }
    }
 /*   function getCourseInfo(courseId) {
        $.getJSON(courseInfoUrl,function (data) {
            if (data.success) {
                let course = data.course;
                $("#input-establish-course-name").val(course.courseName);
                $("#input-establish-class-name").val(course.className);

            }

        })
    }*/
    //创建课程 确认按钮

}

// 教师添加课程
function studentAddCourse() {
    let bg = document.getElementById("bg");            //获取 背景
    let addCoursePopup = document.getElementById("add-course-popup");     //获取  添加课程弹出框
    //  加入课程  按钮    被点击时弹出  添加课程框
    let divContentAdd = document.getElementById("div-content-add");
    divContentAdd.onclick = function () {
        bg.style.position = "fixed";
        addCoursePopup.style.display = "block"
    }

//  添加课程框  取消按钮
    let addCoursePopupButCancel = document.getElementById("add-course-popup-but-cancel");
    addCoursePopupButCancel.onclick = function () {
        bg.style.position = "";
        addCoursePopup.style.display = "none"
    }



    // 添加课程框   确认按钮

}

// 课程  点击更多  显示
function more(id) {
    if ($('#more-bg' + id + '').is(':hidden')){
        $('#more-bg' + id + '').show();
    }else {
        $('#more-bg' + id + '').hide();
    }
}
//更多  点击编辑
function moreEditA(courseId,courseName,className,year,term) {
    $('#bg').css('position','fixed');
    $('#edit-course').css('display','block');
    $('#edit-course-sure').attr('title',courseId);
    $('#input-edit-course-name').val(courseName);
    $('#input-edit-class-name').val(className);
    $('#edit-course-year-box').attr('value',year);
    $('#edit-course-studyData-box').attr('value',term);
}
// 更多  点击退课
function moreDropOutA(courseId) {
    console.log(courseId);
    $.ajax({
        url: "/teacgercourse/deletecourse",
        async: true,
        cache: false,
        type: "post",
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
//更多  点击归档
function moreArchiveA(id) {

}





