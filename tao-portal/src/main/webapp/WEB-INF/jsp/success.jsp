<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>



<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
   <meta http-equiv="pragma" content="no-cache" />
   <meta http-equiv="cache-control" content="no-cache" />
   <meta http-equiv="expires" content="0" /> 
   <meta name="format-detection" content="telephone=no" />  
   <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" /> 
   <meta name="format-detection" content="telephone=no" />
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
   <link type="text/css" rel="stylesheet" href="/css/base.css" />
   <link rel="stylesheet" type="text/css" href="/css/purchase.base.2012.css" />
   <link rel="stylesheet" type="text/css" href="/css/purchase.sop.css" />
   <title>订单成功页面 - 淘淘商城</title>
   <script type="text/javascript" src="/js/jquery-1.6.4.js"></script>
   <script type="text/javascript" src="/js/base-2011.js" charset="utf-8"></script>
   <script type="text/javascript" src="/js/jquery.cookie.js" charset="utf-8"></script>
   <script type="text/javascript" src="/js/taotao.js" charset="utf-8"></script>
</head> <body id="mainframe">
<!--shortcut start-->
<jsp:include page="commons/shortcut.jsp" />
<!--shortcut end-->
<div class="w" id="headers">
		<div id="logo"><a href="/"><img alt="淘淘商城" src="/images/taotao-logo.gif"></a></div>
		<ul class="step" id="step3">
			<li class="fore1">1.我的购物车<b></b></li>
			<li class="fore2">2.填写核对订单信息<b></b></li>
			<li class="fore3">3.成功提交订单</li>
		</ul>
		<div class="clr"></div>
</div>
<div class="w" id="safeinfo"></div><!--父订单的ID-->
<div class="w main">
	<div class="m m3 msop">
        <div class="mt" id="success_tittle"><s class="icon-succ02"></s><h3 class="ftx-02">感谢您，订单提交成功！</h3>
		</div>
		<div class="mc" id="success_detail">	
		    <ul class="list-order">
			    <li class="li-st">
					<div class="fore1">订单号：<a href="javascript:void(0)">${orderId }</a></div>
					<!-- 货到付款 -->
					<div class="fore2">货到付款：<strong class="ftx-01">${payment}元</strong></div>
					<div class="fore3">
					   	淘淘快递 &nbsp; 送货时间: 预计 ${date} 送达&nbsp;
					</div>
				</li>
			</ul>
		<!-- 在线支付按钮  -->
				<div id="bookDiv"></div>
 					<p class="i-tips01">
				            	您的订单已经在处理中，发货后订单内容会显示承运人联系方式，如有必要您可以联系对方
             		</p>
		 </div>
	</div>
</div>
  <div class="w">
	<!-- links start -->
    <jsp:include page="commons/footer-links.jsp"></jsp:include>
    <!-- links end -->
</div><!-- footer end -->
     </body>
  <script type="text/javascript">
      var t;  //成员变量
      var TT = TAOTAO = {

          checkLogin : function(){
              var _ticket = $.cookie("TT_TOKEN");

              if(!_ticket){
                  return ;
              }
              t=_ticket;
              $.ajax({
                  url : "http://localhost:8084/user/token/" + _ticket,
                  dataType : "jsonp",
                  type : "GET",
                  success : function(data){
                      if(data.status == 200){
                          var username = data.data.username;
                          var html = username + "，欢迎来到淘淘！<a onclick='exit()'  class=\"link-logout\">[退出]</a>";
                          $("#loginbar").html(html);

                      }
                  }
              });


          }
      }
      //退出按钮
      function exit(){
          // 退出功能  ， callback =回调函数
          $.ajax({
              url: 'http://localhost:8084/user/logout/'+t+'?callback=e',
              type: 'get',
              dataType: 'jsonp',
              jsonpCallback: 'e'
          });

      }
      //回调函数
      function e(){
          window.location="/index.html";
      }


      $(function(){
          // 查看是否已经登录，如果已经登录查询登录信息
          TT.checkLogin();
      });

  </script>
</html>