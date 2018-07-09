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

<script type="text/javascript">
   function setDate(){
  	  document.getElementById('date').valueAsDate = new Date();
  	  var today = new Date();
  	  today.setDate(today.getDate() + 10); 
  	  document.getElementById('reminder').valueAsDate = today;
  }
  window.onload = setDate;
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
				<h1>Sales</h1>
				<ol class="breadcrumb">
					<li><a href="index"><i class="fa fa-dashboard"></i> Home</a></li>
					<li class="active">Sales</li>
				</ol>
			</section>

			<section class="content">
				<div class="row">
					<div class="col-xs-12">

						<div class="box box-primary" style="overflow: scroll;">
						<form action = "javascript:callController()" method = "post">
							 <div class="box-header">
							 
								<label>Party Name: </label> 
								<select required id="customerId" name="customerId">
									<option value="" selected="selected">Select Party</option>
									<c:forEach items="${customerList}" var="customerList">
										<option value="${customerList.id}">${customerList.name}</option>
									</c:forEach>
								</select>
								&nbsp;&nbsp;&nbsp;
								<label>Sales Date: </label> 
								<input type="date" name="date" id="date" required>
								
								&nbsp;&nbsp;&nbsp;
								<label>Reminder: </label> 
								<input type="date" name="reminder" id="reminder" required>
								
								&nbsp;&nbsp;&nbsp;
								<label>Comment: </label> 
								<input type="text" size="45" name="comment" id="comment"/>
							</div>
							<!-- /.box-header -->
							<div class="box-body">
								<table id="tblCustomers"
									border="1" class="table table-bordered">
									<thead>
										<tr>
											<th>Id</th>
											<th>Product</th>
											<th>Qty (kg)</th>
											<th>Bags</th>
											<th>Less (Kg)</th>
											<th>Rate (&#x20B9;)</th>
											<th>Desc</th>
											<th>Nt.Wt (kg)</th>
											<th>Total (&#x20B9;)</th>
											<th>+/-</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td><label id="id">-</label></td>
											<td><select id="product" name="product">
													<option selected="selected"></option>
													<c:forEach items="${productList}" var="productList">
														<option value="${productList.id}">${productList.name}</option>
													</c:forEach>
											</select></td>
											<td><input class="col-md-12" type="number" id="quantity"
												name="quantity"/></td>
											<td><input class="col-md-12" type="text" id="bags"
												name="bags"/></td>
											<td><input class="col-md-12" type="number" id="less"
												name="less" value="0"/></td>
											<td><input class="col-md-12" type="number" id="rate"
												name="rate"/></td>
											<td><input class="col-md-12" type="text"
												id="description" name="description" /></td>
											<td><label id="ntWt">-</label></td>
											<td><label id="total">-</label></td>
											<td><input type="button" onclick="Add()" value="+" required/></td>
										</tr>
									</tbody>
									<tfoot>
										<tr>
										<!-- <td ><input type="button" onclick="callController()" value="Call" /></td> -->
										<td ><input type="submit" value="Submit" /></td>
										<td colspan="7" align="right"><b>Total Amount:</b></td>
										<td ><input type="number" id="tamount" required></td>
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
