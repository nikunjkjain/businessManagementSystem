<%@ page contentType="text/html; charset=iso-8859-1" language="java"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>BMS | Products</title>
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
				<h1>Users</h1>
				<ol class="breadcrumb">
					<li><a href="index"><i class="fa fa-dashboard"></i> Home</a></li>
					<li class="active">Products</li>
				</ol>
			</section>

			<section class="content">
				<div class="row" style="overflow: scroll;">
					<div class="col-xs-12">

						<div class="box">
							<div class="box-header">
								<h3 class="box-title">
									<a href="addProduct">Add Product <i class="fa fa-user-plus"></i></a>
								</h3>
							</div>
							<!-- /.box-header -->
							<div class="box-body">
								<table id="example1" class="table table-bordered table-striped">
									<thead>
										<tr>
											<th>Product Id</th>
											<th>Name</th>
											<th>UnitPrice</th>
											<th>unit Measure</th>
											<th>Updated by</th>
											<th>Updated On</th>
											<th>Actions</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${productList}" var="productList">
											<tr>
												<td>${productList.id}</td>
												<td>${productList.name}</td>
												<td>${productList.unitPrice}</td>
												<td>${productList.unitMeasure}</td>
												<td>${productList.updatedBy}</td>
												<td>${productList.updatedOn}</td>
												<td>
												<a href="viewProduct/${productList.id}/" class="btn btn-success btn-xs"> View <i class="fa fa-search-plus"></i></a> 
												<a href="editProduct/${productList.id}/" class="btn btn-info btn-xs">Edit <i class="fa fa-edit"></i></a>
												<a class="btn btn-danger btn-xs modelLink" data-toggle="modal" data-target="#modal-danger" data-id="${productList.id}"> Delete <i class="fa fa-remove"></i></a>
												</td>
											</tr>
										</c:forEach>
									</tbody>
									<tfoot>
										<tr>
											<th>Product Id</th>
											<th>Name</th>
											<th>UnitPrice</th>
											<th>unit Measure</th>
											<th>Updated by</th>
											<th>Updated On</th>
											<th>Actions</th>
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
								<p>Are you sure you want to delete the Product ?&hellip; </p>
								<p class="model-user" data="abc"></p>
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
    	$.ajax({url:"viewProducts",
    			data:{ "dataId" : dataId },
    			cache:false,
    			success:
    			function(data){
        		$(".confirm").attr('href',"deleteProduct/"+dataId+"/")
        		$(".model-user").text(dataId)
    		}});
		});
</script>
	
</body>
</html>
