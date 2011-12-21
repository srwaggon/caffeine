package caffeine.rule;

public interface Rule {
	public boolean appliesTo(Object o);

	public void applyOn(Object o);
}
