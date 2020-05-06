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