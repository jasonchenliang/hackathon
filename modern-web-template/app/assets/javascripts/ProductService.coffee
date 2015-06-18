
class ProductService

  @headers = {'Accept': 'application/json', 'Content-Type': 'application/json'}
  @defaultConfig = { headers: @headers }

  constructor: (@$log, @$http, @$q) ->
    @$log.debug "constructing ProductService"

  hotItemList: () ->
    @$log.debug "hotItemList()"
    deferred = @$q.defer()

    @$http.get("/search/hot/0/0")
    .success((data, status, headers) =>
      @$log.info("Successfully retrieved hot items - status #{status}")
      deferred.resolve(data)
    )
    .error((data, status, headers) =>
      @$log.error("Failed to retrieve hot items - status #{status}")
      deferred.reject(data)
    )
    deferred.promise

  newItemList: () ->
    @$log.debug "newItemList()"
    deferred = @$q.defer()

    @$http.get("/search/new/0/0")
    .success((data, status, headers) =>
      @$log.info("Successfully retrieved new items - status #{status}")
      deferred.resolve(data)
    )
    .error((data, status, headers) =>
      @$log.error("Failed to retrieve new items - status #{status}")
      deferred.reject(data)
    )
    deferred.promise

servicesModule.service('ProductService', ProductService)