$(function () {
    JQergodicOtherCourse();  //JQuery遍历
})
//JQuery课程遍历
function JQergodicOtherCourse() {
    let courseId = getQueryString('courseId');
    console.log("进入遍历");
    $.getJSON("teacgercourse/getcourselist", function (data) {

        if (data.success) {
            // let studentCourseMap = ["教", "数据库", "917213802", "AM745W", "2019-2020", "第二学期", "第五次作业：巴拉巴拉", "第四次作业：鸡格格", "15"];
            let studentCourseMap = data.courseList;

            var tempHtml = "";
            $.each(studentCourseMap, function (n, value) {
                tempHtml += '<dl class="course">';
                tempHtml += '<dt class="course-title">'
                    + '<div class="course-title-lable">'
                    + '<p class="course-title-lable-identity" id="course-title-lable-identity">教</p>'
                    + '</div>'
                    + '<div class="course-name">'
                    + '<a href="#" title="' + value.courseName + '" class="courseName" id="courseName">' + value.courseName + '</a>'
                    + '<span title="' + value.courseName + '" class="courseRemarks" id="courseRemarks">' + value.className + '</span>'
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
                    + '<li class="work-list-first"><a class="work-a-first" id="work-a-first" href="#">无</a></li>'
                    + '<li class="work-list-second"><a class="work-a-second" id="work-a-second" href="#"></a></li>'
                    + '</ul>'
                    + '<div class="course-main-foot">'
                    + '<img class="course-teatherAvatar">'
                    + '<p class="course-main-foot-member">成员<a href = "/teachercoursemember" id="course-main-foot-member-num">' + value.id + '</a>人</p>'
                    + '<div class="more" id="more" onclick="more(' + value.id + ')">'
                    + '<p class="more-text">更多</p>'
                    + '<img class="more-img">'
                    + '</div>'
                    + '<ul class="more-bg" id="more-bg' + value.id + '">'
                    + '<li class="more-edit" onclick="moreEditA(\'' + value.courseId + '\',\'' + value.courseName + '\',\'' + value.className + '\',\'' + value.year + '\',\'' + value.term + '\')"><a class="more-edit-a">编辑</a></li>'
                    + '<li class="more-drop-out" onclick="moreDropOutA(\'' + value.courseId + '\')" title="' + value.courseId + '"><a class="more-drop-out-a">退课</a></li>'
                    + '<li class="more-archive" onclick="moreArchiveA(' + value.id + ')"><a class="more-archive-a">归档</a></li>'
                    + '</ul>'
                    + '<a class="top" id="top">置顶</a>'
                    + '<a class="unpin" id="unpin">取消置顶</a>'
                    + '</div>';
                tempHtml += '</dd>';
                tempHtml += '</dl>';
            });
            // 遍历形式 在div中 向前遍历
            $("#content-otherCourse").prepend(tempHtml);
        }
    });
}