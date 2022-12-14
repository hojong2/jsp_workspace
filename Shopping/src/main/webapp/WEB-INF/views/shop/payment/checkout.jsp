<%@page import="com.academy.shopping.model.domain.PayMethod"%>
<%@page import="com.academy.shopping.model.domain.Cart"%>
<%@page import="com.academy.shopping.model.domain.Member"%>
<%@page import="com.academy.shopping.model.util.CurrencyFormatter"%>
<%@page import="com.academy.shopping.model.domain.Product"%>
<%@page import="com.academy.shopping.model.domain.SubCategory"%>
<%@page import="com.academy.shopping.model.domain.TopCategory"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	Member member = (Member)request.getAttribute("member");
	List<Cart> cartList = (List)request.getAttribute("cartList");
	List<PayMethod> payMethodList = (List)request.getAttribute("payMethodList");
%>
<!DOCTYPE html>
<html lang="zxx">

<head>
    <meta charset="UTF-8">
    <meta name="description" content="Ashion Template">
    <meta name="keywords" content="Ashion, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Ashion | Template</title>

    <%@ include file="../inc/css.jsp" %>
</head>

<body>
    

    <%@ include file="../inc/topbar.jsp" %>

    <!-- Breadcrumb Begin -->
    <div class="breadcrumb-option">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="breadcrumb__links">
                        <a href="./index.html"><i class="fa fa-home"></i> Home</a>
                        <span>Shop</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Breadcrumb End -->
     
      <!-- Checkout Section Begin -->
    <section class="checkout spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <h6 class="coupon__link"><span class="icon_tag_alt"></span> <a href="#">Have a coupon?</a> Click
                    here to enter your code.</h6>
                </div>
            </div>
            <form action="#" class="checkout__form" id="pay-form">
            	
                <div class="row">
                    <div class="col-lg-8">
                        <h5>????????? ??????</h5>
                        <div class="row">
                        	<div class="col-lg-6 col-md-6 col-sm-6">
                                <div class="checkout__form__input">
                                    <p>??????ID<span>*</span></p>
                                    <input type="text" value="<%=member.getCustomer_id()%>">
                                </div>
                            </div>
                            <div class="col-lg-6 col-md-6 col-sm-6">
                                <div class="checkout__form__input">
                                    <p>????????????<span>*</span></p>
                                    <input type="text" value="<%=member.getCustomer_name()%>">
                                </div>
                            </div>
                            <div class="col-lg-6 col-md-6 col-sm-6">
                                <div class="checkout__form__input">
                                    <p>Email <span>*</span></p>
                                    <input type="text" value="<%=member.getCustomer_email()%>">
                                </div>
                            </div>
                            </div>
                        </div>
                        <div class="col-lg-4">
                            <div class="checkout__order">
                                <h5>?????? ??????</h5>
                                <div class="checkout__order__product">
                                    <ul>
                                        <li>
                                            <span class="top__text">Product</span>
                                            <span class="top__text__right">Total</span>
                                        </li>
                                        <%int total=0; %>
                                        <%for(int i=0; i<cartList.size();i++){ %>
                                        <%Cart cart=cartList.get(i); %>
                                        <li><%=cart.getProduct_name() %> <span><%=CurrencyFormatter.getCurrency(cart.getDiscount()*cart.getQuantity())%></span></li>
                                        <%total+= cart.getDiscount()*cart.getQuantity();%>
                                        <%} %>
                                    </ul>
                                </div>
                                <div class="checkout__order__total">
                                    <ul>
                                        <li>Subtotal <span><%=CurrencyFormatter.getCurrency(total) %></span></li>
                                        <li>Total <span><%=CurrencyFormatter.getCurrency(total) %></span></li>
                                    </ul>
                                </div>
                                <div class="checkout__order__widget">
                                	<%for(int i=0; i<payMethodList.size(); i++){ %>
                                	<%PayMethod payMethod= payMethodList.get(i); %>
                                    <label for="<%=payMethod.getPayname()%>">
                                        <%=payMethod.getPayname() %>
                                        <input type="radio" id="<%=payMethod.getPayname()%>" name="paymethod.paymethod_id" value="<%=payMethod.getPaymethod_id()%>">
                                        <span class="checkmark"></span>
                                    </label>
									<%} %>
                                </div>
                                <button type="button" class="site-btn" onClick="pay()">Place oder</button>
                            </div>
                        </div>
                    </div>
                    <input type="hidden" name="totalbuy" value="<%=total%>">
            		<input type="hidden" name="totalpay" value="<%=total%>">
                </form>
            </div>
        </section>
        <!-- Checkout Section End -->
   	

   

<%@ include file="../inc/footer.jsp" %>

<%@ include file="../inc/search.jsp" %>

<%@ include file="../inc/plugin.jsp" %>
<script>
function pay(){
	if(confirm("???????????? ????????? ????????? ????????????????")){
		//?????? ??????????????? ??????????????? ????????? post ???????????? ?????????.
		$("#pay-form").attr({
			action:"/shop/pay",
			method:"post"
		});
		$("#pay-form").submit();
	}
}

</script>
</body>

</html>