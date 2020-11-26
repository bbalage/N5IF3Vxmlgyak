<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <head>
                <title>Autok1</title>
            </head>
            <body>
                <xsl:for-each select="autok/auto">
                    <xsl:sort select="ar"/>
                    <ul>
                        <li>
                            <span padding="10px"><xsl:value-of select="@rsz"/></span>
                            <xsl:text> : </xsl:text>
                            <span padding="10px"><xsl:value-of select="ar"/></span>
                        </li>
                    </ul>
                </xsl:for-each>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>