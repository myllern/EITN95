package assign1.task5_v2;

public class Global {
	public static final int DISPATCHER_ARRIVAL = 1, ARRIVAL = 2, SERVED = 3, MEASURE = 4;
	public static final int RANDOM = 1, ROUND_ROUND = 2, SMALLEST_NBR_JOBS = 3;
	public static double time = 0;

	// public static int nrOfArrivals = 100;

	public static void reset() {
		time = 0;
	}

}
