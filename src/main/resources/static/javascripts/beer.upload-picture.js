var Brewer = Brewer || {};

Brewer.UploadPicture = (function() {
	
	function UploadPicture(){
		this.inputPictureName = $('input[name=picture]');
		this.inputContentType = $('input[name=contentType]'); 	
		this.htmlPictureBeerTemplate = $('#picture-beer').html();
		this.template = Handlebars.compile(this.htmlPictureBeerTemplate);
//		this.htmlPictureBeer = template({pictureName: response.name});
		this.containerPictureBeer = $('.js-container-picture-beer');		
		this.uploadDrop = $('#upload-drop'); 
		
	}
	
	UploadPicture.prototype.start = function() {
		var settings = {
				type: 'json',
				filelimit: 1,
				allow: '*.(jpg|jpeg|png)',
				action: this.containerPictureBeer.data('url-pictures'),
				complete: onUploadComplete.bind(this)
		}
		
		UIkit.uploadSelect($('#upload-select'),settings);
		UIkit.uploadDrop($('#upload-drop'),settings);	
		
		
		if(this.inputPictureName.val()){
			onUploadComplete.call(this,{ name: this.inputPictureName.val(), contentType: this.inputContentType.val()});
		}
		
	} 	
	
	function onUploadComplete(response){
		this.inputPictureName.val(response.name);
		this.inputContentType.val(response.contentType); 
		this.uploadDrop.addClass('hidden');
		var htmlPictureBeer = this.template({pictureName: response.name});
		this.containerPictureBeer.append(htmlPictureBeer);		
		
		$('.js-remove-picture').on('click', onRemovePicture.bind(this));
	}
	
	
	function onRemovePicture(){
		$('.js-picture-beer').remove();							
		this.uploadDrop.removeClass('hidden');
		this.inputPictureName.val('');
		this.inputContentType.val('');
	}
	
	return UploadPicture;
	
})();


$(function(){
	var uploadPicture = new Brewer.UploadPicture();
	uploadPicture.start();
});