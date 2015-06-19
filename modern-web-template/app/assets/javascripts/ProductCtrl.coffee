class ProductCtrl

    constructor: (@$log, @ProductService, @$routeParams) ->
        @$log.debug "constructing ProductController"
        @product = {}
        @getSelectedProduct()

    getSelectedProduct: () ->
        @$log.debug "getSelectedProduct()"

        @ProductService.getProduct(@$routeParams.productId)
        .then(
            (data) =>
                @$log.debug "Promise returned product"
                @product = data
            ,
            (error) =>
                @$log.error "Unable to get Users: #{error}"
            )

controllersModule.controller('ProductCtrl', ProductCtrl)