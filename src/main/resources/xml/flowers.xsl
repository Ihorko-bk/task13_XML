<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/">
        <html>
            <body>
                <table border="1">
                    <tr>
                        <td><b>Name</b></td>
                        <td><b>Soil</b></td>
                        <td><b>Origin</b></td>
                        <td>
                            <b>Visual parameters:</b><br/>
                            Stem color<br/>
                            Leaf color<br/>
                            Plant avg size
                        </td>
                        <td>
                            <b>Growing tips:</b><br/>
                            Temperature<br/>
                            Light<br/>
                            Watering per week
                        </td>
                        <td><b>Multiplying</b></td>
                    </tr>
                    <xsl:for-each select="flowers/flower">
                        <tr>
                            <td>
                                <xsl:value-of select="name"/>
                            </td>
                            <td>
                                <xsl:choose>
                                    <xsl:when test="soil = 'підзолиста'">
                                        <xsl:attribute name="bgcolor">#e1e1d0</xsl:attribute>
                                    </xsl:when>
                                    <xsl:when test="soil = 'ґрунтова'">
                                        <xsl:attribute name="bgcolor">#1f1f14</xsl:attribute>
                                    </xsl:when>
                                    <xsl:otherwise>
                                        <xsl:attribute name="bgcolor">#9c9c63</xsl:attribute>

                                    </xsl:otherwise>
                                </xsl:choose>
                                <xsl:value-of select="soil"/>
                            </td>
                            <td>
                                <xsl:value-of select="origin"/>
                            </td>
                            <td>
                                <xsl:value-of select="visual_parameters/stem_color"/><br/>
                                <xsl:value-of select="visual_parameters/leaf_color"/><br/>
                                <xsl:value-of select="visual_parameters/plant_average_size"/>
                            </td>
                            <td>
                                <xsl:value-of select="growing_tips/temperature"/><br/>
                                <xsl:value-of select="growing_tips/light"/><br/>
                                <xsl:value-of select="growing_tips/watering"/>
                            </td>
                            <td>
                                <xsl:value-of select="multiplying"/>
                            </td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>