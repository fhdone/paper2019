<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>student list</title>
    <% pageContext.setAttribute("PRO_PATH", request.getContextPath()); %>
    <script type="text/javascript" src="${PRO_PATH}/static/js/jquery-3.4.1.min.js"></script>
    <script type="text/javascript" src="${PRO_PATH}/static/js/vue-2.6.10.min.js"></script>
    <link href="${PRO_PATH}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="${PRO_PATH}/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

    <link href="${PRO_PATH}/static/bootstrap-table-dist/bootstrap-table.min.css" rel="stylesheet">
    <script src="${PRO_PATH}/static/bootstrap-table-dist/bootstrap-table.min.js"></script>
    <script src="${PRO_PATH}/static/bootstrap-table-dist/locale/bootstrap-table-zh-CN.min.js"></script>


</head>
<body>

<div class="modal fade" id="lxsModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Modal title</h4>
      </div>
      <div class="modal-body">
        ...
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>


	<div class="container" id="stuListInfo">
		<div class="row">
			<div class="col-md-12">
				<h1>{{ stuListInfo_title }}</h1>
			</div>
		</div>

<%--		<div class="row">--%>
<%--			<div class="col-md-4 col-md-offset-8">--%>
<%--				<button type="button" class="btn btn-info" id="addbuttonid">Add</button>--%>
<%--				<button type="button" class="btn btn-warning">Del</button>--%>
<%--			</div>--%>
<%--		</div>--%>

		<div class="row">
			<div class="col-md-12" >
				<table class="table table-hover" id="stuInfoTable">
<%--					<tr>--%>
<%--						<th>ID</th>--%>
<%--						<th>NAME</th>--%>
<%--						<th>ADDR</th>--%>
<%--						<th>OPER</th>--%>
<%--					</tr>--%>
<%--					<c:forEach items="${pageInfo.list}" var="stu">--%>
<%--						<tr>--%>
<%--							<th>${stu.id}</th>--%>
<%--							<th>${stu.stuName}</th>--%>
<%--							<th>${stu.stuAdd}</th>--%>
<%--							<th></th>--%>
<%--						</tr>--%>
<%--					</c:forEach>--%>
				</table>
			</div>
		</div>

<%--		<div class="row">--%>
<%--			<div class="col-md-6">--%>
<%--			  当前第${pageInfo.pageNum}页，总页${pageInfo.pages},总记录数${pageInfo.total}--%>
<%--			</div>--%>

<%--			<div class="col-md-6">--%>
<%--				<nav aria-label="Page navigation">--%>
<%--				<ul class="pagination">--%>
<%--				    <li><a href="${PRO_PATH}/student/list?pn=1">首页</a></li>--%>

<%--				    <c:if test="${pageInfo.hasPreviousPage}">--%>
<%--					<li><a href="${PRO_PATH}/student/list?pn=${pageInfo.pageNum-1}" aria-label="Previous"> <span--%>
<%--							aria-hidden="true">&laquo;</span>--%>
<%--					</a></li>--%>
<%--					</c:if>--%>

<%--					<c:forEach items="${pageInfo.navigatepageNums}" var="apageNum">--%>
<%--					 <c:if test="${apageNum==pageInfo.pageNum}">--%>
<%--					  <li class="active"><a href="#">${apageNum}</a></li>--%>
<%--					 </c:if>--%>
<%--					 <c:if test="${apageNum!=pageInfo.pageNum}">--%>
<%--					  <li><a href="${PRO_PATH}/student/list?pn=${apageNum}">${apageNum}</a></li>--%>
<%--					  </c:if>--%>
<%--					</c:forEach>--%>

<%--					<c:if test="${pageInfo.hasNextPage}">--%>
<%--					<li><a href="${PRO_PATH}/student/list?pn=${pageInfo.pageNum+1}" aria-label="Next"> <span--%>
<%--							aria-hidden="true">&raquo;</span>--%>
<%--					</a></li>--%>
<%--					</c:if>--%>

<%--					 <li><a href="${PRO_PATH}/student/list?pn=${pageInfo.pages}">末页</a></li>--%>
<%--				</ul>--%>
<%--				</nav>--%>
<%--			</div>--%>
<%--		</div>--%>

	</div>
	
	<script type="text/javascript">
	$("#addbuttonid").click(function(){
		alert("ddd");
		$("#lxsModal").modal({
			backdrop:"static"
		});
	});

    var app = new Vue({
        el: '#stuListInfo',
        data: {
            stuListInfo_title : '学生列表',
        }
    });

    function initTable(){
        $('#stuInfoTable').bootstrapTable({
            url: '/student/queryStuByPage',
            undefinedText : '-',
            pagination : true, // 分页
            //striped : true, // 是否显示行间隔色
            cache : false, // 是否使用缓存
            pageList : [ 5, 10, 20 ],
            // toolbar : "#toolbar",// 指定工具栏
            // showColumns : true, // 显示隐藏列
            // showRefresh : true, // 显示刷新按钮
            // uniqueId : "userName", // 每一行的唯一标识
            sidePagination : "server", // 服务端处理分页
            // search: true,
            columns: [{
                field: 'id',
                title: 'ID'
            }, {
                field: 'stuName',
                title: 'Name'
            }, {
                field: 'stuAdd',
                title: 'ADDR'
            }, {
                title: '操作',
                field: 'id',
                align: 'center',
                formatter: function (value, row, index) {
                    var e = '<button href="#" class="btn btn-default" onclick="edit(\'' + row.id + '\')">编辑</button> ';
                    var d = '<button href="#" class="btn btn-default" onclick="del(\'' + row.id + '\')">删除</button> ';
                    return e + d;
                }
            }],
            responseHandler : function(res) {
                return {
                    total : res.total,
                    rows : res.list
                };
            }
        })
    }

    $(document).ready(function() {
        initTable();
    });

	</script>
</body>
</html>