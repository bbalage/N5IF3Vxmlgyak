<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<html>
		<head>
			<title>Órarend</title>
		</head>
		<body>
			<table border="1">
				<tr>
					<th>Ido</th>
					<th>Hetfo</th>
					<th>Kedd</th>
					<th>Szerda</th>
					<th>Csutortok</th>
					<th>Pentek</th>
				</tr>
				<xsl:for-each select="osztaly/alkalmazott">
					<tr>
						<td><xsl:value-of select="keresztnev"/></td>
						<td><xsl:value-of select="vezeteknev"/></td>
						<td><xsl:value-of select="becenev"/></td>
						<td><xsl:value-of select="fizetes"/></td>
					</tr>
				</xsl:for-each>
			</table>
		</body>
		</html>
	</xsl:template>
</xsl:stylesheet>