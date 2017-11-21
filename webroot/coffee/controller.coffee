class @.TemplateController
  statics: ->
		$.get 'http://localhost:8080/statics', (data) ->
			context =
				registers: data
			html = ViewResolver.mergeViewWithModel "#statics", context
			$("#handlebars").html(html)

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
		    console.log "Registro agregado"
      error: ->
        console.log "Error al agregar"
