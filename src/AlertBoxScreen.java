import javax.swing.*;
public class AlertBoxScreen {
    JFrame f;
    public AlertBoxScreen(String message){
        f=new JFrame();
        JOptionPane.showMessageDialog(f,message);
    }

}  