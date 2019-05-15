public class TestJNIPrimitive {
   static {
      System.loadLibrary("myjni"); // myjni.dll (Windows) or libmyjni.so (Unixes)
   }
 
   // Declare a native method average() that receives two ints and return a double containing the average
   private native double average(int n1, int n2);
 
   // Test Driver
   public static void main(String args[]) {
      System.out.println("In Java, the average is " + new TestJNIPrimitive().average(3, 2));
   }
}