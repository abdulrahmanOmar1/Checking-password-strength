package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class SecurityAssignmentWithHint extends Application {

	final static String filename="commenWord";
    @Override
    public void start(Stage primaryStage) {

    	TextField  Pass = new TextField ();
        Pass.setPrefWidth(250);
        
        Button btn = new Button("Verify Password");
        btn.setStyle("-fx-font: 17 arial; -fx-base: #363ceb;");

        Label LH1 = new Label();
        
        Button btn2 = new Button("See Rules to write Stringth Password");
        btn2.setStyle("-fx-font: 14 arial; -fx-base: #ee2211;");

        Label L1 = new Label();
        
        Rectangle reg = new Rectangle(40,24);
        reg.setFill(Color.TRANSPARENT);        
        
        btn.setOnAction(e -> {
        String p , str; 
        p= Pass.getText();
        if (p.length() < 8) {
            LH1.setText("The minimum password length is 8 characters.");
            LH1.setTextFill(Color.RED);
            LH1.setStyle("-fx-font: 13 arial");
//          System.out.print("test case ");
            return; 
        }
        
//          System.out.print("test case ");
        
        str = CheckFunction(p , p.length());
        
        
        if(str.equals("Weak"))
        L1.setText(str + " Password :( \n -Add special characters(!,@,/,\\,&,^,..)\n -use a mix of capital Letter and small Letter letters(IonGyU ,..)\n -include numbers(1,6,3,4,..)\n Variance = "+VarianceFunction(p , p.length())+"\n Entropy = "+EntropyFunction(p , p.length()));
        
        if(str.equals("Medium"))
        L1.setText(str + " Password  , Please look at the Rules \n Variance = "+VarianceFunction(p , p.length())+"\nEntropy = "+EntropyFunction(p , p.length()));
        
        if(str.equals("Strong"))
        L1.setText(str + " Password  :) \n Variance = "+VarianceFunction(p , p.length())+"\nEntropy = "+EntropyFunction(p , p.length()));

        if(str.equals("CommonWord")) {
        L1.setText( "Careful !!\nThese are common words that are easy for a hacker to predict !\nPlease write password agian ");
        L1.setTextFill(Color.RED);
        L1.setStyle("-fx-font: 15 arial");
        return; 
        }
        L1.setStyle("-fx-font: 15 arial");

    });
       
        //Here the rules for the password are created
        btn2.setOnAction(e -> {
        	Stage sg = new Stage();
            VBox vbox = new VBox(5);
            vbox.setPadding(new Insets(5));
            Label r1,r2,r3,r4,r5,r6,r7,L,ex1,ex2,ex3,titel;
             titel = new Label("Stringth Password Rules:");
             r1 = new Label("1. 8 characters at least");
             r2 = new Label("2. One small letter at least(a,b,c,...)");
             r3 = new Label("3. One capital letter at least(A,B,C,...)");
             r4 = new Label("4. One special character at least(!,@,#,$,%,...)");
             r5 = new Label("5. One digit at least(1,2,3,4,...)");
             r6 = new Label("6. Make your password specific to your\npersonal information or a specific sequence\n(Abood123,abcdefgh,12345567,...)");
             r7 = new Label("7. avoidance of common words(password,computer ,...)");

             L = new Label(" ------- Example Password ---------");
             ex1 = new Label("AxWedJdC/123! -->Strong ");
             ex2 = new Label("Apm!db-12 -->Medium");
             ex3 = new Label("12345678a -->Weak");

            vbox.getChildren().addAll(titel, r1, r2, r3, r4, r5,r6,r7, L, ex1, ex2, ex3);
//          System.out.print("test case ");
            Scene S = new Scene(vbox, 280, 350);
            S.getRoot().setStyle("-fx-background-color: #fab2b2;");
            sg.setScene(S);
            sg.showAndWait();
        });

            //Here the color of the rectangle is changed depending on the password strength
            Pass.textProperty().addListener((observable, oldValue, passNew) -> {
            LH1.setText(""); 
            String passStrong= CheckFunction(passNew , passNew.length());

            if ("Strong".equals(passStrong)) {
            	reg.setFill(Color.GREEN);
            } else if ("Medium".equals(passStrong)) {
            	reg.setFill(Color.ORANGE);
            } else if ("CommonWord".equals(passStrong)){
            	reg.setFill(Color.BLUE);
            }else {
            	reg.setFill(Color.RED);
            }

            if (passNew.length() < 8) {
                LH1.setText("The minimum password length is 8 characters.");
                LH1.setTextFill(Color.RED);
                LH1.setStyle("-fx-font: 15 arial");
            }
        });

        
        HBox hbox = new HBox(5);
        hbox.getChildren().add(Pass);
        hbox.getChildren().add(reg);

        VBox vbox = new VBox(15);
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().add(hbox);
        vbox.getChildren().add( btn);
        vbox.getChildren().add(btn2);
        vbox.getChildren().add(LH1);
        vbox.getChildren().add(L1);

        Scene s = new Scene(vbox, 450, 350);
        s.getRoot().setStyle("-fx-background-color: #00f0f0;");
        primaryStage.setScene(s);
        primaryStage.show();
    }

    private static  String CheckFunction(String password , int length) {

    	int Points=Points(password,length);
        double Entropy =EntropyFunction(password , password.length());
        double Varnice =VarianceFunction(password ,length );
        
             if(CommenWord(password ,filename)){
 				return "CommonWord";
             }

			if(Points == 4  && Varnice >= 550 && Entropy >= 75) {
				return "Strong";
			}else if ( Points >=3 && Varnice >400   &&  Entropy >= 38.0 ) {
				return "Medium";
			}else if(Points >=3) {
				return "Medium";
			}else {
			return "Weak";
		}
    }
    
    private static  int Points(String password , int length) {
    	
    	   boolean capitalLetter = password.matches(".*[A-Z].*");
           boolean smallLetter = password.matches(".*[a-z].*");
           boolean numbers = Number(password);
           boolean SpecialSymbols = password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\",.<>/?].*");
           
           int Points=0;
           if(capitalLetter){
        	   Points++;
           }
           if(smallLetter){
        	   Points++;
           }
           if(numbers){
        	   Points++;
           }
           if(SpecialSymbols){
        	   Points++;
           }
           return Points;   
    }

    
    private static boolean CommenWord(String pass, String fileName) {
        try (BufferedReader r = new BufferedReader(new FileReader(fileName))) {
            String commen;
            while ((commen = r.readLine()) != null) {
                if (commen.trim().equalsIgnoreCase(pass)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
   
    	private static double VarianceFunction(String pass , int length) {
    	double m = MeanFunction(pass ,length);
    	int i;
    	double sum = 0;
    	for (i = 0; i < length; i++) {
    	double charpass = pass.charAt(i);
    	double x = charpass - m;
    	sum += Math.pow(x, 2);
    	}
    	//System.out.print(sum);
    	double VarianceResult=sum/length;
    	return VarianceResult;
    	}

    	private static double MeanFunction(String pass , int length) {
    	double sum = 0;
    	int i=0;
    	for (i = 0; i < length ; i++) {
    	char x = pass.charAt(i);
    	sum += x;
    	//System.out.print(i +":"+ sum );
    	}
    	
    	double Mean=sum / pass.length();
    	return Mean;
    	}

   
    private static double EntropyFunction(String pass , int length) {
        int rang = RangFunction(pass ,length);
        double R=Math.pow(rang, length);
        double e = (double) (Math.log(R) / Math.log(2));
        return e;
    }
    
    private static int RangFunction(String pass , int length) {
        int i , sum=0;
        boolean CapitalLetter = false;
        boolean SmallLetter = false;
        boolean Numerics = false;
        boolean SpecialSymbols = false;

        for ( i = 0; i < length; i++) {
            char c = pass.charAt(i);

            if (Character.isDigit(c)) {
            	Numerics = true;
            } else if (Character.isLowerCase(c)) {
            	SmallLetter = true;
            } else if (Character.isUpperCase(c)) {
            	CapitalLetter = true;
            } else {
            	SpecialSymbols = true;
            }
        }
        
        if (Numerics == true) {
        	sum += 10; 
        }
        if (SpecialSymbols == true) {
        	sum += 32; 
        }
        if (CapitalLetter == true) {
        	sum += 26; 
        }
        if (SmallLetter == true) {
        	sum += 26; 
        }
        return sum;
    }  
    
    public static boolean Number(String pass) {
		  for(int j=0;j<pass.length();j++) {
			  int c=pass.charAt(j);
			  if(c>=48&&c<=57) {
				  return true;
			  }
		  }
		  return false;
	  }
    
    
    public static void main(String[] args) {
        launch(args);
    }

}
