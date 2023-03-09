/*
 * Copyright 2023 National Bank of Belgium
 * 
 * Licensed under the EUPL, Version 1.2 or – as soon they will be approved 
 * by the European Commission - subsequent versions of the EUPL (the "Licence");
 * You may not use this work except in compliance with the Licence.
 * You may obtain a copy of the Licence at:
 * 
 * https://joinup.ec.europa.eu/software/page/eupl
 * 
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the Licence is distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Licence for the specific language governing permissions and 
 * limitations under the Licence.
 */
package demetra.desktop.stl.ui;

import demetra.desktop.mstl.*;
import demetra.desktop.stl.StlPlusDocumentManager;
import demetra.desktop.ui.processing.TsProcessingViewer;
import demetra.desktop.workspace.DocumentUIServices;
import demetra.desktop.workspace.WorkspaceFactory;
import demetra.desktop.workspace.WorkspaceItem;
import demetra.desktop.workspace.ui.WorkspaceTsTopComponent;
import jdplus.stlplus.StlPlusDocument;
import org.openide.windows.TopComponent;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.explorer.ExplorerManager;
import org.openide.explorer.ExplorerUtils;
import org.openide.util.NbBundle;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(dtd = "-//demetra.desktop.stl//StlPlus//EN",
        autostore = false)
@TopComponent.Description(preferredID = "StlPlusTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE", 
        persistenceType = TopComponent.PERSISTENCE_NEVER)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Seasonal Adjustment", id = "demetra.desktop.stlplus.StlPlusTopComponent")
@ActionReference(path = "Menu/Statistical methods/Seasonal Adjustment/Single Analysis", position = 3000)
@TopComponent.OpenActionRegistration(displayName = "#CTL_StlPlusAction")
@NbBundle.Messages({
    "CTL_StlPlusAction=STL+",
    "CTL_StlPlusTopComponent=STL+ Window",
    "HINT_StlPlusTopComponent=This is a STL+ window"
})
public final class StlPlusTopComponent extends WorkspaceTsTopComponent<StlPlusDocument> {

    private final ExplorerManager mgr = new ExplorerManager();

    private static StlPlusDocumentManager manager() {
        return WorkspaceFactory.getInstance().getManager(StlPlusDocumentManager.class);
    }

    public StlPlusTopComponent() {
        this(null);
    }

    public StlPlusTopComponent(WorkspaceItem<StlPlusDocument> doc) {
        super(doc);
        initComponents();
        setToolTipText(NbBundle.getMessage(StlPlusTopComponent.class, "HINT_StlPlusTopComponent"));
        associateLookup(ExplorerUtils.createLookup(mgr, getActionMap()));
    }

    @Override
    public ExplorerManager getExplorerManager() {
        return mgr;
    }

    @Override
    protected TsProcessingViewer initViewer() {
        return TsProcessingViewer.create(getElement(), DocumentUIServices.forDocument(StlPlusDocument.class));
    }


    @Override
    public WorkspaceItem<StlPlusDocument> newDocument() {
        return manager().create(WorkspaceFactory.getInstance().getActiveWorkspace());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(new java.awt.BorderLayout());
    }// </editor-fold>//GEN-END:initComponents

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }

    @Override
    protected String getContextPath() {
        return StlPlusDocumentManager.CONTEXTPATH;
    }
}
