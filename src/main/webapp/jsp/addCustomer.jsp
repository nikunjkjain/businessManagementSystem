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
<title>BMS | Add Customer</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, Customer-scalable=no"
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
		<div class="content-wrapper" style="overflow: scroll;">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>Add Customer</h1>
				<ol class="breadcrumb">
					<li><a href="index"><i class="fa fa-dashboard"></i> Home</a></li>
					<li class="active">Add Customer</li>
				</ol>
			</section>

			<section class="content">
				<div class="row">
					<div class="col-md-10" style="overflow: scroll;">
						<!-- Horizontal Form -->
						<div class="box box-info">
							<div class="box-header with-border">
								<h3 class="box-title">Enter Customer Details</h3>
							</div>
							<!-- /.box-header -->
							<!-- form start -->
							<form:form action="insertCustomer" method="post"
								class="form-horizontal">
								<div class="box-body">
									<div class="form-group">
										
										<label for="name" class="col-sm-2 control-label">Customer Name:</label>

										<div class="col-sm-4">
											<input type="text" class="form-control" id="name" name="name"
												placeholder ="Customer Name" required="required">
										</div>
									</div>

									<div class="form-group">
										<label for="mobileNo" class="col-sm-2 control-label">Mobile No:</label>

										<div class="col-sm-4">
											<input type="number" class="form-control" id="mobileNo" name="mobileNo"
												placeholder ="Mobile No" required="required">
										</div>

										<label for="altContactNo" class="col-sm-2 control-label">Alt Contact No:</label>

										<div class="col-sm-4">
											<input type="number" class="form-control" id="altContactNo" name="altContactNo"
												placeholder ="Alternate Contact Number" value="0">
										</div>
									</div>
									
									<div class="form-group">
										<label for="email" class="col-sm-2 control-label">Email:</label>

										<div class="col-sm-4">
											<input type="email" class="form-control" id="email" name="email"
												placeholder ="Email id" value="">
										</div>
										
										<label for="companyName" class="col-sm-2 control-label">Company Name:</label>

										<div class="col-sm-4">
											<input type="text" class="form-control" id="companyName" name="companyName"
												placeholder ="Company Name" required="required">
										</div>
										</div>
										<div class="form-group">
								
										<label for="billingAddress" class="col-sm-2 control-label">Billing Address:</label>

										<div class="col-sm-10">
											<input type="text" class="form-control" id="billingAddress" name="billingAddress"
												placeholder ="Billing Address" required="required"/>
										</div>
										<input type = "hidden" id="status" name="status" value="1"/>
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
