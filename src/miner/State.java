package miner;

public abstract class State<T> {
	abstract public void Enter(T g);
	abstract public void Exit(T g);
	abstract public void Execute(T g);
}
