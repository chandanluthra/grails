<html>
	<head>
		<meta http-equiv="Content-type" content="text/html; charset=utf-8">
		<feed:meta kind="rss" version="2.0" controller="store" action="latest" params="[format:'rss']"/>
		<feed:meta kind="atom" version="1.0" controller="store" action="latest" params="[format:'atom']"/>		
		<meta name="layout" content="main">
		<title>gTunes Store</title>
	</head>
	<body id="body">
		<h1>Online Store</h1>
		<p>Browse or search the categories below:</p>
		<g:render template="/store/searchbox" />
		<g:render template="/store/top5panel" model="${pageScope.variables}" />
		<div id="musicPanel">
			<g:if test="${flash.message}">
				<div class="message">
					<g:message code="${flash.message}"></g:message>					
				</div>
			</g:if>
			<g:layoutBody />
		</div>
	</body>
	
</html>