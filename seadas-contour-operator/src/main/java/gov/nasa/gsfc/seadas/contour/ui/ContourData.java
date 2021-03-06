package gov.nasa.gsfc.seadas.contour.ui;

import com.bc.ceres.core.runtime.RuntimeContext;
import gov.nasa.gsfc.seadas.watermask.ui.SourceFileInfo;

import javax.swing.event.SwingPropertyChangeSupport;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Aynur Abdurazik (aabduraz)
 * Date: 9/5/13
 * Time: 1:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class ContourData {


    ContourData contourData = this;

    public static String NOTIFY_USER_FILE_INSTALL_RESULTS_EVENT = "NOTIFY_USER_FILE_INSTALL_RESULTS_EVENT";
    public static String FILE_INSTALLED_EVENT2 = "FILE_INSTALLED_EVENT2";
    public static String PROMPT_REQUEST_TO_INSTALL_FILE_EVENT = "REQUEST_TO_INSTALL_FILE_EVENT";
    public static String CONFIRMED_REQUEST_TO_INSTALL_FILE_EVENT = "CONFIRMED_REQUEST_TO_INSTALL_FILE_EVENT";

    public static String LANDMASK_URL = "http://oceandata.sci.gsfc.nasa.gov/SeaDAS/installer/landmask";

    private boolean createMasks = false;
    private boolean deleteMasks = false;

    private double maskTransparency = 0.5;
    private boolean showMaskAllBands = true;
    private Color maskColor = new Color(0, 0, 255);
    private String maskName = "Contour";
    private String maskDescription = "Contour pixels";

    private double maskMinDepth = 0;
    private double maskMaxDepth = -10923;


    public static final String OCSSWROOT_ENVVAR = "OCSSWROOT";
    public static final String OCSSWROOT_PROPERTY = "ocssw.root";


    private int superSampling = 1;

    private String bathymetryBandName = "bathymetry";


    private ArrayList<SourceFileInfo> sourceFileInfos = new ArrayList<SourceFileInfo>();
    private SourceFileInfo sourceFileInfo;

    private final SwingPropertyChangeSupport propertyChangeSupport = new SwingPropertyChangeSupport(this);


    public ContourData() {

        SourceFileInfo sourceFileInfo;

        File ocsswRootDir = getOcsswRoot();
        File ocsswRunDir = new File(ocsswRootDir, "run");
        File ocsswRunDataDir = new File(ocsswRunDir, "data");
        File ocsswRunDataCommonDir = new File(ocsswRunDataDir, "common");
        //File bathymetryFile = new File(ocsswRunDataCommonDir, ContourMaskClassifier.FILENAME_BATHYMETRY);


//        sourceFileInfo = new SourceFileInfo(ContourMaskClassifier.RESOLUTION_1km,
//                SourceFileInfo.Unit.METER,
//                bathymetryFile);
//        getSourceFileInfos().add(sourceFileInfo);
//        // set the default
//        this.sourceFileInfo = sourceFileInfo;
//
//        sourceFileInfo = new SourceFileInfo(ContourMaskClassifier.RESOLUTION_10km,
//                SourceFileInfo.Unit.METER,
//                ContourMaskClassifier.FILENAME_GSHHS_10km);
//        getSourceFileInfos().add(sourceFileInfo);
//
//        this.addPropertyChangeListener(ContourData.NOTIFY_USER_FILE_INSTALL_RESULTS_EVENT, new PropertyChangeListener() {
//            @Override
//            public void propertyChange(PropertyChangeEvent evt) {
//                SourceFileInfo sourceFileInfo = (SourceFileInfo) evt.getNewValue();
//
//                InstallResolutionFileDialog dialog = new InstallResolutionFileDialog(contourData, sourceFileInfo, InstallResolutionFileDialog.Step.CONFIRMATION);
//                dialog.setVisible(true);
//                dialog.setEnabled(true);
//            }
//        });
    }


    public static File getOcsswRoot() {
        return new File(RuntimeContext.getConfig().getContextProperty(OCSSWROOT_PROPERTY, System.getenv(OCSSWROOT_ENVVAR)));
    }


    public boolean isCreateMasks() {
        return createMasks;
    }

    public void setCreateMasks(boolean closeClicked) {
        this.createMasks = closeClicked;
    }


    public double getMaskTransparency() {
        return maskTransparency;
    }

    public void setMaskTransparency(double maskTransparency) {
        this.maskTransparency = maskTransparency;
    }


    public boolean isShowMaskAllBands() {
        return showMaskAllBands;
    }

    public void setShowMaskAllBands(boolean showMaskAllBands) {
        this.showMaskAllBands = showMaskAllBands;
    }


    public Color getMaskColor() {
        return maskColor;
    }

    public void setMaskColor(Color maskColor) {
        this.maskColor = maskColor;
    }

    public int getSuperSampling() {
        return superSampling;
    }

    public void setSuperSampling(int superSampling) {
        this.superSampling = superSampling;
    }

    public SourceFileInfo getSourceFileInfo() {
        return sourceFileInfo;
    }

    public void setSourceFileInfo(SourceFileInfo resolution) {
        this.sourceFileInfo = resolution;
    }


    public String getContourBandName() {
        return bathymetryBandName;
    }

    public void setContourBandName(String bathymetryBandName) {
        this.bathymetryBandName = bathymetryBandName;
    }


    public String getMaskName() {
        return maskName;
    }

    public void setMaskName(String maskName) {
        this.maskName = maskName;
    }

    public String getMaskMath() {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getContourBandName());
        stringBuilder.append(" >= ");
        stringBuilder.append(new Double(getMaskMaxDepth()).toString());
        stringBuilder.append(" and ");
        stringBuilder.append(getContourBandName());
        stringBuilder.append(" <= ");
        stringBuilder.append(new Double(getMaskMinDepth()).toString());

        return stringBuilder.toString();
    }


    public String getMaskDescription() {
        return maskDescription;
    }

    public void setMaskDescription(String maskDescription) {
        this.maskDescription = maskDescription;
    }


    public boolean isDeleteMasks() {
        return deleteMasks;
    }

    public void setDeleteMasks(boolean deleteMasks) {
        this.deleteMasks = deleteMasks;
    }

    public ArrayList<SourceFileInfo> getSourceFileInfos() {
        return sourceFileInfos;
    }

    public void setSourceFileInfos(ArrayList<SourceFileInfo> sourceFileInfos) {
        this.sourceFileInfos = sourceFileInfos;
    }

    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
    }

    public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(propertyName, listener);
    }

    public void fireEvent(String propertyName) {
        propertyChangeSupport.firePropertyChange(new PropertyChangeEvent(this, propertyName, null, null));
    }

    public void fireEvent(String propertyName, SourceFileInfo oldValue, SourceFileInfo newValue) {
        propertyChangeSupport.firePropertyChange(new PropertyChangeEvent(this, propertyName, oldValue, newValue));
    }


    public double getMaskMinDepth() {
        return maskMinDepth;
    }

    public String getMaskMinDepthString() {
        return new Double(maskMinDepth).toString();
    }

    public void setMaskMinDepth(double maskMinDepth) {
        this.maskMinDepth = maskMinDepth;
    }

    public void setMaskMinDepth(String maskMinDepth) {
        this.maskMinDepth = Double.parseDouble(maskMinDepth);
    }


    public double getMaskMaxDepth() {
        return maskMaxDepth;
    }


    public String getMaskMaxDepthString() {
        return new Double(maskMaxDepth).toString();
    }

    public void setMaskMaxDepth(double maskMaxDepth) {
        this.maskMaxDepth = maskMaxDepth;
    }

    public void setMaskMaxDepth(String maskMaxDepth) {
        this.maskMaxDepth = Double.parseDouble(maskMaxDepth);
    }


}



