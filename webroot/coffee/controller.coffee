class @.TemplateController
  statics: ->
    console.log "Estadisticas route"
    html = ViewResolver.mergeViewWithModel "#statics"
    $("#handlebars").html(html)

  register: ->
    console.log "Rgister route"
    html = ViewResolver.mergeViewWithModel "#register"
    $("#handlebars").html(html)
