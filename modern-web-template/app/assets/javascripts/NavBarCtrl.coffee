
class NavBarCtrl
  @cart = []
  constructor: (@$log, @$location, @CategoryService, @$rootScope) ->
    @$log.debug "constructing NavBar Controller"
    @categories = []
    @user = {}
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

  addToCart: (productId, productName, productPrice, productQty) ->
    @$log.debug "value of productId: #{productId}"
    index = containsObject(productId,NavBarCtrl.cart)
    if index == -1
      NavBarCtrl.cart.push {id: productId, name: productName, price: productPrice, qty: productQty}
      @$rootScope.cart = NavBarCtrl.cart
    else
      NavBarCtrl.cart[index].qty = parseInt(NavBarCtrl.cart[index].qty, 10) + parseInt(productQty, 10)

    @$log.debug "cart: #{NavBarCtrl.cart[0]}"

  containsObject = (objId, list) ->
    i = 0
    while i < list.length
      if list[i].id == objId
        return i
      i++
    return -1

  changeProductQty: (productId, amount) ->
    index = containsObject(productId, NavBarCtrl.cart)
    if index != -1
      NavBarCtrl.cart[index].qty = parseInt(NavBarCtrl.cart[index].qty, 10) + amount

  removeProductFromCart: (productId) ->
    index = containsObject(productId, NavBarCtrl.cart)
    if index != -1
      NavBarCtrl.cart.splice(index, 1)



controllersModule.controller('NavBarCtrl', NavBarCtrl)