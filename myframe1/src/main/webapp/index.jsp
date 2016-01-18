<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set  var="ctx"  value="<%= request.getContextPath() %>" />
<script>
window.location.href = "${ctx}/login";
</script>