class SearchCtrl

  constructor: (@$log, @CategoryService, @ProductService, @$routeParams) ->
    @$log.debug "constructing ProductController"
    @category = {}
    @keyword = @$routeParams.keyword
    @items = []
    @getSearchCategory()
    @getItemsForQuery()

  getSearchCategory: () ->
    @$log.debug "getSearchCategory()"

    if (@$routeParams.category != "0")
      @CategoryService.category(@$routeParams.category)
      .then(
        (data) =>
          @$log.debug "Promise returned category"
          @category = data[0]
      ,
        (error) =>
          @$log.error "Unable to get category: #{error}"
      )

  getItemsForQuery: () ->
    @$log.debug "getItemsForQuery()"

    if (@$routeParams.category)
      @ProductService.queryCategoryList(@$routeParams.category, @$routeParams.keyword)
      .then(
        (data) =>
          @$log.debug "Promise returned #{data.length} query in category items"
          @items = data
      ,
        (error) =>
          @$log.error "Unable to get query in category items: #{error}"
      )
    else
      @ProductService.queryList(@$routeParams.keyword)
      .then(
        (data) =>
          @$log.debug "Promise returned #{data.length} query items"
          @items = data
      ,
        (error) =>
          @$log.error "Unable to get query items: #{error}"
      )

controllersModule.controller('SearchCtrl', SearchCtrl)