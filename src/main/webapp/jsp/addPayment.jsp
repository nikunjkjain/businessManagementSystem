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
		<div class="content-wrapper" style="overflow: scroll;">
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
						<div class="box box-primary" style="overflow: scroll;">
							<div class="box-header with-border">
								<h3 class="box-title">Enter Payment Details</h3>
							</div>
							<!-- /.box-header -->
							<!-- form start -->
							<form:form action="insertPayment" method="post"
								class="form-horizontal">
								<div class="box-body">
									<div class="form-group">
										<label for="date" class="col-sm-2 control-label">Payment Date:</label>

										<div class="col-sm-4">
											<input type="date" name="date" id="date">
										</div>
										
										<label for="Party" class="col-sm-2 control-label">Party:</label>

										<div class="col-sm-4">
										<select required id="customerId" name="customerId">
												<option value = "">Select Party</option>
												<c:forEach items="${customerList}" var="customerList">
													<option value="${customerList.id}">${customerList.name}</option>
												</c:forEach>
											</select>
										</div>
										
									</div>

									<div class="form-group">
										<label for="payment" class="col-sm-2 control-label">Type:</label>
										<div class="col-sm-4">
											<select id="type" name="type">
											<c:set value="${sessionScope.MKEYVAL['ADDPAY']}" var="mIdKeyValMap"></c:set>
												<c:forEach items="${mIdKeyValMap}" var="mKeyValMap">
												<c:set var="key" value="${mKeyValMap.key}"></c:set>
													<c:choose>
													    <c:when test="${key=='RECEIPT'}">
													    	<option selected value="${key}">${key}</option>
													    </c:when>    
													    <c:otherwise>
													    	<option value="${key}">${key}</option>
													    </c:otherwise>
													</c:choose>
												</c:forEach>
											</select>
										</div>

										<label for="mode" class="col-sm-2 control-label">Mode:</label>

										<div class="col-sm-4">
											<select id="mode" name="mode">
												<c:set value="${sessionScope.MKEYVAL['MODE']}" var="mIdKeyValMap"></c:set>
												<c:forEach items="${mIdKeyValMap}" var="mKeyValMap">
													<c:choose>
													    <c:when test="${mKeyValMap.key=='CASH'}">
													    	<option selected value="${mKeyValMap.key}">${mKeyValMap.key}</option>
													    </c:when>    
													    <c:otherwise>
													    	<option value="${mKeyValMap.key}">${mKeyValMap.key}</option>
													    </c:otherwise>
													</c:choose>
												</c:forEach>
											</select>
										</div>
									</div>
									
									<div class="form-group">
										<label for="amount" class="col-sm-2 control-label">Amount:</label>

										<div class="col-sm-4">
											<input type="number" class="form-control" id="amount" name="amount"
												placeholder="Amount" required="required">												
										</div>
										
										<label for="reminder" class="col-sm-2 control-label">Reminder:</label>
												<div class="col-sm-2">
											<input type="date" name="reminder" id="reminder">
										
										</div>
										
										</div>
										<div class="form-group">
								
										<label for="Comment" class="col-sm-2 control-label">Comment:</label>

										<div class="col-sm-10">
											<input type="text" class="form-control" id="comment" name="comment"
												placeholder="Comment" value="">
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
