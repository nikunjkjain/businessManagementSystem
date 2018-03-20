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
				<h1>Add Payment</h1>
				<ol class="breadcrumb">
					<li><a href="index"><i class="fa fa-dashboard"></i> Home</a></li>
					<li class="active">Add Payment</li>
				</ol>
			</section>

			<section class="content">
				<div class="row">
					<div class="col-md-8">
						<!-- Horizontal Form -->
						<div class="box box-info">
							<div class="box-header with-border">
								<h3 class="box-title">Enter Payment Details</h3>
							</div>
							<!-- /.box-header -->
							<!-- form start -->
							<form:form action="insertPayment" method="post"
								class="form-horizontal">
								<div class="box-body">
									<div class="form-group">
										<label for="inputEmail3" class="col-sm-2 control-label">Payment Date:</label>

										<div class="col-sm-4">
											<input type="date" name="date" id="date">
										</div>
										
										<label for="inputEmail3" class="col-sm-2 control-label">Party:</label>

										<div class="col-sm-4">
										<select id="customerId" name="customerId">
											<option selected="selected">Select Party</option>
												<c:forEach items="${customerList}" var="customerList">
													<option value="${customerList.id}">${customerList.name}</option>
												</c:forEach>
											</select>
										</div>
										
									</div>

									<div class="form-group">
										<label for="inputEmail3" class="col-sm-2 control-label">Type:</label>

										<div class="col-sm-4">
											<select id="type" name="type">
											<option selected value = "receipt">Receipt</option>
											<option value="payment">Payment</option>
											</select>
										</div>

										<label for="inputEmail3" class="col-sm-2 control-label">Mode:</label>

										<div class="col-sm-4">
											<select id="mode" name="mode">
												<option selected value = "cash">Cash</option>
												<option value="cheque">Cheque</option>
											</select>
										</div>
									</div>
									
									<div class="form-group">
										<label for="inputEmail3" class="col-sm-2 control-label">Amount:</label>

										<div class="col-sm-4">
											<input type="number" class="form-control" id="amount" name="amount"
												placeholder="Amount">
										</div>
										
										<!-- <label for="inputEmail3" class="col-sm-2 control-label">Mobile:</label>

										<div class="col-sm-4">
											<input type="number" class="form-control" id="mobileNo" name="mobileNo"
												placeholder="Mobile No" maxlength="6">
										</div> -->
										</div>
										<div class="form-group">
								
										<label for="inputEmail3" class="col-sm-2 control-label">Comment:</label>

										<div class="col-sm-10">
											<input type="text" class="form-control" id="comment" name="comment"
												placeholder="Comment">
										</div>
									</div>
								</div>
								<!-- /.box-body -->
								<div class="box-footer" align="center">
									<button type="reset" class="btn btn-default">Reset</button>
									<button type="submit" class="btn btn-info">Add Payment</button>
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
