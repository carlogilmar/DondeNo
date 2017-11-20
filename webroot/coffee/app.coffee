class @.App
  constructor: ->
    @manager= new UrlManager()

class @.UrlManager

  constructor: ->
    @templateController = new TemplateController()
    @start()

  start: ->
    @routes =
      '/statics': @templateController.statics
      '/register': @templateController.register
    @urlMappings()

  urlMappings: ->
    router = Router(@routes)
    router.init()

  @setRoute: ->
    router = Router(@routes)
    router.setRoute '/'

