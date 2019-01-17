//This program converts any picture into a photo made up of ONLY black and white pixels

public class Milestone3{
	public static void main(String[] args){
		
		/* TESTING WAS HERE
		Picture test = new Picture ("C:\\Users\\user\\Pictures\\purpleFlower.jpg");
		grainy(test);
		test.write("C:\\Users\\user\\Pictures\\purpleFlower-grainy30000.jpg");
		*/
	
	}
	
	public static void grainy(Picture v){
	/*	-1 -1 -1   weighted kernal to focus on each pixel and then make it black/white 
		-1  9 -1	through 3d distance formula d=sqrt((red-255)^2 + (blue-255)^2 + (green-255)^2))
		-1 -1 -1 
	*/
		
		
		for(int x = 0; x < v.getWidth();x++){
			for(int y = 0; y < v.getHeight();y++){
				 
				int sumRedValues = 0;
				int sumBlueValues = 0;
				int sumGreenValues = 0;
							
				Pixel selected = v.getPixel(x,y);
												
				for(int k = x-1; k <= x+1; k++){
					for(int j = y-1; j <= y+1; j++){
												
						Pixel localPixel = v.getPixel(k,j);
						
						//ignores pixels on the edge of the picture
						if ((y == 0 || y == v.getHeight()-1 || x == 0 || x == v.getWidth()-1)){
							sumRedValues = selected.getRed();
							sumBlueValues = selected.getBlue();
							sumGreenValues = selected.getGreen();
						}

						//the values of the center are mutiplied by 9						
						else{				
							if(k == x && j == y){
								sumRedValues += (9*selected.getRed());
								sumBlueValues += (9*selected.getBlue());
								sumGreenValues += (9*selected.getGreen());											
								}
																		
						//get values of 8 local pixels around selected and subtract it to the 
						// 9 times the center rgb value.
						//this new total is the pixel's rgb value
							else if(k >= 0 && k < v.getWidth() && j >= 0 && j < v.getHeight()){
								sumRedValues -= localPixel.getRed();
								sumBlueValues -= localPixel.getBlue();
								sumGreenValues -= localPixel.getGreen();
								}
														
							}													
						}
					}

				//if the rgb value > 255, set it to 255
				//if rgb value < 0, set it to 0
				if(sumRedValues > 255){
					selected.setRed(255);
				}
				else if(sumRedValues < 0){
					selected.setRed(0);
				}
				else{
					selected.setRed(sumRedValues);
				}
				
				if(sumGreenValues > 255){
					selected.setGreen(255);
				}
				else if(sumGreenValues < 0){
					selected.setGreen(0);
				}
				else{
					selected.setGreen(sumGreenValues);
				}
				
				if(sumBlueValues > 255){
					selected.setBlue(255);
				}
				else if(sumBlueValues < 0){
					selected.setBlue(0);
				}
				else{
					selected.setBlue(sumBlueValues);
				}
				
				
				double distancedUnsquared = Math.pow(selected.getRed()-255,2) 
				+ Math.pow(selected.getBlue()-255,2) + Math.pow(selected.getRed()-255,2);
				
				double distancedSquared = Math.sqrt(distancedUnsquared);
				
				//converts all pixels to black or white based on their distance from white (255,255,255).
				
				if(distancedSquared < 10){
					selected.setRed(255);
					selected.setGreen(255);
					selected.setBlue(255);
				}
				else{
					selected.setRed(0);
					selected.setGreen(0);
					selected.setBlue(0);
				}								
			}
		}
	}	

}
			