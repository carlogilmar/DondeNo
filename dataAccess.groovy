import io.vertx.ext.mongo.MongoClient
def config = vertx.currentContext().config()

def mongoconfig = [
  connection_string:"mongodb://localhost:27017",
  db_name:"donde-no"
]

def mongoClient = MongoClient.createShared(vertx, mongoconfig)

vertx.eventBus().consumer("com.carlogilmar.new.register", { message ->
	mongoClient.save("full_storage", message.body(), { id ->
		if (id.succeeded()) {
	    println "New register added"
			vertx.eventBus().send("com.carlogilmar.new.specific.register", message.body())
	    //vertx.eventBus().send("com.carlogilmar.success", "Registro agregado a la aplicación!!")
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
        def registers = res.result()
				println "Registros totales encontrados de <${message.body().type}>: ${res.result().size}"
				message.reply( [type:message.body().type,
                        registers:registers,
                        total:registers.size(),
                        notPreviousComplaince:registers.findAll{ it.previousComplaince == "false" }.size(),
                        withPreviousComplaince:registers.findAll{ it.previousComplaince == "true" }.size(),
                        valueOne:registers.findAll{ it.value == 1 }.size(),
                        valueTwo:registers.findAll{ it.value == 2 }.size(),
                        valueThree:registers.findAll{ it.value == 3 }.size(),
                        valueFour:registers.findAll{ it.value == 4 }.size(),
                        valueFive:registers.findAll{ it.value == 5 }.size(),
                        ] )
      } else {
				println "Error en la consulta de registros ${message.body().type}"
      }
    })
})




