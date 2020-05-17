$(function () {
    $.ajax({
        type: 'get',                            //发送请求方式post get
        async: true,
        cache: false,
        // data: JSON.stringify({            //发送到服务器的数据
        //     extrCode: extrCode,
        // }),
        url: "",                    //发送请求的地址
     /*   contentType: "application/json;charset=utf-8",*/
        dataType: "JSON",                        //服务器返回的数据类型（html xml text.....）
        success: function (data) {
            if (data.success) {
                // 遍历教学团队
                let teacherMemberList = data.teacherMemberList;
                var tempHtml = "";
                $.each(teacherMemberList,function (n,value) {
                    tempHtml += '<div class="teacher">'
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
                $("#teacherBox").append(tempHtml);
                // 遍历全部学生
                let studentMemberList = data.studentMemberList;
                var studentHtml = "";
                $.each(studentMemberList,function (n,value) {
                    studentHtml += '<tr>'
                        + '<td class="checkBox">'
                        + '<input type="checkbox" name="gg" class="tableCheck">'
                        + '</td>'
                        + '<td>'
                        + '<img src="../img/seeStudent/34.png" class="touxiang2">'
                        + '</td>'
                        + '<td>' + value.userId + '</td>'
                        + '<td>' + value.username + '</td>'
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
                $("#studentBox").append(studentHtml);
            }
        }
    });
});