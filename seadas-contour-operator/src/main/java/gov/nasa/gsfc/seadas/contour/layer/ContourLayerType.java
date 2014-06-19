package gov.nasa.gsfc.seadas.contour.layer;

/**
 * Created by IntelliJ IDEA.
 * User: Aynur Abdurazik (aabduraz)
 * Date: 3/27/14
 * Time: 3:54 PM
 * To change this template use File | Settings | File Templates.
 */

import com.bc.ceres.binding.Property;
import com.bc.ceres.binding.PropertyContainer;
import com.bc.ceres.binding.PropertySet;
import com.bc.ceres.glayer.LayerContext;
import com.bc.ceres.glayer.annotations.LayerTypeMetadata;
import org.esa.beam.framework.datamodel.RasterDataNode;
import org.esa.beam.framework.datamodel.VectorDataNode;
import org.esa.beam.framework.ui.product.VectorDataLayer;
import org.esa.beam.framework.ui.product.VectorDataLayerType;

import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: Aynur Abdurazik (aabduraz)
 * Date: 3/27/14
 * Time: 3:03 PM
 * To change this template use File | Settings | File Templates.
 */
@LayerTypeMetadata(name = "ContourLayerType",
        aliasNames = {"gov.nasa.gsfc.seadas.contour.layer.ContourLayerType"})
public class ContourLayerType extends VectorDataLayerType {

    public static final String PROPERTY_NAME_RASTER = "raster";
    public static final String PROPERTY_NAME_RES_AUTO = "contour.res.auto";
    public static final String PROPERTY_NAME_RES_PIXELS = "contour.res.pixels";
    public static final String PROPERTY_NAME_RES_LAT = "contour.res.lat";
    public static final String PROPERTY_NAME_RES_LON = "contour.res.lon";
    public static final String PROPERTY_NAME_LINE_COLOR = "contour.line.color";
    public static final String PROPERTY_NAME_LINE_TRANSPARENCY = "contour.line.transparency";
    public static final String PROPERTY_NAME_LINE_WIDTH = "contour.line.width";
    public static final String PROPERTY_NAME_TEXT_ENABLED = "contour.text.enabled";
    public static final String PROPERTY_NAME_TEXT_FONT = "contour.text.font";
    public static final String PROPERTY_NAME_TEXT_FG_COLOR = "contour.text.fg.color";
    public static final String PROPERTY_NAME_TEXT_BG_COLOR = "contour.text.bg.color";
    public static final String PROPERTY_NAME_TEXT_BG_TRANSPARENCY = "contour.text.bg.transparency";
    public static final boolean DEFAULT_RES_AUTO = true;
    public static final int DEFAULT_RES_PIXELS = 128;
    public static final double DEFAULT_RES_LAT = 1.0;
    public static final double DEFAULT_RES_LON = 1.0;
    public static final Color DEFAULT_LINE_COLOR = new Color(204, 204, 255);
    public static final double DEFAULT_LINE_TRANSPARENCY = 0.0;
    public static final double DEFAULT_LINE_WIDTH = 0.5;
    public static final boolean DEFAULT_TEXT_ENABLED = true;
    public static final Font DEFAULT_TEXT_FONT = new Font("SansSerif", Font.ITALIC, 12);
    public static final Color DEFAULT_TEXT_FG_COLOR = Color.WHITE;
    public static final Color DEFAULT_TEXT_BG_COLOR = Color.BLACK;
    public static final double DEFAULT_TEXT_BG_TRANSPARENCY = 0.7;

    private static final String ALIAS_NAME_RES_AUTO = "resAuto";
    private static final String ALIAS_NAME_RES_PIXELS = "resPixels";
    private static final String ALIAS_NAME_RES_LAT = "resLat";
    private static final String ALIAS_NAME_RES_LON = "resLon";
    private static final String ALIAS_NAME_LINE_COLOR = "lineColor";
    private static final String ALIAS_NAME_LINE_TRANSPARENCY = "lineTransparency";
    private static final String ALIAS_NAME_LINE_WIDTH = "lineWidth";
    private static final String ALIAS_NAME_TEXT_ENABLED = "textEnabled";
    private static final String ALIAS_NAME_TEXT_FONT = "textFont";
    private static final String ALIAS_NAME_TEXT_FG_COLOR = "textFgColor";
    private static final String ALIAS_NAME_TEXT_BG_COLOR = "textBgColor";
    private static final String ALIAS_NAME_TEXT_BG_TRANSPARENCY = "textBgTransparency";



