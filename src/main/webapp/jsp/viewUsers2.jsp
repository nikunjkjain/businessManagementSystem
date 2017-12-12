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
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">

<%@include file="/jsp/jspf/headerScripts.jspf"%>

<!-- Bootstrap 3.3.7 -->
  <%-- <link rel="stylesheet" href="<c:url value ="resources/bower_components/bootstrap/dist/css/bootstrap.min.css"/>">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="<c:url value ="resources/bower_components/font-awesome/css/font-awesome.min.css"/>">
  <!-- Ionicons -->
  <link rel="stylesheet" href="<c:url value ="resources/bower_components/Ionicons/css/ionicons.min.css"/>">
  <!-- DataTables -->
  <link rel="stylesheet" href="<c:url value ="resources/bower_components/datatables.net-bs/css/dataTables.bootstrap.min.css"/>">
  <!-- Theme style -->
  <link rel="stylesheet" href="<c:url value ="resources/dist/css/AdminLTE.min.css"/>">
  <!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
  <link rel="stylesheet" href="<c:url value ="resources/dist/css/skins/_all-skins.min.css"/>"> --%>

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
					<li class="active">Users</li>
				</ol>
			</section>

			<section class="content">
				<div class="row">
					<div class="col-xs-12">

						<div class="box">
							<div class="box-header">
								<h3 class="box-title">
									<a href="addUser">Add Users <i class="fa fa-user-plus"></i></a>
								</h3>
							</div>
							<!-- /.box-header -->
							<div class="box-body">
								<table id="example1" class="table table-bordered table-striped">
									<thead>
										<tr>
											<th>ID</th>
											<th>Name</th>
											<th>User name</th>
											<th>Mobile</th>
											<th>Status</th>
											<th>Actions</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${userList}" var="userList">
											<tr>
												<td>${userList.id}</td>
												<td>${userList.name}</td>
												<td>${userList.username}</td>
												<td>${userList.mobileNo}</td>
												<td>${userList.status}</td>
												<td><a class="btn btn-success btn-xs"> View <i
														class="fa fa-search-plus"></i></a> <a href="editUser"
													class="btn btn-info btn-xs">Edit <i class="fa fa-edit"></i></a>
													<a class="btn btn-danger btn-xs" data-toggle="modal"
													data-target="#modal-danger"> Delete <i
														class="fa fa-remove"></i></a></td>
											</tr>
										</c:forEach>
									</tbody>
									<tfoot>
										<tr>
											<th>ID</th>
											<th>Name</th>
											<th>User name</th>
											<th>Mobile</th>
											<th>Status</th>
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
								<p>Are you sure you want to delete the User ?&hellip;</p>
							</div>
							<div class="modal-footer" align="center">
								<button type="button" class="btn btn-outline"
									data-dismiss="modal">Cancel</button>
								<button type="button" class="btn btn-outline">Confirm
									delete</button>
							</div>
						</div>
						<!-- /.modal-content -->
					</div>
					<!-- /.modal-dialog -->
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
<!-- jQuery 3 -->
 <%-- <script src="<c:url value="resources/bower_components/jquery/dist/jquery.min.js"/>"></script>
<!-- Bootstrap 3.3.7 -->
<script src="<c:url value="resources/bower_components/bootstrap/dist/js/bootstrap.min.js"/>"></script>
<!-- DataTables -->
<script src="<c:url value="resources/bower_components/datatables.net/js/jquery.dataTables.min.js"/>"></script>
<script src="<c:url value="resources/bower_components/datatables.net-bs/js/dataTables.bootstrap.min.js"/>"></script>
<!-- SlimScroll -->
<script src="<c:url value="resources/bower_components/jquery-slimscroll/jquery.slimscroll.min.js"/>"></script>
<!-- FastClick -->
<script src="<c:url value="resources/bower_components/fastclick/lib/fastclick.js"/>"></script>
<!-- AdminLTE App -->
<script src="<c:url value="resources/dist/js/adminlte.min.js"/>"></script>
<!-- AdminLTE for demo purposes -->
<script src="<c:url value="resources/dist/js/demo.js"/>"></script>
<!-- page script -->
<script>
  $(function () {
    $('#example1').DataTable()
    $('#example2').DataTable({
      'paging'      : true,
      'lengthChange': false,
      'searching'   : false,
      'ordering'    : true,
      'info'        : true,
      'autoWidth'   : false
    })
  })
  </script> --%> 

</body>
</html>
