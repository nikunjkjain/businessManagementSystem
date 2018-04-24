<%@ page contentType="text/html; charset=iso-8859-1" language="java"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>BMS | Dashboard</title>
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
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>Sales Details</h1>
				<ol class="breadcrumb">
					<li><a href="index"><i class="fa fa-dashboard"></i> Home</a></li>
					<li class="active">View Sales Details</li>
				</ol>
			</section>

			<section class="content">
				<div class="row">
					<div class="col-xs-12">

						<div class="box">
							<%-- <div class="box-header">
								<h3 class="box-title">
									<a href="editSales/${salesDetails.salesId}/">Edit Sales<i class="fa fa-user-plus"></i></a>
								</h3>
							</div> --%>
							<!-- /.box-header -->
							<div class="box-body">
								<table id="example1" class="table table-bordered table-striped">
									<thead>
										<tr>
											<th>Product</th>
											<th>Quantity</th>
											<th>Rate</th>
											<th>Bags</th>
											<th>Less</th>
											<th>Total</th>
											<th>Description</th>
											<th>UpdatedBy</th>
											<th>UpdatedOn</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${salesDetails}" var="salesDetails">
										<c:set var="total" scope="session" value="0" />
											<tr>
												<td>${salesDetails.productId}</td>
												<td>${salesDetails.quantity}</td>
												<td>${salesDetails.rate}</td>
												<td>${salesDetails.bags}</td>
												<td>${salesDetails.lessInQuantity}</td>
												<td>${(salesDetails.quantity - salesDetails.lessInQuantity) * salesDetails.rate}</td>
												<td>${salesDetails.description}</td>
												<td>${salesDetails.updatedBy}</td>
												<td>${salesDetails.updatedOn}</td>
											</tr>
											<c:set var="total" value="${total + (salesDetails.quantity - salesDetails.lessInQuantity) * salesDetails.rate}" />
										</c:forEach>
									</tbody>
									<tfoot>
										<tr>
											<th></th>
											<th></th>
											<th></th>
											<th></th>
											<th>Grand total:</th>
											<th>${total}</th>
											<th></th>
											<th></th>
											<th></th>
										</tr>
									</tfoot>
								</table>
							</div>
							<!-- /.box-body -->
						</div>
						<!-- /.box -->
					</div>
					<!-- /.col -->
				</div>
				<!-- /.row -->
			</section>
				<div class="modal modal-danger fade" id="modal-danger">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title">Delete Confirmation</h4>
							</div>
							<div class="modal-body">
								<p>Are you sure you want to delete the Customer ?&hellip; </p>
								<p class="model-Customer" data=""></p>
							</div>
							<div class="modal-footer" align="center">
								<button type="button" class="btn btn-outline" data-dismiss="modal">Cancel</button>
								<a class="confirm" href = "#"><button type="button" class="btn btn-outline">Confirm delete</button></a>
							</div>
						</div>
						<!-- /.modal-content-->
					</div>
					<!-- /.modal-dialog  -->
				</div>

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
	
	<script>
		$('.modelLink').click(function(){
    	var dataId=$(this).attr('data-id');
    	$.ajax({url:"viewCustomers",
    			data:{ "dataId" : dataId },
    			cache:false,
    			success:
    			function(data){
        		$(".confirm").attr('href',"deleteCustomer/"+dataId+"/")
        		$(".model-Customer").text(dataId)
    		}});
		});
</script>
	
</body>
</html>
