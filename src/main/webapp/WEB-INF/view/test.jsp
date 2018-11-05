<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="/statics/js/jquery-3.3.1.min.js"></script>

    <%
        String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
    %>
</head>
<body>
<form action="">
    <input type="button" value="提交" id="commit">
</form>

<script>

    $(function () {
        $("#commit").click(function () {
            var json = {
                "content": "哈喽",
                "replyTime": new Date(),
                "infoId": {
                    "id": 2,
                    "title": "开心"
                }
            };

            var user = {
              "username":"xhg",
              "password":"12345"
            };

            $.ajax({
                type: "POST",
                url: "<%=basePath%>/jsonCommit",
                contentType: "application/json;charset=utf-8",
                data: JSON.stringify(json),
                dataType: "json",
                success: function (data) {
                    alert(data)
                }
            })
        })
    })
</script>
</body>
</html>
