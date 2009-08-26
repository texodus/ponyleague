<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ include file="/common/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
    <head>
        <%@ include file="/common/meta.jsp" %>
        <title><decorator:title/> | <fmt:message key="webapp.name"/></title>

        <link rel="stylesheet" type="text/css" media="all" href="<c:url value='/styles/${appConfig["csstheme"]}/theme.css'/>" />
        <link rel="stylesheet" type="text/css" media="all" href="<c:url value='/styles/myfaces.css'/>" />
        <link rel="stylesheet" type="text/css" media="print" href="<c:url value='/styles/${appConfig["csstheme"]}/print.css'/>" />
		
        <script type="text/javascript" src="<c:url value='/scripts/prototype.js'/>"></script>
        <script type="text/javascript" src="<c:url value='/scripts/scriptaculous.js'/>"></script>
        <script type="text/javascript" src="<c:url value='/scripts/global.js'/>"></script>
        <decorator:head/>
    </head>

<body<decorator:getProperty property="body.id" writeEntireProperty="true"/><decorator:getProperty property="body.class" writeEntireProperty="true"/>>

    <div id="page">
        <div id="header" class="clearfix">
            <jsp:include page="/common/header.jsp"/>
        </div>

        <div id="content" class="clearfix">
            <div class="roundedcornr_box_262063">
                <div class="roundedcornr_top_262063"><div></div></div>
                <div class="main" id="main">
                    <%@ include file="/common/messages.jsp" %>
                    <h1><decorator:getProperty property="meta.heading"/></h1>
                    <decorator:body/>
                </div>
                <div class="roundedcornr_bottom_262063"><div></div></div>
            </div>


            <c:set var="currentMenu" scope="request"><decorator:getProperty property="meta.menu"/></c:set>
                <c:if test="${empty pageContext.request.remoteUser}">
                <div id="sub">
                    <jsp:include page="/login.jsp"/>
                    <div id="divider"></div>

                    <form id="adsform">
                    <ul>

                    <li>
                    <a href="#">Lorems for sale!</a><br/>
                    <p>Vestibulum sem. Mauris tempor tempus leo. Nunc erat. Nulla mi. Sed ultrices rutrum velit. In quis libero. Donec magna. Pellentesque eu lectus. Mauris sapien diam, laoreet ut, vehicula at, tincidunt id, nisi. Suspendisse pretium.</p>
                    </li>
                    <li>
                    <a href="#">Good Deals on Ipsum</a><br/>
                    <p>Vestibulum sem. Mauris tempor tempus leo. Nunc erat. Nulla mi. Sed ultrices rutrum velit. In quis libero. Donec magna. Pellentesque eu lectus. Mauris sapien diam, laoreet ut, vehicula at, tincidunt id, nisi. Suspendisse pretium.</p>
                    </li>
                    <li>
                    <a href="#">LoremIpsum</a><br/>
                    <p>Vestibulum sem. Mauris tempor tempus leo. Nunc erat. Nulla mi. Sed ultrices rutrum velit. In quis libero. Donec magna. Pellentesque eu lectus. Mauris sapien diam, laoreet ut, vehicula at, tincidunt id, nisi. Suspendisse pretium.</p>
                    </li>
                    </ul>
                    </form>
                </div>
                </c:if>
                <c:if test="${!empty pageContext.request.remoteUser}">
                <div id="sub">
                    <decorator:getProperty property="meta.username"/>

                    <jsp:include page="/status.jsp" />
                     <div id="divider"></div>

                    <form id="adsform">
                    <ul>

                    <li>
                    <a href="#">Lorems for sale!</a><br/>
                    <p>Vestibulum sem. Mauris tempor tempus leo. Nunc erat. Nulla mi. Sed ultrices rutrum velit. In quis libero. Donec magna. Pellentesque eu lectus. Mauris sapien diam, laoreet ut, vehicula at, tincidunt id, nisi. Suspendisse pretium.</p>
                    </li>
                    <li>
                    <a href="#">Good Deals on Ipsum</a><br/>
                    <p>Vestibulum sem. Mauris tempor tempus leo. Nunc erat. Nulla mi. Sed ultrices rutrum velit. In quis libero. Donec magna. Pellentesque eu lectus. Mauris sapien diam, laoreet ut, vehicula at, tincidunt id, nisi. Suspendisse pretium.</p>
                    </li>
                    <li>
                    <a href="#">LoremIpsum</a><br/>
                    <p>Vestibulum sem. Mauris tempor tempus leo. Nunc erat. Nulla mi. Sed ultrices rutrum velit. In quis libero. Donec magna. Pellentesque eu lectus. Mauris sapien diam, laoreet ut, vehicula at, tincidunt id, nisi. Suspendisse pretium.</p>
                    </li>
                    </ul>
                    </form>

                </div>

                </c:if>
                   
            <div id="nav">
                <div class="wrapper">
                    <h2 class="accessibility">Navigation</h2>
                    <jsp:include page="/common/menu.jsp"/>
                </div>
                <hr />
            </div><!-- end nav -->
        </div>                                

        <div id="footer" class="clearfix">
            <jsp:include page="/common/footer.jsp"/>
        </div>
    </div>
</body>
</html>
