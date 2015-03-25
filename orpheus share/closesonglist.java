/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package orpheusshare;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

/**
 *
 * @author jokz
 */
public class closesonglist implements ActionListener{
    public static JFrame slist;
    public static JFrame mnframe;

    public void actionPerformed(ActionEvent e) {
        slist.dispose();
        playerbutton.ref.doClick();
        mnframe.setVisible(true);
    }

}
