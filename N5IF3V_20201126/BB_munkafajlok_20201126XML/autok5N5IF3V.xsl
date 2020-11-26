<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <head>
                <title>Autok1</title>
            </head>
            <body>
                <ul>
                <xsl:for-each select="autok/auto">
                    <xsl:if test="tulaj/varos/text()='Miskolc'">
                            <li><xsl:value-of select="@rsz"/></li>
                    </xsl:if>
                </xsl:for-each>
                </ul>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>