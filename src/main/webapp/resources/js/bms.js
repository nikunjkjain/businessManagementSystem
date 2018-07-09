/**
 * This file contains the custom JavaScript exclusively written for Business Management System  
 */

function editSalesfun(){
	//alert('editSales called');
	var editSalesDetails = fillSalesDetailsInArray();
	callEditSalesController(editSalesDetails)
}

function fillSalesDetailsInArray() {
	//alert('fillSalesDetailsInArray called')
	var i = 0;
	var products = [] // declaring an empty array
	products = products;

	var productArr = document.getElementsByClassName('product');
	var quantityArr = document.getElementsByClassName('quantity');
	var bagsArr = document.getElementsByClassName('bags');
	var lessArr = document.getElementsByClassName('less');
	var rateArr = document.getElementsByClassName('rate');
	var descriptionArr = document.getElementsByClassName('description');
	var ntWtArr = document.getElementsByClassName('ntWt');
	var totalArr = document.getElementsByClassName('total');
	var salesId = document.getElementById('salesAndPaymentId').value
	
	for (i = 0; i < productArr.length; i++) {
		var productDetails = {
			"id" : salesId,
			"product" : productArr[i].value,
			"quantity" : quantityArr[i].value,
			"rate" : rateArr[i].value,
			"description" : descriptionArr[i].value,
			"bags" : bagsArr[i].value,
			"less" : lessArr[i].value
		}
		products.push(productDetails);
	}
	
	var details = {
			"customerId" : document.getElementById('customerId').value,
			"date" : document.getElementById('date').value,
			"reminder" : document.getElementById('reminder').value,
			"totalAmount" : document.getElementById('tamount').value,
			"comment" : document.getElementById('comment').value,
			"type" : "SALES",
			"mode" : "CASH",
			"payment" : 0,
			"id" : document.getElementById('salesAndPaymentId').value
		}
	products.push(details);
	console.log(JSON.stringify(products));
	//alert(JSON.stringify(products))
	return products;
}

function callEditSalesController(products) {
	console.log("===> function callEditSalesController");
	$.ajax({
		type : 'POST',
		dataType : 'html',
		contentType : 'application/json',
		url : "editSalesDetails",
		data : JSON.stringify(products),
		success : function() {
			console.log("===> function callEditSalesController Success");
		},
		error : function(xhr, textStatus, errorThrown) {
			console.log("===> function callEditSalesController Error");
			console.log("xhr:" + xhr);
			console.log("textStatus:" + textStatus);
			console.log("errorThrown:" + errorThrown);
		},
		complete : function() {
			console.log("===> function callEditSalesController completed");
			window.location.href = "viewCustomers";
		}
	});
};

function updateRowInfo(id) {
	var qty = document.getElementById('quantity' + id).value;
	var less = document.getElementById('less' + id).value;
	var rate = document.getElementById('rate' + id).value;
	document.getElementById('ntWt' + id).value = (qty - less);
	document.getElementById('total' + id).value = (qty - less) * rate;
	updateTotalInfo();
}

function updateTotalInfo() {
	//gettting all the elements as array with same class name
	var total = document.getElementsByClassName('total');
	var i;
	var grandtotal = 0;
	for (i = 0; i < total.length; i++) {
		grandtotal = grandtotal + parseInt(total[i].value)
	}
	document.getElementById('tamount').value = grandtotal;
}

function validatePasswords(){
    if($("#password").val() != $("#repassword").val())
    {
        alert("Password and Re-Password Should be same");
        return false;
    }
}