class LoginController

  constructor: (@$log, @LoginService) ->
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
          alert "Login success."
          @user = data[0]
    (error)=>
      alert "Error - " + error
      @reset()
    )


controllersModule.controller('LoginController', LoginController)
