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
<title>MyShare</title>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="static/css/bootstrap.min.css" rel='stylesheet' type='text/css' />
<link href="static/css/style.css" rel='stylesheet' type='text/css' />
<link href="static/css/font-awesome.css" rel="stylesheet">
<link href="static/css/custom.css" rel="stylesheet">

<script type="text/javascript" src="static/js/jquery.min.js"></script>
<script type="text/javascript" src="static/js/bootstrap.min.js"></script>
<script type="text/javascript">
	function cancelShare(id){
		$("#cancelModal").modal('show');
		$("#share_id").val(id);
	}
</script>
</head>
<body>
	<div id="wrapper">
		<%@include file="common/nav.jsp" %>
		<div id="page-wrapper">
			<div class="col-md-12 graphs">
				<div class="xs">
					<h3>我的共享</h3>
					<div class="bs-example4" data-example-id="contextual-table">
						<table class="table">
							<thead>
								<tr>
									<th>文件名</th>
									<th>大小</th>
									<th>上传日期</th>
									<th>共享状态</th>
									<th>下载次数</th>
									<th>下载</th>
								</tr>
							</thead>
							<tbody>
							<c:forEach items="${fileList }" var="userFile">
								<tr>
									<td>${userFile.filename }</td>
									<td>${userFile.fileSize }</td>
									<td>${userFile.createTime }</td>
									<td>
										<button class="btn btn-danger" onclick="cancelShare(${userFile.id })">取消分享</button>
									</td>
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
<!--分享 模态框（Modal） -->
<div class="modal fade" id="cancelModal" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
          		   确认
            </h4>
         </div>
         <div class="modal-body">
    		       确定要取消分享？
         </div>
         <div class="modal-footer">
            <form action="admin">
            <button type="button" class="btn btn-default" 
               data-dismiss="modal">关闭
            </button>
            	<input class="hidden" value="cancelShare" name="action">
            	<input class="hidden" id="share_id" name="id">
           		<input type="submit" class="btn btn-danger" value="确定" />
            </form>
         </div>
      </div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>

</body>
</html>
