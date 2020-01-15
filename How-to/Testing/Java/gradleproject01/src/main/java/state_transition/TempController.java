package state_transition;

public class TempController {
	private ControllerState state;
	public TempController() {
		state = ControllerState.IDLE;
	}
	public void start() {
		if (state == ControllerState.IDLE) {
			powerOn();
			state = ControllerState.OPERATING;
		}
	}
	
	public void stop() {
		if (state == ControllerState.OPERATING) {
			powerOff();
			state = ControllerState.IDLE;
		}
	}
	
	public void high() {
		if (state == ControllerState.OPERATING) {
			activateCooler();
			state = ControllerState.COOLING;
		}
	}
	
	public void low() {
		if (state == ControllerState.OPERATING) {
			activateHeater();
			state = ControllerState.HEATING;
		}
	}
	
	public void inRange() {
		if (state == ControllerState.COOLING) deactivateCooler();
		if (state == ControllerState.HEATING) deactivateHeater();
		state = ControllerState.OPERATING;
	}
	
	public void setState(ControllerState state) { this.state = state; }
	public ControllerState getState() { return this.state; }
	
	/* required stubs */
	protected void powerOn() {}
	protected void powerOff() {}
	protected void activateHeater() {}
	protected void activateCooler() {}
	protected void deactivateHeater() {}
	protected void deactivateCooler() {}
}
