package proje;

public class Dispatcher {
	private ResourceManager resource_manager;
	private MemoryManager memory_manager;
	private UserQueue user_queue;
	private RealTimeQueue rt_queue;
	private ProcessList process_list;
	private GBG gbg;
	private FCFS fcfs;
	private RR rr;
	int timer;
	int q;

	public Dispatcher() {
		resource_manager = new ResourceManager();
		memory_manager = new MemoryManager();
		user_queue = new UserQueue();
		rt_queue = new RealTimeQueue();
		process_list = new ProcessList();
		timer = 0;
		q = 1;
		gbg = new GBG();
		fcfs = new FCFS();
		rr = new RR();
	}

	public void Execute() {
		try {
			process_list.ReadFile("giris.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}

		int rt_process_count = process_list.getRTProcessCount();
		while (!user_queue.isEmpty() || !rt_queue.isEmpty() || !process_list.isEmpty()) {

			if (!process_list.isEmpty()) {
				while (!process_list.isEmpty() && process_list.getFirst().getArrivalTime() == timer) {
					Process process = process_list.getFirst();
					int priority = process.getPriority();
					if (!memory_manager.isValid(process)) {
						if (priority == 0) {
							process.setStatus(ProcessStatus.REJECTEDREALMEM);
							rt_process_count--;
						} else
							process.setStatus(ProcessStatus.REJECTEDUSERMEM);
						process.PrintProcessError();
					} else if (!resource_manager.isValid(process)) {
						if (priority == 0) {
							process.setStatus(ProcessStatus.REJECTEDREALRESOURCE);
							rt_process_count--;
						} else
							process.setStatus(ProcessStatus.REJECTEDUSERRESOURCE);
						process.PrintProcessError();
					} else {
						if (priority == 0)
							rt_queue.Enqueue(process);
						else
							user_queue.Enqueue(process);
					}

					process_list.getProcessList().remove(0);
				}
			}

			if (user_queue.isEmpty() && rt_queue.isEmpty())
				timer++;
			else {
				if (!user_queue.isEmpty() && !user_queue.check4RunRR())
					timer = gbg.runGBG(user_queue, rt_queue, resource_manager, memory_manager, timer, q);
				if (!rt_queue.isEmpty())
					timer = fcfs.runFCFS(rt_queue, user_queue, process_list, resource_manager, memory_manager, timer, rt_process_count);
				if (process_list.isEmpty() && user_queue.check4RunRR())
					timer = rr.runRR(user_queue, resource_manager, memory_manager, timer, q);
			}

			System.out.println("Program Sonlandı!");


		}
	}
}
