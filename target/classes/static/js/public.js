/**
 * 
 */
var contextPath="/topSearch"

function getRandomStr(){
	return (((1+Math.random())* 0x10000) | 0).toString(16).substring(1);
}

function getRandomUuid(){
	var uuid="";
	for(var i=0;i<8;i++){
		uuid+=getRandomStr();
	}
	return uuid;
}

function getD(){
	var day = new Date();
	var y=day.getFullYear();
	var m=day.getMonth()+1<10?'0'+(day.getMonth()+1):day.getMonth()+1;
	var d=day.getDate()<10?('0'+day.getDate()):day.getDate();
//	$('.date').val( y+"-" + m + "-" + d);
}