/**
 * 
 */

function genXML(params){
	var defaults = {
			id: 1,
			comment: '',
			location: '',
			challenge: '',
			commonName: '',
			docType: 'TIPO_DOC',
			verificationCode: '',
			applet: 'SignerApplet',
			action: '',	
			userId: '',
			validate_certificate: 'false',
			callback: {
				success: false,
				fail: false,
				always: false
			}
	}
	var params=$.extend(defaults,params);
	
	var xml='<PorFirmar/>';
    xmlDoc=$.parseXML(xml);    
    documento=xmlDoc.createElement('documento');
    documento.setAttribute("id",params.id);            
    documento.setAttribute('comentario',params.comment);    
    documento.setAttribute('lugar',params.location);    
    documento.setAttribute('tipoFirma',params.docType);
    documento.setAttribute('challenge',params.challenge);
    documento.setAttribute('aliase', params.commonName);
    documento.setAttribute('userId',params.userId);
    documento.setAttribute('verificationCode', params.verificationCode);
    documento.setAttribute('signatureOperation', params.action);
    documento.setAttribute('verifyCertificate', params.validate_certificate);
    xmlDoc.documentElement.appendChild(documento);
    
    var serializer = new XMLSerializer();
    var str=serializer.serializeToString(xmlDoc);    
    var resultadoApplet;
    //LLama a applet
    //window.setTimeout(function(){resultadoApplet = eval("document."+params.applet+".signDocumentAsyn(str)");},1000);
    resultadoApplet = eval("document."+params.applet+".signDocumentAsyn(str)");
    xmlRes = resultadoApplet;//$(resultadoApplet).find('documento').attr('RESULTADO');
    if(xmlRes == "true"){
    	if(params.callback.success){
    		window.setTimeout(function(){params.callback.success.call();},1000);
    	}
    }else{
    	if(params.callback.fail){
    		window.setTimeout(function(){params.callback.fail.call();},1000);
    	}
    }    
	if(params.callback.always){
		window.setTimeout(function(){params.callback.always.call();},1000);
	}
}