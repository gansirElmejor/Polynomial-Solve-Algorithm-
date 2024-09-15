//函数区间求解算法
import java.util.ArrayList;
class SetUps {
    //配置函数的上下区间 E.G [-100,100]
    private double UPPER_BOUND = 100;
    private double LOWER_BOUND = -100;
    private double PRECISION = 0.001; //精确度，解精确到小数点后第几位.
    public double funn(double x) {
        //配置函数解析式 E.G y=x^2 - 10
        double y = Math.pow(x, 5) + Math.pow(x, 2);
        return y;
    }

    public double getB() {return this.UPPER_BOUND;}
    public double getA() {return this.LOWER_BOUND;}
    public double getPrecision() {return this.PRECISION;}
}
public class Main {
    public static void main(String[] args) {
        SetUps stup = new SetUps();
        Solver solver = new Solver(stup);
        solver.solve();
        ArrayList<Double> result = solver.getResult();
        if (result.size()== 0) {
            System.out.println("没有实数根 | No Real Root!");
        };
        for (int i=0;i<result.size();i++) {
            System.out.println("x="+String.valueOf(result.get(i)));
        }
    }
}
class Solver{
    SetUps stup;
    double PRECISION;
    ArrayList<Double> zeros;
    Solver(SetUps stup){
        this.stup = stup;
        this.PRECISION = stup.getPrecision();
        this.zeros = new ArrayList<Double>();
    }
    public void solve() {
        recSolve(stup.getA(),stup.getB());
    }
    public ArrayList<Double> getResult() {
        return this.zeros;
    }
    private void recSolve(double a, double b) {
        double mean = (b + a)/2; // 求平均数
        if (Math.abs((b-a))<PRECISION) {
            if (stup.funn(a)*stup.funn(b)<=0) {
                zeros.add(mean);
            }
            return;
        } else if (stup.funn(a)*stup.funn(mean) <= 0) {
            recSolve(a,mean);
            if (stup.funn(b)*stup.funn(mean) <= 0) { //Return Stack时检查下二分的另一半行不行
                recSolve(mean,b); 
            }
        } else if (stup.funn(b)*stup.funn(mean) <= 0) {
            recSolve(mean,b);
            if (stup.funn(a)*stup.funn(mean) <= 0) {
                recSolve(a,mean);
            }            
        }
    }
}
