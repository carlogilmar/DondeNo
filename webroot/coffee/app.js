// Generated by CoffeeScript 1.12.7
(function() {
  this.App = (function() {
    function App() {
      this.manager = new UrlManager();
    }

    return App;

  })();

  this.UrlManager = (function() {
    function UrlManager() {
      this.templateController = new TemplateController();
      this.start();
    }

    UrlManager.prototype.start = function() {
      this.routes = {
        '/statics': this.templateController.statics,
        '/register': this.templateController.register
      };
      return this.urlMappings();
    };

    UrlManager.prototype.urlMappings = function() {
      var router;
      router = Router(this.routes);
      return router.init();
    };

    UrlManager.setRoute = function() {
      var router;
      router = Router(this.routes);
      return router.setRoute('/');
    };

    return UrlManager;

  })();

}).call(this);