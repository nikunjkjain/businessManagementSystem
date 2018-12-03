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
			<c:set var="cid">${id}</c:set>
				<h1>${sessionScope.CVALKEY[cid]} Detail Ledger </h1>
				<ol class="breadcrumb">
					<li><a href="index"><i class="fa fa-dashboard"></i> Home</a></li>
					<li class="active">Ledger</li>
				</ol>
			</section>
			<section class="content">
				<div class="row">
					<div class="col-xs-12">

						<div class="box" style="overflow: scroll;">
							<!-- <div class="box-header">
								<h4 class="box-title">
									View Ledger
								</h4>
							</div> -->
							<!-- /.box-header -->
							<div class="box-body">
								<table id="ledger1" class="table table-bordered table-striped">
									<thead>
										<tr>
											<th>Date</th>
											<th>SalesId</th>
											<th>type</th>
											<th>Product</th>
											<th>Qty</th>
											<th>bags</th>
											<th>less</th>
											<th>Rate</th>
											<th>Total</th>
											<th>Description</th>
											<th>Action</th>
										</tr>
									</thead>
									<tbody>
										<c:set var="idPrev" scope="request" value="0" />
										<c:set var="idCurr" scope="request" value="0" />
										<c:forEach items="${ledgerDetailList}" var="ledgerDetailList">
											<c:if test="${idPrev == 0 }">
											 <c:set var="idPrev" scope="request" value="${ledgerDetailList.id}" />
											</c:if>
											<c:set var="idCurr" scope="request" value="${ledgerDetailList.id}" />
											<c:choose>
											<c:when test="${idPrev == idCurr}">
											<tr>
												<td>${ledgerDetailList.date}</td>
												<td>${ledgerDetailList.id}</td>
												<td>${ledgerDetailList.type}</td>
												<c:set var="pid">${ledgerDetailList.productId}</c:set>
												<td>${sessionScope.PVALKEY[pid]}</td>
												<td>${ledgerDetailList.quantity}</td>
												<td>${ledgerDetailList.bags}</td>
												<td>${ledgerDetailList.lessInQuantity}</td>
												<td>${ledgerDetailList.rate}</td>
												<td>${ledgerDetailList.total}</td>
												<td>${ledgerDetailList.description}</td>
												<c:choose>
												<c:when test="${ledgerDetailList.type != 'SALES'}">
													<td>
														<a href="viewPaymentDetails/${ledgerDetailList.id}/" class="btn btn-success btn-xs"> View <i class="fa fa-search-plus"></i></a> 
														<a href="editPaymentDetails/${ledgerDetailList.id}/" class="btn btn-info btn-xs">Edit <i class="fa fa-edit"></i></a>
													</td>
												</c:when>
												<c:otherwise>
													<td>
														<a href="viewSalesDetails/${ledgerDetailList.id}/" class="btn btn-success btn-xs"> View <i class="fa fa-search-plus"></i></a>
														<a href="editSales/${ledgerDetailList.id}/" class="btn btn-info btn-xs"> Edit <i class="fa fa-edit"></i></a>
													</td>
												</c:otherwise>
											</c:choose>
											</tr>
											<c:set var="amtPrev" scope="session" value="${ledgerDetailList.amount}" />
											<c:set var="extraCharge" scope="session" value="${ledgerDetailList.extraCharge}" />
											</c:when>
											<c:otherwise>
											<tr>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td align="right"><b>Extra Charge:</b></td>
												<td><b>${extraCharge}</b></td>
												<td align="right"><b>Total:</b></td>
												<td><b>${amtPrev}</b></td>
												<td></td>
												<td></td>
											</tr>
											<tr>
												<td>${ledgerDetailList.date}</td>
												<td>${ledgerDetailList.id}</td>
												<td>${ledgerDetailList.type}</td>
												<c:set var="pid">${ledgerDetailList.productId}</c:set>
												<td>${sessionScope.PVALKEY[pid]}</td>
												<td>${ledgerDetailList.quantity}</td>
												<td>${ledgerDetailList.bags}</td>
												<td>${ledgerDetailList.lessInQuantity}</td>
												<td>${ledgerDetailList.rate}</td>
												<td>${ledgerDetailList.total}</td>
												<td>${ledgerDetailList.description}</td>
												<c:choose>
												<c:when test="${ledgerDetailList.type != 'SALES'}">
													<td>
														<a href="viewPaymentDetails/${ledgerDetailList.id}/" class="btn btn-success btn-xs"> View <i class="fa fa-search-plus"></i></a> 
														<a href="editPaymentDetails/${ledgerDetailList.id}/" class="btn btn-info btn-xs">Edit <i class="fa fa-edit"></i></a>
													</td>
												</c:when>
												<c:otherwise>
													<td>
														<a href="viewSalesDetails/${ledgerDetailList.id}/" class="btn btn-success btn-xs"> View <i class="fa fa-search-plus"></i></a>
														<a href="editSales/${ledgerDetailList.id}/" class="btn btn-info btn-xs"> Edit <i class="fa fa-edit"></i></a>
													</td>
												</c:otherwise>
											</c:choose>
											</tr>
											<c:set var="idPrev" scope="request" value="${ledgerDetailList.id}" />
											<c:set var="amtPrev" scope="request" value="${ledgerDetailList.amount}" />
											<c:set var="extraCharge" scope="session" value="${ledgerDetailList.extraCharge}" />
											</c:otherwise>
											</c:choose>
										</c:forEach>
									</tbody>
									<tfoot>
										<tr>
											<th>Date</th>
											<th>SalesId</th>
											<th>type</th>
											<th>Product</th>
											<th>Qty</th>
											<th>bags</th>
											<th>less</th>
											<th>Rate</th>
											<th>Total</th>
											<th>Description</th>
											<th>Action</th>
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
								<p>Are you sure you want to delete Sales ?&hellip; </p>
								<p class="model-ledger" data="abc"></p>
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
    	$.ajax({url:"viewLedger/"+dataId+"/",
    			data:{ "dataId" : dataId },
    			cache:false,
    			success:
    			function(data){
        		$(".confirm").attr('href',"deleteSales/"+dataId+"/")
        		$(".model-ledger").text(dataId)
    		}});
		});
</script>
	
</body>
</html>
