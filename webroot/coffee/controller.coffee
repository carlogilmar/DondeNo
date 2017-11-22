class @.TemplateController
  statics: ->
		$.get 'http://localhost:8080/statics', (data) ->
			assault = data.registers.filter((e) -> e.type == 'assault')
			violation = data.registers.filter((e) -> e.type == 'violation')
			physicalViolence = data.registers.filter((e) -> e.type == 'physicalViolence')
			theftOfAutoParts = data.registers.filter((e) -> e.type == 'theftOfAutoParts')
			extortion = data.registers.filter((e) -> e.type == 'extortion')
			weaponInjury = data.registers.filter((e) -> e.type == 'weaponInjury')
			robberyWithViolence = data.registers.filter((e) -> e.type == 'robberyWithViolence')
			vehicleTheft = data.registers.filter((e) -> e.type == 'vehicleTheft')
			context =
				registers: data.registers.reverse()
				counter: data.registers.length
				message: data.message
				assault: assault
				violation: violation
				physicalViolence: physicalViolence
				theftOfAutoParts: theftOfAutoParts
				extortion: extortion
				weaponInjury: weaponInjury
				robberyWithViolence: robberyWithViolence
				vehicleTheft: vehicleTheft
			html = ViewResolver.mergeViewWithModel "#statics", context
			$("#handlebars").html(html)
			new (Chartist.Line)('.chart', {
			  labels: [
			    "Asaltos"
			    "Violaciones"
			    "Violencia Física"
			    "Robo de autopartes"
			    "Extorsiones"
			    "Lesión con arma blanca"
			    "Robo con violencia"
			    "Robo a vehículo"
			  ]
			  series: [ [
			    assault.length
			    violation.length
			    physicalViolence.length
			    theftOfAutoParts.length
			    extortion.length
			    weaponInjury.length
			    robberyWithViolence.length
			    vehicleTheft.length
			  ] ]
			},
			  low: 0
			  showArea: true)

  assaults: ->
    ApiController.showAssaults()

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

