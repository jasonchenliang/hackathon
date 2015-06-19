
class NavBarCtrl

  constructor: (@$log, @CategoryService) ->
    @$log.debug "constructing UserController"
    @categories = []
    @cart = []
    @user = {}
    @getCategories()

  getCategories: () ->
    @$log.debug "getCategories()"
    @CategoryService.categoryList()
    .then(
      (data) =>
        @$log.debug "Promise returned #{data.length} categories"
        @categories = data
      (error) =>
        @$log.error "Unable to get categories: #{error}"
    )


controllersModule.controller('NavBarCtrl', NavBarCtrl)