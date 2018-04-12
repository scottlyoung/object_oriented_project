package Server;

public abstract class Item
{
	private String name;

	public Item(String _name)
	{
		this.name = _name;
	}

	public String getName()
	{
		return name;
	}
	@Override
	public String toString() {
		return getName();
	}
}
