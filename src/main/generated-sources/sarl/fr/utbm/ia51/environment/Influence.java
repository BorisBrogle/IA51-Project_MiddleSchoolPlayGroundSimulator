package fr.utbm.ia51.environment;

import fr.utbm.ia51.agents.Person;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Event;
import org.eclipse.xtext.xbase.lib.Pure;
import org.eclipse.xtext.xbase.lib.util.ToStringBuilder;

@SarlSpecification("0.9")
@SarlElementType(15)
@SuppressWarnings("all")
public class Influence extends Event {
  public final Person caller;
  
  public final int time;
  
  public final double wantedPosX;
  
  public final double wantedPosY;
  
  public Influence(final Person caller, final int time, final double wantedPosX, final double wantedPosY) {
    this.caller = caller;
    this.time = time;
    this.wantedPosX = wantedPosX;
    this.wantedPosY = wantedPosY;
  }
  
  @Override
  @Pure
  @SyntheticMember
  public boolean equals(final Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Influence other = (Influence) obj;
    if (other.time != this.time)
      return false;
    if (Double.doubleToLongBits(other.wantedPosX) != Double.doubleToLongBits(this.wantedPosX))
      return false;
    if (Double.doubleToLongBits(other.wantedPosY) != Double.doubleToLongBits(this.wantedPosY))
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + this.time;
    result = prime * result + (int) (Double.doubleToLongBits(this.wantedPosX) ^ (Double.doubleToLongBits(this.wantedPosX) >>> 32));
    result = prime * result + (int) (Double.doubleToLongBits(this.wantedPosY) ^ (Double.doubleToLongBits(this.wantedPosY) >>> 32));
    return result;
  }
  
  /**
   * Returns a String representation of the Influence event's attributes only.
   */
  @SyntheticMember
  @Pure
  protected void toString(final ToStringBuilder builder) {
    super.toString(builder);
    builder.add("caller", this.caller);
    builder.add("time", this.time);
    builder.add("wantedPosX", this.wantedPosX);
    builder.add("wantedPosY", this.wantedPosY);
  }
  
  @SyntheticMember
  private static final long serialVersionUID = -3476358242L;
}
