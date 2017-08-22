<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<nav class="top1 navbar navbar-default navbar-static-top"
	role="navigation" style="margin-bottom: 0">
	<div class="navbar-header">
		<a class="navbar-brand" href="user">在线网盘</a>
	</div>
	<ul class="nav navbar-nav navbar-right">
		<strong style="margin-right: 10px">${user.username}</strong>
		<a href="LoginServlet?action=logout" class="btn btn-success">退出</a>
	</ul>
	<div class="navbar-default sidebar" role="navigation">
		<div class="sidebar-nav navbar-collapse">
			<ul class="nav" id="side-menu">
				<%@include file="left.jsp"%>
			</ul>
		</div>
	</div>
</nav>
</li>