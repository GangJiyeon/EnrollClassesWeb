/**
 * 
 */
 



function geParameter(name){
	let parameters = location.search.substr(1);
	parameters = paramerters.split("&");
	
	for(let i=0; i<geParameter.length; i++){
		let parameter = parameters[i];
		parameter = parameter.split("=");
		let paramName = parameter[0];
		let paramValue = parameter[0];
		
		if(paramName==name){
			return paramValue;
		}
	}
	
	return undefined;
}