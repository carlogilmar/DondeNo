import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.BodyHandler
import io.vertx.core.json.JsonObject
import io.vertx.core.json.JsonArray
import io.vertx.core.json.Json

def server = vertx.createHttpServer()
def router = Router.router(vertx)
router.route().handler(BodyHandler.create())

router.get("/statics").handler({ routingContext ->
	vertx.eventBus().send("com.carlogilmar.get.total.registers", "Total registers"){ reply ->
		if(reply.succeeded()){
			routingContext.response()
			.setStatusCode(200)
			.putHeader("content-type", "application/json; charset=utf-8")
			.end( Json.encodePrettily(reply.result().body()) )
		}
	}
})

router.post("/newRegister").handler { routingContext ->
	def bodyRequest = routingContext.getBodyAsJson()
	println "Request for new register in ${bodyRequest.type}"
	vertx.eventBus().send("com.carlogilmar.new.register", bodyRequest)
	routingContext.response()
	.setStatusCode(201)
	.putHeader("content-type", "application/json; charset=utf-8")
	.end("Reporte Creado!")
}

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

server.requestHandler(router.&accept).listen(8080)
