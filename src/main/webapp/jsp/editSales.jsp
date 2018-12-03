<%@ page contentType="text/html; charset=iso-8859-1" language="java"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>BMS | Sales</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">

<%@include file="/jsp/jspf/headerScripts.jspf"%>
<link rel="stylesheet" href="<c:url value ="/resources/bower_components/bootstrap-datepicker/dist/css/bootstrap-datepicker.min.css"/>">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->

<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>

<style type="text/css">
.row{
  overflow-x:scroll;
}
</style>

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
				<h1>Sales</h1>
				<ol class="breadcrumb">
					<li><a href="index"><i class="fa fa-dashboard"></i> Home</a></li>
					<li class="active">Edit Sales</li>
				</ol>
			</section>

			<section class="content">
				<div class="row">
					<div class="col-xs-12">
						<div class="box box-primary" style="overflow: scroll;">
						<form action = "javascript:editSalesfun()" method = "post">
							 <div class="box-header">
									<%-- <div class="form-group">
										<label for="name" class="col-sm-1 control-label">SalesId:</label>
										<div class="col-sm-1">
											<input type="text" class="form-control"
												name="salesAndPaymentId" id="salesAndPaymentId"
												value="${salesAndPayment.id}" readonly="readonly">
										</div>
										<label for="partyName" class="col-sm-2 control-label">Name:</label>
										<div class="col-sm-4">
											<c:set var="cid" scope="session">${salesAndPayment.customerId}</c:set>
											<input type="number" class="form-control"
												value="${sessionScope.CVALKEY[cid]}" name="cId" id="cId"
												readonly="readonly">
												<input type="number" class="form-control"
												value="${sessionScope.CVALKEY[cid]}" name="cId" id="cId"
												readonly="readonly"> 
												<input type="hidden"
												name="customerId" id="customerId"
												value="${salesAndPayment.customerId}" required>
										</div>
										<label for="sales date" class="col-sm-2 control-label">Sales
											Date:</label>
										<div class="col-sm-2">
											<input class="form-control" type="date" name="date" id="date"
												value="${salesAndPayment.date}" readonly="readonly">
										</div>
									</div>
									
									<div class="form-group">
										<label for="comments" class="col-sm-1 control-label">Commnets:</label>
										<div class="col-sm-4">
											<input class="form-control" type="text" name="comment" id="comment" value="${salesAndPayment.comment}">
										</div>
									</div> --%>

							<div class="col-sm-12">
							 	<label>Sales Id: </label>
							 	<input type="text" name="salesAndPaymentId" id="salesAndPaymentId" value="${salesAndPayment.id}" readonly="readonly">
							 	&nbsp;&nbsp;
							 	<label>Party Name: </label>
								<c:set var="cid">${salesAndPayment.customerId}</c:set>
								<input type="text" value="${sessionScope.CVALKEY[cid]}" name="cId" id="cId" readonly="readonly">
								<input type="hidden" name="customerId" id="customerId" value="${salesAndPayment.customerId}" required> 
								&nbsp;&nbsp;
								<label>Sales Date: </label> 
								<input type="date" name="date" id="date" value="${salesAndPayment.salesDate}" required>
								&nbsp;&nbsp;
								<label>Reminder: </label> 
								<input type="date" name="reminder" id="reminder" value="${salesAndPayment.reminder}" required>
							</div>
							<div class="col-sm-12">
							<br/>
								<label>Comment: </label> 
								<input type="text" size="70" name="comment" id="comment" value="${salesAndPayment.comment}">
							</div>
							</div>
							<!-- /.box-header -->
							<div class="box-body">
								<table id="tblCustomers"
									border="1" class="table table-bordered">
									<thead>
										<tr>
											<th>Sr No</th>
											<th>Product</th>
											<th>Qty (kg)</th>
											<th>Bags</th>
											<th>Less (Kg)</th>
											<th>Rate (&#x20B9;)</th>
											<th>Desc</th>
											<th>Nt.Wt (kg)</th>
											<th>Total (&#x20B9;)</th>
											<td></td>
										</tr>
									</thead>
									<tbody>
									<c:set var="count" scope="session" value="0" />
									<c:set var="grandTotal" scope="session" value="0" />
									<c:set var="productTotal" scope="session" value="0" />
									<c:set var="netWeight" scope="session" value="0" />
									<c:forEach items="${salesDetailsList}" var="salesDetailsList">
									<c:set var="count" value="${count + 1}" />
									<c:set var="netWeight" value="${salesDetailsList.quantity - salesDetailsList.lessInQuantity}" />
									<c:set var="productTotal" value="${(salesDetailsList.quantity - salesDetailsList.lessInQuantity) * salesDetailsList.rate}" />
									<c:set var="grandTotal" value="${grandTotal + productTotal}" />
									<c:set var="pid">${salesDetailsList.productId}</c:set>
										<tr>
											<td><input class="col-md-12" type="number" id="num" name="num" readonly="readonly" value="${count}"/></td>
											<td><input class="col-md-12" type="text" id="product1${count}" name="product1${count}" readonly="readonly" value="${sessionScope.PVALKEY[pid]}"></td>
											<input class = "product" type ="hidden" id="product${count}" name="product${count}" value="${salesDetailsList.productId}"/>
											<td><input class="col-md-12 quantity" type="number" onChange = "updateRowInfo(${count});" id="quantity${count}" name="quantity${count}" value="${salesDetailsList.quantity}"/></td>
											<td><input class="col-md-12 bags" type="text" id="bags${count}" name="bags${count}" value="${salesDetailsList.bags}"/></td>
											<td><input class="col-md-12 less" type="number" onChange = "updateRowInfo(${count});" id="less${count}" name="less${count}" value="${salesDetailsList.lessInQuantity}"/></td>
											<td><input class="col-md-12 rate" type="number" onChange = "updateRowInfo(${count});" id="rate${count}" name="rate${count}" value="${salesDetailsList.rate}"/></td>
											<td><input class="col-md-12 description" type="text" id="description${count}" name="description${count}" value="${salesDetailsList.description}"/></td>
											<td><input class="col-md-12 ntWt" type="text" id="ntWt${count}" name="ntWt${count}" readonly="readonly" value="${netWeight}"/></td>
											<td><input class="col-md-12 total" type="text" id="total${count}" name="total${count}" readonly="readonly" value="${(salesDetailsList.quantity - salesDetailsList.lessInQuantity) * salesDetailsList.rate}"></td>
											<td></td>
										</tr>
										</c:forEach>
									</tbody>
									<tfoot>
										<tr>
										<td colspan="8" align="right"><b>Extra Charge:</b></td>
										<td><input type="number" value="${salesAndPayment.eCharge}" id="echarge" required ></td>
										<td>
										<input type="button" id ="buttonC" onclick="calculateTotal(this)" value="-" required/>
										</td>
										</tr>
										<tr>
										<td colspan="8" align="right"><b>Total Amount:</b></td>
										<td ><input class="col-md-12" type="number" id="tamount" value="${grandTotal + salesAndPayment.eCharge}" readonly="readonly"></td>
										<td><input type="submit" value="Submit" /></td>
										</tr>
									</tfoot>
								</table>
							</div>
							</form>
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

<!-- Page script -->
<script>
  $(function () {
    //Initialize Select2 Elements
    $('.select2').select2()

    //Datemask dd/mm/yyyy
    $('#datemask').inputmask('dd/mm/yyyy', { 'placeholder': 'dd/mm/yyyy' })
    //Datemask2 mm/dd/yyyy
    $('#datemask2').inputmask('mm/dd/yyyy', { 'placeholder': 'mm/dd/yyyy' })
    //Money Euro
    $('[data-mask]').inputmask()

    //Date range picker
    $('#reservation').daterangepicker()
    //Date range picker with time picker
    $('#reservationtime').daterangepicker({ timePicker: true, timePickerIncrement: 30, format: 'MM/DD/YYYY h:mm A' })
    //Date range as a button
    $('#daterange-btn').daterangepicker(
      {
        ranges   : {
          'Today'       : [moment(), moment()],
          'Yesterday'   : [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
          'Last 7 Days' : [moment().subtract(6, 'days'), moment()],
          'Last 30 Days': [moment().subtract(29, 'days'), moment()],
          'This Month'  : [moment().startOf('month'), moment().endOf('month')],
          'Last Month'  : [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
        },
        startDate: moment().subtract(29, 'days'),
        endDate  : moment()
      },
      function (start, end) {
        $('#daterange-btn span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'))
      }
    )

    //Date picker
    $('#datepicker').datepicker({
      autoclose: true
    })

    //iCheck for checkbox and radio inputs
    $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
      checkboxClass: 'icheckbox_minimal-blue',
      radioClass   : 'iradio_minimal-blue'
    })
    //Red color scheme for iCheck
    $('input[type="checkbox"].minimal-red, input[type="radio"].minimal-red').iCheck({
      checkboxClass: 'icheckbox_minimal-red',
      radioClass   : 'iradio_minimal-red'
    })
    //Flat red color scheme for iCheck
    $('input[type="checkbox"].flat-red, input[type="radio"].flat-red').iCheck({
      checkboxClass: 'icheckbox_flat-green',
      radioClass   : 'iradio_flat-green'
    })

    //Colorpicker
    $('.my-colorpicker1').colorpicker()
    //color picker with addon
    $('.my-colorpicker2').colorpicker()

    //Timepicker
    $('.timepicker').timepicker({
      showInputs: false
    })
  })
</script>

</body>
</html>
