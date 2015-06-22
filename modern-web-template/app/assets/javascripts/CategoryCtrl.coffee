class CategoryCtrl

  constructor: (@$log, @CategoryService, @ProductService, @$routeParams) ->
    @$log.debug "constructing ProductController"
    @category = {}
    @items = []
    @getSelectedCategory()
    @getItemsForCategory()

  getSelectedCategory: () ->
    @$log.debug "getSelectedCategory()"

    @CategoryService.category(@$routeParams.cid)
    .then(
      (data) =>
        @$log.debug "Promise returned category"
        @category = data[0]
    ,
      (error) =>
        @$log.error "Unable to get category: #{error}"
    )

  getItemsForCategory: () ->
    @$log.debug "getItemsForCategory()"

    @ProductService.categoryItemList(@$routeParams.cid)
    .then(
      (data) =>
        @$log.debug "Promise returned #{data.length} category items"
        @items = data
    ,
      (error) =>
        @$log.error "Unable to get category items: #{error}"
    )

controllersModule.controller('CategoryCtrl', CategoryCtrl)