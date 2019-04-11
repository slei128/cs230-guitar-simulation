import java.util.Random; //used in pluck()
import javafoundations.BoundedQueue;
/**
 * GuitarString models a vibrating guitar string of a given frequency.
 *
 * @author Shirley Lei, Emily Yin
 * @version April 8th, 2019
 */
public class GuitarString{
    private double frequency;
    private BoundedQueue<Double> bq;
    private static final double DECAY_FACTOR = 0.994;
    private static final int SAMPLING_RATE = 44100;
    private static final double SAMPLE_RANGE = 0.5;

    /**
     * The constructor creates a model of guitar string at rest of the given 
     * frequency, using a fixed sampling rate of 44,100
     * @param frequency the fundamental frequency of vibration
     */
    public GuitarString(double frequency){
        this.frequency = frequency;
        //initializes a bounded queue of the desired capacity N, where N by definition is 
        //sampling rate 44,100 divided by the frequency, rounded up to the nearest integer
        bq = new BoundedQueue<Double>((int) Math.ceil(SAMPLING_RATE/frequency));
        //fills the bounded queue with N zeros to model a guitar string at rest
        while (!bq.isFull()) {
            bq.enqueue(0.0);
        }
    }

    /**
     * Pluck simulates the pluck of a guitar by replacing new sound samples
     */
    public void pluck(){
        Random rand = new Random();
        for (int i=0; i<bq.size(); i++) { 
            //dequeue original value
            bq.dequeue();
            //get new random value within given range
            double randomVal = rand.nextDouble()-SAMPLE_RANGE;
            //System.out.println(i + " " + randomVal);
            //enqueue new random value
            bq.enqueue(randomVal);
        }
    }

    /**
     * Returns a sound sample
     * @return double a sound sample
     */
    public double sample(){
        return bq.first();
    }

    /**
     * Tic simulates the behaviour of the sound wave as time passes, taking 
     * into account its characteristic of sound decay
     */
    public void tic(){
        //store original first sample in firstSample, then dequeue it
        double firstSample = bq.dequeue();
        //get new first sample, and name it secondSample
        double secondSample = bq.first();
        //get average of the two multiplied by given decay factor, then enqueue that average
        double average = (DECAY_FACTOR*(firstSample+secondSample)/2.0);
        bq.enqueue(average);
    }

    /**
     * Provides a string representation of a GuitarString
     * @return string representation of a GuitarString
     */
    public String toString() {
        String s = "";
        for (int i=0;i<bq.size(); i++) {
            double firstElement = bq.dequeue();
            s+= firstElement + "\n";
            bq.enqueue(firstElement);
        }
        return s;
    }

    /**
     * Helper method used in testing, counts number of samples
     * @return total total number of samples
     */
    private int count() {
        int total = 0;
        for (int i=0;i<bq.size(); i++) {
            double firstElement = bq.dequeue();
            total+= 1;
            bq.enqueue(firstElement);
        }
        return total;
    }

    /**
     * Helper method used in testing, checks whether samples are in correct
     * range (between .5 and -.5)
     * @return boolean true if samples are in correct range, false otherwise
     */
    private boolean checkRange() {
        boolean inRange = true;
        for (int i=0;i<bq.size(); i++) {
            double firstElement = bq.dequeue();
            if (!(firstElement>-0.5 && firstElement<0.5)) {
                inRange = false;
                return inRange;
            }
            bq.enqueue(firstElement);
        }
        return inRange;
    }

    /**
     * Trivial (preliminary) testing for GuitarString methods
     * @param args an array string (unused in this case)
     */
    public static void main (String[] args) { 
        System.out.println("-------------Tests on high frequency=5000--------------");
        System.out.println("------------------------------");
        GuitarString gs = new GuitarString(5000);
        System.out.println("gs initialized " + gs.count() + " samples:\n" + gs);
        System.out.println("first sample is (expect 0.0) " + gs.sample());
        System.out.println("------------------------------");
        gs.pluck();
        System.out.println("gs filled by plucking, still " + gs.count() + " samples:\n" + gs); 
        System.out.println("first sample is: " + gs.sample());
        System.out.println("all samples within -.5 to .5 range is (expect true): " + gs.checkRange());
        System.out.println("------------------------------");
        gs.tic();
        System.out.println("gs ticked, still " + gs.count() + " samples:\n" + gs);
        System.out.println("first sample is: " + gs.sample());
        System.out.println("all samples within -.5 to .5 range is (expect true): " + gs.checkRange());
        System.out.println("------------------------------");

        System.out.println("-------------Tests on mid frequency=500--------------");
        System.out.println("------------------------------");
        GuitarString gs2 = new GuitarString(500);
        System.out.println("gs2 initialized " + gs2.count() + " samples:\n" + gs2);
        System.out.println("first sample is (expect 0.0) " + gs2.sample());
        System.out.println("------------------------------");
        gs2.pluck();
        System.out.println("gs2 filled by plucking, still " + gs2.count() + " samples:\n" + gs2); 
        System.out.println("first sample is: " + gs2.sample());
        System.out.println("all samples within -.5 to .5 range is (expect true): " + gs2.checkRange());
        System.out.println("------------------------------");
        gs2.tic();
        System.out.println("gs2 ticked, still " + gs2.count() + " samples:\n" + gs2);
        System.out.println("first sample is: " + gs2.sample());
        System.out.println("all samples within -.5 to .5 range is (expect true): " + gs2.checkRange());
        System.out.println("------------------------------");

        System.out.println("-------------Tests on low frequency=100--------------");
        System.out.println("------------------------------");
        GuitarString gs3 = new GuitarString(100);
        System.out.println("gs3 initialized " + gs3.count() + " samples:\n" + gs3);
        System.out.println("first sample is (expect 0.0) " + gs3.sample());
        System.out.println("------------------------------");
        gs3.pluck();
        System.out.println("gs3 filled by plucking, still " + gs3.count() + " samples:\n" + gs3); 
        System.out.println("first sample is: " + gs3.sample());
        System.out.println("all samples within -.5 to .5 range is (expect true): " + gs3.checkRange());
        System.out.println("------------------------------");
        gs3.tic();
        System.out.println("gs3 ticked, still " + gs3.count() + " samples:\n" + gs3);
        System.out.println("first sample is: " + gs3.sample());
        System.out.println("all samples within -.5 to .5 range is (expect true): " + gs3.checkRange());
        System.out.println("------------------------------");
    }
}
