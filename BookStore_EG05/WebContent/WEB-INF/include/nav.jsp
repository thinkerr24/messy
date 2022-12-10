<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="scott">
	<!-- 分页导航栏只有有page对象数据时才能显示 -->
		<c:if test="${!empty page }">
			<!-- 动态设置遍历的起始索引页码和结束的页码 ，始终保持只有5个页码显示在页面中即可-->
		<c:choose>
			<c:when test="${page.totalPage<=5 }">
				<!-- 设置遍历的begin=1 -->
				<c:set var="begin" value="1"></c:set>
				<!-- 设置end的值=totalPage -->
				<c:set var="end" value="${page.totalPage }"></c:set>
			</c:when>
			<c:otherwise>
				<c:choose>
					<c:when test="${page.pageNumber<=3 }">
						<c:set var="begin" value="1"></c:set>
						<c:set var="end" value="5"></c:set>
					</c:when>
					<c:otherwise>
						<c:set var="begin" value="${page.pageNumber-2 }"></c:set>
						<c:set var="end" value="${page.pageNumber+2 }"></c:set>
						<!-- 每次设置end值后需要验证end是否超过了总页码 -->
						<c:if test="${end>page.totalPage }">
							<c:set var="end" value="${page.totalPage }"></c:set>
							<c:set var="begin" value="${end-4 }"></c:set>
						</c:if>
					</c:otherwise>
				</c:choose>
			</c:otherwise>
		</c:choose>
		
		
		<!-- 上一页的页码= 当前页码-1 -->
		<a href="${page.path }&pageNumber=${page.pageNumber-1 }"> &lt; </a>
		<c:forEach begin="${begin }" end="${end }" var="index">
			<!-- 如果index和pageNumber相等，index就是当前的页码 -->
			<c:choose>
				<c:when test="${page.pageNumber==index }">
					<span class="current">${index }</span>
				</c:when>
				<c:otherwise>
				<!-- /BookStore_EG04/BookClientServlet?type=getPageByPrice&pageNumber=2&minPrice=10&maxPrice=20\
					价格区间需要在pageNumber前拼接
				 -->
					<a href="${page.path }&pageNumber=${index }">${index }</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<a href="${page.path }&pageNumber=${page.pageNumber+1 }"> &gt; </a>
	共${page.totalPage }页，${page.totalCount }条记录 到第<input
		value="${page.pageNumber }" name="pn" id="pn_input" />页 <input
		id="sendBtn" type="button" value="确定">
		<!-- 将点击事件的js代码写在标签下 -->
		<script type="text/javascript">
			$("#sendBtn").click(function(){
				//1、获取pn_input的value值
				var pageNumber = $("#pn_input").val();
				//js提供了函数 isNaN(); is not a number    可以验证传入的内容是否不是数字
				//不是数字返回true  是返回false
				if(isNaN(pageNumber)){
					//给用户提示并不跳转
					alert("请输入正确的页码！");
					//获取之前的正确value值
					var defaultVal = $("#pn_input")[0].defaultValue;
					//设置之前的正确页码
					$("#pn_input").val(defaultVal);
				}else{
					//2、向服务器发起分页请求
					//修改window对象的location属性值可以让浏览器向新属性值地址发起新的请求
					//在js中使用EL必须写在引号内
					window.location = "${page.path }&pageNumber="
							+pageNumber;
					
				}
			});
		</script>
		</c:if>
		
	</div>