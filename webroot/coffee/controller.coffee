class @.TemplateController
  prueba: ->
    html = ViewResolver.mergeViewWithModel "#dashboard"
    $("#handlebars").html(html)

  pruebados: ->
    html = ViewResolver.mergeViewWithModel "#info-hb"
    console.log html
    $("#handlebars").html(html)

  pruebatres: ->
    console.log "Prueba tres"
    html = ViewResolver.mergeViewWithModel "#pruebatres"
    console.log html
    $("#handlebars").html(html)