    @Override
    public boolean isValidFor(LayerContext ctx) {
        return true;
    }

    @Override
       protected VectorDataLayer createLayer(VectorDataNode vectorDataNode, PropertySet configuration) {
           return new ContourLayer(this, vectorDataNode, configuration);
       }


    @Override
    public PropertySet createLayerConfig(LayerContext ctx) {
        final PropertyContainer vc = new PropertyContainer();

        final Property rasterModel = Property.create(PROPERTY_NAME_RASTER, RasterDataNode.class);
        rasterModel.getDescriptor().setNotNull(true);
        vc.addProperty(rasterModel);

        final Property resAutoModel = Property.create(PROPERTY_NAME_RES_AUTO, Boolean.class, DEFAULT_RES_AUTO, true);
        resAutoModel.getDescriptor().setAlias(ALIAS_NAME_RES_AUTO);
        vc.addProperty(resAutoModel);

        final Property resPixelsModel = Property.create(PROPERTY_NAME_RES_PIXELS, Integer.class, DEFAULT_RES_PIXELS, true);
        resPixelsModel.getDescriptor().setAlias(ALIAS_NAME_RES_PIXELS);
        vc.addProperty(resPixelsModel);

        final Property resLatModel = Property.create(PROPERTY_NAME_RES_LAT, Double.class, DEFAULT_RES_LAT, true);
        resLatModel.getDescriptor().setAlias(ALIAS_NAME_RES_LAT);
        vc.addProperty(resLatModel);

        final Property resLonModel = Property.create(PROPERTY_NAME_RES_LON, Double.class, DEFAULT_RES_LON, true);
        resLonModel.getDescriptor().setAlias(ALIAS_NAME_RES_LON);
        vc.addProperty(resLonModel);

        final Property lineColorModel = Property.create(PROPERTY_NAME_LINE_COLOR, Color.class, DEFAULT_LINE_COLOR, true);
        lineColorModel.getDescriptor().setAlias(ALIAS_NAME_LINE_COLOR);
        vc.addProperty(lineColorModel);

        final Property lineTransparencyModel = Property.create(PROPERTY_NAME_LINE_TRANSPARENCY, Double.class, DEFAULT_LINE_TRANSPARENCY, true);
        lineTransparencyModel.getDescriptor().setAlias(ALIAS_NAME_LINE_TRANSPARENCY);
        vc.addProperty(lineTransparencyModel);

        final Property lineWidthModel = Property.create(PROPERTY_NAME_LINE_WIDTH, Double.class, DEFAULT_LINE_WIDTH, true);
        lineWidthModel.getDescriptor().setAlias(ALIAS_NAME_LINE_WIDTH);
        vc.addProperty(lineWidthModel);

        final Property textEnabledModel = Property.create(PROPERTY_NAME_TEXT_ENABLED, Boolean.class, DEFAULT_TEXT_ENABLED, true);
        textEnabledModel.getDescriptor().setAlias(ALIAS_NAME_TEXT_ENABLED);
        vc.addProperty(textEnabledModel);

        final Property textFontModel = Property.create(PROPERTY_NAME_TEXT_FONT, Font.class, DEFAULT_TEXT_FONT, true);
        textFontModel.getDescriptor().setAlias(ALIAS_NAME_TEXT_FONT);
        vc.addProperty(textFontModel);

        final Property textFgColorModel = Property.create(PROPERTY_NAME_TEXT_FG_COLOR, Color.class, DEFAULT_TEXT_FG_COLOR, true);
        textFgColorModel.getDescriptor().setAlias(ALIAS_NAME_TEXT_FG_COLOR);
        vc.addProperty(textFgColorModel);

        final Property textBgColorModel = Property.create(PROPERTY_NAME_TEXT_BG_COLOR, Color.class, DEFAULT_TEXT_BG_COLOR, true);
        textBgColorModel.getDescriptor().setAlias(ALIAS_NAME_TEXT_BG_COLOR);
        vc.addProperty(textBgColorModel);

        final Property textBgTransparencyModel = Property.create(PROPERTY_NAME_TEXT_BG_TRANSPARENCY, Double.class, DEFAULT_TEXT_BG_TRANSPARENCY, true);
        textBgTransparencyModel.getDescriptor().setAlias(ALIAS_NAME_TEXT_BG_TRANSPARENCY);
        vc.addProperty(textBgTransparencyModel);

        return vc;
    }
}