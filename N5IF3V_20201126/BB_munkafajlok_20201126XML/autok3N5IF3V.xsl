<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <head>
                <title>Autok1</title>
            </head>
            <body>
                <xsl:value-of select="count(autok/auto[ar > 30000])"/>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>