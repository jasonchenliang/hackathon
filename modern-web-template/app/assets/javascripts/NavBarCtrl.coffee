class NavBarCtrl

  constructor: (@$log, @$location, @CategoryService) ->
    @$log.debug "constructing NavBar Controller"
    @categories = []
    @cart = []
    @query = {}
    @getCategories()

  verifyUser: () ->
    if (!@user)
      window.location.href = '/login'

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

  search: () ->
    @$log.debug "search()"
    if (@query.category)
      @$location.path('/search/' + @query.category + '/' + @query.keyword)
    else
      @$location.path('/search/0/' + @query.keyword)

controllersModule.controller('NavBarCtrl', NavBarCtrl)