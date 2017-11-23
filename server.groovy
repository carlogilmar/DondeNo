import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.BodyHandler
import io.vertx.ext.web.handler.StaticHandler
import io.vertx.core.json.JsonObject
import io.vertx.core.json.JsonArray
import io.vertx.core.json.Json
import io.vertx.ext.web.handler.sockjs.SockJSHandler

def server = vertx.createHttpServer()
def router = Router.router(vertx)
router.route().handler(BodyHandler.create())


// Create the event bus bridge and add it to the router.
def opts = [outboundPermitteds:[[address:"com.carlogilmar.success"]]]
def ebHandler = SockJSHandler.create(vertx).bridge(opts)
router.route("/eventbus/*").handler(ebHandler)

router.route("/static/*").handler(
  StaticHandler.create().setCachingEnabled(false)
)

/*
assault
violation
physicalViolence
theftOfAutoParts
extortion
weaponInjury
robberyWithViolence
vehicleTheft
*/

router.get("/statics").handler({ routingContext ->
	vertx.eventBus().send("com.carlogilmar.get.total.registers", "Total registers"){ reply ->
		if(reply.succeeded()){
			routingContext.response()
			.setStatusCode(200)
			.putHeader("content-type", "application/json; charset=utf-8")
			.end( Json.encodePrettily([registers:reply.result().body()]) )
		}
	}
})

router.post("/mobileRegister").handler { routingContext ->
  def bodyRequest = routingContext.getBodyAsJson()
  vertx.eventBus().send("com.carlogilmar.new.register", bodyRequest)
  println bodyRequest
	vertx.eventBus().publish("com.carlogilmar.success", " Registro: <${bodyRequest.type}> agregado desde un iPhone ")
	routingContext.response()
	.setStatusCode(201)
	.putHeader("content-type", "application/json; charset=utf-8")
	.end("REST: Reporte Creado!")
}

router.post("/newRegister").handler { routingContext ->
  def params = routingContext.request().params()
  def registerMessage = [
                          type:params.type,
                          email:params.email,
                          delegation:params.delegation,
                          street:params.street,
                          description:params.description,
                          previousComplaince:params.previousComplaince,
                          date:params.date,
													value:params.value
                        ]
  println registerMessage
	vertx.eventBus().publish("com.carlogilmar.success", " Registro: <${registerMessage.type}> agregado desde la aplicación")
	vertx.eventBus().send("com.carlogilmar.new.register", registerMessage)
	routingContext.response()
	.setStatusCode(201)
	.putHeader("content-type", "application/json; charset=utf-8")
	.end("FORM: Reporte Creado!")
}

router.get("/vehicleTheft").handler({ routingContext ->
	vertx.eventBus().send("com.carlogilmar.get.specific.registers", [type:"vehicleTheft"]){ reply ->
		if(reply.succeeded()){
			routingContext.response()
			.setStatusCode(200)
			.putHeader("content-type", "application/json; charset=utf-8")
			.end( Json.encodePrettily(reply.result().body()) )
		}
	}
})

router.get("/robberyWithViolence").handler({ routingContext ->
	vertx.eventBus().send("com.carlogilmar.get.specific.registers", [type:"robberyWithViolence"]){ reply ->
		if(reply.succeeded()){
			routingContext.response()
			.setStatusCode(200)
			.putHeader("content-type", "application/json; charset=utf-8")
			.end( Json.encodePrettily(reply.result().body()) )
		}
	}
})

router.get("/weaponInjury").handler({ routingContext ->
	vertx.eventBus().send("com.carlogilmar.get.specific.registers", [type:"weaponInjury"]){ reply ->
		if(reply.succeeded()){
			routingContext.response()
			.setStatusCode(200)
			.putHeader("content-type", "application/json; charset=utf-8")
			.end( Json.encodePrettily(reply.result().body()) )
		}
	}
})

router.get("/extortion").handler({ routingContext ->
	vertx.eventBus().send("com.carlogilmar.get.specific.registers", [type:"extortion"]){ reply ->
		if(reply.succeeded()){
			routingContext.response()
			.setStatusCode(200)
			.putHeader("content-type", "application/json; charset=utf-8")
			.end( Json.encodePrettily(reply.result().body()) )
		}
	}
})

router.get("/theftOfAutoParts").handler({ routingContext ->
	vertx.eventBus().send("com.carlogilmar.get.specific.registers", [type:"theftOfAutoParts"]){ reply ->
		if(reply.succeeded()){
			routingContext.response()
			.setStatusCode(200)
			.putHeader("content-type", "application/json; charset=utf-8")
			.end( Json.encodePrettily(reply.result().body()) )
		}
	}
})

router.get("/physicalViolence").handler({ routingContext ->
	vertx.eventBus().send("com.carlogilmar.get.specific.registers", [type:"physicalViolence"]){ reply ->
		if(reply.succeeded()){
			routingContext.response()
			.setStatusCode(200)
			.putHeader("content-type", "application/json; charset=utf-8")
			.end( Json.encodePrettily(reply.result().body()) )
		}
	}
})

router.get("/assault").handler({ routingContext ->
	vertx.eventBus().send("com.carlogilmar.get.specific.registers", [type:"assault"]){ reply ->
		if(reply.succeeded()){
			routingContext.response()
			.setStatusCode(200)
			.putHeader("content-type", "application/json; charset=utf-8")
			.end( Json.encodePrettily(reply.result().body()) )
		}
	}
})

router.get("/violation").handler({ routingContext ->
	vertx.eventBus().send("com.carlogilmar.get.specific.registers", [type:"violation"]){ reply ->
		if(reply.succeeded()){
			routingContext.response()
			.setStatusCode(200)
			.putHeader("content-type", "application/json; charset=utf-8")
			.end( Json.encodePrettily(reply.result().body()) )
		}
	}
})

server.requestHandler(router.&accept).listen(8080)
