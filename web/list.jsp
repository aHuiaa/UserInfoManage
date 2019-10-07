<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>用户信息管理系统</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>
    <script>
        //删除单个用户
        function delUser(id) {
            if (confirm("您确定要删除吗")) {
                location.href = "${pageContext.request.contextPath}/deleteServlet?u_id=" + id;
            }
        }

        window.onload = function (ev) {

            //删除选中功能
            document.getElementById("delSelected").onclick = function () {
                var flag = false;//设置一个标记
                var cbs = document.getElementsByName("u_id");
                if (confirm("您确定要删除吗？")) {
                    for (var i = 0; i < cbs.length; i++) {
                        //只要有一个是被选中的就设置标记为true
                        if (cbs[i].checked) {
                            flag = true;
                            break;
                        }
                    }
                    //如果标记为true就提交表单
                    if (flag) {
                        document.getElementById("from").submit();
                    }
                }
            }
            // 全选/全不选功能
            //设置点击事件
            document.getElementById("cb_first").onclick = function () {
                //获取所有的checkbox
                var cbs = document.getElementsByName("u_id");
                //将所有的checkbox状态设置为this即cb_first的状态
                for (var i = 0; i < cbs.length; i++) {
                    cbs[i].checked = this.checked;
                }

            }
        }
    </script>
</head>
<body>
<div class="container">
    <h3 style="text-align: center">用户信息列表</h3>
    <div style="float: left;margin: 5px">
        <form class="form-inline" action="${pageContext.request.contextPath}/findUserByPageServlet" method="post">
            <div class="form-group">
                <label for="u_nikename">名字</label>
                <input type="text" class="form-control" name="u_nikename" value="${u_nikename}" id="u_nikename">
            </div>
            <div class="form-group">
                <label for="u_address">籍贯</label>
                <input type="text" class="form-control" name="u_address" value="${u_address}" id="u_address">
            </div>
            <div class="form-group">
                <label for="u_email">邮箱</label>
                <input type="text" class="form-control" name="u_email" value="${u_email}" id="u_email">
            </div>
            <button type="submit" class="btn btn-default">查询</button>
        </form>
    </div>
    <div style="float: right;margin: 5px">
        <a class="btn btn-primary" href="${pageContext.request.contextPath}/add.jsp">添加联系人</a>
        <a class="btn btn-primary" href="javascript:void (0);" id="delSelected">删除选中</a>
    </div>
    <form id="from" action="${pageContext.request.contextPath}/deleteSelectedServlet" method="get">
        <table border="1" class="table table-bordered table-hover">

            <tr class="success">
                <th><input type="checkbox" id="cb_first"></th>
                <th>编号</th>
                <th>姓名</th>
                <th>性别</th>
                <th>年龄</th>
                <th>籍贯</th>
                <th>QQ</th>
                <th>邮箱</th>
                <th>操作</th>
            </tr>

            <c:forEach items="${pb.list}" var="user" varStatus="s">
                <tr>
                    <td><input type="checkbox" name="u_id" value="${user.u_id}" ></td>
                    <td>${s.count}</td>
                    <td>${user.u_nikename}</td>
                    <td>${user.u_gender}</td>
                    <td>${user.u_age}</td>
                    <td>${user.u_address}</td>
                    <td>${user.u_qq}</td>
                    <td>${user.u_email}</td>
                    <td><a class="btn btn-default btn-sm"
                           href="${pageContext.request.contextPath}/findUserServlet?u_id=${user.u_id}">修改</a>&nbsp;
                        <a class="btn btn-default btn-sm" id="del" href="javascript:delUser(${user.u_id});"
                           id="del">删除</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </form>
    <div>
        <nav>
            <ul class="pagination">
                <c:if test="${pb.currentPage == 1 }">
                    <li class="disabled">
                </c:if>
                <c:if test="${pb.currentPage != 1 }">
                    <li>
                </c:if>
                    <a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${pb.currentPage - 1}&rows=5&u_nikename=${u_nikename}&u_address=${u_address}&u_email=${u_email}" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>

                <c:forEach begin="1" end="${pb.totalPage}" var="i">
                    <c:if test="${pb.currentPage == i}">
                        <li class="active">
                            <a  href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${i}&rows=5&u_nikename=${u_nikename}&u_address=${u_address}&u_email=${u_email}">${i}</a>
                        </li>
                    </c:if>
                    <c:if test="${pb.currentPage != i}">
                        <li>
                            <a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${i}&rows=5&u_nikename=${u_nikename}&u_address=${u_address}&u_email=${u_email}">${i}</a>
                        </li>
                    </c:if>
                </c:forEach>

                <c:if test="${pb.totalPage == pb.currentPage }">
                <li class="disabled">
                </c:if>
                <c:if test="${pb.totalPage != pb.currentPage }">
                <li>
                </c:if>
                    <a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${pb.currentPage + 1}&rows=5&u_nikename=${u_nikename}&u_address=${u_address}&u_email=${u_email}" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
                <span style="font-size:25px;margin-left: 5px">共${pb.totalCount}条记录，共${pb.totalPage}页</span>
            </ul>
        </nav>
    </div>
</div>
</body>
</html>
