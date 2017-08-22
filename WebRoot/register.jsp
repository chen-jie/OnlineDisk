<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<head>
	<title>Register One</title>
	<meta name="keywords" content="" />
	<meta name="description" content="" />
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<link href="static/css/font-awesome.css" rel="stylesheet" type="text/css">
	<link href="static/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="static/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css">
	<link href="static/css/templatemo_style.css" rel="stylesheet" type="text/css">	
	
	<script type="text/javascript">
		function check(){
			var password=document.getElementById("password").value;
			var confirm=document.getElementById("confirm-password").value;
			if(password!=confirm){
				alert("两次密码输入不一致！");
				return false;
			}
			return true;
		}
	</script>
</head>
<body class="templatemo-bg-gray">
	<div class="container">
		<div class="col-md-12">
			<h1 class="margin-bottom-15">新用户注册</h1>
			<form class="form-horizontal templatemo-container templatemo-login-form-1 margin-bottom-30" role="form" action="RegisterServlet" method="post">				
		        <div class="form-group">
		          <div class="col-xs-12">		            
		            <div class="control-wrapper">
		            	<label for="username" class="control-label fa-label"><i class="fa fa-user fa-medium"></i></label>
		            	<input type="text" 	required="true" class="form-control" id="username" name="username" placeholder="用户名"><font color='red'>${msg}</font>
		            </div>		            	            
		          </div>              
		        </div>
		        <div class="form-group">
		          <div class="col-md-12">
		          	<div class="control-wrapper">
		            	<label for="password" class="control-label fa-label"><i class="fa fa-lock fa-medium"></i></label>
		            	<input type="password" required="true" class="form-control" id="password" name="password" placeholder="密码">
		            </div>
		          </div>
		        </div>
		        <div class="form-group">
		          <div class="col-md-12">
		          	<div class="control-wrapper">
		            	<label for="password" class="control-label fa-label"><i class="fa fa-lock fa-medium"></i></label>
		            	<input type="password" required="true" class="form-control" id="confirm-password" name="confirm-password" placeholder="确认密码">
		            </div>
		          </div>
		        </div>
		       
		        <div class="form-group">
		          <div class="col-md-12">
		          	<div class="control-wrapper">
		          		<input type="submit" value="注册" class="btn btn-info center-block" onclick="return check();"  >
		          		<font color='blue'>${msg2}</font>
		          	</div>
		          	<div class="col-md-12">
		          			<a href="login.jsp" class="text-right pull-right">返回用户登录页面</a>
					</div>
		          </div>
		        </div>
		      </form>
		     
		</div>
	</div>
</body>
</html>