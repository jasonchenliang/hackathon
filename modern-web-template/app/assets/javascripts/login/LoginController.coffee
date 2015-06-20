class LoginController

  constructor: (@$log, @LoginService, @$scope, @$controller) ->
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
          window.location.href = '/'
    (error)=>
      alert "Error - " + error
      @reset()
    )


controllersModule.controller('LoginController', LoginController)
