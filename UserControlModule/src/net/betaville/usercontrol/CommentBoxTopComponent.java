/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.betaville.usercontrol;

import edu.poly.bxmc.betaville.CentralLookup;
import edu.poly.bxmc.betaville.model.ClientSession;
import edu.poly.bxmc.betaville.model.Comment;
import edu.poly.bxmc.betaville.model.Design;
import edu.poly.bxmc.betaville.net.InsecureClientManager;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.List;
import javax.swing.text.StyledEditorKit;
import javax.swing.text.html.HTMLEditorKit;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(dtd = "-//net.betaville.usercontrol//CommentBox//EN",
autostore = false)
@TopComponent.Description(preferredID = "CommentBoxTopComponent",
//iconBase="SET/PATH/TO/ICON/HERE", 
persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "rightSlidingSide", openAtStartup = false)
@ActionID(category = "Window", id = "net.betaville.usercontrol.CommentBoxTopComponent")
@ActionReference(path = "Menu/Window" /*
 * , position = 333
 */)
@TopComponent.OpenActionRegistration(displayName = "#CTL_CommentBoxAction",
preferredID = "CommentBoxTopComponent")
@Messages({
    "CTL_CommentBoxAction=CommentBox",
    "CTL_CommentBoxTopComponent=CommentBox Window",
    "HINT_CommentBoxTopComponent=This is a CommentBox window"
})
public final class CommentBoxTopComponent extends TopComponent implements LookupListener {

    public CommentBoxTopComponent() {
        initComponents();
        setName(Bundle.CTL_CommentBoxTopComponent());
        setToolTipText(Bundle.HINT_CommentBoxTopComponent());
        putClientProperty(PROP_KEEP_PREFERRED_SIZE_WHEN_SLIDED_IN, Boolean.TRUE);

    }
    private Lookup.Result<Design> result = null;
    List<Comment> cList;
    StringBuilder appendText;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        commentPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        displayText = new javax.swing.JEditorPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();

        commentPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        displayText.setContentType(org.openide.util.NbBundle.getMessage(CommentBoxTopComponent.class, "CommentBoxTopComponent.displayText.contentType")); // NOI18N
        displayText.setEditable(false);
        jScrollPane1.setViewportView(displayText);

        textArea.setColumns(20);
        textArea.setLineWrap(true);
        textArea.setRows(2);
        textArea.setWrapStyleWord(true);
        jScrollPane2.setViewportView(textArea);

        org.openide.awt.Mnemonics.setLocalizedText(jButton1, org.openide.util.NbBundle.getMessage(CommentBoxTopComponent.class, "CommentBoxTopComponent.jButton1.text")); // NOI18N
        jButton1.setFocusTraversalPolicyProvider(true);
        jButton1.setRequestFocusEnabled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout commentPanelLayout = new javax.swing.GroupLayout(commentPanel);
        commentPanel.setLayout(commentPanelLayout);
        commentPanelLayout.setHorizontalGroup(
            commentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, commentPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton1))
        );
        commentPanelLayout.setVerticalGroup(
            commentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(commentPanelLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(commentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(commentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
//        String readText, replaceText;
//
//        readText = textArea.getText();
//        textArea.setText("");
//        displayComments(readText);
    }//GEN-LAST:event_jButton1ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel commentPanel;
    private javax.swing.JEditorPane displayText;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea textArea;
    // End of variables declaration//GEN-END:variables

    public void displayComments(String readText) {
        String replaceText;
        replaceText = readText.replaceAll("\n", "<br>");
        appendText.append("<html><body><hr>").append(replaceText).append("</body></html>");
        displayText.setText(readText);
        try {
            ((HTMLEditorKit) displayText.getEditorKit()).read(
                    new java.io.StringReader(appendText.toString()), displayText.getDocument(), displayText.getDocument().getLength());
        } catch (Throwable bl) {
            System.out.println("-- " + bl.getMessage());
        }
        displayText.setCaretPosition(displayText.getDocument().getLength());
    }

    @Override
    public void componentOpened() {
        result = CentralLookup.getDefault().lookupResult(Design.class);
        result.addLookupListener(this);
    }

    @Override
    public void componentClosed() {
        result.removeLookupListener(this);
        result = null;
    }

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
    public void resultChanged(LookupEvent le) {
        Design design = CentralLookup.getDefault().lookup(Design.class);
        //String replaceText;
        appendText = new StringBuilder("<html><body>");
        //StringBuilder buffer = new StringBuilder();
        if (design != null) {
            try {
                InsecureClientManager icm = new InsecureClientManager(null, CentralLookup.getDefault().lookup(ClientSession.class).getServer());
                cList = icm.getComments(design.getID());
                for (Iterator<Comment> it = cList.iterator(); it.hasNext();) {
                    //Comment commentItem = it.next();
                    //replaceText = it.next().getComment().replaceAll("\n", "<br>");
                    appendText.append("<hr>").append(it.next().getComment().replaceAll("\n", "<br>")).append("<hr>");
                    //buffer.append(it.next().getComment());
                }
                appendText.append("</body></html>");
                displayText.setText(appendText.toString());
                //displayComments(buffer.toString());
                //jTextArea1.setText(buffer.toString());

            } catch (UnknownHostException ex) {
                Exceptions.printStackTrace(ex);
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
    }
}
