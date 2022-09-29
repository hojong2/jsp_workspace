<%@page import="com.academy.shopping.model.domain.Product"%>
<%@page import="java.util.List"%>
<%@page import="com.academy.shopping.model.domain.TopCategory"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	Product product = (Product)request.getAttribute("product");
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>AdminLTE 3 | Advanced form elements</title>
	<%@ include file="../inc/header_link.jsp" %>
<style>
.col-md-9 *{
margin:5px;
}
</style>

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
            <h1>상품 상세 보기</h1>
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
        <!-- SELECT2 EXAMPLE -->
        <div class="card card-primary">
          <div class="card-header">
            <h3 class="card-title">상품 등록</h3>

            <div class="card-tools">
              <button type="button" class="btn btn-tool" data-card-widget="collapse">
                <i class="fas fa-minus"></i>
              </button>
              <button type="button" class="btn btn-tool" data-card-widget="remove">
                <i class="fas fa-times"></i>
              </button>
            </div>
          </div>
          <!-- /.card-header -->
          <form>
	          <div class="card-body">
	            <div class="row">
	            	<div class="col-md-3">
		            	<div class="col-md-12">
		                <div class="form-group">
		                  <label>상위 카테고리</label>
	
		                  <select class="form-control select" style="width: 100%;" size="7" name="top" id="top">
		                  	<option><%=product.getSubcategory().getTopcategory().getCategory_name()%></option>
		                  </select>
		                </div>
		                              
		                </div>
		                <!-- /.form-group -->
		              <div class="col-md-12">
		                <div class="form-group">
		                  <label>하위 카테고리</label>
	
		                  <select class="form-control select" style="width: 100%;" size="7" name="subcategory.subcategory_id" id="sub">
		                  </select>
		                </div>
		                              
		                </div>
	                </div>
	                
		            	<div class="col-md-9">
		            		<input type="hidden" name="product_id" value="<%=product.getProduct_id() %>">
		            		<input type="text" name="product_name" class="form-control" value=<%=product.getProduct_name() %>>
		            		<input type="text" name="brand" class="form-control" value=<%=product.getBrand() %>>
		            		<input type="number" name="price" class="form-control" value=<%=product.getPrice() %>>
		            		<input type="number" name="discount" class="form-control" value=<%=product.getDiscount() %>>
		            		<textarea class="form-control" name="memo"><%=product.getMemo()%></textarea>
		            		<textarea class="form-control" id="summernote" style="height:200px" name="detail"><%=product.getDetail()%></textarea>
		            		<img src="/static/data/<%=product.getProduct_img()%>" width="45px">
		            		<input type="hidden"  name="product_img" value="<%=product.getProduct_img()%>">
		            		<input type="file" class="form-control" name="photo" >
		            		
		            		
		            		<button type="button" class="btn btn-warning" onClick="editProduct()">상품 수정</button>
		            		<button type="button" class="btn btn-warning" onClick="deleteProduct()">상품 삭제</button>
		            		<button type="button" class="btn btn-primary" onClick="location.href='/admin/product/list';">목록 보기</button>
		            	</div>
	
	              
	                <!-- /.form-group -->
	              </div>
	              <!-- /.col -->
	            </div>
            </form>
            <!-- /.row -->     
          <!-- /.card-body -->
          <div class="card-footer">
            Visit <a href="https://select2.github.io/">Select2 documentation</a> for more examples and information about
            the plugin.
          </div>
        </div>
        <!-- /.card -->

        
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
<script src="/static/admin/plugins/summernote/summernote-bs4.min.js"></script>
<!-- AdminLTE for demo purposes -->
<!-- Page specific script -->
<script>
//동기 삭제
/*
function deleteProduct(){
	if(confirm("정말 삭제하시겠습니까?")){
		$("form").attr({
			"action":"/admin/product/delete",
			"method":"post",
		});
		$("form").submit();
	}
}
*/
//비동기 삭제
function deleteProduct(){
	//기존의 폼양식을 전송할 수 있도록 쪼개자, key-value 쌍으로 분리시킴
	var formArray=$("form").serializeArray();
	
	//1안
	//서버에 전송시 json으로 보내기
	var json={};
	for(var i=0; i<formArray.length; i++){
		json[formArray[i].name]=formArray[i].value;
	}
	console.log(json);

	if(confirm("정말 삭제하시겠습니까?")){
		$.ajax({
			url:"/rest/admin/product/delete",
			type:"post",
			data:JSON.stringify(json),
			contentType:"application/json;charset=utf-8",  //서버에게 이 자료가 json 형태라는 것을 알려줌(헤더에서)
			processData:false,  //query string화 시키지 않도록 방지
			success:function(result, status, xhr){
				alert(result);
				location.href="/admin/product/list";
			}
		});
	}

}


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
		if(<%=product.getSubcategory().getTopcategory().getTopcategory_id()%>==i+1){
			tag+="<option value=\""+topcategory.topcategory_id+"\" selected>"+topcategory.category_name+"</option>";
		}else{
			tag+="<option value=\""+topcategory.topcategory_id+"\">"+topcategory.category_name+"</option>";
		}
	}
	$(sel).append(tag);
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
	var sel=$("select[name='subcategory.subcategory_id']");
	$(sel).empty();  //기존 카테고리 초기화
	var tag="";

	for(var i=0; i<jsonList.length; i++){
		var subcategory=jsonList[i];
		if(<%=product.getSubcategory().getSubcategory_id()%>==i+1){
			tag+="<option value=\""+subcategory.subcategory_id+"\" selected>"+subcategory.category_name+"</option>";
		}else{
		tag+="<option value=\""+subcategory.subcategory_id+"\">"+subcategory.category_name+"</option>";
		}
	}
	$(sel).append(tag);
}

//상품 등록 요청
function registProduct(){
	if(confirm("상품을 등록하시겠어요?")){
		$("form").attr({
			"action":"/admin/product/regist",
			"method":"post",
			"enctype":"multipart/form-data"
		});
		$("form").submit();
	}
	
}

//수정요청 - 비동기+파일업로드
//FormData - json만으로 보낼 수 없었던, 바이너리 파일까지도 보낼 수 있다.
function editProduct(){
	//기존폼을 시리얼: 폼 양식 요소들을 key-value 쌍으로 구성하여 배열로 반환
	var formArray = $("form").serializeArray();
	
	//json 대신 바이너리 파일을 포함할 수 있는 FormData를 이용하자
	var formData = new FormData();
	for(var i=0; i<formArray.length; i++){
		formData.append(formArray[i].name, formArray[i].value);
	}
	//특히 input type="file" 컴포넌트는 텍스트가 아니므로, 실제 선택한 파일을 포함
	formData.append("photo", $("input[name='photo']")[0].files[0]);
	console.log(formData);
	//ajax 전송
	$.ajax({
		url:"/rest/admin/product/update",
		type:"post",
		data:formData,
		enctype:"multipart/form-data",
		contentType:false,  //문자열화 시킴 방지(바이너리 파일이 포함될 경우 이 속성을 false로 바꿔준다)
		processData:false,  //문자열화 시킴 방지(바이너리 파일이 포함될 경우 이 속성을 false로 바꿔준다)
		success:function(result, status, xhr){
			alert(result);
		}
	})
}

$(function(){
	 $('#summernote').summernote({
		 height:200
	 });
	getTopList();
	
	getSubList(<%=product.getSubcategory().getTopcategory().getTopcategory_id()%>);
})

</script>
</body>
</html>
