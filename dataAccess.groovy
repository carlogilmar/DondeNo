import io.vertx.ext.mongo.MongoClient
def config = vertx.currentContext().config()

def mongoconfig = [
  connection_string:"mongodb://localhost:27017",
  db_name:"vertx-test"
]

def mongoClient = MongoClient.createShared(vertx, mongoconfig)

vertx.eventBus().consumer("com.carlogilmar.new.register", { message ->
	mongoClient.save("full_storage", message.body(), { id ->
		if (id.succeeded()) {
	    println "New register added"
			vertx.eventBus().send("com.carlogilmar.new.specific.register", message.body())
		} else {
			println "Error al guardar nuevo registro"
		}
	})
})

vertx.eventBus().consumer("com.carlogilmar.new.specific.register"){message ->
	mongoClient.save(message.body().type, message.body(), { id ->
		if (id.succeeded()) {
			println "New register added: ${message.body().type}"
		} else {
			println "Error al guardar registro ${message.body().type}"
		}
	})
}

vertx.eventBus().consumer("com.carlogilmar.get.total.registers", { message ->
    mongoClient.find("full_storage", [:], { res ->
      if (res.succeeded()) {
				println "Registros totales encontrados: ${res.result().size}"
				message.reply(res.result())
      } else {
				println "Error en la consulta"
      }
    })
})

vertx.eventBus().consumer("com.carlogilmar.get.specific.registers", { message ->
    mongoClient.find(message.body().type, [:], { res ->
      if (res.succeeded()) {
				println "Registros totales encontrados de <${message.body().type}>: ${res.result().size}"
				message.reply(res.result())
      } else {
				println "Error en la consulta de registros ${message.body().type}"
      }
    })
})




