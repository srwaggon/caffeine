package caffeine.action;

public interface Action {
	public static Action Inaction = new Action(){	
		@Override
		public void performOn(Object o) {
			// Do nothing
		}
	};
		
	void performOn(Object o);
}
