/**
 * This file contains the custom JavaScript exclusively written for Business Management System  
 */

function editSalesfun(){
	alert('editSales called');
	var editSalesDetails = fillSalesDetailsInArray();
	callEditSalesController(editSalesDetails)
}

function fillSalesDetailsInArray() {
	alert('fillSalesDetailsInArray called')
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
			"totalAmount" : document.getElementById('tamount').value,
			"comment" : document.getElementById('comment').value,
			"type" : "sales",
			"mode" : "cash",
			"payment" : 0,
			"id" : document.getElementById('salesAndPaymentId').value
		}
	products.push(details);
	console.log(JSON.stringify(products));
	alert(JSON.stringify(products))
	return products;
}

function callEditSalesController(products) {
	console.log("===> function callController");
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
			console.log("===> function callController Error");
			console.log("xhr:" + xhr);
			console.log("textStatus:" + textStatus);
			console.log("errorThrown:" + errorThrown);
		},
		complete : function() {
			console.log("===> function callEditSalesController completed");
			window.location.href = "viewUsers";
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


/*var products = [] // declaring an empty array
var i = 1;
var totalAmount = 0;
products = products;

function callController() {
	getElementsById();
	
	console.log("===> function callController");
	
	$.ajax({
		type : 'POST',
		dataType : 'html',
		contentType : 'application/json',
		url : "insertSales",
		data : JSON.stringify(products),
		success : function() {
			console.log("===> function callController Success");
		},
		error : function(xhr, textStatus, errorThrown) {
			console.log("===> function callController Error");
			console.log("xhr:" + xhr);
			console.log("textStatus:" + textStatus);
			console.log("errorThrown:" + errorThrown);
		},
		complete : function() {
			console.log("===> function callController completed");
			window.location.href = "viewUsers";
		}
	});
};

function Add() {
	console.log("===> function Add");
	AddRow(i, $("#product").val(), $("#quantity").val(), $("#rate").val(),
			$("#description").val(), $("#bags").val(), $("#less").val());
	$("#id").val("");
	$("#product").val("");
	$("#quantity").val("");
	$("#bags").val("");
	$("#less").val("");
	$("#rate").val("");
	$("#description").val("");
	$("#ntWt").val("");
	$("#total").val("");
	console.log("<=== function Add");
};

function AddElementsToArray(id, product, quantity, rate, description, bags, less){
	console.log("===> function AddRow");
	var productDetails = {
		"id" : id,
		"product" : product,
		"quantity" : quantity,
		"rate" : rate,
		"description" : description,
		"bags" : bags,
		"less" : less
	}
	products.push(productDetails);
	console.log("===> function AddRow Added to Sales Array");
}

function AddRow(id, product, quantity, rate, description, bags, less) {
	
	AddElementsToArray(id, product, quantity, rate, description, bags, less);
	
	//TODO:delete below  comments
	 console.log("===> function AddRow");
	var productDetails = {
		"id" : id,
		"product" : product,
		"quantity" : quantity,
		"rate" : rate,
		"description" : description,
		"bags" : bags,
		"less" : less
	}
	products.push(productDetails);
	console.log("===> function AddRow Added to Sales Array"); 
	
	//Get the reference of the Table's TBODY element.
	i++;
	var tBody = $("#tblCustomers > TBODY")[0];

	//Add Row.
	row = tBody.insertRow(-1);

	//Add srNo cell.
	var cell = $(row.insertCell(-1));
	cell.html(id);

	//Add product cell.
	cell = $(row.insertCell(-1));
	cell.html(product);

	//Add quantity cell.
	cell = $(row.insertCell(-1));
	cell.html(quantity);

	//Add bags cell.
	cell = $(row.insertCell(-1));
	cell.html(bags);

	//Add less cell.
	cell = $(row.insertCell(-1));
	cell.html(less);

	//Add rate cell.
	cell = $(row.insertCell(-1));
	cell.html(rate);

	//Add description cell.
	cell = $(row.insertCell(-1));
	cell.html(description);

	//Add ntWt cell.
	cell = $(row.insertCell(-1));
	cell.html(quantity - less);

	//Add total cell.
	cell = $(row.insertCell(-1));
	cell.html((quantity - less) * rate);

	//Add Button cell.
	cell = $(row.insertCell(-1));
	var btnRemove = $("<input />");
	btnRemove.attr("type", "button");
	btnRemove.attr("onclick", "Remove(this, id);");
	btnRemove.val("x");
	cell.append(btnRemove);
	
	totalAmount += (quantity - less) * rate;
	
	$("#tamount").val(totalAmount);
	
	console.log("<=== function AddRow");
};

function Remove(button) {
		console.log("===> function Remove");
	//Determine the reference of the Row using the Button.
	var row = $(button).closest("TR");

	var name = $("TD", row).eq(0).html();

	//retriving the cell value from the row reference
	var rowID = row[0].cells[0].innerHTML;
	var pcode = row[0].cells[1].innerHTML;
	var pqty = row[0].cells[2].innerHTML;
	var pbags = row[0].cells[3].innerHTML;
	var pless = row[0].cells[4].innerHTML;
	var prate = row[0].cells[5].innerHTML;
	
	console.log("rowID:"+rowID +" pcode:"+ pcode +" pqty:"+ pqty +" pbags:"+ pbags +" pless:"+ pless +" prate:"+ prate);

	if (confirm("Confirm delete row with Id: " + rowID)) {

		//Get the reference of the Table.
		var table = $("#tblCustomers")[0];

		//subtracting current row total from total amount
		totalAmount = totalAmount - ((pqty - pless) * prate);
		$("#tamount").val(totalAmount);
		
		// function to remove the element from the array
		removeElementFromArrary(rowID)

		//Delete the Table row using it's Index.
		table.deleteRow(row[0].rowIndex);
	}
	console.log("<===  function Remove");
};

function removeElementFromArrary(row) {
	console.log('====> removeElement')
	for (elementIdx in products) {
		console.log('Object.keys(Sales.products[elementIdx]): '
				+ Object.keys(products[elementIdx]))
		var key = Object.keys(products[elementIdx])[0]
		console.log('Sales.products[x][key]: ' + products[elementIdx][key])
		var id = products[elementIdx][key]
		if (id == row) {
			console.log('Deleting Row Id: ' + id)
			products.splice(elementIdx, 1);
			break;
		}
	}
	console.log("JSON.stringify(products): " + JSON.stringify(products));
}

function getElementsById() {
	var details = {
		"customerId" : document.getElementById('customerId').value,
		"date" : document.getElementById('date').value,
		"totalAmount" : document.getElementById('tamount').value,
		"comment" : document.getElementById('comment').value,
		"type" : "sales",
		"mode" : "cash",
		"payment" : 0
	}
	products.push(details);
	alert(JSON.stringify(products));
	console.log('Printing products Json in getElementsById');
	console.log(JSON.stringify(products));
	alert('Printing products Json in getElementsById After');
}*/