$(function () {
    JQergodicContentCourse();//JQ遍历课程  上层
    JQergodicOtherCourse//JQuery课程遍历  下层
    JQergodicSort();//JQuery  排序遍历
    JQergodicfile();//JQuery  归档遍历
})
//JQuery课程遍历  上层
function JQergodicContentCourse() {
    $.getJSON("/studentcourse/getcourselist", function(data) {
        if (data.success) {
            // let studentCourseMap = ["教", "数据库", "917213802", "AM745W", "2019-2020", "第二学期", "第五次作业：巴拉巴拉", "第四次作业：鸡格格", "15"];
            let studentCourseMap = data.courseList;
            var tempHtml = "";
            $.each(studentCourseMap,function (n,value) {
                tempHtml += '<dl class="course">';
                tempHtml += '<dt class="course-title">'
                    + '<div class="course-title-lable">'
                    + '<p class="course-title-lable-identity" id="course-title-lable-identity">教</p>'
                    + '</div>'
                    + '<div class="course-name">'
                    + '<a href="#" class="courseName" id="courseName" onclick="olikCourseName(' + value.courseId + ')">' + value.courseName + '</a>'
                    + '<span class="courseRemarks" id="courseRemarks">' + value.className + '</span>'
                    + '</div>'
                    + '<div class="add-course-no">'
                    + '<i class="add-course-QR"></i>'
                    + '<p class="add-course-tit" id="add-course-tit">加课码：'
                    + '<i class="add-course-num" id="add-course-num">' + value.courseId + '</i>'
                    + '</p>'
                    + '</div>'
                    + '<span class="course-term">'
                    + '<p class="course-term-year" id="course-term-year">' + value.year + '</p>'
                    + '<p class="course-term-term" id="course-term-term">' + value.term + '</p>'
                    + '</span>';
                tempHtml += '</dt>';
                tempHtml += '<dd class="course-main">'
                    + '<ul class="job-list">'
                    + '<li class="recent-word">近期作业</li>'
                    + '<li class="work-list-first"><a class="work-a-first" id="work-a-first" onclick="workAFirst(' + value.id + ')">无</a></li>'
                    + '<li class="work-list-second"><a class="work-a-second" id="work-a-second" onclick="workASecond(' + value.id + ')"></a></li>'
                    + '</ul>'
                    + '<div class="course-main-foot">'
                    + '<img class="course-teatherAvatar">'
                    + '<p class="course-main-foot-teacherNmae" onclick="courseMainFootTeacherNmae(' + value.id + ')">' + value.teacherName + '</p>'
                    + '<div class="more" id="more" onclick="more(' + value.id + ')">'
                    + '<p class="more-text">更多</p>'
                    + '<img class="more-img">'
                    + '</div>'
                    + '<ul class="more-bg" id="more-bg' + value.id + '">'
                    + '<li class="more-drop-out" onclick="moreDropOutA(\'' + value.courseId + '\')" title="' + value.courseId + '"><a class="more-drop-out-a">退课</a></li>'
                    + '<li class="more-archive" onclick="moreArchiveA(' + value.id + ')"><a class="more-archive-a">归档</a></li>'
                    + '</ul>'
                    + '<a class="top" id="top" onclick="courseTop(' + value.id + ')">置顶</a>'
                    + '<a class="unpin" id="unpin" onclick="courseUnpin(' + value.id + ')">取消置顶</a>'
                    + '</div>';
                tempHtml += '</dd>';
                tempHtml += '</dl>';
            })
            // 遍历形式 在div中 向前遍历
            $("#content-selectedCourse").prepend(tempHtml);
        }
    });
}
//JQuery课程遍历  下层
function JQergodicOtherCourse() {
    $.getJSON("teacgercourse/getcourselist", function(data) {
        if (data.success) {
            // let studentCourseMap = ["教", "数据库", "917213802", "AM745W", "2019-2020", "第二学期", "第五次作业：巴拉巴拉", "第四次作业：鸡格格", "15"];
            let studentCourseMap = data.courseList;
            var tempHtml = "";
            $.each(studentCourseMap,function (n,value) {
                tempHtml += '<dl class="course">';
                tempHtml += '<dt class="course-title">'
                    + '<div class="course-title-lable">'
                    + '<p class="course-title-lable-identity" id="course-title-lable-identity">教</p>'
                    + '</div>'
                    + '<div class="course-name">'
                    + '<a href="#" class="courseName" id="courseName" onclick="olikCourseName(' + value.courseId + ')">' + value.courseName + '</a>'
                    + '<span class="courseRemarks" id="courseRemarks">' + value.className + '</span>'
                    + '</div>'
                    + '<div class="add-course-no">'
                    + '<i class="add-course-QR"></i>'
                    + '<p class="add-course-tit" id="add-course-tit">加课码：'
                    + '<i class="add-course-num" id="add-course-num">' + value.courseId + '</i>'
                    + '</p>'
                    + '</div>'
                    + '<span class="course-term">'
                    + '<p class="course-term-year" id="course-term-year">' + value.year + '</p>'
                    + '<p class="course-term-term" id="course-term-term">' + value.term + '</p>'
                    + '</span>';
                tempHtml += '</dt>';
                tempHtml += '<dd class="course-main">'
                    + '<ul class="job-list">'
                    + '<li class="recent-word">近期作业</li>'
                    + '<li class="work-list-first"><a class="work-a-first" id="work-a-first" onclick="workAFirst(' + value.id + ')">无</a></li>'
                    + '<li class="work-list-second"><a class="work-a-second" id="work-a-second" onclick="workASecond(' + value.id + ')"></a></li>'
                    + '</ul>'
                    + '<div class="course-main-foot">'
                    + '<img class="course-teatherAvatar">'
                    + '<p class="course-main-foot-teacherNmae" onclick="courseMainFootTeacherNmae(' + value.id + ')">' + value.teacherName + '</p>'
                    + '<div class="more" id="more" onclick="more(' + value.id + ')">'
                    + '<p class="more-text">更多</p>'
                    + '<img class="more-img">'
                    + '</div>'
                    + '<ul class="more-bg" id="more-bg' + value.id + '">'
                    + '<li class="more-drop-out" onclick="moreDropOutA(\'' + value.courseId + '\')" title="' + value.courseId + '"><a class="more-drop-out-a">退课</a></li>'
                    + '<li class="more-archive" onclick="moreArchiveA(' + value.id + ')"><a class="more-archive-a">归档</a></li>'
                    + '</ul>'
                    + '<a class="top" id="top" onclick="courseTop(' + value.id + ')">置顶</a>'
                    + '<a class="unpin" id="unpin" onclick="courseUnpin(' + value.id + ')">取消置顶</a>'
                    + '</div>';
                tempHtml += '</dd>';
                tempHtml += '</dl>';
            })
            // 遍历形式 在div中 向前遍历
            $("#content-otherCourse").prepend(tempHtml);
        }
    });
}
//JQuery  排序遍历
function JQergodicSort() {
    $.getJSON("地址", function(data) {
        if (data.success) {
            let teacherSortMap = data.sortList;
            var tempHtml = "";
            $.each(teacherSortMap,function (n,value) {
                tempHtml += '<li>'
                    + '<i></i>'
                    + '<span>' + value.courseName + '</span>'
                tempHtml += '</li>';
            })
            // 遍历形式 在div中 向前遍历
            $("#sort-main-sort").prepend(tempHtml);

        }
    });
}
//JQuery  归档遍历
function JQergodicfile() {
    $.getJSON("地址", function(data) {
        if (data.success) {
            let teacherFileMap = data.fileList;
            var tempHtml = "";
            $.each(teacherFileMap,function (n,value) {
                tempHtml += '<dl>';
                tempHtml += '<dt>'
                    + '<p class="file-title">课堂</p>'
                    + '<p class="file-role">角色：老师</p>'
                    + '<a class="file-more" onclick="fileMore(' + value.courseId +')"></a>'
                    + '<ul class="file-more-box" id="file-more-box' + value.courseId + '">'
                    + '<li class="file-more-box-recovery" onclick="fileMoreBoxRecovery(' + value.courseId +')"><a href="javascript:;">恢复</a></li>'
                    + '<li class="file-more-box-del" onclick="fileMoreBoxDel(' + value.courseId +')"><a href="javascript:;">退课</a></li>'
                    + '</ul>'
                tempHtml += '</dt>';
                tempHtml += '</dl>';
            })
            // 遍历形式 在div中 向前遍历
            $("#file-main-file").prepend(tempHtml);

        }
    });
}
