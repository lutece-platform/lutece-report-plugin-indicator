<jsp:useBean id="manageindicatorIndicator" scope="session" class="fr.paris.lutece.plugins.indicator.web.IndicatorJspBean" />
<% String strContent = manageindicatorIndicator.processController ( request , response ); %>

<%@ page errorPage="../../ErrorPage.jsp" %>
<jsp:include page="../../AdminHeader.jsp" />

<%= strContent %>

<%@ include file="../../AdminFooter.jsp" %>
