import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Filters {

  public static void main(String[] args) throws IOException {

    // Read in the image file.
    File f = new File("dog.png");
    BufferedImage img = ImageIO.read(f);

    // For debugging
    /*System.out.println("Before:");
    System.out.println(Utilities.getRGBArray(0, 0, img)[0]);
    System.out.println(Utilities.getRGBArray(0, 0, img)[1]);
    System.out.println(Utilities.getRGBArray(0, 0, img)[2]);
    // 92 40 27

    //Apply a filter implementation on img.
    applyGrayscale(img);

    //For debugging
    System.out.println("After:");
    System.out.println(Utilities.getRGBArray(0, 0, img)[0]);
    System.out.println(Utilities.getRGBArray(0, 0, img)[1]);
    System.out.println(Utilities.getRGBArray(0, 0, img)[2]);
    // 53 53 53

    // Write the result to a new image file.
    f = new File("dog_filtered.png");
    ImageIO.write(img, "png", f);*/

    //Norak filter
    /*applyNorak(img);

    System.out.println("Narok Filter ");
    System.out.println(Utilities.getRGBArray(0, 0, img)[0]);
    System.out.println(Utilities.getRGBArray(0, 0, img)[1]);
    System.out.println(Utilities.getRGBArray(0, 0, img)[2]);

    f = new File("dog_filtered2.png");
    ImageIO.write(img, "png", f);*/

    //Border

    /*int [] border = {255, 255, 255};

    applyBorder(img, 30, border);

    f = new File("dog_filtered3.png");
    ImageIO.write(img, "png", f);*/

    //Mirror Filter
    /*applyMirror(img);

    f = new File("dog_filtered4.png");
    ImageIO.write(img, "png", f);*/

    //Blurred filter
    /*applyBlur(img);

    f = new File("dog_filtered5.png");
    ImageIO.write(img, "png", f);*/

    //Custom filter
    /*applyCustom(img);

    f = new File("dog_filtered6.png");
    ImageIO.write(img, "png", f); */


  }

  public static void applyGrayscale(BufferedImage img) {

    int average = 0; //This variable will store the average of the RGB value for each pixel
    
    //Traverse through entire image 
    for (int i = 0; i < img.getHeight(); i ++) {
      for (int j = 0; j < img.getWidth(); j ++) {

        //Updates the value of average to be the sum of RGB values at coordinate (i, j) divided by 3
        average = ((Utilities.getRGBArray(i, j, img)[0]) + (Utilities.getRGBArray(i, j, img)[1]) + (Utilities.getRGBArray(i, j, img)[2])) / 3;

        //Array that will be used in setRGB. Its three spaces store the same number (average) as all three RGB values will be the same 
        int [] rgb = {average, average, average};

        Utilities.setRGB(rgb, i, j, img); //Update the RGB value on coordinate (i, j)

      }

    }

  }

  public static void applyNorak(BufferedImage img) {
    
    int average = 0; //This variable will store the average of the RGB value of each pixel

    //Traverse through entire image
    for (int i = 0; i < img.getHeight(); i ++) {
      for (int j = 0; j < img.getWidth(); j ++) {

        //Updates the value of average to be the sum of RGB values at coordinate (i, j) divided by 3. This will be used to determine if a 
        //pixel will be changed or if it will remain the same
        average = ((Utilities.getRGBArray(i, j, img)[0]) + (Utilities.getRGBArray(i, j, img)[1]) + (Utilities.getRGBArray(i, j, img)[2])) / 3;

        if (average > 153) {

          //If the average is greater than 153, all 3 RGB values will be changed to average
          int [] rgb = {average, average, average};

          Utilities.setRGB(rgb, i, j, img);//Update the RGB value on coordinate (i, j)

        }

      }

    }

  }

  public static void applyBorder(BufferedImage img, int borderThickness, int[] borderColor) {
    
    //These first two nested for loops will fill in the borders on the left and top of the image
    for (int i = 0; i < img.getHeight(); i ++) {

      for (int j = 0; j < img.getWidth(); j ++) {

        // If j is less than or equal to the border thickness, the RGB value for that pixel will be changed to the values contained
        //in borderColor (this same idea applies to the other 3 if statements in this method)
        if (j < borderThickness) { 

          Utilities.setRGB(borderColor, i, j, img);

        }

        if (i < borderThickness) {

          Utilities.setRGB(borderColor, i, j, img);

        }

      }

    }//end of first nested for loops (left and top)

    //The next two nested for loops will fill in the borders on the right and bottom of the image.
    for (int i = 0; i < img.getHeight(); i ++) {

      for (int j = 0; j < img.getWidth(); j ++) {

        if (j < borderThickness) {

          //These if statements are basically the same as the first two, but here I used img.getHeight() - 1 - i and img.getWidth() - 1 - j
          //so that the pixels are filled in in reverse order (right and bottom sides)
          Utilities.setRGB(borderColor, (img.getHeight() - 1 - i ), (img.getWidth() - 1 - j), img);

        }

        if (i < borderThickness) {

          Utilities.setRGB(borderColor, (img.getHeight() - 1 - i), (img.getWidth() - 1 - j), img);

        }

      }

    }// end of second for loops (right and bottom)

    //I think that there is no need for two pairs of nested for loops in this method, but I decided to keep it like this because
    //it helps me visualize the sides getting filled in better

  }

  public static void applyMirror(BufferedImage img) {

    //Nested for loops to traverse through image
    for(int i = 0; i < img.getHeight(); i ++) {

      //img.getWidth() is divided by 2 so the pixels are only changed once (to flip it horizontally) and they don't go back to their starting position
      for (int j = 0; j < img.getWidth() / 2; j ++) { 

        //This array temporally stores the RGB values on the leftmost pixel
        int tempRGB [] = {Utilities.getRGBArray(i, j, img)[0], Utilities.getRGBArray(i, j, img)[1], Utilities.getRGBArray(i, j, img)[2]};
        //This uses getRGBArray to get the RGB values for the rightmost pixel and assigns those values to i and j (leftmost pixel)
        Utilities.setRGB((Utilities.getRGBArray(i,img.getWidth() - 1 - j, img)), i, j, img);
        //This stores the original RGB values at i,j and assigns them to the rightmost pixel
        Utilities.setRGB(tempRGB, i, (img.getWidth() - 1 - j), img);

      }

    }


  }

  public static void applyBlur(BufferedImage img) { 

    //These three variables will keep track of the averages for each RGB value so that the average can be computed
    int averageRed = 0;
    int averageGreen = 0; 
    int averageBlue = 0; 

    //Nested for loops to traverse through image (staring on 1 so it doesn't count the pixels that don't have 8 neighbours)
    for(int i = 1; i < img.getHeight()-1; i ++) {

      for (int j = 1; j < img.getWidth()-1; j ++) {

      //Computes the average of all 9 pixels (the one on the center + its 8 neighbours) by getting the red values for each pixel, adding
      //them up, and dividing by 9 (same principle applies for the next two averages, but for green and blue instead) 
      averageRed = (Utilities.getRGBArray(i-1, j-1, img)[0] + Utilities.getRGBArray(i-1, j, img)[0] + Utilities.getRGBArray(i-1, j+1, img)[0] +
                    Utilities.getRGBArray(i, j-1, img)[0] + Utilities.getRGBArray(i, j, img)[0] + Utilities.getRGBArray(i, j+1, img)[0] +
                    Utilities.getRGBArray(i+1, j-1, img)[0] + Utilities.getRGBArray(i+1, j, img)[0] + Utilities.getRGBArray(i+1, j+1, img)[0])/9;

      averageGreen = (Utilities.getRGBArray(i-1, j-1, img)[1] + Utilities.getRGBArray(i-1, j, img)[1] + Utilities.getRGBArray(i-1, j+1, img)[1] +
                    Utilities.getRGBArray(i, j-1, img)[1] + Utilities.getRGBArray(i, j, img)[1] + Utilities.getRGBArray(i, j+1, img)[1] +
                    Utilities.getRGBArray(i+1, j-1, img)[1] + Utilities.getRGBArray(i+1, j, img)[1] + Utilities.getRGBArray(i+1, j+1, img)[1])/9;

      averageBlue = (Utilities.getRGBArray(i-1, j-1, img)[2] + Utilities.getRGBArray(i-1, j, img)[2] + Utilities.getRGBArray(i-1, j+1, img)[2] +
                    Utilities.getRGBArray(i, j-1, img)[2] + Utilities.getRGBArray(i, j, img)[2] + Utilities.getRGBArray(i, j+1, img)[2] +
                    Utilities.getRGBArray(i+1, j-1, img)[2] + Utilities.getRGBArray(i+1, j, img)[2] + Utilities.getRGBArray(i+1, j+1, img)[2])/9;

        //Array that stores all three averages
        int [] rgb = {averageRed, averageGreen, averageBlue};

        Utilities.setRGB(rgb, i, j, img); //the RGB value at (i,j) is changed to the averages computed             

      }

    }

  }

  public static void applyCustom(BufferedImage img) {

    //Array that holds the RGB values for white that will be used to add dots to the image
    int white [] = {255, 255, 255};

    //This variable will store the average of the RGB value for each pixel. I will be changing the blue values for this average on all pixels 
    //so the entire image ends up with blue tones
    int average = 0;
    
    for (int i = 0; i < img.getHeight(); i ++) {
      for (int j = 0; j < img.getWidth(); j ++) {

        int greenValue = Utilities.getRGBArray(i, j, img)[1]; //I created this variable so the green value on each pixel stays the same

        //Computes the average RGB at pixel (i,j)
        average = ((Utilities.getRGBArray(i, j, img)[0]) + (Utilities.getRGBArray(i, j, img)[1]) + (Utilities.getRGBArray(i, j, img)[2])) / 3;

        //This array will have the appropriate values so the image has a blue filter. All red values are kept to 0, the green value
        //stays the same, and the blue value is replaced with the average
        int [] rgb = {0, greenValue, average};

        Utilities.setRGB(rgb, i, j, img); //The pixel color is replaced with the new RGB

      }

    }

    //These two nested for loops will just add white dots on some places of the image if i is divisible by 25 and j is divisible by 40,
    //or if i is divisible by 15 and j is divisible by 70
    for (int i = 0; i < img.getHeight(); i ++) {

      for (int j = 0; j < img.getWidth(); j ++) {

        if (i % 25 == 0 && j % 40 == 0 || i % 15 == 0 && j % 70 == 0) {

          Utilities.setRGB(white, i, j, img);

        }


      }

    }

    //These for loops will mirror the left side of the image 
    for(int i = 0; i < img.getHeight(); i ++) {

      for (int j = 0; j < img.getWidth(); j ++) {

        //Array that stores the RGB values at (i,j)
        int tempRGB [] = {Utilities.getRGBArray(i, j, img)[0], Utilities.getRGBArray(i, j, img)[1], Utilities.getRGBArray(i, j, img)[2]};
        //These RGB values are then copied to the corresponding rightmost side of the image
        Utilities.setRGB(tempRGB, i, (img.getWidth() - 1 - j), img);

      }

    }

    //These for loops will mirror the bottom side of the image 
    for(int i = 0; i < img.getHeight(); i ++) {

      for (int j = 0; j < img.getWidth(); j ++) {

        //Array that stores the RGB values at the bottommost pixels (img.getHeight() - 1-i) and rightmost pixels (img.getWidth() - 1-j)
        int tempRGB [] = {Utilities.getRGBArray(img.getHeight() - 1 - i, img.getWidth() - 1 - j, img)[0], 
        Utilities.getRGBArray(img.getHeight() - 1 - i, img.getWidth() - 1 - j, img)[1], 
        Utilities.getRGBArray(img.getHeight() - 1 - i, img.getWidth() - 1 - j, img)[2]};
        //These RGB values are then copied to the corresponding uppermost side of the image 
        Utilities.setRGB(tempRGB, i, (img.getWidth() - 1 - j), img);

      }

    }


  }
}
