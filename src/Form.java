import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Form {
	
	public static JButton [] buttons=new JButton[16];
	
	private static void setLocation(int [][] arr){
		int index,x=20,y=20;
		for (int i=0;i<4;i++)
			for (int j=0;j<4;j++){
				index=arr[i][j];
				buttons[index].setLocation(x+i*50,y+j*50);
			}
	}
	
	public static void main(String[] args) {
		
		final Field field=new Field();
		JFrame frame= new JFrame("Пятнашки");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		final int [][] f=field.getField();
		
		for (int i=0;i<16;i++){
			int width=50,height=50;
			final int index;
			index=i;
			if (i==0) 
				buttons[i]=new JButton("");
			else
				buttons[i]=new JButton(Integer.toString(i));	
			buttons[i].setSize(width, height);
			panel.add(buttons[i]);
			buttons[i].addActionListener( new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	int [] find=FindButton(index,f);
	            	if (field.changeOnClick(find[0], find[1])){
	            		for (int i=0;i<16;i++)
	            			buttons[i].setVisible(false);
	            		JTextArea a=new JTextArea("Вы выиграли!");
	            		a.setVisible(true);
	            	};
	            	setLocation(f);
	            };
	           });
			
		}
		
		frame.setContentPane(panel);
		frame.setSize(300, 300);
	}
	
	private static int[] FindButton(int k,int [][]arr){
		int[] res={0,0};
		for (int i=0;i<4;i++)
			for (int j=0;j<4;j++){
				if (arr[i][j]==k) {
					res[0]=i;
					res[1]=j;
					break;
				}
			}
		return res;
	}
	
	public class TestActionListener implements ActionListener {
		
	    public void actionPerformed(ActionEvent e) {
	    	
	    	
	    }
	}

}


