
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>秒杀详情页</title>
    <%@include file="common/head.jsp" %>
</head>
<body>
<div class="container">
    <div class="panel panel-default text-center">


        <div class="panel-heading">
            <h1>${seckill.name}</h1>
        </div>
        <div class="panel-body">
            <h2 class="text-danger">
                <%-- 显示time图标 --%>
                <span class="glyphicon glyphicon-time"></span>
                <%-- 展示倒计时 --%>
                <span class="glyphicon" id="seckill-box">

                </span>
            </h2>
        </div>
    </div>

</div>
<%-- 登录弹出层,输入电话 --%>
<div id="killPhoneModal" class="modal fade"><%--模态框（Modal）是覆盖在父窗体上的子窗体--%>
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header"> <%--模态框头部--%>
                <h3 class="modal-title text-center">
                    <span class="glyphicon glyphicon-phone"></span>秒杀电话:
                </h3>
            </div>


            <div class="modal-body"> <%--模态框body--%>
                <div class="row">
                    <div class="col-xs-8 col-xs-offset-2">
                        <input type="text" name="killPhone" id="killPhoneKey" placeholder="填写手机号码" class="form-control">
                    </div>
                </div>
            </div>

            <div class="modal-footer"><%--模态框底部--%>
                <span id="killPhoneMessage" class="glyphicon"></span><%--输入错误时,显示出错信息--%>
                <button type="button" id="killPhoneBtn" class="btn btn-success">
                    <span class="glyphicon glyphicon-phone"></span>
                    提交
                </button>
            </div>
        </div>
    </div>
</div>


</body>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<!-- 最新的 Jq Cookie 文件 -->
<script src="https://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.js"></script>

<!-- 最新的 Jq 倒计时 文件 -->
<script src="http://cdn.bootcss.com/jquery.countdown/2.1.0/jquery.countdown.min.js"></script>

<script src="${pageContext.request.contextPath}/resources/script/seckill.js"></script>

<script type="text/javascript">
    $(function () { /*jquery*/

        //通过EL表达式,传入参数
        seckill.detail.init({
            seckillId: ${seckill.seckillId},
            startTime: ${seckill.startTime.time},
            endTime: ${seckill.endTime.time},
        });
    })
</script>

</html>