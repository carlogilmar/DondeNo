// Generated by CoffeeScript 1.12.7
(function() {
  this.TemplateController = (function() {
    function TemplateController() {}

    TemplateController.prototype.statics = function() {};

    $.get('http://localhost:8080/statics', function(data) {
      var context, html;
      context = {
        registers: data
      };
      html = ViewResolver.mergeViewWithModel("#statics", context);
      return $("#handlebars").html(html);
    });

    TemplateController.prototype.register = function() {
      var html;
      html = ViewResolver.mergeViewWithModel("#register");
      $("#handlebars").html(html);
      return Validator.validateNewForm();
    };

    return TemplateController;

  })();

  this.ApiController = (function() {
    function ApiController() {}

    ApiController.add = function() {
      return $.ajax({
        url: 'http://localhost:8080/newRegister',
        type: 'post',
        data: $('#registerForm').serialize(),
        success: function() {}
      }, console.log("Registro agregado"), {
        error: function() {
          return console.log("Error al agregar");
        }
      });
    };

    return ApiController;

  })();

}).call(this);
