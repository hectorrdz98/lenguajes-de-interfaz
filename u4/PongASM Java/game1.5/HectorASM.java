public class HectorASM {
    static {
        System.loadLibrary("hectorASM");
    }
    
    public native int add(int n1, int n2);
    public native int sub(int n1, int n2);
    public native int mul(int n1, int n2);
    public native int div(int n1, int n2);
    public native int inc(int n1);
    public native int dec(int n1);
}