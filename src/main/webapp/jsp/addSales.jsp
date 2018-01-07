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

<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<script type="text/javascript">
		console.log("Loading Script");
		var Sales = {};
		var products = []
		var i = 0;
		Sales.products = products;
		console.log("Script Loaded");
		function callController() {
			console.log("===> function callController");
        	$.ajax({
        		type: 'POST',
        		dataType: 'html',
        		contentType:'application/json',
        		url: "insertSales",
        		data:JSON.stringify(products),
        		success: function(){
        		console.log("===> function callController Success");
        		},
        		error: function(xhr, textStatus, errorThrown){
        		console.log("===> function callController Error");
        		console.log("xhr:"+xhr);
        		console.log("textStatus:"+textStatus);
        		console.log("errorThrown:"+errorThrown);
        		},
        		complete:function(){
        			console.log("===> function callController completed");
        			window.location.href="viewUsers";
        		}
        		});
		};
		
        function Add() {
        	console.log("===> function Add");
            AddRow($("#srNo").val(), $("#product").val(), $("#quantity").val(), $("#rate").val(), $("#description").val());
            $("#srNo").val("");
            $("#product").val("");
            $("#quantity").val("");
            $("#rate").val("");
            $("#description").val("");
            console.log("<=== function Add");
        };

        function AddRow(srNo, product, quantity, rate, description) {
        	console.log("===> function AddRow");
        	var productDetails = {
        		"srNo":srNo, 
        		"product":product, 
        		"quantity":quantity, 
        		"rate":rate, 
        		"description":description}
        	
        	Sales.products.push(productDetails);
        	console.log("===> function AddRow Added to Sales Array");
            //Get the reference of the Table's TBODY element.
            i++;
            var tBody = $("#tblCustomers > TBODY")[0];
            
            //Add Row.
            row = tBody.insertRow(-1);

            //Add srNo cell.
            var cell = $(row.insertCell(-1));
            cell.html(srNo);

            //Add product cell.
            cell = $(row.insertCell(-1));
            cell.html(product);
            
          //Add quantity cell.
            cell = $(row.insertCell(-1));
            cell.html(quantity);
            
          //Add rate cell.
            cell = $(row.insertCell(-1));
            cell.html(rate);
            
          //Add description cell.
            cell = $(row.insertCell(-1));
            cell.html(description);

            //Add Button cell.
            cell = $(row.insertCell(-1));
            var btnRemove = $("<input />");
            btnRemove.attr("type", "button");
            btnRemove.attr("onclick", "Remove(this);");
            btnRemove.val("Remove");
            cell.append(btnRemove);
            console.log("<=== function AddRow");
        };

        function Remove(button) {
        	console.log("===> function Remove");
            //Determine the reference of the Row using the Button.
            var row = $(button).closest("TR");
            var name = $("TD", row).eq(0).html();
            if (confirm("Do you want to delete: " + srNo)) {

                //Get the reference of the Table.
                var table = $("#tblCustomers")[0];

                //Delete the Table row using it's Index.
                table.deleteRow(row[0].rowIndex);
            }
            console.log("<===  function Remove");
        };
        
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
				<h1>Users</h1>
				<ol class="breadcrumb">
					<li><a href="index"><i class="fa fa-dashboard"></i> Home</a></li>
					<li class="active">Products</li>
				</ol>
			</section>

			<section class="content">
				<div class="row">
					<div class="col-xs-12">

						<div class="box">
							<div class="box-header">
								<h3 class="box-title">
									<a href="addProduct">Add Product <i class="fa fa-user-plus"></i></a>
								</h3>
							</div>
							<!-- /.box-header -->
							<div class="box-body">
								<table id="tblCustomers" cellpadding="0" cellspacing="0"
									border="1" class="table table-bordered">
									<thead>
										<tr>
											<th>Sr No</th>
											<th>Product</th>
											<th>Qty</th>
											<th>Rate</th>
											<th>Desc</th>
											<th>Action</th>
										</tr>
									</thead>
									<tbody>
									</tbody>
									<tfoot>
										<tr>
											<td><input type="text" id="srNo" name="srNo"/></td>
											<td><input type="text" id="product" name="product"/></td>
											<td><input type="text" id="quantity" name="quantity"/></td>
											<td><input type="text" id="rate" name="rate"/></td>
											<td><input type="text" id="description" name="description"/></td>
											<td><input type="button" onclick="Add()" value="Add" /></td>
										</tr>
										<tr><td><input type="button" onclick="callController()" value="Call" /></td></tr>
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
							<p>Are you sure you want to delete the Product ?&hellip;</p>
							<p class="model-user" data="abc"></p>
						</div>
						<div class="modal-footer" align="center">
							<button type="button" class="btn btn-outline"
								data-dismiss="modal">Cancel</button>
							<a class="confirm" href="#"><button type="button"
									class="btn btn-outline">Confirm delete</button></a>
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
