var Brewer = Brewer || {};

Brewer.StyleFastRegister = (function() {
	
	function StyleFastRegister() {
		this.modal = $(modalRegisterFastStyle);
		this.saveButton = this.modal.find('.js-modal-register-style-save-btn');
		this.form = this.modal.find('form');	
		this.url = this.form.attr('action');
		this.inputStyleName = $('#styleName');
		this.containerMessageError = $('.js-message-register-fast-style');		
	}	
	
	StyleFastRegister.prototype.start = function()  {
		this.form.on('submit', function(event) { event.preventDefault() });
		this.modal.on('shown.bs.modal', onModalShow.bind(this));
		this.modal.on('hide.bs.modal', onModalClose.bind(this));
		this.saveButton.on('click', onSaveButtonClick.bind(this));		
	}

	function onModalShow() {
		this.inputStyleName.focus();		
	}
	
	function onModalClose(){
		 this.inputStyleName.val('');
		 this.containerMessageError.addClass('hidden');
		 this.form.find('.form-group').removeClass('has-error');
	}	

	
	function onSaveButtonClick() {
		var styleName = this.inputStyleName.val().trim();
		$.ajax({
			url: this.url,
			method: 'POST',
			contentType: 'application/json',
			data: JSON.stringify({name: styleName}),
			error: onErroSavingStyle.bind(this),
			success: onSavedStyle.bind(this)			
		});
	}
	
	function onSavedStyle(style){
		var styleComb =  $('#style');
		styleComb.append('<option value=' + style.id + '>' + style.name + '</option>');
		styleComb.val(style.id);
		this.modal.modal('hide');
	}
	
	function onErroSavingStyle(obj){
		var messageError = obj.responseText;
	    this.containerMessageError.removeClass('hidden');
		this.containerMessageError.html('<span>' + messageError + '<span>');
		this.form.find('.form-group').addClass('has-error');
	}
		
	
	return StyleFastRegister;
	
}());

$(function() {	
	var styleFastRegister = new Brewer.StyleFastRegister();
	styleFastRegister.start(); 	
});