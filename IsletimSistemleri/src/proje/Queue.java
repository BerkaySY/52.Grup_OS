package proje;

interface Queue {
	public void Enqueue(Process process);
	public void Dequeue();
	public Process getFirst();
	public boolean isEmpty();
}
