
class CategoryService

  @headers = {'Accept': 'application/json', 'Content-Type': 'application/json'}
  @defaultConfig = { headers: @headers }

  constructor: (@$log, @$http, @$q) ->
    @$log.debug "constructing ProductService"

  category: (cid) ->
    @$log.debug "category()"
    deferred = @$q.defer()

    @$http.get("/category/#{cid}")
    .success((data, status, headers) =>
      @$log.info("Successfully retrieved category - status #{status}")
      deferred.resolve(data)
    )
    .error((data, status, headers) =>
      @$log.error("Failed to retrieve category - status #{status}")
      deferred.reject(data)
    )
    deferred.promise

  categoryList: () ->
    @$log.debug "categoryList()"
    deferred = @$q.defer()

    @$http.get("/getCategories/0")
    .success((data, status, headers) =>
      @$log.info("Successfully retrieved categories - status #{status}")
      deferred.resolve(data)
    )
    .error((data, status, headers) =>
      @$log.error("Failed to retrieve categories - status #{status}")
      deferred.reject(data)
    )
    deferred.promise

servicesModule.service('CategoryService', CategoryService)