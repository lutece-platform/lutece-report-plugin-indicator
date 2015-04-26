<jsp:useBean id="manageindicatorIndicatorHistory" scope="session" class="fr.paris.lutece.plugins.indicator.web.IndicatorHistoryJspBean" />
<% String strContent = manageindicatorIndicatorHistory.processController ( request , response ); %>

<%@ page errorPage="../../ErrorPage.jsp" %>
<jsp:include page="../../AdminHeader.jsp" />

<%= strContent %>

<%@ include file="../../AdminFooter.jsp" %>
