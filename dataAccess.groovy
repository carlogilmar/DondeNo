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
			vertx.eventBus().send("com.carlogilmar.new.specific.register.${message.body().type}", message.body())
		} else {
			println "Error al guardar nuevo registro"
		}
	})
})

vertx.eventBus().consumer("com.carlogilmar.new.specific.register.assault"){message ->
	mongoClient.save("assault", message.body(), { id ->
		if (id.succeeded()) {
			println "New assault added"
		} else {
			println "Error al guardar registro"
		}
	})
}

vertx.eventBus().consumer("com.carlogilmar.new.specific.register.violation"){message ->
	mongoClient.save("violation", message.body(), { id ->
		if (id.succeeded()) {
			println "New violation added"
		} else {
			println "Error al guardar registro"
		}
	})
}
vertx.eventBus().consumer("com.carlogilmar.new.specific.register.physicalViolation"){message ->
	mongoClient.save("physicalViolation", message.body(), { id ->
		if (id.succeeded()) {
			println "New physicalViolation added"
		} else {
			println "Error al guardar registro"
		}
	})
}

vertx.eventBus().consumer("com.carlogilmar.new.specific.register.autoPartTheft"){message ->
	mongoClient.save("autoPartTheft", message.body(), { id ->
		if (id.succeeded()) {
			println "New autoPartTheft added"
		} else {
			println "Error al guardar registro"
		}
	})
}

vertx.eventBus().consumer("com.carlogilmar.new.specific.register.extortion"){message ->
	mongoClient.save("extortion", message.body(), { id ->
		if (id.succeeded()) {
			println "New extortion added"
		} else {
			println "Error al guardar registro"
		}
	})
}
vertx.eventBus().consumer("com.carlogilmar.new.specific.register.weaponInjury"){message ->
	mongoClient.save("weaponInjury", message.body(), { id ->
		if (id.succeeded()) {
			println "New weaponInjury added"
		} else {
			println "Error al guardar registro"
		}
	})
}

vertx.eventBus().consumer("com.carlogilmar.new.specific.register.robberyWithViolence"){message ->
	mongoClient.save("robberyWithViolence", message.body(), { id ->
		if (id.succeeded()) {
			println "New robberyWithViolence added"
		} else {
			println "Error al guardar registro"
		}
	})
}

vertx.eventBus().consumer("com.carlogilmar.new.specific.register.vehicleTheft"){message ->
	mongoClient.save("vehicleTheft", message.body(), { id ->
		if (id.succeeded()) {
			println "New vehicleTheft added"
		} else {
			println "Error al guardar registro"
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




