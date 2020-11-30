
/**
 * Investigation class
 * 
 * @author TritiumGaming
 */
public class Investigation {

	public enum Ghost {
		BANSHEE		(new Evidence[] {Evidence.EMF_5, Evidence.FREEZING_TEMP, Evidence.FINGERPRINTS}), 
		DEMON		(new Evidence[] {Evidence.FREEZING_TEMP, Evidence.SPIRIT_BOX, Evidence.GHOST_WRITING}), 
		JINN		(new Evidence[] {Evidence.EMF_5, Evidence.SPIRIT_BOX, Evidence.GHOST_ORBS}), 
		MARE		(new Evidence[] {Evidence.FREEZING_TEMP, Evidence.SPIRIT_BOX, Evidence.GHOST_ORBS}), 
		ONI			(new Evidence[] {Evidence.EMF_5, Evidence.SPIRIT_BOX, Evidence.GHOST_WRITING}), 
		PHANTOM		(new Evidence[] {Evidence.EMF_5, Evidence.FREEZING_TEMP, Evidence.GHOST_ORBS}), 
		POLTERGEIST	(new Evidence[] {Evidence.SPIRIT_BOX, Evidence.GHOST_ORBS, Evidence.FINGERPRINTS}), 
		REVENANT	(new Evidence[] {Evidence.EMF_5, Evidence.GHOST_WRITING, Evidence.FINGERPRINTS}), 
		SHADE		(new Evidence[] {Evidence.EMF_5, Evidence.GHOST_WRITING, Evidence.GHOST_ORBS}), 
		SPIRIT		(new Evidence[] {Evidence.SPIRIT_BOX, Evidence.GHOST_WRITING, Evidence.FINGERPRINTS}), 
		WRAITH		(new Evidence[] {Evidence.FREEZING_TEMP, Evidence.SPIRIT_BOX, Evidence.FINGERPRINTS}), 
		YUREI		(new Evidence[] {Evidence.FREEZING_TEMP, Evidence.GHOST_WRITING, Evidence.GHOST_ORBS});

		private Evidence[] evidence = new Evidence[3];
		
		Ghost(Evidence[] evidence) {
			this.evidence = evidence;
		}
		
		public int getRating() {
			
			int i = 0;
			
			for(Evidence e: evidence)
				if(e.getRuling() == Evidence.Ruling.NEGATIVE)
					i = -5;
				else
					if(e.getRuling() == Evidence.Ruling.POSITIVE)
						i++;
			
			return i;
		}

		public String toString() {

			String s = name() + ":\n";
			for (Evidence e : evidence)
				s += "    " + e.name() + " [" + e.getRuling() + "]\n";

			return s;
			
		}

	};

	enum Evidence {
		EMF_5, FREEZING_TEMP, SPIRIT_BOX, GHOST_WRITING, GHOST_ORBS, FINGERPRINTS;

		private Ruling ruling = Ruling.NEUTRAL;
		public enum Ruling {
			NEGATIVE, NEUTRAL, POSITIVE
		};

		public void setRuling(Ruling ruling) {
			this.ruling = ruling;
		}
		
		public Ruling getRuling() {
			return ruling;
		}
		
		public String toString() {
			return ruling.name();
		}

	};

	public void setEvidenceRuling(Evidence e, Evidence.Ruling r) {
		e.setRuling(r);
	}
	
	public Ghost[] getGhosts() {
		return Ghost.values();
	}
	
	public String toString() {

		String s = "";
		for (Ghost g : Ghost.values()) 
				s += g + ": " + g.getRating() + "\n";
		return s;
		
	}
	
}
