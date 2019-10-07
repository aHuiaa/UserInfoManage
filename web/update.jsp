<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>修改用户</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery-2.1.0.min.js"></script>
    <script src="js/bootstrap.min.js"></script>

</head>
<body>
<div class="container" style="width: 400px;">
    <h3 style="text-align: center;">修改联系人</h3>
    <form action="${pageContext.request.contextPath}/updateServlet" method="post">
        <div>
            <input type="hidden" name="u_id" value="${f_user.u_id}">
        </div>
        <div class="form-group">
            <label for="u_nikename">姓名：</label>
            <input type="text" class="form-control" id="u_nikename" name="u_nikename" value="${f_user.u_nikename}" readonly="readonly" placeholder="请输入姓名" />
        </div>
        <div class="form-group">
            <label>性别：</label>
            <input type="radio" name="u_gender" value="男"  ${f_user.u_gender == "男" ? "checked" :" "}  />男
            <input type="radio" name="u_gender" value="女" ${f_user.u_gender == "女" ? "checked" :" "} />女
        </div>


        <div class="form-group">
            <label for="u_age">年龄：</label>
            <input type="text" class="form-control" id="u_age"  name="u_age" value="${f_user.u_age}" placeholder="请输入年龄" />
        </div>

        <div class="form-group">
            <label for="u_address">籍贯：</label>
            <select name="u_address" id="u_address" class="form-control" >
                <option value="广东" ${f_user.u_address == "广东" ? "selected" :""}>广东</option>
                <option value="广西" ${f_user.u_address == "广西" ? "selected" :""}>广西</option>
                <option value="湖南" ${f_user.u_address == "湖南" ? "selected" :""}>湖南</option>
            </select>
        </div>

        <div class="form-group">
            <label for="u_qq">QQ：</label>
            <input type="text" class="form-control" name="u_qq" id="u_qq" value="${f_user.u_qq}" placeholder="请输入QQ号码"/>
        </div>

        <div class="form-group">
            <label for="u_email">Email：</label>
            <input type="text" class="form-control" id="u_email" name="u_email" value="${f_user.u_email}" placeholder="请输入邮箱地址"/>
        </div>

        <div class="form-group" style="text-align: center">
            <input class="btn btn-primary" type="submit" value="提交" />
            <input class="btn btn-default" type="reset" value="重置" />
            <input class="btn btn-default" type="button" value="返回"/>
        </div>
    </form>
</div>
</body>
</html>