$(function() {
	
	var modal = $(modalRegisterFastStyle);
	var saveButton = modal.find('.js-modal-register-style-save-btn');
	var form = modal.find('form');	
	form.on('submit', function(event) { event.preventDefault() });
	var url = form.attr('action');
	var inputStyleName = $('#styleName');
	var containerMessageError = $('.js-message-register-fast-style')
	modal.on('shown.bs.modal', onModalShow);

	modal.on('hide.bs.modal', onModalClose);
	
	saveButton.on('click', onSaveButtonClick);
	
	
	function onSaveButtonClick() {
		var styleName = inputStyleName.val().trim();
		$.ajax({
			url: url,
			method: 'POST',
			contentType: 'application/json',
			data: JSON.stringify({name: styleName}),
			error: onErroSavingStyle,
			success: onSavedStyle			
		});
	}
	
	function onSavedStyle(style){
		var styleComb =  $('#style');
		styleComb.append('<option value=' + style.id + '>' + style.name + '</option>');
		styleComb.val(style.id);
		modal.modal('hide');
	}
	
	function onErroSavingStyle(obj){
		var messageError = obj.responseText;
		containerMessageError.removeClass('hidden');
		containerMessageError.html('<span>' + messageError + '<span>');
		form.find('.form-group').addClass('has-error');
	}
	
	
	function onModalClose(){
		 inputStyleName.val('');
		 containerMessageError.addClass('hidden');
		 form.find('.form-group').removeClass('has-error');
	}
	
	function onModalShow(){
		inputStyleName.focus();
		
	}
});