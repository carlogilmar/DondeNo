class @.ViewResolver
  @mergeViewWithModel = (templateName, model) ->
    source = $(templateName).html()
    template = Handlebars.compile source
    template(model)

class @.Validator
	@validateNewForm: ->
		$('#submitRegister').click ->
			console.log "Click para registro"
			ApiController.add()
