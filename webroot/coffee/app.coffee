class @.App
  constructor: ->
    @manager= new UrlManager()

class @.UrlManager

  constructor: ->
    @templateController = new TemplateController()
    @start()

  start: ->
    @routes =
      '/pruebauno': @templateController.prueba
      '/pruebados': @templateController.pruebados
      '/pruebatres': @templateController.pruebatres
    @urlMappings()

  urlMappings: ->
    router = Router(@routes)
    router.init()

  @setRoute: ->
    router = Router(@routes)
    router.setRoute '/'

