package fr.utbm.ia51.environment;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Address;
import io.sarl.lang.core.Event;

@SarlSpecification("0.8")
@SarlElementType(15)
@SuppressWarnings("all")
public class RunBeginingOfStep extends Event {
  @SyntheticMember
  public RunBeginingOfStep() {
    super();
  }
  
  @SyntheticMember
  public RunBeginingOfStep(final Address source) {
    super(source);
  }
  
  @SyntheticMember
  private final static long serialVersionUID = 588368462L;
}
