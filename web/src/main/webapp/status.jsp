<%@ include file="/common/taglibs.jsp"%>


<body id="login">

</body>
<form id="loginForm">
        <ul>
            <c:if test="${param.error != null}">
                <li class="error">
                    <img src="${ctx}/images/iconWarning.gif" alt="<fmt:message key='icon.warning'/>" class="icon"/>
                    <fmt:message key="errors.password.mismatch"/>
                    <%--${sessionScope.ACEGI_SECURITY_LAST_EXCEPTION.message}--%>
                </li>
            </c:if>
            <li>
                <p>Welcome, <b>${pageContext.request.remoteUser}</b>! <br/> </p>
                <p>You have <b>0</b> messages  </p>
            </li>
            <li>
                  <p><a href="/logout.jsp">logout</a></p>
            </li>
        </ul>
</form>
