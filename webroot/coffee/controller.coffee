class @.TemplateController
  statics: ->
    html = ViewResolver.mergeViewWithModel "#statics"
    $("#handlebars").html(html)
    $.ajax
      url: 'http://localhost:8080/statics'
      type: 'get'
      dataType: 'json'
      contentType: 'application/json; charset=UTF-8'
      #success: ->
			#	console.log "Hicimos algo"
			#error: ->
      #	alert 'error al procesar'
		  error: (jqXHR, textStatus, errorThrown) ->
				console.log "Error"
		  success: (data, textStatus, jqXHR) ->
				console.log "Lo logramos"


  register: ->
    html = ViewResolver.mergeViewWithModel "#register"
    $("#handlebars").html(html)
    Validator.validateNewForm()

class @.ApiController
	@add: ->
    $.ajax
      url: 'http://localhost:8080/newRegister'
      type: 'post'
      data: $('#registerForm').serialize()
      success: ->
		    console.log "Registro okas!"
      error: ->
        console.log "Error al agregar"
