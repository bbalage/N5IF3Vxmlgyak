<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <head>
                <title>Autok1</title>
            </head>
            <body>
                <ul>
                    <xsl:for-each-group select="autok/auto" group-by="tipus" >
                        <li>
                            <xsl:value-of select="tipus"/>
                            <xsl:text> : </xsl:text>
                            <xsl:value-of select="count(current-group())"/>
                        </li>
                    </xsl:for-each-group>
                </ul>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>