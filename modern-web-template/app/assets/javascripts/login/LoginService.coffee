class LoginService
  @headers = {'Accept': 'application/json', 'Content-Type': 'application/json'}
  @defaultConfig = { headers: @headers }

  constructor: (@$log, @$http, @$q) ->
    @$log.debug "constructing LoginService"

  submit: (@userId, @password) ->
    @$log.debug "submit()"
    deferred = @$q.defer()

    @$http.get("/login/"+@userId+"/"+@password)
    .success((data, status, headers) =>
      @$log.info("Login result returned - #{data}")
      deferred.resolve(data)
    )
    .error((data, status, headers) =>
      @$log.error("Login error - #{status}")
      deferred.reject(data)
    )
    deferred.promise

servicesModule.service('LoginService', LoginService)
