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
<title>BMS | Add Payment</title>
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
				<h1>View Payment</h1>
				<ol class="breadcrumb">
					<li><a href="index"><i class="fa fa-dashboard"></i> Home</a></li>
					<li class="active">View Payment</li>
				</ol>
			</section>

			<section class="content">
				<div class="row" style="overflow: scroll;">
					<div class="col-md-8">
						<!-- Horizontal Form -->
						<div class="box box-info">
							<div class="box-header with-border">
								<h3 class="box-title">View Payment Details</h3>
							</div>
							<!-- /.box-header -->
							<!-- form start -->
							<form:form class="form-horizontal">
								<div class="box-body">
									<div class="form-group">
										<label for="date" class="col-sm-2 control-label">Payment Date:</label>

										<div class="col-sm-4">
											<input type="date" name="date" id="date" value="${salesPaymenet.salesDate}" readonly="readonly">
										</div>
										
										<label for="Party" class="col-sm-2 control-label">Party:</label>
										<div class="col-sm-4">
											<c:set var="cid">${salesPaymenet.customerId}</c:set>
											<input type="text" name="customerId" id="customerId" value="${sessionScope.CVALKEY[cid]}" readonly="readonly">
										</div>
										
									</div>

									<div class="form-group">
										<label for="payment" class="col-sm-2 control-label">Type:</label>
										<div class="col-sm-4">
											<input type="text" name="type" id="type" value="${salesPaymenet.type}" readonly="readonly">
										</div>

										<label for="mode" class="col-sm-2 control-label">Mode:</label>

										<div class="col-sm-4">
											<input type="text" name="mode" id="mode" value="${salesPaymenet.mode}" readonly="readonly">
										</div>
									</div>
									
									<div class="form-group">
										<label for="amount" class="col-sm-2 control-label">Amount:</label>

										<div class="col-sm-4">
											<input type="number" class="form-control" id="amount" name="amount" value="${salesPaymenet.amount}"
												readonly="readonly">
										</div>
										
										</div>
										<div class="form-group">
								
										<label for="Comment" class="col-sm-2 control-label">Comment:</label>

										<div class="col-sm-10">
											<input type="text" class="form-control" id="comment" name="comment"
												value="${salesPaymenet.comment}" readonly="readonly">
										</div>
									</div>
									<div class="form-group">
										<label for="payment" class="col-sm-2 control-label">Updated By:</label>
										<div class="col-sm-4">
											<input type="text" name="updatedBy" id="updatedBy" value="${salesPaymenet.updatedBy}" readonly="readonly">
										</div>

										<label for="mode" class="col-sm-2 control-label">Updated On:</label>

										<div class="col-sm-4">
											<input type="text" size="25" name="updatedOn" id="updatedOn" value="${salesPaymenet.updatedOn}" readonly="readonly">
										</div>
									</div>
								</div>
								<!-- /.box-body -->
								<div class="box-footer" align="center">
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
