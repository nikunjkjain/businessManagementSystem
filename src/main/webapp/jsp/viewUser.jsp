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
<title>BMS | View User Details</title>
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
				<h1>View User Details</h1>
				<ol class="breadcrumb">
					<li><a href="index"><i class="fa fa-dashboard"></i>Home</a></li>
					<li><a href="viewUsers">Users</a></li>
					<li class="active">View User Details</li>
				</ol>
			</section>

			<section class="content">
				<div class="row">
					<div class="col-md-8">
						<!-- Horizontal Form -->
						<div class="box box-info">
							<div class="box-header with-border">
								<h3 class="box-title"> User Details:</h3>
							</div>
							<!-- /.box-header -->
							<!-- form start -->
							<form:form action="#" method="post"
								class="form-horizontal">
								<div class="box-body">
									<div class="form-group">
									
										<label for="name" class="col-sm-2 control-label">Name:</label>

										<div class="col-sm-4">
											<input type="text" class="form-control" id="name" name="name"
												value="${userDetails.name}" disabled>
										</div>
										
										<label for="username" class="col-sm-2 control-label">Username:</label>

										<div class="col-sm-4">
											<input type="text" class="form-control" id="username" name="username"
												value ="${userDetails.username}" disabled>
										</div>
									</div>

									<div class="form-group">
										<label for="password" class="col-sm-2 control-label">Password:</label>

										<div class="col-sm-4">
											<input type="text" class="form-control" id="password" name="password"
												value="${userDetails.password}" disabled>
										</div>

										<label for="inputEmail3" class="col-sm-2 control-label">User Id</label>

										<div class="col-sm-4">
											<input type="text" class="form-control" id="id" name="id"
												value="${userDetails.id}" disabled>
										</div>
									</div>
									
									<div class="form-group">
										<label for="inputEmail3" class="col-sm-2 control-label">Email:</label>

										<div class="col-sm-4">
											<input type="email" class="form-control" id="email" name="email"
												value="${userDetails.email}" disabled>
										</div>
										
										<label for="inputEmail3" class="col-sm-2 control-label">Mobile:</label>

										<div class="col-sm-4">
											<input type="number" class="form-control" id="mobileNo" name="mobileNo"
												value="${userDetails.mobileNo}" disabled>
										</div>
										</div>
										<div class="form-group">
								
										<label for="inputEmail3" class="col-sm-2 control-label">Address:</label>

										<div class="col-sm-10">
											<input type="text" class="form-control" id="address" name="address"
												value="${userDetails.address}" disabled/>
										</div>
										
										<input type = "hidden" id="status" name="status" value="${userDetails.status}"/>
										<input type = "hidden" id="id" name="id" value="${userDetails.id}" disabled/>
										
									</div>
								</div>
								<!-- /.box-body -->
								<!-- <div class="box-footer" align="center">
									<button type="submit" class="btn btn-info">Update Details</button>
								</div> -->
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
