package state_transition;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.lang.invoke.ConstantCallSite;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("상태 전이 테스팅")
@ExtendWith(MockitoExtension.class)
class TempControllerTest {

	@DisplayName("State Testing : Idle->Operating->Cooling->Operating->Heating")
	@Test
	void should_visit_all_states() {
		TempController tc = new TempController();
		TempController spyc = spy(tc);

		spyc.start();
		
		verify(spyc).powerOn();
		assertEquals(ControllerState.OPERATING, spyc.getState());
		
		spyc.high();
		
		verify(spyc).activateCooler();
		assertEquals(ControllerState.COOLING, spyc.getState());
		
		spyc.inRange();
		
		verify(spyc).deactivateCooler();
		assertEquals(ControllerState.OPERATING, spyc.getState());
		
		spyc.low();
		
		verify(spyc).activateHeater();
		assertEquals(ControllerState.HEATING, spyc.getState());
	}
	
	@DisplayName("Single Transitions Testing : Idle->Operating->Cooling->Operating->Heating->Operating->Idle")
	@Test
	void should_visit_0_switch_states() {
		TempController tc = new TempController();
		TempController spyc = spy(tc);

		spyc.start();
		
		verify(spyc).powerOn();
		assertEquals(ControllerState.OPERATING, spyc.getState());
		
		spyc.high();
		
		verify(spyc).activateCooler();
		assertEquals(ControllerState.COOLING, spyc.getState());
		
		spyc.inRange();
		
		verify(spyc).deactivateCooler();
		assertEquals(ControllerState.OPERATING, spyc.getState());
		
		spyc.low();
		
		verify(spyc).activateHeater();
		assertEquals(ControllerState.HEATING, spyc.getState());
		
		spyc.inRange();
		
		verify(spyc).deactivateCooler();
		assertEquals(ControllerState.OPERATING, spyc.getState());
		
		spyc.stop();
		
		verify(spyc).powerOff();
		assertEquals(ControllerState.IDLE, spyc.getState());
	}

}
