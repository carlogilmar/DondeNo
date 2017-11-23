//First Script for deploy my vertx web server
vertx.deployVerticle("dataAccess.groovy"){deploy ->
  if(deploy.succeeded()){
    vertx.deployVerticle("server.groovy")
  }
}
