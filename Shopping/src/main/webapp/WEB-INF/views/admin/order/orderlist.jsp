<%@page import="com.academy.shopping.model.domain.OrderSummary"%>
<%@page import="com.academy.shopping.model.domain.Product"%>
<%@page import="java.util.List"%>
<%@page import="com.academy.shopping.model.domain.TopCategory"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	List <OrderSummary> orderSummaryList = (List) request.getAttribute("orderSummaryList");

%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>AdminLTE 3 | Advanced form elements</title>

   <%@ include file="../inc/header_link.jsp" %>
</head>
<body class="hold-transition sidebar-mini">
<div class="wrapper">

  <%@ include file="../inc/topbar.jsp" %> 
  <%@ include file="../inc/sidebar.jsp" %>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1>Advanced Form</h1>
          </div>
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">Home</a></li>
              <li class="breadcrumb-item active">Advanced Form</li>
            </ol>
          </div>
        </div>
      </div><!-- /.container-fluid -->
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="container-fluid">
            <!-- /.row -->
        <div class="row">
          <div class="col-12">
            <div class="card">
              <div class="card-header">
                <h3 class="card-title">Responsive Hover Table</h3>

                <div class="card-tools">
                  <div class="input-group input-group-sm" style="width: 150px;">
                    <input type="text" name="table_search" class="form-control float-right" placeholder="Search">

                    <div class="input-group-append">
                      <button type="submit" class="btn btn-default">
                        <i class="fas fa-search"></i>
                      </button>
                    </div>
                  </div>
                </div>
              </div>
              <!-- /.card-header -->
              <div class="card-body table-responsive p-0">
                <table class="table table-hover text-nowrap">
                  <thead>
                    <tr>
                      
                      <th>No</th>
                      <th>주문번호</th>
                      <th>구매자</th>
                      <th>결제 방법</th>
                      <th>총 구매금액</th>
                      <th>결제금액</th>
                      <th>주문일시</th>
                     
                    </tr>
                  </thead>
                  
                  <tbody>
                  
                  <%for(int i = 0; i < orderSummaryList.size(); i++) { %>
                  <% OrderSummary orderSummary = orderSummaryList.get(i); %>
                    <tr>
                      <td><%=i %></td>
                      <td><a href="/admin/order/detail?ordersummary_id=<%=orderSummary.getOrdersummary_id()%>"><%=orderSummary.getOrdersummary_id()%></td>
                      <td><%= orderSummary.getMember().getCustomer_name()%></td>
                      <td><%= orderSummary.getPaymethod().getPayname()%></td>
                      <td><%=orderSummary.getTotalbuy() %></td>
                      <td><%= orderSummary.getTotalpay()%></td>
                      <td><%=orderSummary.getBuydate()%></td>
                    </tr>
                     <% } %>
                  </tbody>
                </table>
                
                <button class="btn btn-primary" onClick="location.href='/admin/product/registform';">상품등록</button>
                <button class="btn btn-primary" onClick="showExcel()">엑셀등록</button>
                <div style="display: none" id="excel-area">
                <form id="excel-form">
                <input type="file" name="excel">
                <button type="button" class="btn btn-info" onClick="registExcel()">등록</button>
              </form>
              </div>
              </div>
              <!-- /.card-body -->
            </div>
            <!-- /.card -->
          </div>
        </div>
        <!-- /.row -->
        
      </div>
      <!-- /.container-fluid -->
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
  <footer class="main-footer">
    <div class="float-right d-none d-sm-block">
      <b>Version</b> 3.2.0
    </div>
    <strong>Copyright &copy; 2014-2021 <a href="https://adminlte.io">AdminLTE.io</a>.</strong> All rights reserved.
  </footer>

  <!-- Control Sidebar -->
  <aside class="control-sidebar control-sidebar-dark">
    <!-- Control sidebar content goes here -->
  </aside>
  <!-- /.control-sidebar -->
</div>
<!-- ./wrapper -->


