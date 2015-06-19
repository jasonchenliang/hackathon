class MainCtrl

  constructor: (@$log, @ProductService) ->
    @$log.debug "constructing MainController"
    @hotItems = []
    @newItems = []
    @getHotItems()
    @getNewItems()

  getHotItems: () ->
    @$log.debug "getHotItems()"
    @ProductService.hotItemList()
    .then(
      (data) =>
        @$log.debug "Promise returned #{data.length} hot items"
        @hotItems = data
      (error) =>
        @$log.error "Unable to get hot items: #{error}"
    )

  getNewItems: () ->
    @$log.debug "getNewItems()"
    @ProductService.newItemList()
    .then(
      (data) =>
        @$log.debug "Promise returned #{data.length} new items"
        @newItems = data
      (error) =>
        @$log.error "Unable to get new items: #{error}"
    )

controllersModule.controller('MainCtrl', MainCtrl)