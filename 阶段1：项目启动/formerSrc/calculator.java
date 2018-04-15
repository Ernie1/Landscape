import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class calculator extends JFrame implements ActionListener{
	
	//定义组件
	JTextField[] input;
	JLabel[] label;
	JButton[] operator;
	
	public calculator(){
		
		//创建组件
		input = new JTextField[] {
			new JTextField(10),
			new JTextField(10)
		};
		label = new JLabel[] {
			new JLabel(""),
			new JLabel("="),
			new JLabel("")
		};
		operator = new JButton[] {
			new JButton("+"),
			new JButton("-"),
			new JButton("*"),
			new JButton("/"),
			new JButton("OK")
		};
		
		//设置网格布局, 组件样式, 注册监听
		setLayout(new GridLayout(2, 5, 5, 5));
		for(int i = 0; i < 3; ++i){
			if(i < 2){
				input[i].setHorizontalAlignment(JTextField.CENTER);
				add(input[i]);
			}
			label[i].setHorizontalAlignment(JLabel.CENTER);
			label[i].setBorder(BorderFactory.createLineBorder(Color.gray));
			add(label[i]);
		}
		for(JButton j : operator){
			add(j);
			j.addActionListener(this);
			j.setActionCommand(j.getText());
		}
		
		//给窗体设置标题
		setTitle("Easy Calculator");
		//设置窗体
		setSize(300, 150);
		//设置初始位置
		setLocation(500, 500);
		//设置当窗口关闭时，保证JVM也退出
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//显示
		setVisible(true);
	}
	public static void main(String[] args) {
		calculator gui1 = new calculator();
	}
	
	//对事件处理的方法
	@Override
	public void actionPerformed(ActionEvent e) {
		//判断是哪个按钮被点击
		if(e.getActionCommand().equals("OK")){
			if(!label[0].getText().isEmpty()){
				double a = Double.parseDouble(input[0].getText()), b = Double.parseDouble(input[1].getText());
				if(label[0].getText().equals("+"))
					label[2].setText(a+b+"");
				else if(label[0].getText().equals("-"))
					label[2].setText(a-b+"");
				else if(label[0].getText().equals("*"))
					label[2].setText(a*b+"");
				else if(label[0].getText().equals("/"))
					label[2].setText(a/b+"");
			}
		}
		else
			label[0].setText(e.getActionCommand());
	}
}