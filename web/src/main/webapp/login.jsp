<%@ include file="/common/taglibs.jsp"%>
<head>
<style type="text/css">
<!--
body {
	background-color: #000000;
	background-repeat: no-repeat;
}
.loginpanel {
	background-color: #000000;
	background-image: url("./images/login.jpg");
	width: 763px;
	margin: 0px;
	left: 0px;
	top: 0px;
	right: 0px;
	bottom: 0px;
	padding: 0px;
	text-align: center;
	position: relative;
	height: 450px;
	background-repeat: no-repeat;
	background-position: center;
}
.loginbody {
	text-align: center;
	padding-left: 100px;
	padding-top: 50px;
}
ul li.login{
    position: absolute;
    top: 169px;
    left: 52px;
    background-color: black;
    color: white;
}
ul li.password{
    position: absolute;
    top: 222px;
    left: 52px;
}
ul li .button {
    padding: 0px;
    position: absolute;
    font-size: 235%;
    font-family: arial;
    height: 36px;
    width: 118px;
    top: 263px;
    left: 47px;
    background-color: black;
    border-width: 0px;
    border-color: black;
    border-style: none;
    color: white;
}
ul li .button:hover {
    color: red;
}
-->
</style>
</head>

<body>
<div class="loginbody">
<div class="loginpanel">

<form method="post" id="loginForm" action="<c:url value='/j_security_check'/>"
    onsubmit="saveUsername(this);return validateForm(this)">
<ul>
<c:if test="${param.error != null}">
    <li class="error">
        <img src="${ctx}/images/iconWarning.gif" alt="<fmt:message key='icon.warning'/>" class="icon"/>
        <fmt:message key="errors.password.mismatch"/>
        <%--${sessionScope.ACEGI_SECURITY_LAST_EXCEPTION.message}--%>
    </li>
</c:if>
    <li class="login">
        <input type="text" class="text medium" name="j_username" id="j_username" tabindex="1" />
    </li>

    <li class="password">
        <input type="password" class="text medium" name="j_password" id="j_password" tabindex="2" />
    </li>
    <li>
        <input type="submit" class="button" name="login" value="LOGIN" tabindex="4" />
    </li>
</ul>
</form>
</div>
</div>

<%@ include file="/scripts/login.js"%>
<br/>
</body>

