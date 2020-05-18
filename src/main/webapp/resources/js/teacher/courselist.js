$(function () {
    JQergodicOtherCourse();  //JQuery遍历
    JQergodicSort();//JQ排序遍历
    JQergodicfile();//JQ归档遍历
})
//JQuery课程遍历
function JQergodicOtherCourse() {
    $.getJSON("teacgercourse/getcourselist", function (data) {
        if (data.success) {
            let studentCourseMap = data.courseList;
            var otherCourseHtml = "";//不置顶
            var selectedCourseHtml = "";//置顶
            var fileCourseHtml = "";//归档
            $.each(studentCourseMap, function (n, value) {
                if (value.topStatus == 0 && value.archiveStatus == 0){
                    //0为不置顶  0为不归档
                    otherCourseHtml += '<dl class="course">';
                    otherCourseHtml += '<dt class="course-title">'
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
                    otherCourseHtml += '</dt>';
                    otherCourseHtml += '<dd class="course-main">'
                        + '<ul class="job-list">'
                        + '<li class="recent-word">近期作业</li>'
                        + '<li class="work-list-first"><a class="work-a-first" id="work-a-first" href="#">无</a></li>'
                        + '<li class="work-list-second"><a class="work-a-second" id="work-a-second" href="#"></a></li>'
                        + '</ul>'
                        + '<div class="course-main-foot">'
                        + '<img class="course-teatherAvatar">'
                        + '<p class="course-main-foot-member">成员<a  href="/memebr?courseId=' + value.courseId + '" id="course-main-foot-member-num">' + value.id + '</a>人</p>'
                        + '<div class="more" id="more" onclick="more(' + value.id + ')">'
                        + '<p class="more-text">更多</p>'
                        + '<img class="more-img">'
                        + '</div>'
                        + '<ul class="more-bg" id="more-bg' + value.id + '">'
                        + '<li class="more-edit" onclick="moreEditA(\'' + value.courseId + '\',\'' + value.courseName + '\',\'' + value.className + '\',\'' + value.year + '\',\'' + value.term + '\')"><a class="more-edit-a">编辑</a></li>'
                        + '<li class="more-drop-out" onclick="moreDropOutA(\'' + value.courseId + '\')" title="' + value.courseId + '"><a class="more-drop-out-a">退课</a></li>'
                        + '<li class="more-archive" onclick="moreArchiveA(\'' + value.id + '\')"><a class="more-archive-a">归档</a></li>'
                        + '</ul>'
                        + '<a class="top" id="top" onclick="courseTop(\'' + value.id + '\')">置顶</a>'
                        + '<a class="unpin" id="unpin" style="display: none">取消置顶</a>'
                        + '</div>';
                    otherCourseHtml += '</dd>';
                    otherCourseHtml += '</dl>';
                }else if (value.topStatus == 1 && value.archiveStatus == 0){
                    //1为置顶  0为归档
                    selectedCourseHtml += '<dl class="course">';
                    selectedCourseHtml += '<dt class="course-title">'
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
                    selectedCourseHtml += '</dt>';
                    selectedCourseHtml += '<dd class="course-main">'
                        + '<ul class="job-list">'
                        + '<li class="recent-word">近期作业</li>'
                        + '<li class="work-list-first"><a class="work-a-first" id="work-a-first" href="#">无</a></li>'
                        + '<li class="work-list-second"><a class="work-a-second" id="work-a-second" href="#"></a></li>'
                        + '</ul>'
                        + '<div class="course-main-foot">'
                        + '<img class="course-teatherAvatar">'
                        + '<p class="course-main-foot-member">成员<a  href="/memebr?courseId=' + value.courseId + '" id="course-main-foot-member-num">' + value.id + '</a>人</p>'
                        + '<div class="more" id="more" onclick="more(' + value.id + ')">'
                        + '<p class="more-text">更多</p>'
                        + '<img class="more-img">'
                        + '</div>'
                        + '<ul class="more-bg" id="more-bg' + value.id + '">'
                        + '<li class="more-edit" onclick="moreEditA(\'' + value.courseId + '\',\'' + value.courseName + '\',\'' + value.className + '\',\'' + value.year + '\',\'' + value.term + '\')"><a class="more-edit-a">编辑</a></li>'
                        + '<li class="more-drop-out" onclick="moreDropOutA(\'' + value.courseId + '\')" title="' + value.courseId + '"><a class="more-drop-out-a">退课</a></li>'
                        + '<li class="more-archive" onclick="moreArchiveA(\'' + value.id + '\')"><a class="more-archive-a">归档</a></li>'
                        + '</ul>'
                        + '<a class="top" id="top" style="display: none">置顶</a>'
                        + '<a class="unpin" id="unpin" onclick="courseUnpin(\'' + value.id + '\')">取消置顶</a>'
                        + '</div>';
                    selectedCourseHtml += '</dd>';
                    selectedCourseHtml += '</dl>';
                }
                // else if (value.archiveStatus == 1){
                //     //1为归档
                //     fileCourseHtml += '<dl>';
                //     fileCourseHtml += '<dt>'
                //         + '<p class="file-title">课堂</p>'
                //         + '<p class="file-role">角色：老师</p>'
                //         + '<a class="file-more" onclick="fileMore(' + value.courseId +')"></a>'
                //         + '<ul class="file-more-box" id="file-more-box' + value.courseId + '">'
                //         + '<li class="file-more-box-recovery" onclick="fileMoreBoxRecovery(' + value.courseId +')"><a href="javascript:;">恢复</a></li>'
                //         + '<li class="file-more-box-del" onclick="fileMoreBoxDel(' + value.courseId +')"><a href="javascript:;">退课</a></li>'
                //         + '</ul>'
                //     fileCourseHtml += '</dt>';
                //     fileCourseHtml += '</dl>';
                // }
            });
            $("#content-otherCourse").prepend(otherCourseHtml);//在其他课程中遍历
            $("#content-selectedCourse").prepend(selectedCourseHtml);//在置顶课程中遍历
            // $("#file-main-file").prepend(fileCourseHtml);//在归档课程中遍历
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
// JQuery  归档遍历
function JQergodicfile() {
    console.log("归档遍历");
    $.getJSON("teacgercourse/getcourselist", function(data) {
        if (data.success) {
            let teacherFileList = data.courseList;
            var fileCourseHtml = "";
            let topNum = 0;//判断置顶个数
            let unpinNum = 0;//判断非置顶个数
            $.each(teacherFileList,function (n,value) {
                if (value.archiveStatus == 1){
                    fileCourseHtml += '<dl>';
                    fileCourseHtml += '<dt>'
                        + '<p class="file-title">' + value.courseName + '</p>'
                        + '<p class="file-role">角色：老师</p>'
                        + '<a class="file-more" onclick="fileMore(\'' + value.courseId + '\')"></a>'
                        + '<ul class="file-more-box" id="file-more-box' + value.courseId + '">'
                        + '<li class="file-more-box-recovery" onclick="fileMoreBoxRecovery(\'' + value.id + '\')"><a href="javascript:;">恢复</a></li>'
                        + '<li class="file-more-box-del" onclick="fileMoreBoxDel(\'' + value.courseId + '\')"><a href="javascript:;">退课</a></li>'
                        + '</ul>'
                    fileCourseHtml += '</dt>';
                    fileCourseHtml += '</dl>';
                }

            });
            console.log("结束");
            // 遍历形式 在div中 向前遍历
            $("#file-main-file").prepend(fileCourseHtml);
        }
    });
}