<!-- jQuery -->
<script src="/static/admin/plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap 4 -->
<script src="/static/admin/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- Select2 -->
<script src="/static/admin/plugins/select2/js/select2.full.min.js"></script>
<!-- Bootstrap4 Duallistbox -->
<script src="/static/admin/plugins/bootstrap4-duallistbox/jquery.bootstrap-duallistbox.min.js"></script>
<!-- InputMask -->
<script src="/static/admin/plugins/moment/moment.min.js"></script>
<script src="/static/admin/plugins/inputmask/jquery.inputmask.min.js"></script>
<!-- date-range-picker -->
<script src="/static/admin/plugins/daterangepicker/daterangepicker.js"></script>
<!-- bootstrap color picker -->
<script src="/static/admin/plugins/bootstrap-colorpicker/js/bootstrap-colorpicker.min.js"></script>
<!-- Tempusdominus Bootstrap 4 -->
<script src="/static/admin/plugins/tempusdominus-bootstrap-4/js/tempusdominus-bootstrap-4.min.js"></script>
<!-- Bootstrap Switch -->
<script src="/static/admin/plugins/bootstrap-switch/js/bootstrap-switch.min.js"></script>
<!-- BS-Stepper -->
<script src="/static/admin/plugins/bs-stepper/js/bs-stepper.min.js"></script>
<!-- dropzonejs -->
<script src="/static/admin/plugins/dropzone/min/dropzone.min.js"></script>
<!-- AdminLTE App -->
<script src="/static/admin/dist/js/adminlte.min.js"></script>
<!-- AdminLTE for demo purposes -->
<!-- Page specific script -->
<script type="text/javascript" src="/static/common/js/lib.js"></script>
<script>
function getTopList(){
	$.ajax({
		url:"/rest/admin/topcategory",
		type:"get",
		success:function(result, status, xhr){
			printTopList(result);
		}
	});
}
function printTopList(jsonList){
	var sel=$("select[name='top']");
	$(sel).empty();  //기존 카테고리 초기화
	var tag="";
	for(var i=0; i<jsonList.length; i++){
		var topcategory=jsonList[i];
		tag+="<option value=\""+topcategory.topcategory_id+"\">"+topcategory.category_name+"</option>";
	}
	$(sel).append(tag);
}
function registTop(){
	$.ajax({
		url:"/rest/admin/topcategory",
		type:"post",
		data:{
			category_name:$($("input[name='category_name'][0]")).val()
		},
		success:function(result, status, xhr){
			alert(status);
		},
		error:function(xhr, status, error){
			alert(status+", "+error);
		}
	})
}

//하위 카테고리에 대한 비동기 등록요청
//주의) 반드시 상위 카테고리가 하나라도 선택되어있어야 한다(유효성 체크)
function registSub(){
	if($($("select")[0]).prop('selectedIndex')==-1){
		alert("좌측 영역에서 상위카테고리를 선택하세요");
		return;
	}
	$.ajax({
		url:"/rest/admin/subcategory",
		type:"post",
		data:{
			"category_name":$($("input[name='category_name']")[1]).val(),
			"topcategory.topcategory_id":$($("select")[0]).val()
		},
		success:function(result, status, xhr){
			alert(result);
			getSubList($($("select")[0]).val())
		},
		error:function(xhr, status, error){
		}
	})		
}

//선택한 상위 카테고리에 소속된 하위 목록 가져오기
function getSubList(topcategory_id){
	$.ajax({
		url:"/rest/admin/subcategory/"+topcategory_id,
		type:"get",
		success:function(result, status, xhr){
			printSubList(result);
		},
		error:function(xhr, status, error){
			
		}
	});
}

function printSubList(jsonList){
	var sel=$("select[name='sub']");
	$(sel).empty();  //기존 카테고리 초기화
	var tag="";
	for(var i=0; i<jsonList.length; i++){
		var subcategory=jsonList[i];
		tag+="<option value=\""+subcategory.subcategory_id+"\">"+subcategory.category_name+"</option>";
	}
	$(sel).append(tag);
}
//가려져 있던 엑셀 등록폼을 등장시키자
function showExcel(){
	$("#excel-area").css({display:"block"});
}

//엑셀파일을 이용한 상품일괄 등록
function registExcel(){
	if($("input[name='excel']").val()==""){
		alert("선택한 파일이 없음")
		return ;
	}
	if(confirm("엑셀로 등록할까요?")){
	$("#excel-form").attr({
		"action":"/admin/product/excel",
		"method":"post",
		"enctype":"multipart/form-data"
	});
	$("#excel-form").submit();
	}
}

$(function(){
	getTopList();
	
	$($("select")[0]).change(function(){
		//alert("당신이 선택한 아이템의 value값은 "+ $(this).val())
		getSubList($(this).val());
	});
	
	//파일선택 컴포넌트에 이벤트 연결
	$("input[name='excel']").change(function(){
		//선택한 파일구하기
		var ext=getExt($(this).val());
		if(ext!="xls" && ext!="xlsx"){
			alert("엑셀 파일만 선택 가능합니다.");
		}
	})
})

</script>
</body>
</html>
