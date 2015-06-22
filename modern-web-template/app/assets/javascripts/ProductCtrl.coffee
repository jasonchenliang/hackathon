class ProductCtrl

    constructor: (@$log, @ProductService, @$routeParams, @$controller) ->
        @$log.debug "constructing ProductController"
        @product = {}
        @NavBarCtrl = @$controller("NavBarCtrl");
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

    addToCart: () ->
        @$log.debug "addToCart()"
        @NavBarCtrl.addToCart(@product.data[0].productId, @product.data[0].name,
          @product.data[0].price, document.getElementById('quantity').value)

controllersModule.controller('ProductCtrl', ProductCtrl)