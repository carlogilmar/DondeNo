class @.TemplateController
  statics: ->
		$.get 'http://localhost:8080/statics', (data) ->
			context =
				registers: data.registers.reverse()
				counter: data.registers.length
				message: data.message
			html = ViewResolver.mergeViewWithModel "#statics", context
			$("#handlebars").html(html)
			new (Chartist.Line)('.chart', {
			  labels: [
			    1
			    2
			    3
			    4
			    5
			    6
			    7
			    8
			  ]
			  series: [ [
			    5
			    9
			    7
			    8
			    5
			    3
			    5
			    4
			  ] ]
			},
			  low: 0
			  showArea: true)


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
