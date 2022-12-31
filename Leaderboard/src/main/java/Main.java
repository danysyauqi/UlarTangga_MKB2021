import frame.ldbFrame;
import helper.koneksi;

public class Main {
    public static void main (String [] args){
        koneksi.getConnection();
        ldbFrame viewFrame = new ldbFrame();
        viewFrame.setVisible(true);

    }
}
