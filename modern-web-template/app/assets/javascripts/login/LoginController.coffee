class LoginController

  constructor: (@$log, @LoginService, @$scope, @$location) ->
    @account = ""
    @password = ""
    @user = {}

  reset: () ->
    @account =""
    @password =""

  submit: () ->
    @LoginService.submit(@account, @password)
    .then(
      (data)=>
        @$log.debug data
        if data.length == 0
          alert "Login failed."
          @reset()
        else
          @user = data[0]
          @LoginService.setCredentials(@user)
          @$location.path('/')
    (error)=>
      alert "Error - " + error
      @reset()
    )

  logout: () ->
      @$log.debug "logut"
      @LoginService.clearCredentials()
      @$location.path("/login")


controllersModule.controller('LoginController', LoginController)
