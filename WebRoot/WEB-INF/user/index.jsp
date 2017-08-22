<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Home</title>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="static/css/bootstrap.min.css" rel='stylesheet' type='text/css' />
<link href="static/css/style.css" rel='stylesheet' type='text/css' />
<link href="static/css/font-awesome.css" rel="stylesheet">
<script src="static/js/jquery.min.js"></script>
<link href="static/css/custom.css" rel="stylesheet">
<script type="text/javascript">
	function submit_form(){
		document.forms["search_form"].submit();
	}
</script>
</head>

<body>
	<div id="wrapper">
		<%@include file="common/nav.jsp" %>
		<div id="page-wrapper">
			<div class="col-md-12 graphs">
				<div class="xs">
					<h3>所有共享</h3>
					<div class="bs-example4" data-example-id="contextual-table">
					<div class="text-center">
						<form class="form-inline" id="search_form" method="post">
							<input placeholder="输入要查询的文件名.." name="filename" class="form-control">
							<a class="btn btn-info" onclick="submit_form()"><i class="fa fa-search nav_icon"></i>搜索</a>
						</form>
					</div>
						<table class="table">
							<thead>
								<tr>
									<th>文件名</th>
									<th>大小</th>
									<th>分享者</th>
									<th>上传日期</th>
									<th>下载次数</th>
									<th>下载</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${fileList }" var="userFile">
								<tr>
									<td>${userFile.filename }</td>
									<td>${userFile.fileSize }</td>
									<td>${userFile.owner.username }</td>
									<td>${userFile.createTime }</td>
									<td>${userFile.count }</td>
									<td><a class="btn btn-primary" href="download?id=${userFile.id }"><i class="fa fa-download"></i>下载</a></td>
								</tr>
							</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		<!-- /#page-wrapper -->
	</div>
	<!-- /#wrapper -->
</body>
</html>
