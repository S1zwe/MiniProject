package gui;

import file.SaveToFile;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.PowerPlant;
import model.Route;
import model.adt.Graph;
import model.adt.Graph.*;
import util.Drive;

public class Home extends BorderPane {
	private Pane mid;
	private VBox set;	
	private  int c=0;
	private Button btnStandby, remove,btnStandby2;
	private Edge myE;
	Drive driveG = new Drive();
	private Graph myGraph;
	private Vertex<String> myVer ;
	private TextArea dis = new TextArea();
	 Button  btnV = new Button("Reset graph to default");
	 Button btnCacl = new Button("Calculate Route to use for allocation");
	 Button  btna =new Button("Get update Graph");
	   SaveToFile myFile = new SaveToFile();
	  
	  
	/**
	 * Default constructor
	 */
   public Home() {
	mid = new Pane();
	set= new VBox();
	this.setLeft(set);
	this.setCenter(mid);
	mid.setBackground(new Background(new BackgroundImage(new Image("/data/bg.jpg"), null, null, null, null)));
	
   set.getChildren().add(btnV);
   set.getChildren().add(btnCacl);
   set.getChildren().add(btna);
set.getChildren().add(dis);	

btnCacl.setOnAction(e->{
	dis.appendText(driveG.Route());
});

	btnV.setOnAction(e->{
	driveG.defaultGraph();
        setLayout();
	});
	btna.setOnAction(e->{
	dis.appendText(driveG.updatedG());
	});
	
	
	// crete new node/vertex
	mid.setOnMousePressed(e->{
		if(e.isPrimaryButtonDown()) {
	btnStandby=createV(e.getX(),e.getY());
		}
	});
	
	mid.setOnMouseReleased(e->{
		btnStandby2= null;
			
		});
	//create vertex from selected node
	mid.setOnDragDetected((e->{
		if(e.isPrimaryButtonDown()) {
	btnStandby2 =createV(e.getX(),e.getY());
	myE= createEdge(btnStandby, btnStandby2);
		}
	}));
	
	mid.setOnMouseDragged(e->{
		if(btnStandby2!=null) {
			btnStandby2.setLayoutX(e.getX());
			btnStandby2.setLayoutY(e.getY());
		}
	});
	
   }
   
   
    /**
     * Creates a Vertex using button component
     * @param me
     * @return node
     */   
   private Button createV(Double Xloc, Double Yloc) {
	  
	Button v = new Button();
	
	BackgroundFill st = new BackgroundFill(Color.DARKRED, new CornerRadii(33), null);
	v.setBackground(new Background(st));

	String strPowerP= JOptionPane.showInputDialog(null,"What is the name of the new PowerPlant?");
	v.setText(strPowerP);
	v.setTextFill(Color.ANTIQUEWHITE);
	Integer iWeight =Integer.parseInt(JOptionPane.showInputDialog(null,"What is the cost of using this plant?"));
	
	v.setPadding(new Insets(4,13,4,13));
	v.setLayoutX(Xloc);
	v.setLayoutY(Yloc);
        v.translateXProperty().bind(v.widthProperty().divide(-2));
        v.translateYProperty().bind(v.heightProperty().divide(-2));
	driveG.populateVertex(strPowerP, iWeight); 
        SaveToFile as= new SaveToFile();
       // as.writeToFile(strPowerP+":"+iWeight+":"+Xloc.intValue()+":"+Yloc.intValue());
	v.setOnDragDetected(ce-> collisonD(ce,v));
	v.setOnMouseDragged(ev->{ moveV(ev,v);});
	v.setOnMousePressed(value->removeN(value,v));
	v.setOnMouseReleased(e->{ removeVa(e, v);
		
	});
	c++;
	mid.getChildren().add(v);
	return v;
	
}
/**
 * Removes node
 * @param e
 * @param v
 */
protected void removeVa(MouseEvent e, Button v) {
		
		if(remove!=null) {
			mid.getChildren().remove(remove);
		}
		btnStandby2=null;
		remove= null;
	}

/**
 * Removes nodes
 * @param value
 * @param v
 */
protected void removeN(MouseEvent value, Button v) {
	
	if(value.isPrimaryButtonDown()) {
		btnStandby= v;
		remove= v; 
	}if(value.isSecondaryButtonDown()==true)
	{
		mid.getChildren().remove(remove);
		}
	
 
	}
/**
 * Detects collisions
 * @param ce retrieve mouse event selection 
 * @param v value representation node
 */
protected void collisonD(MouseEvent ce, Button v) {
	v.toFront();
	remove=null;
	if(ce.isPrimaryButtonDown()) {
		btnStandby2=createV(v.getLayoutX()+ce.getX()+v.getTranslateX(),v.getLayoutY()+ce.getY()+v.getTranslateY());
		myE= createEdge(btnStandby, btnStandby2);
	}	else if(ce.isSecondaryButtonDown()) {
		
	}
}

/**
 * Move the node around
 * @param ev action from user
 * @param v node targeted
 */
private void moveV(MouseEvent ev, Button v) {
	if(btnStandby2!=null) {
		v.setLayoutX(v.getLayoutX()+ev.getX()+v.getTranslateX());
		v.setLayoutY(v.getLayoutY()+ev.getY()+v.getTranslateY());
	}
	else if(ev.isSecondaryButtonDown()) {
	v.setLayoutX(v.getLayoutX()+ev.getX()+v.getTranslateX());
	v.setLayoutY(v.getLayoutY()+ev.getY()+v.getTranslateY());
	}
}

private Edge createEdge(Button src,Button dest) 
{

	Edge my = new Edge(src.getLayoutX(), src.getLayoutY(), dest.getLayoutX(), dest.getLayoutY());
	
	my.getLoc1X().bind(src.layoutXProperty());
	my.getLoc1Y().bind(src.layoutYProperty());
	my.getLoc2X().bind(dest.layoutXProperty());
	my.getLoc2Y().bind(dest.layoutYProperty());
	mid.getChildren().add(my);
	
	Graph<String>newG = driveG.getMyGraph();
	Vertex<String> myV1 = null,myV2 = null;
	for(Vertex<String> v: driveG.getVertices()) {
		if(v.getValue().equals(src.getText())) {
			myV1 = new Vertex<>(v);
		}else if(v.getValue().equals(dest.getText())) {
	       myV2 = new Vertex<>(v);
		}
	}
	Integer iWeight =Integer.parseInt(JOptionPane.showInputDialog(null,"What is the Edge cost?"));
	driveG.addEdge(iWeight, myV1, myV2);
	return my;
	
}
/**
 * DrawGraph method
 */
public void setLayout() {
    ArrayList<PowerPlant> pp= myFile.addPowerPlant();
  ArrayList<Route> route= myFile.readRoute();
    for(PowerPlant p :pp){
        DrawV(p.getpName(), p.getX(), p.getY());
    }
    for(Route  p :route){
        DrawE(p.getpName(),p.getcost(), p.getX1(), p.getY1(),p.getX2(),p.getY2());
    }
}
/**
 * Draw auto V
 * @param svalue
 * @param Xloc
 * @param Yloc 
 */
 private void DrawV (String svalue,double Xloc,double Yloc){
      Button v = new Button();
	int co=0;
	BackgroundFill st = new BackgroundFill(Color.DARKRED, new CornerRadii(33), null);
	v.setBackground(new Background(st));

	//String strPowerP= JOptionPane.showInputDialog(null,"What is the name of the new PowerPlant?");
	v.setText(svalue);
	v.setTextFill(Color.ANTIQUEWHITE);
	
	
	v.setPadding(new Insets(4,13,4,13));
	v.setLayoutX(Xloc);
	v.setLayoutY(Yloc);
        v.translateXProperty().bind(v.widthProperty().divide(-2));
        v.translateYProperty().bind(v.heightProperty().divide(-2));
	
	v.setOnDragDetected(ce-> collisonD(ce,v));
	v.setOnMouseDragged(ev->{ moveV(ev,v);});
	v.setOnMousePressed(value->removeN(value,v));
	v.setOnMouseReleased(e->{ removeVa(e, v);
		
	});
	c++;
	mid.getChildren().addAll(v);
  
  }

 
 private void DrawE(String n,Double cost,double Xloc,double Yloc, double x, double y){
    Button q= new Button();
    Button qq = new Button();
    q.setLayoutX(Xloc);
    q.setLayoutY(Yloc);
    qq.setLayoutX(x);
    qq.setLayoutY(y);
     Edge my = new Edge(Xloc, Yloc, x, y);
	my.setId(n);
	my.getLoc1X().bind(q.layoutXProperty());
	my.getLoc1Y().bind(q.layoutYProperty());
	my.getLoc2X().bind(qq.layoutXProperty());
	my.getLoc2Y().bind(qq.layoutYProperty());
	mid.getChildren().add(my);
 }

   
}
