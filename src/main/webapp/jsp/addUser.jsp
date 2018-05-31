<%@ page contentType="text/html; charset=iso-8859-1" language="java"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>BMS | Add User</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">

<%@include file="/jsp/jspf/headerScripts.jspf"%>

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
  
  <script type="text/javascript">
  	function check(){
	     if($("#password").val() != $("#repassword").val())
	     {
	         alert("Password and Re-Password Should be same");
	         return false;
	     }
	 }
  </script>
  
</head>


<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">

		<!-- Include Main Header -->
		<%@include file="/jsp/jspf/header.jspf"%>

		<!-- Left side column. contains the logo and sidebar -->
		<%@include file="/jsp/jspf/sideMenu.jspf"%>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>Add User</h1>
				<ol class="breadcrumb">
					<li><a href="index"><i class="fa fa-dashboard"></i> Home</a></li>
					<li class="active">Add Users</li>
				</ol>
			</section>

			<section class="content">
				<div class="row">
					<div class="col-md-8">
						<!-- Horizontal Form -->
						<div class="box box-primary" style="overflow: scroll;">
							<div class="box-header with-border">
								<h3 class="box-title">Enter User Details</h3>
							</div>
							<!-- /.box-header -->
							<!-- form start -->
							<form:form action="insertUser" method="post" onsubmit="return validatePasswords()"
								class="form-horizontal">
								<div class="box-body">
									<div class="form-group">
										<label for="inputEmail3" class="col-sm-2 control-label">Name:</label>

										<div class="col-sm-4">
											<input type="text" class="form-control" id="name" name="name"
												placeholder="Name" required="required">
										</div>
										
										<label for="inputEmail3" class="col-sm-2 control-label">Username:</label>

										<div class="col-sm-4">
											<input type="text" class="form-control" id="username" name="username"
												placeholder="Username" required="required">
										</div>
										
										<input type="hidden" id="status" name="status" value="1"/>
									</div>

									<div class="form-group">
										<label for="inputEmail3" class="col-sm-2 control-label">Password:</label>

										<div class="col-sm-4">
											<input type="password" class="form-control" id="password" name="password"
												placeholder="Password" required="required">
										</div>

										<label for="inputEmail3" class="col-sm-2 control-label">Re-Password:</label>

										<div class="col-sm-4">
											<input type="password" class="form-control" id="repassword" name="repassword"
												placeholder="Re-Password" required="required">
										</div>
									</div>
									
									<div class="form-group">
										<label for="inputEmail3" class="col-sm-2 control-label">Email:</label>

										<div class="col-sm-4">
											<input type="email" class="form-control" id="email" name="email"
												placeholder="Email">
										</div>
										
										<label for="inputEmail3" class="col-sm-2 control-label">Mobile:</label>

										<div class="col-sm-4">
											<input type="number" class="form-control" id="mobileNo" name="mobileNo"
												placeholder="Mobile No" maxlength="10" required="required">
										</div>
										</div>
										
										<div class="form-group">
										
										<label for="inputEmail3" class="col-sm-2 control-label">Alt Mobile:</label>

										<div class="col-sm-4">
											<input type="number" class="form-control" id="alternateNo" name="alternateNo"
												placeholder="alternateNo" maxlength="10" value="0">
										</div>
										</div>
										<div class="form-group">
								
										<label for="inputEmail3" class="col-sm-2 control-label">Address:</label>

										<div class="col-sm-10">
											<input type="text" class="form-control" id="address" name="address"
												placeholder="Address" required="required">
										</div>
									</div>
								</div>
								<!-- /.box-body -->
								<div class="box-footer" align="center">
									<button type="reset" class="btn btn-default">Reset</button>
									<button type="submit" class="btn btn-info">Sign in</button>
								</div>
								<!-- /.box-footer -->
							</form:form>
						</div>
						<!-- /.box -->
					</div>
					<!--/.col (right) -->
				</div>
				<!-- /.row -->
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->

		<!-- Include footer -->
		<%@include file="/jsp/jspf/footer.jspf"%>

		<!-- Include control side menu -->
		<%@include file="/jsp/jspf/controlSideMenu.jspf"%>

	</div>
	<!-- ./wrapper -->
	<%@include file="/jsp/jspf/footerScripts.jspf"%>

</body>
</html>
