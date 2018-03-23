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
<title>BMS | View Product Details</title>
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
				<h1>View Product Details</h1>
				<ol class="breadcrumb">
					<li><a href="index"><i class="fa fa-dashboard"></i>Home</a></li>
					<li><a href="viewProducts">Products</a></li>
					<li class="active">View Product Details</li>
				</ol>
			</section>

			<section class="content">
				<div class="row">
					<div class="col-md-8">
						<!-- Horizontal Form -->
						<div class="box box-info">
							<div class="box-header with-border">
								<h3 class="box-title">Product Details:</h3>
							</div>
							<!-- /.box-header -->
							<!-- form start -->
							<form:form action="#" method="post" class="form-horizontal">
								<div class="box-body">
									<div class="form-group">

										<label for="username" class="col-sm-2 control-label">Product
											Id:</label>

										<div class="col-sm-4">
											<input type="text" class="form-control" id="id" name="id"
												value="${productDetails.id}" disabled>
										</div>

										<label for="name" class="col-sm-2 control-label">Name:</label>

										<div class="col-sm-4">
											<input type="text" class="form-control" id="name" name="name"
												value="${productDetails.name}" disabled>
										</div>

									</div>

									<div class="form-group">
										<label for="unitPrice" class="col-sm-2 control-label">unitPrice:</label>

										<div class="col-sm-4">
											<input type="text" class="form-control" id="unitPrice"
												name="unitPrice" value="${productDetails.unitPrice}"
												disabled>
										</div>

										<label for="unitMeasure" class="col-sm-2 control-label">unitMeasure</label>

										<div class="col-sm-4">
											<input type="text" class="form-control" id="unitMeasure"
												name="unitMeasure" value="${productDetails.unitMeasure}"
												disabled>
										</div>
									</div>

									<div class="form-group">
										<label for="updatedBy" class="col-sm-2 control-label">updatedBy:</label>

										<div class="col-sm-4">
											<input type="text" class="form-control" id="updatedBy"
												name="updatedBy" value="${productDetails.updatedBy}"
												disabled>
										</div>

										<label for="updatedOn" class="col-sm-2 control-label">updatedOn:</label>

										<div class="col-sm-4">
											<input type="text" class="form-control" id="updatedOn"
												name="updatedOn" value="${productDetails.updatedOn}"
												disabled>
										</div>
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
